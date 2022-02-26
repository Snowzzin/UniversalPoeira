package me.snow.poeira.services;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQlite {

    private Connection connection;

    public SQlite() {
        openConnection();
    }

    public void openConnection() {
        File file = new File("plugins/UniversalPoeiras/database/database.db");
        String url = "jdbc:sqlite:" + file;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            Bukkit.getConsoleSender().sendMessage("§9[UniversalPoeiras] §eConexão com §fSQLite §eaberta com sucesso");
            createTables();

        } catch (SQLException | ClassNotFoundException e) {
            Bukkit.getConsoleSender().sendMessage("§c[UniversalPoeiras] §cHouve um erro ao tentar fazer conexão com §6SQLite");
        }
    }

    public void closeConnection() {
        if (connection == null)
            return;

        try {
            connection.close();
            Bukkit.getConsoleSender().sendMessage("§cConexão com SQLite fechada com sucesso");

        } catch (SQLException e) {
            Bukkit.getConsoleSender()
                    .sendMessage("§cOcorreu um erro ao tentar fechar a conexão com o SQLite, erro:");
            e.printStackTrace();
        }
    }

    public boolean executeQuery(String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createPlayerInDB(Player p) {

        executeQuery("INSERT INTO poeiras VALUES ('" + p.getName() + "', 0)");
    }

    public void createTables() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `poeiras` (player VARCHAR(24), quantia INTEGER)");
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
