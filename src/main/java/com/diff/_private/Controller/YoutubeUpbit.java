package com.diff._private.Controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.sql.Timestamp;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javax.net.ssl.HttpsURLConnection;
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
import com.diff._private.Service.MainService;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/YoutubeUpbit")
public class YoutubeUpbit {
	
	@Autowired
	MainService MainService;
	
	@GetMapping(path = "/MIN5")
	public List<HashMap<String, String>> MIN5() throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[업비트][" + START_DATETIME + "] MIN5 시작");
		String GIJUN_DATETIME = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date = DateNow;
        String API_HH = HHNow;
        String API_MM = "";
        
        if(Integer.parseInt(MMNow) >= 55) {
        	API_MM = "55";
        } else if(Integer.parseInt(MMNow) >= 50) {
        	API_MM = "50";
        } else if(Integer.parseInt(MMNow) >= 45) {
        	API_MM = "45";
        } else if(Integer.parseInt(MMNow) >= 40) {
        	API_MM = "40";
        } else if(Integer.parseInt(MMNow) >= 35) {
        	API_MM = "35";
        } else if(Integer.parseInt(MMNow) >= 30) {
        	API_MM = "30";
        } else if(Integer.parseInt(MMNow) >= 25) {
        	API_MM = "25";
        } else if(Integer.parseInt(MMNow) >= 20) {
        	API_MM = "20";
        } else if(Integer.parseInt(MMNow) >= 15) {
        	API_MM = "15";
        } else if(Integer.parseInt(MMNow) >= 10) {
        	API_MM = "10";
        } else if(Integer.parseInt(MMNow) >= 5) {
        	API_MM = "05";
        } else {
        	API_MM = "00";
        }
        
        GIJUN_DATETIME = API_Date + " " + API_HH + ":" + API_MM;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = simpleDateFormat.format(System.currentTimeMillis());
        
        // 5분전
        long TIMESTMAP_5 = TIMESTMAP - (60000*5);
		String DATETIME_KST_5 = simpleDateFormat.format(TIMESTMAP_5);
        
        String FROM_TIMESTAMP = (Long.toString(TIMESTMAP_5)).substring(0, 10);
        String TO_TIMESTAMP = (Long.toString(TIMESTMAP)).substring(0, 10);
        
		List<HashMap<String, String>> CoinList = MainService.UpbitCoinList();
		List<HashMap<String, String>> APICoinList = new ArrayList<HashMap<String, String>>();
		
		for(int x=0; x<CoinList.size(); x++) {
			if(x == Math.ceil(CoinList.size()/2)) {
				TIMESTMAP = System.currentTimeMillis();
				CURRENT_DATETIME_KST = simpleDateFormat.format(System.currentTimeMillis());
			}
			
			String Coin_Ticker = CoinList.get(x).get("coin_ticker");
			String API_Coin_Ticker = CoinList.get(x).get("api_coin_ticker");
			String Coin_Kor_Name = CoinList.get(x).get("coin_kor_name");
			
			URL url = new URL("https://crix-api-tv.upbit.com/v1/crix/tradingview/history?symbol="+API_Coin_Ticker+"KRW&resolution=5&from="+FROM_TIMESTAMP+"&to="+TO_TIMESTAMP);
			
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection(); 
	        conn.setRequestMethod("GET");
	        conn.setConnectTimeout(3000); // 연결 타임아웃 설정 (3초)
	        conn.setReadTimeout(3000); // 읽기 타임아웃 설정 (3초)
	        conn.connect();
	        
	        String RESPONSE_DATA = "";
	        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = null;
	            while(true){
	                line = reader.readLine();
	                if(line == null)
	                    break;
	                
	                RESPONSE_DATA += line;
	            }
	            reader.close();
	        }
	        
