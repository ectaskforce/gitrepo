/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.oono;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 *
 * @author duke_2
 */
abstract public class GetRequest {
    
    abstract public ModelAndView doGet(Request request, Response response);
}
