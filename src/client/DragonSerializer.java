package client;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import game.entities.Dragon;

import java.io.IOException;

public class DragonSerializer extends StdSerializer<Dragon> {

    public DragonSerializer() {
        this(null);
    }

    public DragonSerializer(Class<Dragon> t) {
        super(t);
    }

    @Override
    public void serialize(Dragon dragon, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("parentAge", dragon.getParentAge());
        jsonGenerator.writeNumberField("age", dragon.getAge());
        jsonGenerator.writeStringField("rank", dragon.getRank());
        jsonGenerator.writeStringField("name", dragon.getName());
        jsonGenerator.writeNumberField("lives", dragon.getLives());
        jsonGenerator.writeNumberField("fire_rate", dragon.getFire_rate());
        jsonGenerator.writeEndObject();
    }
}
