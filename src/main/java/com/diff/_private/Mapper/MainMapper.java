package com.diff._private.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
	public void UpbitCoinMIN(HashMap<String, String> map) throws Exception;
	public HashMap<String, String> CoinAnalysisSetting() throws Exception;
	public List<HashMap<String, String>> UPBIT_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> UPBIT_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> UPBIT_DAY_RANKING(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BITHUMB_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BITHUMB_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BITHUMB_DAY_RANKING(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BINANCE_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BINANCE_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BINANCE_DAY_RANKING(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BINANCE_FUTURES_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BINANCE_FUTURES_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BINANCE_FUTURES_DAY_RANKING(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ReportList() throws Exception;
	public List<HashMap<String, String>> ReportHTMLList(HashMap<String, String> map) throws Exception;
}
