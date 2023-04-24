package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.github.pjfanning.safenumberparser.SafeDouble;

import java.io.IOException;

final class SafeDoubleKeyDeserializer extends KeyDeserializer {

    static final SafeDoubleKeyDeserializer INSTANCE = new SafeDoubleKeyDeserializer();

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        try {
            return new SafeDouble(key);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
