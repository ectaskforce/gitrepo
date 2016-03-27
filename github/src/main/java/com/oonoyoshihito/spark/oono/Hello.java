/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.oono;

import com.oonoyoshihito.spark.velocity.AppBase;
import com.oonoyoshihito.spark.velocity.HtmlTableHelper;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

/**
 *
 * @author duke_2
 */
public class Hello extends GetRequest {

    @Override
    public ModelAndView doGet(Request request, Response response) {
        HtmlTableHelper hth = AppBase.getHtmlTable("select * from siharai order by id desc", false);
        String htmlTable = AppBase.getSiharaiSum() + "<br/>" + hth.makeTableInner() + "<div>" + request.contextPath() + "</div>";
        Map<String, Object> model = new HashMap<>();
        model.put("table", htmlTable);
        model.put("result", hth.core.size());
        model.put("root", request.contextPath());
        Session s = request.session();
        if ( s != null ) {
            model.put("error", s.attribute("error"));
        } else {
            model.put("error", " ");
        }

        // The wm files are located under the resources directory
        return new ModelAndView(model, "hello.wm");
    }
}
