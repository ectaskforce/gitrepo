/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.dbcp;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import javax.sql.DataSource;
/**
 *
 * @author duke
 */
public class DBCP {

    public static Connection connect() throws Exception {
	Properties properties = new Properties();
	InputStream is = ClassLoader.getSystemResourceAsStream("dbcp.properties");
	properties.load(is);
	DataSource ds = org.apache.commons.dbcp2.BasicDataSourceFactory.createDataSource(properties);
	Connection con = ds.getConnection();
        
        return con;
    }
    
}
