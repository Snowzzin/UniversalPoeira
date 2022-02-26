package me.snow.poeira.api;

import me.snow.poeira.Terminal;
import me.snow.poeira.services.SQlite;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PoeirasAPI {

    protected Terminal main;

    private final SQlite sql;

    public PoeirasAPI(Terminal main) {
        this.main = main;
        this.sql = main.getsQlite();
    }

    public boolean hasAccount(Player p) {
        try {
            PreparedStatement preparedStatement = this.sql.getConnection()
                    .prepareStatement("SELECT * FROM poeiras WHERE player='" + p.getName() + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getPoeiras(Player p) {
        int poeiras = 0;
        if (hasAccount(p)) {
            try {
                PreparedStatement preparedStatement = this.sql.getConnection()
                        .prepareStatement("SELECT quantia FROM poeiras WHERE player='" + p.getName() + "'");
                ResultSet resultSet = preparedStatement.executeQuery();
                poeiras = resultSet.getInt("quantia");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            this.sql.createPlayerInDB(p);
        }
        return poeiras;
    }

    public int addPoeiras(Player p, Integer value) {
        if (hasAccount(p)) {
            int poeiras = getPoeiras(p);
            int poeirasTotal = poeiras + value;
            this.sql.executeQuery("UPDATE poeiras SET quantia=" + poeirasTotal + " WHERE player='" + p.getName() + "'");
        } else {
            this.sql.createPlayerInDB(p);
            addPoeiras(p, value);
        }
        return value;
    }

    public int removePoeiras(Player p, Integer value) {
        if (hasAccount(p)) {
            int poeiras = getPoeiras(p);
            int poeirasTotal = poeiras - value;
            this.sql.executeQuery("UPDATE poeiras SET quantia=" + poeirasTotal + " WHERE player='" + p.getName() + "'");
        } else {
            this.sql.createPlayerInDB(p);
            addPoeiras(p, value);
        }
        return value;
    }
}
