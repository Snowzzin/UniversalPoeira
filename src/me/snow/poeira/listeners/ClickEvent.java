package me.snow.poeira.listeners;

import me.snow.poeira.Terminal;
import me.snow.poeira.managers.LojaManager;
import me.snow.poeira.util.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Map;

public class ClickEvent implements Listener {

    LojaManager lojaManager = Terminal.plugin.getLojaManager();

    @EventHandler
    void click(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getTitle().equals("Loja de Poeiras")) {
            e.setCancelled(true);

            lojaManager.getItem().entrySet().stream().map(Map.Entry::getValue).forEach(kit -> {

                if(e.getSlot() == kit.getSlot()) {

                    if(Terminal.plugin.getPoeirasAPI().getPoeiras(p) < kit.getPriceMoney()) {
                        p.sendMessage("§cVocê não possui Poeiras suficientes.");
                        return;
                    }

                    for(Player all : Bukkit.getOnlinePlayers()) {
                        ActionBarAPI.sendActionBarMessage(all, Terminal.plugin.getConfig().getString("Mensagens.ComprouItem")
                                .replace("&","§")
                                .replace("{player}", p.getName())
                                .replace("{item}", kit.getName()));
                    }
                    Terminal.plugin.getPoeirasAPI().removePoeiras(p, (int) kit.getPriceMoney());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), kit.getCommand().replace("{target}", p.getName()));
                }
            });
        }
    }
}
