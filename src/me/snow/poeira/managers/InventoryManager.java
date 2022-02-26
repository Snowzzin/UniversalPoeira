package me.snow.poeira.managers;

import com.maria.cash.api.CashAPI;
import me.snow.poeira.Terminal;
import me.snow.poeira.api.PoeirasAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.Map;

public class InventoryManager {

    LojaManager lojaManager = Terminal.plugin.getLojaManager();

    public Inventory storePoeira(Player p) {

        Inventory inv = Bukkit.createInventory(null, 6*9, "Loja de Poeiras");

        inv.setItem(4, new ItemManager().createItem(Material.SIGN, "§ePrecisa de Ajuda?", Arrays.asList("",
                "§7Adquira itens espaciais com suas",
                "§7Poeiras Cosmicas")));

        lojaManager.getItem().entrySet().stream().map(Map.Entry::getValue).forEach(kit -> {
            inv.setItem(kit.getSlot(), kit.getIcon());
        });

        return inv;
    }
}
