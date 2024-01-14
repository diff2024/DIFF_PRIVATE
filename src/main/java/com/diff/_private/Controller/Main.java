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
import com.diff._private.Service.CoinService;
import com.diff._private.Service.MainService;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/Main")
public class Main {
	
	@Autowired
	CoinService CoinService;
	
	@Autowired
	MainService MainService;
	
	@GetMapping(path = "/MainTest")
	public void MainTest() throws Exception{
	    String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[" + START_DATETIME + "] MainTest 시작");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		long TIMESTMAP = System.currentTimeMillis();
		String DATETIME_KST = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println(TIMESTMAP);
        System.out.println(DATETIME_KST);
        
        // 5분전
        long TIMESTMAP_5 = TIMESTMAP - (60000*5);
		String DATETIME_KST_5 = simpleDateFormat.format(TIMESTMAP_5);
        System.out.println("[TIMESTMAP_5] " + TIMESTMAP_5);
        System.out.println("[DATETIME_KST_5] " + DATETIME_KST_5);
        
        // 15분전
        long TIMESTMAP_15 = TIMESTMAP - (60000*15);
		String DATETIME_KST_15 = simpleDateFormat.format(TIMESTMAP_15);
        System.out.println("[TIMESTMAP_15] " + TIMESTMAP_15);
        System.out.println("[DATETIME_KST_15] " + DATETIME_KST_15);
        
        // 60분전
        long TIMESTMAP_60 = TIMESTMAP - (60000*60);
		String DATETIME_KST_60 = simpleDateFormat.format(TIMESTMAP_60);
        System.out.println("[TIMESTMAP_60] " + TIMESTMAP_60);
        System.out.println("[DATETIME_KST_60] " + DATETIME_KST_60);
        
        // 240분전
        long TIMESTMAP_240 = TIMESTMAP - (60000*240);
		String DATETIME_KST_240 = simpleDateFormat.format(TIMESTMAP_240);
        System.out.println("[TIMESTMAP_240] " + TIMESTMAP_240);
        System.out.println("[DATETIME_KST_240] " + DATETIME_KST_240);
        
        // 600분전
        long TIMESTMAP_600 = TIMESTMAP - (60000*600);
		String DATETIME_KST_600 = simpleDateFormat.format(TIMESTMAP_600);
        System.out.println("[TIMESTMAP_600] " + TIMESTMAP_600);
        System.out.println("[DATETIME_KST_600] " + DATETIME_KST_600);
        
        String FROM_TIMESTAMP = (Long.toString(TIMESTMAP_600)).substring(0, 10);
        String TO_TIMESTAMP = (Long.toString(TIMESTMAP)).substring(0, 10);
        
        
		//URL url = new URL("https://crix-api-tv.upbit.com/v1/crix/tradingview/history?symbol=TFUELKRW&resolution=60&from=1704871966&to=1704894026");
        //URL url = new URL("https://crix-api-tv.upbit.com/v1/crix/tradingview/history?symbol=TFUELKRW&resolution=5&from="+FROM_TIMESTAMP+"&to="+TO_TIMESTAMP);
        //URL url = new URL("https://crix-api-tv.upbit.com/v1/crix/tradingview/history?symbol=TFUELKRW&resolution=15&from="+FROM_TIMESTAMP+"&to="+TO_TIMESTAMP);
        //URL url = new URL("https://crix-api-tv.upbit.com/v1/crix/tradingview/history?symbol=TFUELKRW&resolution=60&from="+FROM_TIMESTAMP+"&to="+TO_TIMESTAMP);
        URL url = new URL("https://crix-api-tv.upbit.com/v1/crix/tradingview/history?symbol=TFUELKRW&resolution=240&from="+FROM_TIMESTAMP+"&to="+TO_TIMESTAMP);
        
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
        System.out.println("=========RESPONSE_DATA=========");
        System.out.println(RESPONSE_DATA);
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
        		String datetime_utc = simpleDateFormat.format(new Date(Long.parseLong(timestamp) - (60000*540)));
        		
        		String o_price = (jArray_o.get(i)).toString();
        		String l_price = (jArray_l.get(i)).toString();
        		String h_price = (jArray_h.get(i)).toString();
        		String c_price = (jArray_c.get(i)).toString();
        		String volume = (jArray_v.get(i)).toString();
        		
        		BigDecimal BI_C_PRICE = new BigDecimal(c_price);
        		BigDecimal BI_VOLUME = new BigDecimal(volume);
        		BigDecimal BI_PRICE_VOLUME = BI_C_PRICE.multiply(BI_VOLUME);
        		
        		String volume_price = (BI_PRICE_VOLUME.setScale(0, RoundingMode.FLOOR)).toString();
        		
        		System.out.println("===============================================================");
        		System.out.println("> TIMESTAMP : " + timestamp);
        		System.out.println("> DATETIME KST : " + datetime_kst);
        		System.out.println("> DATETIME UTC : " + datetime_utc);
        		System.out.println("> 시가 : " + o_price);
        		System.out.println("> 저가 : " + l_price);
        		System.out.println("> 고가 : " + h_price);
        		System.out.println("> 종가 : " + c_price);
        		System.out.println("> 거래량 : " + volume);
        		System.out.println("> 거래대금 : " + volume_price);
        	}
        }
        

        /*
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(RESPONSE_DATA);
        System.out.println(obj);
        JSONObject jsonMain = (JSONObject) obj;
        */
        conn.disconnect();
		
        /*
        JSONParser jsonParser = new JSONParser();
		JSONObject resultJsonObj = new JSONObject(); //최초 깊이 1의 제이슨 객체
		resultJsonObj = (JSONObject) jsonParser.parse(RESPONSE_DATA);
		System.out.println(resultJsonObj.toString());
		
		System.out.println("----------------------------------------");
		JSONObject resultJsonObj2 = (JSONObject) resultJsonObj.get("s"); //깊이 2의 제이슨 객체
		System.out.println(resultJsonObj2);
		*/
        
		/*
		JSONArray resultJsonArray = new JSONArray(); //깊이 2에 존재하는 제이슨 배열을 가져올 객체
		resultJsonArray = (JSONArray) resultJsonObj2.get("jsonList"); 	
		
		//배열에 있는 제이슨 객체를 받을 임시 제이슨 객체
		JSONObject tempJson = new JSONObject();
		for (int i = 0; i < resultJsonArray.size(); i++) { //배열에 있는 제이슨 수많큼 반복한다.
			tempJson = (JSONObject) resultJsonArray.get(i);	
			System.out.println(tempJson.toString());
		}
		*/
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[" + END_DATETIME + "] MainTest 종료");
	}
	
	@GetMapping(path = "/Main_5MIN")
	public void Main_5MIN() throws Exception{
		List<HashMap<String, String>> CoinList = MainService.UpbitCoinList();
		
		for(int x=0; x<CoinList.size(); x++) {
			System.out.println("> CoinTicker : " + CoinList.get(x).get("coin_ticker"));
			System.out.println("> APICoinTicker : " + CoinList.get(x).get("api_coin_ticker"));
		}
	}
	
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
	
	@GetMapping(path = "/BYBIT_BTC_HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> BYBIT_BTC_HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BYBIT_BTC_HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/BYBIT_BTC_4HOUR_Ticker_Comparison")
	public List<HashMap<String, String>> BYBIT_BTC_4HOUR_Ticker_Comparison(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BYBIT_BTC_4HOUR_Ticker_Comparison(map);
		return list;
	}
	
	@GetMapping(path = "/BYBIT_DAY_RANKING")
	public List<HashMap<String, String>> BYBIT_DAY_RANKING(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
			day.add(Calendar.HOUR, -9); // UTC 기준
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		List<HashMap<String, String>> list = MainService.BYBIT_DAY_RANKING(map);
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