package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pjfanning.safenumberparser.SafeNumber;

public class SafeNumberSerializerResolver extends Serializers.Base {
    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType javaType, BeanDescription beanDesc) {
        if (javaType.getRawClass().isAssignableFrom(SafeNumber.class)) {
            return SafeNumberSerializer.INSTANCE;
        }
        return null;
    }
}
