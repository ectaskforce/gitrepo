/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function onReady() {
        
        // 締日の自動入力
        $("#datadate").on("change", function () {
            
           // 起票日を取得
           var simeDate = $("#datadate").val();
           
           // 日付セパレータでsplit
           var simeSplit = simeDate.split("-");
           
           // 3個に出来たら
           if ( simeSplit.length === 3 ) {
               var sDate = new Date(simeSplit[0], simeSplit[1], simeSplit[2]);
               
               // 日付として認可出来るなら
               if ( sDate.getDate() > 0 && simeSplit[2].match(/[0-9]+/) ) {
                   // 締日を計算する
                   var monp = 1;
                   
                   // 9日以上なら翌々月
                   if ( parseInt(simeSplit[2]) > 8 ) {
                       monp = 2;
                   }
                   
                   // 付きの0サブ
                   simeSplit[1] = parseInt(simeSplit[1]) + monp ;
                   if ( parseInt(simeSplit[1]) > 12 ) {
                       simeSplit[0] ++;
                       simeSplit[1] = parseInt(simeSplit[1]) - 12;
                   }
                   simeSplit[1] = "0" + simeSplit[1] ;
                   simeSplit[1] = simeSplit[1].substring(simeSplit[1].length - 2);
                   simeSplit[2] = '05';
                   
                   // 締日を自動で入力
                   $("#sime").val( simeSplit[0] + "-" + simeSplit[1] + "-" + simeSplit[2] );
               }
           }
        }
        );
    
}
