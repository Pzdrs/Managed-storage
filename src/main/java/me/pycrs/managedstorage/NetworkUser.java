package me.pycrs.managedstorage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NetworkUser {
    private final UUID id;

    public NetworkUser(UUID id) {
        this.id = id;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(id);
    }

    public UUID getId() {
        return id;
    }
}
