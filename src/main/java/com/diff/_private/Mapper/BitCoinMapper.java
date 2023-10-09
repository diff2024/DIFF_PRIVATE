package com.diff._private.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BitCoinMapper {
	public List<HashMap<String, String>> MainGridList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> CoinInfo() throws Exception;
	public String ExcelMake_Daily_Result(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Daily(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Daily_Sub(HashMap<String, String> map) throws Exception;
	public String ExcelMake_Weekly_Result(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Weekly(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ExcelMake_Weekly_Sub(HashMap<String, String> map) throws Exception;
}
