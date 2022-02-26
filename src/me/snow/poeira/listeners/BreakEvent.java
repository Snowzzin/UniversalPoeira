package me.snow.poeira.listeners;

import me.snow.poeira.Terminal;
import me.snow.poeira.util.ActionBarAPI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakEvent implements Listener {

    @EventHandler
    void block(BlockBreakEvent e) {

        Player p = e.getPlayer();

        Block block = e.getBlock();
        Material material = block.getType();

        if(material.equals(Material.STAINED_CLAY)) {
            if(block.getData() == 5 || block.getData() == 6) {

                Terminal.plugin.getPoeirasAPI().addPoeiras(p, Terminal.plugin.getConfig().getInt("Info.PoeirasPorBloco"));
            }
        }
    }
}
