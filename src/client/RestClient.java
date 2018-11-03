package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import game.entities.Dragon;
import game.logic.trees.AVLTree;
import game.logic.trees.BinaryTree;
import game.logic.trees.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
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
        logger.info(CLIENT, "Client received wave with id: " + id + " status: " + head.getStatus());
        return mapWave(message);
    }

    public Wave getNewWave(int size) {
        WebResource waveResource = wavesResource.path("size=" + size);
        waveResource.type(MediaType.APPLICATION_JSON);

        // Process the message
        String message = waveResource.post(String.class);
        logger.info(CLIENT, "Client received new wave: " + message);
        return mapWave(message);
    }

    public Wave getWaveSorted(Wave wave, String sortMethod){
        long id = wave.id;
        WebResource waveResource = wavesResource.path(id + ".sort=" + sortMethod);

        // Process the message
        String message = null;
        try {
            message = mapper.writeValueAsString(wave);
            logger.info(CLIENT, "Mapped Wave to Json: " + message);
        } catch (JsonProcessingException e) {
            logger.error(CLIENT, "Couldn't map Wave to Json: " + e);
        }

        // Send message to the server
        waveResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(String.class, message);

        logger.info(CLIENT, "Client ask for Wave sorted: " +sortMethod + "   " + message);
        return getWave(id);
    }


    /** Mappers **/

    private Wave mapWave(String json) {
        Wave wave = null;
        try {
            // Sets the custom serializer


            // Maps the Wave
            JsonNode root = mapper.readTree(json);

            long id = root.get("id").asLong();
            int size = root.get("size").asInt();
            String formation = root.get("formation").asText();

            JsonNode dragonsList = root.get("dragonsList");
            List<Dragon> list = mapList(dragonsList);

            JsonNode dragonsBinaryTree = mapper.readTree(String.valueOf(root.get("dragonsBinaryTree")));
            BinaryTree binaryTree = mapBinaryTree(dragonsBinaryTree);

            JsonNode dragonsAVLTree = mapper.readTree(String.valueOf(root.get("dragonsAVLTree")));
            AVLTree avlTree = mapAvlTree(dragonsAVLTree);

            wave = new Wave(id, size, formation, list, binaryTree, avlTree);
            logger.info(CLIENT, "Mapped the Wave from json");
        } catch (IOException e) {
            logger.error(CLIENT, "Couldn't map the Wave from json", e);
        }
        return wave;
    }

    private List<Dragon> mapList(JsonNode list) {
        List<Dragon> dragonList = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Dragon dragon = mapDragon(list.get(i));
            dragonList.add(dragon);
        }
        return dragonList;
    }

    private BinaryTree mapBinaryTree(JsonNode node) {
        TreeNode root = mapTreeNode(node.get("root"));
        BinaryTree binaryTree = new BinaryTree(root);
        logger.info(CLIENT, "Mapped the Wave.BinaryTree from json");
        return binaryTree;
    }

    private AVLTree mapAvlTree(JsonNode node) {
        TreeNode root = mapTreeNode(node.get("root"));
        AVLTree avlTree = new AVLTree(root);
        logger.info(CLIENT, "Mapped the Wave.AvlTree from json");
        return avlTree;
    }

    private TreeNode mapTreeNode(JsonNode node) {
        if (node == null) return null;
        double element = node.get("element").asDouble();
        Dragon dragon = mapDragon(node.get("dragon"));
        TreeNode left = mapTreeNode(node.get("left"));
        TreeNode right = mapTreeNode(node.get("right"));
        logger.info(CLIENT, "Mapped the Wave.TreeNode from json");
        return new TreeNode(element, dragon, left, right);
    }

    private Dragon mapDragon(JsonNode dragon) {
        int parentAge = dragon.get("parentAge").asInt();
        int age = dragon.get("age").asInt();
        String rank = dragon.get("rank").asText();
        String name = dragon.get("name").asText();
        int lives = dragon.get("lives").asInt();
        int fire_rate = dragon.get("fire_rate").asInt();
        return new Dragon(parentAge, age, rank, name, lives, fire_rate);
    }
}
