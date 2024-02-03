package com.diff._private.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diff._private.Mapper.Main.MainMapper;

@Service
@Transactional
public class MainService {
	
	@Autowired
	MainMapper MainMapper;
	
	public String SelectCodeData(String parameter) throws Exception{
		return MainMapper.SelectCodeData(parameter);
	}
	
	public void UpbitCoinMIN(HashMap<String, String> map) throws Exception{
		MainMapper.UpbitCoinMIN(map);
	}
	
	public HashMap<String, String> CoinAnalysisSetting() throws Exception{
		return MainMapper.CoinAnalysisSetting();
	}
	
	public List<HashMap<String, String>> UPBIT_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.UPBIT_BTC_HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> UPBIT_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.UPBIT_BTC_4HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> UPBIT_DAY_RANKING(HashMap<String, String> map) throws Exception{
		return MainMapper.UPBIT_DAY_RANKING(map);
	}
	
	public List<HashMap<String, String>> BITHUMB_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.BITHUMB_BTC_HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> BITHUMB_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.BITHUMB_BTC_4HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> BITHUMB_DAY_RANKING(HashMap<String, String> map) throws Exception{
		return MainMapper.BITHUMB_DAY_RANKING(map);
	}
	
	public List<HashMap<String, String>> BINANCE_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.BINANCE_BTC_HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> BINANCE_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.BINANCE_BTC_4HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> BINANCE_DAY_RANKING(HashMap<String, String> map) throws Exception{
		return MainMapper.BINANCE_DAY_RANKING(map);
	}
	
	public List<HashMap<String, String>> BINANCE_FUTURES_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.BINANCE_FUTURES_BTC_HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> BINANCE_FUTURES_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.BINANCE_FUTURES_BTC_4HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> BINANCE_FUTURES_DAY_RANKING(HashMap<String, String> map) throws Exception{
		return MainMapper.BINANCE_FUTURES_DAY_RANKING(map);
	}
	
	public List<HashMap<String, String>> BYBIT_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.BYBIT_BTC_HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> BYBIT_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception{
		return MainMapper.BYBIT_BTC_4HOUR_Ticker_Comparison(map);
	}
	
	public List<HashMap<String, String>> BYBIT_DAY_RANKING(HashMap<String, String> map) throws Exception{
		return MainMapper.BYBIT_DAY_RANKING(map);
	}
	
	public List<HashMap<String, String>> ReportList() throws Exception{
		return MainMapper.ReportList();
	}
	
	public List<HashMap<String, String>> ReportHTMLList(HashMap<String, String> map) throws Exception{
		return MainMapper.ReportHTMLList(map);
	}
	
	public List<HashMap<String, String>> UpbitCoinList() throws Exception{
		return MainMapper.UpbitCoinList();
	}
}
