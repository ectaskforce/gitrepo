/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.oracle;

import com.oonoyoshihito.spark.velocity.AppBase;
import com.oonoyoshihito.spark.velocity.HtmlTableHelper;
import com.oonoyoshihito.spark.velocity.ORACLE;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 * /oracle を処理する.
 * @author duke_2
 */
public class OracleHR extends Oracle {
    
    public OracleHR () {
        this.setSchema("HR");
    }
    
    public ModelAndView process(Request request, Response reponse) {
        Map<String, Object> model = new HashMap<>();
        model.put("table", "");
        model.put("result", "");

        ORACLE ora = new ORACLE();
        try {
            String sql = "select * from SYS.ALL_TAB_COLUMNS where OWNER = '" + schema + "' order by OWNER, TABLE_NAME, COLUMN_ID";
            HtmlTableHelper hth = AppBase.getHtmlTable(new ORACLE(), sql, false);
            String htmlTable = hth.makeTableInner();
                model.put("table" ,htmlTable);
        } catch (Exception ex) {
            Logger.getLogger(OracleHR.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ModelAndView( model, "hello.wm");
    }

}
 
