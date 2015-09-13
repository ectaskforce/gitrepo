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
import spark.Request;
import spark.Response;

/**
 *
 * @author duke_2
 */
public class PHello extends PostRequest {

    @Override
    public String doPost(Request request, Response response) {
        String ret = "";
        try {
        Connection con = Connector.connect(new JDBC());
        String psql = " INSERT INTO siharai ( datadate, l_kamoku, r_kamoku, kin , shiharaidate, sumi, seikyu_kbn ,description ) values ";
        psql += "(?, '5999', '2101', ?, ? , '0', ?, ?)";
        PreparedStatement ps = con.prepareStatement(psql);
        ps.setString(1, request.queryParams("datadate"));
        ps.setString(2, request.queryParams("price"));
        ps.setString(3, request.queryParams("sime"));
        ps.setString(4, request.queryParams("type"));
        ps.setString(5, request.queryParams("desc"));
        ps.execute();
        response.redirect("./hello"); 
        } catch(Exception e) {
            doErrorForward(e, request, response, "./hello");
        }
        return ret;
    }
    
}
