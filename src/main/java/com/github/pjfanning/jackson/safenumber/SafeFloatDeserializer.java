package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeFloat;

import java.io.IOException;

final class SafeFloatDeserializer extends StdDeserializer<SafeFloat> {

    static final SafeFloatDeserializer INSTANCE = new SafeFloatDeserializer();

    SafeFloatDeserializer() {
        super(SafeFloat.class);
    }

    @Override
    public SafeFloat deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return new SafeFloat(p.getValueAsString());
        } catch (ConstraintException e) {
            throw new IOException(e);
        }
    }
}
