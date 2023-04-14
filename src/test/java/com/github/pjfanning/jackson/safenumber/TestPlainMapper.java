package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.pjfanning.safenumberparser.SafeBigDecimal;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class TestPlainMapper {
    @Test
    void testSerialization() throws Exception {
        Quote quote = new Quote("init", new SafeBigDecimal("1234.56"));
        ObjectMapper mapper = JsonMapper.builder().build();
        String json = mapper.writeValueAsString(quote);
        assertTrue(json.contains("\"value\":1234.56"));
    }

    @Test
    void testDeserialization() throws Exception {
        Quote quote = new Quote("init", new SafeBigDecimal("1234.56"));
        ObjectMapper mapper = JsonMapper.builder().build();
        String json = mapper.writeValueAsString(quote);
        Quote deserialized = mapper.readValue(json, Quote.class);
        assertEquals(quote, deserialized);
    }

    @Test
    @Disabled("fails because Jackson uses double implicitly for unknown number types and this value is too big for double")
    void testDeserializationBigValue() throws Exception {
        Quote quote = new Quote("init", new SafeBigDecimal("2e308"));
        ObjectMapper mapper = JsonMapper.builder().build();
        String json = mapper.writeValueAsString(quote);
        Quote deserialized = mapper.readValue(json, Quote.class);
        assertEquals(quote, deserialized);
    }
}
