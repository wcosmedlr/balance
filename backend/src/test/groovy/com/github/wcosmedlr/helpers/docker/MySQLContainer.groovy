package com.github.wcosmedlr.helpers.docker;

import org.testcontainers.containers.GenericContainer;

class MySQLContainer {
    static final GenericContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new GenericContainer("mysql:8.0.22")
                .withEnv("MYSQL_USER", "balance-us")
                .withEnv("MYSQL_ROOT_PASSWORD", "balance-pw-root")
                .withEnv("MYSQL_PASSWORD", "balance-pw")
                .withEnv("MYSQL_DATABASE", "balance-db")
                .withExposedPorts(3306)
        MY_SQL_CONTAINER.start();
    }
}
