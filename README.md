# Overview

This project contains a simple provider implementation for loading the HAL dataformat as Jackson provider.

The goal is to easily bootstrap loading the Jackson HAL dataformat prvider.

# Status

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.openapitools.jackson.dataformat/jackson-dataformat-hal-provider/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.openapitools.jackson.dataformat/jackson-dataformat-hal-provider/)
[![Javadoc](http://javadoc.io/badge/io.openapitools.jackson.dataformat/jackson-dataformat-hal-provider.svg)](https://www.javadoc.io/doc/io.openapitools.jackson.dataformat/jackson-dataformat-hal-provider)
[![Build status](https://github.com/openapi-tools/jackson-dataformat-hal-provider/actions/workflows/ci.yaml/badge.svg)](https://github.com/openapi-tools/jackson-dataformat-hal-provider/actions/workflows/ci.yaml)

# Usage

This project contains a JAX-RS provider class, `JacksonHALJsonProvider` which must be registered in your JAX-RS environment. How this is done depends on the JAX-RS framework you use, as they each have a different way of doing this. Some may provide package scanning, looking for any classes with the `@Provider` annotation. In that case, you must make sure that the `io.openapitools.jackson.dataformat.hal.provider` package is scanned. You may also need to register it directly, either in a class or in a configuration file. 

## Eclipse Jersey
For the [Eclipse Jersey](https://eclipse-ee4j.github.io/jersey/) JAX-RS framework, there are several ways to register the JAX-RS provider. This depends on how you set up your application. It is possible to scan for the providers on the classpath which doesn't require any additional configuration. But you can also register the JAX-RS providers through a [`ResourceConfig`](https://eclipse-ee4j.github.io/jersey.github.io/apidocs/latest/jersey/org/glassfish/jersey/server/ResourceConfig.html) class, either by creating a new instance of it or creating a subclass of it. 

If you create a new instance of `ResourceConfig`, your configuration could look like this.

```java
ResourceConfig resourceConfig = new ResourceConfig(MyResource.class);
resourceConfig.register(new JacksonHALJsonProvider(new HALMapper));
```

If you create a subclass of `ResourceConfig`, your configuration could look like this.

```java
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        register(new JacksonHALJsonProvider(new HALMapper()));
    }
}
```
