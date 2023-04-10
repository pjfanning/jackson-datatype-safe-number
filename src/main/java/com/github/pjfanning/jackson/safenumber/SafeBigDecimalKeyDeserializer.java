package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeBigDecimal;

import java.io.IOException;

final class SafeBigDecimalKeyDeserializer extends KeyDeserializer {

    static final SafeBigDecimalKeyDeserializer INSTANCE = new SafeBigDecimalKeyDeserializer();

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        try {
            return new SafeBigDecimal(key);
        } catch (ConstraintException e) {
            throw new IOException(e);
        }
    }
}
