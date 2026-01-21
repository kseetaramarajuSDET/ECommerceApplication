package bridgelabz.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class YamlReader {

    public static List<Map<String, Object>> getLoginData(String fileName) {

        Yaml yaml = new Yaml();
        InputStream inputStream =
                YamlReader.class
                        .getClassLoader()
                        .getResourceAsStream("testdata/" + fileName);

        Map<String, Object> data = yaml.load(inputStream);

        return (List<Map<String, Object>>) data.get("logins");
    }

}
