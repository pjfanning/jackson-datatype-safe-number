# jackson-datatype-safe-number
Jackson bindings for [safe-number-parser](https://github.com/pjfanning/safe-number-parser) types.

You can add the `SafeNumberModule` to your ObjectMapper. When this is enabled, classes like SafeBigInteger will be
serialized/deserialized in a similar way to their equivalents in the core Java runtime - but with validations enabled
to help you avoid issues with malicious input.

```
import com.github.pjfanning.jackson.safenumber.SafeNumberModule;

ObjectMapper mapper = JsonMapper.builder().addModule(new SafeNumberModule()).build();
```
or
```
import com.github.pjfanning.jackson.safenumber.SafeNumberModule;

ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new SafeNumberModule());
```

## Releases

jackson-datatype-safe-number [releases](https://github.com/pjfanning/jackson-datatype-safe-number/tags) are in Maven Central. If you use Jackson 2.12, use the latest jackson-datatype-safe-number 2.12.x release. Likewise for Jackson 2.13 and 2.14. A release for Jackson 2.15 will follow soon.
