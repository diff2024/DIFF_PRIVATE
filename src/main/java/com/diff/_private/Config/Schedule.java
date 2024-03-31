package com.diff._private.Config;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.diff._private.Controller.CoinController;
import com.diff._private.Service.MainService;
import com.diff._private.Service.CoinService;
import com.diff._private.Service.BitCoinService;
import com.diff._private.Service.BinanceCoinService;
import com.diff._private.Service.BinanceFuturesCoinService;
import com.diff._private.Service.BybitCoinService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Schedule {
	@Autowired
	MainService MainService;
	
	@Autowired
	CoinService UpbitCoinService;
	
	@Autowired
	BitCoinService BitCoinService;
	
	@Autowired
	BinanceCoinService BinanceCoinService;
	
	@Autowired
	BinanceFuturesCoinService BinanceFuturesCoinService;
	
	@Autowired
	BybitCoinService BybitCoinService;
	
	/*
	@Scheduled(cron = "55 0/5 * * * *")
    public void CointInfoTextList() throws Exception {
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[CointInfoTextList-시작][" + START_DATETIME + "] 시작");
		
		List<HashMap<String, String>> UpbitCoinList = MainService.UpbitCoinList();
		List<HashMap<String, String>> BithumbCoinList = MainService.BithumbCoinList();
		
		String BithumbText = "";
		String UpbitText = "";
		
		for(int x=0; x<BithumbCoinList.size(); x++) {
			String coin_ticker = BithumbCoinList.get(x).get("coin_ticker");
			String coin_kor_name = BithumbCoinList.get(x).get("coin_kor_name");
			String coin_number = BithumbCoinList.get(x).get("coin_number");
			
			if(BithumbText.equals("")) {
				BithumbText += coin_ticker+"▦"+coin_kor_name+"▦"+coin_number;
			} else {
				BithumbText += "■"+coin_ticker+"▦"+coin_kor_name+"▦"+coin_number;
			}
		}
		
		for(int x=0; x<UpbitCoinList.size(); x++) {
			String coin_ticker = UpbitCoinList.get(x).get("coin_ticker");
			String api_coin_ticker = UpbitCoinList.get(x).get("api_coin_ticker");
			String coin_kor_name = UpbitCoinList.get(x).get("coin_kor_name");
			
			if(UpbitText.equals("")) {
				UpbitText += coin_ticker+"▦"+api_coin_ticker+"▦"+coin_kor_name;
			} else {
				UpbitText += "■"+coin_ticker+"▦"+api_coin_ticker+"▦"+coin_kor_name;
			}
		}
		
		String Path = "C:\\Coin";
        String BithumbFileName = "BithumbCointInfo.txt";
        String UpbitFileName = "UpbitCointInfo.txt";
        
        File BithumbFile = new File(Path, BithumbFileName);
        File UpbitFile = new File(Path, UpbitFileName);
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(BithumbFile));
            writer.write(BithumbText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(UpbitFile));
            writer.write(UpbitText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[CointInfoTextList-종료][" + END_DATETIME + "] 종료");
	}
	
	@Scheduled(cron = "0/20 * * * * *")
    public void LiveBithumbData_1M() throws Exception {
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[LiveBithumbData_1M][" + START_DATETIME + "] 시작");
		
		String BithumbText = "";
		String GIJUN_DATETIME_5 = "";
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
        String API_MM_5 = "";
        String API_MM_15 = "";
        
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
        
        // 현재
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SDF.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        long TIMESTMAP = System.currentTimeMillis();
 		String CURRENT_DATETIME_KST = SDF.format(System.currentTimeMillis());
 		
        GIJUN_DATETIME_5 = API_Date + " " + API_HH + ":" + API_MM_5+":00";
		Date GIJUN_DATE_5 = SDF.parse(GIJUN_DATETIME_5);
		long GIJUN_TIMESTAMP_5 = GIJUN_DATE_5.getTime();
		
		GIJUN_DATETIME_15 = API_Date + " " + API_HH + ":" + API_MM_15+":00";
		Date GIJUN_DATE_15 = SDF.parse(GIJUN_DATETIME_15);
		long GIJUN_TIMESTAMP_15 = GIJUN_DATE_15.getTime();
		
		List<HashMap<String, String>> CoinList = new ArrayList<HashMap<String, String>>();
		
		BufferedReader FileReader = new BufferedReader(new FileReader("C:\\Coin\\BithumbCointInfo.txt"));
		String reader_str = "";
		while ((reader_str = FileReader.readLine()) != null) {
			String[] ARRAY_STRING = reader_str.split("■");
		    for (String STRING : ARRAY_STRING){
		    	String[] FULL_ARRAY_STRING = STRING.split("▦");
		    	
		    	HashMap<String, String> map = new HashMap<String, String>();
		    	map.put("coin_ticker", FULL_ARRAY_STRING[0]);
		    	map.put("coin_kor_name", FULL_ARRAY_STRING[1]);
		    	map.put("coin_number", FULL_ARRAY_STRING[2]);
		    	
		    	CoinList.add(map);
		    }
		}
		FileReader.close();
		
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
		        //System.out.println("["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"] " +conn.getResponseCode());
		        String RESPONSE_DATA = "";
		        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
		            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            String line = null;
		            while(reader != null){
		                line = reader.readLine();
		                if(line == null)
		                    break;
		                
		                RESPONSE_DATA += line;
		            }
		            reader.close();
		        } else {
		        	System.out.println("ERROR CODE ["+conn.getResponseCode()+"]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
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
			        			
				        		if(Long.parseLong((jArray_t.get(i)).toString()) >= GIJUN_TIMESTAMP_5) {
				        			
				        			
				        			if(o_price_5.equals("")) {
				        				o_price_5 = tmp_o_price;
				        			}
				        			
				        			if(l_price_5.equals("") && l_price_5.equals("")) {
				        				l_price_5 = tmp_l_price;
				        				h_price_5 = tmp_h_price;
				        			}
				        			
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
			        	}
			        	
			        	if(!o_price_15.equals("") && !l_price_15.equals("") && !h_price_15.equals("") && !c_price_15.equals("")
			        		|| (!o_price_5.equals("") && !l_price_5.equals("") && !h_price_5.equals("") && !c_price_5.equals(""))) {
			        		BigDecimal BD_100 = new BigDecimal("100");
			        		
			        		if(o_price_5.equals("")) {
			        			o_price_5 = "0";
			        		}
			        		if(c_price_5.equals("")) {
			        			c_price_5 = "0";
			        		}
			        		if(volume_5.equals("")) {
			        			volume_5 = "0";
			        		}

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
			        		BigDecimal BD_O_C_RATE_5 = new BigDecimal("0");
			        		if(!(BD_O_PRICE_5.toString()).equals("0")) {
			        			BD_O_C_RATE_5 = ((BD_C_PRICE_5.subtract(BD_O_PRICE_5)).divide(BD_O_PRICE_5, 6, RoundingMode.HALF_UP)).multiply(BD_100);
			        		}
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
			        		
			        		if(o_price_15.equals("")) {
			        			o_price_15 = "0";
			        		}
			        		if(c_price_15.equals("")) {
			        			c_price_15 = "0";
			        		}
			        		if(volume_15.equals("")) {
			        			volume_15 = "0";
			        		}
			        		
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
			        		BigDecimal BD_O_C_RATE_15 = new BigDecimal("0");
			        		if(!(BD_O_PRICE_15.toString()).equals("0")) {
			        			BD_O_C_RATE_15 = ((BD_C_PRICE_15.subtract(BD_O_PRICE_15)).divide(BD_O_PRICE_15, 6, RoundingMode.HALF_UP)).multiply(BD_100);
			        		}
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
			        		map.put("API_DATETIME_KST_5", GIJUN_DATETIME_5);
			        		map.put("gubun_5", gubun_5);
			        		map.put("o_price_5", o_price_5);
			        		map.put("l_price_5", l_price_5);
			        		map.put("h_price_5", h_price_5);
			        		map.put("c_price_5", c_price_5);
			        		map.put("format_c_price_5", format_c_price_5);
			        		map.put("o_c_rate_5", o_c_rate_5);
			        		map.put("o_c_subtract_5", o_c_subtract_5);
			        		map.put("volume_5", volume_5);
			        		map.put("volume_price_5", volume_price_5);
			        		map.put("format_volume_price_5", format_volume_price_5);
			        		map.put("API_DATETIME_KST_15", GIJUN_DATETIME_15);
			        		map.put("gubun_15", gubun_15);
			        		map.put("o_price_15", o_price_15);
			        		map.put("l_price_15", l_price_15);
			        		map.put("h_price_15", h_price_15);
			        		map.put("c_price_15", c_price_15);
			        		map.put("format_c_price_15", format_c_price_15);
			        		map.put("o_c_rate_15", o_c_rate_15);
			        		map.put("o_c_subtract_15", o_c_subtract_15);
			        		map.put("volume_15", volume_15);
			        		map.put("volume_price_15", volume_price_15);
			        		map.put("format_volume_price_15", format_volume_price_15);
			        		
			        		if(BithumbText.equals("")) {
			    				BithumbText += CURRENT_DATETIME_KST+"▦"+Coin_Ticker+"▦"+Coin_Kor_Name+"▦"+GIJUN_DATETIME_5+"▦"+gubun_5+"▦"+o_price_5+"▦"+l_price_5+"▦"+h_price_5+"▦"+c_price_5+"▦"+format_c_price_5+"▦"+o_c_rate_5+"▦"+o_c_subtract_5+"▦"+volume_5+"▦"+volume_price_5+"▦"+format_volume_price_5+"▦"+GIJUN_DATETIME_15+"▦"+gubun_15+"▦"+o_price_15+"▦"+l_price_15+"▦"+h_price_15+"▦"+c_price_15+"▦"+format_c_price_15+"▦"+o_c_rate_15+"▦"+o_c_subtract_15+"▦"+volume_5+"▦"+volume_price_15+"▦"+format_volume_price_15;
			    			} else {
			    				BithumbText += "■"+CURRENT_DATETIME_KST+"▦"+Coin_Ticker+"▦"+Coin_Kor_Name+"▦"+GIJUN_DATETIME_5+"▦"+gubun_5+"▦"+o_price_5+"▦"+l_price_5+"▦"+h_price_5+"▦"+c_price_5+"▦"+format_c_price_5+"▦"+o_c_rate_5+"▦"+o_c_subtract_5+"▦"+volume_5+"▦"+volume_price_5+"▦"+format_volume_price_5+"▦"+GIJUN_DATETIME_15+"▦"+gubun_15+"▦"+o_price_15+"▦"+l_price_15+"▦"+h_price_15+"▦"+c_price_15+"▦"+format_c_price_15+"▦"+o_c_rate_15+"▦"+o_c_subtract_15+"▦"+volume_5+"▦"+volume_price_15+"▦"+format_volume_price_15;
			    			}
			        	}
			        	
			        } else {
			        	System.out.println("[실패]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        }
		        }
		        conn.disconnect();
			}
		}
		
        File BithumbFile = new File("C:\\Coin", "BithumbLive1M.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(BithumbFile));
            writer.write(BithumbText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[LiveBithumbData_1M][" + END_DATETIME + "] 종료");
	}
	
	@Scheduled(cron = "0/30 * * * * *")
    public void LiveBithumbData_1H() throws Exception {
		String START_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[LiveBithumbData_1H][" + START_DATETIME + "] 시작");
		
		String BithumbText = "";
		String GIJUN_DATETIME_60 = "";
		String GIJUN_DATETIME_240 = "";
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        String API_Date_60 = DateNow;
        String API_HH_60 = HHNow;
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
        
        GIJUN_DATETIME_60 = API_Date_60 + " " + API_HH_60 + ":" + API_MM+":00";
		Date GIJUN_DATE_60 = SDF.parse(GIJUN_DATETIME_60);
		long GIJUN_TIMESTAMP_60 = GIJUN_DATE_60.getTime();
		
		GIJUN_DATETIME_240 = API_Date_240 + " " + API_HH_240 + ":" + API_MM+":00";
		Date GIJUN_DATE_240 = SDF.parse(GIJUN_DATETIME_240);
		long GIJUN_TIMESTAMP_240 = GIJUN_DATE_240.getTime();
		
		// 현재
		long TIMESTMAP = System.currentTimeMillis();
		String CURRENT_DATETIME_KST = SDF.format(System.currentTimeMillis());
		
		List<HashMap<String, String>> CoinList = new ArrayList<HashMap<String, String>>();
		
		BufferedReader FileReader = new BufferedReader(new FileReader("C:\\Coin\\BithumbCointInfo.txt"));
		String reader_str = "";
		while ((reader_str = FileReader.readLine()) != null) {
			String[] ARRAY_STRING = reader_str.split("■");
		    for (String STRING : ARRAY_STRING){
		    	String[] FULL_ARRAY_STRING = STRING.split("▦");
		    	
		    	HashMap<String, String> map = new HashMap<String, String>();
		    	map.put("coin_ticker", FULL_ARRAY_STRING[0]);
		    	map.put("coin_kor_name", FULL_ARRAY_STRING[1]);
		    	map.put("coin_number", FULL_ARRAY_STRING[2]);
		    	
		    	CoinList.add(map);
		    }
		}
		FileReader.close();
		
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
		            while(reader != null){
		                line = reader.readLine();
		                if(line == null)
		                    break;
		                
		                RESPONSE_DATA += line;
		            }
		            reader.close();
		        } else {
		        	System.out.println("ERROR CODE ["+conn.getResponseCode()+"]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
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
			        			
			        			BigDecimal BD_TMP_L_PRICE = new BigDecimal(tmp_l_price);
			        			BigDecimal BD_TMP_H_PRICE = new BigDecimal(tmp_h_price);
			        			BigDecimal BD_TMP_VOLUME = new BigDecimal(tmp_volume);
			        			
			        			if(o_price_240.equals("")) {
			        				o_price_240 = tmp_o_price;
			        			}
			        			
			        			if(l_price_240.equals("") && l_price_240.equals("")) {
			        				l_price_240 = tmp_l_price;
			        				h_price_240 = tmp_h_price;
			        			}
			        			
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
			        			
			        			if(Long.parseLong((jArray_t.get(i)).toString()) >= GIJUN_TIMESTAMP_60) {
			        				if(o_price_60.equals("")) {
				        				o_price_60 = tmp_o_price;
				        			}
				        			
				        			if(l_price_60.equals("") && l_price_60.equals("")) {
				        				l_price_60 = tmp_l_price;
				        				h_price_60 = tmp_h_price;
				        			}
				        			
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
			        	}
			        	
			        	if((!o_price_60.equals("") && !l_price_60.equals("") && !h_price_60.equals("") && !c_price_60.equals(""))
			        		|| (!o_price_240.equals("") && !l_price_240.equals("") && !h_price_240.equals("") && !c_price_240.equals(""))) {
			        		BigDecimal BD_100 = new BigDecimal("100");
			        		
			        		if(o_price_240.equals("")) {
			        			o_price_240 = "0";
			        		}
			        		if(c_price_240.equals("")) {
			        			c_price_240 = "0";
			        		}
			        		if(volume_240.equals("")) {
			        			volume_240 = "0";
			        		}
			        		
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
			        		BigDecimal BD_O_C_RATE_240 = new BigDecimal("0");
			        		if(!(BD_O_C_RATE_240.toString()).equals("0")) {
			        			BD_O_C_RATE_240 = ((BD_C_PRICE_240.subtract(BD_O_PRICE_240)).divide(BD_O_PRICE_240, 6, RoundingMode.HALF_UP)).multiply(BD_100);
			        		}
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
		        			
			        		if(o_price_60.equals("")) {
			        			o_price_60 = "0";
			        		}
			        		if(c_price_60.equals("")) {
			        			c_price_60 = "0";
			        		}
			        		if(volume_60.equals("")) {
			        			volume_60 = "0";
			        		}
			        		
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
			        		BigDecimal BD_O_C_RATE_60 = new BigDecimal("0");
			        		if(!(BD_O_C_RATE_60.toString()).equals("0")) {
			        			BD_O_C_RATE_60 = ((BD_C_PRICE_60.subtract(BD_O_PRICE_60)).divide(BD_O_PRICE_60, 6, RoundingMode.HALF_UP)).multiply(BD_100);
			        		}
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
			        		map.put("API_DATETIME_KST_60", GIJUN_DATETIME_60);
			        		map.put("gubun_60", gubun_60);
			        		map.put("o_price_60", o_price_60);
			        		map.put("l_price_60", l_price_60);
			        		map.put("h_price_60", h_price_60);
			        		map.put("c_price_60", c_price_60);
			        		map.put("format_c_price_60", format_c_price_60);
			        		map.put("o_c_rate_60", o_c_rate_60);
			        		map.put("o_c_subtract_60", o_c_subtract_60);
			        		map.put("volume_60", volume_60);
			        		map.put("volume_price_60", volume_price_60);
			        		map.put("format_volume_price_60", format_volume_price_60);
			        		map.put("API_DATETIME_KST_240", GIJUN_DATETIME_240);
			        		map.put("gubun_240", gubun_240);
			        		map.put("o_price_240", o_price_240);
			        		map.put("l_price_240", l_price_240);
			        		map.put("h_price_240", h_price_240);
			        		map.put("c_price_240", c_price_240);
			        		map.put("format_c_price_240", format_c_price_240);
			        		map.put("o_c_rate_240", o_c_rate_240);
			        		map.put("o_c_subtract_240", o_c_subtract_240);
			        		map.put("volume_240", volume_240);
			        		map.put("volume_price_240", volume_price_240);
			        		map.put("format_volume_price_240", format_volume_price_240);
			        		
			        		if(BithumbText.equals("")) {
			    				BithumbText += CURRENT_DATETIME_KST+"▦"+Coin_Ticker+"▦"+Coin_Kor_Name+"▦"+GIJUN_DATETIME_60+"▦"+gubun_60+"▦"+o_price_60+"▦"+l_price_60+"▦"+h_price_60+"▦"+c_price_60+"▦"+format_c_price_60+"▦"+o_c_rate_60+"▦"+o_c_subtract_60+"▦"+volume_60+"▦"+volume_price_60+"▦"+format_volume_price_60+"▦"+GIJUN_DATETIME_240+"▦"+gubun_240+"▦"+o_price_240+"▦"+l_price_240+"▦"+h_price_240+"▦"+c_price_240+"▦"+format_c_price_240+"▦"+o_c_rate_240+"▦"+o_c_subtract_240+"▦"+volume_60+"▦"+volume_price_240+"▦"+format_volume_price_240;
			    			} else {
			    				BithumbText += "■"+CURRENT_DATETIME_KST+"▦"+Coin_Ticker+"▦"+Coin_Kor_Name+"▦"+GIJUN_DATETIME_60+"▦"+gubun_60+"▦"+o_price_60+"▦"+l_price_60+"▦"+h_price_60+"▦"+c_price_60+"▦"+format_c_price_60+"▦"+o_c_rate_60+"▦"+o_c_subtract_60+"▦"+volume_60+"▦"+volume_price_60+"▦"+format_volume_price_60+"▦"+GIJUN_DATETIME_240+"▦"+gubun_240+"▦"+o_price_240+"▦"+l_price_240+"▦"+h_price_240+"▦"+c_price_240+"▦"+format_c_price_240+"▦"+o_c_rate_240+"▦"+o_c_subtract_240+"▦"+volume_60+"▦"+volume_price_240+"▦"+format_volume_price_240;
			    			}
			        	}
			        	
			        } else {
			        	System.out.println("[실패]["+Coin_Ticker+"]["+Coin_Kor_Name+"]["+Coin_Number+"]");
			        }
		        }
		        conn.disconnect();
			}
		}
		
		File BithumbFile = new File("C:\\Coin", "BithumbLive1H.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(BithumbFile));
            writer.write(BithumbText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		String END_DATETIME = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Calendar.getInstance()).getTime());
		System.out.println("[LiveBithumbData_1H][" + END_DATETIME + "] 종료");
	}
	*/
	
	@Async
	@Scheduled(cron = "0 30 0/1 * * *")
    public void Schedule_Report_Reg() throws Exception {
		/*
		LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("mm");
        String MMNow = now.format(formatter);
        
        System.out.println("HHNow ["+HHNow+"]");
        
		Calendar UpbitDay = Calendar.getInstance();
		UpbitDay.add(Calendar.HOUR, -9); // UTC 기준
		//if(Integer.parseInt(HHNow) == 9) {
		//	UpbitDay.add(Calendar.DATE , -1);
		//}
	    String UpbitDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(UpbitDay.getTime());
	    UpbitDay.add(Calendar.DATE , -1);
	    String UpbitYesterDay = new java.text.SimpleDateFormat("yyyy-MM-dd").format(UpbitDay.getTime());
	    
	    Calendar BitDay = Calendar.getInstance();
	    //if(Integer.parseInt(HHNow) == 0) {
	    //	BitDay.add(Calendar.DATE , -1);
	    //}
	    String BitDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(BitDay.getTime());
	    BitDay.add(Calendar.DATE , -1);
	    String BitYesterDay = new java.text.SimpleDateFormat("yyyy-MM-dd").format(BitDay.getTime());
	    
	    HashMap<String, String> SettingMap = MainService.CoinAnalysisSetting();
	    String BithumbMainRankingCount = SettingMap.get("bithumb_report_main_ranking");
	    String BithumbSubRankingCount = SettingMap.get("bithumb_report_sub_ranking");
	    String BithumbReportAD1 = SettingMap.get("bithumb_report_ad1");
	    String BithumbReportAD2 = SettingMap.get("bithumb_report_ad2");
	    String BithumbReportAD3 = SettingMap.get("bithumb_report_ad3");
	    String BithumbReportAD4 = SettingMap.get("bithumb_report_ad4");
	    String BithumbReportAD5 = SettingMap.get("bithumb_report_ad5");
	    String UpbitMainRankingCount = SettingMap.get("upbit_report_main_ranking");
	    String UpbitSubRankingCount = SettingMap.get("upbit_report_sub_ranking");
	    String UpbitReportAD1 = SettingMap.get("upbit_report_ad1");
	    String UpbitReportAD2 = SettingMap.get("upbit_report_ad2");
	    String UpbitReportAD3 = SettingMap.get("upbit_report_ad3");
	    String UpbitReportAD4 = SettingMap.get("upbit_report_ad4");
	    String UpbitReportAD5 = SettingMap.get("upbit_report_ad5");
	    String BinanceMainRankingCount = SettingMap.get("binance_report_main_ranking");
	    String BinanceSubRankingCount = SettingMap.get("binance_report_sub_ranking");
	    String BinanceReportAD1 = SettingMap.get("binance_report_ad1");
	    String BinanceReportAD2 = SettingMap.get("binance_report_ad2");
	    String BinanceReportAD3 = SettingMap.get("binance_report_ad3");
	    String BinanceReportAD4 = SettingMap.get("binance_report_ad4");
	    String BinanceReportAD5 = SettingMap.get("binance_report_ad5");
	    String BybitMainRankingCount = SettingMap.get("bybit_report_main_ranking");
	    String BybitSubRankingCount = SettingMap.get("bybit_report_sub_ranking");
	    String BybitReportAD1 = SettingMap.get("bybit_report_ad1");
	    String BybitReportAD2 = SettingMap.get("bybit_report_ad2");
	    String BybitReportAD3 = SettingMap.get("bybit_report_ad3");
	    String BybitReportAD4 = SettingMap.get("bybit_report_ad4");
	    String BybitReportAD5 = SettingMap.get("bybit_report_ad5");
	    
	    HashMap<String, String> BithumbMap = new HashMap<String, String>();
	    BithumbMap.put("date", BitDate);
	    BithumbMap.put("yyyymmdd", BitDate);
	    BithumbMap.put("yesterday", BitYesterDay);
	    BithumbMap.put("MainRankingCount", BithumbMainRankingCount);
	    BithumbMap.put("SubRankingCount", BithumbSubRankingCount);
	    BithumbMap.put("BithumbReportAD1", BithumbReportAD1);
	    BithumbMap.put("BithumbReportAD2", BithumbReportAD2);
	    BithumbMap.put("BithumbReportAD3", BithumbReportAD3);
	    BithumbMap.put("BithumbReportAD4", BithumbReportAD4);
	    BithumbMap.put("BithumbReportAD5", BithumbReportAD5);
	    
	    HashMap<String, String> UpbitMap = new HashMap<String, String>();
	    UpbitMap.put("date", UpbitDate);
	    UpbitMap.put("yyyymmdd", UpbitDate);
	    UpbitMap.put("yesterday", UpbitYesterDay);
	    UpbitMap.put("MainRankingCount", UpbitMainRankingCount);
	    UpbitMap.put("SubRankingCount", UpbitSubRankingCount);
	    UpbitMap.put("UpbitReportAD1", UpbitReportAD1);
	    UpbitMap.put("UpbitReportAD2", UpbitReportAD2);
	    UpbitMap.put("UpbitReportAD3", UpbitReportAD3);
	    UpbitMap.put("UpbitReportAD4", UpbitReportAD4);
	    UpbitMap.put("UpbitReportAD5", UpbitReportAD5);
	    
	    HashMap<String, String> BinanceMap = new HashMap<String, String>();
	    BinanceMap.put("date", UpbitDate);
	    BinanceMap.put("yyyymmdd", UpbitDate);
	    BinanceMap.put("yesterday", UpbitYesterDay);
	    BinanceMap.put("MainRankingCount", UpbitMainRankingCount);
	    BinanceMap.put("SubRankingCount", UpbitSubRankingCount);
	    BinanceMap.put("BinanceReportAD1", BinanceReportAD1);
	    BinanceMap.put("BinanceReportAD2", BinanceReportAD2);
	    BinanceMap.put("BinanceReportAD3", BinanceReportAD3);
	    BinanceMap.put("BinanceReportAD4", BinanceReportAD4);
	    BinanceMap.put("BinanceReportAD5", BinanceReportAD5);
	    
	    HashMap<String, String> BybitMap = new HashMap<String, String>();
	    BybitMap.put("date", UpbitDate);
	    BybitMap.put("yyyymmdd", UpbitDate);
	    BybitMap.put("yesterday", UpbitYesterDay);
	    BybitMap.put("MainRankingCount", BybitMainRankingCount);
	    BybitMap.put("SubRankingCount", BybitSubRankingCount);
	    BybitMap.put("BybitReportAD1", BybitReportAD1);
	    BybitMap.put("BybitReportAD2", BybitReportAD2);
	    BybitMap.put("BybitReportAD3", BybitReportAD3);
	    BybitMap.put("BybitReportAD4", BybitReportAD4);
	    BybitMap.put("BybitReportAD5", BybitReportAD5);
	    
	    if((Integer.parseInt(HHNow) >= 16) || (Integer.parseInt(HHNow) <= 2)) {
        	BitCoinService.CoinAnalysisDelete(BithumbMap);
    		Thread.sleep(1500);
    		BitCoinService.CoinAnalysisCreate(BithumbMap);
    		BitCoinService.CoinAnalysisHourGraphCreate(BithumbMap);
    	    BitCoinService.CoinAnalysis4HourGraphCreate(BithumbMap);
    	    BithumbMap.put("blog_id", "1");
		    BitCoinService.WordPressReportHTMLCreate(BithumbMap);
		    BithumbMap.put("blog_id", "2");
		    BitCoinService.TiStoryReportHTMLCreate(BithumbMap);
		    
		    UpbitCoinService.CoinAnalysisDelete(UpbitMap);
		    Thread.sleep(1500);
    		UpbitCoinService.CoinAnalysisCreate(UpbitMap);
    		UpbitCoinService.CoinAnalysisHourGraphCreate(UpbitMap);
    		UpbitCoinService.CoinAnalysis4HourGraphCreate(UpbitMap);
    		UpbitMap.put("blog_id", "1");
    	    UpbitCoinService.WordPressReportHTMLCreate(UpbitMap);
    	    UpbitMap.put("blog_id", "3");
    	    UpbitCoinService.TiStoryReportHTMLCreate(UpbitMap);
    	    
    	    BinanceCoinService.CoinAnalysisDelete(BinanceMap);
		    Thread.sleep(1500);
		    BinanceCoinService.CoinAnalysisCreate(BinanceMap);
		    BinanceCoinService.CoinAnalysisHourGraphCreate(BinanceMap);
		    BinanceCoinService.CoinAnalysis4HourGraphCreate(BinanceMap);
    		BinanceMap.put("blog_id", "1");
    		BinanceCoinService.WordPressReportHTMLCreate(BinanceMap);
    	    BinanceMap.put("blog_id", "4");
    	    BinanceCoinService.TiStoryReportHTMLCreate(BinanceMap);
    	    
    	    BinanceFuturesCoinService.CoinAnalysisDelete(BinanceMap);
		    Thread.sleep(1500);
		    BinanceFuturesCoinService.CoinAnalysisCreate(BinanceMap);
		    BinanceFuturesCoinService.CoinAnalysisHourGraphCreate(BinanceMap);
		    BinanceFuturesCoinService.CoinAnalysis4HourGraphCreate(BinanceMap);
    		BinanceMap.put("blog_id", "1");
    		BinanceFuturesCoinService.WordPressReportHTMLCreate(BinanceMap);
    	    BinanceMap.put("blog_id", "5");
    	    BinanceFuturesCoinService.TiStoryReportHTMLCreate(BinanceMap);
    	    
    	    BybitCoinService.CoinAnalysisDelete(BybitMap);
		    Thread.sleep(1500);
		    BybitCoinService.CoinAnalysisCreate(BybitMap);
		    BybitCoinService.CoinAnalysisHourGraphCreate(BybitMap);
		    BybitCoinService.CoinAnalysis4HourGraphCreate(BybitMap);
		    BybitMap.put("blog_id", "1");
    		BybitCoinService.WordPressReportHTMLCreate(BybitMap);
    		BybitMap.put("blog_id", "6");
    	    BybitCoinService.TiStoryReportHTMLCreate(BybitMap);
        }else {
        	UpbitCoinService.CoinAnalysisDelete(UpbitMap);
		    Thread.sleep(1500);
    		UpbitCoinService.CoinAnalysisCreate(UpbitMap);
    		UpbitCoinService.CoinAnalysisHourGraphCreate(UpbitMap);
    		UpbitCoinService.CoinAnalysis4HourGraphCreate(UpbitMap);
    		UpbitMap.put("blog_id", "1");
    	    UpbitCoinService.WordPressReportHTMLCreate(UpbitMap);
    	    UpbitMap.put("blog_id", "3");
    	    UpbitCoinService.TiStoryReportHTMLCreate(UpbitMap);
        	
    	    BinanceCoinService.CoinAnalysisDelete(BinanceMap);
		    Thread.sleep(1500);
		    BinanceCoinService.CoinAnalysisCreate(BinanceMap);
		    BinanceCoinService.CoinAnalysisHourGraphCreate(BinanceMap);
		    BinanceCoinService.CoinAnalysis4HourGraphCreate(BinanceMap);
    		BinanceMap.put("blog_id", "1");
    		BinanceCoinService.WordPressReportHTMLCreate(BinanceMap);
    	    BinanceMap.put("blog_id", "4");
    	    BinanceCoinService.TiStoryReportHTMLCreate(BinanceMap);
    	    
    	    BinanceFuturesCoinService.CoinAnalysisDelete(BinanceMap);
		    Thread.sleep(1500);
		    BinanceFuturesCoinService.CoinAnalysisCreate(BinanceMap);
		    BinanceFuturesCoinService.CoinAnalysisHourGraphCreate(BinanceMap);
		    BinanceFuturesCoinService.CoinAnalysis4HourGraphCreate(BinanceMap);
    		BinanceMap.put("blog_id", "1");
    		BinanceFuturesCoinService.WordPressReportHTMLCreate(BinanceMap);
    	    BinanceMap.put("blog_id", "5");
    	    BinanceFuturesCoinService.TiStoryReportHTMLCreate(BinanceMap);
    	    
    	    BybitCoinService.CoinAnalysisDelete(BybitMap);
		    Thread.sleep(1500);
		    BybitCoinService.CoinAnalysisCreate(BybitMap);
		    BybitCoinService.CoinAnalysisHourGraphCreate(BybitMap);
		    BybitCoinService.CoinAnalysis4HourGraphCreate(BybitMap);
		    BybitMap.put("blog_id", "1");
    		BybitCoinService.WordPressReportHTMLCreate(BybitMap);
    		BybitMap.put("blog_id", "6");
    	    BybitCoinService.TiStoryReportHTMLCreate(BybitMap);
    	    
    	    BitCoinService.CoinAnalysisDelete(BithumbMap);
    		Thread.sleep(1500);
    		BitCoinService.CoinAnalysisCreate(BithumbMap);
    		BitCoinService.CoinAnalysisHourGraphCreate(BithumbMap);
    	    BitCoinService.CoinAnalysis4HourGraphCreate(BithumbMap);
    	    BithumbMap.put("blog_id", "1");
		    BitCoinService.WordPressReportHTMLCreate(BithumbMap);
		    BithumbMap.put("blog_id", "2");
		    BitCoinService.TiStoryReportHTMLCreate(BithumbMap);  
        }
        */
	}
	
	
	@Scheduled(cron = "0 30 0/1 * * *")
    public void Schedule_MailSender() throws UnsupportedEncodingException {
    	/*
		String username = "diff2024@naver.com";
		String password = "VR6PRXMB5YRK";
		String recipient = "diff2024@naver.com";
		String recipient2 = "asaswq2002@naver.com";
		
    	Calendar day = Calendar.getInstance();
	    day.add(Calendar.DATE , -1);
	    String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
	    day.add(Calendar.DATE , -1);
	    String yesterday = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
	    String fileName = "Daily_"+date+".xlsx";
	    
	    LocalTime HHnow = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        String HHNow = HHnow.format(formatter);
        
	    if((Integer.parseInt(HHNow) == 9) || (Integer.parseInt(HHNow) == 0)) {
	    	String TradeGubun = "";
	    	try {
	    		if(Integer.parseInt(HHNow) == 9) {
	    			TradeGubun = "업비트";
	    			fileName = TradeGubun + "_" + fileName;
	    			this.UpbitExcelDaily(date, yesterday, fileName);
	    		} else {
	    			TradeGubun = "빗썸";
	    			fileName = TradeGubun + "_" + fileName;
	    			this.BithumbExcelDaily(date, yesterday, fileName);
	    		}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	try {
	    		
		    	Properties props = new Properties();
		        props.put("mail.smtp.host", "smtp.naver.com");
		        props.put("mail.smtp.port", "465");
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.debug", "true");
		        props.put("mail.smtp.ssl.trust", "smtp.naver.com");
		        props.put("mail.smtp.ssl.enable", "true");
		        
		        javax.mail.Session mailsession = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
		            String un = username;
		            String pw = password;
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(un, pw);
		            }
		         });
		         
		         mailsession.setDebug(true);
		         javax.mail.Message mimeMessage = new MimeMessage(mailsession);
		         mimeMessage.setFrom(new InternetAddress(username));
		         mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		         //mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient2));
		         mimeMessage.setSubject(MimeUtility.encodeText("["+date+"] "+TradeGubun+" 일일 분석", "UTF-8", "B"));
		         
		         MimeMultipart multipart = new MimeMultipart();
		         MimeBodyPart partText = new MimeBodyPart();
		         MimeBodyPart partFile1 = new MimeBodyPart();
		         partText.setContent("["+date+"] "+TradeGubun+" 일일 분석".toString(), "text/html; charset=utf-8");
		         multipart.addBodyPart(partText);
		         
		         javax.activation.FileDataSource fds1 = new javax.activation.FileDataSource("C:\\Coin\\MailExcel\\"+fileName);
		         partFile1.setDataHandler(new javax.activation.DataHandler(fds1));
		         partFile1.setFileName(MimeUtility.encodeText(TradeGubun+" 일일 "+date+".xlsx", "UTF-8","B"));
		         multipart.addBodyPart(partFile1);
		         
		         mimeMessage.setContent(multipart);
		         Transport.send(mimeMessage);
	    	}catch (MessagingException e) {
	    		e.printStackTrace();
	    	}
	    }
    }
	
	public void UpbitExcelDaily(String date, String yesterday, String fileName) throws Exception {
		DecimalFormat formatter = new DecimalFormat("###,###.########");
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("date", date);
        map.put("yesterday", yesterday);
        
		try {
			String path = CoinController.class.getResource("").getPath();
			InputStream fis = new FileInputStream(new File("C:/Coin/Excel/Daily.xlsx"));
	        XSSFWorkbook form_wb = new XSSFWorkbook(fis);
	        XSSFSheet form_sheet = form_wb.getSheetAt(0);
	        XSSFSheet ranking_sheet = form_wb.getSheetAt(1);
	        XSSFSheet summary_sheet = form_wb.createSheet("스크립트");
	        
	        List<HashMap<String, String>> result_map = UpbitCoinService.ExcelMake_Daily_Result(map);
	        String coin_total_count = result_map.get(0).get("total_count");
	        String coin_increase_count = result_map.get(0).get("increase_count");
	        String coin_degradation_count = result_map.get(0).get("degradation_count");
	        String coin_flat_count = result_map.get(0).get("flat_count");
	        String coin_increase_ticker = result_map.get(0).get("increase_ticker");
	        String coin_degradation_ticker = result_map.get(0).get("degradation_ticker");
	        String coin_flat_ticker = result_map.get(0).get("flat_ticker");
	        
	        List<HashMap<String, String>> ranking_list = UpbitCoinService.ExcelMake_Daily_Ranking(map);
	        
	        String result_data = " "+coin_total_count+" | " + coin_increase_count +" | " + coin_degradation_count +" | " + coin_flat_count;
	        form_wb.setSheetName(0, result_data);
	        
	        HashMap<String, String> excel_map = UpbitCoinService.ExcelMake_Daily(map);
	        
	        Font Font_Red = form_wb.createFont();
	        Font_Red.setFontName("맑은 고딕");
	        Font_Red.setColor(IndexedColors.RED.index);
	        
	        Font Font_Blue = form_wb.createFont();
	        Font_Blue.setFontName("맑은 고딕");
	        Font_Blue.setColor(IndexedColors.BLUE.index);
	        
	        CellStyle cell_Red = form_wb.createCellStyle();
	        cell_Red.setFont(Font_Red);
	        cell_Red.setAlignment(HorizontalAlignment.RIGHT);
	        cell_Red.setBorderTop(BorderStyle.THIN);
	        cell_Red.setBorderBottom(BorderStyle.THIN);
	        cell_Red.setBorderLeft(BorderStyle.THIN);
	        cell_Red.setBorderRight(BorderStyle.THIN);
	        
	        CellStyle cell_Red_Bottom = form_wb.createCellStyle();
	        cell_Red_Bottom.setFont(Font_Red);
	        cell_Red_Bottom.setAlignment(HorizontalAlignment.RIGHT);
	        cell_Red_Bottom.setBorderBottom(BorderStyle.MEDIUM);
	        
	        CellStyle cell_Blue = form_wb.createCellStyle();
	        cell_Blue.setFont(Font_Blue);
	        cell_Blue.setAlignment(HorizontalAlignment.RIGHT);
	        cell_Blue.setBorderTop(BorderStyle.THIN);
	        cell_Blue.setBorderBottom(BorderStyle.THIN);
	        cell_Blue.setBorderLeft(BorderStyle.THIN);
	        cell_Blue.setBorderRight(BorderStyle.THIN);
	        
	        CellStyle cell_Blue_Bottom = form_wb.createCellStyle();
	        cell_Blue_Bottom.setFont(Font_Blue);
	        cell_Blue_Bottom.setAlignment(HorizontalAlignment.RIGHT);
	        cell_Blue_Bottom.setBorderBottom(BorderStyle.MEDIUM);
	        
	        CellStyle cell_Ranking_Left = form_wb.createCellStyle();
	        cell_Ranking_Left.setAlignment(HorizontalAlignment.LEFT);
	        cell_Ranking_Left.setBorderTop(BorderStyle.THIN);
	        cell_Ranking_Left.setBorderBottom(BorderStyle.THIN);
	        cell_Ranking_Left.setBorderLeft(BorderStyle.THIN);
	        cell_Ranking_Left.setBorderRight(BorderStyle.THIN);
	        
	        CellStyle cell_Ranking_Right = form_wb.createCellStyle();
	        cell_Ranking_Right.setAlignment(HorizontalAlignment.RIGHT);
	        cell_Ranking_Right.setBorderTop(BorderStyle.THIN);
	        cell_Ranking_Right.setBorderBottom(BorderStyle.THIN);
	        cell_Ranking_Right.setBorderLeft(BorderStyle.THIN);
	        cell_Ranking_Right.setBorderRight(BorderStyle.THIN);
	        
	        CellStyle cell_Ranking_Center = form_wb.createCellStyle();
	        cell_Ranking_Center.setAlignment(HorizontalAlignment.CENTER);
	        cell_Ranking_Center.setBorderTop(BorderStyle.THIN);
	        cell_Ranking_Center.setBorderBottom(BorderStyle.THIN);
	        cell_Ranking_Center.setBorderLeft(BorderStyle.THIN);
	        cell_Ranking_Center.setBorderRight(BorderStyle.THIN);
	        
	        CellStyle cell_Ranking_List = form_wb.createCellStyle();
	        cell_Ranking_List.setAlignment(HorizontalAlignment.LEFT);
	        cell_Ranking_List.setWrapText(true);
	        
	        int Sheet1_Row = 1;
	        int Sheet2_Row = 1;
	        
	        String coopang_partner = "";
	        coopang_partner += "<br/>";
	        coopang_partner += "<a href=\"https://link.coupang.com/a/bctzMe\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://image5.coupangcdn.com/image/affiliate/banner/4c0754a7767da6ff093f71b999aaed60@2x.jpg\" alt=\"서른살 비트코인으로 퇴사합니다:투자 2년 경제적 자유를 얻다, 국일증권경제연구소, 강기태(세력\" width=\"110\" height=\"240\"></a>";
	        coopang_partner += "<a href=\"https://link.coupang.com/a/bctB5h\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://img5a.coupangcdn.com/image/affiliate/banner/579dbb3cd67c960da5a58adde964cfb8@2x.jpg\" alt=\"시골 영어 선생 비트코인 투자로 매월 1억 번다:, 국일증권경제연구소, 구대환\" width=\"110\" height=\"240\"></a>";
	        coopang_partner += "<a href=\"https://link.coupang.com/a/bctAo1\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://image6.coupangcdn.com/image/affiliate/banner/2f2f3b63a231fab01ea771b08986226f@2x.jpg\" alt=\"비트코인 2년 만에 흙수저 졸업했습니다.:강남 아파트 대신 비트코인에 미쳐라!, 청출판, 박보혜\" width=\"110\" height=\"240\"></a>";
	        coopang_partner += "<a href=\"https://link.coupang.com/a/bctBOI\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://image8.coupangcdn.com/image/affiliate/banner/4f719b97961b3ed9b64df21ec9ae4d10@2x.jpg\" alt=\"나는 가상화폐로 3달 만에 3억 벌었다:비트코인 이더리움 채굴기 실전투자 전략서, 이코노믹북스, 빈현우\" width=\"110\" height=\"240\"></a>";
	        coopang_partner += "<a href=\"https://link.coupang.com/a/bctBpi\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://image4.coupangcdn.com/image/affiliate/banner/834b320bfbaa409cbac27a7407ac7127@2x.jpg\" alt=\"코인으로 인생역전:전략 없이 비트코인 시장에 뛰어들지 마라!, 리치캠프, 홍지윤윤용욱정지훈류경문김민형최완순\" width=\"110\" height=\"240\"></a>";
	        coopang_partner += "<a href=\"https://link.coupang.com/a/bctARp\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://img1c.coupangcdn.com/image/affiliate/banner/3867c1e551232847e60b6a16ffcfb8d3@2x.jpg\" alt=\"부자의 길을 선택하다:비트코인 거물이 알려주는 운명을 바꾸는 투자 원칙, 메가스터디북스, 리샤오라이\" width=\"110\" height=\"240\"></a>";
	        coopang_partner += "<div style=\"text-align:center; font-size:9px;\">이 포스팅은 쿠팡 파트너스 활동의 일환으로, 이에 따른 일정액의 수수료를 제공받습니다.</div>";
	        coopang_partner += "<br/>";
	        
	        form_sheet.getRow(Sheet1_Row).getCell(1).setCellValue("업비트 일일 분석 " + date);
	        form_sheet.getRow(Sheet1_Row+3).getCell(2).setCellValue(coin_increase_count);
	        form_sheet.getRow(Sheet1_Row+3).getCell(3).setCellValue(coin_increase_ticker);
	        form_sheet.getRow(Sheet1_Row+5).getCell(2).setCellValue(coin_flat_count);
	        form_sheet.getRow(Sheet1_Row+5).getCell(3).setCellValue(coin_flat_ticker);
	        form_sheet.getRow(Sheet1_Row+7).getCell(2).setCellValue(coin_degradation_count);
	        form_sheet.getRow(Sheet1_Row+7).getCell(3).setCellValue(coin_degradation_ticker);
	        form_sheet.getRow(Sheet1_Row+10).getCell(2).setCellValue(excel_map.get("btc_ticker"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(3).setCellValue(excel_map.get("btc_o_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(7).setCellValue(excel_map.get("btc_c_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(3).setCellValue(excel_map.get("btc_l_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(7).setCellValue(excel_map.get("btc_h_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(3).setCellValue(excel_map.get("btc_open_close"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(7).setCellValue(excel_map.get("btc_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(3).setCellValue(excel_map.get("btc_price_min_time"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(7).setCellValue(excel_map.get("btc_price_max_time"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(3).setCellValue(excel_map.get("btc_volume_min_time"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(7).setCellValue(excel_map.get("btc_volume_max_time"));
	        
	        form_sheet.getRow(Sheet1_Row+11).getCell(10).setCellValue(excel_map.get("btc_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(11).setCellValue(excel_map.get("btc_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(12).setCellValue(excel_map.get("btc_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(13).setCellValue(excel_map.get("btc_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(14).setCellValue(excel_map.get("btc_oclh_1_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+11).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+11).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+12).getCell(10).setCellValue(excel_map.get("btc_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(11).setCellValue(excel_map.get("btc_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(12).setCellValue(excel_map.get("btc_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(13).setCellValue(excel_map.get("btc_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(14).setCellValue(excel_map.get("btc_oclh_2_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+12).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+12).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+13).getCell(10).setCellValue(excel_map.get("btc_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(11).setCellValue(excel_map.get("btc_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(12).setCellValue(excel_map.get("btc_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(13).setCellValue(excel_map.get("btc_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(14).setCellValue(excel_map.get("btc_oclh_3_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+13).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+13).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+14).getCell(10).setCellValue(excel_map.get("btc_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(11).setCellValue(excel_map.get("btc_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(12).setCellValue(excel_map.get("btc_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(13).setCellValue(excel_map.get("btc_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(14).setCellValue(excel_map.get("btc_oclh_4_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+14).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+14).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+15).getCell(10).setCellValue(excel_map.get("btc_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(11).setCellValue(excel_map.get("btc_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(12).setCellValue(excel_map.get("btc_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(13).setCellValue(excel_map.get("btc_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(14).setCellValue(excel_map.get("btc_oclh_5_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+15).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+15).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+16).getCell(10).setCellValue(excel_map.get("btc_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(11).setCellValue(excel_map.get("btc_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(12).setCellValue(excel_map.get("btc_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(13).setCellValue(excel_map.get("btc_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(14).setCellValue(excel_map.get("btc_oclh_6_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+16).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+16).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(Sheet1_Row+18).getCell(2).setCellValue(excel_map.get("coin1_ticker"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(3).setCellValue(excel_map.get("coin1_o_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(7).setCellValue(excel_map.get("coin1_c_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(3).setCellValue(excel_map.get("coin1_l_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(7).setCellValue(excel_map.get("coin1_h_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(3).setCellValue(excel_map.get("coin1_open_close"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(7).setCellValue(excel_map.get("coin1_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(3).setCellValue(excel_map.get("coin1_price_min_time"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(7).setCellValue(excel_map.get("coin1_price_max_time"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(3).setCellValue(excel_map.get("coin1_volume_min_time"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(7).setCellValue(excel_map.get("coin1_volume_max_time"));
	        
	        form_sheet.getRow(Sheet1_Row+19).getCell(10).setCellValue(excel_map.get("coin1_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+19).getCell(11).setCellValue(excel_map.get("coin1_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+19).getCell(12).setCellValue(excel_map.get("coin1_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+19).getCell(13).setCellValue(excel_map.get("coin1_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+19).getCell(14).setCellValue(excel_map.get("coin1_oclh_1_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+19).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+19).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+20).getCell(10).setCellValue(excel_map.get("coin1_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(11).setCellValue(excel_map.get("coin1_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(12).setCellValue(excel_map.get("coin1_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(13).setCellValue(excel_map.get("coin1_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(14).setCellValue(excel_map.get("coin1_oclh_2_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+20).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+20).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+21).getCell(10).setCellValue(excel_map.get("coin1_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(11).setCellValue(excel_map.get("coin1_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(12).setCellValue(excel_map.get("coin1_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(13).setCellValue(excel_map.get("coin1_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(14).setCellValue(excel_map.get("coin1_oclh_3_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+21).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+21).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+22).getCell(10).setCellValue(excel_map.get("coin1_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(11).setCellValue(excel_map.get("coin1_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(12).setCellValue(excel_map.get("coin1_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(13).setCellValue(excel_map.get("coin1_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(14).setCellValue(excel_map.get("coin1_oclh_4_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+22).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+22).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+23).getCell(10).setCellValue(excel_map.get("coin1_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(11).setCellValue(excel_map.get("coin1_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(12).setCellValue(excel_map.get("coin1_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(13).setCellValue(excel_map.get("coin1_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(14).setCellValue(excel_map.get("coin1_oclh_5_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+23).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+23).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+24).getCell(10).setCellValue(excel_map.get("coin1_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(11).setCellValue(excel_map.get("coin1_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(12).setCellValue(excel_map.get("coin1_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(13).setCellValue(excel_map.get("coin1_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(14).setCellValue(excel_map.get("coin1_oclh_6_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+24).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+24).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(Sheet1_Row+26).getCell(2).setCellValue(excel_map.get("coin2_ticker"));
	        form_sheet.getRow(Sheet1_Row+28).getCell(3).setCellValue(excel_map.get("coin2_o_price"));
	        form_sheet.getRow(Sheet1_Row+28).getCell(7).setCellValue(excel_map.get("coin2_c_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(3).setCellValue(excel_map.get("coin2_l_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(7).setCellValue(excel_map.get("coin2_h_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(3).setCellValue(excel_map.get("coin2_open_close"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(7).setCellValue(excel_map.get("coin2_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(3).setCellValue(excel_map.get("coin2_price_min_time"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(7).setCellValue(excel_map.get("coin2_price_max_time"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(3).setCellValue(excel_map.get("coin2_volume_min_time"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(7).setCellValue(excel_map.get("coin2_volume_max_time"));
	        
	        form_sheet.getRow(Sheet1_Row+27).getCell(10).setCellValue(excel_map.get("coin2_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+27).getCell(11).setCellValue(excel_map.get("coin2_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+27).getCell(12).setCellValue(excel_map.get("coin2_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+27).getCell(13).setCellValue(excel_map.get("coin2_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+27).getCell(14).setCellValue(excel_map.get("coin2_oclh_1_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+27).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+27).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+28).getCell(10).setCellValue(excel_map.get("coin2_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+28).getCell(11).setCellValue(excel_map.get("coin2_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+28).getCell(12).setCellValue(excel_map.get("coin2_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+28).getCell(13).setCellValue(excel_map.get("coin2_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+28).getCell(14).setCellValue(excel_map.get("coin2_oclh_2_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+28).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+28).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+29).getCell(10).setCellValue(excel_map.get("coin2_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(11).setCellValue(excel_map.get("coin2_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(12).setCellValue(excel_map.get("coin2_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(13).setCellValue(excel_map.get("coin2_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(14).setCellValue(excel_map.get("coin2_oclh_3_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+29).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+29).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+30).getCell(10).setCellValue(excel_map.get("coin2_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(11).setCellValue(excel_map.get("coin2_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(12).setCellValue(excel_map.get("coin2_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(13).setCellValue(excel_map.get("coin2_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(14).setCellValue(excel_map.get("coin2_oclh_4_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+30).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+30).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+31).getCell(10).setCellValue(excel_map.get("coin2_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(11).setCellValue(excel_map.get("coin2_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(12).setCellValue(excel_map.get("coin2_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(13).setCellValue(excel_map.get("coin2_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(14).setCellValue(excel_map.get("coin2_oclh_5_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+31).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+31).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+32).getCell(10).setCellValue(excel_map.get("coin2_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(11).setCellValue(excel_map.get("coin2_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(12).setCellValue(excel_map.get("coin2_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(13).setCellValue(excel_map.get("coin2_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(14).setCellValue(excel_map.get("coin2_oclh_6_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+32).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+32).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(Sheet1_Row+34).getCell(2).setCellValue(excel_map.get("coin3_ticker"));
	        form_sheet.getRow(Sheet1_Row+36).getCell(3).setCellValue(excel_map.get("coin3_o_price"));
	        form_sheet.getRow(Sheet1_Row+36).getCell(7).setCellValue(excel_map.get("coin3_c_price"));
	        form_sheet.getRow(Sheet1_Row+37).getCell(3).setCellValue(excel_map.get("coin3_l_price"));
	        form_sheet.getRow(Sheet1_Row+37).getCell(7).setCellValue(excel_map.get("coin3_h_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(3).setCellValue(excel_map.get("coin3_open_close"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(7).setCellValue(excel_map.get("coin3_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(3).setCellValue(excel_map.get("coin3_price_min_time"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(7).setCellValue(excel_map.get("coin3_price_max_time"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(3).setCellValue(excel_map.get("coin3_volume_min_time"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(7).setCellValue(excel_map.get("coin3_volume_max_time"));
	        
	        form_sheet.getRow(Sheet1_Row+35).getCell(10).setCellValue(excel_map.get("coin3_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(11).setCellValue(excel_map.get("coin3_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(12).setCellValue(excel_map.get("coin3_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(13).setCellValue(excel_map.get("coin3_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(14).setCellValue(excel_map.get("coin3_oclh_1_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+35).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+35).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+36).getCell(10).setCellValue(excel_map.get("coin3_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+36).getCell(11).setCellValue(excel_map.get("coin3_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+36).getCell(12).setCellValue(excel_map.get("coin3_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+36).getCell(13).setCellValue(excel_map.get("coin3_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+36).getCell(14).setCellValue(excel_map.get("coin3_oclh_2_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+36).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+36).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+37).getCell(10).setCellValue(excel_map.get("coin3_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+37).getCell(11).setCellValue(excel_map.get("coin3_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+37).getCell(12).setCellValue(excel_map.get("coin3_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+37).getCell(13).setCellValue(excel_map.get("coin3_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+37).getCell(14).setCellValue(excel_map.get("coin3_oclh_3_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+37).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+37).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+38).getCell(10).setCellValue(excel_map.get("coin3_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(11).setCellValue(excel_map.get("coin3_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(12).setCellValue(excel_map.get("coin3_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(13).setCellValue(excel_map.get("coin3_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(14).setCellValue(excel_map.get("coin3_oclh_4_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+38).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+38).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+39).getCell(10).setCellValue(excel_map.get("coin3_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(11).setCellValue(excel_map.get("coin3_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(12).setCellValue(excel_map.get("coin3_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(13).setCellValue(excel_map.get("coin3_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(14).setCellValue(excel_map.get("coin3_oclh_5_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+39).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+39).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+40).getCell(10).setCellValue(excel_map.get("coin3_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(11).setCellValue(excel_map.get("coin3_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(12).setCellValue(excel_map.get("coin3_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(13).setCellValue(excel_map.get("coin3_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(14).setCellValue(excel_map.get("coin3_oclh_6_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+40).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+40).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        String kor_date = date.substring(0, 4) + "년 "+date.substring(5, 7)+"월 "+(date.substring(8, 9)).replace("0", "") + (date.substring(9, 10)) +"일";
	        String short_kor_date = date.substring(5, 7)+"월 "+(date.substring(8, 9)).replace("0", "") + (date.substring(9, 10)) +"일";
	        
	        String yesterday_kor_date = yesterday.substring(0, 4) + "년 "+yesterday.substring(5, 7)+"월 "+(yesterday.substring(8, 9)).replace("0", "") + (yesterday.substring(9, 10)) +"일";
	        String yesterday_short_kor_date = yesterday.substring(5, 7)+"월 "+(yesterday.substring(8, 9)).replace("0", "") + (yesterday.substring(9, 10)) +"일";
	        
	        int RowNumber = 0;
	        String today1_text = "";
	        String today2_text = "";
	        String today3_text = "";
	        String today4_text = "";
	        String today5_text = "";
	        String today6_text = "";
	        String today7_text = "";
	        String today8_text = "";
	        String today9_text = "";
	        String today10_text = "";
	        String today11_text = "";
	        String today12_text = "";
	        String today13_text = "";
	        String today14_text = "";
	        String today15_text = "";
	        String today16_text = "";
	        String today17_text = "";
	        String today18_text = "";
	        String today19_text = "";
	        String today20_text = "";
	        for(int x=0; x<ranking_list.size(); x++) {
	        	RowNumber = (x+2);
	        	Row RankingRow = ranking_sheet.createRow(x+2);
	        	RankingRow.createCell(1);
	        	RankingRow.createCell(2);
	        	RankingRow.createCell(3);
	        	RankingRow.createCell(4);
	        	RankingRow.createCell(5);
	        	RankingRow.createCell(6);
	        	RankingRow.createCell(7);
	        	RankingRow.createCell(8);
	        	
	        	String ranking_number = (ranking_list.get(x).get("ranking"));
	        	String ranking_coin_name = (ranking_list.get(x).get("coin_name"));
	        	String ranking_o_price = (ranking_list.get(x).get("o_price")).toString();
	        	String ranking_l_price = (ranking_list.get(x).get("l_price")).toString();
	        	String ranking_h_price = (ranking_list.get(x).get("h_price")).toString();
	        	String ranking_c_price = (ranking_list.get(x).get("c_price")).toString();
	        	String ranking_o_c_rate = (ranking_list.get(x).get("o_c_price_rate")).toString();
	        	String ranking_l_h_rate = (ranking_list.get(x).get("l_h_price_rate")).toString();
	        	
	        	String yesterday_ranking_number = (ranking_list.get(x).get("before_ranking"));
	        	String yesterday_ranking_o_price = (ranking_list.get(x).get("before_o_price")).toString();
	        	String yesterday_ranking_l_price = (ranking_list.get(x).get("before_l_price")).toString();
	        	String yesterday_ranking_h_price = (ranking_list.get(x).get("before_h_price")).toString();
	        	String yesterday_ranking_c_price = (ranking_list.get(x).get("before_c_price")).toString();
	        	String yesterday_ranking_o_c_rate = (ranking_list.get(x).get("before_o_c_price_rate")).toString();
	        	String yesterday_ranking_l_h_rate = (ranking_list.get(x).get("before_l_h_price_rate")).toString();
	        	
	        	ranking_o_price = formatter.format(Double.parseDouble(ranking_o_price));
	        	ranking_l_price = formatter.format(Double.parseDouble(ranking_l_price));
	        	ranking_h_price = formatter.format(Double.parseDouble(ranking_h_price));
	        	ranking_c_price = formatter.format(Double.parseDouble(ranking_c_price));
	        	
	        	yesterday_ranking_o_price = formatter.format(Double.parseDouble(yesterday_ranking_o_price));
	        	yesterday_ranking_l_price = formatter.format(Double.parseDouble(yesterday_ranking_l_price));
	        	yesterday_ranking_h_price = formatter.format(Double.parseDouble(yesterday_ranking_h_price));
	        	yesterday_ranking_c_price = formatter.format(Double.parseDouble(yesterday_ranking_c_price));
	        	
	        	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
	        		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
	        	}
	        	
	        	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
	        		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
	        	}
	        	
	        	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
	        		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
	        	}
	        	
	        	if(yesterday_ranking_o_c_rate.contains(".") && ((yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("0") || (yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("."))) {
	        		yesterday_ranking_o_c_rate = yesterday_ranking_o_c_rate.substring(0, yesterday_ranking_o_c_rate.length()-1);
	        	}
	        	
	        	if(yesterday_ranking_o_c_rate.contains(".") && ((yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("0") || (yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("."))) {
	        		yesterday_ranking_o_c_rate = yesterday_ranking_o_c_rate.substring(0, yesterday_ranking_o_c_rate.length()-1);
	        	}
	        	
	        	if(yesterday_ranking_o_c_rate.contains(".") && ((yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("0") || (yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("."))) {
	        		yesterday_ranking_o_c_rate = yesterday_ranking_o_c_rate.substring(0, yesterday_ranking_o_c_rate.length()-1);
	        	}
	        	
	        	if(ranking_l_h_rate.contains(".") && ((ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("0") || (ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("."))) {
	        		ranking_l_h_rate = ranking_l_h_rate.substring(0, ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(ranking_l_h_rate.contains(".") && ((ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("0") || (ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("."))) {
	        		ranking_l_h_rate = ranking_l_h_rate.substring(0, ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(ranking_l_h_rate.contains(".") && ((ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("0") || (ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("."))) {
	        		ranking_l_h_rate = ranking_l_h_rate.substring(0, ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(yesterday_ranking_l_h_rate.contains(".") && ((yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("0") || (yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("."))) {
	        		yesterday_ranking_l_h_rate = yesterday_ranking_l_h_rate.substring(0, yesterday_ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(yesterday_ranking_l_h_rate.contains(".") && ((yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("0") || (yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("."))) {
	        		yesterday_ranking_l_h_rate = yesterday_ranking_l_h_rate.substring(0, yesterday_ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(yesterday_ranking_l_h_rate.contains(".") && ((yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("0") || (yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("."))) {
	        		yesterday_ranking_l_h_rate = yesterday_ranking_l_h_rate.substring(0, yesterday_ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(ranking_number.equals("0")) {
	        		ranking_number = "기준";
	        	}
	        	
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(ranking_number);
	        	ranking_sheet.getRow(RowNumber).getCell(2).setCellValue(ranking_coin_name);
	        	ranking_sheet.getRow(RowNumber).getCell(3).setCellValue(ranking_o_price);
	        	ranking_sheet.getRow(RowNumber).getCell(4).setCellValue(ranking_l_price);
	        	ranking_sheet.getRow(RowNumber).getCell(5).setCellValue(ranking_h_price);
	        	ranking_sheet.getRow(RowNumber).getCell(6).setCellValue(ranking_c_price);
	        	ranking_sheet.getRow(RowNumber).getCell(7).setCellValue(ranking_o_c_rate+"%");
	        	ranking_sheet.getRow(RowNumber).getCell(8).setCellValue(ranking_l_h_rate+"%");
	        	
	        	if(ranking_number.equals("기준")) {
	        		ranking_sheet.getRow(RowNumber).getCell(1).setCellStyle(cell_Ranking_Center);
	        	} else {
	        		ranking_sheet.getRow(RowNumber).getCell(1).setCellStyle(cell_Ranking_Right);
	        	}
        		ranking_sheet.getRow(RowNumber).getCell(2).setCellStyle(cell_Ranking_Left);
        		ranking_sheet.getRow(RowNumber).getCell(3).setCellStyle(cell_Ranking_Right);
        		ranking_sheet.getRow(RowNumber).getCell(4).setCellStyle(cell_Ranking_Right);
        		ranking_sheet.getRow(RowNumber).getCell(5).setCellStyle(cell_Ranking_Right);
        		ranking_sheet.getRow(RowNumber).getCell(6).setCellStyle(cell_Ranking_Right);
        		ranking_sheet.getRow(RowNumber).getCell(7).setCellStyle(cell_Ranking_Right);
        		ranking_sheet.getRow(RowNumber).getCell(8).setCellStyle(cell_Ranking_Right);
        		
        		if(x == 1) {
        			today1_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 2) {
        			today2_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 3) {
        			today3_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 4) {
        			today4_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 5) {
        			today5_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 6) {
        			today6_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 7) {
        			today7_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 8) {
        			today8_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 9) {
        			today9_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 10) {
        			today10_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 11) {
        			today11_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 12) {
        			today12_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 13) {
        			today13_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 14) {
        			today14_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 15) {
        			today15_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 16) {
        			today16_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 17) {
        			today17_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 18) {
        			today18_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 19) {
        			today19_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		} else if(x == 20) {
        			today20_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
        		}
	        }
	        
	        if(!today1_text.equals("")) {
	        	RowNumber = RowNumber+2;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today1_text);
	        }
	        if(!today2_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today2_text);
	        }
	        if(!today3_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today3_text);
	        }
	        if(!today4_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today4_text);
	        }
	        if(!today5_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today5_text);
	        }
	        if(!today6_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today6_text);
	        }
	        if(!today7_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today7_text);
	        }
	        if(!today8_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today8_text);
	        }
	        if(!today9_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today9_text);
	        }
	        if(!today10_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today10_text);
	        }
	        if(!today11_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today11_text);
	        }
	        if(!today12_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today12_text);
	        }
	        if(!today13_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today13_text);
	        }
	        if(!today14_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today14_text);
	        }
	        if(!today15_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today15_text);
	        }
	        if(!today16_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today16_text);
	        }
	        if(!today17_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today17_text);
	        }
	        if(!today18_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today18_text);
	        }
	        if(!today19_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today19_text);
	        }
	        if(!today20_text.equals("")) {
	        	RowNumber = RowNumber+1;
	        	Row RankingRow = ranking_sheet.createRow(RowNumber);
	        	RankingRow.createCell(1);
	        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today20_text);
	        }
	        
	        RowNumber = RowNumber+2;
        	Row RankingRow = ranking_sheet.createRow(RowNumber);
        	RankingRow.createCell(1);
        	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue("\n"+today1_text+"\n\n"+today2_text+"\n\n"+today3_text+"\n\n"+today4_text+"\n\n"+today5_text+"\n\n"+today6_text+"\n\n"+today7_text+"\n\n"+today8_text+"\n\n"+today9_text+"\n\n"+today10_text+"\n\n"+today11_text+"\n\n"+today12_text+"\n\n"+today13_text+"\n\n"+today14_text+"\n\n"+today15_text+"\n\n"+today16_text+"\n\n"+today17_text+"\n\n"+today18_text+"\n\n"+today19_text+"\n\n"+today20_text);
        	ranking_sheet.getRow(RowNumber).getCell(1).setCellStyle(cell_Ranking_List);
        	
	        Row row = summary_sheet.createRow(Sheet2_Row);
	        Cell cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row).getCell(1).setCellValue("업비트 " + kor_date + " 코인 분석");
	        
	        row = summary_sheet.createRow(Sheet2_Row+1);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+1).getCell(1).setCellValue("업비트 " + kor_date + " 코인 분석을 시작하겠습니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+3);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+3).getCell(1).setCellValue("BTC 기준 시가 대비 종가 " + excel_map.get("summary_1_1") + kor_date+ " 업비트 원화 코인 "+coin_total_count+"종 중 상승인 코인이 "+coin_increase_count+"종, 하락인 코인이 "+coin_degradation_count+"종, 보합인 코인이 "+coin_flat_count+"종입니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+4);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+4).getCell(1).setCellValue(kor_date + "에 투자했으면 가장 안정적으로 많이 벌 수 있었던 코인 순위를 알려드리겠습니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+6);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+6).getCell(1).setCellValue("오늘 코인 매매 시 한 번 고려 해보시기 바랍니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+8);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+8).getCell(1).setCellValue(short_kor_date + " 3순위 " + excel_map.get("coin3_ticker"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+10);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+10).getCell(1).setCellValue(kor_date + " " + excel_map.get("coin3_summary"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+12);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+12).getCell(1).setCellValue(short_kor_date + " 2순위 " + excel_map.get("coin2_ticker"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+14);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+14).getCell(1).setCellValue(kor_date + " " + excel_map.get("coin2_summary"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+16);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+16).getCell(1).setCellValue(short_kor_date + " 1순위 " + excel_map.get("coin1_ticker"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+18);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+18).getCell(1).setCellValue(kor_date + " " + excel_map.get("coin1_summary"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+20);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+20).getCell(1).setCellValue(short_kor_date+" 업비트 코인 요약");
	        
	        row = summary_sheet.createRow(Sheet2_Row+21);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+21).getCell(1).setCellValue(kor_date + " 업비트 코인 중 BTC가 " + excel_map.get("summary_1_1") + " 업비트 원화 코인 "+coin_total_count+"종 중 상승인 코인이 "+coin_increase_count+"종, 하락인 코인이 "+coin_degradation_count+"종, 보합인 코인이 "+coin_flat_count+"종입니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+23);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+23).getCell(1).setCellValue("광고");
	        	        
	        row = summary_sheet.createRow(Sheet2_Row+25);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+25).getCell(1).setCellValue("쿠팡 파트너스");
	        	        
	        row = summary_sheet.createRow(Sheet2_Row+26);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+26).getCell(1).setCellValue(coopang_partner);
	        
	        try {
	        	File file = new File("C:\\Coin\\MailExcel\\"+fileName);
	            
	        	if(file.exists()){
	        		if(file.delete()){
	        			System.out.println("파일 삭제 성공");
	        		}
	        	}
	        	FileOutputStream fileOut = new FileOutputStream("C:\\Coin\\MailExcel\\"+fileName);
	        	form_wb.write(fileOut);//파일 생성
	        	fileOut.close();
	        	form_wb.close();
	        } catch(IOException e) {
	        	
	        }
		} catch (Exception e) {
	        e.printStackTrace();
	    }
	 	*/
	}
	
	public void BithumbExcelDaily(String date, String yesterday, String fileName) throws Exception {
		/*
		DecimalFormat formatter = new DecimalFormat("###,###.########");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("date", date);
		map.put("yesterday", yesterday);
		
		try {
			InputStream fis = new FileInputStream(new File("C:/Coin/Excel/B_Daily.xlsx"));
		    XSSFWorkbook form_wb = new XSSFWorkbook(fis);
		    XSSFSheet form_sheet = form_wb.getSheetAt(0);
		    XSSFSheet ranking_sheet = form_wb.getSheetAt(1);
		    XSSFSheet summary_sheet = form_wb.createSheet("스크립트");
		    
		    List<HashMap<String, String>> result_map = BitCoinService.ExcelMake_Daily_Result(map);
		    String coin_total_count = result_map.get(0).get("total_count");
		    String coin_increase_count = result_map.get(0).get("increase_count");
		    String coin_degradation_count = result_map.get(0).get("degradation_count");
		    String coin_flat_count = result_map.get(0).get("flat_count");
		    String coin_increase_ticker = result_map.get(0).get("increase_ticker");
		    String coin_degradation_ticker = result_map.get(0).get("degradation_ticker");
		    String coin_flat_ticker = result_map.get(0).get("flat_ticker");
		    
		    List<HashMap<String, String>> ranking_list = BitCoinService.ExcelMake_Daily_Ranking(map);
		    
		    String result_data = " "+coin_total_count+" | " + coin_increase_count +" | " + coin_degradation_count +" | " + coin_flat_count;
		    form_wb.setSheetName(0, result_data);
		    
		    HashMap<String, String> excel_map = BitCoinService.ExcelMake_Daily(map);
		    
		    Font Font_Red = form_wb.createFont();
		    Font_Red.setFontName("맑은 고딕");
		    Font_Red.setColor(IndexedColors.RED.index);
		    
		    Font Font_Blue = form_wb.createFont();
		    Font_Blue.setFontName("맑은 고딕");
		    Font_Blue.setColor(IndexedColors.BLUE.index);
		    
		    CellStyle cell_Red = form_wb.createCellStyle();
		    cell_Red.setFont(Font_Red);
		    cell_Red.setAlignment(HorizontalAlignment.RIGHT);
		    cell_Red.setBorderTop(BorderStyle.THIN);
		    cell_Red.setBorderBottom(BorderStyle.THIN);
		    cell_Red.setBorderLeft(BorderStyle.THIN);
		    cell_Red.setBorderRight(BorderStyle.THIN);
		    
		    CellStyle cell_Red_Bottom = form_wb.createCellStyle();
		    cell_Red_Bottom.setFont(Font_Red);
		    cell_Red_Bottom.setAlignment(HorizontalAlignment.RIGHT);
		    cell_Red_Bottom.setBorderBottom(BorderStyle.MEDIUM);
		    
		    CellStyle cell_Blue = form_wb.createCellStyle();
		    cell_Blue.setFont(Font_Blue);
		    cell_Blue.setAlignment(HorizontalAlignment.RIGHT);
		    cell_Blue.setBorderTop(BorderStyle.THIN);
		    cell_Blue.setBorderBottom(BorderStyle.THIN);
		    cell_Blue.setBorderLeft(BorderStyle.THIN);
		    cell_Blue.setBorderRight(BorderStyle.THIN);
		    
		    CellStyle cell_Blue_Bottom = form_wb.createCellStyle();
		    cell_Blue_Bottom.setFont(Font_Blue);
		    cell_Blue_Bottom.setAlignment(HorizontalAlignment.RIGHT);
		    cell_Blue_Bottom.setBorderBottom(BorderStyle.MEDIUM);
		    
		    CellStyle cell_Ranking_Left = form_wb.createCellStyle();
		    cell_Ranking_Left.setAlignment(HorizontalAlignment.LEFT);
		    cell_Ranking_Left.setBorderTop(BorderStyle.THIN);
		    cell_Ranking_Left.setBorderBottom(BorderStyle.THIN);
		    cell_Ranking_Left.setBorderLeft(BorderStyle.THIN);
		    cell_Ranking_Left.setBorderRight(BorderStyle.THIN);
		    
		    CellStyle cell_Ranking_Right = form_wb.createCellStyle();
		    cell_Ranking_Right.setAlignment(HorizontalAlignment.RIGHT);
		    cell_Ranking_Right.setBorderTop(BorderStyle.THIN);
		    cell_Ranking_Right.setBorderBottom(BorderStyle.THIN);
		    cell_Ranking_Right.setBorderLeft(BorderStyle.THIN);
		    cell_Ranking_Right.setBorderRight(BorderStyle.THIN);
		    
		    CellStyle cell_Ranking_Center = form_wb.createCellStyle();
		    cell_Ranking_Center.setAlignment(HorizontalAlignment.CENTER);
		    cell_Ranking_Center.setBorderTop(BorderStyle.THIN);
		    cell_Ranking_Center.setBorderBottom(BorderStyle.THIN);
		    cell_Ranking_Center.setBorderLeft(BorderStyle.THIN);
		    cell_Ranking_Center.setBorderRight(BorderStyle.THIN);
		    
		    CellStyle cell_Ranking_List = form_wb.createCellStyle();
		    cell_Ranking_List.setAlignment(HorizontalAlignment.LEFT);
		    cell_Ranking_List.setWrapText(true);
		    
		    int Sheet1_Row = 1;
		    int Sheet2_Row = 1;
		    
		    String coopang_partner = "";
		    coopang_partner += "<br/>";
		    coopang_partner += "<a href=\"https://link.coupang.com/a/bctzMe\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://image5.coupangcdn.com/image/affiliate/banner/4c0754a7767da6ff093f71b999aaed60@2x.jpg\" alt=\"서른살 비트코인으로 퇴사합니다:투자 2년 경제적 자유를 얻다, 국일증권경제연구소, 강기태(세력\" width=\"110\" height=\"240\"></a>";
		    coopang_partner += "<a href=\"https://link.coupang.com/a/bctB5h\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://img5a.coupangcdn.com/image/affiliate/banner/579dbb3cd67c960da5a58adde964cfb8@2x.jpg\" alt=\"시골 영어 선생 비트코인 투자로 매월 1억 번다:, 국일증권경제연구소, 구대환\" width=\"110\" height=\"240\"></a>";
		    coopang_partner += "<a href=\"https://link.coupang.com/a/bctAo1\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://image6.coupangcdn.com/image/affiliate/banner/2f2f3b63a231fab01ea771b08986226f@2x.jpg\" alt=\"비트코인 2년 만에 흙수저 졸업했습니다.:강남 아파트 대신 비트코인에 미쳐라!, 청출판, 박보혜\" width=\"110\" height=\"240\"></a>";
		    coopang_partner += "<a href=\"https://link.coupang.com/a/bctBOI\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://image8.coupangcdn.com/image/affiliate/banner/4f719b97961b3ed9b64df21ec9ae4d10@2x.jpg\" alt=\"나는 가상화폐로 3달 만에 3억 벌었다:비트코인 이더리움 채굴기 실전투자 전략서, 이코노믹북스, 빈현우\" width=\"110\" height=\"240\"></a>";
		    coopang_partner += "<a href=\"https://link.coupang.com/a/bctBpi\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://image4.coupangcdn.com/image/affiliate/banner/834b320bfbaa409cbac27a7407ac7127@2x.jpg\" alt=\"코인으로 인생역전:전략 없이 비트코인 시장에 뛰어들지 마라!, 리치캠프, 홍지윤윤용욱정지훈류경문김민형최완순\" width=\"110\" height=\"240\"></a>";
		    coopang_partner += "<a href=\"https://link.coupang.com/a/bctARp\" target=\"_blank\" referrerpolicy=\"unsafe-url\" rel=\"noopener\"><img src=\"https://img1c.coupangcdn.com/image/affiliate/banner/3867c1e551232847e60b6a16ffcfb8d3@2x.jpg\" alt=\"부자의 길을 선택하다:비트코인 거물이 알려주는 운명을 바꾸는 투자 원칙, 메가스터디북스, 리샤오라이\" width=\"110\" height=\"240\"></a>";
		    coopang_partner += "<div style=\"text-align:center; font-size:9px;\">이 포스팅은 쿠팡 파트너스 활동의 일환으로, 이에 따른 일정액의 수수료를 제공받습니다.</div>";
		    coopang_partner += "<br/>";
		    
		    String adfit = "";
		    adfit += "<p><ins class=\"kakao_ad_area\" style=\"display:none;\" data-ad-unit = \"DAN-djDi0AsDMOpTDFgY\" data-ad-width = \"300\" data-ad-height = \"250\"></ins></p>";
		    adfit += "<script type=\"text/javascript\" src=\"//t1.daumcdn.net/kas/static/ba.min.js\"></script>";
		    
		    form_sheet.getRow(Sheet1_Row).getCell(1).setCellValue("빗썸 일일 분석 " + date);
		    form_sheet.getRow(Sheet1_Row+3).getCell(2).setCellValue(coin_increase_count);
		    form_sheet.getRow(Sheet1_Row+3).getCell(3).setCellValue(coin_increase_ticker);
		    form_sheet.getRow(Sheet1_Row+5).getCell(2).setCellValue(coin_flat_count);
		    form_sheet.getRow(Sheet1_Row+5).getCell(3).setCellValue(coin_flat_ticker);
		    form_sheet.getRow(Sheet1_Row+7).getCell(2).setCellValue(coin_degradation_count);
		    form_sheet.getRow(Sheet1_Row+7).getCell(3).setCellValue(coin_degradation_ticker);
		    form_sheet.getRow(Sheet1_Row+10).getCell(2).setCellValue(excel_map.get("btc_ticker"));
		    form_sheet.getRow(Sheet1_Row+12).getCell(3).setCellValue(excel_map.get("btc_o_price"));
		    form_sheet.getRow(Sheet1_Row+12).getCell(7).setCellValue(excel_map.get("btc_c_price"));
		    form_sheet.getRow(Sheet1_Row+13).getCell(3).setCellValue(excel_map.get("btc_l_price"));
		    form_sheet.getRow(Sheet1_Row+13).getCell(7).setCellValue(excel_map.get("btc_h_price"));
		    form_sheet.getRow(Sheet1_Row+14).getCell(3).setCellValue(excel_map.get("btc_open_close"));
		    form_sheet.getRow(Sheet1_Row+14).getCell(7).setCellValue(excel_map.get("btc_lowest_highest"));
		    form_sheet.getRow(Sheet1_Row+15).getCell(3).setCellValue(excel_map.get("btc_price_min_time"));
		    form_sheet.getRow(Sheet1_Row+15).getCell(7).setCellValue(excel_map.get("btc_price_max_time"));
		    form_sheet.getRow(Sheet1_Row+16).getCell(3).setCellValue(excel_map.get("btc_volume_min_time"));
		    form_sheet.getRow(Sheet1_Row+16).getCell(7).setCellValue(excel_map.get("btc_volume_max_time"));
		    
		    form_sheet.getRow(Sheet1_Row+11).getCell(10).setCellValue(excel_map.get("btc_oclh_1_o_price"));
		    form_sheet.getRow(Sheet1_Row+11).getCell(11).setCellValue(excel_map.get("btc_oclh_1_l_price"));
		    form_sheet.getRow(Sheet1_Row+11).getCell(12).setCellValue(excel_map.get("btc_oclh_1_h_price"));
		    form_sheet.getRow(Sheet1_Row+11).getCell(13).setCellValue(excel_map.get("btc_oclh_1_c_price"));
		    form_sheet.getRow(Sheet1_Row+11).getCell(14).setCellValue(excel_map.get("btc_oclh_1_trade_volume"));
		    if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+11).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+11).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+12).getCell(10).setCellValue(excel_map.get("btc_oclh_2_o_price"));
		    form_sheet.getRow(Sheet1_Row+12).getCell(11).setCellValue(excel_map.get("btc_oclh_2_l_price"));
		    form_sheet.getRow(Sheet1_Row+12).getCell(12).setCellValue(excel_map.get("btc_oclh_2_h_price"));
		    form_sheet.getRow(Sheet1_Row+12).getCell(13).setCellValue(excel_map.get("btc_oclh_2_c_price"));
		    form_sheet.getRow(Sheet1_Row+12).getCell(14).setCellValue(excel_map.get("btc_oclh_2_trade_volume"));
		    if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+12).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+12).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+13).getCell(10).setCellValue(excel_map.get("btc_oclh_3_o_price"));
		    form_sheet.getRow(Sheet1_Row+13).getCell(11).setCellValue(excel_map.get("btc_oclh_3_l_price"));
		    form_sheet.getRow(Sheet1_Row+13).getCell(12).setCellValue(excel_map.get("btc_oclh_3_h_price"));
		    form_sheet.getRow(Sheet1_Row+13).getCell(13).setCellValue(excel_map.get("btc_oclh_3_c_price"));
		    form_sheet.getRow(Sheet1_Row+13).getCell(14).setCellValue(excel_map.get("btc_oclh_3_trade_volume"));
		    if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+13).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+13).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+14).getCell(10).setCellValue(excel_map.get("btc_oclh_4_o_price"));
		    form_sheet.getRow(Sheet1_Row+14).getCell(11).setCellValue(excel_map.get("btc_oclh_4_l_price"));
		    form_sheet.getRow(Sheet1_Row+14).getCell(12).setCellValue(excel_map.get("btc_oclh_4_h_price"));
		    form_sheet.getRow(Sheet1_Row+14).getCell(13).setCellValue(excel_map.get("btc_oclh_4_c_price"));
		    form_sheet.getRow(Sheet1_Row+14).getCell(14).setCellValue(excel_map.get("btc_oclh_4_trade_volume"));
		    if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+14).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+14).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+15).getCell(10).setCellValue(excel_map.get("btc_oclh_5_o_price"));
		    form_sheet.getRow(Sheet1_Row+15).getCell(11).setCellValue(excel_map.get("btc_oclh_5_l_price"));
		    form_sheet.getRow(Sheet1_Row+15).getCell(12).setCellValue(excel_map.get("btc_oclh_5_h_price"));
		    form_sheet.getRow(Sheet1_Row+15).getCell(13).setCellValue(excel_map.get("btc_oclh_5_c_price"));
		    form_sheet.getRow(Sheet1_Row+15).getCell(14).setCellValue(excel_map.get("btc_oclh_5_trade_volume"));
		    if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+15).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+15).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+16).getCell(10).setCellValue(excel_map.get("btc_oclh_6_o_price"));
		    form_sheet.getRow(Sheet1_Row+16).getCell(11).setCellValue(excel_map.get("btc_oclh_6_l_price"));
		    form_sheet.getRow(Sheet1_Row+16).getCell(12).setCellValue(excel_map.get("btc_oclh_6_h_price"));
		    form_sheet.getRow(Sheet1_Row+16).getCell(13).setCellValue(excel_map.get("btc_oclh_6_c_price"));
		    form_sheet.getRow(Sheet1_Row+16).getCell(14).setCellValue(excel_map.get("btc_oclh_6_trade_volume"));
		    if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+16).getCell(11).setCellStyle(cell_Blue_Bottom); }
		    if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+16).getCell(12).setCellStyle(cell_Red_Bottom); }
		    
		    form_sheet.getRow(Sheet1_Row+18).getCell(2).setCellValue(excel_map.get("coin1_ticker"));
		    form_sheet.getRow(Sheet1_Row+20).getCell(3).setCellValue(excel_map.get("coin1_o_price"));
		    form_sheet.getRow(Sheet1_Row+20).getCell(7).setCellValue(excel_map.get("coin1_c_price"));
		    form_sheet.getRow(Sheet1_Row+21).getCell(3).setCellValue(excel_map.get("coin1_l_price"));
		    form_sheet.getRow(Sheet1_Row+21).getCell(7).setCellValue(excel_map.get("coin1_h_price"));
		    form_sheet.getRow(Sheet1_Row+22).getCell(3).setCellValue(excel_map.get("coin1_open_close"));
		    form_sheet.getRow(Sheet1_Row+22).getCell(7).setCellValue(excel_map.get("coin1_lowest_highest"));
		    form_sheet.getRow(Sheet1_Row+23).getCell(3).setCellValue(excel_map.get("coin1_price_min_time"));
		    form_sheet.getRow(Sheet1_Row+23).getCell(7).setCellValue(excel_map.get("coin1_price_max_time"));
		    form_sheet.getRow(Sheet1_Row+24).getCell(3).setCellValue(excel_map.get("coin1_volume_min_time"));
		    form_sheet.getRow(Sheet1_Row+24).getCell(7).setCellValue(excel_map.get("coin1_volume_max_time"));
		    
		    form_sheet.getRow(Sheet1_Row+19).getCell(10).setCellValue(excel_map.get("coin1_oclh_1_o_price"));
		    form_sheet.getRow(Sheet1_Row+19).getCell(11).setCellValue(excel_map.get("coin1_oclh_1_l_price"));
		    form_sheet.getRow(Sheet1_Row+19).getCell(12).setCellValue(excel_map.get("coin1_oclh_1_h_price"));
		    form_sheet.getRow(Sheet1_Row+19).getCell(13).setCellValue(excel_map.get("coin1_oclh_1_c_price"));
		    form_sheet.getRow(Sheet1_Row+19).getCell(14).setCellValue(excel_map.get("coin1_oclh_1_trade_volume"));
		    if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+19).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+19).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+20).getCell(10).setCellValue(excel_map.get("coin1_oclh_2_o_price"));
		    form_sheet.getRow(Sheet1_Row+20).getCell(11).setCellValue(excel_map.get("coin1_oclh_2_l_price"));
		    form_sheet.getRow(Sheet1_Row+20).getCell(12).setCellValue(excel_map.get("coin1_oclh_2_h_price"));
		    form_sheet.getRow(Sheet1_Row+20).getCell(13).setCellValue(excel_map.get("coin1_oclh_2_c_price"));
		    form_sheet.getRow(Sheet1_Row+20).getCell(14).setCellValue(excel_map.get("coin1_oclh_2_trade_volume"));
		    if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+20).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+20).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+21).getCell(10).setCellValue(excel_map.get("coin1_oclh_3_o_price"));
		    form_sheet.getRow(Sheet1_Row+21).getCell(11).setCellValue(excel_map.get("coin1_oclh_3_l_price"));
		    form_sheet.getRow(Sheet1_Row+21).getCell(12).setCellValue(excel_map.get("coin1_oclh_3_h_price"));
		    form_sheet.getRow(Sheet1_Row+21).getCell(13).setCellValue(excel_map.get("coin1_oclh_3_c_price"));
		    form_sheet.getRow(Sheet1_Row+21).getCell(14).setCellValue(excel_map.get("coin1_oclh_3_trade_volume"));
		    if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+21).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+21).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+22).getCell(10).setCellValue(excel_map.get("coin1_oclh_4_o_price"));
		    form_sheet.getRow(Sheet1_Row+22).getCell(11).setCellValue(excel_map.get("coin1_oclh_4_l_price"));
		    form_sheet.getRow(Sheet1_Row+22).getCell(12).setCellValue(excel_map.get("coin1_oclh_4_h_price"));
		    form_sheet.getRow(Sheet1_Row+22).getCell(13).setCellValue(excel_map.get("coin1_oclh_4_c_price"));
		    form_sheet.getRow(Sheet1_Row+22).getCell(14).setCellValue(excel_map.get("coin1_oclh_4_trade_volume"));
		    if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+22).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+22).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+23).getCell(10).setCellValue(excel_map.get("coin1_oclh_5_o_price"));
		    form_sheet.getRow(Sheet1_Row+23).getCell(11).setCellValue(excel_map.get("coin1_oclh_5_l_price"));
		    form_sheet.getRow(Sheet1_Row+23).getCell(12).setCellValue(excel_map.get("coin1_oclh_5_h_price"));
		    form_sheet.getRow(Sheet1_Row+23).getCell(13).setCellValue(excel_map.get("coin1_oclh_5_c_price"));
		    form_sheet.getRow(Sheet1_Row+23).getCell(14).setCellValue(excel_map.get("coin1_oclh_5_trade_volume"));
		    if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+23).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+23).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+24).getCell(10).setCellValue(excel_map.get("coin1_oclh_6_o_price"));
		    form_sheet.getRow(Sheet1_Row+24).getCell(11).setCellValue(excel_map.get("coin1_oclh_6_l_price"));
		    form_sheet.getRow(Sheet1_Row+24).getCell(12).setCellValue(excel_map.get("coin1_oclh_6_h_price"));
		    form_sheet.getRow(Sheet1_Row+24).getCell(13).setCellValue(excel_map.get("coin1_oclh_6_c_price"));
		    form_sheet.getRow(Sheet1_Row+24).getCell(14).setCellValue(excel_map.get("coin1_oclh_6_trade_volume"));
		    if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+24).getCell(11).setCellStyle(cell_Blue_Bottom); }
		    if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+24).getCell(12).setCellStyle(cell_Red_Bottom); }
		    
		    form_sheet.getRow(Sheet1_Row+26).getCell(2).setCellValue(excel_map.get("coin2_ticker"));
		    form_sheet.getRow(Sheet1_Row+28).getCell(3).setCellValue(excel_map.get("coin2_o_price"));
		    form_sheet.getRow(Sheet1_Row+28).getCell(7).setCellValue(excel_map.get("coin2_c_price"));
		    form_sheet.getRow(Sheet1_Row+29).getCell(3).setCellValue(excel_map.get("coin2_l_price"));
		    form_sheet.getRow(Sheet1_Row+29).getCell(7).setCellValue(excel_map.get("coin2_h_price"));
		    form_sheet.getRow(Sheet1_Row+30).getCell(3).setCellValue(excel_map.get("coin2_open_close"));
		    form_sheet.getRow(Sheet1_Row+30).getCell(7).setCellValue(excel_map.get("coin2_lowest_highest"));
		    form_sheet.getRow(Sheet1_Row+31).getCell(3).setCellValue(excel_map.get("coin2_price_min_time"));
		    form_sheet.getRow(Sheet1_Row+31).getCell(7).setCellValue(excel_map.get("coin2_price_max_time"));
		    form_sheet.getRow(Sheet1_Row+32).getCell(3).setCellValue(excel_map.get("coin2_volume_min_time"));
		    form_sheet.getRow(Sheet1_Row+32).getCell(7).setCellValue(excel_map.get("coin2_volume_max_time"));
		    
		    form_sheet.getRow(Sheet1_Row+27).getCell(10).setCellValue(excel_map.get("coin2_oclh_1_o_price"));
		    form_sheet.getRow(Sheet1_Row+27).getCell(11).setCellValue(excel_map.get("coin2_oclh_1_l_price"));
		    form_sheet.getRow(Sheet1_Row+27).getCell(12).setCellValue(excel_map.get("coin2_oclh_1_h_price"));
		    form_sheet.getRow(Sheet1_Row+27).getCell(13).setCellValue(excel_map.get("coin2_oclh_1_c_price"));
		    form_sheet.getRow(Sheet1_Row+27).getCell(14).setCellValue(excel_map.get("coin2_oclh_1_trade_volume"));
		    if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+27).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+27).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+28).getCell(10).setCellValue(excel_map.get("coin2_oclh_2_o_price"));
		    form_sheet.getRow(Sheet1_Row+28).getCell(11).setCellValue(excel_map.get("coin2_oclh_2_l_price"));
		    form_sheet.getRow(Sheet1_Row+28).getCell(12).setCellValue(excel_map.get("coin2_oclh_2_h_price"));
		    form_sheet.getRow(Sheet1_Row+28).getCell(13).setCellValue(excel_map.get("coin2_oclh_2_c_price"));
		    form_sheet.getRow(Sheet1_Row+28).getCell(14).setCellValue(excel_map.get("coin2_oclh_2_trade_volume"));
		    if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+28).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+28).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+29).getCell(10).setCellValue(excel_map.get("coin2_oclh_3_o_price"));
		    form_sheet.getRow(Sheet1_Row+29).getCell(11).setCellValue(excel_map.get("coin2_oclh_3_l_price"));
		    form_sheet.getRow(Sheet1_Row+29).getCell(12).setCellValue(excel_map.get("coin2_oclh_3_h_price"));
		    form_sheet.getRow(Sheet1_Row+29).getCell(13).setCellValue(excel_map.get("coin2_oclh_3_c_price"));
		    form_sheet.getRow(Sheet1_Row+29).getCell(14).setCellValue(excel_map.get("coin2_oclh_3_trade_volume"));
		    if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+29).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+29).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+30).getCell(10).setCellValue(excel_map.get("coin2_oclh_4_o_price"));
		    form_sheet.getRow(Sheet1_Row+30).getCell(11).setCellValue(excel_map.get("coin2_oclh_4_l_price"));
		    form_sheet.getRow(Sheet1_Row+30).getCell(12).setCellValue(excel_map.get("coin2_oclh_4_h_price"));
		    form_sheet.getRow(Sheet1_Row+30).getCell(13).setCellValue(excel_map.get("coin2_oclh_4_c_price"));
		    form_sheet.getRow(Sheet1_Row+30).getCell(14).setCellValue(excel_map.get("coin2_oclh_4_trade_volume"));
		    if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+30).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+30).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+31).getCell(10).setCellValue(excel_map.get("coin2_oclh_5_o_price"));
		    form_sheet.getRow(Sheet1_Row+31).getCell(11).setCellValue(excel_map.get("coin2_oclh_5_l_price"));
		    form_sheet.getRow(Sheet1_Row+31).getCell(12).setCellValue(excel_map.get("coin2_oclh_5_h_price"));
		    form_sheet.getRow(Sheet1_Row+31).getCell(13).setCellValue(excel_map.get("coin2_oclh_5_c_price"));
		    form_sheet.getRow(Sheet1_Row+31).getCell(14).setCellValue(excel_map.get("coin2_oclh_5_trade_volume"));
		    if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+31).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+31).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+32).getCell(10).setCellValue(excel_map.get("coin2_oclh_6_o_price"));
		    form_sheet.getRow(Sheet1_Row+32).getCell(11).setCellValue(excel_map.get("coin2_oclh_6_l_price"));
		    form_sheet.getRow(Sheet1_Row+32).getCell(12).setCellValue(excel_map.get("coin2_oclh_6_h_price"));
		    form_sheet.getRow(Sheet1_Row+32).getCell(13).setCellValue(excel_map.get("coin2_oclh_6_c_price"));
		    form_sheet.getRow(Sheet1_Row+32).getCell(14).setCellValue(excel_map.get("coin2_oclh_6_trade_volume"));
		    if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+32).getCell(11).setCellStyle(cell_Blue_Bottom); }
		    if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+32).getCell(12).setCellStyle(cell_Red_Bottom); }
		    
		    form_sheet.getRow(Sheet1_Row+34).getCell(2).setCellValue(excel_map.get("coin3_ticker"));
		    form_sheet.getRow(Sheet1_Row+36).getCell(3).setCellValue(excel_map.get("coin3_o_price"));
		    form_sheet.getRow(Sheet1_Row+36).getCell(7).setCellValue(excel_map.get("coin3_c_price"));
		    form_sheet.getRow(Sheet1_Row+37).getCell(3).setCellValue(excel_map.get("coin3_l_price"));
		    form_sheet.getRow(Sheet1_Row+37).getCell(7).setCellValue(excel_map.get("coin3_h_price"));
		    form_sheet.getRow(Sheet1_Row+38).getCell(3).setCellValue(excel_map.get("coin3_open_close"));
		    form_sheet.getRow(Sheet1_Row+38).getCell(7).setCellValue(excel_map.get("coin3_lowest_highest"));
		    form_sheet.getRow(Sheet1_Row+39).getCell(3).setCellValue(excel_map.get("coin3_price_min_time"));
		    form_sheet.getRow(Sheet1_Row+39).getCell(7).setCellValue(excel_map.get("coin3_price_max_time"));
		    form_sheet.getRow(Sheet1_Row+40).getCell(3).setCellValue(excel_map.get("coin3_volume_min_time"));
		    form_sheet.getRow(Sheet1_Row+40).getCell(7).setCellValue(excel_map.get("coin3_volume_max_time"));
		    
		    form_sheet.getRow(Sheet1_Row+35).getCell(10).setCellValue(excel_map.get("coin3_oclh_1_o_price"));
		    form_sheet.getRow(Sheet1_Row+35).getCell(11).setCellValue(excel_map.get("coin3_oclh_1_l_price"));
		    form_sheet.getRow(Sheet1_Row+35).getCell(12).setCellValue(excel_map.get("coin3_oclh_1_h_price"));
		    form_sheet.getRow(Sheet1_Row+35).getCell(13).setCellValue(excel_map.get("coin3_oclh_1_c_price"));
		    form_sheet.getRow(Sheet1_Row+35).getCell(14).setCellValue(excel_map.get("coin3_oclh_1_trade_volume"));
		    if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+35).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+35).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+36).getCell(10).setCellValue(excel_map.get("coin3_oclh_2_o_price"));
		    form_sheet.getRow(Sheet1_Row+36).getCell(11).setCellValue(excel_map.get("coin3_oclh_2_l_price"));
		    form_sheet.getRow(Sheet1_Row+36).getCell(12).setCellValue(excel_map.get("coin3_oclh_2_h_price"));
		    form_sheet.getRow(Sheet1_Row+36).getCell(13).setCellValue(excel_map.get("coin3_oclh_2_c_price"));
		    form_sheet.getRow(Sheet1_Row+36).getCell(14).setCellValue(excel_map.get("coin3_oclh_2_trade_volume"));
		    if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+36).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+36).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+37).getCell(10).setCellValue(excel_map.get("coin3_oclh_3_o_price"));
		    form_sheet.getRow(Sheet1_Row+37).getCell(11).setCellValue(excel_map.get("coin3_oclh_3_l_price"));
		    form_sheet.getRow(Sheet1_Row+37).getCell(12).setCellValue(excel_map.get("coin3_oclh_3_h_price"));
		    form_sheet.getRow(Sheet1_Row+37).getCell(13).setCellValue(excel_map.get("coin3_oclh_3_c_price"));
		    form_sheet.getRow(Sheet1_Row+37).getCell(14).setCellValue(excel_map.get("coin3_oclh_3_trade_volume"));
		    if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+37).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+37).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+38).getCell(10).setCellValue(excel_map.get("coin3_oclh_4_o_price"));
		    form_sheet.getRow(Sheet1_Row+38).getCell(11).setCellValue(excel_map.get("coin3_oclh_4_l_price"));
		    form_sheet.getRow(Sheet1_Row+38).getCell(12).setCellValue(excel_map.get("coin3_oclh_4_h_price"));
		    form_sheet.getRow(Sheet1_Row+38).getCell(13).setCellValue(excel_map.get("coin3_oclh_4_c_price"));
		    form_sheet.getRow(Sheet1_Row+38).getCell(14).setCellValue(excel_map.get("coin3_oclh_4_trade_volume"));
		    if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+38).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+38).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+39).getCell(10).setCellValue(excel_map.get("coin3_oclh_5_o_price"));
		    form_sheet.getRow(Sheet1_Row+39).getCell(11).setCellValue(excel_map.get("coin3_oclh_5_l_price"));
		    form_sheet.getRow(Sheet1_Row+39).getCell(12).setCellValue(excel_map.get("coin3_oclh_5_h_price"));
		    form_sheet.getRow(Sheet1_Row+39).getCell(13).setCellValue(excel_map.get("coin3_oclh_5_c_price"));
		    form_sheet.getRow(Sheet1_Row+39).getCell(14).setCellValue(excel_map.get("coin3_oclh_5_trade_volume"));
		    if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+39).getCell(11).setCellStyle(cell_Blue); }
		    if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+39).getCell(12).setCellStyle(cell_Red); }
		    
		    form_sheet.getRow(Sheet1_Row+40).getCell(10).setCellValue(excel_map.get("coin3_oclh_6_o_price"));
		    form_sheet.getRow(Sheet1_Row+40).getCell(11).setCellValue(excel_map.get("coin3_oclh_6_l_price"));
		    form_sheet.getRow(Sheet1_Row+40).getCell(12).setCellValue(excel_map.get("coin3_oclh_6_h_price"));
		    form_sheet.getRow(Sheet1_Row+40).getCell(13).setCellValue(excel_map.get("coin3_oclh_6_c_price"));
		    form_sheet.getRow(Sheet1_Row+40).getCell(14).setCellValue(excel_map.get("coin3_oclh_6_trade_volume"));
		    if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+40).getCell(11).setCellStyle(cell_Blue_Bottom); }
		    if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+40).getCell(12).setCellStyle(cell_Red_Bottom); }
		    
		    String kor_date = date.substring(0, 4) + "년 "+date.substring(5, 7)+"월 "+(date.substring(8, 9)).replace("0", "") + (date.substring(9, 10)) +"일";
		    String short_kor_date = date.substring(5, 7)+"월 "+(date.substring(8, 9)).replace("0", "") + (date.substring(9, 10)) +"일";
		    
		    String yesterday_kor_date = yesterday.substring(0, 4) + "년 "+yesterday.substring(5, 7)+"월 "+(yesterday.substring(8, 9)).replace("0", "") + (yesterday.substring(9, 10)) +"일";
		    String yesterday_short_kor_date = yesterday.substring(5, 7)+"월 "+(yesterday.substring(8, 9)).replace("0", "") + (yesterday.substring(9, 10)) +"일";
		    
		    int RowNumber = 0;
		    String today1_text = "";
		    String today2_text = "";
		    String today3_text = "";
		    String today4_text = "";
		    String today5_text = "";
		    String today6_text = "";
		    String today7_text = "";
		    String today8_text = "";
		    String today9_text = "";
		    String today10_text = "";
		    String today11_text = "";
		    String today12_text = "";
		    String today13_text = "";
		    String today14_text = "";
		    String today15_text = "";
		    String today16_text = "";
		    String today17_text = "";
		    String today18_text = "";
		    String today19_text = "";
		    String today20_text = "";
		    for(int x=0; x<ranking_list.size(); x++) {
		    	RowNumber = (x+2);
		    	Row RankingRow = ranking_sheet.createRow(x+2);
		    	RankingRow.createCell(1);
		    	RankingRow.createCell(2);
		    	RankingRow.createCell(3);
		    	RankingRow.createCell(4);
		    	RankingRow.createCell(5);
		    	RankingRow.createCell(6);
		    	RankingRow.createCell(7);
		    	RankingRow.createCell(8);
		    	
		    	String ranking_number = (ranking_list.get(x).get("ranking"));
		    	String ranking_coin_name = (ranking_list.get(x).get("coin_name"));
		    	String ranking_o_price = (ranking_list.get(x).get("o_price")).toString();
		    	String ranking_l_price = (ranking_list.get(x).get("l_price")).toString();
		    	String ranking_h_price = (ranking_list.get(x).get("h_price")).toString();
		    	String ranking_c_price = (ranking_list.get(x).get("c_price")).toString();
		    	String ranking_o_c_rate = (ranking_list.get(x).get("o_c_price_rate")).toString();
		    	String ranking_l_h_rate = (ranking_list.get(x).get("l_h_price_rate")).toString();
		    	
		    	String yesterday_ranking_number = (ranking_list.get(x).get("before_ranking"));
		    	String yesterday_ranking_o_price = (ranking_list.get(x).get("before_o_price")).toString();
		    	String yesterday_ranking_l_price = (ranking_list.get(x).get("before_l_price")).toString();
		    	String yesterday_ranking_h_price = (ranking_list.get(x).get("before_h_price")).toString();
		    	String yesterday_ranking_c_price = (ranking_list.get(x).get("before_c_price")).toString();
		    	String yesterday_ranking_o_c_rate = (ranking_list.get(x).get("before_o_c_price_rate")).toString();
		    	String yesterday_ranking_l_h_rate = (ranking_list.get(x).get("before_l_h_price_rate")).toString();
		    	
		    	ranking_o_price = formatter.format(Double.parseDouble(ranking_o_price));
		    	ranking_l_price = formatter.format(Double.parseDouble(ranking_l_price));
		    	ranking_h_price = formatter.format(Double.parseDouble(ranking_h_price));
		    	ranking_c_price = formatter.format(Double.parseDouble(ranking_c_price));
		    	
		    	yesterday_ranking_o_price = formatter.format(Double.parseDouble(yesterday_ranking_o_price));
		    	yesterday_ranking_l_price = formatter.format(Double.parseDouble(yesterday_ranking_l_price));
		    	yesterday_ranking_h_price = formatter.format(Double.parseDouble(yesterday_ranking_h_price));
		    	yesterday_ranking_c_price = formatter.format(Double.parseDouble(yesterday_ranking_c_price));
		    	
		    	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
		    		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
		    	}
		    	
		    	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
		    		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
		    	}
		    	
		    	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
		    		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
		    	}
		    	
		    	if(yesterday_ranking_o_c_rate.contains(".") && ((yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("0") || (yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("."))) {
		    		yesterday_ranking_o_c_rate = yesterday_ranking_o_c_rate.substring(0, yesterday_ranking_o_c_rate.length()-1);
		    	}
		    	
		    	if(yesterday_ranking_o_c_rate.contains(".") && ((yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("0") || (yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("."))) {
		    		yesterday_ranking_o_c_rate = yesterday_ranking_o_c_rate.substring(0, yesterday_ranking_o_c_rate.length()-1);
		    	}
		    	
		    	if(yesterday_ranking_o_c_rate.contains(".") && ((yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("0") || (yesterday_ranking_o_c_rate.substring(yesterday_ranking_o_c_rate.length()-1)).equals("."))) {
		    		yesterday_ranking_o_c_rate = yesterday_ranking_o_c_rate.substring(0, yesterday_ranking_o_c_rate.length()-1);
		    	}
		    	
		    	if(ranking_l_h_rate.contains(".") && ((ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("0") || (ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("."))) {
		    		ranking_l_h_rate = ranking_l_h_rate.substring(0, ranking_l_h_rate.length()-1);
		    	}
		    	
		    	if(ranking_l_h_rate.contains(".") && ((ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("0") || (ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("."))) {
		    		ranking_l_h_rate = ranking_l_h_rate.substring(0, ranking_l_h_rate.length()-1);
		    	}
		    	
		    	if(ranking_l_h_rate.contains(".") && ((ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("0") || (ranking_l_h_rate.substring(ranking_l_h_rate.length()-1)).equals("."))) {
		    		ranking_l_h_rate = ranking_l_h_rate.substring(0, ranking_l_h_rate.length()-1);
		    	}
		    	
		    	if(yesterday_ranking_l_h_rate.contains(".") && ((yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("0") || (yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("."))) {
		    		yesterday_ranking_l_h_rate = yesterday_ranking_l_h_rate.substring(0, yesterday_ranking_l_h_rate.length()-1);
		    	}
		    	
		    	if(yesterday_ranking_l_h_rate.contains(".") && ((yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("0") || (yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("."))) {
		    		yesterday_ranking_l_h_rate = yesterday_ranking_l_h_rate.substring(0, yesterday_ranking_l_h_rate.length()-1);
		    	}
		    	
		    	if(yesterday_ranking_l_h_rate.contains(".") && ((yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("0") || (yesterday_ranking_l_h_rate.substring(yesterday_ranking_l_h_rate.length()-1)).equals("."))) {
		    		yesterday_ranking_l_h_rate = yesterday_ranking_l_h_rate.substring(0, yesterday_ranking_l_h_rate.length()-1);
		    	}
		    	
		    	if(ranking_number.equals("0")) {
		    		ranking_number = "기준";
		    	}
		    	
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(ranking_number);
		    	ranking_sheet.getRow(RowNumber).getCell(2).setCellValue(ranking_coin_name);
		    	ranking_sheet.getRow(RowNumber).getCell(3).setCellValue(ranking_o_price);
		    	ranking_sheet.getRow(RowNumber).getCell(4).setCellValue(ranking_l_price);
		    	ranking_sheet.getRow(RowNumber).getCell(5).setCellValue(ranking_h_price);
		    	ranking_sheet.getRow(RowNumber).getCell(6).setCellValue(ranking_c_price);
		    	ranking_sheet.getRow(RowNumber).getCell(7).setCellValue(ranking_o_c_rate+"%");
		    	ranking_sheet.getRow(RowNumber).getCell(8).setCellValue(ranking_l_h_rate+"%");
		    	
		    	if(ranking_number.equals("기준")) {
		    		ranking_sheet.getRow(RowNumber).getCell(1).setCellStyle(cell_Ranking_Center);
		    	} else {
		    		ranking_sheet.getRow(RowNumber).getCell(1).setCellStyle(cell_Ranking_Right);
		    	}
				ranking_sheet.getRow(RowNumber).getCell(2).setCellStyle(cell_Ranking_Left);
				ranking_sheet.getRow(RowNumber).getCell(3).setCellStyle(cell_Ranking_Right);
				ranking_sheet.getRow(RowNumber).getCell(4).setCellStyle(cell_Ranking_Right);
				ranking_sheet.getRow(RowNumber).getCell(5).setCellStyle(cell_Ranking_Right);
				ranking_sheet.getRow(RowNumber).getCell(6).setCellStyle(cell_Ranking_Right);
				ranking_sheet.getRow(RowNumber).getCell(7).setCellStyle(cell_Ranking_Right);
				ranking_sheet.getRow(RowNumber).getCell(8).setCellStyle(cell_Ranking_Right);
				
				if(x == 1) {
					today1_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 2) {
					today2_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 3) {
					today3_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 4) {
					today4_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 5) {
					today5_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 6) {
					today6_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 7) {
					today7_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 8) {
					today8_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 9) {
					today9_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 10) {
					today10_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 11) {
					today11_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 12) {
					today12_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 13) {
					today13_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 14) {
					today14_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 15) {
					today15_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 16) {
					today16_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 17) {
					today17_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 18) {
					today18_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 19) {
					today19_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				} else if(x == 20) {
					today20_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 어제[" + yesterday_short_kor_date + "]는 " + yesterday_ranking_o_c_rate + "%로 "+ yesterday_ranking_number +"등을 했습니다." ;
				}
		    }
		    
		    if(!today1_text.equals("")) {
		    	RowNumber = RowNumber+2;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today1_text);
		    }
		    if(!today2_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today2_text);
		    }
		    if(!today3_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today3_text);
		    }
		    if(!today4_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today4_text);
		    }
		    if(!today5_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today5_text);
		    }
		    if(!today6_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today6_text);
		    }
		    if(!today7_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today7_text);
		    }
		    if(!today8_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today8_text);
		    }
		    if(!today9_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today9_text);
		    }
		    if(!today10_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today10_text);
		    }
		    if(!today11_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today11_text);
		    }
		    if(!today12_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today12_text);
		    }
		    if(!today13_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today13_text);
		    }
		    if(!today14_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today14_text);
		    }
		    if(!today15_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today15_text);
		    }
		    if(!today16_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today16_text);
		    }
		    if(!today17_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today17_text);
		    }
		    if(!today18_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today18_text);
		    }
		    if(!today19_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today19_text);
		    }
		    if(!today20_text.equals("")) {
		    	RowNumber = RowNumber+1;
		    	Row RankingRow = ranking_sheet.createRow(RowNumber);
		    	RankingRow.createCell(1);
		    	ranking_sheet.getRow(RowNumber).getCell(1).setCellValue(today20_text);
		    }
		    
		    RowNumber = RowNumber+2;
			Row RankingRow = ranking_sheet.createRow(RowNumber);
			RankingRow.createCell(1);
			ranking_sheet.getRow(RowNumber).getCell(1).setCellValue("\n"+today1_text+"\n\n"+today2_text+"\n\n"+today3_text+"\n\n"+today4_text+"\n\n"+today5_text+"\n\n"+today6_text+"\n\n"+today7_text+"\n\n"+today8_text+"\n\n"+today9_text+"\n\n"+today10_text+"\n\n"+today11_text+"\n\n"+today12_text+"\n\n"+today13_text+"\n\n"+today14_text+"\n\n"+today15_text+"\n\n"+today16_text+"\n\n"+today17_text+"\n\n"+today18_text+"\n\n"+today19_text+"\n\n"+today20_text);
			ranking_sheet.getRow(RowNumber).getCell(1).setCellStyle(cell_Ranking_List);
			
		    Row row = summary_sheet.createRow(Sheet2_Row);
		    Cell cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row).getCell(1).setCellValue("빗썸 " + kor_date + " 코인 분석");
		    
		    row = summary_sheet.createRow(Sheet2_Row+1);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+1).getCell(1).setCellValue("빗썸 " + kor_date + " 코인 분석을 시작하겠습니다.");
		    
		    row = summary_sheet.createRow(Sheet2_Row+3);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+3).getCell(1).setCellValue("BTC 기준 시가 대비 종가 " + excel_map.get("summary_1_1") + kor_date+ " 빗썸 원화 코인 "+coin_total_count+"종 중 상승인 코인이 "+coin_increase_count+"종, 하락인 코인이 "+coin_degradation_count+"종, 보합인 코인이 "+coin_flat_count+"종입니다.");
		    
		    row = summary_sheet.createRow(Sheet2_Row+4);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+4).getCell(1).setCellValue(kor_date + "에 투자했으면 가장 안정적으로 많이 벌 수 있었던 코인 순위를 알려드리겠습니다.");
		    
		    row = summary_sheet.createRow(Sheet2_Row+6);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+6).getCell(1).setCellValue("오늘 코인 매매 시 한 번 고려 해보시기 바랍니다.");
		    
		    row = summary_sheet.createRow(Sheet2_Row+8);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+8).getCell(1).setCellValue(short_kor_date + " 3순위 " + excel_map.get("coin3_ticker"));
		    
		    row = summary_sheet.createRow(Sheet2_Row+10);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+10).getCell(1).setCellValue(kor_date + " " + excel_map.get("coin3_summary"));
		    
		    row = summary_sheet.createRow(Sheet2_Row+12);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+12).getCell(1).setCellValue(short_kor_date + " 2순위 " + excel_map.get("coin2_ticker"));
		    
		    row = summary_sheet.createRow(Sheet2_Row+14);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+14).getCell(1).setCellValue(kor_date + " " + excel_map.get("coin2_summary"));
		    
		    row = summary_sheet.createRow(Sheet2_Row+16);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+16).getCell(1).setCellValue(short_kor_date + " 1순위 " + excel_map.get("coin1_ticker"));
		    
		    row = summary_sheet.createRow(Sheet2_Row+18);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+18).getCell(1).setCellValue(kor_date + " " + excel_map.get("coin1_summary"));
		    
		    row = summary_sheet.createRow(Sheet2_Row+20);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+20).getCell(1).setCellValue(short_kor_date+" 빗썸 코인 요약");
		    
		    row = summary_sheet.createRow(Sheet2_Row+21);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+21).getCell(1).setCellValue(kor_date + " 빗썸 코인 중 BTC가 " + excel_map.get("summary_1_1") + " 빗썸 원화 코인 "+coin_total_count+"종 중 상승인 코인이 "+coin_increase_count+"종, 하락인 코인이 "+coin_degradation_count+"종, 보합인 코인이 "+coin_flat_count+"종입니다.");
		    
		    row = summary_sheet.createRow(Sheet2_Row+23);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+23).getCell(1).setCellValue("광고");
		    	        
		    row = summary_sheet.createRow(Sheet2_Row+25);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+25).getCell(1).setCellValue("쿠팡 파트너스");
		    	        
		    row = summary_sheet.createRow(Sheet2_Row+26);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+26).getCell(1).setCellValue(coopang_partner);
		    
		    row = summary_sheet.createRow(Sheet2_Row+28);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+28).getCell(1).setCellValue("애드핏");
		    	        
		    row = summary_sheet.createRow(Sheet2_Row+29);
		    cell = row.createCell(1);
		    summary_sheet.getRow(Sheet2_Row+29).getCell(1).setCellValue(adfit);
		    
		    try {
		    	File file = new File("C:\\Coin\\MailExcel\\"+fileName);
		        
		    	if(file.exists()){
		    		if(file.delete()){
		    			System.out.println("파일 삭제 성공");
		    		}
		    	}
		    	FileOutputStream fileOut = new FileOutputStream("C:\\Coin\\MailExcel\\"+fileName);
		    	form_wb.write(fileOut);//파일 생성
		    	fileOut.close();
		    	form_wb.close();
		    } catch(IOException e) {
		    	
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		*/
	}
}
