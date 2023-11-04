package com.diff._private.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BitCoinMapper {
	public List<HashMap<String, String>> MainGridList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> MainLiveRankList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> CoinInfo() throws Exception;
	public void CoinDailyReportDelete(HashMap<String, String> map) throws Exception;
	public void CoinDailyReportReg(HashMap<String, String> map) throws Exception;
	public void CoinDailyReportHTMLDelete(HashMap<String, String> map) throws Exception;
	public void CoinDailyReportHTMLReg(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> CoinReportList() throws Exception;
	public List<HashMap<String, String>> CoinDailyReportList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> CoinDailyReportHTMLList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Daily_Result(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Daily_Ranking(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Daily(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Daily_Sub(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Weekly_Result(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Weekly_Ranking(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Weekly(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Weekly_Sub(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Monthly_Sub(HashMap<String, String> map) throws Exception;
}
