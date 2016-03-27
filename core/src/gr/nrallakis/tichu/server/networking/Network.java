package gr.nrallakis.tichu.server.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import gr.nrallakis.tichu.server.networking.GamePackets.*;
import gr.nrallakis.tichu.server.game.Card;
import gr.nrallakis.tichu.server.game.CardCombination;
import gr.nrallakis.tichu.server.game.GameConnection;

public class Network {

    public static void registerPackets(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        registerBasicPackets(kryo);
        registerGamePackets(kryo);
    }

    private static void registerBasicPackets(Kryo kryo) {
        kryo.register(Packets.CreateRoom.class);
        kryo.register(Packets.RoomInfo.class);
        kryo.register(Packets.JoinRoom.class);
        kryo.register(Packets.JoinAccepted.class);
        kryo.register(Packets.StartGame.class);
        kryo.register(Packets.GameStarted.class);
        kryo.register(Packets.Login.class);
        kryo.register(Packets.LoginSuccessful.class);
        kryo.register(Packets.GetRooms.class);
        kryo.register(Packets.Rooms.class);
        kryo.register(Packets.GuestInfo.class);
        kryo.register(Packets.GuestLogin.class);
        kryo.register(Packets.RoomCreated.class);
        kryo.register(Packets.RoomPlayers.class);
        kryo.register(Packets.GetRoomPlayers.class);
        kryo.register(String.class);
        kryo.register(String[].class);
    }

    private static void registerGamePackets(Kryo kryo) {
        kryo.register(PlayerGrandTichu.class);
        kryo.register(PlayerTichu.class);
        kryo.register(PlayerPassed.class);
        kryo.register(PlayerPlayed.class);
        kryo.register(GiveCards.class);
        kryo.register(PlayerBombed.class);
        kryo.register(Card.class);
        kryo.register(CardCombination.class);
        kryo.register(GameConnection.class);
        kryo.register(PlayerToPlayFirst.class);

        DefaultArraySerializers.ObjectArraySerializer cardArraySerializer
                = new DefaultArraySerializers.ObjectArraySerializer(kryo, Card[].class);
        cardArraySerializer.setElementsCanBeNull(false);
        cardArraySerializer.setElementsAreSameType(true);
        kryo.register(Card[].class, cardArraySerializer);
        ObjectSpace.registerClasses(kryo);
    }
}
