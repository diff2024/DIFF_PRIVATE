google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    var data_list = null;
    var dataArray = [];
    
    var url_location = window.location.pathname;
    var date = (url_location.split('/'))[2]
    if(date.length != 10 || isNaN(date.replaceAll('-',''))){
        date = ''
    }

    dataArray.push(['X', 'PERCENT', { role: 'style' }, { role: 'annotation' } ]);
    fetch('/Main/UPBIT_DAY_RANKING?date='+date)
    .then((response) => response.json())
    .then((list) => {
        data_list = list;
        var btc_coin_ticker = data_list[0]['coin_ticker'];
        var btc_coin_kor_name = data_list[0]['coin_kor_name'];
        var btc_color = data_list[0]['color'];
        var btc_o_c_percent = data_list[0]['o_c_price_rate'];
        var rank_x = ''
        var ranking = ''
        var coin_ticker = ''
        var coin_kor_name = ''
        var color = ''
        var o_c_percent = ''
        
        for(var x=1; x<data_list.length; x++){
            coin_ticker = data_list[x]['coin_ticker'];
            coin_kor_name = data_list[x]['coin_kor_name'];
            color = data_list[x]['color'];
            o_c_percent = data_list[x]['o_c_price_rate'];
            
            if(x%20==1){
                rank_x = String(x)
                dataArray = [];
                dataArray.push(['X', 'PERCENT', { role: 'style' }, { role: 'annotation' } ]);
                dataArray.push([btc_coin_ticker, Number(btc_o_c_percent), '#F8941C', String(btc_o_c_percent)]);
                dataArray.push([coin_ticker, Number(o_c_percent), color, String(o_c_percent)]);

            }else if(x%20==0){
                dataArray.push([coin_ticker, Number(o_c_percent), color, String(o_c_percent)]);

                var data = new google.visualization.arrayToDataTable(dataArray);
                var options = {
                    bar: {groupWidth: "45%"},
                    chartArea: { width: '90%' },
                    legend: 'none',
                    hAxis: {
                        textStyle: {
                            fontName: '맑은 고딕',
                            fontSize: 13,
                            color: 'black',
                            bold: true
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
                    width: 900,
                    height: 400
                };
                var chart = new google.visualization.ColumnChart(document.getElementById('upbit_day_rank_'+String(rank_x)+'_graph'));
                chart.draw(data, options);
            }else{
                dataArray.push([coin_ticker, Number(o_c_percent), color, String(o_c_percent)]);
            }
        }
        var data = new google.visualization.arrayToDataTable(dataArray);
        var options = {
            bar: {groupWidth: "45%"},
            chartArea: { width: '90%' },
            legend: 'none',
            hAxis: {
                textStyle: {
                    fontName: '맑은 고딕',
                    fontSize: 13,
                    color: 'black',
                    bold: true
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
            width: 900,
            height: 400
        };
        var chart = new google.visualization.ColumnChart(document.getElementById('upbit_day_rank_'+String(rank_x)+'_graph'));
        chart.draw(data, options);
    })
}