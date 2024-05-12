package it.ms.fiscalcodetool.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Utility {
    
    private Map<String, Map<String, Map<String, String>>> COD_BELFIORE;

    public Map<String, Map<String, Map<String, String>>> getCodBelfioreMap() {
        
        Map<String, Map<String, Map<String, String>>> resultMap = new HashMap<>();
        
        String filePath = "src/main/resources/files/COD_BELFIORE.json";

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray codBelfioreArray = jsonObject.getJSONArray("COD_BELFIORE");

            for (int i = 0; i < codBelfioreArray.length(); i++) {
                JSONObject item = codBelfioreArray.getJSONObject(i);

                String flagComuneNormal = item.getString("FLAG_COMUNE_NORMAL").trim();
                String siglaProvincia = item.getString("SIGLA_PROVINCIA");
                String descrProvinciaComune = item.getString("DESCR_PROV_COMUNE").trim();
                String comuneCodFisc = item.getString("COMUNE_COD_FISC");

                resultMap.computeIfAbsent(flagComuneNormal, k -> new HashMap<>())
                        .computeIfAbsent(siglaProvincia, k -> new HashMap<>())
                        .put(descrProvinciaComune, comuneCodFisc);
            }       
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
        
    }
}
