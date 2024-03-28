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
import java.util.TimeZone;
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
@RequestMapping("/YoutubeBithumb")
public class YoutubeBithumb {
	
	@Autowired
	MainService MainService;
	
	@GetMapping(path = "/BithumbCoinInfo")
	public List<HashMap<String, String>> BithumbCoinInfo() throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[" + START_DATETIME + "] BithumbCoinInfo 시작");
		
		String GIJUN_DATETIME_5 = "";
		String GIJUN_DATETIME_15 = "";
		String GIJUN_DATETIME_60 = "";
		String GIJUN_DATETIME_240 = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date = DateNow;
        String API_HH = HHNow;
        String API_MM_5 = "";
        String API_MM_15 = "";
        String API_Date_60 = DateNow;
        String API_Date_240 = DateNow;
        String API_HH_60 = HHNow;
        String API_HH_240 = "";
        String API_MM = "00";
        
        if(Integer.parseInt(HHNow) >= 20) {
        	API_HH_240 = "20";
        } else if(Integer.parseInt(HHNow) >= 16) {
        	API_HH_240 = "16";
        } else if(Integer.parseInt(HHNow) >= 12) {
        	API_HH_240 = "12";
        } else if(Integer.parseInt(HHNow) >= 8) {
        	API_HH_240 = "08";
        } else if(Integer.parseInt(HHNow) >= 4) {
        	API_HH_240 = "04";
        } else if(Integer.parseInt(HHNow) >= 0) {
        	API_HH_240 = "00";
        }
        
        if(Integer.parseInt(MMNow) >= 55) {
        	API_MM_5 = "55";
        } else if(Integer.parseInt(MMNow) >= 50) {
        	API_MM_5 = "50";
        } else if(Integer.parseInt(MMNow) >= 45) {
        	API_MM_5 = "45";
        } else if(Integer.parseInt(MMNow) >= 40) {
        	API_MM_5 = "40";
        } else if(Integer.parseInt(MMNow) >= 35) {
        	API_MM_5 = "35";
        } else if(Integer.parseInt(MMNow) >= 30) {
        	API_MM_5 = "30";
        } else if(Integer.parseInt(MMNow) >= 25) {
        	API_MM_5 = "25";
        } else if(Integer.parseInt(MMNow) >= 20) {
        	API_MM_5 = "20";
        } else if(Integer.parseInt(MMNow) >= 15) {
        	API_MM_5 = "15";
        } else if(Integer.parseInt(MMNow) >= 10) {
        	API_MM_5 = "10";
        } else if(Integer.parseInt(MMNow) >= 5) {
        	API_MM_5 = "05";
        } else {
        	API_MM_5 = "00";
        }
        
        if(Integer.parseInt(MMNow) >= 45) {
        	API_MM_15 = "45";
        } else if(Integer.parseInt(MMNow) >= 30) {
        	API_MM_15 = "30";
        } else if(Integer.parseInt(MMNow) >= 15) {
        	API_MM_15 = "15";
        } else {
        	API_MM_15 = "00";
        }
        
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SDF.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        
        GIJUN_DATETIME_5 = API_Date + " " + API_HH + ":" + API_MM_5+":00";
		Date GIJUN_DATE_5 = SDF.parse(GIJUN_DATETIME_5);
		long GIJUN_TIMESTAMP_5 = GIJUN_DATE_5.getTime();
		
		GIJUN_DATETIME_15 = API_Date + " " + API_HH + ":" + API_MM_15+":00";
		Date GIJUN_DATE_15 = SDF.parse(GIJUN_DATETIME_15);
		long GIJUN_TIMESTAMP_15 = GIJUN_DATE_15.getTime();
		
		GIJUN_DATETIME_60 = API_Date_60 + " " + API_HH_60 + ":" + API_MM+":00";
		Date GIJUN_DATE_60 = SDF.parse(GIJUN_DATETIME_60);
		long GIJUN_TIMESTAMP_60 = GIJUN_DATE_60.getTime();
		
		GIJUN_DATETIME_240 = API_Date_240 + " " + API_HH_240 + ":" + API_MM+":00";
		Date GIJUN_DATE_240 = SDF.parse(GIJUN_DATETIME_240);
		long GIJUN_TIMESTAMP_240 = GIJUN_DATE_240.getTime();
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = SDF.format(System.currentTimeMillis());
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("TIMESTAMP5", Long.toString(GIJUN_TIMESTAMP_5));
		map.put("GIJUN_DATETIME_5", GIJUN_DATETIME_5);
		map.put("TIMESTAMP15", Long.toString(GIJUN_TIMESTAMP_15));
		map.put("GIJUN_DATETIME_15", GIJUN_DATETIME_15);
		map.put("TIMESTAMP60", Long.toString(GIJUN_TIMESTAMP_60));
		map.put("GIJUN_DATETIME_60", GIJUN_DATETIME_60);
		map.put("TIMESTAMP240", Long.toString(GIJUN_TIMESTAMP_240));
		map.put("GIJUN_DATETIME_240", GIJUN_DATETIME_240);
		
		List<HashMap<String, String>> BithumbCoinList = MainService.BithumbCoinList_Script(map);
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[" + END_DATETIME + "] BithumbCoinInfo 종료");
		
