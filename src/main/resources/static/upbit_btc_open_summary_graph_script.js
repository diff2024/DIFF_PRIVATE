
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

    fetch('/Main/UPBIT_DAY_RANKING?date='+date)
    .then((response) => response.json())
    .then((list) => {
        data_list = list;
        
        var rise_count = '0';
        var drop_count = '0';
        var station_count = '0';

        for(var x=1; x<data_list.length; x++){
            if(String(data_list[x].status) == '상승'){
                rise_count = String(Number(rise_count)+1)
            }else if(String(data_list[x].status) == '하락'){
                drop_count = String(Number(drop_count)+1)
            }else{
                station_count = String(Number(station_count)+1)
            }
        }

        var data = new google.visualization.DataTable();
        data.addColumn('string','변동');
        data.addColumn('number','비중');

        data.addRows([ 
            ['상승', Number(rise_count)],
            ['보합', Number(station_count)],
            ['하락', Number(drop_count)]
        ]);
        var opt = {
                chartArea: { width: '100%', height: '85%' },
                'width':450,
                'height':300,
                vAxis: {
                    textStyle: {
                        fontName: '맑은 고딕',
                        fontSize: 30,
                        color: 'black'
                    }
                },
                colors: ['red', 'black', 'blue'],
                pieSliceText: 'value',
                pieSliceTextStyle: {
                    fontSize: 22
                },
                pieHole: 0.3,
                legend: { position: 'labeled', textStyle: {color: 'black', fontSize: 20} },
        };
        var chart = new google.visualization.PieChart(document.getElementById('upbit_open_summary_graph'));
        chart.draw(data,opt);
    })
}