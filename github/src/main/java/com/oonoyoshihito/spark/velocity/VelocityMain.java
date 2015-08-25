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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.servlet.SparkApplication;

/**
 * VelocityTemplateRoute example.
 */
public final class VelocityMain implements SparkApplication {

    @Override
    public void init() {

        get("/hello", (request, response) -> {
            HtmlTableHelper hth = getHtmlTable("select * from siharai order by id desc");
            String htmlTable = getSiharaiSum() + "<br/>" + hth.makeTableInner();
            Map<String, Object> model = new HashMap<>();
            model.put("table", htmlTable);
            model.put("result", hth.core.size());

            // The wm files are located under the resources directory
            return new ModelAndView(model, "hello.wm");
        }, new VelocityTemplateEngine());
        
        post("/hello", (request, response) -> {
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
                throw new RuntimeException(e);
            }
            return ret;
            });
        
    }

    public String getSiharaiSum() {
        return getHtmlTable("select * from v_total order by shiharaidate desc").makeTableInner();
    }
    
    public HtmlTableHelper getHtmlTable(String sql) {
        String table = "";
        HtmlTableHelper hth = new HtmlTableHelper();
        try {
            Connection con = Connector.connect(new JDBC());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                hth.putRec(rs);
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return hth;
    }
}