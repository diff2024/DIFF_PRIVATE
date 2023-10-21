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
import com.diff._private.Mapper.MainMapper;

@Service
@Transactional
public class MainService {
	
	@Autowired
	MainMapper MainMapper;
	
	public void UpbitCoinMIN(HashMap<String, String> map) throws Exception{
		MainMapper.UpbitCoinMIN(map);
	}
}
