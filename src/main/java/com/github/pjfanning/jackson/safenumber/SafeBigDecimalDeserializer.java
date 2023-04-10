package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeBigDecimal;

import java.io.IOException;

final class SafeBigDecimalDeserializer extends StdDeserializer<SafeBigDecimal> {

    static final SafeBigDecimalDeserializer INSTANCE = new SafeBigDecimalDeserializer();

    SafeBigDecimalDeserializer() {
        super(SafeBigDecimal.class);
    }

    @Override
    public SafeBigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        try {
            return new SafeBigDecimal(p.getValueAsString());
        } catch (ConstraintException e) {
            throw new IOException(e);
        }
    }
}
