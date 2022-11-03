package File;

import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonFileUtil {

    public static String loadJsonFile(String filePath) throws Exception {
        Object parse = new JSONParser().parse(new FileReader(filePath));
        JSONObject object = (JSONObject) parse;
        return object.toJSONString();
    }

    public static Object read(String json, String jsonPath){
        Object data = JsonPath.read(json, jsonPath);
         return  data;
    }
}
