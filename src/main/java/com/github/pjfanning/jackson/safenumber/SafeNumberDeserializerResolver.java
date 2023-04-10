package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.github.pjfanning.safenumberparser.SafeBigDecimal;
import com.github.pjfanning.safenumberparser.SafeBigInteger;
import com.github.pjfanning.safenumberparser.SafeDouble;
import com.github.pjfanning.safenumberparser.SafeFloat;

public class SafeNumberDeserializerResolver extends Deserializers.Base {
    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        if (type.getRawClass().isAssignableFrom(SafeDouble.class)) {
            return SafeDoubleDeserializer.INSTANCE;
        }
        if (type.getRawClass().isAssignableFrom(SafeFloat.class)) {
            return SafeFloatDeserializer.INSTANCE;
        }
        if (type.getRawClass().isAssignableFrom(SafeBigDecimal.class)) {
            return SafeBigDecimalDeserializer.INSTANCE;
        }
        if (type.getRawClass().isAssignableFrom(SafeBigInteger.class)) {
            return SafeBigIntegerDeserializer.INSTANCE;
        }
        return null;
    }
}
