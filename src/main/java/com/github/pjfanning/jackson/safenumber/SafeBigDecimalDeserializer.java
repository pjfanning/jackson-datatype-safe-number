package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.pjfanning.safenumberparser.SafeBigDecimal;

import java.io.IOException;

final class SafeBigDecimalDeserializer extends StdDeserializer<SafeBigDecimal> {

    static final SafeBigDecimalDeserializer INSTANCE = new SafeBigDecimalDeserializer();

    SafeBigDecimalDeserializer() {
        super(SafeBigDecimal.class);
    }

    @Override
    public SafeBigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return new SafeBigDecimal(p.getValueAsString());
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
