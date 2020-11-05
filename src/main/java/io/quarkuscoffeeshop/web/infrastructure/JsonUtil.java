package io.quarkuscoffeeshop.web.infrastructure;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@RegisterForReflection
public class JsonUtil {

    static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    static final Jsonb jsonb = JsonbBuilder.create();

    static String toJson(final Object object) {
        logger.debug("marshalling {} to JSON", object.toString());
        return jsonb.toJson(object);
    }

}
