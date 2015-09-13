/*
 * Copyright 2013 Per Wendel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oonoyoshihito.spark.velocity;

import com.oonoyoshihito.spark.oracle.OracleHR;
import com.oonoyoshihito.spark.oono.GetRequest;
import com.oonoyoshihito.spark.oono.Hello;
import com.oonoyoshihito.spark.oono.PHello;
import com.oonoyoshihito.spark.oono.PZandaka;
import com.oonoyoshihito.spark.oono.PostRequest;
import com.oonoyoshihito.spark.oono.Zandaka;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.servlet.SparkApplication;

/**
 * VelocityTemplateRoute example.
 */
public final class VelocityMain extends AppBase implements SparkApplication {

    @Override
    public void init() {
        
        get("/oracle", (request, response) -> { 
            return new OracleHR().process(request, response);
        }, new VelocityTemplateEngine());

        get("/hello", (request, response) -> {
            GetRequest hello = new Hello();
            return hello.doGet(request, response);
        }, new VelocityTemplateEngine());
        
        get("/zandaka", (request, response) -> {
            GetRequest zandaka = new Zandaka();
            return zandaka.doGet(request, response);
        }, new VelocityTemplateEngine());

        post("/zandaka", (request, response) -> {
            PostRequest pzandaka = new PZandaka();
            return pzandaka.doPost(request, response);
        });

        post("/hello", (request, response) -> {
            PostRequest phello = new PHello();
            return phello.doPost(request, response);
        });
    }

}