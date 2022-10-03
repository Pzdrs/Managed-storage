package me.pycrs.managedstorage;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public final class ManagedStorage extends JavaPlugin implements Listener, CommandExecutor {
    public static Set<StorageNetwork> networks = new HashSet<>();

    @Override
    public void onEnable() {
        // Automatic reload
        final long lastModified = getFile().lastModified();

        new BukkitRunnable() {
            public void run() {
                if (getFile().lastModified() > lastModified) {
                    cancel();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload confirm");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "start");
                }
            }
        }.runTaskTimer(this, 0, 20);

        getServer().getPluginManager().registerEvents(this, this);
        getCommand("storage").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onOpne(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block != null) {
            player.sendMessage(String.valueOf(block.getLocation()));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }
        return true;
    }
}
