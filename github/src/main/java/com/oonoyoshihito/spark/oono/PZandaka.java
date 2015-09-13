/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.oono;

import static com.oonoyoshihito.spark.velocity.AppBase.doErrorForward;
import com.oonoyoshihito.spark.velocity.Connector;
import com.oonoyoshihito.spark.velocity.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import spark.Request;
import spark.Response;

/**
 *
 * @author duke_2
 */
public class PZandaka extends PostRequest {

    @Override
    public String doPost(Request request, Response response) {

        String ret = "";
        try {
        Connection con = Connector.connect(new JDBC());
        String psql = " select count(datadate) dt from tb_zandaka where datadate = ?";
        PreparedStatement ps = con.prepareStatement(psql);
        ps.setString(1, request.queryParams("datadate"));

        ResultSet rs = ps.executeQuery();
        rs.next();
        if ( rs.getInt("dt") == 0 ) {
            rs.close();
            ps.close();
            psql = " insert into tb_zandaka ( datadate, mufj, jibun, edy, gen, floating) values ( ?, ?, ?, ?, ?, ? )";
            ps = con.prepareStatement(psql);
            ps.setString(1, request.queryParams("datadate"));
            ps.setString(2, request.queryParams("mufj"));
            ps.setString(3, request.queryParams("jibun"));
            ps.setString(4, request.queryParams("edy"));
            ps.setString(5, request.queryParams("gen"));
            ps.setString(6, request.queryParams("floating"));

        } else {

            rs.close();
            ps.close();
            psql = " update tb_zandaka set mufj = ?, jibun = ?, edy =? ,gen = ? , floating = ? where datadate = ? ";
            ps = con.prepareStatement(psql);
            ps.setString(1, request.queryParams("mufj"));
            ps.setString(2, request.queryParams("jibun"));
            ps.setString(3, request.queryParams("edy"));
            ps.setString(4, request.queryParams("gen"));
            ps.setString(5, request.queryParams("floating"));
            ps.setString(6, request.queryParams("datadate"));
        }

        ps.execute();
        response.redirect("./zandaka"); 
        } catch(Exception e) {
            doErrorForward(e, request, response, "./zandaka");
        }
        return ret;
    }
    
}
