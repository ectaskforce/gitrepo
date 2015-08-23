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

import com.oonoyoshihito.dbcp.DBCP;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.get;
import spark.servlet.SparkApplication;

/**
 * VelocityTemplateRoute example.
 */
public final class VelocityMain implements SparkApplication {

    @Override
    public void init() {
        String table = "";
        HtmlTableHelper hth = new HtmlTableHelper();
        try {
            Connection con = DBCP.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from siharai order by id desc");
            while(rs.next()) {
                hth.putRec(rs);
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String htmlTable = hth.makeTableInner();
        get("/hello", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("table", htmlTable);
            model.put("result", hth.core.size());

            // The wm files are located under the resources directory
            return new ModelAndView(model, "hello.wm");
        }, new VelocityTemplateEngine());
    }

}