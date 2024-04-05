package com.diff._private.Mapper.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BinanceCoinMapper {
	public List<HashMap<String, String>> MainGridList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> MainLiveRankList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> CoinInfo() throws Exception;
	public void BlogUploadLogDelete(HashMap<String, String> map) throws Exception;
	public void BlogUploadLogCreate(HashMap<String, String> map) throws Exception;
	public void BlogUploadLogUpdate(HashMap<String, String> map) throws Exception;
	public void CoinAnalysisDelete(HashMap<String, String> map) throws Exception;
	public void CoinAnalysisCreate(HashMap<String, String> map) throws Exception;
	public void CoinAnalysisHourGraphDelete(HashMap<String, String> map) throws Exception;
	public void CoinAnalysisHourGraphCreate(HashMap<String, String> map) throws Exception;
	public void CoinAnalysis4HourGraphDelete(HashMap<String, String> map) throws Exception;
	public void CoinAnalysis4HourGraphCreate(HashMap<String, String> map) throws Exception;
	public void WordPressReportHTMLDelete(HashMap<String, String> map) throws Exception;
	public void WordPressReportHTMLCreate(HashMap<String, String> map) throws Exception;
	public void TiStoryReportHTMLDelete(HashMap<String, String> map) throws Exception;
	public void TiStoryReportHTMLCreate(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> CoinDailyReportList(HashMap<String, String> map) throws Exception;
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
