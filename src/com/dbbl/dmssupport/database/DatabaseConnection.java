package com.dbbl.dmssupport.database;

import com.dbbl.dmssupport.common.constant.DatabaseConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public  class DatabaseConnection {
    public Connection conn = null;

    public DatabaseConnection() throws Exception {

            Class.forName(DatabaseConstant.DATABASE_DRIVER);
             this.conn = DriverManager.getConnection
                    (DatabaseConstant.DATABASE_URL,
                            DatabaseConstant.DATABASE_CONNECTION_USER,
                            DatabaseConstant.DATABASE_CONNECTION_PASSWORD);
    }

    public  void executeQuery(){};
}
