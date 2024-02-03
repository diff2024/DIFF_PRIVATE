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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diff._private.Service.MainService;
import com.diff._private.Service.BybitCoinService;
import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@RestController
@RequestMapping("/Bybit")
public class BybitCoinController {

	@Autowired
	MainService MainService;
	
	@Autowired
	BybitCoinService BybitCoinService;
	
	@GetMapping(path = "/CoinInfo")
	public List<HashMap<String, String>> CoinInfo(HttpServletRequest req) throws Exception {
		return BybitCoinService.CoinInfo();
	}
	
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
		return BybitCoinService.MainGridList(map);
	}
	
	@Async
	@PostMapping(path = "/CoinAnalysisCreate")
	public void CoinAnalysisCreate(HttpServletRequest req) throws Exception {
		String date = (req.getParameter("date")==null)?"":req.getParameter("date");
		String yesterday = (req.getParameter("yesterday")==null)?"":req.getParameter("yesterday");
		 
		if(date.equals("")) {
			Calendar day = Calendar.getInstance();
		    date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		    day.add(Calendar.DATE , -1);
		    yesterday = new java.text.SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
		} else {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date SetDate = transFormat.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(SetDate);
			cal.add(Calendar.DATE , -1);
			yesterday = new java.text.SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}
		
	    HashMap<String, String> SettingMap = MainService.CoinAnalysisSetting();
	    String MainRankingCount = SettingMap.get("bybit_report_main_ranking");
	    String SubRankingCount = SettingMap.get("bybit_report_sub_ranking");
	    String BithumbReportAD1 = SettingMap.get("bithumb_report_ad1");
	    String BithumbReportAD2 = SettingMap.get("bithumb_report_ad2");
	    String BithumbReportAD3 = SettingMap.get("bithumb_report_ad3");
	    String BithumbReportAD4 = SettingMap.get("bithumb_report_ad4");
	    String BithumbReportAD5 = SettingMap.get("bithumb_report_ad5");
	    String UpbitReportAD1 = SettingMap.get("upbit_report_ad1");
	    String UpbitReportAD2 = SettingMap.get("upbit_report_ad2");
	    String UpbitReportAD3 = SettingMap.get("upbit_report_ad3");
	    String UpbitReportAD4 = SettingMap.get("upbit_report_ad4");
	    String UpbitReportAD5 = SettingMap.get("upbit_report_ad5");
	    String BinanceReportAD1 = SettingMap.get("binance_report_ad1");
	    String BinanceReportAD2 = SettingMap.get("binance_report_ad2");
	    String BinanceReportAD3 = SettingMap.get("binance_report_ad3");
	    String BinanceReportAD4 = SettingMap.get("binance_report_ad4");
	    String BinanceReportAD5 = SettingMap.get("binance_report_ad5");
	    String BybitReportAD1 = SettingMap.get("bybit_report_ad1");
	    String BybitReportAD2 = SettingMap.get("bybit_report_ad2");
	    String BybitReportAD3 = SettingMap.get("bybit_report_ad3");
	    String BybitReportAD4 = SettingMap.get("bybit_report_ad4");
	    String BybitReportAD5 = SettingMap.get("Bybit_report_ad5");
	    
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("date", date);
	    map.put("yyyymmdd", date);
	    map.put("yesterday", yesterday);
	    map.put("MainRankingCount", MainRankingCount);
	    map.put("SubRankingCount", SubRankingCount);
	    map.put("BithumbReportAD1", BithumbReportAD1);
	    map.put("BithumbReportAD2", BithumbReportAD2);
	    map.put("BithumbReportAD3", BithumbReportAD3);
	    map.put("BithumbReportAD4", BithumbReportAD4);
	    map.put("BithumbReportAD5", BithumbReportAD5);
	    map.put("UpbitReportAD1", UpbitReportAD1);
	    map.put("UpbitReportAD2", UpbitReportAD2);
	    map.put("UpbitReportAD3", UpbitReportAD3);
	    map.put("UpbitReportAD4", UpbitReportAD4);
	    map.put("UpbitReportAD5", UpbitReportAD5);
	    map.put("BinanceReportAD1", BinanceReportAD1);
	    map.put("BinanceReportAD2", BinanceReportAD2);
	    map.put("BinanceReportAD3", BinanceReportAD3);
	    map.put("BinanceReportAD4", BinanceReportAD4);
	    map.put("BinanceReportAD5", BinanceReportAD5);
	    map.put("BybitReportAD1", BybitReportAD1);
	    map.put("BybitReportAD2", BybitReportAD2);
	    map.put("BybitReportAD3", BybitReportAD3);
	    map.put("BybitReportAD4", BybitReportAD4);
	    map.put("BybitReportAD5", BybitReportAD5);
	    
	    BybitCoinService.CoinAnalysisDelete(map);
	    Thread.sleep(1500);
	    BybitCoinService.CoinAnalysisCreate(map);
	    BybitCoinService.CoinAnalysisHourGraphCreate(map);
	    BybitCoinService.CoinAnalysis4HourGraphCreate(map);
	    map.put("blog_id", "1");
	    BybitCoinService.WordPressReportHTMLCreate(map);
	    map.put("blog_id", "6");
	    BybitCoinService.TiStoryReportHTMLCreate(map);
	}
	
	@GetMapping(path = "/CoinDailyReportList")
	public List<HashMap<String, String>> CoinDailyReportList(HttpServletRequest req) throws Exception {
		String yyyymmdd = (req.getParameter("yyyymmdd")==null)?"":req.getParameter("yyyymmdd");
		String std_date = (req.getParameter("std_date")==null)?"":req.getParameter("std_date");
		String end_date = (req.getParameter("end_date")==null)?"":req.getParameter("end_date");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("yyyymmdd", yyyymmdd);
		map.put("std_date", std_date);
		map.put("end_date", end_date);
		return BybitCoinService.CoinDailyReportList(map);
	}
	
	@GetMapping(path = "/MainLiveRankList")
	public List<HashMap<String, String>> MainLiveRankList(HttpServletRequest req) throws Exception {
		String search_date = (req.getParameter("search_date")==null)?"":req.getParameter("search_date");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("search_date", search_date);
		return BybitCoinService.MainLiveRankList(map);
	}
	
	@GetMapping(path = "/ExcelMake_Daily")
	public void ExcelMake_Daily(HttpServletRequest req, HttpServletResponse response) throws Exception {
		String date = (req.getParameter("std_date")==null)?"":req.getParameter("std_date");
		String coin_ticker = (req.getParameter("coin_ticker")==null)?"":req.getParameter("coin_ticker");
		coin_ticker = coin_ticker.replaceAll(",", "','");
		
		DecimalFormat formatter = new DecimalFormat("###,###.########");
		SimpleDateFormat SDFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date yesterdayData = SDFormat.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(yesterdayData);
		calendar.add(Calendar.DATE, -1);
		String yesterday = SDFormat.format(calendar.getTime());
		
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("date", date);
        map.put("yesterday", yesterday);
        map.put("coin_ticker", coin_ticker);
        
        String fileName = "";
		try {
			req.setCharacterEncoding("UTF-8");
			
			InputStream fis = new FileInputStream(new File("C:/Coin/Excel/B_Daily.xlsx"));
	        XSSFWorkbook form_wb = new XSSFWorkbook(fis);
	        XSSFSheet form_sheet = form_wb.getSheetAt(0);
	        XSSFSheet ranking_sheet = form_wb.getSheetAt(1);
	        XSSFSheet summary_sheet = form_wb.createSheet("스크립트");
	        
	        List<HashMap<String, String>> result_map = BybitCoinService.ExcelMake_Daily_Result(map);
	        String coin_total_count = result_map.get(0).get("total_count");
	        String coin_increase_count = result_map.get(0).get("increase_count");
	        String coin_degradation_count = result_map.get(0).get("degradation_count");
	        String coin_flat_count = result_map.get(0).get("flat_count");
	        String coin_increase_ticker = result_map.get(0).get("increase_ticker");
	        String coin_degradation_ticker = result_map.get(0).get("degradation_ticker");
	        String coin_flat_ticker = result_map.get(0).get("flat_ticker");
	        
	        List<HashMap<String, String>> ranking_list = BybitCoinService.ExcelMake_Daily_Ranking(map);
	        
	        String result_data = " "+coin_total_count+" | " + coin_increase_count +" | " + coin_degradation_count +" | " + coin_flat_count;
	        form_wb.setSheetName(0, result_data);
	        
	        HashMap<String, String> excel_map = BybitCoinService.ExcelMake_Daily(map);
	        
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
	        
	        form_sheet.getRow(Sheet1_Row).getCell(1).setCellValue("바이비트 일일 분석 " + date);
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
	        
	        String kor_date = date.substring(2, 4) + "년 "+date.substring(5, 7)+"월 "+(date.substring(8, 9)).replace("0", "") + (date.substring(9, 10)) +"일";
	        String short_kor_date = date.substring(5, 7)+"월 "+(date.substring(8, 9)).replace("0", "") + (date.substring(9, 10)) +"일";
	        
	        String yesterday_kor_date = yesterday.substring(2, 4) + "년 "+yesterday.substring(5, 7)+"월 "+(yesterday.substring(8, 9)).replace("0", "") + (yesterday.substring(9, 10)) +"일";
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
	        summary_sheet.getRow(Sheet2_Row).getCell(1).setCellValue("바이비트 " + kor_date + " 코인 분석");
	        
	        row = summary_sheet.createRow(Sheet2_Row+1);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+1).getCell(1).setCellValue("바이비트 " + kor_date + " 코인 분석을 시작하겠습니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+3);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+3).getCell(1).setCellValue("BTC 기준 시가 대비 종가 " + excel_map.get("summary_1_1") + kor_date+ " 바이비트 원화 코인 "+coin_total_count+"종 중 상승인 코인이 "+coin_increase_count+"종, 하락인 코인이 "+coin_degradation_count+"종, 보합인 코인이 "+coin_flat_count+"종입니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+4);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+4).getCell(1).setCellValue(kor_date + "에 투자했으면 가장 안정적으로 많이 벌 수 있었던 코인 3~1순위를 알려드리겠습니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+5);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+5).getCell(1).setCellValue("오늘 코인 매매 시 한 번 고려 해보시기 바랍니다.");
	        
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
	        summary_sheet.getRow(Sheet2_Row+20).getCell(1).setCellValue(short_kor_date+" 바이비트 코인 요약");
	        
	        row = summary_sheet.createRow(Sheet2_Row+21);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+21).getCell(1).setCellValue(kor_date + " 바이비트 코인 중 BTC가 " + excel_map.get("summary_1_1") + " 바이비트 원화 코인 "+coin_total_count+"종 중 상승인 코인이 "+coin_increase_count+"종, 하락인 코인이 "+coin_degradation_count+"종, 보합인 코인이 "+coin_flat_count+"종입니다.");
	        
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
	        
	        fileName = "바이비트_Daily_"+date+".xlsx";
	        
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
		String coin_ticker = (req.getParameter("coin_ticker")==null)?"":req.getParameter("coin_ticker");
		coin_ticker = coin_ticker.replaceAll(",", "','");
		
		DecimalFormat formatter = new DecimalFormat("###,###.########");
		SimpleDateFormat SDFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date format1 = new SimpleDateFormat("yyyy-MM-dd").parse(std_date);
        Date format2 = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
        
        long diffSec = (format1.getTime() - format2.getTime()) / 1000;
        long diffMin = (format1.getTime() - format2.getTime()) / 60000;
        long diffHor = (format1.getTime() - format2.getTime()) / 3600000;
        long diffDays = (diffSec / (24*60*60))-1;
        
        Calendar std_cal = Calendar.getInstance();
        std_cal.setTime(format1);
        std_cal.add(Calendar.DATE, Integer.parseInt(Long.toString(diffDays)));
        
        Calendar end_cal = Calendar.getInstance();
        end_cal.setTime(format2);
        end_cal.add(Calendar.DATE, Integer.parseInt(Long.toString(diffDays)));
        
        String past_std_date = SDFormat.format(std_cal.getTime());
        String past_end_date = SDFormat.format(end_cal.getTime());
        
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("std_date", std_date);
        map.put("end_date", end_date);
        map.put("past_std_date", past_std_date);
        map.put("past_end_date", past_end_date);
        map.put("coin_ticker", coin_ticker);
        
		String fileName = "";
		try {
			req.setCharacterEncoding("UTF-8");
			
			InputStream fis = new FileInputStream(new File("C:/Coin/Excel/Weekly.xlsx"));
	        XSSFWorkbook form_wb = new XSSFWorkbook(fis);
	        XSSFSheet form_sheet = form_wb.getSheetAt(0);
	        XSSFSheet ranking_sheet = form_wb.getSheetAt(1);
	        XSSFSheet summary_sheet = form_wb.createSheet("스크립트");
	        
	        List<HashMap<String, String>> result_map = BybitCoinService.ExcelMake_Weekly_Result(map);
	        String coin_total_count = result_map.get(0).get("total_count");
	        String coin_increase_count = result_map.get(0).get("increase_count");
	        String coin_degradation_count = result_map.get(0).get("degradation_count");
	        String coin_flat_count = result_map.get(0).get("flat_count");
	        String coin_increase_ticker = result_map.get(0).get("increase_ticker");
	        String coin_degradation_ticker = result_map.get(0).get("degradation_ticker");
	        String coin_flat_ticker = result_map.get(0).get("flat_ticker");
	        
	        List<HashMap<String, String>> ranking_list = BybitCoinService.ExcelMake_Weekly_Ranking(map);
	        
	        String result_data = " "+coin_total_count+" | " + coin_increase_count +" | " + coin_degradation_count +" | " + coin_flat_count;
	        form_wb.setSheetName(0, result_data);
	        
	        HashMap<String, String> excel_map = BybitCoinService.ExcelMake_Weekly(map);
	        
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
	        
	        form_sheet.getRow(Sheet1_Row).getCell(1).setCellValue("바이비트 주간 분석 " + std_date + " ~ " + end_date);
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
	        form_sheet.getRow(Sheet1_Row+15).getCell(3).setCellValue(excel_map.get("btc_5_rate_count"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(7).setCellValue(excel_map.get("btc_10_rate_count"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(3).setCellValue(excel_map.get("btc_15_rate_count"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(7).setCellValue(excel_map.get("btc_20_rate_count"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(3).setCellValue(excel_map.get("btc_25_rate_count"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(7).setCellValue(excel_map.get("btc_30_rate_count"));
	        
	        form_sheet.getRow(Sheet1_Row+11).getCell(9).setCellValue(excel_map.get("btc_oclh_1_date"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(10).setCellValue(excel_map.get("btc_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(11).setCellValue(excel_map.get("btc_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(12).setCellValue(excel_map.get("btc_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(13).setCellValue(excel_map.get("btc_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(14).setCellValue(excel_map.get("btc_oclh_1_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+11).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+11).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+12).getCell(9).setCellValue(excel_map.get("btc_oclh_2_date"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(10).setCellValue(excel_map.get("btc_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(11).setCellValue(excel_map.get("btc_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(12).setCellValue(excel_map.get("btc_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(13).setCellValue(excel_map.get("btc_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(14).setCellValue(excel_map.get("btc_oclh_2_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+12).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+12).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+13).getCell(9).setCellValue(excel_map.get("btc_oclh_3_date"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(10).setCellValue(excel_map.get("btc_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(11).setCellValue(excel_map.get("btc_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(12).setCellValue(excel_map.get("btc_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(13).setCellValue(excel_map.get("btc_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(14).setCellValue(excel_map.get("btc_oclh_3_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+13).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+13).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+14).getCell(9).setCellValue(excel_map.get("btc_oclh_4_date"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(10).setCellValue(excel_map.get("btc_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(11).setCellValue(excel_map.get("btc_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(12).setCellValue(excel_map.get("btc_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(13).setCellValue(excel_map.get("btc_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(14).setCellValue(excel_map.get("btc_oclh_4_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+14).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+14).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+15).getCell(9).setCellValue(excel_map.get("btc_oclh_5_date"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(10).setCellValue(excel_map.get("btc_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(11).setCellValue(excel_map.get("btc_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(12).setCellValue(excel_map.get("btc_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(13).setCellValue(excel_map.get("btc_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(14).setCellValue(excel_map.get("btc_oclh_5_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+15).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+15).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+16).getCell(9).setCellValue(excel_map.get("btc_oclh_6_date"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(10).setCellValue(excel_map.get("btc_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(11).setCellValue(excel_map.get("btc_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(12).setCellValue(excel_map.get("btc_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(13).setCellValue(excel_map.get("btc_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(14).setCellValue(excel_map.get("btc_oclh_6_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+16).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+16).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+17).getCell(9).setCellValue(excel_map.get("btc_oclh_7_date"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(10).setCellValue(excel_map.get("btc_oclh_7_o_price"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(11).setCellValue(excel_map.get("btc_oclh_7_l_price"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(12).setCellValue(excel_map.get("btc_oclh_7_h_price"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(13).setCellValue(excel_map.get("btc_oclh_7_c_price"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(14).setCellValue(excel_map.get("btc_oclh_7_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_7_l_price"))) { form_sheet.getRow(Sheet1_Row+17).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_7_h_price"))) { form_sheet.getRow(Sheet1_Row+17).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(Sheet1_Row+19).getCell(2).setCellValue(excel_map.get("coin1_ticker"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(3).setCellValue(excel_map.get("coin1_o_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(7).setCellValue(excel_map.get("coin1_c_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(3).setCellValue(excel_map.get("coin1_l_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(7).setCellValue(excel_map.get("coin1_h_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(3).setCellValue(excel_map.get("coin1_open_close"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(7).setCellValue(excel_map.get("coin1_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(3).setCellValue(excel_map.get("coin1_5_rate_count"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(7).setCellValue(excel_map.get("coin1_10_rate_count"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(3).setCellValue(excel_map.get("coin1_15_rate_count"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(7).setCellValue(excel_map.get("coin1_20_rate_count"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(3).setCellValue(excel_map.get("coin1_25_rate_count"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(7).setCellValue(excel_map.get("coin1_30_rate_count"));
	        
	        form_sheet.getRow(Sheet1_Row+20).getCell(9).setCellValue(excel_map.get("coin1_oclh_1_date"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(10).setCellValue(excel_map.get("coin1_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(11).setCellValue(excel_map.get("coin1_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(12).setCellValue(excel_map.get("coin1_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(13).setCellValue(excel_map.get("coin1_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(14).setCellValue(excel_map.get("coin1_oclh_1_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+20).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+20).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+21).getCell(9).setCellValue(excel_map.get("coin1_oclh_2_date"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(10).setCellValue(excel_map.get("coin1_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(11).setCellValue(excel_map.get("coin1_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(12).setCellValue(excel_map.get("coin1_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(13).setCellValue(excel_map.get("coin1_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(14).setCellValue(excel_map.get("coin1_oclh_2_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+21).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+21).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+22).getCell(9).setCellValue(excel_map.get("coin1_oclh_3_date"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(10).setCellValue(excel_map.get("coin1_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(11).setCellValue(excel_map.get("coin1_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(12).setCellValue(excel_map.get("coin1_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(13).setCellValue(excel_map.get("coin1_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(14).setCellValue(excel_map.get("coin1_oclh_3_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+22).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+22).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+23).getCell(9).setCellValue(excel_map.get("coin1_oclh_4_date"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(10).setCellValue(excel_map.get("coin1_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(11).setCellValue(excel_map.get("coin1_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(12).setCellValue(excel_map.get("coin1_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(13).setCellValue(excel_map.get("coin1_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(14).setCellValue(excel_map.get("coin1_oclh_4_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+23).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+23).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+24).getCell(9).setCellValue(excel_map.get("coin1_oclh_5_date"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(10).setCellValue(excel_map.get("coin1_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(11).setCellValue(excel_map.get("coin1_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(12).setCellValue(excel_map.get("coin1_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(13).setCellValue(excel_map.get("coin1_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(14).setCellValue(excel_map.get("coin1_oclh_5_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+24).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+24).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+25).getCell(9).setCellValue(excel_map.get("coin1_oclh_6_date"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(10).setCellValue(excel_map.get("coin1_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(11).setCellValue(excel_map.get("coin1_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(12).setCellValue(excel_map.get("coin1_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(13).setCellValue(excel_map.get("coin1_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(14).setCellValue(excel_map.get("coin1_oclh_6_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+25).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+25).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+26).getCell(9).setCellValue(excel_map.get("coin1_oclh_7_date"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(10).setCellValue(excel_map.get("coin1_oclh_7_o_price"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(11).setCellValue(excel_map.get("coin1_oclh_7_l_price"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(12).setCellValue(excel_map.get("coin1_oclh_7_h_price"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(13).setCellValue(excel_map.get("coin1_oclh_7_c_price"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(14).setCellValue(excel_map.get("coin1_oclh_7_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_7_l_price"))) { form_sheet.getRow(Sheet1_Row+26).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_7_h_price"))) { form_sheet.getRow(Sheet1_Row+26).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(Sheet1_Row+28).getCell(2).setCellValue(excel_map.get("coin2_ticker"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(3).setCellValue(excel_map.get("coin2_o_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(7).setCellValue(excel_map.get("coin2_c_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(3).setCellValue(excel_map.get("coin2_l_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(7).setCellValue(excel_map.get("coin2_h_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(3).setCellValue(excel_map.get("coin2_open_close"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(7).setCellValue(excel_map.get("coin2_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(3).setCellValue(excel_map.get("coin2_5_rate_count"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(7).setCellValue(excel_map.get("coin2_10_rate_count"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(3).setCellValue(excel_map.get("coin2_15_rate_count"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(7).setCellValue(excel_map.get("coin2_20_rate_count"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(3).setCellValue(excel_map.get("coin2_25_rate_count"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(7).setCellValue(excel_map.get("coin2_30_rate_count"));
	        
	        form_sheet.getRow(Sheet1_Row+29).getCell(9).setCellValue(excel_map.get("coin2_oclh_1_date"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(10).setCellValue(excel_map.get("coin2_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(11).setCellValue(excel_map.get("coin2_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(12).setCellValue(excel_map.get("coin2_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(13).setCellValue(excel_map.get("coin2_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(14).setCellValue(excel_map.get("coin2_oclh_1_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+29).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+29).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+30).getCell(9).setCellValue(excel_map.get("coin2_oclh_2_date"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(10).setCellValue(excel_map.get("coin2_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(11).setCellValue(excel_map.get("coin2_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(12).setCellValue(excel_map.get("coin2_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(13).setCellValue(excel_map.get("coin2_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(14).setCellValue(excel_map.get("coin2_oclh_2_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+30).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+30).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+31).getCell(9).setCellValue(excel_map.get("coin2_oclh_3_date"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(10).setCellValue(excel_map.get("coin2_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(11).setCellValue(excel_map.get("coin2_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(12).setCellValue(excel_map.get("coin2_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(13).setCellValue(excel_map.get("coin2_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(14).setCellValue(excel_map.get("coin2_oclh_3_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+31).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+31).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+32).getCell(9).setCellValue(excel_map.get("coin2_oclh_4_date"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(10).setCellValue(excel_map.get("coin2_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(11).setCellValue(excel_map.get("coin2_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(12).setCellValue(excel_map.get("coin2_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(13).setCellValue(excel_map.get("coin2_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(14).setCellValue(excel_map.get("coin2_oclh_4_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+32).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+32).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+33).getCell(9).setCellValue(excel_map.get("coin2_oclh_5_date"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(10).setCellValue(excel_map.get("coin2_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(11).setCellValue(excel_map.get("coin2_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(12).setCellValue(excel_map.get("coin2_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(13).setCellValue(excel_map.get("coin2_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(14).setCellValue(excel_map.get("coin2_oclh_5_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+33).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+33).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+34).getCell(9).setCellValue(excel_map.get("coin2_oclh_6_date"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(10).setCellValue(excel_map.get("coin2_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(11).setCellValue(excel_map.get("coin2_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(12).setCellValue(excel_map.get("coin2_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(13).setCellValue(excel_map.get("coin2_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(14).setCellValue(excel_map.get("coin2_oclh_6_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+34).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+34).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+35).getCell(9).setCellValue(excel_map.get("coin2_oclh_7_date"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(10).setCellValue(excel_map.get("coin2_oclh_7_o_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(11).setCellValue(excel_map.get("coin2_oclh_7_l_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(12).setCellValue(excel_map.get("coin2_oclh_7_h_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(13).setCellValue(excel_map.get("coin2_oclh_7_c_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(14).setCellValue(excel_map.get("coin2_oclh_7_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_7_l_price"))) { form_sheet.getRow(Sheet1_Row+35).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_7_h_price"))) { form_sheet.getRow(Sheet1_Row+35).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(Sheet1_Row+37).getCell(2).setCellValue(excel_map.get("coin3_ticker"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(3).setCellValue(excel_map.get("coin3_o_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(7).setCellValue(excel_map.get("coin3_c_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(3).setCellValue(excel_map.get("coin3_l_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(7).setCellValue(excel_map.get("coin3_h_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(3).setCellValue(excel_map.get("coin3_open_close"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(7).setCellValue(excel_map.get("coin3_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(3).setCellValue(excel_map.get("coin3_5_rate_count"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(7).setCellValue(excel_map.get("coin3_10_rate_count"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(3).setCellValue(excel_map.get("coin3_15_rate_count"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(7).setCellValue(excel_map.get("coin3_20_rate_count"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(3).setCellValue(excel_map.get("coin3_25_rate_count"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(7).setCellValue(excel_map.get("coin3_30_rate_count"));
	        
	        form_sheet.getRow(Sheet1_Row+38).getCell(9).setCellValue(excel_map.get("coin3_oclh_1_date"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(10).setCellValue(excel_map.get("coin3_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(11).setCellValue(excel_map.get("coin3_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(12).setCellValue(excel_map.get("coin3_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(13).setCellValue(excel_map.get("coin3_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(14).setCellValue(excel_map.get("coin3_oclh_1_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+38).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+38).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+39).getCell(9).setCellValue(excel_map.get("coin3_oclh_2_date"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(10).setCellValue(excel_map.get("coin3_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(11).setCellValue(excel_map.get("coin3_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(12).setCellValue(excel_map.get("coin3_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(13).setCellValue(excel_map.get("coin3_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(14).setCellValue(excel_map.get("coin3_oclh_2_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+39).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+39).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+40).getCell(9).setCellValue(excel_map.get("coin3_oclh_3_date"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(10).setCellValue(excel_map.get("coin3_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(11).setCellValue(excel_map.get("coin3_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(12).setCellValue(excel_map.get("coin3_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(13).setCellValue(excel_map.get("coin3_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(14).setCellValue(excel_map.get("coin3_oclh_3_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+40).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+40).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+41).getCell(9).setCellValue(excel_map.get("coin3_oclh_4_date"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(10).setCellValue(excel_map.get("coin3_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(11).setCellValue(excel_map.get("coin3_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(12).setCellValue(excel_map.get("coin3_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(13).setCellValue(excel_map.get("coin3_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(14).setCellValue(excel_map.get("coin3_oclh_4_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+41).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+41).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+42).getCell(9).setCellValue(excel_map.get("coin3_oclh_5_date"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(10).setCellValue(excel_map.get("coin3_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(11).setCellValue(excel_map.get("coin3_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(12).setCellValue(excel_map.get("coin3_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(13).setCellValue(excel_map.get("coin3_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(14).setCellValue(excel_map.get("coin3_oclh_5_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+42).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+42).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+43).getCell(9).setCellValue(excel_map.get("coin3_oclh_6_date"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(10).setCellValue(excel_map.get("coin3_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(11).setCellValue(excel_map.get("coin3_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(12).setCellValue(excel_map.get("coin3_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(13).setCellValue(excel_map.get("coin3_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(14).setCellValue(excel_map.get("coin3_oclh_6_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+43).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+43).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+44).getCell(9).setCellValue(excel_map.get("coin3_oclh_7_date"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(10).setCellValue(excel_map.get("coin3_oclh_7_o_price"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(11).setCellValue(excel_map.get("coin3_oclh_7_l_price"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(12).setCellValue(excel_map.get("coin3_oclh_7_h_price"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(13).setCellValue(excel_map.get("coin3_oclh_7_c_price"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(14).setCellValue(excel_map.get("coin3_oclh_7_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_7_l_price"))) { form_sheet.getRow(Sheet1_Row+44).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_7_h_price"))) { form_sheet.getRow(Sheet1_Row+44).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        String kor_jucha_date = std_date.substring(2, 4) + "년 " + std_date.substring(5, 7)+"월 " + Integer.toString((int)(Integer.parseInt((std_date.substring(8, 9)).replace("0", "") + (std_date.substring(9, 10)))/7)+1) + "주차";
	        String kor_date = "["+std_date.substring(5, 7)+"월 "+(std_date.substring(8, 9)).replace("0", "") + (std_date.substring(9, 10)) +"일 ~ "+ end_date.substring(5, 7)+"월 "+(end_date.substring(8, 9)).replace("0", "") + (end_date.substring(9, 10))+"일]";
	        
	        String past_kor_jucha_date = past_std_date.substring(2, 4) + "년 " + past_std_date.substring(5, 7)+"월 " + Integer.toString((int)(Integer.parseInt((past_std_date.substring(8, 9)).replace("0", "") + (past_std_date.substring(9, 10)))/7)+1) + "주차";
	        String past_kor_date = "["+past_std_date.substring(5, 7)+"월 "+(past_std_date.substring(8, 9)).replace("0", "") + (past_std_date.substring(9, 10)) +"일 ~ "+ past_end_date.substring(5, 7)+"월 "+(past_end_date.substring(8, 9)).replace("0", "") + (past_end_date.substring(9, 10))+"일]";
	        
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

	        	String past_ranking_number = (ranking_list.get(x).get("before_ranking"));
	        	String past_ranking_o_price = (ranking_list.get(x).get("before_o_price")).toString();
	        	String past_ranking_l_price = (ranking_list.get(x).get("before_l_price")).toString();
	        	String past_ranking_h_price = (ranking_list.get(x).get("before_h_price")).toString();
	        	String past_ranking_c_price = (ranking_list.get(x).get("before_c_price")).toString();
	        	String past_ranking_o_c_rate = (ranking_list.get(x).get("before_o_c_price_rate")).toString();
	        	String past_ranking_l_h_rate = (ranking_list.get(x).get("before_l_h_price_rate")).toString();

	        	ranking_o_price = formatter.format(Double.parseDouble(ranking_o_price));
	        	ranking_l_price = formatter.format(Double.parseDouble(ranking_l_price));
	        	ranking_h_price = formatter.format(Double.parseDouble(ranking_h_price));
	        	ranking_c_price = formatter.format(Double.parseDouble(ranking_c_price));

	        	past_ranking_o_price = formatter.format(Double.parseDouble(past_ranking_o_price));
	        	past_ranking_l_price = formatter.format(Double.parseDouble(past_ranking_l_price));
	        	past_ranking_h_price = formatter.format(Double.parseDouble(past_ranking_h_price));
	        	past_ranking_c_price = formatter.format(Double.parseDouble(past_ranking_c_price));

	        	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
	        		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
	        	}

	        	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
	        		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
	        	}

	        	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
	        		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
	        	}

	        	if(past_ranking_o_c_rate.contains(".") && ((past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("0") || (past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("."))) {
	        		past_ranking_o_c_rate = past_ranking_o_c_rate.substring(0, past_ranking_o_c_rate.length()-1);
	        	}

	        	if(past_ranking_o_c_rate.contains(".") && ((past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("0") || (past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("."))) {
	        		past_ranking_o_c_rate = past_ranking_o_c_rate.substring(0, past_ranking_o_c_rate.length()-1);
	        	}

	        	if(past_ranking_o_c_rate.contains(".") && ((past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("0") || (past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("."))) {
	        		past_ranking_o_c_rate = past_ranking_o_c_rate.substring(0, past_ranking_o_c_rate.length()-1);
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
	        	
	        	if(past_ranking_l_h_rate.contains(".") && ((past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("0") || (past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("."))) {
	        		past_ranking_l_h_rate = past_ranking_l_h_rate.substring(0, past_ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(past_ranking_l_h_rate.contains(".") && ((past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("0") || (past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("."))) {
	        		past_ranking_l_h_rate = past_ranking_l_h_rate.substring(0, past_ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(past_ranking_l_h_rate.contains(".") && ((past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("0") || (past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("."))) {
	        		past_ranking_l_h_rate = past_ranking_l_h_rate.substring(0, past_ranking_l_h_rate.length()-1);
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
	        		today1_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 2) {
	        		today2_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 3) {
	        		today3_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 4) {
	        		today4_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 5) {
	        		today5_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 6) {
	        		today6_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 7) {
	        		today7_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 8) {
	        		today8_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 9) {
	        		today9_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 10) {
	        		today10_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 11) {
	        		today11_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 12) {
	        		today12_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 13) {
	        		today13_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 14) {
	        		today14_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 15) {
	        		today15_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 16) {
	        		today16_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 17) {
	        		today17_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 18) {
	        		today18_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 19) {
	        		today19_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
	        	} else if(x == 20) {
	        		today20_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_jucha_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
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
	        summary_sheet.getRow(Sheet2_Row).getCell(1).setCellValue("바이비트 " + kor_jucha_date + " 코인 분석");
	        
	        row = summary_sheet.createRow(Sheet2_Row+1);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+1).getCell(1).setCellValue("바이비트 " + kor_jucha_date + kor_date + " 분석을 시작하겠습니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+3);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+3).getCell(1).setCellValue("BTC 기준 시가 대비 종가 " + excel_map.get("summary_1_1") + kor_jucha_date + kor_date + " 바이비트 원화 코인 중 "+coin_total_count+"종 중 상승인 코인이 " + coin_increase_count + "종, 하락인 코인이 " + coin_degradation_count + "종, 보합인 코인이 " + coin_flat_count + "종입니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+4);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+4).getCell(1).setCellValue(kor_jucha_date + kor_date + "에 투자했으면 가장 안정적으로 많이 벌 수 있었던 코인 3~1순위를 알려드리겠습니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+5);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+5).getCell(1).setCellValue("이번주 코인 매매 시 한 번 고려 해보시기 바랍니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+8);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+8).getCell(1).setCellValue(kor_jucha_date + " 3순위 " + excel_map.get("coin3_ticker"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+10);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+10).getCell(1).setCellValue(kor_jucha_date + kor_date + " " + excel_map.get("coin3_summary"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+12);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+12).getCell(1).setCellValue(kor_jucha_date + " 2순위 " + excel_map.get("coin2_ticker"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+14);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+14).getCell(1).setCellValue(kor_jucha_date + kor_date + " " + excel_map.get("coin2_summary"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+16);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+16).getCell(1).setCellValue(kor_jucha_date + " 1순위 " + excel_map.get("coin1_ticker"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+18);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+18).getCell(1).setCellValue(kor_jucha_date + kor_date + " " + excel_map.get("coin1_summary"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+20);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+20).getCell(1).setCellValue(kor_jucha_date + kor_date + " 요약");
	        
	        row = summary_sheet.createRow(Sheet2_Row+21);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+21).getCell(1).setCellValue(kor_jucha_date + kor_date + " 바이비트 코인 중 BTC가 " + excel_map.get("summary_1_1") + " 바이비트 원화 코인 "+coin_total_count+"종 중 상승인 코인이 " + coin_increase_count + "종, 하락인 코인이 " + coin_degradation_count + "종, 보합인 코인이 " + coin_flat_count + "종입니다.");
	        
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
	        
	        fileName = "바이비트_Weekly_"+std_date+"_"+end_date+".xlsx";
	        
	        fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        response.setContentType("application/vsd.ms-excel");
	        response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
	        
	        form_wb.write(response.getOutputStream());
	        form_wb.close();
		} catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@GetMapping(path = "/ExcelMake_Monthly")
	public void ExcelMake_Monthly(HttpServletRequest req, HttpServletResponse response) throws Exception {
		String std_date = (req.getParameter("std_date")==null)?"":req.getParameter("std_date");
		String end_date = (req.getParameter("end_date")==null)?"":req.getParameter("end_date");
		String yyyy = (req.getParameter("yyyy")==null)?"":req.getParameter("yyyy");
		String mm = (req.getParameter("mm")==null)?"":req.getParameter("mm");
		String excel_title = (req.getParameter("excel_title")==null)?"":req.getParameter("excel_title");
		String coin_ticker = (req.getParameter("coin_ticker")==null)?"":req.getParameter("coin_ticker");
		coin_ticker = coin_ticker.replaceAll(",", "','");
		
		DecimalFormat formatter = new DecimalFormat("###,###.########");
		SimpleDateFormat SDFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date FormatD = new SimpleDateFormat("yyyy-MM-dd").parse(std_date);
        
        Calendar std_cal = Calendar.getInstance();
        std_cal.setTime(FormatD);
        std_cal.add(Calendar.DATE, -1);
        
        String past_end_date = SDFormat.format(std_cal.getTime());
        String past_std_date = past_end_date.substring(0, 7)+"-01";
        
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("std_date", std_date);
        map.put("end_date", end_date);
        map.put("past_std_date", past_std_date);
        map.put("past_end_date", past_end_date);
        map.put("coin_ticker", coin_ticker);
        
		String fileName = "";
		try {
			req.setCharacterEncoding("UTF-8");
			
			InputStream fis = new FileInputStream(new File("C:/Coin/Excel/Monthly.xlsx"));
	        XSSFWorkbook form_wb = new XSSFWorkbook(fis);
	        XSSFSheet form_sheet = form_wb.getSheetAt(0);
	        XSSFSheet ranking_sheet = form_wb.getSheetAt(1);
	        XSSFSheet summary_sheet = form_wb.createSheet("스크립트");
	        
	        List<HashMap<String, String>> result_map = BybitCoinService.ExcelMake_Weekly_Result(map);
	        String coin_total_count = result_map.get(0).get("total_count");
	        String coin_increase_count = result_map.get(0).get("increase_count");
	        String coin_degradation_count = result_map.get(0).get("degradation_count");
	        String coin_flat_count = result_map.get(0).get("flat_count");
	        String coin_increase_ticker = result_map.get(0).get("increase_ticker");
	        String coin_degradation_ticker = result_map.get(0).get("degradation_ticker");
	        String coin_flat_ticker = result_map.get(0).get("flat_ticker");
	        
	        List<HashMap<String, String>> ranking_list = BybitCoinService.ExcelMake_Weekly_Ranking(map);
	        
	        String result_data = " "+coin_total_count+" | " + coin_increase_count +" | " + coin_degradation_count +" | " + coin_flat_count;
	        form_wb.setSheetName(0, result_data);
	        
	        HashMap<String, String> excel_map = BybitCoinService.ExcelMake_Monthly(map);
	        
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
	        
	        form_sheet.getRow(Sheet1_Row).getCell(1).setCellValue("바이비트 월간 분석 " + std_date + " ~ " + end_date);
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
	        form_sheet.getRow(Sheet1_Row+15).getCell(3).setCellValue(excel_map.get("btc_5_rate_count"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(7).setCellValue(excel_map.get("btc_10_rate_count"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(3).setCellValue(excel_map.get("btc_15_rate_count"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(7).setCellValue(excel_map.get("btc_20_rate_count"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(3).setCellValue(excel_map.get("btc_25_rate_count"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(7).setCellValue(excel_map.get("btc_30_rate_count"));
	        
	        form_sheet.getRow(Sheet1_Row+11).getCell(9).setCellValue(excel_map.get("btc_oclh_1_date"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(10).setCellValue(excel_map.get("btc_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(11).setCellValue(excel_map.get("btc_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(12).setCellValue(excel_map.get("btc_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(13).setCellValue(excel_map.get("btc_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+11).getCell(14).setCellValue(excel_map.get("btc_oclh_1_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+11).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+11).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+12).getCell(9).setCellValue(excel_map.get("btc_oclh_2_date"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(10).setCellValue(excel_map.get("btc_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(11).setCellValue(excel_map.get("btc_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(12).setCellValue(excel_map.get("btc_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(13).setCellValue(excel_map.get("btc_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+12).getCell(14).setCellValue(excel_map.get("btc_oclh_2_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+12).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+12).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+13).getCell(9).setCellValue(excel_map.get("btc_oclh_3_date"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(10).setCellValue(excel_map.get("btc_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(11).setCellValue(excel_map.get("btc_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(12).setCellValue(excel_map.get("btc_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(13).setCellValue(excel_map.get("btc_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+13).getCell(14).setCellValue(excel_map.get("btc_oclh_3_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+13).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+13).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+14).getCell(9).setCellValue(excel_map.get("btc_oclh_4_date"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(10).setCellValue(excel_map.get("btc_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(11).setCellValue(excel_map.get("btc_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(12).setCellValue(excel_map.get("btc_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(13).setCellValue(excel_map.get("btc_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+14).getCell(14).setCellValue(excel_map.get("btc_oclh_4_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+14).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+14).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+15).getCell(9).setCellValue(excel_map.get("btc_oclh_5_date"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(10).setCellValue(excel_map.get("btc_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(11).setCellValue(excel_map.get("btc_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(12).setCellValue(excel_map.get("btc_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(13).setCellValue(excel_map.get("btc_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+15).getCell(14).setCellValue(excel_map.get("btc_oclh_5_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+15).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+15).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+16).getCell(9).setCellValue(excel_map.get("btc_oclh_6_date"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(10).setCellValue(excel_map.get("btc_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(11).setCellValue(excel_map.get("btc_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(12).setCellValue(excel_map.get("btc_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(13).setCellValue(excel_map.get("btc_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+16).getCell(14).setCellValue(excel_map.get("btc_oclh_6_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+16).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+16).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+17).getCell(9).setCellValue(excel_map.get("btc_oclh_7_date"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(10).setCellValue(excel_map.get("btc_oclh_7_o_price"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(11).setCellValue(excel_map.get("btc_oclh_7_l_price"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(12).setCellValue(excel_map.get("btc_oclh_7_h_price"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(13).setCellValue(excel_map.get("btc_oclh_7_c_price"));
	        form_sheet.getRow(Sheet1_Row+17).getCell(14).setCellValue(excel_map.get("btc_oclh_7_o_c_price_rate"));
	        if((excel_map.get("btc_l_price")).equals(excel_map.get("btc_oclh_7_l_price"))) { form_sheet.getRow(Sheet1_Row+17).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("btc_h_price")).equals(excel_map.get("btc_oclh_7_h_price"))) { form_sheet.getRow(Sheet1_Row+17).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(Sheet1_Row+19).getCell(2).setCellValue(excel_map.get("coin1_ticker"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(3).setCellValue(excel_map.get("coin1_o_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(7).setCellValue(excel_map.get("coin1_c_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(3).setCellValue(excel_map.get("coin1_l_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(7).setCellValue(excel_map.get("coin1_h_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(3).setCellValue(excel_map.get("coin1_open_close"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(7).setCellValue(excel_map.get("coin1_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(3).setCellValue(excel_map.get("coin1_5_rate_count"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(7).setCellValue(excel_map.get("coin1_10_rate_count"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(3).setCellValue(excel_map.get("coin1_15_rate_count"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(7).setCellValue(excel_map.get("coin1_20_rate_count"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(3).setCellValue(excel_map.get("coin1_25_rate_count"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(7).setCellValue(excel_map.get("coin1_30_rate_count"));
	        
	        form_sheet.getRow(Sheet1_Row+20).getCell(9).setCellValue(excel_map.get("coin1_oclh_1_date"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(10).setCellValue(excel_map.get("coin1_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(11).setCellValue(excel_map.get("coin1_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(12).setCellValue(excel_map.get("coin1_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(13).setCellValue(excel_map.get("coin1_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+20).getCell(14).setCellValue(excel_map.get("coin1_oclh_1_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+20).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+20).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+21).getCell(9).setCellValue(excel_map.get("coin1_oclh_2_date"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(10).setCellValue(excel_map.get("coin1_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(11).setCellValue(excel_map.get("coin1_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(12).setCellValue(excel_map.get("coin1_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(13).setCellValue(excel_map.get("coin1_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+21).getCell(14).setCellValue(excel_map.get("coin1_oclh_2_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+21).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+21).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+22).getCell(9).setCellValue(excel_map.get("coin1_oclh_3_date"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(10).setCellValue(excel_map.get("coin1_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(11).setCellValue(excel_map.get("coin1_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(12).setCellValue(excel_map.get("coin1_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(13).setCellValue(excel_map.get("coin1_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+22).getCell(14).setCellValue(excel_map.get("coin1_oclh_3_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+22).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+22).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+23).getCell(9).setCellValue(excel_map.get("coin1_oclh_4_date"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(10).setCellValue(excel_map.get("coin1_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(11).setCellValue(excel_map.get("coin1_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(12).setCellValue(excel_map.get("coin1_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(13).setCellValue(excel_map.get("coin1_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+23).getCell(14).setCellValue(excel_map.get("coin1_oclh_4_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+23).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+23).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+24).getCell(9).setCellValue(excel_map.get("coin1_oclh_5_date"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(10).setCellValue(excel_map.get("coin1_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(11).setCellValue(excel_map.get("coin1_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(12).setCellValue(excel_map.get("coin1_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(13).setCellValue(excel_map.get("coin1_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+24).getCell(14).setCellValue(excel_map.get("coin1_oclh_5_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+24).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+24).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+25).getCell(9).setCellValue(excel_map.get("coin1_oclh_6_date"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(10).setCellValue(excel_map.get("coin1_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(11).setCellValue(excel_map.get("coin1_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(12).setCellValue(excel_map.get("coin1_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(13).setCellValue(excel_map.get("coin1_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+25).getCell(14).setCellValue(excel_map.get("coin1_oclh_6_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+25).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+25).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+26).getCell(9).setCellValue(excel_map.get("coin1_oclh_7_date"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(10).setCellValue(excel_map.get("coin1_oclh_7_o_price"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(11).setCellValue(excel_map.get("coin1_oclh_7_l_price"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(12).setCellValue(excel_map.get("coin1_oclh_7_h_price"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(13).setCellValue(excel_map.get("coin1_oclh_7_c_price"));
	        form_sheet.getRow(Sheet1_Row+26).getCell(14).setCellValue(excel_map.get("coin1_oclh_7_o_c_price_rate"));
	        if((excel_map.get("coin1_l_price")).equals(excel_map.get("coin1_oclh_7_l_price"))) { form_sheet.getRow(Sheet1_Row+26).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin1_h_price")).equals(excel_map.get("coin1_oclh_7_h_price"))) { form_sheet.getRow(Sheet1_Row+26).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(Sheet1_Row+28).getCell(2).setCellValue(excel_map.get("coin2_ticker"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(3).setCellValue(excel_map.get("coin2_o_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(7).setCellValue(excel_map.get("coin2_c_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(3).setCellValue(excel_map.get("coin2_l_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(7).setCellValue(excel_map.get("coin2_h_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(3).setCellValue(excel_map.get("coin2_open_close"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(7).setCellValue(excel_map.get("coin2_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(3).setCellValue(excel_map.get("coin2_5_rate_count"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(7).setCellValue(excel_map.get("coin2_10_rate_count"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(3).setCellValue(excel_map.get("coin2_15_rate_count"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(7).setCellValue(excel_map.get("coin2_20_rate_count"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(3).setCellValue(excel_map.get("coin2_25_rate_count"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(7).setCellValue(excel_map.get("coin2_30_rate_count"));
	        
	        form_sheet.getRow(Sheet1_Row+29).getCell(9).setCellValue(excel_map.get("coin2_oclh_1_date"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(10).setCellValue(excel_map.get("coin2_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(11).setCellValue(excel_map.get("coin2_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(12).setCellValue(excel_map.get("coin2_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(13).setCellValue(excel_map.get("coin2_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+29).getCell(14).setCellValue(excel_map.get("coin2_oclh_1_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+29).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+29).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+30).getCell(9).setCellValue(excel_map.get("coin2_oclh_2_date"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(10).setCellValue(excel_map.get("coin2_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(11).setCellValue(excel_map.get("coin2_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(12).setCellValue(excel_map.get("coin2_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(13).setCellValue(excel_map.get("coin2_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+30).getCell(14).setCellValue(excel_map.get("coin2_oclh_2_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+30).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+30).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+31).getCell(9).setCellValue(excel_map.get("coin2_oclh_3_date"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(10).setCellValue(excel_map.get("coin2_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(11).setCellValue(excel_map.get("coin2_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(12).setCellValue(excel_map.get("coin2_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(13).setCellValue(excel_map.get("coin2_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+31).getCell(14).setCellValue(excel_map.get("coin2_oclh_3_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+31).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+31).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+32).getCell(9).setCellValue(excel_map.get("coin2_oclh_4_date"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(10).setCellValue(excel_map.get("coin2_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(11).setCellValue(excel_map.get("coin2_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(12).setCellValue(excel_map.get("coin2_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(13).setCellValue(excel_map.get("coin2_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+32).getCell(14).setCellValue(excel_map.get("coin2_oclh_4_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+32).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+32).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+33).getCell(9).setCellValue(excel_map.get("coin2_oclh_5_date"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(10).setCellValue(excel_map.get("coin2_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(11).setCellValue(excel_map.get("coin2_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(12).setCellValue(excel_map.get("coin2_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(13).setCellValue(excel_map.get("coin2_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+33).getCell(14).setCellValue(excel_map.get("coin2_oclh_5_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+33).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+33).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+34).getCell(9).setCellValue(excel_map.get("coin2_oclh_6_date"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(10).setCellValue(excel_map.get("coin2_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(11).setCellValue(excel_map.get("coin2_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(12).setCellValue(excel_map.get("coin2_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(13).setCellValue(excel_map.get("coin2_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+34).getCell(14).setCellValue(excel_map.get("coin2_oclh_6_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+34).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+34).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+35).getCell(9).setCellValue(excel_map.get("coin2_oclh_7_date"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(10).setCellValue(excel_map.get("coin2_oclh_7_o_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(11).setCellValue(excel_map.get("coin2_oclh_7_l_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(12).setCellValue(excel_map.get("coin2_oclh_7_h_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(13).setCellValue(excel_map.get("coin2_oclh_7_c_price"));
	        form_sheet.getRow(Sheet1_Row+35).getCell(14).setCellValue(excel_map.get("coin2_oclh_7_o_c_price_rate"));
	        if((excel_map.get("coin2_l_price")).equals(excel_map.get("coin2_oclh_7_l_price"))) { form_sheet.getRow(Sheet1_Row+35).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin2_h_price")).equals(excel_map.get("coin2_oclh_7_h_price"))) { form_sheet.getRow(Sheet1_Row+35).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        form_sheet.getRow(Sheet1_Row+37).getCell(2).setCellValue(excel_map.get("coin3_ticker"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(3).setCellValue(excel_map.get("coin3_o_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(7).setCellValue(excel_map.get("coin3_c_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(3).setCellValue(excel_map.get("coin3_l_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(7).setCellValue(excel_map.get("coin3_h_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(3).setCellValue(excel_map.get("coin3_open_close"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(7).setCellValue(excel_map.get("coin3_lowest_highest"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(3).setCellValue(excel_map.get("coin3_5_rate_count"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(7).setCellValue(excel_map.get("coin3_10_rate_count"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(3).setCellValue(excel_map.get("coin3_15_rate_count"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(7).setCellValue(excel_map.get("coin3_20_rate_count"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(3).setCellValue(excel_map.get("coin3_25_rate_count"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(7).setCellValue(excel_map.get("coin3_30_rate_count"));
	        
	        form_sheet.getRow(Sheet1_Row+38).getCell(9).setCellValue(excel_map.get("coin3_oclh_1_date"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(10).setCellValue(excel_map.get("coin3_oclh_1_o_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(11).setCellValue(excel_map.get("coin3_oclh_1_l_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(12).setCellValue(excel_map.get("coin3_oclh_1_h_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(13).setCellValue(excel_map.get("coin3_oclh_1_c_price"));
	        form_sheet.getRow(Sheet1_Row+38).getCell(14).setCellValue(excel_map.get("coin3_oclh_1_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_1_l_price"))) { form_sheet.getRow(Sheet1_Row+38).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_1_h_price"))) { form_sheet.getRow(Sheet1_Row+38).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+39).getCell(9).setCellValue(excel_map.get("coin3_oclh_2_date"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(10).setCellValue(excel_map.get("coin3_oclh_2_o_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(11).setCellValue(excel_map.get("coin3_oclh_2_l_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(12).setCellValue(excel_map.get("coin3_oclh_2_h_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(13).setCellValue(excel_map.get("coin3_oclh_2_c_price"));
	        form_sheet.getRow(Sheet1_Row+39).getCell(14).setCellValue(excel_map.get("coin3_oclh_2_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_2_l_price"))) { form_sheet.getRow(Sheet1_Row+39).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_2_h_price"))) { form_sheet.getRow(Sheet1_Row+39).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+40).getCell(9).setCellValue(excel_map.get("coin3_oclh_3_date"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(10).setCellValue(excel_map.get("coin3_oclh_3_o_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(11).setCellValue(excel_map.get("coin3_oclh_3_l_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(12).setCellValue(excel_map.get("coin3_oclh_3_h_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(13).setCellValue(excel_map.get("coin3_oclh_3_c_price"));
	        form_sheet.getRow(Sheet1_Row+40).getCell(14).setCellValue(excel_map.get("coin3_oclh_3_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_3_l_price"))) { form_sheet.getRow(Sheet1_Row+40).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_3_h_price"))) { form_sheet.getRow(Sheet1_Row+40).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+41).getCell(9).setCellValue(excel_map.get("coin3_oclh_4_date"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(10).setCellValue(excel_map.get("coin3_oclh_4_o_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(11).setCellValue(excel_map.get("coin3_oclh_4_l_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(12).setCellValue(excel_map.get("coin3_oclh_4_h_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(13).setCellValue(excel_map.get("coin3_oclh_4_c_price"));
	        form_sheet.getRow(Sheet1_Row+41).getCell(14).setCellValue(excel_map.get("coin3_oclh_4_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_4_l_price"))) { form_sheet.getRow(Sheet1_Row+41).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_4_h_price"))) { form_sheet.getRow(Sheet1_Row+41).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+42).getCell(9).setCellValue(excel_map.get("coin3_oclh_5_date"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(10).setCellValue(excel_map.get("coin3_oclh_5_o_price"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(11).setCellValue(excel_map.get("coin3_oclh_5_l_price"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(12).setCellValue(excel_map.get("coin3_oclh_5_h_price"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(13).setCellValue(excel_map.get("coin3_oclh_5_c_price"));
	        form_sheet.getRow(Sheet1_Row+42).getCell(14).setCellValue(excel_map.get("coin3_oclh_5_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_5_l_price"))) { form_sheet.getRow(Sheet1_Row+42).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_5_h_price"))) { form_sheet.getRow(Sheet1_Row+42).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+43).getCell(9).setCellValue(excel_map.get("coin3_oclh_6_date"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(10).setCellValue(excel_map.get("coin3_oclh_6_o_price"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(11).setCellValue(excel_map.get("coin3_oclh_6_l_price"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(12).setCellValue(excel_map.get("coin3_oclh_6_h_price"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(13).setCellValue(excel_map.get("coin3_oclh_6_c_price"));
	        form_sheet.getRow(Sheet1_Row+43).getCell(14).setCellValue(excel_map.get("coin3_oclh_6_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_6_l_price"))) { form_sheet.getRow(Sheet1_Row+43).getCell(11).setCellStyle(cell_Blue); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_6_h_price"))) { form_sheet.getRow(Sheet1_Row+43).getCell(12).setCellStyle(cell_Red); }
	        
	        form_sheet.getRow(Sheet1_Row+44).getCell(9).setCellValue(excel_map.get("coin3_oclh_7_date"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(10).setCellValue(excel_map.get("coin3_oclh_7_o_price"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(11).setCellValue(excel_map.get("coin3_oclh_7_l_price"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(12).setCellValue(excel_map.get("coin3_oclh_7_h_price"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(13).setCellValue(excel_map.get("coin3_oclh_7_c_price"));
	        form_sheet.getRow(Sheet1_Row+44).getCell(14).setCellValue(excel_map.get("coin3_oclh_7_o_c_price_rate"));
	        if((excel_map.get("coin3_l_price")).equals(excel_map.get("coin3_oclh_7_l_price"))) { form_sheet.getRow(Sheet1_Row+44).getCell(11).setCellStyle(cell_Blue_Bottom); }
	        if((excel_map.get("coin3_h_price")).equals(excel_map.get("coin3_oclh_7_h_price"))) { form_sheet.getRow(Sheet1_Row+44).getCell(12).setCellStyle(cell_Red_Bottom); }
	        
	        String kor_month_date = std_date.substring(2, 4) + "년 " + std_date.substring(5, 7)+"월";
	        String kor_date = "["+std_date.substring(5, 7)+"월 "+(std_date.substring(8, 9)).replace("0", "") + (std_date.substring(9, 10)) +"일 ~ "+ end_date.substring(5, 7)+"월 "+(end_date.substring(8, 9)).replace("0", "") + (end_date.substring(9, 10))+"일]";
	        
	        String past_kor_month_date = past_std_date.substring(2, 4) + "년 " + past_std_date.substring(5, 7)+"월";
	        String past_kor_date = "["+past_std_date.substring(5, 7)+"월 "+(past_std_date.substring(8, 9)).replace("0", "") + (past_std_date.substring(9, 10)) +"일 ~ "+ past_end_date.substring(5, 7)+"월 "+(past_end_date.substring(8, 9)).replace("0", "") + (past_end_date.substring(9, 10))+"일]";
	        
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
	        	System.out.println("["+ranking_list.get(x).get("ranking")+"] "+ranking_list.get(x).get("coin_name"));
	        }
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

	        	String past_ranking_number = (ranking_list.get(x).get("before_ranking"));
	        	String past_ranking_o_price = (ranking_list.get(x).get("before_o_price")).toString();
	        	String past_ranking_l_price = (ranking_list.get(x).get("before_l_price")).toString();
	        	String past_ranking_h_price = (ranking_list.get(x).get("before_h_price")).toString();
	        	String past_ranking_c_price = (ranking_list.get(x).get("before_c_price")).toString();
	        	String past_ranking_o_c_rate = (ranking_list.get(x).get("before_o_c_price_rate")).toString();
	        	String past_ranking_l_h_rate = (ranking_list.get(x).get("before_l_h_price_rate")).toString();

	        	ranking_o_price = formatter.format(Double.parseDouble(ranking_o_price));
	        	ranking_l_price = formatter.format(Double.parseDouble(ranking_l_price));
	        	ranking_h_price = formatter.format(Double.parseDouble(ranking_h_price));
	        	ranking_c_price = formatter.format(Double.parseDouble(ranking_c_price));

	        	past_ranking_o_price = formatter.format(Double.parseDouble(past_ranking_o_price));
	        	past_ranking_l_price = formatter.format(Double.parseDouble(past_ranking_l_price));
	        	past_ranking_h_price = formatter.format(Double.parseDouble(past_ranking_h_price));
	        	past_ranking_c_price = formatter.format(Double.parseDouble(past_ranking_c_price));

	        	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
	        		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
	        	}

	        	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
	        		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
	        	}

	        	if(ranking_o_c_rate.contains(".") && ((ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("0") || (ranking_o_c_rate.substring(ranking_o_c_rate.length()-1)).equals("."))) {
	        		ranking_o_c_rate = ranking_o_c_rate.substring(0, ranking_o_c_rate.length()-1);
	        	}

	        	if(past_ranking_o_c_rate.contains(".") && ((past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("0") || (past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("."))) {
	        		past_ranking_o_c_rate = past_ranking_o_c_rate.substring(0, past_ranking_o_c_rate.length()-1);
	        	}

	        	if(past_ranking_o_c_rate.contains(".") && ((past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("0") || (past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("."))) {
	        		past_ranking_o_c_rate = past_ranking_o_c_rate.substring(0, past_ranking_o_c_rate.length()-1);
	        	}

	        	if(past_ranking_o_c_rate.contains(".") && ((past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("0") || (past_ranking_o_c_rate.substring(past_ranking_o_c_rate.length()-1)).equals("."))) {
	        		past_ranking_o_c_rate = past_ranking_o_c_rate.substring(0, past_ranking_o_c_rate.length()-1);
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
	        	
	        	if(past_ranking_l_h_rate.contains(".") && ((past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("0") || (past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("."))) {
	        		past_ranking_l_h_rate = past_ranking_l_h_rate.substring(0, past_ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(past_ranking_l_h_rate.contains(".") && ((past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("0") || (past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("."))) {
	        		past_ranking_l_h_rate = past_ranking_l_h_rate.substring(0, past_ranking_l_h_rate.length()-1);
	        	}
	        	
	        	if(past_ranking_l_h_rate.contains(".") && ((past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("0") || (past_ranking_l_h_rate.substring(past_ranking_l_h_rate.length()-1)).equals("."))) {
	        		past_ranking_l_h_rate = past_ranking_l_h_rate.substring(0, past_ranking_l_h_rate.length()-1);
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
	        	
	        	String tmp_text = "";
	        	if(past_ranking_o_price.equals("0") && past_ranking_l_price.equals("0") && past_ranking_h_price.equals("0") && past_ranking_c_price.equals("0")) {
	        		tmp_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%입니다.";
        		}else {
        			tmp_text = "상승률 "+ranking_number+"위는 " + ranking_coin_name + "로 " + ranking_o_c_rate +"%이며, 지난[" + past_kor_month_date + "]는 " + past_ranking_o_c_rate + "%로 "+ past_ranking_number +"등을 했습니다." ;
        		}
	        	
	        	if(x == 1) {
	        		today1_text = tmp_text;
	        	} else if(x == 2) {
	        		today2_text = tmp_text;
	        	} else if(x == 3) {
	        		today3_text = tmp_text;
	        	} else if(x == 4) {
	        		today4_text = tmp_text;
	        	} else if(x == 5) {
	        		today5_text = tmp_text;
	        	} else if(x == 6) {
	        		today6_text = tmp_text;
	        	} else if(x == 7) {
	        		today7_text = tmp_text;
	        	} else if(x == 8) {
	        		today8_text = tmp_text;
	        	} else if(x == 9) {
	        		today9_text = tmp_text;
	        	} else if(x == 10) {
	        		today10_text = tmp_text;
	        	} else if(x == 11) {
	        		today11_text = tmp_text;
	        	} else if(x == 12) {
	        		today12_text = tmp_text;
	        	} else if(x == 13) {
	        		today13_text = tmp_text;
	        	} else if(x == 14) {
	        		today14_text = tmp_text;
	        	} else if(x == 15) {
	        		today15_text = tmp_text;
	        	} else if(x == 16) {
	        		today16_text = tmp_text;
	        	} else if(x == 17) {
	        		today17_text = tmp_text;
	        	} else if(x == 18) {
	        		today18_text = tmp_text;
	        	} else if(x == 19) {
	        		today19_text = tmp_text;
	        	} else if(x == 20) {
	        		today20_text = tmp_text;
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
	        summary_sheet.getRow(Sheet2_Row).getCell(1).setCellValue("바이비트 " + kor_month_date + " 코인 분석");
	        
	        row = summary_sheet.createRow(Sheet2_Row+1);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+1).getCell(1).setCellValue("바이비트 " + kor_month_date + kor_date + " 분석을 시작하겠습니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+3);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+3).getCell(1).setCellValue("BTC 기준 시가 대비 종가 " + excel_map.get("summary_1_1") + kor_month_date + kor_date + " 바이비트 원화 코인 중 "+coin_total_count+"종 중 상승인 코인이 " + coin_increase_count + "종, 하락인 코인이 " + coin_degradation_count + "종, 보합인 코인이 " + coin_flat_count + "종입니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+4);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+4).getCell(1).setCellValue(kor_month_date + kor_date + "에 투자했으면 가장 안정적으로 많이 벌 수 있었던 코인 3~1순위를 알려드리겠습니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+5);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+5).getCell(1).setCellValue("이번주 코인 매매 시 한 번 고려 해보시기 바랍니다.");
	        
	        row = summary_sheet.createRow(Sheet2_Row+8);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+8).getCell(1).setCellValue(kor_month_date + " 3순위 " + excel_map.get("coin3_ticker"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+10);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+10).getCell(1).setCellValue(kor_month_date + kor_date + " " + excel_map.get("coin3_summary"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+12);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+12).getCell(1).setCellValue(kor_month_date + " 2순위 " + excel_map.get("coin2_ticker"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+14);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+14).getCell(1).setCellValue(kor_month_date + kor_date + " " + excel_map.get("coin2_summary"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+16);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+16).getCell(1).setCellValue(kor_month_date + " 1순위 " + excel_map.get("coin1_ticker"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+18);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+18).getCell(1).setCellValue(kor_month_date + kor_date + " " + excel_map.get("coin1_summary"));
	        
	        row = summary_sheet.createRow(Sheet2_Row+20);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+20).getCell(1).setCellValue(kor_month_date + kor_date + " 요약");
	        
	        row = summary_sheet.createRow(Sheet2_Row+21);
	        cell = row.createCell(1);
	        summary_sheet.getRow(Sheet2_Row+21).getCell(1).setCellValue(kor_month_date + kor_date + " 바이비트 코인 중 BTC가 " + excel_map.get("summary_1_1") + " 바이비트 원화 코인 "+coin_total_count+"종 중 상승인 코인이 " + coin_increase_count + "종, 하락인 코인이 " + coin_degradation_count + "종, 보합인 코인이 " + coin_flat_count + "종입니다.");
	        
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
	        
	        fileName = "바이비트_Monthly_"+excel_title+".xlsx";
	        
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