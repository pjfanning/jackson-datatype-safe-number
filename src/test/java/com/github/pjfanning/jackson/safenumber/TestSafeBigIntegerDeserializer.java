package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.pjfanning.safenumberparser.SafeBigInteger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSafeBigIntegerDeserializer {
    @Test
    void testSimpleValue() throws JsonProcessingException {
        String num = "123456789";
        ObjectMapper mapper = JsonMapper.builder().addModule(new SafeNumberModule()).build();
        SafeBigInteger sbi = mapper.readValue(num, SafeBigInteger.class);
        assertEquals(new BigDecimal(num).toBigInteger(), sbi.toBigInteger());
    }
}
