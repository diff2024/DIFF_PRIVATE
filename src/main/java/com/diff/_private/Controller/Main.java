package com.diff._private.Controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import org.apache.catalina.valves.rewrite.InternalRewriteMap.UpperCase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diff._private.Service.CoinService;
import com.diff._private.Service.MainService;

@RestController
@RequestMapping("/Main")
public class Main {
	
	@Autowired
	CoinService CoinService;
	
	@Autowired
	MainService MainService;
	
	@GetMapping(path = "/CoinAnalysisSetting")
	public HashMap<String, String> CoinAnalysisSetting() throws Exception{
		return MainService.CoinAnalysisSetting();
	}
	
	@GetMapping(path = "/UPBIT_BTC_HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> UPBIT_BTC_HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.UPBIT_BTC_HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/UPBIT_BTC_4HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> UPBIT_BTC_4HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.UPBIT_BTC_4HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/UPBIT_DAY_RANKING")
	public List<HashMap<String, String>> UPBIT_DAY_RANKING(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.UPBIT_DAY_RANKING(map);
		return list;
	}
	
	@GetMapping(path = "/BITHUMB_BTC_HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> BITHUMB_BTC_HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BITHUMB_BTC_HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/BITHUMB_BTC_4HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> BITHUMB_BTC_4HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BITHUMB_BTC_4HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/BITHUMB_DAY_RANKING")
	public List<HashMap<String, String>> BITHUMB_DAY_RANKING(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BITHUMB_DAY_RANKING(map);
		return list;
	}
	
	@GetMapping(path = "/BINANCE_BTC_HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> BINANCE_BTC_HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BINANCE_BTC_HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/BINANCE_BTC_4HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> BINANCE_BTC_4HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BINANCE_BTC_4HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/BINANCE_DAY_RANKING")
	public List<HashMap<String, String>> BINANCE_DAY_RANKING(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BINANCE_DAY_RANKING(map);
		return list;
	}
	
	@GetMapping(path = "/BINANCE_FUTURES_BTC_HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> BINANCE_FUTURES_BTC_HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BINANCE_FUTURES_BTC_HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/BINANCE_FUTURES_BTC_4HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> BINANCE_FUTURES_BTC_4HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BINANCE_FUTURES_BTC_4HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/BINANCE_FUTURES_DAY_RANKING")
	public List<HashMap<String, String>> BINANCE_FUTURES_DAY_RANKING(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BINANCE_FUTURES_DAY_RANKING(map);
		return list;
	}
	
