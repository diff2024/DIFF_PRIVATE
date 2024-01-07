google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    // RANKING List
    var data_list = null;
    var array_data = [];
    
    var url_location = window.location.pathname;
    var date = (url_location.split('/'))[2]
    if(date.length != 10 || isNaN(date.replaceAll('-',''))){
        date = ''
    }

    fetch('/Main/BYBIT_BTC_HOUR_Ticker_Comparison?date='+date)
    .then((response) => response.json())
    .then((list) => {
        data_list = list;
        var date_kst = (String(data_list[0]['datetime_kst'])).substring(0, 7)
        if(date_kst.substring(0,1) == '0' && date_kst.substring(4,5) == '0'){
            date_kst = (String(data_list[0]['datetime_kst'])).substring(1, 3) + ' '+ (String(data_list[0]['datetime_kst'])).substring(5, 7)
        }else if(date_kst.substring(0,1) != '0' && date_kst.substring(4,5) == '0'){
            date_kst = (String(data_list[0]['datetime_kst'])).substring(0, 3) + ' ' + (String(data_list[0]['datetime_kst'])).substring(5, 7)
        }else if(date_kst.substring(0,1) == '0' && date_kst.substring(4,5) != '0'){
            date_kst = (String(data_list[0]['datetime_kst'])).substring(1, 7)
        }
        var main_ranking = String(data_list[0]['main_ranking'])
        for(var x=0; x<Number(main_ranking); x++){
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'X');
            data.addColumn('number', 'BTC');
            data.addColumn('number', (String(data_list[0]['rank'+String(Number(x)+1)+'_coin_ticker'])).replace('USDT', ''));
            array_data = [];
            for(var y=0; y<list.length; y++){
                var array_datetime_kst = list[y]['datetime_kst']
                /*
                array_datetime_kst = array_datetime_kst.split('월 ')
                datetime_kst = array_datetime_kst[1];
                
                if((datetime_kst.slice(3)).substr(1, 1) == '0'){
                    datetime_kst = datetime_kst.substring(0, 4) + datetime_kst.substring(5)
                }

                if((datetime_kst).substr(0, 1) == '0'){
                    datetime_kst = datetime_kst.substring(1)
                }
                */

                array_datetime_kst = array_datetime_kst.split('일 ')
                datetime_kst = array_datetime_kst[1];
                datetime_kst = datetime_kst.replace("시", "")
                
                if((datetime_kst).substr(0, 1) == '0'){
                    datetime_kst = datetime_kst.substring(1)
                }

                var btc_o_c_percent = Number(list[y]['btc_o_c_percent'])
                var rank_o_c_percent = Number(list[y]['rank'+String(Number(x)+1)+'_o_c_percent'])
                array_data.push([String(datetime_kst), Number(btc_o_c_percent), Number(rank_o_c_percent)])
            }

            data.addRows(array_data);
            var options = {
                title: date_kst+' BTC / '+(String(data_list[0]['rank'+String(Number(x)+1)+'_coin_ticker'])).replace('USDT', '')+' 1시간봉 증감율(%)',	
                titleTextStyle: {
                    fontName: '맑은 고딕',
                    fontSize: 20,
                    color: 'black',
                    bold: true
                },
                chartArea: { width: '90%' },
                legend: { position: 'bottom' },
                series: {
                    0: { color: '#F8941C' },
                    1: { color: '#FF3636' }
                },
                hAxis: {
                    textStyle: {
                        fontName: '맑은 고딕',
                        fontSize: 12,
                        color: 'black'
                    }
                },
                vAxis: {
                    textStyle: {
                        fontName: '맑은 고딕',
                        fontSize: 12,
                        color: 'black',
                        bold: true
                    }
                },
                width: 900,
                height: 350
            };
            var chart = new google.visualization.LineChart(document.getElementById('bybit_hour_rank'+String(Number(x)+1)+'_graph'));
            chart.draw(data, options);
        }
    });
}