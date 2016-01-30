package gr.nrallakis.tichu.server.game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryonet.Connection;

import gr.nrallakis.tichu.core.AccountManager;

public class Player {

    private Connection connection;
    public boolean ready;
    private String id;
    private Array<Card> hand;
    private GameChangesListener gameActions;

    public Player(Connection c) {
        this.connection = c;
        this.id = connection.toString();
    }

    public void giveCard(Card card) {
        hand.add(card);

    }

    public String toJson() {
        Json json = new Json();
        json.setSerializer(Player.class, new Json.Serializer<Player>() {
            @Override
            public void write(Json json, Player player, @SuppressWarnings("rawtypes") Class knownType) {
                json.writeObjectStart();
                json.writeValue("name", player.getName());
                json.writeValue("points", player.getPoints());
                json.writeValue("id", player.getId());
                json.writeObjectEnd();
            }

            @Override
            public Player read(Json json, JsonValue jsonData, @SuppressWarnings("rawtypes") Class type) {
                return null;
            }
        });
        return json.toJson(this);
    }

    public Connection getConnection() {
        return connection;
    }

    public String getId() {
        return id;
    }

    public int getPoints() {
        return AccountManager.getInstance().getAccountRankPoints(id);
    }

    public String getName() {
        return AccountManager.getInstance().getAccountName(id);
    }
}