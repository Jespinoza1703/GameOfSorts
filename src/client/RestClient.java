package client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import game.logic.trees.AVLTree;
import game.logic.trees.BinaryTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.MissingResourceException;

public class RestClient {

    private static Logger logger = LoggerFactory.getLogger(RestClient.class);
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
    private static ObjectMapper mapper = new ObjectMapper();

    private static final Marker CLIENT = MarkerFactory.getMarker("CLIENT");
    private static final String REST_URI = "http://localhost:9080/WebOfSorts_war_exploded/waves/";

    private Client client = Client.create();
    private WebResource wavesResource = client.resource(REST_URI);

    public Wave getWave(long id) {
        WebResource waveResource = wavesResource.path(String.valueOf(id));
        waveResource.type(MediaType.APPLICATION_JSON);

        // Server return an OK response
        ClientResponse head = waveResource.head();
        if (head.getStatus() != 200) {
            throw new MissingResourceException("Server couldn't find the wave", "Wave", String.valueOf(id));
        }

        // Process the message
        String message = waveResource.get(String.class);
        logger.info(CLIENT, "Client received the wave with status: " + head.getStatus());
        logger.info(CLIENT, "Message: " + message);
        return mapWave(message);
    }

    private Wave mapWave(String json){
        Wave wave = null;
        try {
            // Sets the custom serializer


            // Maps the Wave
            JsonNode root = mapper.readTree(json);

            long id = root.get("id").asLong();
            int size = root.get("size").asInt();
            String formation = root.get("formation").asText();

            JsonNode dragonsList = root.get("dragonsList");
            List list = mapper.treeToValue(dragonsList, List.class);

            JsonNode dragonsBinaryTree = mapper.readTree(String.valueOf( root.get("dragonsBinaryTree")));
            BinaryTree binaryTree = mapBinaryTree(dragonsBinaryTree);

            JsonNode dragonsAVLTree =  mapper.readTree(String.valueOf(root.get("dragonsAVLTree")));
            AVLTree avlTree = mapAvlTree(dragonsAVLTree);

            wave = new Wave(id, size, formation, list, binaryTree, avlTree);
            logger.info(CLIENT, "Mapped the Wave from json");
        } catch (IOException e) {
            logger.error(CLIENT, "Couldn't map the Wave form json", e);
        }
        return wave;
    }


    private BinaryTree mapBinaryTree(JsonNode node){
        return null;
    }

    private AVLTree mapAvlTree(JsonNode node){
        return null;
    }
}
