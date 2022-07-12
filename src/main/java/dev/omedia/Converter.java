package dev.omedia;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public final class Converter {

    public static void yamlToXml(String path) throws FileNotFoundException {
        Map<String, Object> obj = yamlToMap(path);
        System.out.println(obj);
    }
    private static Map<String, Object> yamlToMap(String path) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(path);
        return yaml.load(inputStream);
    }
    public static YamlObject yamlToObject(String path) throws FileNotFoundException {
        Map<String, Object> obj = yamlToMap(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(obj, YamlObject.class);
    }
}
