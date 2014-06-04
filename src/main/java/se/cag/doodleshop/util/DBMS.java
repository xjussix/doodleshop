package se.cag.doodleshop.util;

import com.googlecode.flyway.core.Flyway;

import javax.sql.DataSource;

public enum DBMS {
    INSTANCE;

    private Flyway flyway;

    private DBMS() {
        flyway = new Flyway();
        flyway.setDataSource(Config.INSTANCE.getDbUrl(), "doodleshop", "doodleshop");
        if (Config.INSTANCE.isDbTestData()) {
            flyway.setLocations("db/migration", "db/testdata");
        }
    }

    public void migrate() {
        flyway.migrate();
    }

    public DataSource getDataSource() {
        return flyway.getDataSource();
    }
}