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
    fetch('/Main/BITHUMB_DAY_RANKING?date='+date)
    .then((response) => response.json())
    .then((list) => {
        data_list = list;
        var btc_coin_ticker = data_list[0]['coin_ticker'];
        var btc_coin_kor_name = data_list[0]['coin_kor_name'];
        var btc_color = data_list[0]['color'];
        var btc_o_c_percent = data_list[0]['o_c_price_rate'];
        var rank_x_title = ''
        var rank_x = ''
        var ranking = ''
        var coin_ticker = ''
        var coin_kor_name = ''
        var color = ''
        var o_c_percent = ''
        var all_color = ''
        
        for(var x=1; x<data_list.length; x++){
            coin_ticker = data_list[x]['coin_ticker'];
            coin_kor_name = data_list[x]['coin_kor_name'];
            color = data_list[x]['color'];
            o_c_percent = data_list[x]['o_c_price_rate'];
            o_c_percent = zeroCut(o_c_percent)
            
            if(x%10==1){
                rank_x = String(x)
                rank_x_title = date.replace('20','').replace('-','년 ').replace('-','월 ') + '일 순위 '+ rank_x + '위 ~ ' + String(Number(rank_x)+9) + '위 변동율(%)'
                dataArray = [];
                dataArray.push(['X', 'PERCENT', { role: 'style' }, { role: 'annotation' } ]);
                dataArray.push([btc_coin_ticker, Number(btc_o_c_percent), '#F8941C', String(btc_o_c_percent)]);
                dataArray.push([coin_ticker, Number(o_c_percent), color, String(o_c_percent)]);
                
                if(Number(btc_o_c_percent) > 0 && Number(o_c_percent) > 0){
                    all_color = color
                }else if(Number(btc_o_c_percent) < 0 && Number(o_c_percent) < 0){
                    all_color = color
                }else{
                    all_color = ''
                }

            }else if(x%10==0){
                if(all_color != '' && all_color != color){
                    all_color = ''
                }
                dataArray.push([coin_ticker, Number(o_c_percent), color, String(o_c_percent)]);

                var data = new google.visualization.arrayToDataTable(dataArray);
                var options = {
                    bar: {groupWidth: "50%"},
                    chartArea: { width: '93%' },
                    legend: 'none',
                    hAxis: {
                        textStyle: {
                            fontName: '맑은 고딕',
                            fontSize: 14,
                            color: 'black',
                            bold: true
                        }
                    },
                    vAxis: {
                        textStyle: {
                            fontName: '맑은 고딕',
                            fontSize: 18,
                            color: 'black',
                            bold: true
                        }
                    },
                    annotations: {
                        textStyle: {
                            fontSize:20,
                            bold: false
                        }
                    },
                    width: 1250,
                    height: 250
                };

                if(all_color == 'red'){
                    options = {
                        bar: {groupWidth: "50%"},
                        chartArea: { width: '93%' },
                        legend: 'none',
                        hAxis: {
                            textStyle: {
                                fontName: '맑은 고딕',
                                fontSize: 14,
                                color: 'black',
                                bold: true
                            }
                        },
                        vAxis: {
                            textStyle: {
                                fontName: '맑은 고딕',
                                fontSize: 18,
                                color: 'black',
                                bold: true
                            },
                            viewWindow: { min: 0 }, viewWindowMode: "explicit"
                        },
                        annotations: {
                            textStyle: {
                                fontSize:20,
                                bold: false
                            }
                        },
                        width: 1250,
                        height: 250
                    };
                }else if(all_color == 'blue'){
                    options = {
                        bar: {groupWidth: "50%"},
                        chartArea: { width: '93%' },
                        legend: 'none',
                        hAxis: {
                            textStyle: {
                                fontName: '맑은 고딕',
                                fontSize: 14,
                                color: 'black',
                                bold: true
                            }
                        },
                        vAxis: {
                            textStyle: {
                                fontName: '맑은 고딕',
                                fontSize: 18,
                                color: 'black',
                                bold: true
                            },
                            viewWindow: { max: 0 }, viewWindowMode: "explicit"
                        },
                        annotations: {
                            textStyle: {
                                fontSize:20,
                                bold: false
                            }
                        },
                        width: 1250,
                        height: 250
                    };
                }
                
                var chart = new google.visualization.ColumnChart(document.getElementById('bithumb_btc_open_day_rank'+String(rank_x)+'_graph'));
                chart.draw(data, options);
            }else{
                if(all_color != '' && all_color != color){
                    all_color = ''
                }

                dataArray.push([coin_ticker, Number(o_c_percent), color, String(o_c_percent)]);
            }
        }
        
        var data = new google.visualization.arrayToDataTable(dataArray);
        var options = {
            bar: {groupWidth: "50%"},
            chartArea: { width: '93%' },
            legend: 'none',
            hAxis: {
                textStyle: {
                    fontName: '맑은 고딕',
                    fontSize: 14,
                    color: 'black',
                    bold: true
                }
            },
            vAxis: {
                textStyle: {
                    fontName: '맑은 고딕',
                    fontSize: 18,
                    color: 'black',
                    bold: true
                }
            },
            annotations: {
                textStyle: {
                    fontSize:20,
                    bold: false
                }
            },
            width: 1250,
            height: 250
        };

        if(all_color == 'red'){
            options = {
                bar: {groupWidth: "50%"},
                chartArea: { width: '93%' },
                legend: 'none',
                hAxis: {
                    textStyle: {
                        fontName: '맑은 고딕',
                        fontSize: 14,
                        color: 'black',
                        bold: true
                    }
                },
                vAxis: {
                    textStyle: {
                        fontName: '맑은 고딕',
                        fontSize: 18,
                        color: 'black',
                        bold: true
                    },
                    viewWindow: { min: 0 }, viewWindowMode: "explicit"
                },
                annotations: {
                    textStyle: {
                        fontSize:18,
                        bold: false
                    }
                },
                width: 1250,
                height: 250
            };
        }else if(all_color == 'blue'){
            options = {
                bar: {groupWidth: "50%"},
                chartArea: { width: '93%' },
                legend: 'none',
                hAxis: {
                    textStyle: {
                        fontName: '맑은 고딕',
                        fontSize: 14,
                        color: 'black',
                        bold: true
                    }
                },
                vAxis: {
                    textStyle: {
                        fontName: '맑은 고딕',
                        fontSize: 18,
                        color: 'black',
                        bold: true
                    },
                    viewWindow: { max: 0 }, viewWindowMode: "explicit"
                },
                annotations: {
                    textStyle: {
                        fontSize:18,
                        bold: false
                    }
                },
                width: 1250,
                height: 250
            };
        }
        
        var chart = new google.visualization.ColumnChart(document.getElementById('bithumb_btc_open_day_rank'+String(rank_x)+'_graph'));
        chart.draw(data, options);
    })
}

function zeroCut(nn) {
    if (nn == undefined || nn == null){
        return nn;
    }

    nn = nn + "";
    var num3Arr = nn.split(".");
    var arr1 = num3Arr[1];
    var isNotZero = false;
    var result = "";
    for (var i = arr1.length;i > 0; i--) {
        var n = i-1;
        var charA = arr1.charAt(n);
        if (charA == '0') {
            if (isNotZero == true) {
                result += "0";
            } else {
                result += "";
            }
        } else {
            isNotZero = true;
            result += arr1.charAt(n);
        }
    }

    var return_data = ''
    if((result.split("").reverse().join("")) == ''){
        return_data = num3Arr[0]
    }else{
        return_data = num3Arr[0] + "." + result.split("").reverse().join("")
    }
    return return_data;
}