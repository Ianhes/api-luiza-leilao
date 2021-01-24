package br.com.luiza.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonParse {

    private static ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
    public static String toJson(Object object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            log.error(" Erro de parse Json: {} ", e.getMessage());
            return null;
        }
    }


    public static Object fromJson(String json, Object object) {
        try {
            return mapper.readValue(json, object.getClass());
        } catch (Exception e) {
            log.error(" Erro de parse Json: {} ", e.getMessage());
            return null;
        }
    }
}
