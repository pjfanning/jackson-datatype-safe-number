package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.pjfanning.safenumberparser.SafeNumber;

import java.io.IOException;

final class SafeNumberKeySerializer extends JsonSerializer<SafeNumber> {

    static final SafeNumberKeySerializer INSTANCE = new SafeNumberKeySerializer();

    @Override
    public void serialize(SafeNumber value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeFieldName(value.toString());
    }
}
