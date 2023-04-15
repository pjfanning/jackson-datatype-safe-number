package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.pjfanning.safenumberparser.SafeBigDecimal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSafeBigDecimalSerializer {
    @Test
    void testLargeValue() throws Exception {
        String num = "2e308";
        ObjectMapper mapper = JsonMapper.builder().addModule(new SafeNumberModule()).build();
        String json = mapper.writeValueAsString(new SafeBigDecimal(num));
        SafeBigDecimal sbd = mapper.readValue(json, SafeBigDecimal.class);
        assertEquals(new BigDecimal(num), sbd.toBigDecimal());
    }

    @Test
    void testLargeValueWithoutModule() throws Exception {
        String num = "2e308";
        ObjectMapper mapper = JsonMapper.builder().build();
        String json = mapper.writeValueAsString(new SafeBigDecimal(num));
        ObjectMapper mapper2 = JsonMapper.builder().addModule(new SafeNumberModule()).build();
        SafeBigDecimal sbd = mapper2.readValue(json, SafeBigDecimal.class);
        assertEquals(new BigDecimal(num), sbd.toBigDecimal());
    }
}
