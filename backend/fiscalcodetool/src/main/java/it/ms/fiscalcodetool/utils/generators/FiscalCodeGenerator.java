package it.ms.fiscalcodetool.utils.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.ms.fiscalcodetool.exceptions.FiscalCodeException;
import it.ms.fiscalcodetool.models.entities.BelfioreEntity;
import it.ms.fiscalcodetool.models.request.GenerateRequest;
import it.ms.fiscalcodetool.repositories.FiscalCodeToolRepository;
import it.ms.fiscalcodetool.utils.Constants;
import lombok.Data;

@Data
@Component
public class FiscalCodeGenerator {

	@Autowired
	FiscalCodeToolRepository repository;

	private String fiscalCode = null;
	private List<String> homocodianFiscalCodes = null;
	private String error = null;

	public boolean generateFiscalCode(GenerateRequest request) {
		resetValues();
		StringBuilder cf = new StringBuilder("");
		try {
			cf.append(modifyNameOrSurname(request.getSurname(), false));
			cf.append(modifyNameOrSurname(request.getName(), true));
			cf.append(evaluateYear(request.getBirthDate()));
			cf.append(evaluateMonth(request.getBirthDate()));
			cf.append(evaluateDay(request.getBirthDate(), request.getGender()));
			cf.append(evaluateMunicipalityCode(request.getProvince(), request.getMunicipality()));
			cf.append(evaluateControlCode(cf.toString()));

			setFiscalCode(cf.toString().toUpperCase());
			return true;
		} catch (FiscalCodeException e) {
			setError(e.getMessage());
			return false;
		}
	}

	public boolean generateHomocodianFiscalCodes(String fiscalCode) {
		ArrayList<String> list = new ArrayList<>();
		try {
			list.add(fiscalCode);
			for (int i = 0; i < 128; i++) {				
				for (int j = Constants.HOMOCODICIAN_POS_ARRAY.length - 1; j >= 0; j--) {
					if ( i <= list.size()) {
						list = getHomocodeCF(list.get(i), list, j);
					} else {
						break;
					}
				}
			}
			Collections.sort(list);
			setHomocodianFiscalCodes(list);
			return true;
		} catch (Exception e) {
			setError(e.getMessage());
			return false;
		}
	}

	private ArrayList<String> getHomocodeCF(String fiscalCode, ArrayList<String> fiscalCodesHomocodian,	int homocodianPosition) {
		
		char[] fiscalCodeArray = fiscalCode.toCharArray();

		int pos = Constants.HOMOCODICIAN_POS_ARRAY[homocodianPosition];

		if (Character.isDigit(fiscalCodeArray[pos])) {

			String value = Character.toString(fiscalCodeArray[pos]);
			for (Map.Entry<String, String> entry : Constants.HOMOCODICIAN_MAP.entrySet()) {
				if (entry.getValue().equals(value)) {
					fiscalCodeArray[pos] = entry.getKey().charAt(0);

					String cfPartial = new String(fiscalCodeArray, 0, 15);
					String cf = cfPartial + evaluateControlCode(cfPartial);

					if (!fiscalCodesHomocodian.contains(cf)) {
						fiscalCodesHomocodian.add(cf);
					}
				}
			}
		}
		return fiscalCodesHomocodian;
	}

	private String evaluateYear(String birthDate) {
		int year = Integer.parseInt(birthDate.substring(6));
		return (String.format("%d", year % 100));
	}

	private String evaluateMonth(String birthDate) {
		String month = birthDate.substring(3, 5);
		for (int i = 0; i < Constants.MONTHS_CHAR_MAP.size(); i++) {
			if (month.equals(Constants.MONTHS_CHAR_MAP.values().toArray()[i])) {
				return Constants.MONTHS_CHAR_MAP.keySet().toArray()[i].toString();
			}
		}
		return null;
	}

