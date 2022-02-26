package me.snow.poeira.services;

import com.avaje.ebean.validation.NotNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.snow.poeira.Terminal;
import me.snow.poeira.api.PoeirasAPI;
import me.snow.poeira.util.Formatador;
import org.bukkit.entity.Player;

public class PoeirasPAPI extends PlaceholderExpansion {

    protected Terminal main;

    private PoeirasAPI poeirasAPI;

    public PoeirasPAPI(Terminal main) {
        this.main = main;

        poeirasAPI = main.getPoeirasAPI();
    }

    @NotNull
    public String getAuthor() {
        return "Snowzzin";
    }

    @NotNull
    public String getIdentifier() {
        return this.main.getName();
    }

    @NotNull
    public String getVersion() {
        return this.main.getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player p, String params) {

        if (params.equalsIgnoreCase("amount")) {
            int poeiras = poeirasAPI.getPoeiras(p);
            String poeirasFormatted = Formatador.formatNumber(poeiras);
            return poeirasFormatted;

        }

        return "" + 0;
    }
}
