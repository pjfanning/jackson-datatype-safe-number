package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeDouble;
import com.github.pjfanning.safenumberparser.SafeNumberParserConfig;

import java.io.IOException;

final class SafeDoubleDeserializer extends StdDeserializer<SafeDouble> {

    static final SafeDoubleDeserializer INSTANCE = new SafeDoubleDeserializer();

    SafeDoubleDeserializer() {
        super(SafeDouble.class);
    }

    @Override
    public SafeDouble deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final String input = p.getValueAsString();
        try {
            if (p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER)) {
                if (input.length() > SafeNumberParserConfig.getDoubleMaxLength()) {
                    throw new ConstraintException(
                            "Failed to parse SafeDouble because the input is too long; max allowed chars is " +
                                    SafeNumberParserConfig.getDoubleMaxLength());
                }
                return new SafeDouble(NumberInput.parseDouble(p.getValueAsString(), true));
            }
            return new SafeDouble(input);
        } catch (ConstraintException e) {
            throw new IOException(e);
        }
    }
}
