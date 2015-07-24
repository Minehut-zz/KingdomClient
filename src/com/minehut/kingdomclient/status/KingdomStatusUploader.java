package com.minehut.kingdomclient.status;

import com.minehut.core.Core;
import com.minehut.core.util.common.chat.C;
import com.minehut.core.util.common.chat.F;
import com.minehut.kingdomclient.KingdomClient;
import com.mongodb.*;
import org.bukkit.Bukkit;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by luke on 7/12/15.
 */
public class KingdomStatusUploader {
    int runnableID;
    private KingdomClient kingdomClient;
    private String ownerRank;

    /* Database */
    private MongoClient mongo;
    private DB db;
    private DBCollection serversCollection;


    public KingdomStatusUploader(KingdomClient kingdomClient) {
        this.kingdomClient = kingdomClient;
        this.connect();

        this.ownerRank = Core.getInstance().getRank(kingdomClient.getOwnerUUID()).name;

        this.runnableID = this.continuousUpload();
    }

    private void connect() {
        try {
            this.mongo = new MongoClient("localhost", 27017);
            this.db = mongo.getDB("minehut");
            this.serversCollection = db.getCollection("servers");

            if (this.db == null) {
                System.out.println("Couldn't connect to database, enabling offline mode.");
                return;
            } else {
                System.out.println("Successfully connected to database :)");
            }

        } catch (Exception e) {
            System.out.println("Couldn't connect to database, enabling offline mode.");
        }
    }

    public int continuousUpload() {
        return Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(kingdomClient, new Runnable() {
            @Override
            public void run() {
                DBObject query = new BasicDBObject("name", "kingdom" + kingdomClient.getKingdomName());
                DBObject found = serversCollection.findOne(query);

                if (found == null) {
                    serversCollection.insert(createDBObject());
                    F.log(C.logDivider);
                    F.log("Uploading Status for the first time");
                    F.log(C.logDivider);
                } else {
                    serversCollection.findAndModify(query, createDBObject());
                }
            }
        }, 0L, 30L);
    }

    public DBObject createDBObject() {
        DBObject obj = new BasicDBObject("name", "kingdom" + this.kingdomClient.getKingdomName());
        obj.put("kingdomName", kingdomClient.getKingdomName());
        obj.put("type", "kingdom");
        obj.put("bungee", "kingdom" + (Bukkit.getServer().getPort() - 60000));
        obj.put("motd", kingdomClient.getMotd());
        obj.put("rank", this.ownerRank);
        obj.put("featured", kingdomClient.isFeatured());

        try {
            obj.put("ip", InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            obj.put("ip", "unknown");
            e.printStackTrace();
        }

        obj.put("port", Bukkit.getServer().getPort());
        obj.put("playersOnline", Bukkit.getServer().getOnlinePlayers().size());
        obj.put("maxPlayers", Bukkit.getServer().getMaxPlayers());
        obj.put("lastOnline", System.currentTimeMillis());

        return obj;
    }
}
