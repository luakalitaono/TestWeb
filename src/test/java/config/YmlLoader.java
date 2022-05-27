package config;

import org.junit.Assert;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class YmlLoader {

    public static Map<String, Map<String, String>> getDataFromFile(String filename) {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = Files.newInputStream(Paths.get("src/test/resources/params/" + filename));
            return yaml.loadAs(inputStream, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
            return null;
        }
    }

    public static Map<String, String> getParamFromFile(String param, String filename) {
        Map<String, Map<String, String>> data = YmlLoader.getDataFromFile(filename);
        Assert.assertNotNull(data);

        Map<String, String> content = data.get(param);
        Assert.assertNotNull(content);

        return content;
    }

}