	        JSONObject jObject = new JSONObject(RESPONSE_DATA);
	        Calendar TMP_CAL = Calendar.getInstance();
	        if((jObject.getString("s")).equals("ok")) {
	        	JSONArray jArray_t = jObject.getJSONArray("t");
	        	JSONArray jArray_o = jObject.getJSONArray("o");
	        	JSONArray jArray_l = jObject.getJSONArray("l");
	        	JSONArray jArray_h = jObject.getJSONArray("h");
	        	JSONArray jArray_c = jObject.getJSONArray("c");
	        	JSONArray jArray_v = jObject.getJSONArray("v");
	        	
	        	for (int i = 0; i < jArray_t.length(); i++) {
	        		String timestamp = (jArray_t.get(i)).toString();
	        		String datetime_kst = simpleDateFormat.format(new Date(Long.parseLong(timestamp)));
	        		String datetime_kst_hh = datetime_kst.substring(11, 13);
	        		String datetime_kst_mm = datetime_kst.substring(14, 16);
	        		String datetime_utc = simpleDateFormat.format(new Date(Long.parseLong(timestamp) - (60000*540)));
	        		String o_price = Double_ReFresh((jArray_o.get(i)).toString());
	        		String l_price = Double_ReFresh((jArray_l.get(i)).toString());
	        		String h_price = Double_ReFresh((jArray_h.get(i)).toString());
	        		String c_price = Double_ReFresh((jArray_c.get(i)).toString());
	        		String volume = Double_ReFresh((jArray_v.get(i)).toString());
	        		String gubun = "";
	        		
	        		if(o_price.contains("E") || l_price.contains("E") || h_price.contains("E") || c_price.contains("E")) {
	        			BigDecimal BD_O = new BigDecimal(Double.parseDouble((jArray_o.get(i)).toString()));
	        			BigDecimal BD_L = new BigDecimal(Double.parseDouble((jArray_l.get(i)).toString()));
		        		BigDecimal BD_H = new BigDecimal(Double.parseDouble((jArray_h.get(i)).toString()));
		        		BigDecimal BD_C = new BigDecimal(Double.parseDouble((jArray_c.get(i)).toString()));
		        		
	        			o_price = Double_ReFresh((BD_O).toString());
	        			l_price = Double_ReFresh((BD_L).toString());
		        		h_price = Double_ReFresh((BD_H).toString());
		        		c_price = Double_ReFresh((BD_C).toString());
	        		}
	        		
	        		if(volume.contains("E")) {
	        			BigDecimal BD_V = new BigDecimal(Double.parseDouble((jArray_v.get(i)).toString()));
	        			volume = Double_ReFresh((BD_V).toString());
	        		}
	        		
	        		BigDecimal BD_O_PRICE = new BigDecimal(o_price);
	        		BigDecimal BD_C_PRICE = new BigDecimal(c_price);
	        		BigDecimal BD_VOLUME = new BigDecimal(volume);
	        		BigDecimal BD_100 = new BigDecimal("100");
	        		
	        		if(BD_O_PRICE.compareTo(BD_C_PRICE) == -1) {
	        			gubun = "상승";
	        		} else if(BD_O_PRICE.compareTo(BD_C_PRICE) == 1) {
	        			gubun = "하락";
	        		} else {
	        			gubun = "보합";
	        		}
	        		
	        		BigDecimal BD_O_C_SUBTRACT = BD_C_PRICE.subtract(BD_O_PRICE);
	        		BigDecimal BD_O_C_RATE = ((BD_C_PRICE.subtract(BD_O_PRICE)).divide(BD_O_PRICE, 6, RoundingMode.HALF_UP)).multiply(BD_100);
	        		BigDecimal BD_PRICE_VOLUME = BD_C_PRICE.multiply(BD_VOLUME);
	        		
	        		String format_c_price = c_price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		String volume_price = (BD_PRICE_VOLUME.setScale(0, RoundingMode.FLOOR)).toString();
	        		String format_volume_price = volume_price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		String o_c_rate = (BD_O_C_RATE.setScale(2, RoundingMode.FLOOR)).toString() + '%';
	        		String o_c_subtract = (BD_O_C_SUBTRACT.toString()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		
	        		if(API_HH.equals(datetime_kst_hh) && API_MM.equals(datetime_kst_mm)) {
	        			
		        		HashMap<String, String> map = new HashMap<String, String>();
		        		map.put("API_DATETIME_KST", GIJUN_DATETIME);
		        		map.put("CURRENT_DATETIME_KST", CURRENT_DATETIME_KST);
		        		map.put("Coin_Ticker", Coin_Ticker);
		        		map.put("API_Coin_Ticker", API_Coin_Ticker);
		        		map.put("Coin_Kor_Name", Coin_Kor_Name);
		        		map.put("timestamp", timestamp);
		        		map.put("datetime_kst", datetime_kst);
		        		map.put("datetime_utc", datetime_utc);
		        		map.put("gubun", gubun);
		        		map.put("o_price", o_price);
		        		map.put("l_price", l_price);
		        		map.put("h_price", h_price);
		        		map.put("c_price", c_price);
		        		map.put("format_c_price", format_c_price);
		        		map.put("o_c_rate", o_c_rate);
		        		map.put("o_c_subtract", o_c_subtract);
		        		map.put("volume", volume);
		        		map.put("volume_price", volume_price);
		        		map.put("format_volume_price", format_volume_price);
		        		APICoinList.add(map);
	        		}
	        	}
	        }
	        
	        conn.disconnect();
		}
		
