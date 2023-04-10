package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.pjfanning.safenumberparser.SafeBigInteger;

import java.io.IOException;

final class SafeBigIntegerKeySerializer extends JsonSerializer<SafeBigInteger> {

    static final SafeBigIntegerKeySerializer INSTANCE = new SafeBigIntegerKeySerializer();

    @Override
    public void serialize(SafeBigInteger value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeFieldName(value.toString());
    }
}
