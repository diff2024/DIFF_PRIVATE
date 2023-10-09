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
import org.apache.poi.sl.usermodel.Sheet;
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

@RestController
@RequestMapping("/Upbit")
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
	
	@GetMapping(path = "/ExcelMake_Daily")
	public void ExcelMake_Daily(HttpServletRequest req, HttpServletResponse response) throws Exception {
		String date = (req.getParameter("std_date")==null)?"":req.getParameter("std_date");
		
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("date", date);
        
        String fileName = "";
		try {
			req.setCharacterEncoding("UTF-8");
			String path = CoinController.class.getResource("").getPath();
			InputStream fis = new FileInputStream(new File(path+"/Excel/Daily.xlsx"));
	        XSSFWorkbook form_wb = new XSSFWorkbook(fis);
	        XSSFSheet form_sheet = form_wb.getSheetAt(0);
	        XSSFSheet summary_sheet = form_wb.createSheet("요약");
	        
	        String result_data = CoinService.ExcelMake_Daily_Result(map);
	        form_wb.setSheetName(0, result_data);
	        
	        HashMap<String, String> excel_map = CoinService.ExcelMake_Daily(map);
	        
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

	        form_sheet.getRow(1).getCell(1).setCellValue("업비트 일일 분석 " + date);
	        form_sheet.getRow(4).getCell(2).setCellValue(excel_map.get("btc_ticker"));
	        form_sheet.getRow(6).getCell(3).setCellValue(excel_map.get("btc_o_price"));
	        form_sheet.getRow(6).getCell(7).setCellValue(excel_map.get("btc_c_price"));
	        form_sheet.getRow(7).getCell(3).setCellValue(excel_map.get("btc_l_price"));
	        form_sheet.getRow(7).getCell(7).setCellValue(excel_map.get("btc_h_price"));
	        form_sheet.getRow(8).getCell(3).setCellValue(excel_map.get("btc_open_close"));
	        form_sheet.getRow(8).getCell(7).setCellValue(excel_map.get("btc_lowest_highest"));
	        form_sheet.getRow(9).getCell(3).setCellValue(excel_map.get("btc_price_min_time"));
	        form_sheet.getRow(9).getCell(7).setCellValue(excel_map.get("btc_price_max_time"));
	        form_sheet.getRow(10).getCell(3).setCellValue(excel_map.get("btc_volume_min_time"));
	        form_sheet.getRow(10).getCell(7).setCellValue(excel_map.get("btc_volume_max_time"));
	        
	        form_sheet.getRow(5).getCell(10).setCellValue(excel_map.get("btc_oclh_1_o_price"));
	        form_sheet.getRow(5).getCell(11).setCellValue(excel_map.get("btc_oclh_1_l_price"));
	        form_sheet.getRow(5).getCell(12).setCellValue(excel_map.get("btc_oclh_1_h_price"));
	        form_sheet.getRow(5).getCell(13).setCellValue(excel_map.get("btc_oclh_1_c_price"));
	        form_sheet.getRow(5).getCell(14).setCellValue(excel_map.get("btc_oclh_1_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_1_l_price"))) { form_sheet.getRow(5).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_1_h_price"))) { form_sheet.getRow(5).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(6).getCell(10).setCellValue(excel_map.get("btc_oclh_2_o_price"));
	        form_sheet.getRow(6).getCell(11).setCellValue(excel_map.get("btc_oclh_2_l_price"));
	        form_sheet.getRow(6).getCell(12).setCellValue(excel_map.get("btc_oclh_2_h_price"));
	        form_sheet.getRow(6).getCell(13).setCellValue(excel_map.get("btc_oclh_2_c_price"));
	        form_sheet.getRow(6).getCell(14).setCellValue(excel_map.get("btc_oclh_2_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_2_l_price"))) { form_sheet.getRow(6).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_2_h_price"))) { form_sheet.getRow(6).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(7).getCell(10).setCellValue(excel_map.get("btc_oclh_3_o_price"));
	        form_sheet.getRow(7).getCell(11).setCellValue(excel_map.get("btc_oclh_3_l_price"));
	        form_sheet.getRow(7).getCell(12).setCellValue(excel_map.get("btc_oclh_3_h_price"));
	        form_sheet.getRow(7).getCell(13).setCellValue(excel_map.get("btc_oclh_3_c_price"));
	        form_sheet.getRow(7).getCell(14).setCellValue(excel_map.get("btc_oclh_3_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_3_l_price"))) { form_sheet.getRow(7).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_3_h_price"))) { form_sheet.getRow(7).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(8).getCell(10).setCellValue(excel_map.get("btc_oclh_4_o_price"));
	        form_sheet.getRow(8).getCell(11).setCellValue(excel_map.get("btc_oclh_4_l_price"));
	        form_sheet.getRow(8).getCell(12).setCellValue(excel_map.get("btc_oclh_4_h_price"));
	        form_sheet.getRow(8).getCell(13).setCellValue(excel_map.get("btc_oclh_4_c_price"));
	        form_sheet.getRow(8).getCell(14).setCellValue(excel_map.get("btc_oclh_4_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_4_l_price"))) { form_sheet.getRow(8).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_4_h_price"))) { form_sheet.getRow(8).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(9).getCell(10).setCellValue(excel_map.get("btc_oclh_5_o_price"));
	        form_sheet.getRow(9).getCell(11).setCellValue(excel_map.get("btc_oclh_5_l_price"));
	        form_sheet.getRow(9).getCell(12).setCellValue(excel_map.get("btc_oclh_5_h_price"));
	        form_sheet.getRow(9).getCell(13).setCellValue(excel_map.get("btc_oclh_5_c_price"));
	        form_sheet.getRow(9).getCell(14).setCellValue(excel_map.get("btc_oclh_5_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_5_l_price"))) { form_sheet.getRow(9).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_5_h_price"))) { form_sheet.getRow(9).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(10).getCell(10).setCellValue(excel_map.get("btc_oclh_6_o_price"));
	        form_sheet.getRow(10).getCell(11).setCellValue(excel_map.get("btc_oclh_6_l_price"));
	        form_sheet.getRow(10).getCell(12).setCellValue(excel_map.get("btc_oclh_6_h_price"));
	        form_sheet.getRow(10).getCell(13).setCellValue(excel_map.get("btc_oclh_6_c_price"));
	        form_sheet.getRow(10).getCell(14).setCellValue(excel_map.get("btc_oclh_6_trade_volume"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_6_l_price"))) { form_sheet.getRow(10).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_6_h_price"))) { form_sheet.getRow(10).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(12).getCell(2).setCellValue(excel_map.get("coin1_ticker"));
	        form_sheet.getRow(14).getCell(3).setCellValue(excel_map.get("coin1_o_price"));
	        form_sheet.getRow(14).getCell(7).setCellValue(excel_map.get("coin1_c_price"));
	        form_sheet.getRow(15).getCell(3).setCellValue(excel_map.get("coin1_l_price"));
	        form_sheet.getRow(15).getCell(7).setCellValue(excel_map.get("coin1_h_price"));
	        form_sheet.getRow(16).getCell(3).setCellValue(excel_map.get("coin1_open_close"));
	        form_sheet.getRow(16).getCell(7).setCellValue(excel_map.get("coin1_lowest_highest"));
	        form_sheet.getRow(17).getCell(3).setCellValue(excel_map.get("coin1_price_min_time"));
	        form_sheet.getRow(17).getCell(7).setCellValue(excel_map.get("coin1_price_max_time"));
	        form_sheet.getRow(18).getCell(3).setCellValue(excel_map.get("coin1_volume_min_time"));
	        form_sheet.getRow(18).getCell(7).setCellValue(excel_map.get("coin1_volume_max_time"));
	        
	        form_sheet.getRow(13).getCell(10).setCellValue(excel_map.get("coin1_oclh_1_o_price"));
	        form_sheet.getRow(13).getCell(11).setCellValue(excel_map.get("coin1_oclh_1_l_price"));
	        form_sheet.getRow(13).getCell(12).setCellValue(excel_map.get("coin1_oclh_1_h_price"));
	        form_sheet.getRow(13).getCell(13).setCellValue(excel_map.get("coin1_oclh_1_c_price"));
	        form_sheet.getRow(13).getCell(14).setCellValue(excel_map.get("coin1_oclh_1_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_1_l_price"))) { form_sheet.getRow(13).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_1_h_price"))) { form_sheet.getRow(13).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(14).getCell(10).setCellValue(excel_map.get("coin1_oclh_2_o_price"));
	        form_sheet.getRow(14).getCell(11).setCellValue(excel_map.get("coin1_oclh_2_l_price"));
	        form_sheet.getRow(14).getCell(12).setCellValue(excel_map.get("coin1_oclh_2_h_price"));
	        form_sheet.getRow(14).getCell(13).setCellValue(excel_map.get("coin1_oclh_2_c_price"));
	        form_sheet.getRow(14).getCell(14).setCellValue(excel_map.get("coin1_oclh_2_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_2_l_price"))) { form_sheet.getRow(14).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_2_h_price"))) { form_sheet.getRow(14).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(15).getCell(10).setCellValue(excel_map.get("coin1_oclh_3_o_price"));
	        form_sheet.getRow(15).getCell(11).setCellValue(excel_map.get("coin1_oclh_3_l_price"));
	        form_sheet.getRow(15).getCell(12).setCellValue(excel_map.get("coin1_oclh_3_h_price"));
	        form_sheet.getRow(15).getCell(13).setCellValue(excel_map.get("coin1_oclh_3_c_price"));
	        form_sheet.getRow(15).getCell(14).setCellValue(excel_map.get("coin1_oclh_3_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_3_l_price"))) { form_sheet.getRow(15).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_3_h_price"))) { form_sheet.getRow(15).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(16).getCell(10).setCellValue(excel_map.get("coin1_oclh_4_o_price"));
	        form_sheet.getRow(16).getCell(11).setCellValue(excel_map.get("coin1_oclh_4_l_price"));
	        form_sheet.getRow(16).getCell(12).setCellValue(excel_map.get("coin1_oclh_4_h_price"));
	        form_sheet.getRow(16).getCell(13).setCellValue(excel_map.get("coin1_oclh_4_c_price"));
	        form_sheet.getRow(16).getCell(14).setCellValue(excel_map.get("coin1_oclh_4_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_4_l_price"))) { form_sheet.getRow(16).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_4_h_price"))) { form_sheet.getRow(16).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(17).getCell(10).setCellValue(excel_map.get("coin1_oclh_5_o_price"));
	        form_sheet.getRow(17).getCell(11).setCellValue(excel_map.get("coin1_oclh_5_l_price"));
	        form_sheet.getRow(17).getCell(12).setCellValue(excel_map.get("coin1_oclh_5_h_price"));
	        form_sheet.getRow(17).getCell(13).setCellValue(excel_map.get("coin1_oclh_5_c_price"));
	        form_sheet.getRow(17).getCell(14).setCellValue(excel_map.get("coin1_oclh_5_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_5_l_price"))) { form_sheet.getRow(17).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_5_h_price"))) { form_sheet.getRow(17).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(18).getCell(10).setCellValue(excel_map.get("coin1_oclh_6_o_price"));
	        form_sheet.getRow(18).getCell(11).setCellValue(excel_map.get("coin1_oclh_6_l_price"));
	        form_sheet.getRow(18).getCell(12).setCellValue(excel_map.get("coin1_oclh_6_h_price"));
	        form_sheet.getRow(18).getCell(13).setCellValue(excel_map.get("coin1_oclh_6_c_price"));
	        form_sheet.getRow(18).getCell(14).setCellValue(excel_map.get("coin1_oclh_6_trade_volume"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_6_l_price"))) { form_sheet.getRow(18).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_6_h_price"))) { form_sheet.getRow(18).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(20).getCell(2).setCellValue(excel_map.get("coin2_ticker"));
	        form_sheet.getRow(22).getCell(3).setCellValue(excel_map.get("coin2_o_price"));
	        form_sheet.getRow(22).getCell(7).setCellValue(excel_map.get("coin2_c_price"));
	        form_sheet.getRow(23).getCell(3).setCellValue(excel_map.get("coin2_l_price"));
	        form_sheet.getRow(23).getCell(7).setCellValue(excel_map.get("coin2_h_price"));
	        form_sheet.getRow(24).getCell(3).setCellValue(excel_map.get("coin2_open_close"));
	        form_sheet.getRow(24).getCell(7).setCellValue(excel_map.get("coin2_lowest_highest"));
	        form_sheet.getRow(25).getCell(3).setCellValue(excel_map.get("coin2_price_min_time"));
	        form_sheet.getRow(25).getCell(7).setCellValue(excel_map.get("coin2_price_max_time"));
	        form_sheet.getRow(26).getCell(3).setCellValue(excel_map.get("coin2_volume_min_time"));
	        form_sheet.getRow(26).getCell(7).setCellValue(excel_map.get("coin2_volume_max_time"));
	        
	        form_sheet.getRow(21).getCell(10).setCellValue(excel_map.get("coin2_oclh_1_o_price"));
	        form_sheet.getRow(21).getCell(11).setCellValue(excel_map.get("coin2_oclh_1_l_price"));
	        form_sheet.getRow(21).getCell(12).setCellValue(excel_map.get("coin2_oclh_1_h_price"));
	        form_sheet.getRow(21).getCell(13).setCellValue(excel_map.get("coin2_oclh_1_c_price"));
	        form_sheet.getRow(21).getCell(14).setCellValue(excel_map.get("coin2_oclh_1_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_1_l_price"))) { form_sheet.getRow(21).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_1_h_price"))) { form_sheet.getRow(21).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(22).getCell(10).setCellValue(excel_map.get("coin2_oclh_2_o_price"));
	        form_sheet.getRow(22).getCell(11).setCellValue(excel_map.get("coin2_oclh_2_l_price"));
	        form_sheet.getRow(22).getCell(12).setCellValue(excel_map.get("coin2_oclh_2_h_price"));
	        form_sheet.getRow(22).getCell(13).setCellValue(excel_map.get("coin2_oclh_2_c_price"));
	        form_sheet.getRow(22).getCell(14).setCellValue(excel_map.get("coin2_oclh_2_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_2_l_price"))) { form_sheet.getRow(22).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_2_h_price"))) { form_sheet.getRow(22).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(23).getCell(10).setCellValue(excel_map.get("coin2_oclh_3_o_price"));
	        form_sheet.getRow(23).getCell(11).setCellValue(excel_map.get("coin2_oclh_3_l_price"));
	        form_sheet.getRow(23).getCell(12).setCellValue(excel_map.get("coin2_oclh_3_h_price"));
	        form_sheet.getRow(23).getCell(13).setCellValue(excel_map.get("coin2_oclh_3_c_price"));
	        form_sheet.getRow(23).getCell(14).setCellValue(excel_map.get("coin2_oclh_3_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_3_l_price"))) { form_sheet.getRow(23).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_3_h_price"))) { form_sheet.getRow(23).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(24).getCell(10).setCellValue(excel_map.get("coin2_oclh_4_o_price"));
	        form_sheet.getRow(24).getCell(11).setCellValue(excel_map.get("coin2_oclh_4_l_price"));
	        form_sheet.getRow(24).getCell(12).setCellValue(excel_map.get("coin2_oclh_4_h_price"));
	        form_sheet.getRow(24).getCell(13).setCellValue(excel_map.get("coin2_oclh_4_c_price"));
	        form_sheet.getRow(24).getCell(14).setCellValue(excel_map.get("coin2_oclh_4_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_4_l_price"))) { form_sheet.getRow(24).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_4_h_price"))) { form_sheet.getRow(24).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(25).getCell(10).setCellValue(excel_map.get("coin2_oclh_5_o_price"));
	        form_sheet.getRow(25).getCell(11).setCellValue(excel_map.get("coin2_oclh_5_l_price"));
	        form_sheet.getRow(25).getCell(12).setCellValue(excel_map.get("coin2_oclh_5_h_price"));
	        form_sheet.getRow(25).getCell(13).setCellValue(excel_map.get("coin2_oclh_5_c_price"));
	        form_sheet.getRow(25).getCell(14).setCellValue(excel_map.get("coin2_oclh_5_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_5_l_price"))) { form_sheet.getRow(25).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_5_h_price"))) { form_sheet.getRow(25).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(26).getCell(10).setCellValue(excel_map.get("coin2_oclh_6_o_price"));
	        form_sheet.getRow(26).getCell(11).setCellValue(excel_map.get("coin2_oclh_6_l_price"));
	        form_sheet.getRow(26).getCell(12).setCellValue(excel_map.get("coin2_oclh_6_h_price"));
	        form_sheet.getRow(26).getCell(13).setCellValue(excel_map.get("coin2_oclh_6_c_price"));
	        form_sheet.getRow(26).getCell(14).setCellValue(excel_map.get("coin2_oclh_6_trade_volume"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_6_l_price"))) { form_sheet.getRow(26).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_6_h_price"))) { form_sheet.getRow(26).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(28).getCell(2).setCellValue(excel_map.get("coin3_ticker"));
	        form_sheet.getRow(30).getCell(3).setCellValue(excel_map.get("coin3_o_price"));
	        form_sheet.getRow(30).getCell(7).setCellValue(excel_map.get("coin3_c_price"));
	        form_sheet.getRow(31).getCell(3).setCellValue(excel_map.get("coin3_l_price"));
	        form_sheet.getRow(31).getCell(7).setCellValue(excel_map.get("coin3_h_price"));
	        form_sheet.getRow(32).getCell(3).setCellValue(excel_map.get("coin3_open_close"));
	        form_sheet.getRow(32).getCell(7).setCellValue(excel_map.get("coin3_lowest_highest"));
	        form_sheet.getRow(33).getCell(3).setCellValue(excel_map.get("coin3_price_min_time"));
	        form_sheet.getRow(33).getCell(7).setCellValue(excel_map.get("coin3_price_max_time"));
	        form_sheet.getRow(34).getCell(3).setCellValue(excel_map.get("coin3_volume_min_time"));
	        form_sheet.getRow(34).getCell(7).setCellValue(excel_map.get("coin3_volume_max_time"));
	        
	        form_sheet.getRow(29).getCell(10).setCellValue(excel_map.get("coin3_oclh_1_o_price"));
	        form_sheet.getRow(29).getCell(11).setCellValue(excel_map.get("coin3_oclh_1_l_price"));
	        form_sheet.getRow(29).getCell(12).setCellValue(excel_map.get("coin3_oclh_1_h_price"));
	        form_sheet.getRow(29).getCell(13).setCellValue(excel_map.get("coin3_oclh_1_c_price"));
	        form_sheet.getRow(29).getCell(14).setCellValue(excel_map.get("coin3_oclh_1_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_1_l_price"))) { form_sheet.getRow(29).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_1_h_price"))) { form_sheet.getRow(29).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(30).getCell(10).setCellValue(excel_map.get("coin3_oclh_2_o_price"));
	        form_sheet.getRow(30).getCell(11).setCellValue(excel_map.get("coin3_oclh_2_l_price"));
	        form_sheet.getRow(30).getCell(12).setCellValue(excel_map.get("coin3_oclh_2_h_price"));
	        form_sheet.getRow(30).getCell(13).setCellValue(excel_map.get("coin3_oclh_2_c_price"));
	        form_sheet.getRow(30).getCell(14).setCellValue(excel_map.get("coin3_oclh_2_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_2_l_price"))) { form_sheet.getRow(30).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_2_h_price"))) { form_sheet.getRow(30).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(31).getCell(10).setCellValue(excel_map.get("coin3_oclh_3_o_price"));
	        form_sheet.getRow(31).getCell(11).setCellValue(excel_map.get("coin3_oclh_3_l_price"));
	        form_sheet.getRow(31).getCell(12).setCellValue(excel_map.get("coin3_oclh_3_h_price"));
	        form_sheet.getRow(31).getCell(13).setCellValue(excel_map.get("coin3_oclh_3_c_price"));
	        form_sheet.getRow(31).getCell(14).setCellValue(excel_map.get("coin3_oclh_3_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_3_l_price"))) { form_sheet.getRow(31).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_3_h_price"))) { form_sheet.getRow(31).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(32).getCell(10).setCellValue(excel_map.get("coin3_oclh_4_o_price"));
	        form_sheet.getRow(32).getCell(11).setCellValue(excel_map.get("coin3_oclh_4_l_price"));
	        form_sheet.getRow(32).getCell(12).setCellValue(excel_map.get("coin3_oclh_4_h_price"));
	        form_sheet.getRow(32).getCell(13).setCellValue(excel_map.get("coin3_oclh_4_c_price"));
	        form_sheet.getRow(32).getCell(14).setCellValue(excel_map.get("coin3_oclh_4_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_4_l_price"))) { form_sheet.getRow(32).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_4_h_price"))) { form_sheet.getRow(32).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(33).getCell(10).setCellValue(excel_map.get("coin3_oclh_5_o_price"));
	        form_sheet.getRow(33).getCell(11).setCellValue(excel_map.get("coin3_oclh_5_l_price"));
	        form_sheet.getRow(33).getCell(12).setCellValue(excel_map.get("coin3_oclh_5_h_price"));
	        form_sheet.getRow(33).getCell(13).setCellValue(excel_map.get("coin3_oclh_5_c_price"));
	        form_sheet.getRow(33).getCell(14).setCellValue(excel_map.get("coin3_oclh_5_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_5_l_price"))) { form_sheet.getRow(33).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_5_h_price"))) { form_sheet.getRow(33).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(34).getCell(10).setCellValue(excel_map.get("coin3_oclh_6_o_price"));
	        form_sheet.getRow(34).getCell(11).setCellValue(excel_map.get("coin3_oclh_6_l_price"));
	        form_sheet.getRow(34).getCell(12).setCellValue(excel_map.get("coin3_oclh_6_h_price"));
	        form_sheet.getRow(34).getCell(13).setCellValue(excel_map.get("coin3_oclh_6_c_price"));
	        form_sheet.getRow(34).getCell(14).setCellValue(excel_map.get("coin3_oclh_6_trade_volume"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_6_l_price"))) { form_sheet.getRow(34).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_6_h_price"))) { form_sheet.getRow(34).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        String kor_date = date.substring(0, 4) + "년 "+date.substring(5, 7)+"월 "+(date.substring(8, 9)).replace("0", "") + (date.substring(9, 10)) +"일";
	        String short_kor_date = date.substring(5, 7)+"월 "+(date.substring(8, 9)).replace("0", "") + (date.substring(9, 10)) +"일";
	        
	        Row row = summary_sheet.createRow(1);
	        Cell cell = row.createCell(1);
	        summary_sheet.getRow(1).getCell(1).setCellValue("업비트 " + kor_date + " 코인 분석");
	        
	        row = summary_sheet.createRow(2);
	        cell = row.createCell(1);
	        summary_sheet.getRow(2).getCell(1).setCellValue("업비트 " + kor_date + " 코인 분석을 시작하겠습니다.");
	        
	        row = summary_sheet.createRow(3);
	        cell = row.createCell(1);
	        summary_sheet.getRow(3).getCell(1).setCellValue("모든 코인의 기준이 되는 비트코인[BTC]부터 확인하도록 하겠습니다.");
	        
	        row = summary_sheet.createRow(4);
	        cell = row.createCell(1);
	        summary_sheet.getRow(4).getCell(1).setCellValue("BTC 기준 시가 대비 종가 " + excel_map.get("summary_1_1") + kor_date+ " 업비트 원화 코인 중 116종 중 시가 대비 종가가 " + result_data.replace("상승 = ", "상승인 코인이 ").replace(" | 하락 = ", "종, 하락인 코인이 ").replace(" | 보합 = ", "종, 보합인 코인이 ") + "종입니다.");
	        
	        row = summary_sheet.createRow(5);
	        cell = row.createCell(1);
	        summary_sheet.getRow(5).getCell(1).setCellValue(kor_date + "에 투자했으면 가장 안정적으로 많이 벌 수 있었던 코인 3~1순위를 알려드리겠습니다.");
	        
	        row = summary_sheet.createRow(7);
	        cell = row.createCell(1);
	        summary_sheet.getRow(7).getCell(1).setCellValue("오늘 코인 투자 시 한 번 고려 해보시기 바랍니다.");
	        
	        row = summary_sheet.createRow(9);
	        cell = row.createCell(1);
	        summary_sheet.getRow(9).getCell(1).setCellValue(short_kor_date + " 3순위 " + excel_map.get("coin3_ticker"));
	        
	        row = summary_sheet.createRow(11);
	        cell = row.createCell(1);
	        summary_sheet.getRow(11).getCell(1).setCellValue(kor_date + " " + excel_map.get("coin3_summary"));
	        
	        row = summary_sheet.createRow(13);
	        cell = row.createCell(1);
	        summary_sheet.getRow(13).getCell(1).setCellValue(short_kor_date + " 2순위 " + excel_map.get("coin2_ticker"));
	        
	        row = summary_sheet.createRow(15);
	        cell = row.createCell(1);
	        summary_sheet.getRow(15).getCell(1).setCellValue(kor_date + " " + excel_map.get("coin2_summary"));
	        
	        row = summary_sheet.createRow(17);
	        cell = row.createCell(1);
	        summary_sheet.getRow(17).getCell(1).setCellValue(short_kor_date + " 1순위 " + excel_map.get("coin1_ticker"));
	        
	        row = summary_sheet.createRow(19);
	        cell = row.createCell(1);
	        summary_sheet.getRow(19).getCell(1).setCellValue(kor_date + " " + excel_map.get("coin1_summary"));
	        
	        row = summary_sheet.createRow(21);
	        cell = row.createCell(1);
	        summary_sheet.getRow(21).getCell(1).setCellValue(kor_date + " 업비트 코인 중 BTC가 " + excel_map.get("summary_1_1") + " 업비트 원화 코인 116종 중 " + result_data.replace("상승 = ", "상승인 코인이 ").replace(" | 하락 = ", "종, 하락인 코인이 ").replace(" | 보합 = ", "종, 보합인 코인이 ") + "종입니다.");
	        
	        //2023년 10월 7일에 투자했으면 가장 안정적으로 많이 벌 수 있었던 코인 1~3순위를 알려드리겠습니다.
	        //오늘 코인 투자 시 한 번 고려 해보시기 바랍니다.
	        fileName = "Daily_"+date+".xlsx";
	        
	        fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        response.setContentType("application/vsd.ms-excel");
	        response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
	        
	        form_wb.write(response.getOutputStream());
	        form_wb.close();
		} catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@GetMapping(path = "/ExcelMake_Weekly")
	public void ExcelMake_Weekly(HttpServletRequest req, HttpServletResponse response) throws Exception {
		String std_date = (req.getParameter("std_date")==null)?"":req.getParameter("std_date");
		String end_date = (req.getParameter("end_date")==null)?"":req.getParameter("end_date");
		
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("std_date", std_date);
        map.put("end_date", end_date);
        
		String fileName = "";
		try {
			req.setCharacterEncoding("UTF-8");
			String path = CoinController.class.getResource("").getPath();
			InputStream fis = new FileInputStream(new File(path+"/Excel/Weekly.xlsx"));
	        XSSFWorkbook form_wb = new XSSFWorkbook(fis);
	        XSSFSheet form_sheet = form_wb.getSheetAt(0);
	        XSSFSheet summary_sheet = form_wb.createSheet("요약");
	        
	        String result_data = CoinService.ExcelMake_Weekly_Result(map);
	        form_wb.setSheetName(0, result_data);  
	        
	        HashMap<String, String> excel_map = CoinService.ExcelMake_Weekly(map);
	        
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

	        form_sheet.getRow(1).getCell(1).setCellValue("업비트 주간 분석 " + std_date + " ~ " + end_date);
	        form_sheet.getRow(4).getCell(2).setCellValue(excel_map.get("btc_ticker"));
	        form_sheet.getRow(6).getCell(3).setCellValue(excel_map.get("btc_o_price"));
	        form_sheet.getRow(6).getCell(7).setCellValue(excel_map.get("btc_c_price"));
	        form_sheet.getRow(7).getCell(3).setCellValue(excel_map.get("btc_l_price"));
	        form_sheet.getRow(7).getCell(7).setCellValue(excel_map.get("btc_h_price"));
	        form_sheet.getRow(8).getCell(3).setCellValue(excel_map.get("btc_open_close"));
	        form_sheet.getRow(8).getCell(7).setCellValue(excel_map.get("btc_lowest_highest"));
	        form_sheet.getRow(9).getCell(3).setCellValue(excel_map.get("btc_5_rate_count"));
	        form_sheet.getRow(9).getCell(7).setCellValue(excel_map.get("btc_10_rate_count"));
	        form_sheet.getRow(10).getCell(3).setCellValue(excel_map.get("btc_15_rate_count"));
	        form_sheet.getRow(10).getCell(7).setCellValue(excel_map.get("btc_20_rate_count"));
	        form_sheet.getRow(11).getCell(3).setCellValue(excel_map.get("btc_25_rate_count"));
	        form_sheet.getRow(11).getCell(7).setCellValue(excel_map.get("btc_30_rate_count"));
	        
	        form_sheet.getRow(5).getCell(9).setCellValue(excel_map.get("btc_oclh_1_date"));
	        form_sheet.getRow(5).getCell(10).setCellValue(excel_map.get("btc_oclh_1_o_price"));
	        form_sheet.getRow(5).getCell(11).setCellValue(excel_map.get("btc_oclh_1_l_price"));
	        form_sheet.getRow(5).getCell(12).setCellValue(excel_map.get("btc_oclh_1_h_price"));
	        form_sheet.getRow(5).getCell(13).setCellValue(excel_map.get("btc_oclh_1_c_price"));
	        form_sheet.getRow(5).getCell(14).setCellValue(excel_map.get("btc_oclh_1_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_1_l_price"))) { form_sheet.getRow(5).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_1_h_price"))) { form_sheet.getRow(5).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(6).getCell(9).setCellValue(excel_map.get("btc_oclh_2_date"));
	        form_sheet.getRow(6).getCell(10).setCellValue(excel_map.get("btc_oclh_2_o_price"));
	        form_sheet.getRow(6).getCell(11).setCellValue(excel_map.get("btc_oclh_2_l_price"));
	        form_sheet.getRow(6).getCell(12).setCellValue(excel_map.get("btc_oclh_2_h_price"));
	        form_sheet.getRow(6).getCell(13).setCellValue(excel_map.get("btc_oclh_2_c_price"));
	        form_sheet.getRow(6).getCell(14).setCellValue(excel_map.get("btc_oclh_2_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_2_l_price"))) { form_sheet.getRow(6).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_2_h_price"))) { form_sheet.getRow(6).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(7).getCell(9).setCellValue(excel_map.get("btc_oclh_3_date"));
	        form_sheet.getRow(7).getCell(10).setCellValue(excel_map.get("btc_oclh_3_o_price"));
	        form_sheet.getRow(7).getCell(11).setCellValue(excel_map.get("btc_oclh_3_l_price"));
	        form_sheet.getRow(7).getCell(12).setCellValue(excel_map.get("btc_oclh_3_h_price"));
	        form_sheet.getRow(7).getCell(13).setCellValue(excel_map.get("btc_oclh_3_c_price"));
	        form_sheet.getRow(7).getCell(14).setCellValue(excel_map.get("btc_oclh_3_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_3_l_price"))) { form_sheet.getRow(7).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_3_h_price"))) { form_sheet.getRow(7).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(8).getCell(9).setCellValue(excel_map.get("btc_oclh_4_date"));
	        form_sheet.getRow(8).getCell(10).setCellValue(excel_map.get("btc_oclh_4_o_price"));
	        form_sheet.getRow(8).getCell(11).setCellValue(excel_map.get("btc_oclh_4_l_price"));
	        form_sheet.getRow(8).getCell(12).setCellValue(excel_map.get("btc_oclh_4_h_price"));
	        form_sheet.getRow(8).getCell(13).setCellValue(excel_map.get("btc_oclh_4_c_price"));
	        form_sheet.getRow(8).getCell(14).setCellValue(excel_map.get("btc_oclh_4_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_4_l_price"))) { form_sheet.getRow(8).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_4_h_price"))) { form_sheet.getRow(8).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(9).getCell(9).setCellValue(excel_map.get("btc_oclh_5_date"));
	        form_sheet.getRow(9).getCell(10).setCellValue(excel_map.get("btc_oclh_5_o_price"));
	        form_sheet.getRow(9).getCell(11).setCellValue(excel_map.get("btc_oclh_5_l_price"));
	        form_sheet.getRow(9).getCell(12).setCellValue(excel_map.get("btc_oclh_5_h_price"));
	        form_sheet.getRow(9).getCell(13).setCellValue(excel_map.get("btc_oclh_5_c_price"));
	        form_sheet.getRow(9).getCell(14).setCellValue(excel_map.get("btc_oclh_5_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_5_l_price"))) { form_sheet.getRow(9).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_5_h_price"))) { form_sheet.getRow(9).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(10).getCell(9).setCellValue(excel_map.get("btc_oclh_6_date"));
	        form_sheet.getRow(10).getCell(10).setCellValue(excel_map.get("btc_oclh_6_o_price"));
	        form_sheet.getRow(10).getCell(11).setCellValue(excel_map.get("btc_oclh_6_l_price"));
	        form_sheet.getRow(10).getCell(12).setCellValue(excel_map.get("btc_oclh_6_h_price"));
	        form_sheet.getRow(10).getCell(13).setCellValue(excel_map.get("btc_oclh_6_c_price"));
	        form_sheet.getRow(10).getCell(14).setCellValue(excel_map.get("btc_oclh_6_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_6_l_price"))) { form_sheet.getRow(10).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_6_h_price"))) { form_sheet.getRow(10).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(11).getCell(9).setCellValue(excel_map.get("btc_oclh_7_date"));
	        form_sheet.getRow(11).getCell(10).setCellValue(excel_map.get("btc_oclh_7_o_price"));
	        form_sheet.getRow(11).getCell(11).setCellValue(excel_map.get("btc_oclh_7_l_price"));
	        form_sheet.getRow(11).getCell(12).setCellValue(excel_map.get("btc_oclh_7_h_price"));
	        form_sheet.getRow(11).getCell(13).setCellValue(excel_map.get("btc_oclh_7_c_price"));
	        form_sheet.getRow(11).getCell(14).setCellValue(excel_map.get("btc_oclh_7_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_7_l_price"))) { form_sheet.getRow(11).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_7_h_price"))) { form_sheet.getRow(11).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(13).getCell(2).setCellValue(excel_map.get("coin1_ticker"));
	        form_sheet.getRow(15).getCell(3).setCellValue(excel_map.get("coin1_o_price"));
	        form_sheet.getRow(15).getCell(7).setCellValue(excel_map.get("coin1_c_price"));
	        form_sheet.getRow(16).getCell(3).setCellValue(excel_map.get("coin1_l_price"));
	        form_sheet.getRow(16).getCell(7).setCellValue(excel_map.get("coin1_h_price"));
	        form_sheet.getRow(17).getCell(3).setCellValue(excel_map.get("coin1_open_close"));
	        form_sheet.getRow(17).getCell(7).setCellValue(excel_map.get("coin1_lowest_highest"));
	        form_sheet.getRow(18).getCell(3).setCellValue(excel_map.get("coin1_5_rate_count"));
	        form_sheet.getRow(18).getCell(7).setCellValue(excel_map.get("coin1_10_rate_count"));
	        form_sheet.getRow(19).getCell(3).setCellValue(excel_map.get("coin1_15_rate_count"));
	        form_sheet.getRow(19).getCell(7).setCellValue(excel_map.get("coin1_20_rate_count"));
	        form_sheet.getRow(20).getCell(3).setCellValue(excel_map.get("coin1_25_rate_count"));
	        form_sheet.getRow(20).getCell(7).setCellValue(excel_map.get("coin1_30_rate_count"));
	        
	        form_sheet.getRow(14).getCell(9).setCellValue(excel_map.get("coin1_oclh_1_date"));
	        form_sheet.getRow(14).getCell(10).setCellValue(excel_map.get("coin1_oclh_1_o_price"));
	        form_sheet.getRow(14).getCell(11).setCellValue(excel_map.get("coin1_oclh_1_l_price"));
	        form_sheet.getRow(14).getCell(12).setCellValue(excel_map.get("coin1_oclh_1_h_price"));
	        form_sheet.getRow(14).getCell(13).setCellValue(excel_map.get("coin1_oclh_1_c_price"));
	        form_sheet.getRow(14).getCell(14).setCellValue(excel_map.get("coin1_oclh_1_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_1_l_price"))) { form_sheet.getRow(14).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_1_h_price"))) { form_sheet.getRow(14).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(15).getCell(9).setCellValue(excel_map.get("coin1_oclh_2_date"));
	        form_sheet.getRow(15).getCell(10).setCellValue(excel_map.get("coin1_oclh_2_o_price"));
	        form_sheet.getRow(15).getCell(11).setCellValue(excel_map.get("coin1_oclh_2_l_price"));
	        form_sheet.getRow(15).getCell(12).setCellValue(excel_map.get("coin1_oclh_2_h_price"));
	        form_sheet.getRow(15).getCell(13).setCellValue(excel_map.get("coin1_oclh_2_c_price"));
	        form_sheet.getRow(15).getCell(14).setCellValue(excel_map.get("coin1_oclh_2_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_2_l_price"))) { form_sheet.getRow(15).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_2_h_price"))) { form_sheet.getRow(15).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(16).getCell(9).setCellValue(excel_map.get("coin1_oclh_3_date"));
	        form_sheet.getRow(16).getCell(10).setCellValue(excel_map.get("coin1_oclh_3_o_price"));
	        form_sheet.getRow(16).getCell(11).setCellValue(excel_map.get("coin1_oclh_3_l_price"));
	        form_sheet.getRow(16).getCell(12).setCellValue(excel_map.get("coin1_oclh_3_h_price"));
	        form_sheet.getRow(16).getCell(13).setCellValue(excel_map.get("coin1_oclh_3_c_price"));
	        form_sheet.getRow(16).getCell(14).setCellValue(excel_map.get("coin1_oclh_3_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_3_l_price"))) { form_sheet.getRow(16).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_3_h_price"))) { form_sheet.getRow(16).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(17).getCell(9).setCellValue(excel_map.get("coin1_oclh_4_date"));
	        form_sheet.getRow(17).getCell(10).setCellValue(excel_map.get("coin1_oclh_4_o_price"));
	        form_sheet.getRow(17).getCell(11).setCellValue(excel_map.get("coin1_oclh_4_l_price"));
	        form_sheet.getRow(17).getCell(12).setCellValue(excel_map.get("coin1_oclh_4_h_price"));
	        form_sheet.getRow(17).getCell(13).setCellValue(excel_map.get("coin1_oclh_4_c_price"));
	        form_sheet.getRow(17).getCell(14).setCellValue(excel_map.get("coin1_oclh_4_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_4_l_price"))) { form_sheet.getRow(17).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_4_h_price"))) { form_sheet.getRow(17).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(18).getCell(9).setCellValue(excel_map.get("coin1_oclh_5_date"));
	        form_sheet.getRow(18).getCell(10).setCellValue(excel_map.get("coin1_oclh_5_o_price"));
	        form_sheet.getRow(18).getCell(11).setCellValue(excel_map.get("coin1_oclh_5_l_price"));
	        form_sheet.getRow(18).getCell(12).setCellValue(excel_map.get("coin1_oclh_5_h_price"));
	        form_sheet.getRow(18).getCell(13).setCellValue(excel_map.get("coin1_oclh_5_c_price"));
	        form_sheet.getRow(18).getCell(14).setCellValue(excel_map.get("coin1_oclh_5_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_5_l_price"))) { form_sheet.getRow(18).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_5_h_price"))) { form_sheet.getRow(18).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(19).getCell(9).setCellValue(excel_map.get("coin1_oclh_6_date"));
	        form_sheet.getRow(19).getCell(10).setCellValue(excel_map.get("coin1_oclh_6_o_price"));
	        form_sheet.getRow(19).getCell(11).setCellValue(excel_map.get("coin1_oclh_6_l_price"));
	        form_sheet.getRow(19).getCell(12).setCellValue(excel_map.get("coin1_oclh_6_h_price"));
	        form_sheet.getRow(19).getCell(13).setCellValue(excel_map.get("coin1_oclh_6_c_price"));
	        form_sheet.getRow(19).getCell(14).setCellValue(excel_map.get("coin1_oclh_6_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_6_l_price"))) { form_sheet.getRow(19).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_6_h_price"))) { form_sheet.getRow(19).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(20).getCell(9).setCellValue(excel_map.get("coin1_oclh_7_date"));
	        form_sheet.getRow(20).getCell(10).setCellValue(excel_map.get("coin1_oclh_7_o_price"));
	        form_sheet.getRow(20).getCell(11).setCellValue(excel_map.get("coin1_oclh_7_l_price"));
	        form_sheet.getRow(20).getCell(12).setCellValue(excel_map.get("coin1_oclh_7_h_price"));
	        form_sheet.getRow(20).getCell(13).setCellValue(excel_map.get("coin1_oclh_7_c_price"));
	        form_sheet.getRow(20).getCell(14).setCellValue(excel_map.get("coin1_oclh_7_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_7_l_price"))) { form_sheet.getRow(20).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_7_h_price"))) { form_sheet.getRow(20).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(22).getCell(2).setCellValue(excel_map.get("coin2_ticker"));
	        form_sheet.getRow(24).getCell(3).setCellValue(excel_map.get("coin2_o_price"));
	        form_sheet.getRow(24).getCell(7).setCellValue(excel_map.get("coin2_c_price"));
	        form_sheet.getRow(25).getCell(3).setCellValue(excel_map.get("coin2_l_price"));
	        form_sheet.getRow(25).getCell(7).setCellValue(excel_map.get("coin2_h_price"));
	        form_sheet.getRow(26).getCell(3).setCellValue(excel_map.get("coin2_open_close"));
	        form_sheet.getRow(26).getCell(7).setCellValue(excel_map.get("coin2_lowest_highest"));
	        form_sheet.getRow(27).getCell(3).setCellValue(excel_map.get("coin2_5_rate_count"));
	        form_sheet.getRow(27).getCell(7).setCellValue(excel_map.get("coin2_10_rate_count"));
	        form_sheet.getRow(28).getCell(3).setCellValue(excel_map.get("coin2_15_rate_count"));
	        form_sheet.getRow(28).getCell(7).setCellValue(excel_map.get("coin2_20_rate_count"));
	        form_sheet.getRow(29).getCell(3).setCellValue(excel_map.get("coin2_25_rate_count"));
	        form_sheet.getRow(29).getCell(7).setCellValue(excel_map.get("coin2_30_rate_count"));
	        
	        form_sheet.getRow(23).getCell(9).setCellValue(excel_map.get("coin2_oclh_1_date"));
	        form_sheet.getRow(23).getCell(10).setCellValue(excel_map.get("coin2_oclh_1_o_price"));
	        form_sheet.getRow(23).getCell(11).setCellValue(excel_map.get("coin2_oclh_1_l_price"));
	        form_sheet.getRow(23).getCell(12).setCellValue(excel_map.get("coin2_oclh_1_h_price"));
	        form_sheet.getRow(23).getCell(13).setCellValue(excel_map.get("coin2_oclh_1_c_price"));
	        form_sheet.getRow(23).getCell(14).setCellValue(excel_map.get("coin2_oclh_1_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_1_l_price"))) { form_sheet.getRow(23).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_1_h_price"))) { form_sheet.getRow(23).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(24).getCell(9).setCellValue(excel_map.get("coin2_oclh_2_date"));
	        form_sheet.getRow(24).getCell(10).setCellValue(excel_map.get("coin2_oclh_2_o_price"));
	        form_sheet.getRow(24).getCell(11).setCellValue(excel_map.get("coin2_oclh_2_l_price"));
	        form_sheet.getRow(24).getCell(12).setCellValue(excel_map.get("coin2_oclh_2_h_price"));
	        form_sheet.getRow(24).getCell(13).setCellValue(excel_map.get("coin2_oclh_2_c_price"));
	        form_sheet.getRow(24).getCell(14).setCellValue(excel_map.get("coin2_oclh_2_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_2_l_price"))) { form_sheet.getRow(24).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_2_h_price"))) { form_sheet.getRow(24).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(25).getCell(9).setCellValue(excel_map.get("coin2_oclh_3_date"));
	        form_sheet.getRow(25).getCell(10).setCellValue(excel_map.get("coin2_oclh_3_o_price"));
	        form_sheet.getRow(25).getCell(11).setCellValue(excel_map.get("coin2_oclh_3_l_price"));
	        form_sheet.getRow(25).getCell(12).setCellValue(excel_map.get("coin2_oclh_3_h_price"));
	        form_sheet.getRow(25).getCell(13).setCellValue(excel_map.get("coin2_oclh_3_c_price"));
	        form_sheet.getRow(25).getCell(14).setCellValue(excel_map.get("coin2_oclh_3_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_3_l_price"))) { form_sheet.getRow(25).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_3_h_price"))) { form_sheet.getRow(25).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(26).getCell(9).setCellValue(excel_map.get("coin2_oclh_4_date"));
	        form_sheet.getRow(26).getCell(10).setCellValue(excel_map.get("coin2_oclh_4_o_price"));
	        form_sheet.getRow(26).getCell(11).setCellValue(excel_map.get("coin2_oclh_4_l_price"));
	        form_sheet.getRow(26).getCell(12).setCellValue(excel_map.get("coin2_oclh_4_h_price"));
	        form_sheet.getRow(26).getCell(13).setCellValue(excel_map.get("coin2_oclh_4_c_price"));
	        form_sheet.getRow(26).getCell(14).setCellValue(excel_map.get("coin2_oclh_4_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_4_l_price"))) { form_sheet.getRow(26).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_4_h_price"))) { form_sheet.getRow(26).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(27).getCell(9).setCellValue(excel_map.get("coin2_oclh_5_date"));
	        form_sheet.getRow(27).getCell(10).setCellValue(excel_map.get("coin2_oclh_5_o_price"));
	        form_sheet.getRow(27).getCell(11).setCellValue(excel_map.get("coin2_oclh_5_l_price"));
	        form_sheet.getRow(27).getCell(12).setCellValue(excel_map.get("coin2_oclh_5_h_price"));
	        form_sheet.getRow(27).getCell(13).setCellValue(excel_map.get("coin2_oclh_5_c_price"));
	        form_sheet.getRow(27).getCell(14).setCellValue(excel_map.get("coin2_oclh_5_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_5_l_price"))) { form_sheet.getRow(27).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_5_h_price"))) { form_sheet.getRow(27).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(28).getCell(9).setCellValue(excel_map.get("coin2_oclh_6_date"));
	        form_sheet.getRow(28).getCell(10).setCellValue(excel_map.get("coin2_oclh_6_o_price"));
	        form_sheet.getRow(28).getCell(11).setCellValue(excel_map.get("coin2_oclh_6_l_price"));
	        form_sheet.getRow(28).getCell(12).setCellValue(excel_map.get("coin2_oclh_6_h_price"));
	        form_sheet.getRow(28).getCell(13).setCellValue(excel_map.get("coin2_oclh_6_c_price"));
	        form_sheet.getRow(28).getCell(14).setCellValue(excel_map.get("coin2_oclh_6_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_6_l_price"))) { form_sheet.getRow(28).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_6_h_price"))) { form_sheet.getRow(28).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(29).getCell(9).setCellValue(excel_map.get("coin2_oclh_7_date"));
	        form_sheet.getRow(29).getCell(10).setCellValue(excel_map.get("coin2_oclh_7_o_price"));
	        form_sheet.getRow(29).getCell(11).setCellValue(excel_map.get("coin2_oclh_7_l_price"));
	        form_sheet.getRow(29).getCell(12).setCellValue(excel_map.get("coin2_oclh_7_h_price"));
	        form_sheet.getRow(29).getCell(13).setCellValue(excel_map.get("coin2_oclh_7_c_price"));
	        form_sheet.getRow(29).getCell(14).setCellValue(excel_map.get("coin2_oclh_7_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_7_l_price"))) { form_sheet.getRow(29).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_7_h_price"))) { form_sheet.getRow(29).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(31).getCell(2).setCellValue(excel_map.get("coin3_ticker"));
	        form_sheet.getRow(33).getCell(3).setCellValue(excel_map.get("coin3_o_price"));
	        form_sheet.getRow(33).getCell(7).setCellValue(excel_map.get("coin3_c_price"));
	        form_sheet.getRow(34).getCell(3).setCellValue(excel_map.get("coin3_l_price"));
	        form_sheet.getRow(34).getCell(7).setCellValue(excel_map.get("coin3_h_price"));
	        form_sheet.getRow(35).getCell(3).setCellValue(excel_map.get("coin3_open_close"));
	        form_sheet.getRow(35).getCell(7).setCellValue(excel_map.get("coin3_lowest_highest"));
	        form_sheet.getRow(36).getCell(3).setCellValue(excel_map.get("coin3_5_rate_count"));
	        form_sheet.getRow(36).getCell(7).setCellValue(excel_map.get("coin3_10_rate_count"));
	        form_sheet.getRow(37).getCell(3).setCellValue(excel_map.get("coin3_15_rate_count"));
	        form_sheet.getRow(37).getCell(7).setCellValue(excel_map.get("coin3_20_rate_count"));
	        form_sheet.getRow(38).getCell(3).setCellValue(excel_map.get("coin3_25_rate_count"));
	        form_sheet.getRow(38).getCell(7).setCellValue(excel_map.get("coin3_30_rate_count"));
	        
	        form_sheet.getRow(32).getCell(9).setCellValue(excel_map.get("coin3_oclh_1_date"));
	        form_sheet.getRow(32).getCell(10).setCellValue(excel_map.get("coin3_oclh_1_o_price"));
	        form_sheet.getRow(32).getCell(11).setCellValue(excel_map.get("coin3_oclh_1_l_price"));
	        form_sheet.getRow(32).getCell(12).setCellValue(excel_map.get("coin3_oclh_1_h_price"));
	        form_sheet.getRow(32).getCell(13).setCellValue(excel_map.get("coin3_oclh_1_c_price"));
	        form_sheet.getRow(32).getCell(14).setCellValue(excel_map.get("coin3_oclh_1_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_1_l_price"))) { form_sheet.getRow(32).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_1_h_price"))) { form_sheet.getRow(32).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(33).getCell(9).setCellValue(excel_map.get("coin3_oclh_2_date"));
	        form_sheet.getRow(33).getCell(10).setCellValue(excel_map.get("coin3_oclh_2_o_price"));
	        form_sheet.getRow(33).getCell(11).setCellValue(excel_map.get("coin3_oclh_2_l_price"));
	        form_sheet.getRow(33).getCell(12).setCellValue(excel_map.get("coin3_oclh_2_h_price"));
	        form_sheet.getRow(33).getCell(13).setCellValue(excel_map.get("coin3_oclh_2_c_price"));
	        form_sheet.getRow(33).getCell(14).setCellValue(excel_map.get("coin3_oclh_2_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_2_l_price"))) { form_sheet.getRow(33).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_2_h_price"))) { form_sheet.getRow(33).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(34).getCell(9).setCellValue(excel_map.get("coin3_oclh_3_date"));
	        form_sheet.getRow(34).getCell(10).setCellValue(excel_map.get("coin3_oclh_3_o_price"));
	        form_sheet.getRow(34).getCell(11).setCellValue(excel_map.get("coin3_oclh_3_l_price"));
	        form_sheet.getRow(34).getCell(12).setCellValue(excel_map.get("coin3_oclh_3_h_price"));
	        form_sheet.getRow(34).getCell(13).setCellValue(excel_map.get("coin3_oclh_3_c_price"));
	        form_sheet.getRow(34).getCell(14).setCellValue(excel_map.get("coin3_oclh_3_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_3_l_price"))) { form_sheet.getRow(34).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_3_h_price"))) { form_sheet.getRow(34).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(35).getCell(9).setCellValue(excel_map.get("coin3_oclh_4_date"));
	        form_sheet.getRow(35).getCell(10).setCellValue(excel_map.get("coin3_oclh_4_o_price"));
	        form_sheet.getRow(35).getCell(11).setCellValue(excel_map.get("coin3_oclh_4_l_price"));
	        form_sheet.getRow(35).getCell(12).setCellValue(excel_map.get("coin3_oclh_4_h_price"));
	        form_sheet.getRow(35).getCell(13).setCellValue(excel_map.get("coin3_oclh_4_c_price"));
	        form_sheet.getRow(35).getCell(14).setCellValue(excel_map.get("coin3_oclh_4_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_4_l_price"))) { form_sheet.getRow(35).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_4_h_price"))) { form_sheet.getRow(35).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(36).getCell(9).setCellValue(excel_map.get("coin3_oclh_5_date"));
	        form_sheet.getRow(36).getCell(10).setCellValue(excel_map.get("coin3_oclh_5_o_price"));
	        form_sheet.getRow(36).getCell(11).setCellValue(excel_map.get("coin3_oclh_5_l_price"));
	        form_sheet.getRow(36).getCell(12).setCellValue(excel_map.get("coin3_oclh_5_h_price"));
	        form_sheet.getRow(36).getCell(13).setCellValue(excel_map.get("coin3_oclh_5_c_price"));
	        form_sheet.getRow(36).getCell(14).setCellValue(excel_map.get("coin3_oclh_5_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_5_l_price"))) { form_sheet.getRow(36).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_5_h_price"))) { form_sheet.getRow(36).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(37).getCell(9).setCellValue(excel_map.get("coin3_oclh_6_date"));
	        form_sheet.getRow(37).getCell(10).setCellValue(excel_map.get("coin3_oclh_6_o_price"));
	        form_sheet.getRow(37).getCell(11).setCellValue(excel_map.get("coin3_oclh_6_l_price"));
	        form_sheet.getRow(37).getCell(12).setCellValue(excel_map.get("coin3_oclh_6_h_price"));
	        form_sheet.getRow(37).getCell(13).setCellValue(excel_map.get("coin3_oclh_6_c_price"));
	        form_sheet.getRow(37).getCell(14).setCellValue(excel_map.get("coin3_oclh_6_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_6_l_price"))) { form_sheet.getRow(37).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_6_h_price"))) { form_sheet.getRow(37).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(38).getCell(9).setCellValue(excel_map.get("coin3_oclh_7_date"));
	        form_sheet.getRow(38).getCell(10).setCellValue(excel_map.get("coin3_oclh_7_o_price"));
	        form_sheet.getRow(38).getCell(11).setCellValue(excel_map.get("coin3_oclh_7_l_price"));
	        form_sheet.getRow(38).getCell(12).setCellValue(excel_map.get("coin3_oclh_7_h_price"));
	        form_sheet.getRow(38).getCell(13).setCellValue(excel_map.get("coin3_oclh_7_c_price"));
	        form_sheet.getRow(38).getCell(14).setCellValue(excel_map.get("coin3_oclh_7_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_7_l_price"))) { form_sheet.getRow(38).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_7_h_price"))) { form_sheet.getRow(38).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        String kor_jucha_date = std_date.substring(0, 4) + "년 " + std_date.substring(5, 7)+"월 " + Integer.toString((int)(Integer.parseInt((std_date.substring(8, 9)).replace("0", "") + (std_date.substring(9, 10)))/31)+1	) + "주차";
	        String kor_date = "["+std_date.substring(5, 7)+"월 "+(std_date.substring(8, 9)).replace("0", "") + (std_date.substring(9, 10)) +"일 ~ "+ end_date.substring(5, 7)+"월 "+(end_date.substring(8, 9)).replace("0", "") + (end_date.substring(9, 10))+"일]";
	        
	        Row row = summary_sheet.createRow(1);
	        Cell cell = row.createCell(1);
	        summary_sheet.getRow(1).getCell(1).setCellValue("업비트 " + kor_jucha_date + " 코인 분석");
	        
	        row = summary_sheet.createRow(2);
	        cell = row.createCell(1);
	        summary_sheet.getRow(2).getCell(1).setCellValue("업비트 " + kor_jucha_date + kor_date + " 분석을 시작하겠습니다.");
	        
	        row = summary_sheet.createRow(3);
	        cell = row.createCell(1);
	        summary_sheet.getRow(3).getCell(1).setCellValue("모든 코인의 기준이 되는 비트코인[BTC]부터 확인하도록 하겠습니다.");
	        
	        row = summary_sheet.createRow(4);
	        cell = row.createCell(1);
	        summary_sheet.getRow(4).getCell(1).setCellValue("BTC 기준 시가 대비 종가 " + excel_map.get("summary_1_1") + kor_jucha_date + kor_date + " 업비트 원화 코인 중 116종 중 시가 대비 종가가 " + result_data.replace("상승 = ", "상승인 코인이 ").replace(" | 하락 = ", "종, 하락인 코인이 ").replace(" | 보합 = ", "종, 보합인 코인이 ") + "종입니다.");
	        
	        row = summary_sheet.createRow(5);
	        cell = row.createCell(1);
	        summary_sheet.getRow(5).getCell(1).setCellValue(kor_jucha_date + kor_date + "에 투자했으면 가장 안정적으로 많이 벌 수 있었던 코인 3~1순위를 알려드리겠습니다.");
	        
	        row = summary_sheet.createRow(7);
	        cell = row.createCell(1);
	        summary_sheet.getRow(7).getCell(1).setCellValue("이번주 코인 투자 시 한 번 고려 해보시기 바랍니다.");
	        
	        row = summary_sheet.createRow(9);
	        cell = row.createCell(1);
	        summary_sheet.getRow(9).getCell(1).setCellValue(kor_jucha_date + " 3순위 " + excel_map.get("coin3_ticker"));
	        
	        row = summary_sheet.createRow(11);
	        cell = row.createCell(1);
	        summary_sheet.getRow(11).getCell(1).setCellValue(kor_jucha_date + kor_date + " " + excel_map.get("coin3_summary"));
	        
	        row = summary_sheet.createRow(13);
	        cell = row.createCell(1);
	        summary_sheet.getRow(13).getCell(1).setCellValue(kor_jucha_date + " 2순위 " + excel_map.get("coin2_ticker"));
	        
	        row = summary_sheet.createRow(15);
	        cell = row.createCell(1);
	        summary_sheet.getRow(15).getCell(1).setCellValue(kor_jucha_date + kor_date + " " + excel_map.get("coin2_summary"));
	        
	        row = summary_sheet.createRow(17);
	        cell = row.createCell(1);
	        summary_sheet.getRow(17).getCell(1).setCellValue(kor_jucha_date + " 1순위 " + excel_map.get("coin1_ticker"));
	        
	        row = summary_sheet.createRow(19);
	        cell = row.createCell(1);
	        summary_sheet.getRow(19).getCell(1).setCellValue(kor_jucha_date + kor_date + " " + excel_map.get("coin1_summary"));
	        
	        row = summary_sheet.createRow(21);
	        cell = row.createCell(1);
	        summary_sheet.getRow(21).getCell(1).setCellValue(kor_jucha_date + kor_date + " 요약");
	        
	        row = summary_sheet.createRow(22);
	        cell = row.createCell(1);
	        summary_sheet.getRow(22).getCell(1).setCellValue(kor_jucha_date + kor_date + " 업비트 코인 중 BTC가 " + excel_map.get("summary_1_1") + " 업비트 원화 코인 116종 중 " + result_data.replace("상승 = ", "상승인 코인이 ").replace(" | 하락 = ", "종, 하락인 코인이 ").replace(" | 보합 = ", "종, 보합인 코인이 ") + "종입니다.");
	        
	        fileName = "Weekly_"+std_date+"_"+end_date+".xlsx";
	        
	        fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        response.setContentType("application/vsd.ms-excel");
	        response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
	        
	        form_wb.write(response.getOutputStream());
	        form_wb.close();
		} catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}