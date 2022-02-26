package me.snow.poeira.services.papi;

import me.snow.poeira.Terminal;
import me.snow.poeira.services.PoeirasPAPI;
import org.bukkit.Bukkit;

public class Registry {

    protected Terminal main;

    public Registry(Terminal main) {
        this.main = main;
    }

    public void register() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getConsoleSender().sendMessage("§9[UniversalPoeiras] §cNão foi possivel Hookar com PlaceholderAPI");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("§9[UniversalPoeiras] §ePlaceHolderAPI hookado com sucesso");
        (new PoeirasPAPI(this.main)).register();
    }
}
