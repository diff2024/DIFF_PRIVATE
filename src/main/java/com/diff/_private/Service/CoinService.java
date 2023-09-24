package com.diff._private.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diff._private.Mapper.CoinMapper;

@Service
@Transactional
public class CoinService {
	
	@Autowired
	CoinMapper CoinMapper;
	
	public List<HashMap<String, String>> MainGridList(HashMap<String, String> map) throws Exception{
		List<HashMap<String, String>> list = CoinMapper.MainGridList(map);
		return list;
	}
	
	public List<HashMap<String, String>> CoinInfo() throws Exception{
		return CoinMapper.CoinInfo();
	}
}
