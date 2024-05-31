package com.diff._private.Mapper.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
	public String SelectCodeData(String parameter) throws Exception;
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
	public List<HashMap<String, String>> BYBIT_BTC_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BYBIT_BTC_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BYBIT_DAY_RANKING(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ShortList() throws Exception;
	public List<HashMap<String, String>> VideoList() throws Exception;
	public List<HashMap<String, String>> ReportList() throws Exception;
	public List<HashMap<String, String>> ReportHTMLList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> UpbitCoinList() throws Exception;
	public List<HashMap<String, String>> BithumbCoinList() throws Exception;
	public List<HashMap<String, String>> BithumbCoinList_Script(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BITHUMB_BTC_OPEN_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> UPBIT_BTC_OPEN_HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BITHUMB_BTC_OPEN_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> UPBIT_BTC_OPEN_4HOUR_Ticker_Comparison(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> BINANCE_FUTURES_DAY_ANALYSIS() throws Exception;
}
