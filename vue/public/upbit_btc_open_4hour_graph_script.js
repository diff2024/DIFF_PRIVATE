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

    fetch('/Main/UPBIT_BTC_OPEN_4HOUR_Ticker_Comparison?date='+date)
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

        var data_btc = new google.visualization.DataTable();
        data_btc.addColumn('string', 'X');
        data_btc.addColumn('number', 'BTC');
        array_data_btc = [];
        for(var y=0; y<list.length; y++){
            var array_datetime_kst = list[y]['datetime_kst']
            array_datetime_kst = array_datetime_kst.split('일 ')
            datetime_kst = array_datetime_kst[1];
            datetime_kst = datetime_kst
            
            if((datetime_kst).substr(0, 1) == '0'){
                datetime_kst = datetime_kst.substring(1)
            }
            
            var btc_o_c_percent = Number(list[y]['btc_o_c_percent'])
            array_data_btc.push([String(datetime_kst), Number(btc_o_c_percent)])
        }
        data_btc.addRows(array_data_btc);
        var options_btc = {
            title: 'BTC 시가대비 증감율(%)',	
            titleTextStyle: {
                fontName: '맑은 고딕',
                fontSize: 18,
                color: 'black',
                bold: true
            },
            chartArea: { width: '91%' },
            legend : 'none',
            series: {
                0: { color: '#F8941C' }
            },
            hAxis: {
                textStyle: {
                    fontName: '맑은 고딕',
                    fontSize: 15,
                    color: 'black'
                }
            },
            vAxis: {
                textStyle: {
                    fontName: '맑은 고딕',
                    fontSize: 13,
                    color: 'black',
                    bold: true
                }
            },
            width: 550,
            height: 250
        };
        
        var rank1_chart = new google.visualization.LineChart(document.getElementById('upbit_open_4hour_rank1_btc_graph'));
        rank1_chart.draw(data_btc, options_btc);

        var rank2_chart = new google.visualization.LineChart(document.getElementById('upbit_open_4hour_rank2_btc_graph'));
        rank2_chart.draw(data_btc, options_btc);

        var rank3_chart = new google.visualization.LineChart(document.getElementById('upbit_open_4hour_rank3_btc_graph'));
        rank3_chart.draw(data_btc, options_btc);

        var rank4_chart = new google.visualization.LineChart(document.getElementById('upbit_open_4hour_rank4_btc_graph'));
        rank4_chart.draw(data_btc, options_btc);

        var rank5_chart = new google.visualization.LineChart(document.getElementById('upbit_open_4hour_rank5_btc_graph'));
        rank5_chart.draw(data_btc, options_btc);

        var main_ranking = String(data_list[0]['main_ranking'])
        for(var x=0; x<Number(main_ranking); x++){
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'X');
            data.addColumn('number', (String(data_list[0]['rank'+String(Number(x)+1)+'_coin_ticker'])).replace('KRW-', ''));
            array_data = [];
            for(var y=0; y<list.length; y++){
                var array_datetime_kst = list[y]['datetime_kst']
                
                array_datetime_kst = array_datetime_kst.split('일 ')
                datetime_kst = array_datetime_kst[1];
                
                if((datetime_kst).substr(0, 1) == '0'){
                    datetime_kst = datetime_kst.substring(1)
                }
                var rank_o_c_percent = Number(list[y]['rank'+String(Number(x)+1)+'_o_c_percent'])
                array_data.push([String(datetime_kst), Number(rank_o_c_percent)])
            }

            data.addRows(array_data);
            var options = {
                title: (String(data_list[0]['rank'+String(Number(x)+1)+'_coin_ticker'])).replace('KRW-', '')+' 시가대비 증감율(%)',
                titleTextStyle: {
                    fontName: '맑은 고딕',
                    fontSize: 18,
                    color: 'black',
                    bold: true
                },
                chartArea: { width: '91%' },
                legend : 'none',
                series: {
                    0: { color: '#FF3636' }
                },
                hAxis: {
                    textStyle: {
                        fontName: '맑은 고딕',
                        fontSize: 15,
                        color: 'black'
                    }
                },
                vAxis: {
                    textStyle: {
                        fontName: '맑은 고딕',
                        fontSize: 13,
                        color: 'black',
                        bold: true
                    }
                },
                width: 600,
                height: 250
            };
            var chart = new google.visualization.LineChart(document.getElementById('upbit_open_4hour_rank'+String(Number(x)+1)+'_graph'));
            chart.draw(data, options);
        }
    });
}