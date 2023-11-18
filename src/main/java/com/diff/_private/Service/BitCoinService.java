package com.diff._private.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.diff._private.Mapper.BitCoinMapper;

@Service
@Transactional
public class BitCoinService {
	
	@Autowired
	BitCoinMapper BitCoinMapper;
	
	public List<HashMap<String, String>> CoinInfo() throws Exception{
		return BitCoinMapper.CoinInfo();
	}
	
	public List<HashMap<String, String>> MainGridList(HashMap<String, String> map) throws Exception{
		return BitCoinMapper.MainGridList(map);
	}
	
	public void CoinDailyReportDelete(HashMap<String, String> map) throws Exception{
		BitCoinMapper.CoinDailyReportDelete(map);
		BitCoinMapper.CoinDailyReportHTMLDelete(map);
	}
	
	public void CoinDailyReportReg(HashMap<String, String> map) throws Exception{
		BitCoinMapper.CoinDailyReportReg(map);
	}
	
	public void CoinDailyReportScriptReg(HashMap<String, String> map) throws Exception{
		DecimalFormat formatter = new DecimalFormat("###,###.########");
		String date = map.get("date");
		String kor_date = (map.get("date")).substring(2, 4) + "년 "+(map.get("date")).substring(5, 7)+"월 "+((map.get("date")).substring(8, 9)).replace("0", "") + ((map.get("date")).substring(9, 10)) +"일";
        String short_kor_date = (map.get("date")).substring(5, 7)+"월 "+((map.get("date")).substring(8, 9)).replace("0", "") + ((map.get("date")).substring(9, 10)) +"일";
        
        String yesterday_kor_date = (map.get("yesterday")).substring(2, 4) + "년 "+(map.get("yesterday")).substring(5, 7)+"월 "+((map.get("yesterday")).substring(8, 9)).replace("0", "") + ((map.get("yesterday")).substring(9, 10)) +"일";
        String yesterday_short_kor_date = (map.get("yesterday")).substring(5, 7)+"월 "+((map.get("yesterday")).substring(8, 9)).replace("0", "") + ((map.get("yesterday")).substring(9, 10)) +"일";
        
        String MainRankingCount = map.get("MainRankingCount");
        String SubRankingCount = map.get("SubRankingCount");
        List<HashMap<String, String>> coinDailyReportList = BitCoinMapper.CoinDailyReportList(map);
        
        String total_count = "";
        String increase_count = "";
        String degradation_count = "";
        String flat_count = "";
        
        int int_total_count = 0;
    	int int_increase_count = 0;
    	int int_degradation_count = 0;
    	int int_flat_count = 0;
    	
    	String increase_coin = "";
    	String degradation_coin = "";
    	String flat_coin = "";
    	
        for(int x=0; x<coinDailyReportList.size(); x++) {
        	if ((coinDailyReportList.get(x).get("status")).equals("보합")) {
        		int_flat_count = int_flat_count+1;
        		
        		if(flat_coin.equals("")) {
        			flat_coin = coinDailyReportList.get(x).get("coin_ticker");
        		} else {
        			flat_coin = flat_coin + ", " + coinDailyReportList.get(x).get("coin_ticker");
        		}
        	} else if ((coinDailyReportList.get(x).get("status")).equals("하락")) {
        		int_degradation_count = int_degradation_count+1;
        		
        		if(degradation_coin.equals("")) {
        			degradation_coin = coinDailyReportList.get(x).get("coin_ticker");
        		} else {
        			degradation_coin = degradation_coin + ", " + coinDailyReportList.get(x).get("coin_ticker");
        		}
        	} else {
        		int_increase_count = int_increase_count+1;

        		if(increase_coin.equals("")) {
        			increase_coin = coinDailyReportList.get(x).get("coin_ticker");
        		} else {
        			increase_coin = increase_coin + ", " + coinDailyReportList.get(x).get("coin_ticker");
        		}
        	}
        	int_total_count = int_total_count+1;
        	
        	if((coinDailyReportList.size()-1) == x) {
        		total_count = Integer.toString(int_total_count);
        		increase_count = Integer.toString(int_increase_count);
        		degradation_count = Integer.toString(int_degradation_count);
        		flat_count = Integer.toString(int_flat_count);
        	}
        }
        
		int row = 0;
		int ReportNum = 0;
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> htmlMap = new HashMap<String, String>();
		String html = "";
		
		html = "<div id=\"report\">";
		htmlMap.put("id", "report");
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		row = row+1;
		ReportNum = ReportNum+1;
		html = "<h1 id=\"report_"+Integer.toString(ReportNum)+"\" style=\"color:black; font-weight:bold;\">빗썸 "+kor_date+" 코인 분석</h1><br/>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "N");
		list.add(htmlMap);
		
		row = row+1;
		ReportNum = ReportNum+1;
		html = "<p id=\"report_"+Integer.toString(ReportNum)+"\" style=\"font-size:16px; color:black;\">빗썸 "+kor_date+" 코인 분석을 시작하겠습니다.</p>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		html = "";
		html += "<br/><table id=\"tbl_summary\" style=\"color:black; border-spacing:0px; border-color:black;\">";
		html += "<colgroup>";
		html += "<col style=\"width: 70px\">";
		html += "<col style=\"width: 70px\">";
		html += "<col style=\"width: 120px\">";
		html += "<col style=\"width: 120px\">";
		html += "<col style=\"width: 120px\">";
		html += "<col style=\"width: 120px\">";
		html += "<col style=\"width: 120px\">";
		html += "<col style=\"width: 120px\">";
		html += "</colgroup>";
		html += "<tr>";
		html += "<td colspan=\"8\" style=\"height:60px; border: 3px solid black; font-weight:bold; font-size: 28px; text-align:center;\">빗썸 일일 분석 "+date+"</td>";
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td colspan=\"8\" style=\"height:20px;\"></td>";
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-top: 3px solid black; border-left: 3px solid black; font-weight:bold; font-size: 24px; text-align:center; color:red;\">상승</td>";
		html += "<td style=\"border: 1px solid black; border-top: 3px solid black; font-weight:bold; font-size: 24px; text-align:right; padding-right:3px;\">"+increase_count+"</td>";
		html += "<td colspan=\"6\" style=\"border: 1px solid black; border-top: 3px solid black; border-right: 3px solid black; color: black; font-size: 13px; padding-left:3px; padding-right:3px; padding-top: 10px; padding-bottom: 10px; white-space: pre-wrap; word-break: break-all;\">"+increase_coin+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-left: 3px solid black; font-weight:bold; font-size: 24px; text-align:center;\">보합</td>";
		html += "<td style=\"border: 1px solid black; font-weight:bold; font-size: 24px; text-align:right; padding-right:3px;\">"+flat_count+"</td>";
		html += "<td colspan=\"6\" style=\"border: 1px solid black; border-right: 3px solid black; color: black; font-size: 13px; padding-left:3px; padding-right:3px; padding-top: 10px; padding-bottom: 10px; white-space: pre-wrap; word-break: break-all;\">"+flat_coin+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-bottom: 3px solid black; border-left: 3px solid black; font-weight:bold; font-size: 24px; text-align:center; color:blue;\">하락</td>";
		html += "<td style=\"border: 1px solid black; border-bottom: 3px solid black; font-weight:bold; font-size: 24px; text-align:right; padding-right:3px;\">"+degradation_count+"</td>";
		html += "<td colspan=\"6\" style=\"border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; color: black; font-size: 13px; padding-left:3px; padding-right:3px; padding-top: 10px; padding-bottom: 10px; white-space: pre-wrap; word-break: break-all;\">"+degradation_coin+"</td>";
		html += "</tr>";
		html += "</table><br/>";
		
		row = row+1;
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "tbl_summary");
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "N");
		list.add(htmlMap);
		
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "image");
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", "bithumb_summary.png");
		htmlMap.put("diff_yn", "N");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		String coinTitle = coinDailyReportList.get(0).get("coin_title");
		String coinKorName = coinDailyReportList.get(0).get("coin_kor_name");
		String ranking = coinDailyReportList.get(0).get("ranking");
		String status = coinDailyReportList.get(0).get("status");
		String o_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_price")));
		String l_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_price")));
		String h_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("h_price")));
		String c_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("c_price")));
		String o_c_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_c_price")));
		String o_c_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_c_price_rate")));
		String l_h_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_h_price")));
		String l_h_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_h_price_rate")));
		String lower_price_time = coinDailyReportList.get(0).get("lower_price_time");
		String higher_price_time = coinDailyReportList.get(0).get("higher_price_time");
		String lower_volume_time = coinDailyReportList.get(0).get("lower_volume_time");
		String higher_volume_time = coinDailyReportList.get(0).get("higher_volume_time");
		String o_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_0_4")));
		String l_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_0_4")));
		String h_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("h_0_4")));
		String c_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("c_0_4")));
		String v_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("v_0_4")));
		String o_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_4_8")));
		String l_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_4_8")));
		String h_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("h_4_8")));
		String c_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("c_4_8")));
		String v_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("v_4_8")));
		String o_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_8_12")));
		String l_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_8_12")));
		String h_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("h_8_12")));
		String c_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("c_8_12")));
		String v_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("v_8_12")));
		String o_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_12_16")));
		String l_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_12_16")));
		String h_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("h_12_16")));
		String c_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("c_12_16")));
		String v_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("v_12_16")));
		String o_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_16_20")));
		String l_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_16_20")));
		String h_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("h_16_20")));
		String c_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("c_16_20")));
		String v_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("v_16_20")));
		String o_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_20_24")));
		String l_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_20_24")));
		String h_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("h_20_24")));
		String c_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("c_20_24")));
		String v_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("v_20_24")));
		
		row = row+1;
		ReportNum = ReportNum+1;
		html = "<h2 id=\"report_"+Integer.toString(ReportNum)+"\" style=\"font-size:23px; color:black;\"><b>"+short_kor_date+" "+coinTitle+"</b></h2>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "N");
		list.add(htmlMap);
		
		html = "<h2 id=\"report_"+Integer.toString(ReportNum)+"\" style=\"font-size:23px; color:black; margin-top:0px;\"><b>"+short_kor_date+" "+coinTitle+"</b></h2>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "N");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		html = "";
		html += "<br/><table id=\"btc_tbl_main\" style=\"color:black; border-spacing:0px; border-color:black; font-size:13px;\">";
		html += "<colgroup>";
		html += "<col style=\"width: 60px\">";
		html += "<col style=\"width: 60px\">";
		html += "<col style=\"width: 80px\">";
		html += "<col style=\"width: 80px\">";
		html += "<col style=\"width: 60px\">";
		html += "<col style=\"width: 60px\">";
		html += "<col style=\"width: 80px\">";
		html += "<col style=\"width: 80px\">";
		html += "</colgroup>";
		html += "<tr style=\"border: 1px solid black;\">";
		html += "<td style=\"height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;\">기준</td>";
		html += "<td style=\"height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;\" colspan=\"7\">"+coinTitle+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">시가</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+o_price+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">종가</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+c_price+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">저가</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+l_price+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">고가</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+h_price+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">시가 대비 종가</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+o_c_price+"원 ["+o_c_price_rate+"%]</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">저가 대비 고가</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+l_h_price+"원 ["+l_h_price_rate+"%]</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">최저가 시간</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px;\" colspan=\"2\">"+lower_price_time+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">최고가 시간</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px;\" colspan=\"2\">"+higher_price_time+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">최저 거래량 시간</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px;\" colspan=\"2\">"+lower_volume_time+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">최고 거래량 시간</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px;\" colspan=\"2\">"+higher_volume_time+"</td>";
		html += "</tr>";
		html += "</table>";
		html += "<br/>";
		
		row = row+1;
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "btc_tbl_main");
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "N");
		list.add(htmlMap);
		
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "image");
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", "bithumb_btc_main.png");
		htmlMap.put("diff_yn", "N");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		html  = "";
		html += "<table id=\"btc_tbl_sub\" style=\"color:black; border-spacing:0px; border-color:black; font-size:13px;\">";
		html += "<colgroup>";
		html += "<col style=\"width: 95px\">";
		html += "<col style=\"width: 90px\">";
		html += "<col style=\"width: 90px\">";
		html += "<col style=\"width: 90px\">";
		html += "<col style=\"width: 90px\">";
		html += "<col style=\"width: 90px\">";
		html += "</colgroup>";
		html += "<tr style=\"border: 1px solid black;\">";
		html += "<td style=\"height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;\">기준</td>";
		html += "<td style=\"height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;\" colspan=\"5\">"+coinTitle+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">기준시간</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">시가</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">저가</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">고가</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">종가</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">거래량</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">00:00 ~ 03:59</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_0_4+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(l_price.equals(l_0_4)) {
			html += "color:#0000FF";
		}
		html += " \">"+l_0_4+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(h_price.equals(h_0_4)) {
			html += "color:#FF0000";
		}
		html += " \">"+h_0_4+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_0_4+"</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_0_4+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">04:00 ~ 07:59</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_4_8+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(l_price.equals(l_4_8)) {
			html += "color:#0000FF";
		}
		html += " \">"+l_4_8+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(h_price.equals(h_4_8)) {
			html += "color:#FF0000";
		}
		html += " \">"+h_4_8+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_4_8+"</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_4_8+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">08:00 ~ 11:59</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_8_12+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(l_price.equals(l_8_12)) {
			html += "color:#0000FF";
		}
		html += " \">"+l_8_12+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(h_price.equals(h_8_12)) {
			html += "color:#FF0000";
		}
		html += " \">"+h_8_12+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_8_12+"</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_8_12+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">12:00 ~ 15:59</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_12_16+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(l_price.equals(l_12_16)) {
			html += "color:#0000FF";
		}
		html += " \">"+l_12_16+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(h_price.equals(h_12_16)) {
			html += "color:#FF0000";
		}
		html += " \">"+h_12_16+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_12_16+"</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_12_16+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">16:00 ~ 19:59</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_16_20+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(l_price.equals(l_16_20)) {
			html += "color:#0000FF";
		}
		html += " \">"+l_16_20+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(h_price.equals(h_16_20)) {
			html += "color:#FF0000";
		}
		html += " \">"+h_16_20+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right;\">"+c_16_20+"</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_16_20+"</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">20:00 ~ 23:59</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_20_24+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(l_price.equals(l_20_24)) {
			html += "color:#0000FF";
		}
		html += " \">"+l_20_24+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
		if(h_price.equals(h_20_24)) {
			html += "color:#FF0000";
		}
		html += " \">"+h_20_24+"</td>";
		html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_20_24+"</td>";
		html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_20_24+"</td>";
		html += "</tr>";
		html += "</table>";
		html += "<br/>";
		
		row = row+1;
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "btc_tbl_sub");
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "N");
		list.add(htmlMap);
		
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "image");
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", "bithumb_btc_sub.png");
		htmlMap.put("diff_yn", "N");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		row = row+1;
		ReportNum = ReportNum+1;
		html = "<p id=\"report_"+Integer.toString(ReportNum)+"\" style=\"font-size:16px; color:black;\">BTC 기준 시가 대비 종가 "+o_c_price_rate+"% "+status+"하였으며, "+kor_date+" 빗썸 원화 코인 "+total_count+"종 중 <span style=\"color: #ee2323;\"><b>상승인 코인이 "+increase_count+"종</b></span>, <span style=\"color: #006dd7;\"><b>하락인 코인이 "+degradation_count+"종</b></span>, <b>보합인 코인이 "+flat_count+"종</b>입니다.<br />"+kor_date+"에 투자했으면 가장 안정적으로 많이 벌 수 있었던 코인 순위를 알려드리겠습니다.<br />오늘 코인 매매 시 한 번 고려 해보시기 바랍니다.</p>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		for(int x=Integer.parseInt(MainRankingCount); x>=1; x--) {
			coinTitle = coinDailyReportList.get(x).get("coin_title");
			coinKorName = coinDailyReportList.get(x).get("coin_kor_name");
			ranking = coinDailyReportList.get(x).get("ranking");
			status = coinDailyReportList.get(x).get("status");
			o_price = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("o_price")));
			l_price = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("l_price")));
			h_price = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("h_price")));
			c_price = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("c_price")));
			o_c_price = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("o_c_price")));
			o_c_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("o_c_price_rate")));
			l_h_price = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("l_h_price")));
			l_h_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("l_h_price_rate")));
			lower_price_time = coinDailyReportList.get(x).get("lower_price_time");
			higher_price_time = coinDailyReportList.get(x).get("higher_price_time");
			lower_volume_time = coinDailyReportList.get(x).get("lower_volume_time");
			higher_volume_time = coinDailyReportList.get(x).get("higher_volume_time");
			o_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("o_0_4")));
			l_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("l_0_4")));
			h_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("h_0_4")));
			c_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("c_0_4")));
			v_0_4 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("v_0_4")));
			o_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("o_4_8")));
			l_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("l_4_8")));
			h_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("h_4_8")));
			c_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("c_4_8")));
			v_4_8 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("v_4_8")));
			o_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("o_8_12")));
			l_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("l_8_12")));
			h_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("h_8_12")));
			c_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("c_8_12")));
			v_8_12 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("v_8_12")));
			o_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("o_12_16")));
			l_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("l_12_16")));
			h_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("h_12_16")));
			c_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("c_12_16")));
			v_12_16 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("v_12_16")));
			o_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("o_16_20")));
			l_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("l_16_20")));
			h_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("h_16_20")));
			c_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("c_16_20")));
			v_16_20 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("v_16_20")));
			o_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("o_20_24")));
			l_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("l_20_24")));
			h_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("h_20_24")));
			c_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("c_20_24")));
			v_20_24 = formatter.format(Double.parseDouble(coinDailyReportList.get(x).get("v_20_24")));
			
			row = row+1;
			ReportNum = ReportNum+1;
			html = "<br/><h2 id=\"report_"+Integer.toString(ReportNum)+"\" style=\"font-size:23px; color:black;\"><b>"+short_kor_date+" "+ranking+"순위 "+coinTitle+"</b></h2>";
			htmlMap = new HashMap<String, String>();
			htmlMap.put("id", "report_"+Integer.toString(ReportNum));
			htmlMap.put("row", Integer.toString(row));
			htmlMap.put("col", "0");
			htmlMap.put("html", html);
			htmlMap.put("diff_yn", "Y");
			htmlMap.put("blog_yn", "N");
			list.add(htmlMap);
			
			html = "<br/><h2 id=\"report_"+Integer.toString(ReportNum)+"\" style=\"font-size:23px; color:black; margin-top:0px;\"><b>"+short_kor_date+" "+ranking+"순위 "+coinTitle+"</b></h2>";
			htmlMap = new HashMap<String, String>();
			htmlMap.put("id", "report_"+Integer.toString(ReportNum));
			htmlMap.put("row", Integer.toString(row));
			htmlMap.put("col", "0");
			htmlMap.put("html", html);
			htmlMap.put("diff_yn", "N");
			htmlMap.put("blog_yn", "Y");
			list.add(htmlMap);
			
			html = "";
			html += "<br/><table id=\"coin_"+ranking+"_tbl_main\" style=\"color:black; border-spacing:0px; border-color:black; font-size:13px;\">";
			html += "<colgroup>";
			html += "<col style=\"width: 60px\">";
			html += "<col style=\"width: 60px\">";
			html += "<col style=\"width: 80px\">";
			html += "<col style=\"width: 80px\">";
			html += "<col style=\"width: 60px\">";
			html += "<col style=\"width: 60px\">";
			html += "<col style=\"width: 80px\">";
			html += "<col style=\"width: 80px\">";
			html += "</colgroup>";
			html += "<tr style=\"border: 1px solid black;\">";
			html += "<td style=\"height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;\">"+ranking+"위</td>";
			html += "<td style=\"height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;\" colspan=\"7\">"+coinTitle+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">시가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+o_price+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">종가</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+c_price+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">저가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+l_price+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">고가</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+h_price+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">시가 대비 종가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+o_c_price+"원 ["+o_c_price_rate+"%]</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">저가 대비 고가</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px;\" colspan=\"2\">"+l_h_price+"원 ["+l_h_price_rate+"%]</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">최저가 시간</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px;\" colspan=\"2\">"+lower_price_time+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">최고가 시간</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px;\" colspan=\"2\">"+higher_price_time+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">최저 거래량 시간</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px;\" colspan=\"2\">"+lower_volume_time+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;\" colspan=\"2\">최고 거래량 시간</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px;\" colspan=\"2\">"+higher_volume_time+"</td>";
			html += "</tr>";
			html += "</table>";
			html += "<br/>";
			
			row = row+1;
			htmlMap = new HashMap<String, String>();
			htmlMap.put("id", "coin_"+ranking+"_tbl_main");
			htmlMap.put("row", Integer.toString(row));
			htmlMap.put("col", "0");
			htmlMap.put("html", html);
			htmlMap.put("diff_yn", "Y");
			htmlMap.put("blog_yn", "N");
			list.add(htmlMap);
			
			htmlMap = new HashMap<String, String>();
			htmlMap.put("id", "image");
			htmlMap.put("row", Integer.toString(row));
			htmlMap.put("col", "0");
			htmlMap.put("html", "bithumb_coin"+ranking+"_main.png");
			htmlMap.put("diff_yn", "N");
			htmlMap.put("blog_yn", "Y");
			list.add(htmlMap);
			
			html  = "";
			html += "<table id=\"coin_"+ranking+"_tbl_sub\" style=\"color:black; border-spacing:0px; border-color:black; font-size:13px;\">";
			html += "<colgroup>";
			html += "<col style=\"width: 95px\">";
			html += "<col style=\"width: 90px\">";
			html += "<col style=\"width: 90px\">";
			html += "<col style=\"width: 90px\">";
			html += "<col style=\"width: 90px\">";
			html += "<col style=\"width: 90px\">";
			html += "</colgroup>";
			html += "<tr style=\"border: 1px solid black;\">";
			html += "<td style=\"height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;\">"+ranking+"위</td>";
			html += "<td style=\"height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;\" colspan=\"5\">"+coinTitle+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">기준시간</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">시가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">저가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">고가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">종가</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">거래량</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">00:00 ~ 03:59</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_0_4+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(l_price.equals(l_0_4)) {
				html += "color:#0000FF";
			}
			html += " \">"+l_0_4+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(h_price.equals(h_0_4)) {
				html += "color:#FF0000";
			}
			html += " \">"+h_0_4+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_0_4+"</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_0_4+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">04:00 ~ 07:59</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_4_8+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(l_price.equals(l_4_8)) {
				html += "color:#0000FF";
			}
			html += " \">"+l_4_8+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(h_price.equals(h_4_8)) {
				html += "color:#FF0000";
			}
			html += " \">"+h_4_8+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_4_8+"</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_4_8+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">08:00 ~ 11:59</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_8_12+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(l_price.equals(l_8_12)) {
				html += "color:#0000FF";
			}
			html += " \">"+l_8_12+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(h_price.equals(h_8_12)) {
				html += "color:#FF0000";
			}
			html += " \">"+h_8_12+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_8_12+"</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_8_12+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">12:00 ~ 15:59</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_12_16+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(l_price.equals(l_12_16)) {
				html += "color:#0000FF";
			}
			html += " \">"+l_12_16+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(h_price.equals(h_12_16)) {
				html += "color:#FF0000";
			}
			html += " \">"+h_12_16+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_12_16+"</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_12_16+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">16:00 ~ 19:59</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_16_20+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(l_price.equals(l_16_20)) {
				html += "color:#0000FF";
			}
			html += " \">"+l_16_20+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(h_price.equals(h_16_20)) {
				html += "color:#FF0000";
			}
			html += " \">"+h_16_20+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_16_20+"</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_16_20+"</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">20:00 ~ 23:59</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+o_20_24+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(l_price.equals(l_20_24)) {
				html += "color:#0000FF";
			}
			html += " \">"+l_20_24+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; ";
			if(h_price.equals(h_20_24)) {
				html += "color:#FF0000";
			}
			html += " \">"+h_20_24+"</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+c_20_24+"</td>";
			html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+v_20_24+"</td>";
			html += "</tr>";
			html += "</table>";
			html += "<br/>";
			
			row = row+1;
			htmlMap = new HashMap<String, String>();
			htmlMap.put("id", "coin_"+ranking+"_tbl_sub");
			htmlMap.put("row", Integer.toString(row));
			htmlMap.put("col", "0");
			htmlMap.put("html", html);
			htmlMap.put("diff_yn", "Y");
			htmlMap.put("blog_yn", "N");
			list.add(htmlMap);
			
			htmlMap = new HashMap<String, String>();
			htmlMap.put("id", "image");
			htmlMap.put("row", Integer.toString(row));
			htmlMap.put("col", "0");
			htmlMap.put("html", "bithumb_coin"+ranking+"_sub.png");
			htmlMap.put("diff_yn", "N");
			htmlMap.put("blog_yn", "Y");
			list.add(htmlMap);
			
			row = row+1;
			ReportNum = ReportNum+1;
			html = "<p id=\"report_"+Integer.toString(ReportNum)+"\" style=\"font-size:16px; color:black; margin:0px;\">"+kor_date+" "+ranking+"순위는 "+coinKorName+"로, 시가 대비 종가가 "+o_c_price_rate+"%, 저가 대비 고가가 "+l_h_price_rate+"%이며, 가장 좋은 타이밍은 "+lower_price_time.replace(":","시 ")+"분에 "+l_price+"원으로 매수하여 "+higher_price_time.replace(":","시 ")+"분에 "+h_price+"원으로 매도하면 대략 "+l_h_price_rate+"%의 수익을 얻을 수 있었습니다.</p>";
			htmlMap = new HashMap<String, String>();
			htmlMap.put("id", "report_"+Integer.toString(ReportNum));
			htmlMap.put("row", Integer.toString(row));
			htmlMap.put("col", "0");
			htmlMap.put("html", html);
			htmlMap.put("diff_yn", "Y");
			htmlMap.put("blog_yn", "Y");
			list.add(htmlMap);
			
			if(x == 2) {
				row = row+1;
				ReportNum = ReportNum+1;
				html = (map.get("BithumbReportAD1"));
				htmlMap = new HashMap<String, String>();
				htmlMap.put("id", "ad1");
				htmlMap.put("row", Integer.toString(row));
				htmlMap.put("col", "0");
				htmlMap.put("html", html);
				htmlMap.put("diff_yn", "Y");
				htmlMap.put("blog_yn", "Y");
				list.add(htmlMap);
			}
		}
		
		row = row+1;
		ReportNum = ReportNum+1;
		html = "<br/><h2 id=\"report_"+Integer.toString(ReportNum)+"\" style=\"color: black; text-align: start; font-size:23px;\"><b>"+short_kor_date+" 빗썸 코인 요약</b></h2><br/>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "N");
		list.add(htmlMap);
		
		html = "<br/><h2 id=\"report_"+Integer.toString(ReportNum)+"\" style=\"color: black; text-align: start; font-size:23px; margin-top:0px;\"><b>"+short_kor_date+" 빗썸 코인 요약</b></h2>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "N");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		row = row+1;
		ReportNum = ReportNum+1;
		html = "<p id=\"report_"+Integer.toString(ReportNum)+"\" style=\"color:black; font-size:16px;\">"+kor_date+" 빗썸 원화 코인 "+total_count+"종 중 <span style=\"color: #ee2323;\"><b>상승인 코인이 "+increase_count+"종</b></span>, <span style=\"color: #006dd7;\"><b>하락인 코인이 "+degradation_count+"종</b></span>, <b>보합인 코인이 "+flat_count+"종</b>입니다.</p>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		row = row+1;
		ReportNum = ReportNum+1;
		html = "<br/><h2 id=\"report_"+Integer.toString(ReportNum)+"\" style=\"color: black; text-align: start; font-size:23px;\"><b>"+short_kor_date+" 빗썸 코인 상승률 순위</b></h2><br/>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "N");
		list.add(htmlMap);
		
		html = "<br/><h2 id=\"report_"+Integer.toString(ReportNum)+"\" style=\"color: black; text-align: start; font-size:23px; margin-top:0px;\"><b>"+short_kor_date+" 빗썸 코인 상승률 순위</b></h2>";
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report_"+Integer.toString(ReportNum));
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "N");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		String ranking_btc_coin_title = coinDailyReportList.get(0).get("coin_title");
		String ranking_btc_ranking = coinDailyReportList.get(0).get("ranking");
		String ranking_btc_o_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_price")));
		String ranking_btc_l_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_price")));
		String ranking_btc_h_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("h_price")));
		String ranking_btc_c_price = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("c_price")));
		String ranking_btc_o_c_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("o_c_price_rate")));
		String ranking_btc_l_h_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(0).get("l_h_price_rate")));
		String ranking_btc_html = "";
		ranking_btc_html += "<tr>";
		ranking_btc_html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;\">"+ranking_btc_ranking+"</td>";
		ranking_btc_html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-left:3px; text-align:left;\">"+ranking_btc_coin_title+"</td>";
		if(ranking_btc_o_c_price_rate.contains("-")) {
			ranking_btc_html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; color:blue;\">"+ranking_btc_o_c_price_rate+"%</td>";
		} else {
			ranking_btc_html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; color:red;\">"+ranking_btc_o_c_price_rate+"%</td>";
		}
		if(ranking_btc_l_h_price_rate.contains("-")) {
			ranking_btc_html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; color:blue;\">"+ranking_btc_l_h_price_rate+"%</td>";
		} else {
			ranking_btc_html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; color:red;\">"+ranking_btc_l_h_price_rate+"%</td>";
		}
		ranking_btc_html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+ranking_btc_o_price+"</td>";
		ranking_btc_html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+ranking_btc_l_price+"</td>";
		ranking_btc_html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+ranking_btc_h_price+"</td>";
		ranking_btc_html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+ranking_btc_c_price+"</td>";
		ranking_btc_html += "</tr>";
		
		int SubRankingMok = (Integer.parseInt(SubRankingCount)/Integer.parseInt("20"));
		for(int x=1; x<=SubRankingMok; x++) {
			html = "";
			html += "<table id=\"rank_tbl_"+x+"\" style=\"color:black; border-spacing:0px; border-color:black; font-size:13px;\">";
			html += "<colgroup>";
			html += "<col style=\"width: 40px\">";
			html += "<col style=\"width: 220px\">";
			html += "<col style=\"width: 105px\">";
			html += "<col style=\"width: 105px\">";
			html += "<col style=\"width: 90px\">";
			html += "<col style=\"width: 90px\">";
			html += "<col style=\"width: 90px\">";
			html += "<col style=\"width: 90px\">";
			html += "</colgroup>";
			html += "<tr>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; text-align:center; font-size:15px; font-weight:bold;\">순위</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; padding-left:3px; text-align:center; font-size:15px; font-weight:bold;\">코인</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:15px; font-weight:bold;\">시가대비종가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:15px; font-weight:bold;\">저가대비고가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:15px; font-weight:bold;\">시가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:15px; font-weight:bold;\">저가</td>";
			html += "<td style=\"border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:15px; font-weight:bold;\">고가</td>";
			html += "<td style=\"border: 1px solid black; padding-right:3px; text-align:center; font-size:15px; font-weight:bold;\">종가</td>";
			html += "</tr>";
			html += ranking_btc_html;
			for(int y=0; y<20; y++) {
				int TMPy = ((x-1)*20)+y+1;
				if(TMPy > (Integer.parseInt(SubRankingCount)+1)) {
					break;
				}else {
					String ranking_coin_title = coinDailyReportList.get(TMPy).get("coin_title");
					String ranking_ranking = coinDailyReportList.get(TMPy).get("ranking");
					String ranking_o_price = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("o_price")));
					String ranking_l_price = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("l_price")));
					String ranking_h_price = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("h_price")));
					String ranking_c_price = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("c_price")));
					String ranking_o_c_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("o_c_price_rate")));
					String ranking_l_h_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("l_h_price_rate")));
					
					html += "<tr>";
					html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-weight:bold; padding-right:3px; \">"+ranking_ranking+"</td>";
					html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-left:3px; text-align:left;\">"+ranking_coin_title+"</td>";
					if(ranking_o_c_price_rate.contains("-")) {
						html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; color:blue;\">"+ranking_o_c_price_rate+"%</td>";
					} else {
						html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; color:red;\">"+ranking_o_c_price_rate+"%</td>";
					}
					if(ranking_l_h_price_rate.contains("-")) {
						html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; color:blue;\">"+ranking_l_h_price_rate+"%</td>";
					} else {
						html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; color:red;\">"+ranking_l_h_price_rate+"%</td>";
					}
					html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+ranking_o_price+"</td>";
					html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+ranking_l_price+"</td>";
					html += "<td style=\"border: 1px solid black; border-right:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+ranking_h_price+"</td>";
					html += "<td style=\"border: 1px solid black; border-top:0px solid black; padding-right:3px; text-align:right;\">"+ranking_c_price+"</td>";
					html += "</tr>";
				}
			}
			html += "</table><br/>";
			
			row = row+1;
			htmlMap = new HashMap<String, String>();
			htmlMap.put("id", "rank_tbl_"+Integer.toString(x));
			htmlMap.put("row", Integer.toString(row));
			htmlMap.put("col", "0");
			htmlMap.put("html", html);
			htmlMap.put("diff_yn", "Y");
			htmlMap.put("blog_yn", "N");
			list.add(htmlMap);
			
			htmlMap = new HashMap<String, String>();
			htmlMap.put("id", "image");
			htmlMap.put("row", Integer.toString(row));
			htmlMap.put("col", "0");
			htmlMap.put("html", "bithumb_rank_"+Integer.toString(x)+".png");
			htmlMap.put("diff_yn", "N");
			htmlMap.put("blog_yn", "Y");
			list.add(htmlMap);
			
			for(int y=0; y<20; y++) {
				int TMPy = ((x-1)*20)+y+1;
				if(TMPy > (Integer.parseInt(SubRankingCount)+1)) {
					break;
				}else {
					String ranking_coin_title = coinDailyReportList.get(TMPy).get("coin_title");
					String ranking_ranking = coinDailyReportList.get(TMPy).get("ranking");
					String ranking_o_price = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("o_price")));
					String ranking_l_price = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("l_price")));
					String ranking_h_price = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("h_price")));
					String ranking_c_price = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("c_price")));
					String ranking_o_c_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("o_c_price_rate")));
					String ranking_l_h_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("l_h_price_rate")));
					String ranking_yesterday_ranking = coinDailyReportList.get(TMPy).get("yesterday_ranking");
					String ranking_yesterday_o_c_price_rate = formatter.format(Double.parseDouble(coinDailyReportList.get(TMPy).get("yesterday_o_c_price_rate")));
					
					row = row+1;
					ReportNum = ReportNum+1;
					if(ranking_yesterday_ranking.equals("")) {
						html = "<p id=\"report_"+Integer.toString(ReportNum)+"\" style=\"color:black; font-size:15px; margin-top:5px; margin-bottom:5px;\">"+ranking_ranking+"위는 "+ranking_coin_title+"로 "+ranking_o_c_price_rate+"%입니다.</p>";
					} else {
						html = "<p id=\"report_"+Integer.toString(ReportNum)+"\" style=\"color:black; font-size:15px; margin-top:5px; margin-bottom:5px;\">"+ranking_ranking+"위는 "+ranking_coin_title+"로 "+ranking_o_c_price_rate+"%이며, 어제["+yesterday_short_kor_date+"]는 "+ranking_yesterday_o_c_price_rate+"%로 "+ranking_yesterday_ranking+"등을 했습니다.</p>";
					}
					if(y == 19) {
						html += "<br/>";
					}
					
					htmlMap = new HashMap<String, String>();
					htmlMap.put("id", "report_"+Integer.toString(ReportNum));
					htmlMap.put("row", Integer.toString(row));
					htmlMap.put("col", "0");
					htmlMap.put("html", html);
					htmlMap.put("diff_yn", "Y");
					htmlMap.put("blog_yn", "Y");
					list.add(htmlMap);
				}
			}
		}
		
		html = "</div>";
		row = row+1;
		htmlMap = new HashMap<String, String>();
		htmlMap.put("id", "report");
		htmlMap.put("row", Integer.toString(row));
		htmlMap.put("col", "0");
		htmlMap.put("html", html);
		htmlMap.put("diff_yn", "Y");
		htmlMap.put("blog_yn", "Y");
		list.add(htmlMap);
		
		for(int x=0; x<list.size(); x++) {
			String script_id = list.get(x).get("id");
			String script_row = list.get(x).get("row");
			String script_col = list.get(x).get("col");
			String script_html = list.get(x).get("html");
			String script_diff_yn = list.get(x).get("diff_yn");
			String script_blog_yn = list.get(x).get("blog_yn");
			
			if(!script_id.equals("") && !script_row.equals("") && !script_col.equals("") && !script_html.equals("")) {
				htmlMap = new HashMap<String, String>();
				htmlMap.put("yyyymmdd", date);
				htmlMap.put("id", script_id);
				htmlMap.put("row", script_row);
				htmlMap.put("col", script_col);
				htmlMap.put("html", script_html);
				htmlMap.put("diff_yn", script_diff_yn);
				htmlMap.put("blog_yn", script_blog_yn);
				BitCoinMapper.CoinDailyReportHTMLReg(htmlMap);
			}
		}
	}
	
	public List<HashMap<String, String>> CoinReportList() throws Exception{
		return BitCoinMapper.CoinReportList();
	}
	
	public List<HashMap<String, String>> CoinDailyReportList(HashMap<String, String> map) throws Exception{
		return BitCoinMapper.CoinDailyReportList(map);
	}
	
	public List<HashMap<String, String>> CoinDailyReportHTMLList(HashMap<String, String> map) throws Exception{
		return BitCoinMapper.CoinDailyReportHTMLList(map);
	}
	
	public List<HashMap<String, String>> MainLiveRankList(HashMap<String, String> map) throws Exception{
		return BitCoinMapper.MainLiveRankList(map);
	}
	
	public List<HashMap<String, String>> ExcelMake_Daily_Result(HashMap<String, String> map) throws Exception{
		return BitCoinMapper.ExcelMake_Daily_Result(map);
	}
	
	public List<HashMap<String, String>> ExcelMake_Daily_Ranking(HashMap<String, String> map) throws Exception{
		return BitCoinMapper.ExcelMake_Daily_Ranking(map);
	}
	
	public HashMap<String, String> ExcelMake_Daily(HashMap<String, String> map) throws Exception{
		DecimalFormat df = new DecimalFormat("#,###.########");
		HashMap<String, String> excel_map = new HashMap<String, String>();
		List<HashMap<String, String>> list = BitCoinMapper.ExcelMake_Daily(map);
		
		for(int x=0; x<list.size(); x++) {
			String coin_ticker = list.get(x).get("coin_ticker");
			String open_close = df.format(Double.parseDouble(list.get(x).get("open_close_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%]";
			String lowest_highest = df.format(Double.parseDouble(list.get(x).get("lowest_highest_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation"))) + "%]";
			
			if(coin_ticker.equals("BTC")) {
				excel_map.put("btc_ticker", "비트코인 [BTC]");
				excel_map.put("btc_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("btc_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("btc_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("btc_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("btc_open_close", open_close);
				excel_map.put("btc_lowest_highest", lowest_highest);
				excel_map.put("btc_price_min_time", list.get(x).get("coin_price_min_time"));
				excel_map.put("btc_price_max_time", list.get(x).get("coin_price_max_time"));
				excel_map.put("btc_volume_min_time", list.get(x).get("coin_volume_min_time") + "시");
				excel_map.put("btc_volume_max_time", list.get(x).get("coin_volume_max_time") + "시");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("date", map.get("date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Daily_Sub(sub_map);
				excel_map.put("btc_oclh_1_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_0_4"))));
				excel_map.put("btc_oclh_1_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_0_4"))));
				excel_map.put("btc_oclh_1_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_0_4"))));
				excel_map.put("btc_oclh_1_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_0_4"))));
				excel_map.put("btc_oclh_1_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_0_4"))));
				
				excel_map.put("btc_oclh_2_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_4_8"))));
				excel_map.put("btc_oclh_2_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_4_8"))));
				excel_map.put("btc_oclh_2_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_4_8"))));
				excel_map.put("btc_oclh_2_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_4_8"))));
				excel_map.put("btc_oclh_2_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_4_8"))));
				
				excel_map.put("btc_oclh_3_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_8_12"))));
				excel_map.put("btc_oclh_3_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_8_12"))));
				excel_map.put("btc_oclh_3_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_8_12"))));
				excel_map.put("btc_oclh_3_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_8_12"))));
				excel_map.put("btc_oclh_3_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_8_12"))));
				
				excel_map.put("btc_oclh_4_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_12_16"))));
				excel_map.put("btc_oclh_4_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_12_16"))));
				excel_map.put("btc_oclh_4_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_12_16"))));
				excel_map.put("btc_oclh_4_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_12_16"))));
				excel_map.put("btc_oclh_4_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_12_16"))));
				
				excel_map.put("btc_oclh_5_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_16_20"))));
				excel_map.put("btc_oclh_5_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_16_20"))));
				excel_map.put("btc_oclh_5_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_16_20"))));
				excel_map.put("btc_oclh_5_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_16_20"))));
				excel_map.put("btc_oclh_5_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_16_20"))));
				
				excel_map.put("btc_oclh_6_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_20_24"))));
				excel_map.put("btc_oclh_6_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_20_24"))));
				excel_map.put("btc_oclh_6_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_20_24"))));
				excel_map.put("btc_oclh_6_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_20_24"))));
				excel_map.put("btc_oclh_6_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_20_24"))));
				if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 상승하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) < 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 하락하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", "보합이었으며, ");
				}
			} else if(x == 1) {
				excel_map.put("coin1_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin1_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin1_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin1_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin1_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin1_open_close", open_close);
				excel_map.put("coin1_lowest_highest", lowest_highest);
				excel_map.put("coin1_price_min_time", list.get(x).get("coin_price_min_time"));
				excel_map.put("coin1_price_max_time", list.get(x).get("coin_price_max_time"));
				excel_map.put("coin1_volume_min_time", list.get(x).get("coin_volume_min_time") + "시");
				excel_map.put("coin1_volume_max_time", list.get(x).get("coin_volume_max_time") + "시");
				excel_map.put("coin1_summary","1순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("coin_price_min_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("coin_price_max_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("date", map.get("date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Daily_Sub(sub_map);
				excel_map.put("coin1_oclh_1_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_0_4"))));
				excel_map.put("coin1_oclh_1_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_0_4"))));
				excel_map.put("coin1_oclh_1_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_0_4"))));
				excel_map.put("coin1_oclh_1_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_0_4"))));
				excel_map.put("coin1_oclh_1_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_0_4"))));
				
				excel_map.put("coin1_oclh_2_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_4_8"))));
				excel_map.put("coin1_oclh_2_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_4_8"))));
				excel_map.put("coin1_oclh_2_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_4_8"))));
				excel_map.put("coin1_oclh_2_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_4_8"))));
				excel_map.put("coin1_oclh_2_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_4_8"))));
				
				excel_map.put("coin1_oclh_3_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_8_12"))));
				excel_map.put("coin1_oclh_3_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_8_12"))));
				excel_map.put("coin1_oclh_3_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_8_12"))));
				excel_map.put("coin1_oclh_3_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_8_12"))));
				excel_map.put("coin1_oclh_3_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_8_12"))));
				
				excel_map.put("coin1_oclh_4_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_12_16"))));
				excel_map.put("coin1_oclh_4_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_12_16"))));
				excel_map.put("coin1_oclh_4_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_12_16"))));
				excel_map.put("coin1_oclh_4_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_12_16"))));
				excel_map.put("coin1_oclh_4_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_12_16"))));
				
				excel_map.put("coin1_oclh_5_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_16_20"))));
				excel_map.put("coin1_oclh_5_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_16_20"))));
				excel_map.put("coin1_oclh_5_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_16_20"))));
				excel_map.put("coin1_oclh_5_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_16_20"))));
				excel_map.put("coin1_oclh_5_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_16_20"))));
				
				excel_map.put("coin1_oclh_6_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_20_24"))));
				excel_map.put("coin1_oclh_6_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_20_24"))));
				excel_map.put("coin1_oclh_6_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_20_24"))));
				excel_map.put("coin1_oclh_6_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_20_24"))));
				excel_map.put("coin1_oclh_6_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_20_24"))));
			} else if(x == 2) {
				excel_map.put("coin2_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin2_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin2_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin2_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin2_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin2_open_close", open_close);
				excel_map.put("coin2_lowest_highest", lowest_highest);
				excel_map.put("coin2_price_min_time", list.get(x).get("coin_price_min_time"));
				excel_map.put("coin2_price_max_time", list.get(x).get("coin_price_max_time"));
				excel_map.put("coin2_volume_min_time", list.get(x).get("coin_volume_min_time") + "시");
				excel_map.put("coin2_volume_max_time", list.get(x).get("coin_volume_max_time") + "시");
				excel_map.put("coin2_summary","2순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("coin_price_min_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("coin_price_max_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("date", map.get("date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Daily_Sub(sub_map);
				excel_map.put("coin2_oclh_1_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_0_4"))));
				excel_map.put("coin2_oclh_1_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_0_4"))));
				excel_map.put("coin2_oclh_1_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_0_4"))));
				excel_map.put("coin2_oclh_1_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_0_4"))));
				excel_map.put("coin2_oclh_1_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_0_4"))));
				
				excel_map.put("coin2_oclh_2_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_4_8"))));
				excel_map.put("coin2_oclh_2_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_4_8"))));
				excel_map.put("coin2_oclh_2_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_4_8"))));
				excel_map.put("coin2_oclh_2_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_4_8"))));
				excel_map.put("coin2_oclh_2_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_4_8"))));
				
				excel_map.put("coin2_oclh_3_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_8_12"))));
				excel_map.put("coin2_oclh_3_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_8_12"))));
				excel_map.put("coin2_oclh_3_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_8_12"))));
				excel_map.put("coin2_oclh_3_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_8_12"))));
				excel_map.put("coin2_oclh_3_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_8_12"))));
				
				excel_map.put("coin2_oclh_4_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_12_16"))));
				excel_map.put("coin2_oclh_4_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_12_16"))));
				excel_map.put("coin2_oclh_4_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_12_16"))));
				excel_map.put("coin2_oclh_4_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_12_16"))));
				excel_map.put("coin2_oclh_4_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_12_16"))));
				
				excel_map.put("coin2_oclh_5_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_16_20"))));
				excel_map.put("coin2_oclh_5_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_16_20"))));
				excel_map.put("coin2_oclh_5_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_16_20"))));
				excel_map.put("coin2_oclh_5_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_16_20"))));
				excel_map.put("coin2_oclh_5_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_16_20"))));
				
				excel_map.put("coin2_oclh_6_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_20_24"))));
				excel_map.put("coin2_oclh_6_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_20_24"))));
				excel_map.put("coin2_oclh_6_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_20_24"))));
				excel_map.put("coin2_oclh_6_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_20_24"))));
				excel_map.put("coin2_oclh_6_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_20_24"))));
			} else if(x == 3) {
				excel_map.put("coin3_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin3_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin3_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin3_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin3_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin3_open_close", open_close);
				excel_map.put("coin3_lowest_highest", lowest_highest);
				excel_map.put("coin3_price_min_time", list.get(x).get("coin_price_min_time"));
				excel_map.put("coin3_price_max_time", list.get(x).get("coin_price_max_time"));
				excel_map.put("coin3_volume_min_time", list.get(x).get("coin_volume_min_time") + "시");
				excel_map.put("coin3_volume_max_time", list.get(x).get("coin_volume_max_time") + "시");
				excel_map.put("coin3_summary","3순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("coin_price_min_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("coin_price_max_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("date", map.get("date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Daily_Sub(sub_map);
				excel_map.put("coin3_oclh_1_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_0_4"))));
				excel_map.put("coin3_oclh_1_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_0_4"))));
				excel_map.put("coin3_oclh_1_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_0_4"))));
				excel_map.put("coin3_oclh_1_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_0_4"))));
				excel_map.put("coin3_oclh_1_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_0_4"))));
				
				excel_map.put("coin3_oclh_2_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_4_8"))));
				excel_map.put("coin3_oclh_2_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_4_8"))));
				excel_map.put("coin3_oclh_2_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_4_8"))));
				excel_map.put("coin3_oclh_2_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_4_8"))));
				excel_map.put("coin3_oclh_2_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_4_8"))));
				
				excel_map.put("coin3_oclh_3_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_8_12"))));
				excel_map.put("coin3_oclh_3_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_8_12"))));
				excel_map.put("coin3_oclh_3_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_8_12"))));
				excel_map.put("coin3_oclh_3_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_8_12"))));
				excel_map.put("coin3_oclh_3_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_8_12"))));
				
				excel_map.put("coin3_oclh_4_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_12_16"))));
				excel_map.put("coin3_oclh_4_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_12_16"))));
				excel_map.put("coin3_oclh_4_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_12_16"))));
				excel_map.put("coin3_oclh_4_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_12_16"))));
				excel_map.put("coin3_oclh_4_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_12_16"))));
				
				excel_map.put("coin3_oclh_5_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_16_20"))));
				excel_map.put("coin3_oclh_5_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_16_20"))));
				excel_map.put("coin3_oclh_5_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_16_20"))));
				excel_map.put("coin3_oclh_5_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_16_20"))));
				excel_map.put("coin3_oclh_5_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_16_20"))));
				
				excel_map.put("coin3_oclh_6_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_20_24"))));
				excel_map.put("coin3_oclh_6_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_20_24"))));
				excel_map.put("coin3_oclh_6_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_20_24"))));
				excel_map.put("coin3_oclh_6_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_20_24"))));
				excel_map.put("coin3_oclh_6_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_20_24"))));
			}
		}
		return excel_map;
	}
	
	public List<HashMap<String, String>> ExcelMake_Weekly_Result(HashMap<String, String> map) throws Exception{
		return BitCoinMapper.ExcelMake_Weekly_Result(map);
	}
	
	public List<HashMap<String, String>> ExcelMake_Weekly_Ranking(HashMap<String, String> map) throws Exception{
		return BitCoinMapper.ExcelMake_Weekly_Ranking(map);
	}
	
	public HashMap<String, String> ExcelMake_Weekly(HashMap<String, String> map) throws Exception{
		DecimalFormat df = new DecimalFormat("#,###.########");
		HashMap<String, String> excel_map = new HashMap<String, String>();
		List<HashMap<String, String>> list = BitCoinMapper.ExcelMake_Weekly(map);
		for(int x=0; x<list.size(); x++) {
			String coin_ticker = list.get(x).get("coin_ticker");
			
			String open_close = df.format(Double.parseDouble(list.get(x).get("open_close_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%]";
			String lowest_highest = df.format(Double.parseDouble(list.get(x).get("lowest_highest_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation"))) + "%]";
			
			if(coin_ticker.equals("BTC")) {
				excel_map.put("btc_ticker", "비트코인 [BTC]");
				excel_map.put("btc_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("btc_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("btc_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("btc_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("btc_open_close", open_close);
				excel_map.put("btc_lowest_highest", lowest_highest);
				excel_map.put("btc_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("btc_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("btc_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("btc_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("btc_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("btc_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Weekly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
				
				if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 상승하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) < 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 하락하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", "보합이었으며, ");
				}
			}else if(x == 1) {
				excel_map.put("coin1_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin1_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin1_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin1_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin1_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin1_open_close", open_close);
				excel_map.put("coin1_lowest_highest", lowest_highest);
				excel_map.put("coin1_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin1_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin1_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin1_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin1_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin1_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin1_summary", "1순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Weekly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}else if(x == 2) {
				excel_map.put("coin2_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin2_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin2_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin2_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin2_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin2_open_close", open_close);
				excel_map.put("coin2_lowest_highest", lowest_highest);
				excel_map.put("coin2_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin2_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin2_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin2_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin2_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin2_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin2_summary", "2순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");

				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Weekly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}else if(x == 3) {
				excel_map.put("coin3_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin3_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin3_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin3_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin3_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin3_open_close", open_close);
				excel_map.put("coin3_lowest_highest", lowest_highest);
				excel_map.put("coin3_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin3_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin3_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin3_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin3_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin3_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin3_summary", "3순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");

				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Weekly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}
		}
		return excel_map;
	}
	
	public HashMap<String, String> ExcelMake_Monthly(HashMap<String, String> map) throws Exception{
		DecimalFormat df = new DecimalFormat("#,###.########");
		HashMap<String, String> excel_map = new HashMap<String, String>();
		List<HashMap<String, String>> list = BitCoinMapper.ExcelMake_Weekly(map);
		for(int x=0; x<list.size(); x++) {
			String coin_ticker = list.get(x).get("coin_ticker");
			
			String open_close = df.format(Double.parseDouble(list.get(x).get("open_close_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%]";
			String lowest_highest = df.format(Double.parseDouble(list.get(x).get("lowest_highest_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation"))) + "%]";
			
			if(coin_ticker.equals("BTC")) {
				excel_map.put("btc_ticker", "비트코인 [BTC]");
				excel_map.put("btc_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("btc_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("btc_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("btc_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("btc_open_close", open_close);
				excel_map.put("btc_lowest_highest", lowest_highest);
				excel_map.put("btc_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("btc_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("btc_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("btc_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("btc_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("btc_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Monthly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
				
				if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 상승하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) < 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 하락하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", "보합이었으며, ");
				}
			}else if(x == 1) {
				excel_map.put("coin1_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin1_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin1_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin1_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin1_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin1_open_close", open_close);
				excel_map.put("coin1_lowest_highest", lowest_highest);
				excel_map.put("coin1_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin1_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin1_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin1_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin1_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin1_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin1_summary", "1순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Monthly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}else if(x == 2) {
				excel_map.put("coin2_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin2_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin2_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin2_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin2_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin2_open_close", open_close);
				excel_map.put("coin2_lowest_highest", lowest_highest);
				excel_map.put("coin2_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin2_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin2_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin2_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin2_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin2_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin2_summary", "2순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");

				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Monthly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}else if(x == 3) {
				excel_map.put("coin3_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin3_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin3_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin3_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin3_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin3_open_close", open_close);
				excel_map.put("coin3_lowest_highest", lowest_highest);
				excel_map.put("coin3_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin3_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin3_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin3_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin3_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin3_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin3_summary", "3순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");

				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = BitCoinMapper.ExcelMake_Monthly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}
		}
		return excel_map;
	}
}
