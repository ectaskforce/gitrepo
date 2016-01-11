/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author duke_2
 */
public class Connector {
 
    static Logger logger = LoggerFactory.getLogger(Connector.class);
    public static Connection connect(JDBC jdbc) throws Exception {
        
        
        logger.info("driver : " + jdbc.getJdbcDrvier());
        logger.info("url : " + jdbc.getUrl() + " user : " + jdbc.getUser() + " pass : " + jdbc.getPass());
        
        Class.forName(jdbc.getJdbcDrvier());
        Connection c = DriverManager.getConnection(jdbc.getUrl(), jdbc.getUser(), jdbc.getPass()) ;
        
        return c;
    }
}