	@GetMapping(path = "/upbit_5m")
	public List<HashMap<String, String>> upbit_5m(HttpServletRequest req) throws Exception {
		System.out.println("/Main/upbit_5m");
		
		ArrayList<HashMap<String, String>> coinlist = new ArrayList<HashMap<String, String>>();
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = null;
			List<HashMap<String, String>> coininfo = CoinService.CoinInfo();
			
			for(int i=0; i<coininfo.size(); i++) {
				if(i > 0) {
					Thread.sleep(300);
				}
				String ticker = coininfo.get(i).get("coin_ticker");
				String kor_name = coininfo.get(i).get("coin_kor_name");
				String eng_name = coininfo.get(i).get("coin_eng_name");
			
		        httpGet = new HttpGet("https://api.upbit.com/v1/candles/minutes/5?market="+ticker+"&count=2");
		        org.apache.http.Header header = new org.apache.http.message.BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        CloseableHttpResponse response = httpclient.execute(httpGet);
	
		        try {
		            InputStream ips = response.getEntity().getContent();
		            InputStreamReader sr = new InputStreamReader(ips);
		            BufferedReader br = new BufferedReader(sr);
	
		            while (true) {
		            	String return_list = br.readLine();
		            	JSONArray JSONarray = new JSONArray(return_list);
		            	JSONObject JSONObject0 = JSONarray.getJSONObject(0);
		            	JSONObject JSONObject1 = JSONarray.getJSONObject(1);
		            	
		            	//System.out.println("> 현재 " + JSONObject0);
		            	//System.out.println("> 이전 " + JSONObject1);
		            	
	                    String market = (JSONObject0.get("market")).toString();
	                    String timestamp0 = (JSONObject0.get("timestamp")).toString();
	                    String datetime0 = (JSONObject0.get("candle_date_time_kst")).toString();
	                    String open_price0 = (JSONObject0.get("opening_price")).toString();
	                    String trade_price0 = (JSONObject0.get("trade_price")).toString();
	                    String trade_volume0 = JSONObject1.get("candle_acc_trade_volume").toString();
	                    
	                    String timestamp1 = (JSONObject1.get("timestamp")).toString();
	                    String datetime1 = (JSONObject1.get("candle_date_time_kst")).toString();
	                    String open_price1 = (JSONObject1.get("opening_price")).toString();
	                    String trade_price1 = (JSONObject1.get("trade_price")).toString();
	                    String trade_volume1 = JSONObject1.get("candle_acc_trade_volume").toString();
	                    
	                    BigDecimal BIGDECIMAL_TRADE_VOLUME0 = new BigDecimal(trade_volume0);
	                    BigDecimal BIGDECIMAL_TRADE_PRICE0 = new BigDecimal(trade_price0);
	                    BigDecimal BIGDECIMAL_OPEN_PRICE0 = new BigDecimal(open_price0);
	                    BigDecimal BIGDECIMAL_TRADE_VOLUME1 = new BigDecimal(trade_volume1);
	                    BigDecimal BIGDECIMAL_TRADE_PRICE1 = new BigDecimal(trade_price1);
	                    BigDecimal BIGDECIMAL_OPEN_PRICE1 = new BigDecimal(open_price1);
	                    BigDecimal BIGDECIMAL_CHANGE = ((BIGDECIMAL_TRADE_PRICE1.subtract(BIGDECIMAL_TRADE_PRICE0)).divide(BIGDECIMAL_TRADE_PRICE0, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
	                    BigDecimal BIGDECIMAL_VOLUME_CHANGE = ((BIGDECIMAL_TRADE_VOLUME1.subtract(BIGDECIMAL_TRADE_VOLUME0)).divide(BIGDECIMAL_TRADE_VOLUME0, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
	                    
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] "+ datetime0 + " " + open_price0 + " " + trade_price0 + " " + trade_volume_price0+ "  |  " + trade_volume0);
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] "+ datetime1 + " " + open_price1 + " " + trade_price1 + " " + trade_volume_price1+ "  |  " + trade_volume1);
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] TRADE_VOLUME_CHANGE ["+BIGDECIMAL_TRADE_VOLUME_CHANGE.toString()+"] TRADE_VOLUME_PRICE0 ["+BIGDECIMAL_TRADE_VOLUME_PRICE0.toString()+"] TRADE_VOLUME_PRICE1 ["+BIGDECIMAL_TRADE_VOLUME_PRICE1.toString()+"]");
	                    
	                    HashMap<String, String> map = new HashMap<String, String>();
	                    map.put("id", ticker.replace("KRW-", ""));
	                    map.put("kor_name", kor_name);
	                    map.put("eng_name", eng_name);
	                    map.put("change_percent", BIGDECIMAL_CHANGE.toString());
	                    map.put("volume_change_percent", BIGDECIMAL_VOLUME_CHANGE.toString());
	                    map.put("timestamp0", timestamp0);
	                    map.put("datetime0", datetime0);
	                    map.put("open_price0", open_price0);
	                    map.put("trade_price0", trade_price0);
	                    map.put("B_timestamp", timestamp1);
	                    map.put("B_datetime", datetime1);
	                    map.put("B_open_price", open_price1);
	                    map.put("B_trade_price", trade_price1);
	                    
	                    //MainService.UpbitCoinMIN(map);
	                    
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
		
		Collections.sort(coinlist, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> first, HashMap<String, String> second) {
            	Double first_value = Double.parseDouble(first.get("change_percent"));
            	Double second_value = Double.parseDouble(second.get("change_percent"));
                return second_value.compareTo(first_value);
            }
        });
		return coinlist;
	}
	
	@GetMapping(path = "/upbit_15m")
	public List<HashMap<String, String>> upbit_15m(HttpServletRequest req) throws Exception {
		System.out.println("/Main/upbit_15m");
		ArrayList<HashMap<String, String>> coinlist = new ArrayList<HashMap<String, String>>();
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = null;
			List<HashMap<String, String>> coininfo = CoinService.CoinInfo();
			
			for(int i=0; i<coininfo.size(); i++) {
				if(i > 0) {
					Thread.sleep(300);
				}
				String ticker = coininfo.get(i).get("coin_ticker");
				String kor_name = coininfo.get(i).get("coin_kor_name");
				String eng_name = coininfo.get(i).get("coin_eng_name");
			
		        httpGet = new HttpGet("https://api.upbit.com/v1/candles/minutes/15?market="+ticker+"&count=2");
		        org.apache.http.Header header = new org.apache.http.message.BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        CloseableHttpResponse response = httpclient.execute(httpGet);
	
		        try {
		            InputStream ips = response.getEntity().getContent();
		            InputStreamReader sr = new InputStreamReader(ips);
		            BufferedReader br = new BufferedReader(sr);
	
		            while (true) {
		            	String return_list = br.readLine();
		            	JSONArray JSONarray = new JSONArray(return_list);
		            	JSONObject JSONObject0 = JSONarray.getJSONObject(0);
		            	JSONObject JSONObject1 = JSONarray.getJSONObject(1);
		            	
		            	//System.out.println("> 현재 " + JSONObject0);
		            	//System.out.println("> 이전 " + JSONObject1);
		            	
	                    String market = (JSONObject0.get("market")).toString();
	                    String timestamp0 = (JSONObject0.get("timestamp")).toString();
	                    String datetime0 = (JSONObject0.get("candle_date_time_kst")).toString();
	                    String open_price0 = (JSONObject0.get("opening_price")).toString();
	                    String trade_price0 = (JSONObject0.get("trade_price")).toString();
	                    String trade_volume0 = JSONObject1.get("candle_acc_trade_volume").toString();
	                    
	                    String timestamp1 = (JSONObject1.get("timestamp")).toString();
	                    String datetime1 = (JSONObject1.get("candle_date_time_kst")).toString();
	                    String open_price1 = (JSONObject1.get("opening_price")).toString();
	                    String trade_price1 = (JSONObject1.get("trade_price")).toString();
	                    String trade_volume1 = JSONObject1.get("candle_acc_trade_volume").toString();
	                    
	                    BigDecimal BIGDECIMAL_TRADE_VOLUME0 = new BigDecimal(trade_volume0);
	                    BigDecimal BIGDECIMAL_TRADE_PRICE0 = new BigDecimal(trade_price0);
	                    BigDecimal BIGDECIMAL_OPEN_PRICE0 = new BigDecimal(open_price0);
	                    BigDecimal BIGDECIMAL_TRADE_VOLUME1 = new BigDecimal(trade_volume1);
	                    BigDecimal BIGDECIMAL_TRADE_PRICE1 = new BigDecimal(trade_price1);
	                    BigDecimal BIGDECIMAL_OPEN_PRICE1 = new BigDecimal(open_price1);
	                    BigDecimal BIGDECIMAL_CHANGE = ((BIGDECIMAL_TRADE_PRICE1.subtract(BIGDECIMAL_TRADE_PRICE0)).divide(BIGDECIMAL_TRADE_PRICE0, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
	                    BigDecimal BIGDECIMAL_VOLUME_CHANGE = ((BIGDECIMAL_TRADE_VOLUME1.subtract(BIGDECIMAL_TRADE_VOLUME0)).divide(BIGDECIMAL_TRADE_VOLUME0, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
	                    
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] "+ datetime0 + " " + open_price0 + " " + trade_price0 + " " + trade_volume_price0+ "  |  " + trade_volume0);
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] "+ datetime1 + " " + open_price1 + " " + trade_price1 + " " + trade_volume_price1+ "  |  " + trade_volume1);
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] TRADE_VOLUME_CHANGE ["+BIGDECIMAL_TRADE_VOLUME_CHANGE.toString()+"] TRADE_VOLUME_PRICE0 ["+BIGDECIMAL_TRADE_VOLUME_PRICE0.toString()+"] TRADE_VOLUME_PRICE1 ["+BIGDECIMAL_TRADE_VOLUME_PRICE1.toString()+"]");
	                    
	                    HashMap<String, String> map = new HashMap<String, String>();
	                    map.put("id", ticker.replace("KRW-", ""));
	                    map.put("kor_name", kor_name);
	                    map.put("eng_name", eng_name);
	                    map.put("change_percent", BIGDECIMAL_CHANGE.toString());
	                    map.put("volume_change_percent", BIGDECIMAL_VOLUME_CHANGE.toString());
	                    map.put("timestamp0", timestamp0);
	                    map.put("datetime0", datetime0);
	                    map.put("open_price0", open_price0);
	                    map.put("trade_price0", trade_price0);
	                    map.put("B_timestamp", timestamp1);
	                    map.put("B_datetime", datetime1);
	                    map.put("B_open_price", open_price1);
	                    map.put("B_trade_price", trade_price1);
	                    
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
		
		Collections.sort(coinlist, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> first, HashMap<String, String> second) {
            	Double first_value = Double.parseDouble(first.get("change_percent"));
            	Double second_value = Double.parseDouble(second.get("change_percent"));
                return second_value.compareTo(first_value);
            }
        });
		return coinlist;
	}
	
	@GetMapping(path = "/upbit_60m")
	public List<HashMap<String, String>> upbit_60m(HttpServletRequest req) throws Exception {
		System.out.println("/Main/upbit_60m");
		ArrayList<HashMap<String, String>> coinlist = new ArrayList<HashMap<String, String>>();
		
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = null;
			List<HashMap<String, String>> coininfo = CoinService.CoinInfo();
			
			for(int i=0; i<coininfo.size(); i++) {
				if(i > 0) {
					Thread.sleep(300);
				}
				String ticker = coininfo.get(i).get("coin_ticker");
				String kor_name = coininfo.get(i).get("coin_kor_name");
				String eng_name = coininfo.get(i).get("coin_eng_name");
			
		        httpGet = new HttpGet("https://api.upbit.com/v1/candles/minutes/60?market="+ticker+"&count=2");
		        org.apache.http.Header header = new org.apache.http.message.BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        CloseableHttpResponse response = httpclient.execute(httpGet);
	
		        try {
		            InputStream ips = response.getEntity().getContent();
		            InputStreamReader sr = new InputStreamReader(ips);
		            BufferedReader br = new BufferedReader(sr);
	
		            while (true) {
		            	String return_list = br.readLine();
		            	JSONArray JSONarray = new JSONArray(return_list);
		            	JSONObject JSONObject0 = JSONarray.getJSONObject(0);
		            	JSONObject JSONObject1 = JSONarray.getJSONObject(1);
		            	
		            	//System.out.println("> 현재 " + JSONObject0);
		            	//System.out.println("> 이전 " + JSONObject1);
		            	
	                    String market = (JSONObject0.get("market")).toString();
	                    String timestamp0 = (JSONObject0.get("timestamp")).toString();
	                    String datetime0 = (JSONObject0.get("candle_date_time_kst")).toString();
	                    String open_price0 = (JSONObject0.get("opening_price")).toString();
	                    String trade_price0 = (JSONObject0.get("trade_price")).toString();
	                    String trade_volume0 = JSONObject1.get("candle_acc_trade_volume").toString();
	                    
	                    String timestamp1 = (JSONObject1.get("timestamp")).toString();
	                    String datetime1 = (JSONObject1.get("candle_date_time_kst")).toString();
	                    String open_price1 = (JSONObject1.get("opening_price")).toString();
	                    String trade_price1 = (JSONObject1.get("trade_price")).toString();
	                    String trade_volume1 = JSONObject1.get("candle_acc_trade_volume").toString();
	                    
	                    BigDecimal BIGDECIMAL_TRADE_VOLUME0 = new BigDecimal(trade_volume0);
	                    BigDecimal BIGDECIMAL_TRADE_PRICE0 = new BigDecimal(trade_price0);
	                    BigDecimal BIGDECIMAL_OPEN_PRICE0 = new BigDecimal(open_price0);
	                    BigDecimal BIGDECIMAL_TRADE_VOLUME1 = new BigDecimal(trade_volume1);
	                    BigDecimal BIGDECIMAL_TRADE_PRICE1 = new BigDecimal(trade_price1);
	                    BigDecimal BIGDECIMAL_OPEN_PRICE1 = new BigDecimal(open_price1);
	                    BigDecimal BIGDECIMAL_CHANGE = ((BIGDECIMAL_TRADE_PRICE1.subtract(BIGDECIMAL_TRADE_PRICE0)).divide(BIGDECIMAL_TRADE_PRICE0, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
	                    BigDecimal BIGDECIMAL_VOLUME_CHANGE = ((BIGDECIMAL_TRADE_VOLUME1.subtract(BIGDECIMAL_TRADE_VOLUME0)).divide(BIGDECIMAL_TRADE_VOLUME0, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
	                    
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] "+ datetime0 + " " + open_price0 + " " + trade_price0 + " " + trade_volume_price0+ "  |  " + trade_volume0);
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] "+ datetime1 + " " + open_price1 + " " + trade_price1 + " " + trade_volume_price1+ "  |  " + trade_volume1);
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] TRADE_VOLUME_CHANGE ["+BIGDECIMAL_TRADE_VOLUME_CHANGE.toString()+"] TRADE_VOLUME_PRICE0 ["+BIGDECIMAL_TRADE_VOLUME_PRICE0.toString()+"] TRADE_VOLUME_PRICE1 ["+BIGDECIMAL_TRADE_VOLUME_PRICE1.toString()+"]");
	                    
	                    HashMap<String, String> map = new HashMap<String, String>();
	                    map.put("id", ticker.replace("KRW-", ""));
	                    map.put("kor_name", kor_name);
	                    map.put("eng_name", eng_name);
	                    map.put("change_percent", BIGDECIMAL_CHANGE.toString());
	                    map.put("volume_change_percent", BIGDECIMAL_VOLUME_CHANGE.toString());
	                    map.put("timestamp0", timestamp0);
	                    map.put("datetime0", datetime0);
	                    map.put("open_price0", open_price0);
	                    map.put("trade_price0", trade_price0);
	                    map.put("B_timestamp", timestamp1);
	                    map.put("B_datetime", datetime1);
	                    map.put("B_open_price", open_price1);
	                    map.put("B_trade_price", trade_price1);
	                    
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
		
		Collections.sort(coinlist, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> first, HashMap<String, String> second) {
            	Double first_value = Double.parseDouble(first.get("change_percent"));
            	Double second_value = Double.parseDouble(second.get("change_percent"));
                return second_value.compareTo(first_value);
            }
        });
		return coinlist;
	}
	
	@GetMapping(path = "/upbit_240m")
	public List<HashMap<String, String>> upbit_240m(HttpServletRequest req) throws Exception {
		System.out.println("/Main/upbit_240m");
		ArrayList<HashMap<String, String>> coinlist = new ArrayList<HashMap<String, String>>();
		
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = null;
			List<HashMap<String, String>> coininfo = CoinService.CoinInfo();
			
			for(int i=0; i<coininfo.size(); i++) {
				if(i > 0) {
					Thread.sleep(300);
				}
				String ticker = coininfo.get(i).get("coin_ticker");
				String kor_name = coininfo.get(i).get("coin_kor_name");
				String eng_name = coininfo.get(i).get("coin_eng_name");
			
		        httpGet = new HttpGet("https://api.upbit.com/v1/candles/minutes/240?market="+ticker+"&count=2");
		        org.apache.http.Header header = new org.apache.http.message.BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		        CloseableHttpResponse response = httpclient.execute(httpGet);
	
		        try {
		            InputStream ips = response.getEntity().getContent();
		            InputStreamReader sr = new InputStreamReader(ips);
		            BufferedReader br = new BufferedReader(sr);
	
		            while (true) {
		            	String return_list = br.readLine();
		            	JSONArray JSONarray = new JSONArray(return_list);
		            	JSONObject JSONObject0 = JSONarray.getJSONObject(0);
		            	JSONObject JSONObject1 = JSONarray.getJSONObject(1);
		            	
		            	//System.out.println("> 현재 " + JSONObject0);
		            	//System.out.println("> 이전 " + JSONObject1);
		            	
	                    String market = (JSONObject0.get("market")).toString();
	                    String timestamp0 = (JSONObject0.get("timestamp")).toString();
	                    String datetime0 = (JSONObject0.get("candle_date_time_kst")).toString();
	                    String open_price0 = (JSONObject0.get("opening_price")).toString();
	                    String trade_price0 = (JSONObject0.get("trade_price")).toString();
	                    String trade_volume0 = JSONObject1.get("candle_acc_trade_volume").toString();
	                    
	                    String timestamp1 = (JSONObject1.get("timestamp")).toString();
	                    String datetime1 = (JSONObject1.get("candle_date_time_kst")).toString();
	                    String open_price1 = (JSONObject1.get("opening_price")).toString();
	                    String trade_price1 = (JSONObject1.get("trade_price")).toString();
	                    String trade_volume1 = JSONObject1.get("candle_acc_trade_volume").toString();
	                    
	                    BigDecimal BIGDECIMAL_TRADE_VOLUME0 = new BigDecimal(trade_volume0);
	                    BigDecimal BIGDECIMAL_TRADE_PRICE0 = new BigDecimal(trade_price0);
	                    BigDecimal BIGDECIMAL_OPEN_PRICE0 = new BigDecimal(open_price0);
	                    BigDecimal BIGDECIMAL_TRADE_VOLUME1 = new BigDecimal(trade_volume1);
	                    BigDecimal BIGDECIMAL_TRADE_PRICE1 = new BigDecimal(trade_price1);
	                    BigDecimal BIGDECIMAL_OPEN_PRICE1 = new BigDecimal(open_price1);
	                    BigDecimal BIGDECIMAL_CHANGE = ((BIGDECIMAL_TRADE_PRICE1.subtract(BIGDECIMAL_TRADE_PRICE0)).divide(BIGDECIMAL_TRADE_PRICE0, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
	                    BigDecimal BIGDECIMAL_VOLUME_CHANGE = ((BIGDECIMAL_TRADE_VOLUME1.subtract(BIGDECIMAL_TRADE_VOLUME0)).divide(BIGDECIMAL_TRADE_VOLUME0, 4, RoundingMode.CEILING)).multiply(new BigDecimal("100"));
	                    
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] "+ datetime0 + " " + open_price0 + " " + trade_price0 + " " + trade_volume_price0+ "  |  " + trade_volume0);
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] "+ datetime1 + " " + open_price1 + " " + trade_price1 + " " + trade_volume_price1+ "  |  " + trade_volume1);
	                    //System.out.println("["+ticker.replace("KRW-", "")+"-"+kor_name+"] TRADE_VOLUME_CHANGE ["+BIGDECIMAL_TRADE_VOLUME_CHANGE.toString()+"] TRADE_VOLUME_PRICE0 ["+BIGDECIMAL_TRADE_VOLUME_PRICE0.toString()+"] TRADE_VOLUME_PRICE1 ["+BIGDECIMAL_TRADE_VOLUME_PRICE1.toString()+"]");
	                    
	                    HashMap<String, String> map = new HashMap<String, String>();
	                    map.put("id", ticker.replace("KRW-", ""));
	                    map.put("kor_name", kor_name);
	                    map.put("eng_name", eng_name);
	                    map.put("change_percent", BIGDECIMAL_CHANGE.toString());
	                    map.put("volume_change_percent", BIGDECIMAL_VOLUME_CHANGE.toString());
	                    map.put("timestamp0", timestamp0);
	                    map.put("datetime0", datetime0);
	                    map.put("open_price0", open_price0);
	                    map.put("trade_price0", trade_price0);
	                    map.put("B_timestamp", timestamp1);
	                    map.put("B_datetime", datetime1);
	                    map.put("B_open_price", open_price1);
	                    map.put("B_trade_price", trade_price1);
	                    
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
		
		Collections.sort(coinlist, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> first, HashMap<String, String> second) {
            	Double first_value = Double.parseDouble(first.get("change_percent"));
            	Double second_value = Double.parseDouble(second.get("change_percent"));
                return second_value.compareTo(first_value);
            }
        });
		return coinlist;
	}
	
	@GetMapping(path = "/ReportList")
	public List<HashMap<String, String>> ReportList() throws Exception {
		return MainService.ReportList();
	}
	
	@GetMapping(path = "/ReportHTMLList")
	public List<HashMap<String, String>> ReportHTMLList(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		String market = (req.getParameter("market")==null)?"":req.getParameter("market");
		String blog = (req.getParameter("blog")==null)?"":req.getParameter("blog");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		map.put("market", market);
		map.put("blog", blog);
		return MainService.ReportHTMLList(map);
	}
}