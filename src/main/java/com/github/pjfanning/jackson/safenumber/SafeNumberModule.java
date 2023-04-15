package com.github.pjfanning.jackson.safenumber;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.Module;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SafeNumberModule extends Module {
    private Version version = Version.unknownVersion();

    public SafeNumberModule() {
        initModule();
    }

    @Override
    public String getModuleName() {
        return "SafeNumberModule";
    }

    @Override
    public Version version() {
        return version;
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addDeserializers(new SafeNumberDeserializerResolver());
        context.addKeyDeserializers(new SafeNumberKeyDeserializerResolver());
    }

    private void initModule() {
        try (InputStream stream = SafeNumberModule.class.getClassLoader().getResourceAsStream(
                "com/github/pjfanning/jackson/safenumber/build.properties")) {
            if (stream != null) {
                Properties props = new Properties();
                props.load(stream);
                VersionUtil.parseVersion(props.getProperty("version"),
                        props.getProperty("groupId"),
                        props.getProperty("artifactId"));
            }
        } catch (IOException ie) {

        }
    }
}
