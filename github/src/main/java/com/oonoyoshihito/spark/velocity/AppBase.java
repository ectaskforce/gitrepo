/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.velocity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Session;

/**
 *
 * @author duke_2
 */
public class AppBase {
    public static void doErrorForward(Exception e, 
                                        Request request,
                                        Response response,
                                        String path) {
        String error = getExceptionString(e);
        Session s = request.session(true);
        s.attribute("error", error);
        response.redirect(path);
       
    }
    
    public static String getExceptionString(Exception e) {
    
        String result = e.getLocalizedMessage() + "<br/>";
        StackTraceElement [] ste  = e.getStackTrace();
        for (StackTraceElement st : ste) {
            result += st.toString() + "<br/>";
        }
        return result;
    }
    
    public String getSiharaiSum() {
        return getHtmlTable("select * from v_total order by shiharaidate desc", false).makeTableInner();
    }
    
    public HtmlTableHelper getHtmlTable(String sql, boolean zandaka) {
        String table = "";
        HtmlTableHelper hth = new HtmlTableHelper();
        try {
            Connection con = Connector.connect(new JDBC());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String nows = LocalDate.now().toString();
            boolean atFirst = true;
            while(rs.next()) {
                if ( atFirst && zandaka) {
                    if ( nows.compareTo(rs.getString("datadate")) >= 0 ) {
                        Map<String, String> firstRec = new HashMap<>();
                        firstRec.put("mufj", rs.getString("mufj"));
                        firstRec.put("jibun", rs.getString("jibun"));
                        firstRec.put("edy", rs.getString("edy"));
                        firstRec.put("gen", rs.getString("gen"));
                        firstRec.put("floating", rs.getString("floating"));
                        hth.setFirstRec(firstRec);
                        atFirst = false;
                    }
                }
                hth.putRec(rs);
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return hth;
        
    }
}