		return BithumbCoinList;
	}
	
	@GetMapping(path = "/SelectCodeData")
	public String SelectCodeData(HttpServletRequest req) throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[" + START_DATETIME + "] SelectCodeData 시작");
		
		String code_id = (req.getParameter("code_id")==null)?"":req.getParameter("code_id");
		
		String CodeValue =  MainService.SelectCodeData(code_id);
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[" + END_DATETIME + "] SelectCodeData 종료");
		
		return CodeValue;
	}
	
	@GetMapping(path = "/MIN5")
	public List<HashMap<String, String>> MIN5 () throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[빗썸][" + START_DATETIME + "] MIN5 시작");
		
		String GIJUN_DATETIME_5 = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date = DateNow;
        String API_HH = HHNow;
        String API_MM_5 = "";
        
        if(Integer.parseInt(MMNow) >= 55) {
        	API_MM_5 = "55";
        } else if(Integer.parseInt(MMNow) >= 50) {
        	API_MM_5 = "50";
        } else if(Integer.parseInt(MMNow) >= 45) {
        	API_MM_5 = "45";
        } else if(Integer.parseInt(MMNow) >= 40) {
        	API_MM_5 = "40";
        } else if(Integer.parseInt(MMNow) >= 35) {
        	API_MM_5 = "35";
        } else if(Integer.parseInt(MMNow) >= 30) {
        	API_MM_5 = "30";
        } else if(Integer.parseInt(MMNow) >= 25) {
        	API_MM_5 = "25";
        } else if(Integer.parseInt(MMNow) >= 20) {
        	API_MM_5 = "20";
        } else if(Integer.parseInt(MMNow) >= 15) {
        	API_MM_5 = "15";
        } else if(Integer.parseInt(MMNow) >= 10) {
        	API_MM_5 = "10";
        } else if(Integer.parseInt(MMNow) >= 5) {
        	API_MM_5 = "05";
        } else {
        	API_MM_5 = "00";
        }
        
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SDF.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        
        GIJUN_DATETIME_5 = API_Date + " " + API_HH + ":" + API_MM_5+":00";
		Date GIJUN_DATE_5 = SDF.parse(GIJUN_DATETIME_5);
		long GIJUN_TIMESTAMP_5 = GIJUN_DATE_5.getTime();
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = SDF.format(System.currentTimeMillis());
		
		List<HashMap<String, String>> CoinList = MainService.BithumbCoinList();
		List<HashMap<String, String>> APICoinList = new ArrayList<HashMap<String, String>>();
		
		for(int x=0; x<CoinList.size(); x++) {
			String Coin_Ticker = CoinList.get(x).get("coin_ticker");
			String Coin_Kor_Name = CoinList.get(x).get("coin_kor_name");
			String Coin_Number = CoinList.get(x).get("coin_number");
			if(!Coin_Number.equals("")) {
				//System.out.println("["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
				String str_url = "https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/"+Coin_Number+"_C0100/1M";
				URL url = new URL(str_url);
				HttpsURLConnection conn = (HttpsURLConnection)url.openConnection(); 
		        conn.setRequestMethod("GET");
		        conn.setConnectTimeout(5000); // 연결 타임아웃 설정 (3초)
		        conn.setReadTimeout(5000); // 읽기 타임아웃 설정 (3초)
		        conn.setUseCaches(false); // 캐싱데이터를 받을지 안받을지
		        conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정
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
		        
		        //System.out.println("["+conn.getResponseCode()+"]["+str_url+"]["+RESPONSE_DATA+"]");
		        
		        if(!RESPONSE_DATA.equals("")) {
		        	JSONObject jObject = new JSONObject(RESPONSE_DATA);
			        //JSONArray jsonArr = (JSONArray)jObject.get("data");
			        if((jObject.getString("status")).equals("0000")) {
			        	JSONObject jMainObject = jObject.getJSONObject("data");
			        	//System.out.println("[성공]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        	
			        	JSONArray jArray_t = jMainObject.getJSONArray("t");
			        	JSONArray jArray_o = jMainObject.getJSONArray("o");
			        	JSONArray jArray_l = jMainObject.getJSONArray("l");
			        	JSONArray jArray_h = jMainObject.getJSONArray("h");
			        	JSONArray jArray_c = jMainObject.getJSONArray("c");
			        	JSONArray jArray_v = jMainObject.getJSONArray("v");
			        	String o_price_5 = "";
			        	String l_price_5 = "";
			        	String h_price_5 = "";
			        	String c_price_5 = "";
			        	String volume_5 = "0";
			        	String gubun_5 = "";
			        	
			        	for (int i = 0; i < jArray_t.length(); i++) {
			        		if(Long.parseLong((jArray_t.get(i)).toString()) >= GIJUN_TIMESTAMP_5) {
			        			String timestamp = (jArray_t.get(i)).toString();
			        			String KST_DATETIME = SDF.format(Long.parseLong(timestamp));
			        			String tmp_o_price = (jArray_o.get(i)).toString();
			        			String tmp_l_price = (jArray_l.get(i)).toString();
			        			String tmp_h_price = (jArray_h.get(i)).toString();
			        			String tmp_c_price = (jArray_c.get(i)).toString();
			        			String tmp_volume = (jArray_v.get(i)).toString();
			        			
			        			if(o_price_5.equals("")) {
			        				o_price_5 = tmp_o_price;
			        			}
			        			
			        			if(l_price_5.equals("") && l_price_5.equals("")) {
			        				l_price_5 = tmp_l_price;
			        				h_price_5 = tmp_h_price;
			        			}
			        			
			        			BigDecimal BD_TMP_L_PRICE = new BigDecimal(tmp_l_price);
			        			BigDecimal BD_TMP_H_PRICE = new BigDecimal(tmp_h_price);
			        			BigDecimal BD_TMP_VOLUME = new BigDecimal(tmp_volume);
			        			BigDecimal BD_L_PRICE_5 = new BigDecimal(l_price_5);
			        			BigDecimal BD_H_PRICE_5 = new BigDecimal(h_price_5);
			        			BigDecimal BD_VOLUME_5 = new BigDecimal(volume_5);
			        			
			        			if(BD_L_PRICE_5.compareTo(BD_TMP_L_PRICE) == 1) {
			        				l_price_5 = tmp_l_price;
			        			}
			        			if(BD_H_PRICE_5.compareTo(BD_TMP_H_PRICE) == -1) {
			        				h_price_5 = tmp_h_price;
			        			}
			        			c_price_5 = tmp_c_price;
			        			BD_VOLUME_5 = BD_VOLUME_5.add(BD_TMP_VOLUME);
			        			volume_5 = BD_VOLUME_5.toString();
			        		}
			        	}
			        	
			        	if(!o_price_5.equals("") && !l_price_5.equals("") && !h_price_5.equals("") && !c_price_5.equals("")) {
			        		if(o_price_5.equals("")) {
			        			o_price_5 = "0";
			        		}
			        		if(c_price_5.equals("")) {
			        			c_price_5 = "0";
			        		}
			        		if(volume_5.equals("")) {
			        			volume_5 = "0";
			        		}
			        		
			        		BigDecimal BD_100 = new BigDecimal("100");
			        		BigDecimal BD_O_PRICE_5 = new BigDecimal(o_price_5);
			        		BigDecimal BD_C_PRICE_5 = new BigDecimal(c_price_5);
			        		BigDecimal BD_VOLUME_5 = new BigDecimal(volume_5);
			        		if(BD_O_PRICE_5.compareTo(BD_C_PRICE_5) == -1) {
			        			gubun_5 = "상승";
			        		} else if(BD_O_PRICE_5.compareTo(BD_C_PRICE_5) == 1) {
			        			gubun_5 = "하락";
			        		} else {
			        			gubun_5 = "보합";
			        		}
			        		
			        		BigDecimal BD_O_C_SUBTRACT_5 = (BD_C_PRICE_5.multiply(new BigDecimal("10000000000")).subtract(BD_O_PRICE_5.multiply(new BigDecimal("10000000000")))).divide(new BigDecimal("10000000000"));
			        		BigDecimal BD_O_C_RATE_5 = ((BD_C_PRICE_5.subtract(BD_O_PRICE_5)).divide(BD_O_PRICE_5, 6, RoundingMode.HALF_UP)).multiply(BD_100);
			        		BigDecimal BD_PRICE_VOLUME_5 = BD_C_PRICE_5.multiply(BD_VOLUME_5);
			        		
			        		String format_c_price_5 = c_price_5;
			        		if(!c_price_5.contains(".") && (new BigDecimal(c_price_5).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			format_c_price_5 = c_price_5.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
			        		String volume_price_5 = (BD_PRICE_VOLUME_5.setScale(0, RoundingMode.FLOOR)).toString();
			        		String format_volume_price_5 = volume_price_5;
			        		if(!volume_price_5.contains(".") && (new BigDecimal(volume_price_5).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			format_volume_price_5 = volume_price_5.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
			        		String o_c_rate_5 = (BD_O_C_RATE_5.setScale(2, RoundingMode.FLOOR)).toString() + '%';
			        		String o_c_subtract_5 = (BD_O_C_SUBTRACT_5.toString());
			        		if(!(BD_O_C_SUBTRACT_5.toString()).contains(".") && (new BigDecimal(BD_O_C_SUBTRACT_5.toString()).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			o_c_subtract_5 = (BD_O_C_SUBTRACT_5.toString()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
				        	HashMap<String, String> map = new HashMap<String, String>();
				        	map.put("CURRENT_DATETIME_KST", CURRENT_DATETIME_KST);
				        	map.put("Coin_Ticker", Coin_Ticker);
			        		map.put("API_Coin_Ticker", Coin_Ticker);
			        		map.put("Coin_Kor_Name", Coin_Kor_Name);
			        		map.put("API_DATETIME_KST", GIJUN_DATETIME_5);
			        		map.put("timestamp", Long.toString(GIJUN_TIMESTAMP_5));
			        		map.put("datetime_kst", GIJUN_DATETIME_5);
			        		map.put("gubun", gubun_5);
			        		map.put("o_price", o_price_5);
			        		map.put("l_price", l_price_5);
			        		map.put("h_price", h_price_5);
			        		map.put("c_price", c_price_5);
			        		map.put("format_c_price", format_c_price_5);
			        		map.put("o_c_rate", o_c_rate_5);
			        		map.put("o_c_subtract", o_c_subtract_5);
			        		map.put("volume", volume_5);
			        		map.put("volume_price", volume_price_5);
			        		map.put("format_volume_price", format_volume_price_5);
			        		APICoinList.add(map);
			        	}
			        	
			        } else {
			        	System.out.println("[실패]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        }
		        }
		        conn.disconnect();
			}
		}
		
		Collections.sort(APICoinList, new Comparator<HashMap<String,String>>(){
			public int compare(HashMap<String,String> map1, HashMap<String,String> map2){
				if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 1) return 1;
                else if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 0) return 0;
                else return -1;
			}
		});
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[빗썸][" + END_DATETIME + "] MIN5 종료");
		return APICoinList;
	}
	
	@GetMapping(path = "/MIN15")
	public List<HashMap<String, String>> MIN15() throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[빗썸][" + START_DATETIME + "] MIN15 시작");
		
		String GIJUN_DATETIME_15 = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date = DateNow;
        String API_HH = HHNow;
        String API_MM_15 = "";
        
        if(Integer.parseInt(MMNow) >= 45) {
        	API_MM_15 = "45";
        } else if(Integer.parseInt(MMNow) >= 30) {
        	API_MM_15 = "30";
        } else if(Integer.parseInt(MMNow) >= 15) {
        	API_MM_15 = "15";
        } else {
        	API_MM_15 = "00";
        }
        
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SDF.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        
		GIJUN_DATETIME_15 = API_Date + " " + API_HH + ":" + API_MM_15+":00";
		Date GIJUN_DATE_15 = SDF.parse(GIJUN_DATETIME_15);
		long GIJUN_TIMESTAMP_15 = GIJUN_DATE_15.getTime();
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = SDF.format(System.currentTimeMillis());
		
		List<HashMap<String, String>> CoinList = MainService.BithumbCoinList();
		List<HashMap<String, String>> APICoinList = new ArrayList<HashMap<String, String>>();
		
		for(int x=0; x<CoinList.size(); x++) {
			String Coin_Ticker = CoinList.get(x).get("coin_ticker");
			String Coin_Kor_Name = CoinList.get(x).get("coin_kor_name");
			String Coin_Number = CoinList.get(x).get("coin_number");
			if(!Coin_Number.equals("")) {
				//System.out.println("["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
				String str_url = "https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/"+Coin_Number+"_C0100/1M";
				URL url = new URL(str_url);
				HttpsURLConnection conn = (HttpsURLConnection)url.openConnection(); 
		        conn.setRequestMethod("GET");
		        conn.setConnectTimeout(5000); // 연결 타임아웃 설정 (3초)
		        conn.setReadTimeout(5000); // 읽기 타임아웃 설정 (3초)
		        conn.setUseCaches(false); // 캐싱데이터를 받을지 안받을지
		        conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정
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
		        
		        //System.out.println("["+conn.getResponseCode()+"]["+str_url+"]["+RESPONSE_DATA+"]");
		        
		        if(!RESPONSE_DATA.equals("")) {
		        	JSONObject jObject = new JSONObject(RESPONSE_DATA);
			        //JSONArray jsonArr = (JSONArray)jObject.get("data");
			        if((jObject.getString("status")).equals("0000")) {
			        	JSONObject jMainObject = jObject.getJSONObject("data");
			        	//System.out.println("[성공]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        	
			        	JSONArray jArray_t = jMainObject.getJSONArray("t");
			        	JSONArray jArray_o = jMainObject.getJSONArray("o");
			        	JSONArray jArray_l = jMainObject.getJSONArray("l");
			        	JSONArray jArray_h = jMainObject.getJSONArray("h");
			        	JSONArray jArray_c = jMainObject.getJSONArray("c");
			        	JSONArray jArray_v = jMainObject.getJSONArray("v");
			        	String o_price_15 = "";
			        	String l_price_15 = "";
			        	String h_price_15 = "";
			        	String c_price_15 = "";
			        	String volume_15 = "0";
			        	String gubun_15 = "";
			        	
			        	for (int i = 0; i < jArray_t.length(); i++) {
			        		if(Long.parseLong((jArray_t.get(i)).toString()) >= GIJUN_TIMESTAMP_15) {
			        			String timestamp = (jArray_t.get(i)).toString();
			        			String KST_DATETIME = SDF.format(Long.parseLong(timestamp));
			        			String tmp_o_price = (jArray_o.get(i)).toString();
			        			String tmp_l_price = (jArray_l.get(i)).toString();
			        			String tmp_h_price = (jArray_h.get(i)).toString();
			        			String tmp_c_price = (jArray_c.get(i)).toString();
			        			String tmp_volume = (jArray_v.get(i)).toString();
			        			
			        			if(o_price_15.equals("")) {
			        				o_price_15 = tmp_o_price;
			        			}
			        			
			        			if(l_price_15.equals("") && l_price_15.equals("")) {
			        				l_price_15 = tmp_l_price;
			        				h_price_15 = tmp_h_price;
			        			}
			        			
			        			BigDecimal BD_TMP_L_PRICE = new BigDecimal(tmp_l_price);
			        			BigDecimal BD_TMP_H_PRICE = new BigDecimal(tmp_h_price);
			        			BigDecimal BD_TMP_VOLUME = new BigDecimal(tmp_volume);
			        			BigDecimal BD_L_PRICE_15 = new BigDecimal(l_price_15);
			        			BigDecimal BD_H_PRICE_15 = new BigDecimal(h_price_15);
			        			BigDecimal BD_VOLUME_15 = new BigDecimal(volume_15);
			        			
			        			if(BD_L_PRICE_15.compareTo(BD_TMP_L_PRICE) == 1) {
			        				l_price_15 = tmp_l_price;
			        			}
			        			if(BD_H_PRICE_15.compareTo(BD_TMP_H_PRICE) == -1) {
			        				h_price_15 = tmp_h_price;
			        			}
			        			c_price_15 = tmp_c_price;
			        			BD_VOLUME_15 = BD_VOLUME_15.add(BD_TMP_VOLUME);
			        			volume_15 = BD_VOLUME_15.toString();
			        		}
			        	}
			        	
			        	if(!o_price_15.equals("") && !l_price_15.equals("") && !h_price_15.equals("") && !c_price_15.equals("")) {
			        		if(o_price_15.equals("")) {
			        			o_price_15 = "0";
			        		}
			        		if(c_price_15.equals("")) {
			        			c_price_15 = "0";
			        		}
			        		if(volume_15.equals("")) {
			        			volume_15 = "0";
			        		}
			        		
			        		BigDecimal BD_100 = new BigDecimal("100");
			        		BigDecimal BD_O_PRICE_15 = new BigDecimal(o_price_15);
			        		BigDecimal BD_C_PRICE_15 = new BigDecimal(c_price_15);
			        		BigDecimal BD_VOLUME_15 = new BigDecimal(volume_15);
			        		
			        		if(BD_O_PRICE_15.compareTo(BD_C_PRICE_15) == -1) {
			        			gubun_15 = "상승";
			        		} else if(BD_O_PRICE_15.compareTo(BD_C_PRICE_15) == 1) {
			        			gubun_15 = "하락";
			        		} else {
			        			gubun_15 = "보합";
			        		}
			        		
			        		BigDecimal BD_O_C_SUBTRACT_15 = (BD_C_PRICE_15.multiply(new BigDecimal("10000000000")).subtract(BD_O_PRICE_15.multiply(new BigDecimal("10000000000")))).divide(new BigDecimal("10000000000"));
			        		BigDecimal BD_O_C_RATE_15 = ((BD_C_PRICE_15.subtract(BD_O_PRICE_15)).divide(BD_O_PRICE_15, 6, RoundingMode.HALF_UP)).multiply(BD_100);
			        		BigDecimal BD_PRICE_VOLUME_15 = BD_C_PRICE_15.multiply(BD_VOLUME_15);
			        		
			        		String format_c_price_15 = c_price_15;
			        		if(!c_price_15.contains(".") && (new BigDecimal(c_price_15).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			format_c_price_15 = c_price_15.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
			        		String volume_price_15 = (BD_PRICE_VOLUME_15.setScale(0, RoundingMode.FLOOR)).toString();
			        		String format_volume_price_15 = volume_price_15;
			        		if(!volume_price_15.contains(".") && (new BigDecimal(volume_price_15).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			format_volume_price_15 = volume_price_15.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
			        		String o_c_rate_15 = (BD_O_C_RATE_15.setScale(2, RoundingMode.FLOOR)).toString() + '%';
			        		String o_c_subtract_15 = (BD_O_C_SUBTRACT_15.toString());
			        		if(!(BD_O_C_SUBTRACT_15.toString()).contains(".") && (new BigDecimal(BD_O_C_SUBTRACT_15.toString()).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			o_c_subtract_15 = (BD_O_C_SUBTRACT_15.toString()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
				        	HashMap<String, String> map = new HashMap<String, String>();
				        	map.put("CURRENT_DATETIME_KST", CURRENT_DATETIME_KST);
				        	map.put("Coin_Ticker", Coin_Ticker);
			        		map.put("API_Coin_Ticker", Coin_Ticker);
			        		map.put("Coin_Kor_Name", Coin_Kor_Name);
			        		map.put("API_DATETIME_KST", GIJUN_DATETIME_15);
			        		map.put("timestamp", Long.toString(GIJUN_TIMESTAMP_15));
			        		map.put("datetime_kst", GIJUN_DATETIME_15);
			        		map.put("gubun", gubun_15);
			        		map.put("o_price", o_price_15);
			        		map.put("l_price", l_price_15);
			        		map.put("h_price", h_price_15);
			        		map.put("c_price", c_price_15);
			        		map.put("format_c_price", format_c_price_15);
			        		map.put("o_c_rate", o_c_rate_15);
			        		map.put("o_c_subtract", o_c_subtract_15);
			        		map.put("volume", volume_15);
			        		map.put("volume_price", volume_price_15);
			        		map.put("format_volume_price", format_volume_price_15);
			        		APICoinList.add(map);
			        	}
			        	
			        } else {
			        	System.out.println("[실패]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        }
		        }
		        conn.disconnect();
			}
		}
		
		Collections.sort(APICoinList, new Comparator<HashMap<String,String>>(){
			public int compare(HashMap<String,String> map1, HashMap<String,String> map2){
				if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 1) return 1;
                else if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 0) return 0;
                else return -1;
			}
		});
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[빗썸][" + END_DATETIME + "] MIN15 종료");
		return APICoinList;
	}
	
	@GetMapping(path = "/MIN60")
	public List<HashMap<String, String>> MIN60() throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[빗썸][" + START_DATETIME + "] MIN60 시작");
		
		String GIJUN_DATETIME_60 = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date_60 = DateNow;
        String API_HH_60 = HHNow;
        String API_MM = "00";
        
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SDF.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        
        GIJUN_DATETIME_60 = API_Date_60 + " " + API_HH_60 + ":" + API_MM+":00";
		Date GIJUN_DATE_60 = SDF.parse(GIJUN_DATETIME_60);
		long GIJUN_TIMESTAMP_60 = GIJUN_DATE_60.getTime();
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = SDF.format(System.currentTimeMillis());
		
		List<HashMap<String, String>> CoinList = MainService.BithumbCoinList();
		List<HashMap<String, String>> APICoinList = new ArrayList<HashMap<String, String>>();
		
		for(int x=0; x<CoinList.size(); x++) {
			String Coin_Ticker = CoinList.get(x).get("coin_ticker");
			String Coin_Kor_Name = CoinList.get(x).get("coin_kor_name");
			String Coin_Number = CoinList.get(x).get("coin_number");
			if(!Coin_Number.equals("")) {
				//System.out.println("["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
				String str_url = "https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/"+Coin_Number+"_C0100/1H";
				URL url = new URL(str_url);
				HttpsURLConnection conn = (HttpsURLConnection)url.openConnection(); 
		        conn.setRequestMethod("GET");
		        conn.setConnectTimeout(5000); // 연결 타임아웃 설정 (3초)
		        conn.setReadTimeout(5000); // 읽기 타임아웃 설정 (3초)
		        conn.setUseCaches(false); // 캐싱데이터를 받을지 안받을지
		        conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정
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
		        
		        //System.out.println("[RESPONSE_DATA]["+str_url+"]["+RESPONSE_DATA+"]");
		        
		        if(!RESPONSE_DATA.equals("")) {
		        	JSONObject jObject = new JSONObject(RESPONSE_DATA);
			        //JSONArray jsonArr = (JSONArray)jObject.get("data");
			        if((jObject.getString("status")).equals("0000")) {
			        	JSONObject jMainObject = jObject.getJSONObject("data");
			        	//System.out.println("[성공]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        	
			        	JSONArray jArray_t = jMainObject.getJSONArray("t");
			        	JSONArray jArray_o = jMainObject.getJSONArray("o");
			        	JSONArray jArray_l = jMainObject.getJSONArray("l");
			        	JSONArray jArray_h = jMainObject.getJSONArray("h");
			        	JSONArray jArray_c = jMainObject.getJSONArray("c");
			        	JSONArray jArray_v = jMainObject.getJSONArray("v");
			        	String o_price_60 = "";
			        	String l_price_60 = "";
			        	String h_price_60 = "";
			        	String c_price_60 = "";
			        	String volume_60 = "0";
			        	String gubun_60 = "";
			        	
			        	for (int i = 0; i < jArray_t.length(); i++) {
			        		if(Long.parseLong((jArray_t.get(i)).toString()) >= GIJUN_TIMESTAMP_60) {
			        			String timestamp = (jArray_t.get(i)).toString();
			        			String KST_DATETIME = SDF.format(Long.parseLong(timestamp));
			        			String tmp_o_price = (jArray_o.get(i)).toString();
			        			String tmp_l_price = (jArray_l.get(i)).toString();
			        			String tmp_h_price = (jArray_h.get(i)).toString();
			        			String tmp_c_price = (jArray_c.get(i)).toString();
			        			String tmp_volume = (jArray_v.get(i)).toString();
			        			
			        			if(o_price_60.equals("")) {
			        				o_price_60 = tmp_o_price;
			        			}
			        			
			        			if(l_price_60.equals("") && l_price_60.equals("")) {
			        				l_price_60 = tmp_l_price;
			        				h_price_60 = tmp_h_price;
			        			}
			        			
			        			BigDecimal BD_TMP_L_PRICE = new BigDecimal(tmp_l_price);
			        			BigDecimal BD_TMP_H_PRICE = new BigDecimal(tmp_h_price);
			        			BigDecimal BD_TMP_VOLUME = new BigDecimal(tmp_volume);
			        			BigDecimal BD_L_PRICE_60 = new BigDecimal(l_price_60);
			        			BigDecimal BD_H_PRICE_60 = new BigDecimal(h_price_60);
			        			BigDecimal BD_VOLUME_60 = new BigDecimal(volume_60);
			        			
			        			if(BD_L_PRICE_60.compareTo(BD_TMP_L_PRICE) == 1) {
			        				l_price_60 = tmp_l_price;
			        			}
			        			if(BD_H_PRICE_60.compareTo(BD_TMP_H_PRICE) == -1) {
			        				h_price_60 = tmp_h_price;
			        			}
			        			c_price_60 = tmp_c_price;
			        			BD_VOLUME_60 = BD_VOLUME_60.add(BD_TMP_VOLUME);
			        			volume_60 = BD_VOLUME_60.toString();
			        		}
			        	}
			        	
			        	if(!o_price_60.equals("") && !l_price_60.equals("") && !h_price_60.equals("") && !c_price_60.equals("")) {
			        		if(o_price_60.equals("")) {
			        			o_price_60 = "0";
			        		}
			        		if(c_price_60.equals("")) {
			        			c_price_60 = "0";
			        		}
			        		if(volume_60.equals("")) {
			        			volume_60 = "0";
			        		}
			        		
			        		BigDecimal BD_100 = new BigDecimal("100");
			        		BigDecimal BD_O_PRICE_60 = new BigDecimal(o_price_60);
			        		BigDecimal BD_C_PRICE_60 = new BigDecimal(c_price_60);
			        		BigDecimal BD_VOLUME_60 = new BigDecimal(volume_60);
			        		if(BD_O_PRICE_60.compareTo(BD_C_PRICE_60) == -1) {
			        			gubun_60 = "상승";
			        		} else if(BD_O_PRICE_60.compareTo(BD_C_PRICE_60) == 1) {
			        			gubun_60 = "하락";
			        		} else {
			        			gubun_60 = "보합";
			        		}
			        		
			        		BigDecimal BD_O_C_SUBTRACT_60 = (BD_C_PRICE_60.multiply(new BigDecimal("10000000000")).subtract(BD_O_PRICE_60.multiply(new BigDecimal("10000000000")))).divide(new BigDecimal("10000000000"));
			        		BigDecimal BD_O_C_RATE_60 = ((BD_C_PRICE_60.subtract(BD_O_PRICE_60)).divide(BD_O_PRICE_60, 6, RoundingMode.HALF_UP)).multiply(BD_100);
			        		BigDecimal BD_PRICE_VOLUME_60 = BD_C_PRICE_60.multiply(BD_VOLUME_60);
			        		
			        		String format_c_price_60 = c_price_60;
			        		if(!c_price_60.contains(".") && (new BigDecimal(c_price_60).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			format_c_price_60 = c_price_60.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
			        		String volume_price_60 = (BD_PRICE_VOLUME_60.setScale(0, RoundingMode.FLOOR)).toString();
			        		String format_volume_price_60 = volume_price_60;
			        		if(!volume_price_60.contains(".") && (new BigDecimal(volume_price_60).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			format_volume_price_60 = volume_price_60.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
			        		String o_c_rate_60 = (BD_O_C_RATE_60.setScale(2, RoundingMode.FLOOR)).toString() + '%';
			        		String o_c_subtract_60 = (BD_O_C_SUBTRACT_60.toString());
			        		if(!(BD_O_C_SUBTRACT_60.toString()).contains(".") && (new BigDecimal(BD_O_C_SUBTRACT_60.toString()).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			o_c_subtract_60 = (BD_O_C_SUBTRACT_60.toString()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
				        	HashMap<String, String> map = new HashMap<String, String>();
				        	map.put("CURRENT_DATETIME_KST", CURRENT_DATETIME_KST);
				        	map.put("Coin_Ticker", Coin_Ticker);
			        		map.put("API_Coin_Ticker", Coin_Ticker);
			        		map.put("Coin_Kor_Name", Coin_Kor_Name);
			        		map.put("API_DATETIME_KST", GIJUN_DATETIME_60);
			        		map.put("timestamp", Long.toString(GIJUN_TIMESTAMP_60));
			        		map.put("datetime_kst", GIJUN_DATETIME_60);
			        		map.put("gubun", gubun_60);
			        		map.put("o_price", o_price_60);
			        		map.put("l_price", l_price_60);
			        		map.put("h_price", h_price_60);
			        		map.put("c_price", c_price_60);
			        		map.put("format_c_price", format_c_price_60);
			        		map.put("o_c_rate", o_c_rate_60);
			        		map.put("o_c_subtract", o_c_subtract_60);
			        		map.put("volume", volume_60);
			        		map.put("volume_price", volume_price_60);
			        		map.put("format_volume_price", format_volume_price_60);
			        		APICoinList.add(map);
			        	}
			        	
			        } else {
			        	System.out.println("[실패]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        }
		        }
		        conn.disconnect();
			}
		}
		
		Collections.sort(APICoinList, new Comparator<HashMap<String,String>>(){
			public int compare(HashMap<String,String> map1, HashMap<String,String> map2){
				if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 1) return 1;
                else if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 0) return 0;
                else return -1;
			}
		});
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[빗썸][" + END_DATETIME + "] MIN60 종료");
		return APICoinList;
	}
	
	@GetMapping(path = "/MIN240")
	public List<HashMap<String, String>> MIN240() throws Exception{
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[빗썸][" + START_DATETIME + "] MIN240 시작");
		
		String GIJUN_DATETIME_60 = "";
		String GIJUN_DATETIME_240 = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date_240 = DateNow;
        String API_HH_240 = "";
        String API_MM = "00";
        
        if(Integer.parseInt(HHNow) >= 20) {
        	API_HH_240 = "20";
        } else if(Integer.parseInt(HHNow) >= 16) {
        	API_HH_240 = "16";
        } else if(Integer.parseInt(HHNow) >= 12) {
        	API_HH_240 = "12";
        } else if(Integer.parseInt(HHNow) >= 8) {
        	API_HH_240 = "08";
        } else if(Integer.parseInt(HHNow) >= 4) {
        	API_HH_240 = "04";
        } else if(Integer.parseInt(HHNow) >= 0) {
        	API_HH_240 = "00";
        } 
        
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SDF.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        
		GIJUN_DATETIME_240 = API_Date_240 + " " + API_HH_240 + ":" + API_MM+":00";
		Date GIJUN_DATE_240 = SDF.parse(GIJUN_DATETIME_240);
		long GIJUN_TIMESTAMP_240 = GIJUN_DATE_240.getTime();
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = SDF.format(System.currentTimeMillis());
		
		List<HashMap<String, String>> CoinList = MainService.BithumbCoinList();
		List<HashMap<String, String>> APICoinList = new ArrayList<HashMap<String, String>>();
		
		for(int x=0; x<CoinList.size(); x++) {
			String Coin_Ticker = CoinList.get(x).get("coin_ticker");
			String Coin_Kor_Name = CoinList.get(x).get("coin_kor_name");
			String Coin_Number = CoinList.get(x).get("coin_number");
			if(!Coin_Number.equals("")) {
				//System.out.println("["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
				String str_url = "https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/"+Coin_Number+"_C0100/1H";
				URL url = new URL(str_url);
				HttpsURLConnection conn = (HttpsURLConnection)url.openConnection(); 
		        conn.setRequestMethod("GET");
		        conn.setConnectTimeout(5000); // 연결 타임아웃 설정 (3초)
		        conn.setReadTimeout(5000); // 읽기 타임아웃 설정 (3초)
		        conn.setUseCaches(false); // 캐싱데이터를 받을지 안받을지
		        conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정
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
		        
		        //System.out.println("[RESPONSE_DATA]["+str_url+"]["+RESPONSE_DATA+"]");
		        
		        if(!RESPONSE_DATA.equals("")) {
		        	JSONObject jObject = new JSONObject(RESPONSE_DATA);
			        //JSONArray jsonArr = (JSONArray)jObject.get("data");
			        if((jObject.getString("status")).equals("0000")) {
			        	JSONObject jMainObject = jObject.getJSONObject("data");
			        	//System.out.println("[성공]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        	
			        	JSONArray jArray_t = jMainObject.getJSONArray("t");
			        	JSONArray jArray_o = jMainObject.getJSONArray("o");
			        	JSONArray jArray_l = jMainObject.getJSONArray("l");
			        	JSONArray jArray_h = jMainObject.getJSONArray("h");
			        	JSONArray jArray_c = jMainObject.getJSONArray("c");
			        	JSONArray jArray_v = jMainObject.getJSONArray("v");
			        	String o_price_240 = "";
			        	String l_price_240 = "";
			        	String h_price_240 = "";
			        	String c_price_240 = "";
			        	String volume_240 = "0";
			        	String gubun_240 = "";
			        	
			        	for (int i = 0; i < jArray_t.length(); i++) {
			        		if(Long.parseLong((jArray_t.get(i)).toString()) >= GIJUN_TIMESTAMP_240) {
			        			String timestamp = (jArray_t.get(i)).toString();
			        			String KST_DATETIME = SDF.format(Long.parseLong(timestamp));
			        			String tmp_o_price = (jArray_o.get(i)).toString();
			        			String tmp_l_price = (jArray_l.get(i)).toString();
			        			String tmp_h_price = (jArray_h.get(i)).toString();
			        			String tmp_c_price = (jArray_c.get(i)).toString();
			        			String tmp_volume = (jArray_v.get(i)).toString();
			        			
			        			if(o_price_240.equals("")) {
			        				o_price_240 = tmp_o_price;
			        			}
			        			
			        			if(l_price_240.equals("") && l_price_240.equals("")) {
			        				l_price_240 = tmp_l_price;
			        				h_price_240 = tmp_h_price;
			        			}
			        			
			        			BigDecimal BD_TMP_L_PRICE = new BigDecimal(tmp_l_price);
			        			BigDecimal BD_TMP_H_PRICE = new BigDecimal(tmp_h_price);
			        			BigDecimal BD_TMP_VOLUME = new BigDecimal(tmp_volume);
			        			BigDecimal BD_L_PRICE_240 = new BigDecimal(l_price_240);
			        			BigDecimal BD_H_PRICE_240 = new BigDecimal(h_price_240);
			        			BigDecimal BD_VOLUME_240 = new BigDecimal(volume_240);
			        			
			        			if(BD_L_PRICE_240.compareTo(BD_TMP_L_PRICE) == 1) {
			        				l_price_240 = tmp_l_price;
			        			}
			        			if(BD_H_PRICE_240.compareTo(BD_TMP_H_PRICE) == -1) {
			        				h_price_240 = tmp_h_price;
			        			}
			        			c_price_240 = tmp_c_price;
			        			BD_VOLUME_240 = BD_VOLUME_240.add(BD_TMP_VOLUME);
			        			volume_240 = BD_VOLUME_240.toString();
			        		}
			        	}
			        	
			        	if(!o_price_240.equals("") && !l_price_240.equals("") && !h_price_240.equals("") && !c_price_240.equals("")) {
			        		if(o_price_240.equals("")) {
			        			o_price_240 = "0";
			        		}
			        		if(c_price_240.equals("")) {
			        			c_price_240 = "0";
			        		}
			        		if(volume_240.equals("")) {
			        			volume_240 = "0";
			        		}
			        		
			        		BigDecimal BD_100 = new BigDecimal("100");
			        		BigDecimal BD_O_PRICE_240 = new BigDecimal(o_price_240);
			        		BigDecimal BD_C_PRICE_240 = new BigDecimal(c_price_240);
			        		BigDecimal BD_VOLUME_240 = new BigDecimal(volume_240);
			        		
			        		if(BD_O_PRICE_240.compareTo(BD_C_PRICE_240) == -1) {
			        			gubun_240 = "상승";
			        		} else if(BD_O_PRICE_240.compareTo(BD_C_PRICE_240) == 1) {
			        			gubun_240 = "하락";
			        		} else {
			        			gubun_240 = "보합";
			        		}
			        		
			        		BigDecimal BD_O_C_SUBTRACT_240 = (BD_C_PRICE_240.multiply(new BigDecimal("10000000000")).subtract(BD_O_PRICE_240.multiply(new BigDecimal("10000000000")))).divide(new BigDecimal("10000000000"));
			        		BigDecimal BD_O_C_RATE_240 = ((BD_C_PRICE_240.subtract(BD_O_PRICE_240)).divide(BD_O_PRICE_240, 6, RoundingMode.HALF_UP)).multiply(BD_100);
			        		BigDecimal BD_PRICE_VOLUME_240 = BD_C_PRICE_240.multiply(BD_VOLUME_240);
			        		
			        		String format_c_price_240 = c_price_240;
			        		if(!c_price_240.contains(".") && (new BigDecimal(c_price_240).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			format_c_price_240 = c_price_240.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
			        		String volume_price_240 = (BD_PRICE_VOLUME_240.setScale(0, RoundingMode.FLOOR)).toString();
			        		String format_volume_price_240 = volume_price_240;
			        		if(!volume_price_240.contains(".") && (new BigDecimal(volume_price_240).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			format_volume_price_240 = volume_price_240.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
			        		String o_c_rate_240 = (BD_O_C_RATE_240.setScale(2, RoundingMode.FLOOR)).toString() + '%';
			        		String o_c_subtract_240 = (BD_O_C_SUBTRACT_240.toString());
		        			if(!(BD_O_C_SUBTRACT_240.toString()).contains(".") && (new BigDecimal(BD_O_C_SUBTRACT_240.toString()).abs()).compareTo(new BigDecimal("999")) == 1) {
			        			o_c_subtract_240 = (BD_O_C_SUBTRACT_240.toString()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			        		}
			        		
				        	HashMap<String, String> map = new HashMap<String, String>();
				        	map.put("CURRENT_DATETIME_KST", CURRENT_DATETIME_KST);
				        	map.put("Coin_Ticker", Coin_Ticker);
			        		map.put("API_Coin_Ticker", Coin_Ticker);
			        		map.put("Coin_Kor_Name", Coin_Kor_Name);
			        		map.put("API_DATETIME_KST", GIJUN_DATETIME_240);
			        		map.put("timestamp", Long.toString(GIJUN_TIMESTAMP_240));
			        		map.put("datetime_kst", GIJUN_DATETIME_240);
			        		map.put("gubun", gubun_240);
			        		map.put("o_price", o_price_240);
			        		map.put("l_price", l_price_240);
			        		map.put("h_price", h_price_240);
			        		map.put("c_price", c_price_240);
			        		map.put("format_c_price", format_c_price_240);
			        		map.put("o_c_rate", o_c_rate_240);
			        		map.put("o_c_subtract", o_c_subtract_240);
			        		map.put("volume", volume_240);
			        		map.put("volume_price", volume_price_240);
			        		map.put("format_volume_price", format_volume_price_240);
			        		APICoinList.add(map);
			        	}
			        	
			        } else {
			        	System.out.println("[실패]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        }
		        }
		        conn.disconnect();
			}
		}
		
		Collections.sort(APICoinList, new Comparator<HashMap<String,String>>(){
			public int compare(HashMap<String,String> map1, HashMap<String,String> map2){
				if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 1) return 1;
                else if((new BigDecimal(map2.get("volume_price"))).compareTo(new BigDecimal(map1.get("volume_price"))) == 0) return 0;
                else return -1;
			}
		});
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[빗썸][" + END_DATETIME + "] MIN240 종료");
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