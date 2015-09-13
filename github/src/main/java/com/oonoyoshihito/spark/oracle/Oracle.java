/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.oracle;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 *
 * @author duke_2
 */
abstract public class Oracle {
 
    String schema ;
    
     public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
    
    abstract public ModelAndView process(Request request, Response reponse);
}
