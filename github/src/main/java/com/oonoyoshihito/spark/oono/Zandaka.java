/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oonoyoshihito.spark.oono;

import static com.oonoyoshihito.spark.velocity.AppBase.getHtmlTable;
import com.oonoyoshihito.spark.velocity.HtmlTableHelper;
import java.time.LocalDate;
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
public class Zandaka extends GetRequest {

    public ModelAndView doGet(Request request, Response response) {
        HtmlTableHelper hth = getHtmlTable("SELECT z.*, ( z.mufj + z.gen + z.edy + z.jibun + z.floating ) total FROM tb_zandaka z order by datadate desc", true);
        String htmlTable =  hth.makeTableInner();
        Map<String,String> firstRec = hth.getFirstRec();
            if ( firstRec == null ) {
            firstRec = new HashMap<>();
            firstRec.put("mufj","");
            firstRec.put("gen","");
            firstRec.put("edy","");
            firstRec.put("jibun","");
            firstRec.put("floating","");
        }

        Map<String, Object> model = new HashMap<>();
        LocalDate locald = LocalDate.now();
        model.put("table", htmlTable);
        model.put("datadate", locald);
        model.put("mufj", firstRec.get("mufj"));
        model.put("gen", firstRec.get("gen"));
        model.put("edy", firstRec.get("edy"));
        model.put("jibun", firstRec.get("jibun"));
        model.put("floating", firstRec.get("floating"));

        Session s = request.session();
        if ( s != null && s.attribute("error") != null ) {
            model.put("error", s.attribute("error") + " ");
        } else {
            model.put("error", " ");
        }

        // The wm files are located under the resources directory
        return new ModelAndView(model, "zandaka.wm");
    }
}
