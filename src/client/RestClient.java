package client;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.MissingResourceException;

public class RestClient {

    private static Logger logger = LoggerFactory.getLogger(RestClient.class);
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
    private static final Marker CLIENT = MarkerFactory.getMarker("CLIENT");
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String REST_URI = "http://localhost:9080/WebOfSorts_war_exploded/waves/";

    private Client client = Client.create();
    private WebResource wavesResource = client.resource(REST_URI);
    private WebResource.Builder wavesBuilder = wavesResource.accept(MediaType.APPLICATION_JSON);

    public Wave getWave(long id) {
        WebResource waveRes = wavesResource.path(String.valueOf(id));

        ClientResponse head = waveRes.head();
        if (head.getStatus() != 200) {
            throw new MissingResourceException("Server couldn't find the wave", "Wave", String.valueOf(id));
        }

        String message = waveRes.get(String.class);
        logger.info(CLIENT, "Client received the wave with status: " + head.getStatus());
        return mapJson(message);
    }

    private Wave mapJson(String json){
        Wave wave = null;
        try {
            wave = mapper.readValue(json, Wave.class);
            logger.info(CLIENT, "Mapped the Wave from json");
        } catch (IOException e) {
            logger.error(CLIENT, "Couldn't map the Wave form json", e);
        }
        return wave;
    }
}
