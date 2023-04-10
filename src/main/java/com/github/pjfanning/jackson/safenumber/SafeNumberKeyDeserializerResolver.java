package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.github.pjfanning.safenumberparser.SafeBigDecimal;
import com.github.pjfanning.safenumberparser.SafeBigInteger;

public class SafeNumberKeyDeserializerResolver implements KeyDeserializers {

    @Override
    public KeyDeserializer findKeyDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        if (type.getRawClass().isAssignableFrom(SafeBigDecimal.class)) {
            return SafeBigDecimalKeyDeserializer.INSTANCE;
        }
        if (type.getRawClass().isAssignableFrom(SafeBigInteger.class)) {
            return SafeBigIntegerKeyDeserializer.INSTANCE;
        }
        return null;
    }
}
