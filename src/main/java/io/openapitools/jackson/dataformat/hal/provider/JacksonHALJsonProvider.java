package io.openapitools.jackson.dataformat.hal.provider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.cfg.Annotations;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import io.openapitools.jackson.dataformat.hal.HALMapper;
import io.openapitools.jackson.dataformat.hal.JacksonHALModule;

/**
 * Extension of the default {@link JacksonJsonProvider} to support the <code>application/hal+json</code> content type
 * using an {@link ObjectMapper} configured with the {@link JacksonHALModule} (just like {@link HALMapper}).</br>
 *</br>
 * An {@link ObjectMapper} may already be provided through JAX-RS, usually through a provider class that
 * implements {@link ContextResolver}<{@link ObjectMapper}>. Such an {@link ObjectMapper} may be configured differently
 * and may still be used for plain JSON. So this provider will make a copy of that {@link ObjectMapper} and
 * register {@link JacksonHALModule} on the copy, preserving any configuration that was copied over.
 */
@Provider
@Produces(JacksonHALJsonProvider.MEDIA_TYPE_APPLICATION_HAL_JSON)
@Consumes(JacksonHALJsonProvider.MEDIA_TYPE_APPLICATION_HAL_JSON)
public class JacksonHALJsonProvider extends JacksonJsonProvider {
    public static final String MEDIA_TYPE_APPLICATION_HAL_JSON = "application/hal+json";
    public static final JacksonHALModule JACKSON_HAL_MODULE = new JacksonHALModule();

    public JacksonHALJsonProvider() {
        this(null, BASIC_ANNOTATIONS);
    }

    public JacksonHALJsonProvider(Annotations[] annotationsToUse) {
        this(null, annotationsToUse);
    }

    public JacksonHALJsonProvider(ObjectMapper mapper) {
        this(mapper == null ? mapper : mapper.registerModule(JACKSON_HAL_MODULE), BASIC_ANNOTATIONS);
    }

    public JacksonHALJsonProvider(ObjectMapper mapper, Annotations[] annotationsToUse) {
        super(mapper == null ? mapper : mapper.registerModule(JACKSON_HAL_MODULE), annotationsToUse);
    }

    @Override
    protected ObjectMapper _locateMapperViaProvider(Class<?> type, MediaType mediaType) {
        ObjectMapper mapper = super._locateMapperViaProvider(type, mediaType);
        if (mapper == null) {
            return null;
        }
        return mapper.copy().registerModule(JACKSON_HAL_MODULE);
    }
}
