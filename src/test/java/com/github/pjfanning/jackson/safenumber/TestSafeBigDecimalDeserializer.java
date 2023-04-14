package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.pjfanning.safenumberparser.SafeBigDecimal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSafeBigDecimalDeserializer {
    @Test
    void testLargeValue() throws JsonProcessingException {
        String num = "2e308";
        ObjectMapper mapper = JsonMapper.builder().addModule(new SafeNumberModule()).build();
        SafeBigDecimal sbd = mapper.readValue(num, SafeBigDecimal.class);
        assertEquals(new BigDecimal(num), sbd.toBigDecimal());
    }
}
