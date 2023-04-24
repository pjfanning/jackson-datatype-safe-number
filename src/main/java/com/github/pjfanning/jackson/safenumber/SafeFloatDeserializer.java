package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeFloat;
import com.github.pjfanning.safenumberparser.SafeNumberParserConfig;

import java.io.IOException;

final class SafeFloatDeserializer extends StdDeserializer<SafeFloat> {

    static final SafeFloatDeserializer INSTANCE = new SafeFloatDeserializer();

    SafeFloatDeserializer() {
        super(SafeFloat.class);
    }

    @Override
    public SafeFloat deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final String input = p.getValueAsString();
        try {
            if (p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER)) {
                if (input.length() > SafeNumberParserConfig.getFloatMaxLength()) {
                    throw new ConstraintException(
                            "Failed to parse SafeFloat because the input is too long; max allowed chars is " +
                                    SafeNumberParserConfig.getFloatMaxLength());
                }
                return new SafeFloat(NumberInput.parseFloat(p.getValueAsString(), true));
            }
            return new SafeFloat(input);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
