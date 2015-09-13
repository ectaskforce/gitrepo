/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.oono;

import spark.Request;
import spark.Response;

/**
 *
 * @author duke_2
 */
abstract public class PostRequest {
    
    abstract public String doPost(Request request, Response response);
}
