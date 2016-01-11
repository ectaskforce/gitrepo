/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.velocity;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duke_2
 */
public class JDBC implements Serializable {
    
    private String jdbcDrvier = "com.mysql.jdbc.Driver" ;
    
    private String url = "";
    
    private String user = "root";
    
    private String pass = "";
    
    private String schema = "keiri";
    
    private String tableName;

    public String getJdbcDrvier() {
        return jdbcDrvier;
    }

    public void setJdbcDrvier(String jdbcDrvier) {
        this.jdbcDrvier = jdbcDrvier;
    }

    public String getUrl() {
        
        url = "jdbc:mysql://localhost:3306/keiri";
        
        // ローカルとリモートのDB urlを自立して切り替え
        try {
            for(NetworkInterface n: Collections.list(NetworkInterface.getNetworkInterfaces()) ) {
                for (InetAddress addr : Collections.list(n.getInetAddresses()))  {
                    if( addr instanceof Inet4Address && !addr.isLoopbackAddress() ){
                        if ( addr.getHostAddress().contains("192.168.12") ) {
                            url = "jdbc:mysql://oonoyoshihito.com:3306/keiri";
                            break;
                        }
                    }
                }
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        return "JDBC{" + "jdbcDrvier=" + jdbcDrvier + ", url=" + url + ", user=" + user + ", pass=" + pass + ", schema=" + schema + ", tableName=" + tableName + '}';
    }

    
    
}
