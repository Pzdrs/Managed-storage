package me.pycrs.managedstorage;

import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class StorageNetwork {
    private final UUID id;
    private Set<NetworkUser> users;

    public StorageNetwork(UUID id) {
        this.id = id;
    }

    public StorageNetwork() {
        this.id = UUID.randomUUID();
    }

    public static StorageNetwork[] getPlayerNetworks(Player player) {
        return ManagedStorage.networks.stream().filter(storageNetwork -> {
            for (NetworkUser user : storageNetwork.users) {
                if (user.getId().equals(player.getUniqueId())) return true;
            }
            return false;
        }).toArray(StorageNetwork[]::new);
    }
}
