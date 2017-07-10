package pl.lucash.resteasy.infrastructure;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class DatabaseConnector {

    private static DatabaseConnector instance;
    private Connection connection;
    private ScriptRunner scriptRunner;

    private final String url;
    private final String user;
    private final String password;
    private boolean connected;
    private boolean showSql;

    private DatabaseConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.showSql = false;
    }

    public static DatabaseConnector getInstance(String url, String user, String password) {
        if (instance != null) {
            return instance;
        }
        instance = new DatabaseConnector(url, user, password);
        return instance;
    }

    public void init() {
        if (!connected) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                connected = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initRunner();
        }
    }

    private void close() {
        if (connected) {
            try {
                connection.close();
                connected = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void runScript(File file) {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Optional.ofNullable(reader).ifPresent(scriptRunner::runScript);
        Optional.ofNullable(reader).ifPresent(inputStreamReader -> {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initRunner() {
        if (scriptRunner == null) {
            scriptRunner = new ScriptRunner(connection);
            scriptRunner.setStopOnError(true);
            if (!showSql) {
                scriptRunner.setLogWriter(null);
            }
        }
    }

    @Override
    public void finalize() {
        System.out.println("finialize()");
        close();
    }
}
