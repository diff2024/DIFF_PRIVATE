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

    fetch('/Main/BITHUMB_BTC_OPEN_HOUR_Ticker_Comparison?date='+date)
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
            title: 'BTC 시가대비 변동율(%)',	
            titleTextStyle: {
                fontName: '맑은 고딕',
                fontSize: 20,
                color: 'black',
                bold: true
            },
            chartArea: { width: '95%' },
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
                    fontSize: 15,
                    color: 'black',
                    bold: true
                }
            },
            width: 1200,
            height: 250
        };
        var chart = new google.visualization.LineChart(document.getElementById('bithumb_open_hour_btc_graph'));
        chart.draw(data_btc, options_btc);
    });
}