		Collections.sort(APICoinList, new Comparator<HashMap<String,String>>(){
			public int compare(HashMap<String,String> map1, HashMap<String,String> map2){
				if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 1) return 1;
                else if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 0) return 0;
                else return -1;
			}
		});
		
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[업비트][" + END_DATETIME + "] MIN5 종료");
		
		return APICoinList;
	}
	
	@GetMapping(path = "/MIN15")
	public List<HashMap<String, String>> MIN15() throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[업비트][" + START_DATETIME + "] MIN15 시작");
		
		String GIJUN_DATETIME = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date = DateNow;
        String API_HH = HHNow;
        String API_MM = "";
        
        if(Integer.parseInt(MMNow) >= 45) {
        	API_MM = "45";
        } else if(Integer.parseInt(MMNow) >= 30) {
        	API_MM = "30";
        } else if(Integer.parseInt(MMNow) >= 15) {
        	API_MM = "15";
        } else {
        	API_MM = "00";
        }
        
        GIJUN_DATETIME = API_Date + " " + API_HH + ":" + API_MM;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = simpleDateFormat.format(System.currentTimeMillis());
        
        // 15분전
        long TIMESTMAP_15 = TIMESTMAP - (60000*15);
		String DATETIME_KST_15 = simpleDateFormat.format(TIMESTMAP_15);
        
        String FROM_TIMESTAMP = (Long.toString(TIMESTMAP_15)).substring(0, 10);
        String TO_TIMESTAMP = (Long.toString(TIMESTMAP)).substring(0, 10);
        
		List<HashMap<String, String>> CoinList = MainService.UpbitCoinList();
		List<HashMap<String, String>> APICoinList = new ArrayList<HashMap<String, String>>();
		
		for(int x=0; x<CoinList.size(); x++) {
			if(x == Math.ceil(CoinList.size()/2)) {
				TIMESTMAP = System.currentTimeMillis();
				CURRENT_DATETIME_KST = simpleDateFormat.format(System.currentTimeMillis());
			}
			
			String Coin_Ticker = CoinList.get(x).get("coin_ticker");
			String API_Coin_Ticker = CoinList.get(x).get("api_coin_ticker");
			String Coin_Kor_Name = CoinList.get(x).get("coin_kor_name");
			
			URL url = new URL("https://crix-api-tv.upbit.com/v1/crix/tradingview/history?symbol="+API_Coin_Ticker+"KRW&resolution=15&from="+FROM_TIMESTAMP+"&to="+TO_TIMESTAMP);
			
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection(); 
	        conn.setRequestMethod("GET");
	        conn.setConnectTimeout(3000); // 연결 타임아웃 설정 (3초)
	        conn.setReadTimeout(3000); // 읽기 타임아웃 설정 (3초)
	        conn.connect();
	        
	        String RESPONSE_DATA = "";
	        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = null;
	            while(true){
	                line = reader.readLine();
	                if(line == null)
	                    break;
	                
	                RESPONSE_DATA += line;
	            }
	            reader.close();
	        }
	        
	        JSONObject jObject = new JSONObject(RESPONSE_DATA);
	        Calendar TMP_CAL = Calendar.getInstance();
	        if((jObject.getString("s")).equals("ok")) {
	        	JSONArray jArray_t = jObject.getJSONArray("t");
	        	JSONArray jArray_o = jObject.getJSONArray("o");
	        	JSONArray jArray_l = jObject.getJSONArray("l");
	        	JSONArray jArray_h = jObject.getJSONArray("h");
	        	JSONArray jArray_c = jObject.getJSONArray("c");
	        	JSONArray jArray_v = jObject.getJSONArray("v");
	        	
	        	for (int i = 0; i < jArray_t.length(); i++) {
	        		String timestamp = (jArray_t.get(i)).toString();
	        		String datetime_kst = simpleDateFormat.format(new Date(Long.parseLong(timestamp)));
	        		String datetime_kst_hh = datetime_kst.substring(11, 13);
	        		String datetime_kst_mm = datetime_kst.substring(14, 16);
	        		String datetime_utc = simpleDateFormat.format(new Date(Long.parseLong(timestamp) - (60000*540)));
	        		String o_price = Double_ReFresh((jArray_o.get(i)).toString());
	        		String l_price = Double_ReFresh((jArray_l.get(i)).toString());
	        		String h_price = Double_ReFresh((jArray_h.get(i)).toString());
	        		String c_price = Double_ReFresh((jArray_c.get(i)).toString());
	        		String volume = Double_ReFresh((jArray_v.get(i)).toString());
	        		String gubun = "";
	        		
	        		if(o_price.contains("E") || l_price.contains("E") || h_price.contains("E") || c_price.contains("E")) {
	        			BigDecimal BD_O = new BigDecimal(Double.parseDouble((jArray_o.get(i)).toString()));
	        			BigDecimal BD_L = new BigDecimal(Double.parseDouble((jArray_l.get(i)).toString()));
		        		BigDecimal BD_H = new BigDecimal(Double.parseDouble((jArray_h.get(i)).toString()));
		        		BigDecimal BD_C = new BigDecimal(Double.parseDouble((jArray_c.get(i)).toString()));
		        		
	        			o_price = Double_ReFresh((BD_O).toString());
	        			l_price = Double_ReFresh((BD_L).toString());
		        		h_price = Double_ReFresh((BD_H).toString());
		        		c_price = Double_ReFresh((BD_C).toString());
	        		}
	        		
	        		if(volume.contains("E")) {
	        			BigDecimal BD_V = new BigDecimal(Double.parseDouble((jArray_v.get(i)).toString()));
	        			volume = Double_ReFresh((BD_V).toString());
	        		}
	        		
	        		BigDecimal BD_O_PRICE = new BigDecimal(o_price);
	        		BigDecimal BD_C_PRICE = new BigDecimal(c_price);
	        		BigDecimal BD_VOLUME = new BigDecimal(volume);
	        		BigDecimal BD_100 = new BigDecimal("100");
	        		
	        		if(BD_O_PRICE.compareTo(BD_C_PRICE) == -1) {
	        			gubun = "상승";
	        		} else if(BD_O_PRICE.compareTo(BD_C_PRICE) == 1) {
	        			gubun = "하락";
	        		} else {
	        			gubun = "보합";
	        		}
	        		
	        		BigDecimal BD_O_C_SUBTRACT = BD_C_PRICE.subtract(BD_O_PRICE);
	        		BigDecimal BD_O_C_RATE = ((BD_C_PRICE.subtract(BD_O_PRICE)).divide(BD_O_PRICE, 6, RoundingMode.HALF_UP)).multiply(BD_100);
	        		BigDecimal BD_PRICE_VOLUME = BD_C_PRICE.multiply(BD_VOLUME);
	        		
	        		String format_c_price = c_price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		String volume_price = (BD_PRICE_VOLUME.setScale(0, RoundingMode.FLOOR)).toString();
	        		String format_volume_price = volume_price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		String o_c_rate = (BD_O_C_RATE.setScale(2, RoundingMode.FLOOR)).toString() + '%';
	        		String o_c_subtract = (BD_O_C_SUBTRACT.toString()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		
	        		if(API_HH.equals(datetime_kst_hh) && API_MM.equals(datetime_kst_mm)) {
		        		HashMap<String, String> map = new HashMap<String, String>();
		        		map.put("API_DATETIME_KST", GIJUN_DATETIME);
		        		map.put("CURRENT_DATETIME_KST", CURRENT_DATETIME_KST);
		        		map.put("Coin_Ticker", Coin_Ticker);
		        		map.put("API_Coin_Ticker", API_Coin_Ticker);
		        		map.put("Coin_Kor_Name", Coin_Kor_Name);
		        		map.put("timestamp", timestamp);
		        		map.put("datetime_kst", datetime_kst);
		        		map.put("datetime_utc", datetime_utc);
		        		map.put("gubun", gubun);
		        		map.put("o_price", o_price);
		        		map.put("l_price", l_price);
		        		map.put("h_price", h_price);
		        		map.put("c_price", c_price);
		        		map.put("format_c_price", format_c_price);
		        		map.put("o_c_rate", o_c_rate);
		        		map.put("o_c_subtract", o_c_subtract);
		        		map.put("volume", volume);
		        		map.put("volume_price", volume_price);
		        		map.put("format_volume_price", format_volume_price);
		        		APICoinList.add(map);
	        		}
	        	}
	        }
	        
	        conn.disconnect();
		}
		
		Collections.sort(APICoinList, new Comparator<HashMap<String,String>>(){
			public int compare(HashMap<String,String> map1, HashMap<String,String> map2){
				if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 1) return 1;
                else if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 0) return 0;
                else return -1;
			}
		});
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[업비트][" + END_DATETIME + "] MIN15 종료");
		
		return APICoinList;
	}
	
	@GetMapping(path = "/MIN60")
	public List<HashMap<String, String>> MIN60() throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[업비트][" + START_DATETIME + "] MIN60 시작");
		
		String GIJUN_DATETIME = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date = DateNow;
        String API_HH = HHNow;
        String API_MM = "00";
        
        GIJUN_DATETIME = API_Date + " " + API_HH + ":" + API_MM;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = simpleDateFormat.format(System.currentTimeMillis());
        
        // 60분전
        long TIMESTMAP_60 = TIMESTMAP - (60000*60);
		String DATETIME_KST_60 = simpleDateFormat.format(TIMESTMAP_60);
        
        String FROM_TIMESTAMP = (Long.toString(TIMESTMAP_60)).substring(0, 10);
        String TO_TIMESTAMP = (Long.toString(TIMESTMAP)).substring(0, 10);
        
		List<HashMap<String, String>> CoinList = MainService.UpbitCoinList();
		List<HashMap<String, String>> APICoinList = new ArrayList<HashMap<String, String>>();
		
		for(int x=0; x<CoinList.size(); x++) {
			if(x == Math.ceil(CoinList.size()/2)) {
				TIMESTMAP = System.currentTimeMillis();
				CURRENT_DATETIME_KST = simpleDateFormat.format(System.currentTimeMillis());
			}
			
			String Coin_Ticker = CoinList.get(x).get("coin_ticker");
			String API_Coin_Ticker = CoinList.get(x).get("api_coin_ticker");
			String Coin_Kor_Name = CoinList.get(x).get("coin_kor_name");
			
			URL url = new URL("https://crix-api-tv.upbit.com/v1/crix/tradingview/history?symbol="+API_Coin_Ticker+"KRW&resolution=60&from="+FROM_TIMESTAMP+"&to="+TO_TIMESTAMP);
			
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection(); 
	        conn.setRequestMethod("GET");
	        conn.setConnectTimeout(3000); // 연결 타임아웃 설정 (3초)
	        conn.setReadTimeout(3000); // 읽기 타임아웃 설정 (3초)
	        conn.connect();
	        
	        String RESPONSE_DATA = "";
	        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = null;
	            while(true){
	                line = reader.readLine();
	                if(line == null)
	                    break;
	                
	                RESPONSE_DATA += line;
	            }
	            reader.close();
	        }
	        
	        JSONObject jObject = new JSONObject(RESPONSE_DATA);
	        Calendar TMP_CAL = Calendar.getInstance();
	        if((jObject.getString("s")).equals("ok")) {
	        	JSONArray jArray_t = jObject.getJSONArray("t");
	        	JSONArray jArray_o = jObject.getJSONArray("o");
	        	JSONArray jArray_l = jObject.getJSONArray("l");
	        	JSONArray jArray_h = jObject.getJSONArray("h");
	        	JSONArray jArray_c = jObject.getJSONArray("c");
	        	JSONArray jArray_v = jObject.getJSONArray("v");
	        	
	        	for (int i = 0; i < jArray_t.length(); i++) {
	        		String timestamp = (jArray_t.get(i)).toString();
	        		String datetime_kst = simpleDateFormat.format(new Date(Long.parseLong(timestamp)));
	        		String datetime_kst_hh = datetime_kst.substring(11, 13);
	        		String datetime_kst_mm = datetime_kst.substring(14, 16);
	        		String datetime_utc = simpleDateFormat.format(new Date(Long.parseLong(timestamp) - (60000*540)));
	        		String o_price = Double_ReFresh((jArray_o.get(i)).toString());
	        		String l_price = Double_ReFresh((jArray_l.get(i)).toString());
	        		String h_price = Double_ReFresh((jArray_h.get(i)).toString());
	        		String c_price = Double_ReFresh((jArray_c.get(i)).toString());
	        		String volume = Double_ReFresh((jArray_v.get(i)).toString());
	        		String gubun = "";
	        		
	        		if(o_price.contains("E") || l_price.contains("E") || h_price.contains("E") || c_price.contains("E")) {
	        			BigDecimal BD_O = new BigDecimal(Double.parseDouble((jArray_o.get(i)).toString()));
	        			BigDecimal BD_L = new BigDecimal(Double.parseDouble((jArray_l.get(i)).toString()));
		        		BigDecimal BD_H = new BigDecimal(Double.parseDouble((jArray_h.get(i)).toString()));
		        		BigDecimal BD_C = new BigDecimal(Double.parseDouble((jArray_c.get(i)).toString()));
		        		
	        			o_price = Double_ReFresh((BD_O).toString());
	        			l_price = Double_ReFresh((BD_L).toString());
		        		h_price = Double_ReFresh((BD_H).toString());
		        		c_price = Double_ReFresh((BD_C).toString());
	        		}
	        		
	        		if(volume.contains("E")) {
	        			BigDecimal BD_V = new BigDecimal(Double.parseDouble((jArray_v.get(i)).toString()));
	        			volume = Double_ReFresh((BD_V).toString());
	        		}
	        		
	        		BigDecimal BD_O_PRICE = new BigDecimal(o_price);
	        		BigDecimal BD_C_PRICE = new BigDecimal(c_price);
	        		BigDecimal BD_VOLUME = new BigDecimal(volume);
	        		BigDecimal BD_100 = new BigDecimal("100");
	        		
	        		if(BD_O_PRICE.compareTo(BD_C_PRICE) == -1) {
	        			gubun = "상승";
	        		} else if(BD_O_PRICE.compareTo(BD_C_PRICE) == 1) {
	        			gubun = "하락";
	        		} else {
	        			gubun = "보합";
	        		}
	        		
	        		BigDecimal BD_O_C_SUBTRACT = BD_C_PRICE.subtract(BD_O_PRICE);
	        		BigDecimal BD_O_C_RATE = ((BD_C_PRICE.subtract(BD_O_PRICE)).divide(BD_O_PRICE, 6, RoundingMode.HALF_UP)).multiply(BD_100);
	        		BigDecimal BD_PRICE_VOLUME = BD_C_PRICE.multiply(BD_VOLUME);
	        		
	        		String format_c_price = c_price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		String volume_price = (BD_PRICE_VOLUME.setScale(0, RoundingMode.FLOOR)).toString();
	        		String format_volume_price = volume_price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		String o_c_rate = (BD_O_C_RATE.setScale(2, RoundingMode.FLOOR)).toString() + '%';
	        		String o_c_subtract = (BD_O_C_SUBTRACT.toString()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		
	        		if(API_HH.equals(datetime_kst_hh) && API_MM.equals(datetime_kst_mm)) {
		        		HashMap<String, String> map = new HashMap<String, String>();
		        		map.put("API_DATETIME_KST", GIJUN_DATETIME);
		        		map.put("CURRENT_DATETIME_KST", CURRENT_DATETIME_KST);
		        		map.put("Coin_Ticker", Coin_Ticker);
		        		map.put("API_Coin_Ticker", API_Coin_Ticker);
		        		map.put("Coin_Kor_Name", Coin_Kor_Name);
		        		map.put("timestamp", timestamp);
		        		map.put("datetime_kst", datetime_kst);
		        		map.put("datetime_utc", datetime_utc);
		        		map.put("gubun", gubun);
		        		map.put("o_price", o_price);
		        		map.put("l_price", l_price);
		        		map.put("h_price", h_price);
		        		map.put("c_price", c_price);
		        		map.put("format_c_price", format_c_price);
		        		map.put("o_c_rate", o_c_rate);
		        		map.put("o_c_subtract", o_c_subtract);
		        		map.put("volume", volume);
		        		map.put("volume_price", volume_price);
		        		map.put("format_volume_price", format_volume_price);
		        		APICoinList.add(map);
	        		}
	        	}
	        }
	        
	        conn.disconnect();
		}
		
		Collections.sort(APICoinList, new Comparator<HashMap<String,String>>(){
			public int compare(HashMap<String,String> map1, HashMap<String,String> map2){
				if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 1) return 1;
                else if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 0) return 0;
                else return -1;
			}
		});
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[업비트][" + END_DATETIME + "] MIN60 종료");
		
		return APICoinList;
	}
	
	@GetMapping(path = "/MIN240")
	public List<HashMap<String, String>> MIN240() throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[업비트][" + START_DATETIME + "] MIN240 시작");
		
		String GIJUN_DATETIME = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date = DateNow;
        String API_HH = "";
        String API_MM = "00";
        
        if(Integer.parseInt(HHNow) >= 21) {
        	API_HH = "21";
        } else if(Integer.parseInt(HHNow) >= 17) {
        	API_HH = "17";
        } else if(Integer.parseInt(HHNow) >= 13) {
        	API_HH = "13";
        } else if(Integer.parseInt(HHNow) >= 9) {
        	API_HH = "09";
        } else if(Integer.parseInt(HHNow) >= 5) {
        	API_HH = "05";
        } else if(Integer.parseInt(HHNow) >= 1) {
        	API_HH = "01";
        } else {
        	Calendar Yesterdate = Calendar.getInstance();
        	Yesterdate.add(Calendar.DATE , -1);
        	API_Date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(Yesterdate.getTime());
        	API_HH = "21";
        }
        
        GIJUN_DATETIME = API_Date	 + " " + API_HH + ":" + API_MM;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = simpleDateFormat.format(System.currentTimeMillis());
        
        // 240분전
        long TIMESTMAP_240 = TIMESTMAP - (60000*240);
		String DATETIME_KST_240 = simpleDateFormat.format(TIMESTMAP_240);
        
        String FROM_TIMESTAMP = (Long.toString(TIMESTMAP_240)).substring(0, 10);
        String TO_TIMESTAMP = (Long.toString(TIMESTMAP)).substring(0, 10);
        
		List<HashMap<String, String>> CoinList = MainService.UpbitCoinList();
		List<HashMap<String, String>> APICoinList = new ArrayList<HashMap<String, String>>();
		
		for(int x=0; x<CoinList.size(); x++) {
			if(x == Math.ceil(CoinList.size()/2)) {
				TIMESTMAP = System.currentTimeMillis();
				CURRENT_DATETIME_KST = simpleDateFormat.format(System.currentTimeMillis());
			}
			
			String Coin_Ticker = CoinList.get(x).get("coin_ticker");
			String API_Coin_Ticker = CoinList.get(x).get("api_coin_ticker");
			String Coin_Kor_Name = CoinList.get(x).get("coin_kor_name");
			
			URL url = new URL("https://crix-api-tv.upbit.com/v1/crix/tradingview/history?symbol="+API_Coin_Ticker+"KRW&resolution=240&from="+FROM_TIMESTAMP+"&to="+TO_TIMESTAMP);
			
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection(); 
	        conn.setRequestMethod("GET");
	        conn.setConnectTimeout(3000); // 연결 타임아웃 설정 (3초)
	        conn.setReadTimeout(3000); // 읽기 타임아웃 설정 (3초)
	        conn.connect();
	        
	        String RESPONSE_DATA = "";
	        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = null;
	            while(true){
	                line = reader.readLine();
	                if(line == null)
	                    break;
	                
	                RESPONSE_DATA += line;
	            }
	            reader.close();
	        }
	        
	        JSONObject jObject = new JSONObject(RESPONSE_DATA);
	        Calendar TMP_CAL = Calendar.getInstance();
	        if((jObject.getString("s")).equals("ok")) {
	        	JSONArray jArray_t = jObject.getJSONArray("t");
	        	JSONArray jArray_o = jObject.getJSONArray("o");
	        	JSONArray jArray_l = jObject.getJSONArray("l");
	        	JSONArray jArray_h = jObject.getJSONArray("h");
	        	JSONArray jArray_c = jObject.getJSONArray("c");
	        	JSONArray jArray_v = jObject.getJSONArray("v");
	        	
	        	for (int i = 0; i < jArray_t.length(); i++) {
	        		String timestamp = (jArray_t.get(i)).toString();
	        		String datetime_kst = simpleDateFormat.format(new Date(Long.parseLong(timestamp)));
	        		String datetime_kst_hh = datetime_kst.substring(11, 13);
	        		String datetime_kst_mm = datetime_kst.substring(14, 16);
	        		String datetime_utc = simpleDateFormat.format(new Date(Long.parseLong(timestamp) - (60000*540)));
	        		String o_price = Double_ReFresh((jArray_o.get(i)).toString());
	        		String l_price = Double_ReFresh((jArray_l.get(i)).toString());
	        		String h_price = Double_ReFresh((jArray_h.get(i)).toString());
	        		String c_price = Double_ReFresh((jArray_c.get(i)).toString());
	        		String volume = Double_ReFresh((jArray_v.get(i)).toString());
	        		String gubun = "";
	        		
	        		if(o_price.contains("E") || l_price.contains("E") || h_price.contains("E") || c_price.contains("E")) {
	        			BigDecimal BD_O = new BigDecimal(Double.parseDouble((jArray_o.get(i)).toString()));
	        			BigDecimal BD_L = new BigDecimal(Double.parseDouble((jArray_l.get(i)).toString()));
		        		BigDecimal BD_H = new BigDecimal(Double.parseDouble((jArray_h.get(i)).toString()));
		        		BigDecimal BD_C = new BigDecimal(Double.parseDouble((jArray_c.get(i)).toString()));
		        		
	        			o_price = Double_ReFresh((BD_O).toString());
	        			l_price = Double_ReFresh((BD_L).toString());
		        		h_price = Double_ReFresh((BD_H).toString());
		        		c_price = Double_ReFresh((BD_C).toString());
	        		}
	        		
	        		if(volume.contains("E")) {
	        			BigDecimal BD_V = new BigDecimal(Double.parseDouble((jArray_v.get(i)).toString()));
	        			volume = Double_ReFresh((BD_V).toString());
	        		}
	        		
	        		BigDecimal BD_O_PRICE = new BigDecimal(o_price);
	        		BigDecimal BD_C_PRICE = new BigDecimal(c_price);
	        		BigDecimal BD_VOLUME = new BigDecimal(volume);
	        		BigDecimal BD_100 = new BigDecimal("100");
	        		
	        		if(BD_O_PRICE.compareTo(BD_C_PRICE) == -1) {
	        			gubun = "상승";
	        		} else if(BD_O_PRICE.compareTo(BD_C_PRICE) == 1) {
	        			gubun = "하락";
	        		} else {
	        			gubun = "보합";
	        		}
	        		
	        		BigDecimal BD_O_C_SUBTRACT = BD_C_PRICE.subtract(BD_O_PRICE);
	        		BigDecimal BD_O_C_RATE = ((BD_C_PRICE.subtract(BD_O_PRICE)).divide(BD_O_PRICE, 6, RoundingMode.HALF_UP)).multiply(BD_100);
	        		BigDecimal BD_PRICE_VOLUME = BD_C_PRICE.multiply(BD_VOLUME);
	        		
	        		String format_c_price = c_price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		String volume_price = (BD_PRICE_VOLUME.setScale(0, RoundingMode.FLOOR)).toString();
	        		String format_volume_price = volume_price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		String o_c_rate = (BD_O_C_RATE.setScale(2, RoundingMode.FLOOR)).toString() + '%';
	        		String o_c_subtract = (BD_O_C_SUBTRACT.toString()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
	        		
	        		if(API_HH.equals(datetime_kst_hh) && API_MM.equals(datetime_kst_mm)) {
		        		HashMap<String, String> map = new HashMap<String, String>();
		        		map.put("API_DATETIME_KST", GIJUN_DATETIME);
		        		map.put("CURRENT_DATETIME_KST", CURRENT_DATETIME_KST);
		        		map.put("Coin_Ticker", Coin_Ticker);
		        		map.put("API_Coin_Ticker", API_Coin_Ticker);
		        		map.put("Coin_Kor_Name", Coin_Kor_Name);
		        		map.put("timestamp", timestamp);
		        		map.put("datetime_kst", datetime_kst);
		        		map.put("datetime_utc", datetime_utc);
		        		map.put("gubun", gubun);
		        		map.put("o_price", o_price);
		        		map.put("l_price", l_price);
		        		map.put("h_price", h_price);
		        		map.put("c_price", c_price);
		        		map.put("format_c_price", format_c_price);
		        		map.put("o_c_rate", o_c_rate);
		        		map.put("o_c_subtract", o_c_subtract);
		        		map.put("volume", volume);
		        		map.put("volume_price", volume_price);
		        		map.put("format_volume_price", format_volume_price);
		        		APICoinList.add(map);
	        		}
	        	}
	        }
	        
	        conn.disconnect();
		}
		
		Collections.sort(APICoinList, new Comparator<HashMap<String,String>>(){
			public int compare(HashMap<String,String> map1, HashMap<String,String> map2){
				if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 1) return 1;
                else if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 0) return 0;
                else return -1;
			}
		});
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[업비트][" + END_DATETIME + "] MIN240 종료");
		
		return APICoinList;
	}
	
	public String Double_ReFresh(String Origin_Data) {
		for(int x=0; x<10; x++) {
			if(Origin_Data.contains(".")) {
				if(Origin_Data.substring(Origin_Data.length()-1).equals("0") || Origin_Data.substring(Origin_Data.length()-1).equals(".")) {
					Origin_Data = Origin_Data.substring(0, Origin_Data.length()-1);
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return Origin_Data;
	}
}