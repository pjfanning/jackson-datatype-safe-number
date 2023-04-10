package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;

public class SafeNumberModule extends Module {
    @Override
    public String getModuleName() {
        return "SafeNumberModule";
    }

    @Override
    public Version version() {
        return null;
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addDeserializers(new SafeNumberDeserializerResolver());
        context.addSerializers(new SafeNumberSerializerResolver());
        context.addKeyDeserializers(new SafeNumberKeyDeserializerResolver());
        context.addKeySerializers(new SafeNumberKeySerializerResolver());
    }
}
