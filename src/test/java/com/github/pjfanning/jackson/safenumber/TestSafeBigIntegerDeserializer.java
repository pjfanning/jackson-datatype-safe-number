package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.pjfanning.safenumberparser.SafeBigInteger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSafeBigIntegerDeserializer {
    @Test
    void testSimpleValue() throws JsonProcessingException {
        String num = "2e308";
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(SafeBigInteger.class, new SafeBigIntegerDeserializer());
        ObjectMapper mapper = JsonMapper.builder().addModule(simpleModule).build();
        SafeBigInteger sbi = mapper.readValue(num, SafeBigInteger.class);
        assertEquals(new BigDecimal(num).toBigInteger(), sbi.toBigInteger());
    }
}
