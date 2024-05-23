package it.ms.fiscalcodetool.utils.generators;

import org.springframework.stereotype.Component;

import it.ms.fiscalcodetool.exceptions.FiscalCodeException;
import it.ms.fiscalcodetool.utils.Constants;
import lombok.Data;

@Data
@Component
public class FiscalCodeValidator {

    private boolean isValid = false;
    private String error = null;
    private String validMessage = Constants.VALID_MESSAGE;
    private boolean isHomocodeFiscalCode = false;

    public boolean validateFiscalCode(String fiscalCode) {
        resetValues();
        try {
            if (fiscalCode.isEmpty()) {
                errorCode(0);
            }
            if (fiscalCode.length() != 16) {
                errorCode(1);
            }
            if (!fiscalCode.matches(Constants.FISCALCODE_REGEX)) {
                errorCode(2);
            }

            fiscalCode = fiscalCode.toUpperCase();
            char[] fiscalCodeArray = fiscalCode.toCharArray();

            for (int i = 0; i < Constants.HOMOCODICIAN_POS_ARRAY.length; i++) {
            	if (!Character.isDigit(fiscalCodeArray[Constants.HOMOCODICIAN_POS_ARRAY[i]])) {
            		if ( !Constants.HOMOCODICIAN_MAP.containsKey(Character.toString(fiscalCodeArray[Constants.HOMOCODICIAN_POS_ARRAY[i]])) ) {
            			errorCode(3);
            		}
            		fiscalCodeArray[Constants.HOMOCODICIAN_POS_ARRAY[i]] = Constants.HOMOCODICIAN_MAP.get(Character.toString(fiscalCodeArray[Constants.HOMOCODICIAN_POS_ARRAY[i]])).toCharArray()[0];
                    setHomocodeFiscalCode(true);
            	}
            }

            int odd = 0;
            int even = Constants.EVEN_CHAR_MAP.get(Character.toString(fiscalCodeArray[14]));

            for (int i = 0; i < 13; i += 2) {
                even += Constants.EVEN_CHAR_MAP.get(Character.toString(fiscalCodeArray[i]));
                odd += Constants.ODDS_CHAR_MAP.get(Character.toString(fiscalCodeArray[i + 1]));
            }

            if (!(Constants.CONTROL_CHAR_ARRAY[(odd + even) % 26].equals(Character.toString(fiscalCodeArray[15])))) {
                errorCode(4);
            }

            for (int i = 0; i < Constants.HOMOCODICIAN_POS_ARRAY.length; i++) {
                if (!Character.isDigit(fiscalCodeArray[Constants.HOMOCODICIAN_POS_ARRAY[i]])) {
                    fiscalCodeArray[Constants.HOMOCODICIAN_POS_ARRAY[i]] = Constants.HOMOCODICIAN_MAP.get(Character.toString(fiscalCodeArray[Constants.HOMOCODICIAN_POS_ARRAY[i]])).charAt(0);
                }
            }
            setValid(true);
        } catch (FiscalCodeException e) {
            setError(e.getMessage());
            setValid(false);
        }
        return isValid();
    }

    private static void errorCode(int errorNumber) throws FiscalCodeException {
        String errMessage = (errorNumber < Constants.ERROR_LIST_MESSAGE.length) ? Constants.ERROR_LIST_MESSAGE[errorNumber] : "Unhandled exception";
        throw new FiscalCodeException(errMessage);
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getValidMessage() {
        return validMessage;
    }

    public void setValidMessage(String validMessage) {
        this.validMessage = validMessage;
    }

    public boolean isHomocodeFiscalCode() {
        return this.isHomocodeFiscalCode;
    }

    public void setHomocodeFiscalCode(boolean isHomocodeFiscalCode) {
        this.isHomocodeFiscalCode = isHomocodeFiscalCode;
    }   

    private void resetValues() {
        isValid = false;
        isHomocodeFiscalCode = false;
        validMessage = Constants.VALID_MESSAGE;
        error = null;
    }
}