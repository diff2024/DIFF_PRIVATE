package com.diff._private.Controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.catalina.valves.rewrite.InternalRewriteMap.UpperCase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diff._private.Service.CoinService;

@RestController
@RequestMapping("/Coin")
public class CoinController {

	@Autowired
	CoinService CoinService;
	
	@GetMapping(path = "/MainGridList")
	public List<HashMap<String, String>> MainGridList(HttpServletRequest req) throws Exception {
		String std_date = (req.getParameter("std_date")==null)?"":req.getParameter("std_date");
		String end_date = (req.getParameter("end_date")==null)?"":req.getParameter("end_date");
		if(std_date.equals("--")) {
			std_date = "";
		}
		if(end_date.equals("--")) {
			end_date = "";
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("std_date", std_date);
		map.put("end_date", end_date);
		return CoinService.MainGridList(map);
	}
	@GetMapping(path = "/MainGridGraph")
	public List<HashMap<String, String>> MainGridGraph(HttpServletRequest req) throws IOException {
		ArrayList<HashMap<String, String>> coinlist = new ArrayList<HashMap<String, String>>();
		
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = null;
			List<HashMap<String, String>> coininfo = CoinService.CoinInfo();
			
			for(int i=0; i<coininfo.size(); i++) {
				if(i > 0) {
					Thread.sleep(100);
				}
				String ticker = coininfo.get(i).get("coin_ticker");
				String kor_name = coininfo.get(i).get("coin_kor_name");
				String eng_name = coininfo.get(i).get("coin_eng_name");
				
		        httpGet = new HttpGet("https://api.upbit.com/v1/ticker?markets="+ticker);
		        org.apache.http.Header header = new org.apache.http.message.BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        CloseableHttpResponse response = httpclient.execute(httpGet);
	
		        try {
		            InputStream ips = response.getEntity().getContent();
		            InputStreamReader sr = new InputStreamReader(ips);
		            BufferedReader br = new BufferedReader(sr);
	
		            while (true) {
		            	String return_list = br.readLine();
		            	return_list = return_list.replace("[", "").replace("]","");
	                	JSONObject jsonObj = new JSONObject(return_list);
	                    String market = (jsonObj.get("market")).toString();
	                    String trade_price = (jsonObj.get("trade_price")).toString();
	                    String open_price = (jsonObj.get("opening_price")).toString();
	                    String change = (jsonObj.get("change")).toString();
	                    String kst_date = (jsonObj.get("trade_date_kst")).toString();
	                    String kst_time = (jsonObj.get("trade_time_kst")).toString();
	                    String timestamp = (jsonObj.get("timestamp")).toString();
	                    String change_percent = "";
	                    if(change.equals("EVEN")) {
	                    	change = "보합";
	                    } else if(change.equals("FALL")) {
	                    	change = "하락";
	                    } else if(change.equals("RISE")) {
	                    	change = "상승";
	                    }
	                    
	                    BigDecimal BIGDECIMAL_TRADE_PRICE = new BigDecimal(trade_price);
	                    BigDecimal BIGDECIMAL_OPEN_PRICE = new BigDecimal(open_price);
	                    BigDecimal BIGDECIMAL_CHANGE = ((BIGDECIMAL_TRADE_PRICE.subtract(BIGDECIMAL_OPEN_PRICE)).divide(BIGDECIMAL_TRADE_PRICE, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
	                    change_percent = BIGDECIMAL_CHANGE.toString();
	                    
	                    HashMap<String, String> map = new HashMap<String, String>();
	                    map.put("id", ticker);
	                    map.put("kor_name", kor_name);
	                    map.put("eng_name", eng_name);
	                    map.put("trade_price", trade_price);
	                    map.put("change", change);
	                    map.put("change_percent", change_percent);
	                    map.put("datetime", kst_date + kst_time);
	                    map.put("timestamp", timestamp);
	                    coinlist.add(map);

		                if (br.readLine() == null)
		                    break;
		            }
		        } finally {
		        	response.close();
		        }
			}
		} catch (Exception e){
			System.err.println(e.toString());
		}
		return coinlist;
	}
	
	@GetMapping(path = "/MainDetailGraph")
	public List<HashMap<String, String>> MainDetailGraph(HttpServletRequest req) throws IOException {
		ArrayList<HashMap<String, String>> coinDetaillist = new ArrayList<HashMap<String, String>>();
		String ticker = req.getParameter("ticker") == null? "":req.getParameter("ticker");
		if(!ticker.equals("") && !ticker.equals("undefined")) {
			System.out.println("> ticker : " + ticker);
			try {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpGet = null;
				
				httpGet = new HttpGet("https://api.upbit.com/v1/candles/minutes/1?market="+ticker+"&count=150");
		        org.apache.http.Header header = new org.apache.http.message.BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        CloseableHttpResponse response = httpclient.execute(httpGet);
	
		        try {
		            InputStream ips = response.getEntity().getContent();
		            InputStreamReader sr = new InputStreamReader(ips);
		            BufferedReader br = new BufferedReader(sr);
	
		            while (true) {
		            	String return_list = br.readLine();
		                JSONArray jsonArr = new JSONArray(return_list);
		                for (int j = 0; j < jsonArr.length(); j++) {
		                    JSONObject jsonObj = jsonArr.getJSONObject(j);
		                    String datetime = ((jsonObj.get("candle_date_time_kst")).toString()).replace("T", " ");
		                    String timestamp = ((jsonObj.get("timestamp")).toString());
		                    String open_price = (jsonObj.get("opening_price")).toString();
		                    String high_price = (jsonObj.get("high_price")).toString();
		                    String low_price = (jsonObj.get("low_price")).toString();
		                    String trade_price = (jsonObj.get("trade_price")).toString();
		                    String trade_volume = (jsonObj.get("candle_acc_trade_volume")).toString();
		                    String change_percent = "";
		                    
		                    BigDecimal BIGDECIMAL_TRADE_PRICE = new BigDecimal(trade_price);
		                    BigDecimal BIGDECIMAL_OPEN_PRICE = new BigDecimal(open_price);
		                    BigDecimal BIGDECIMAL_CHANGE = ((BIGDECIMAL_TRADE_PRICE.subtract(BIGDECIMAL_OPEN_PRICE)).divide(BIGDECIMAL_TRADE_PRICE, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
		                    change_percent = BIGDECIMAL_CHANGE.toString();
		                    
		                    HashMap<String, String> map = new HashMap<String, String>();
		                    map.put("datetime", datetime);
		                    map.put("timestamp", timestamp);
		                    map.put("open_price", open_price);
		                    map.put("high_price", high_price);
		                    map.put("low_price", low_price);
		                    map.put("close_price", trade_price);
		                    map.put("trade_volume", trade_volume);
		                    map.put("change_percent", change_percent);
		                    coinDetaillist.add(map);
		                }
	
		                if (br.readLine() == null)
		                    break;
		            }
		        } finally {
		        	response.close();
		        }
			} catch (Exception e){
				System.err.println(e.toString());
			}
		}
		return coinDetaillist;
	}
}