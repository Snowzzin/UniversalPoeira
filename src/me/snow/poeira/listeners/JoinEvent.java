package me.snow.poeira.listeners;

import me.snow.poeira.Terminal;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    void join(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if(!Terminal.plugin.getPoeirasAPI().hasAccount(p)) {
            Terminal.plugin.getsQlite().createPlayerInDB(p);
            Bukkit.getConsoleSender().sendMessage("§9[UniversalPoeira] §f[DEBUG] §eO jogador §f" + p.getName() + " §efoi registrado");
        }
    }
}
