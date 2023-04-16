package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeDouble;

import java.io.IOException;

final class SafeDoubleDeserializer extends StdDeserializer<SafeDouble> {

    static final SafeDoubleDeserializer INSTANCE = new SafeDoubleDeserializer();

    SafeDoubleDeserializer() {
        super(SafeDouble.class);
    }

    @Override
    public SafeDouble deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return new SafeDouble(p.getValueAsString());
        } catch (ConstraintException e) {
            throw new IOException(e);
        }
    }
}
