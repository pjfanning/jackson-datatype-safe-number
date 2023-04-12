package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeBigInteger;

import java.io.IOException;

final class SafeBigIntegerKeyDeserializer extends KeyDeserializer {

    static final SafeBigIntegerKeyDeserializer INSTANCE = new SafeBigIntegerKeyDeserializer();

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        try {
            return new SafeBigInteger(key);
        } catch (ConstraintException e) {
            throw new IOException(e);
        }
    }
}
