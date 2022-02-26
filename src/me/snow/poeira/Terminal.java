package me.snow.poeira;

import me.snow.poeira.api.PoeirasAPI;
import me.snow.poeira.commands.PoeiraCommand;
import me.snow.poeira.listeners.BreakEvent;
import me.snow.poeira.listeners.ClickEvent;
import me.snow.poeira.listeners.JoinEvent;
import me.snow.poeira.managers.LojaManager;
import me.snow.poeira.services.SQlite;
import me.snow.poeira.services.papi.Registry;
import me.snow.poeira.util.DateManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Terminal extends JavaPlugin {

    public static Terminal plugin;

    private PoeirasAPI poeirasAPI;

    private SQlite sQlite;

    private LojaManager lojaManager;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        setupSQL();

        loadPlaceholders();
        registerObjects();
        registerCommands();
        registerEvents();

        Bukkit.getConsoleSender().sendMessage("§9[UniversalPoeira] §ePlugin inicializado");
    }

    @Override
    public void onDisable() {
        sQlite.closeConnection();
    }

    private void registerObjects() {
        lojaManager = new LojaManager(getConfig());
    }

    public void registerCommands() {
        getCommand("poeiras").setExecutor(new PoeiraCommand());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new BreakEvent(), this);
    }

    private void loadPlaceholders() {
        (new Registry(this)).register();
    }

    private void setupSQL() {
        DateManager.createFolder("database");

        sQlite = new SQlite();

        this.poeirasAPI = new PoeirasAPI(this);
    }

    public PoeirasAPI getPoeirasAPI() {
        return this.poeirasAPI;
    }

    public SQlite getsQlite() {
        return this.sQlite;
    }

    public LojaManager getLojaManager() {
        return lojaManager;
    }

}
