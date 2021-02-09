package com.github.fernandotaa.partner.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * Utility with static methods for json actions.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convert a {@link Object} to another Object using {@link JsonPath}.
     *
     * @param object - {@link Object} to convert
     * @return - {@link Object} converted
     * @throws JsonProcessingException
     */
    public static Object jsonPath(Object object) throws JsonProcessingException {
        return JsonPath.read(objectMapper.writeValueAsString(object), "$");
    }
}
