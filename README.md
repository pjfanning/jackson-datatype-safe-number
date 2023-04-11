# jackson-datatype-safe-number
Jackson bindings for [safe-number-parser](https://github.com/pjfanning/safe-number-parser) types.

You can add the `SafeNumberModule` to your ObjectMapper. When this is enabled, classes like SafeBigInteger will be
serialized/deserialized in a similar way to their equivalents in the core Java runtime - but with validations enabled
to help you avoid issues with malicious input.
