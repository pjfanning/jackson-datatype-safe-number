package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeFloat;

import java.io.IOException;

final class SafeFloatKeyDeserializer extends KeyDeserializer {

    static final SafeFloatKeyDeserializer INSTANCE = new SafeFloatKeyDeserializer();

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        try {
            return new SafeFloat(key);
        } catch (ConstraintException e) {
            throw new IOException(e);
        }
    }
}
