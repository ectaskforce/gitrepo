/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.velocity;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 *
 * @author duke_2
 */
public class OracleHR {
    public ModelAndView process(Request request, Response reponse) {
        Map<String, Object> model = new HashMap<>();
        model.put("table", "");
        model.put("result", "");

        ORACLE ora = new ORACLE();
        try {
            Connection con = Connector.connect(ora);
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(OracleHR.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ModelAndView( new HashMap<>(), "hello.wm");
    }
}
 
