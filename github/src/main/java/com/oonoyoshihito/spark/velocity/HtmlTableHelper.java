/**
 * jdbcでレコードセットで取得したリストのテーブル展開を行うクラス。
 *
 * <pre>
 *  table.r2, r3. r4 をCSSに登録.
 * </pre>
 */
package com.oonoyoshihito.spark.velocity;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * リストで持っているレコードセットの結果をHTMLテーブルに変換する.<br/>
 * <br/>
 * 行展開、表展開をstatic、内部オブジェクトの両側からサポートする.<br/> 
 * 
 * @author 大野
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
public class HtmlTableHelper {

    public final List<List<String>> core = new ArrayList<>();
    
    private Map<String, String> firstRec;

    public Map<String, String> getFirstRec() {
        return firstRec;
    }

    public void setFirstRec(Map<String, String> firstRec) {
        this.firstRec = firstRec;
    }

    static boolean rowEven = false;

    static boolean atColumnName = false;

    /**
     * コンストラクタ.
     *
     * レコードリストを初期化します。
     *
     */
    public HtmlTableHelper() {

    }

    /**
     * 内部リストのクリア.
     */
    public void clear() {
        core.clear();
    }

    /**
     * レコードプッタ.
     *
     * リザルトセットの１レコードからレコードを生成し、リストに格納します。
     *
     * @param rs レコードセット
     * @throws Exception
     */
    public void putRec(ResultSet rs) throws Exception {
        List<String> rec = new ArrayList<>();

        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        
        // fieldを追加する
        if ( core.isEmpty() ) {
            for ( int i=1; i<= cols; i++ ) {
                rec.add(rsmd.getColumnName(i));
            }
            core.add(rec);
            rec = new ArrayList<>();
        }
        
        
        for (int i = 1; i <=  cols; i++) {
            rec.add(rs.getString(i));
        }
        core.add(rec);
    }

    /**
     * Innerリストをテーブル展開する.
     * @return HTMLテーブル
     */
    public String makeTableInner() {
        return makeTable(this.core);
    }
    
    /**
     * 与えられたリストからHTMLテーブルを作る.
     *
     * @param list データリスト(List２次元)
     * @return 展開されたHTML
     */
    public static String makeTable(List<List<String>> list) {
        StringBuilder sb = new StringBuilder();

        sb.append("<TABLE>\r\n");
        for (List<String> list1 : list) {
            sb.append(makeATableLine(list1));
        }
        sb.append("</TABLE>\r\n");
        return sb.toString();
    }

    /**
     * 行展開する for List型rec.
     *
     * レコードから一行を展開する.
     *
     * @param rec レコード
     * @return 展開されたHTML
     */
    public static String makeATableLine(List<String> rec) {
        StringBuilder sb = new StringBuilder();

        rowEven  = !rowEven;
        
        sb.append("<TR>\n");
        for (String rec1 : rec) {
            if ( atColumnName ) {
                sb.append("<TD nowrap class=\"r4\">");
            } else {
                if ( rowEven ) {
                    sb.append("<TD  nowrap class=\"r3\">");
                } else {
                    sb.append("<TD nowrap class=\"r2\">");
                }
            }
            sb.append(rec1);
            sb.append("</TD>\n");
        }
        sb.append("</TR>\n");
        atColumnName = false;
        return sb.toString();
    }

}
