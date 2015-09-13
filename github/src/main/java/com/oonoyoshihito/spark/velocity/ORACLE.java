/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.velocity;

import java.io.Serializable;

/**
 *
 * @author duke_2
 */
public class ORACLE extends JDBC implements Serializable {
    
    private String jdbcDrvier = "oracle.jdbc.driver.OracleDriver" ;
    
    private String url = "jdbc:thin://oonoyoshihito.com:1521/HR";
    
    private String user = "OONO";
    
    private String pass = "OONO";
    
    private String schema = "HR";
    
    private String tableName;

    @Override
    public String getJdbcDrvier() {
        return jdbcDrvier;
    }

    @Override
    public void setJdbcDrvier(String jdbcDrvier) {
        this.jdbcDrvier = jdbcDrvier;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPass() {
        return pass;
    }

    @Override
    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String getSchema() {
        return schema;
    }

    @Override
    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        return "JDBC{" + "jdbcDrvier=" + jdbcDrvier + ", url=" + url + ", user=" + user + ", pass=" + pass + ", schema=" + schema + ", tableName=" + tableName + '}';
    }

    
    
}
