package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.pjfanning.safenumberparser.SafeBigInteger;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSafeBigIntegerSerializer {
    @Test
    void testLargeValue() throws Exception {
        String num = "123456789012345678901234567890";
        ObjectMapper mapper = JsonMapper.builder().addModule(new SafeNumberModule()).build();
        String json = mapper.writeValueAsString(new SafeBigInteger(num));
        SafeBigInteger sbi = mapper.readValue(json, SafeBigInteger.class);
        assertEquals(new BigInteger(num), sbi.toBigInteger());
    }

    @Test
    void testLargeValueWithoutModule() throws Exception {
        String num = "123456789012345678901234567890";
        ObjectMapper mapper = JsonMapper.builder().build();
        String json = mapper.writeValueAsString(new SafeBigInteger(num));
        ObjectMapper mapper2 = JsonMapper.builder().addModule(new SafeNumberModule()).build();
        SafeBigInteger sbi = mapper2.readValue(json, SafeBigInteger.class);
        assertEquals(new BigInteger(num), sbi.toBigInteger());
    }
}
