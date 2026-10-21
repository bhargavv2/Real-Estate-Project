package realestate.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnection {
    private DatabaseConnection() { }

    public static Connection getConnection() throws SQLException {
        Properties local = loadDotEnv();
        String url = setting("DB_URL", local);
        String user = setting("DB_USER", local);
        String password = setting("DB_PASSWORD", local);
        if (url == null || user == null || password == null) {
            throw new SQLException("Set DB_URL, DB_USER, and DB_PASSWORD in the environment or project .env file.");
        }
        return DriverManager.getConnection(url, user, password);
    }

    private static Properties loadDotEnv() {
        Properties properties = new Properties();
        Path envFile = Path.of(".env");
        if (Files.isRegularFile(envFile)) {
            try (Reader reader = Files.newBufferedReader(envFile)) {
                properties.load(reader);
            } catch (IOException e) {
                throw new IllegalStateException("Could not read the project .env file.", e);
            }
        }
        return properties;
    }

    private static String setting(String key, Properties local) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) value = local.getProperty(key);
        return value == null || value.isBlank() ? null : value.trim();
    }
}
