package com.diff._private.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
	public void UpbitCoinMIN(HashMap<String, String> map) throws Exception;
	public HashMap<String, String> CoinReportDailySetting() throws Exception;
}
