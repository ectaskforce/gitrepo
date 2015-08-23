/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.velocity;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 *
 * @author duke_2
 */
public class Connector {
 
    public static Connection connect(JDBC jdbc) throws Exception {
        
        Class.forName(jdbc.getJdbcDrvier());
        Connection c = DriverManager.getConnection(jdbc.getUrl(), jdbc.getUser(), jdbc.getPass()) ;
        
        return c;
    }
}
