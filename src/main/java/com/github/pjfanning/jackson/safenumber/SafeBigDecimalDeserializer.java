package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeBigDecimal;
import com.github.pjfanning.safenumberparser.SafeNumberParserConfig;

import java.io.IOException;
import java.math.BigDecimal;

final class SafeBigDecimalDeserializer extends StdDeserializer<SafeBigDecimal> {

    static final SafeBigDecimalDeserializer INSTANCE = new SafeBigDecimalDeserializer();

    SafeBigDecimalDeserializer() {
        super(SafeBigDecimal.class);
    }

    @Override
    public SafeBigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final String input = p.getValueAsString();
        try {
            if (p.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER)) {
                if (input.length() > SafeNumberParserConfig.getBigDecimalMaxLength()) {
                    throw new ConstraintException(
                            "Failed to parse SafeBigDecimal because the input is too long; max allowed chars is " +
                                    SafeNumberParserConfig.getBigDecimalMaxLength());
                }
                final BigDecimal bigDecimal = NumberInput.parseBigDecimal(p.getValueAsString(), true);
                if (Math.abs(bigDecimal.scale()) > SafeNumberParserConfig.getBigDecimalMaxScale()) {
                    throw new ConstraintException(
                            "Failed to parse SafeBigDecimal because the scale is too large; max allowed scale is " +
                                    SafeNumberParserConfig.getBigDecimalMaxScale());
                }
                return new SafeBigDecimal(bigDecimal);
            }
            return new SafeBigDecimal(input);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