	private String evaluateDay(String birthDate, String gender) {
		String dayString = birthDate.substring(0, 2);
		if (gender.equalsIgnoreCase(Constants.MALE_CHAR)) {
			return dayString;
		} else if (gender.equalsIgnoreCase(Constants.FEMALE_CHAR)) {
			if (dayString.substring(0, 1).equals("0")) {
				return String.valueOf(Integer.parseInt(dayString.substring(1)) + 40);
			} else {
				return String.valueOf(Integer.parseInt(dayString) + 40);
			}
		}
		return null;
	}

	private String modifyNameOrSurname(String nameOrSurname, boolean cod) {
		StringBuilder newString = new StringBuilder("");
		
		nameOrSurname = nameOrSurname.replaceAll(" ", "");
		nameOrSurname = nameOrSurname.toLowerCase();

		StringBuilder consonants = new StringBuilder(getConsonants(nameOrSurname));
		StringBuilder vowels = new StringBuilder(getVowels(nameOrSurname));

		if (consonants.length() == 3) {
			newString = consonants;
		} else if ((consonants.length() < 3) && (nameOrSurname.length() >= 3)) {
			newString = consonants;
			newString = new StringBuilder(addVowels(consonants, vowels));
		} else if ((consonants.length() < 3) && (nameOrSurname.length() < 3)) {
			newString = consonants;
			newString.append(vowels);
			newString = addX(newString);
		} else if (consonants.length() > 3) {
			consonants = removeConsecutiveDuplicates(consonants);
			if (consonants.length() < 3) {
				newString = new StringBuilder(addVowels(consonants, vowels));
			}
			newString = new StringBuilder(consonants.substring(0, 3));
		}
		return newString.toString().toUpperCase();
	}

	private StringBuilder addX(StringBuilder string) {
		while (string.length() < 3) {
			string.append("x");
		}
		return string;
	}

	private StringBuilder addVowels(StringBuilder string, StringBuilder vowels) {
		int index = 0;
		while (string.length() < 3) {
			string.append(vowels.charAt(index));
			index++;
		}
		return string;
	}

	private String getVowels(String string) {
		string = string.replaceAll("[^aeiou]", "");
		return string;
	}

	private String getConsonants(String string) {
		string = string.replaceAll("[^bcdfghjklmnpqrstvwxyz]", "");
		return string;
	}

	private String evaluateMunicipalityCode(String province, String municipality) throws FiscalCodeException {
		String municipalityCode = "";
		BelfioreEntity entity = repository.findByProvinceAndMunicipality(province.toUpperCase(), municipality.toUpperCase());

		if ( entity == null || entity.getBelfioreCode() == null) {
			setError("Match for " + province + " - " + municipality + " not found.");
			throw new FiscalCodeException(error);
		} else {
			municipalityCode = entity.getBelfioreCode().trim().toUpperCase();
		}

		return municipalityCode;
	}

	private String evaluateControlCode(String partialFiscalCode) {
		int odds = 0;
		int even = 0;

		for (int i = 0; i < 13; i += 2) {
			even += Constants.EVEN_CHAR_MAP.get(Character.toString(partialFiscalCode.charAt(i)));
			odds += Constants.ODDS_CHAR_MAP.get(Character.toString(partialFiscalCode.charAt(i + 1)));
		}
		int control = (odds + even) % 26;

		return Constants.CONTROL_CHAR_ARRAY[control];
	}

	private void resetValues() {
		fiscalCode = null;
		homocodianFiscalCodes = null;
		error = null;
	}

	public static StringBuilder removeConsecutiveDuplicates(StringBuilder sb) {
		StringBuilder result = new StringBuilder();
		
		if (sb.length() == 0) {
			return result;
		}
	
		char prevChar = sb.charAt(0);
		result.append(prevChar);
	
		for (int i = 1; i < sb.length(); i++) {
			char currentChar = sb.charAt(i);
			if (currentChar != prevChar) {
				result.append(currentChar);
				prevChar = currentChar;
			}
		}	
		return result;
	}
	
}
