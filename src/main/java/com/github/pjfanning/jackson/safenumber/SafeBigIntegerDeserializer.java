package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeBigInteger;

import java.io.IOException;

final class SafeBigIntegerDeserializer extends StdDeserializer<SafeBigInteger> {

    static final SafeBigIntegerDeserializer INSTANCE = new SafeBigIntegerDeserializer();

    SafeBigIntegerDeserializer() {
        super(SafeBigInteger.class);
    }

    @Override
    public SafeBigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        try {
            return new SafeBigInteger(p.getValueAsString());
        } catch (ConstraintException e) {
            throw new IOException(e);
        }
    }
}
