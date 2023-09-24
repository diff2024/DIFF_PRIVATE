package com.diff._private.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoinMapper {
	public List<HashMap<String, String>> MainGridList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> CoinInfo() throws Exception;
}
