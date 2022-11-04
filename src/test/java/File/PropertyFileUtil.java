package File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyFileUtil {
    public static Properties properties;
    public static void loadPropertyFile() throws Exception {
        FileInputStream file = new FileInputStream("./src/test/resources/RunTest.properties");
         properties = new Properties();
        properties.load(file);
    }

    public static String getProperty(String key){
        if(!properties.isEmpty() && properties!=null) {
            return properties.getProperty(key);
        }else{
            System.out.println("Properties Object is null");
            return "null";
        }
    }
}
