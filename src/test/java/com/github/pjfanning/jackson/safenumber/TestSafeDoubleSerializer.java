package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.pjfanning.safenumberparser.SafeDouble;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSafeDoubleSerializer {
    @Test
    void testLargeValue() throws Exception {
        String num = "1e308";
        ObjectMapper mapper = JsonMapper.builder().addModule(new SafeNumberModule()).build();
        String json = mapper.writeValueAsString(new SafeDouble(num));
        SafeDouble sd = mapper.readValue(json, SafeDouble.class);
        assertEquals(new Double(num), sd.toDouble());
    }

    @Test
    void testLargeValueWithoutModule() throws Exception {
        String num = "1e308";
        ObjectMapper mapper = JsonMapper.builder().build();
        String json = mapper.writeValueAsString(new SafeDouble(num));
        ObjectMapper mapper2 = JsonMapper.builder().addModule(new SafeNumberModule()).build();
        SafeDouble sd = mapper2.readValue(json, SafeDouble.class);
        assertEquals(new Double(num), sd.toDouble());
    }
}
