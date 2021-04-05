package io.openapitools.jackson.dataformat.hal.provider;

import com.fasterxml.jackson.jaxrs.cfg.Annotations;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.openapitools.jackson.dataformat.hal.HALMapper;
import io.openapitools.jackson.dataformat.hal.JacksonHALModule;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 * Extension of the default {@link JacksonJsonProvider} to support the <code>application/hal+json</code>
 * content type using {@link HALMapper} enabling the {@link io.openapitools.jackson.dataformat.hal.JacksonHALModule}.
 */
@Provider
@Produces(JacksonHALJsonProvider.MEDIA_TYPE_APPLICATION_HAL_JSON)
@Consumes(JacksonHALJsonProvider.MEDIA_TYPE_APPLICATION_HAL_JSON)
public class JacksonHALJsonProvider extends JacksonJsonProvider {
    /** Defining content type for JSON HAL */
    public static final String MEDIA_TYPE_APPLICATION_HAL_JSON = "application/hal+json";

    /**
     * Creating instance with default annotations which uses the {@link HALMapper}.
     */
    public JacksonHALJsonProvider() {
        super(new HALMapper());
    }

    /**
     * Creating instance with custom list of annotations which uses the {@link HALMapper}.
     * 
     * @param annotationsToUse Sets of annotations (Jackson, JAXB) that provider should support
     */
    public JacksonHALJsonProvider(Annotations[] annotationsToUse) {
        super(new HALMapper(), annotationsToUse);
    }
}
