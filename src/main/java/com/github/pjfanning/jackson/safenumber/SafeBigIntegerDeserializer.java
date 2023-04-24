package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.pjfanning.safenumberparser.ConstraintException;
import com.github.pjfanning.safenumberparser.SafeBigInteger;
import com.github.pjfanning.safenumberparser.SafeNumberParserConfig;

import java.io.IOException;
import java.math.BigDecimal;

final class SafeBigIntegerDeserializer extends StdDeserializer<SafeBigInteger> {

    static final SafeBigIntegerDeserializer INSTANCE = new SafeBigIntegerDeserializer();

    SafeBigIntegerDeserializer() {
        super(SafeBigInteger.class);
    }

    @Override
    public SafeBigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final String input = p.getValueAsString();
        try {
            if (p.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER)) {
                if (input.length() > SafeNumberParserConfig.getBigIntegerMaxLength()) {
                    throw new ConstraintException(
                            "Failed to parse SafeBigInteger because the input is too long; max allowed chars is " +
                                    SafeNumberParserConfig.getBigIntegerMaxLength());
                }
                if (SafeNumberParserConfig.isBigIntegerENotationSupported()) {
                    final BigDecimal bigDecimal = NumberInput.parseBigDecimal(p.getValueAsString(), true);
                    if (Math.abs(bigDecimal.scale()) > SafeNumberParserConfig.getBigIntegerMaxScale()) {
                        throw new ConstraintException(
                                "Failed to parse SafeBigInger because the scale is too large; max allowed scale is " +
                                        SafeNumberParserConfig.getBigIntegerMaxScale());
                    }
                    return SafeNumberParserConfig.isBigIntegerExactConversionRequired()
                            ? new SafeBigInteger(bigDecimal.toBigIntegerExact())
                            : new SafeBigInteger(bigDecimal.toBigInteger());
                }
                return new SafeBigInteger(NumberInput.parseBigInteger(p.getValueAsString(), true));
            }
            return new SafeBigInteger(input);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
