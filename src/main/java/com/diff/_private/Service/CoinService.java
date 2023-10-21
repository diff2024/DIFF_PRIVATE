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

import com.diff._private.Mapper.CoinMapper;

@Service
@Transactional
public class CoinService {
	
	@Autowired
	CoinMapper CoinMapper;
	
	public List<HashMap<String, String>> MainGridList(HashMap<String, String> map) throws Exception{
		return CoinMapper.MainGridList(map);
	}
	
	public List<HashMap<String, String>> MainLiveRankList(HashMap<String, String> map) throws Exception{
		return CoinMapper.MainLiveRankList(map);
	}
	
	public List<HashMap<String, String>> CoinInfo() throws Exception{
		return CoinMapper.CoinInfo();
	}
	
	public List<HashMap<String, String>> ExcelMake_Daily_Result(HashMap<String, String> map) throws Exception{
		return CoinMapper.ExcelMake_Daily_Result(map);
	}
	
	public List<HashMap<String, String>> ExcelMake_Daily_Ranking(HashMap<String, String> map) throws Exception{
		return CoinMapper.ExcelMake_Daily_Ranking(map);
	}
	
	public HashMap<String, String> ExcelMake_Daily(HashMap<String, String> map) throws Exception{
		DecimalFormat df = new DecimalFormat("#,###.########");
		HashMap<String, String> excel_map = new HashMap<String, String>();
		List<HashMap<String, String>> list = CoinMapper.ExcelMake_Daily(map);
		
		for(int x=0; x<list.size(); x++) {
			String coin_ticker = list.get(x).get("coin_ticker");
			String open_close = df.format(Double.parseDouble(list.get(x).get("open_close_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%]";
			String lowest_highest = df.format(Double.parseDouble(list.get(x).get("lowest_highest_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation"))) + "%]";
			
			if(coin_ticker.equals("KRW-BTC")) {
				excel_map.put("btc_ticker", "비트코인 [BTC]");
				excel_map.put("btc_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("btc_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("btc_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("btc_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("btc_open_close", open_close);
				excel_map.put("btc_lowest_highest", lowest_highest);
				excel_map.put("btc_price_min_time", list.get(x).get("coin_price_min_time"));
				excel_map.put("btc_price_max_time", list.get(x).get("coin_price_max_time"));
				excel_map.put("btc_volume_min_time", list.get(x).get("coin_volume_min_time") + "시");
				excel_map.put("btc_volume_max_time", list.get(x).get("coin_volume_max_time") + "시");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("date", map.get("date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Daily_Sub(sub_map);
				excel_map.put("btc_oclh_1_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_0_4"))));
				excel_map.put("btc_oclh_1_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_0_4"))));
				excel_map.put("btc_oclh_1_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_0_4"))));
				excel_map.put("btc_oclh_1_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_0_4"))));
				excel_map.put("btc_oclh_1_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_0_4"))));
				
				excel_map.put("btc_oclh_2_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_4_8"))));
				excel_map.put("btc_oclh_2_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_4_8"))));
				excel_map.put("btc_oclh_2_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_4_8"))));
				excel_map.put("btc_oclh_2_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_4_8"))));
				excel_map.put("btc_oclh_2_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_4_8"))));
				
				excel_map.put("btc_oclh_3_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_8_12"))));
				excel_map.put("btc_oclh_3_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_8_12"))));
				excel_map.put("btc_oclh_3_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_8_12"))));
				excel_map.put("btc_oclh_3_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_8_12"))));
				excel_map.put("btc_oclh_3_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_8_12"))));
				
				excel_map.put("btc_oclh_4_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_12_16"))));
				excel_map.put("btc_oclh_4_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_12_16"))));
				excel_map.put("btc_oclh_4_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_12_16"))));
				excel_map.put("btc_oclh_4_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_12_16"))));
				excel_map.put("btc_oclh_4_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_12_16"))));
				
				excel_map.put("btc_oclh_5_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_16_20"))));
				excel_map.put("btc_oclh_5_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_16_20"))));
				excel_map.put("btc_oclh_5_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_16_20"))));
				excel_map.put("btc_oclh_5_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_16_20"))));
				excel_map.put("btc_oclh_5_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_16_20"))));
				
				excel_map.put("btc_oclh_6_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_20_24"))));
				excel_map.put("btc_oclh_6_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_20_24"))));
				excel_map.put("btc_oclh_6_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_20_24"))));
				excel_map.put("btc_oclh_6_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_20_24"))));
				excel_map.put("btc_oclh_6_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_20_24"))));
				
				if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 상승하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) < 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 하락하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", "보합이었으며, ");
				}
			} else if(x == 1) {
				excel_map.put("coin1_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin1_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin1_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin1_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin1_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin1_open_close", open_close);
				excel_map.put("coin1_lowest_highest", lowest_highest);
				excel_map.put("coin1_price_min_time", list.get(x).get("coin_price_min_time"));
				excel_map.put("coin1_price_max_time", list.get(x).get("coin_price_max_time"));
				excel_map.put("coin1_volume_min_time", list.get(x).get("coin_volume_min_time") + "시");
				excel_map.put("coin1_volume_max_time", list.get(x).get("coin_volume_max_time") + "시");
				excel_map.put("coin1_summary","1순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("coin_price_min_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("coin_price_max_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("date", map.get("date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Daily_Sub(sub_map);
				excel_map.put("coin1_oclh_1_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_0_4"))));
				excel_map.put("coin1_oclh_1_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_0_4"))));
				excel_map.put("coin1_oclh_1_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_0_4"))));
				excel_map.put("coin1_oclh_1_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_0_4"))));
				excel_map.put("coin1_oclh_1_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_0_4"))));
				
				excel_map.put("coin1_oclh_2_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_4_8"))));
				excel_map.put("coin1_oclh_2_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_4_8"))));
				excel_map.put("coin1_oclh_2_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_4_8"))));
				excel_map.put("coin1_oclh_2_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_4_8"))));
				excel_map.put("coin1_oclh_2_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_4_8"))));
				
				excel_map.put("coin1_oclh_3_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_8_12"))));
				excel_map.put("coin1_oclh_3_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_8_12"))));
				excel_map.put("coin1_oclh_3_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_8_12"))));
				excel_map.put("coin1_oclh_3_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_8_12"))));
				excel_map.put("coin1_oclh_3_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_8_12"))));
				
				excel_map.put("coin1_oclh_4_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_12_16"))));
				excel_map.put("coin1_oclh_4_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_12_16"))));
				excel_map.put("coin1_oclh_4_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_12_16"))));
				excel_map.put("coin1_oclh_4_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_12_16"))));
				excel_map.put("coin1_oclh_4_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_12_16"))));
				
				excel_map.put("coin1_oclh_5_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_16_20"))));
				excel_map.put("coin1_oclh_5_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_16_20"))));
				excel_map.put("coin1_oclh_5_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_16_20"))));
				excel_map.put("coin1_oclh_5_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_16_20"))));
				excel_map.put("coin1_oclh_5_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_16_20"))));
				
				excel_map.put("coin1_oclh_6_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_20_24"))));
				excel_map.put("coin1_oclh_6_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_20_24"))));
				excel_map.put("coin1_oclh_6_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_20_24"))));
				excel_map.put("coin1_oclh_6_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_20_24"))));
				excel_map.put("coin1_oclh_6_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_20_24"))));
			} else if(x == 2) {
				excel_map.put("coin2_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin2_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin2_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin2_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin2_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin2_open_close", open_close);
				excel_map.put("coin2_lowest_highest", lowest_highest);
				excel_map.put("coin2_price_min_time", list.get(x).get("coin_price_min_time"));
				excel_map.put("coin2_price_max_time", list.get(x).get("coin_price_max_time"));
				excel_map.put("coin2_volume_min_time", list.get(x).get("coin_volume_min_time") + "시");
				excel_map.put("coin2_volume_max_time", list.get(x).get("coin_volume_max_time") + "시");
				excel_map.put("coin2_summary","2순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("coin_price_min_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("coin_price_max_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("date", map.get("date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Daily_Sub(sub_map);
				excel_map.put("coin2_oclh_1_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_0_4"))));
				excel_map.put("coin2_oclh_1_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_0_4"))));
				excel_map.put("coin2_oclh_1_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_0_4"))));
				excel_map.put("coin2_oclh_1_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_0_4"))));
				excel_map.put("coin2_oclh_1_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_0_4"))));
				
				excel_map.put("coin2_oclh_2_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_4_8"))));
				excel_map.put("coin2_oclh_2_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_4_8"))));
				excel_map.put("coin2_oclh_2_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_4_8"))));
				excel_map.put("coin2_oclh_2_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_4_8"))));
				excel_map.put("coin2_oclh_2_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_4_8"))));
				
				excel_map.put("coin2_oclh_3_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_8_12"))));
				excel_map.put("coin2_oclh_3_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_8_12"))));
				excel_map.put("coin2_oclh_3_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_8_12"))));
				excel_map.put("coin2_oclh_3_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_8_12"))));
				excel_map.put("coin2_oclh_3_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_8_12"))));
				
				excel_map.put("coin2_oclh_4_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_12_16"))));
				excel_map.put("coin2_oclh_4_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_12_16"))));
				excel_map.put("coin2_oclh_4_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_12_16"))));
				excel_map.put("coin2_oclh_4_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_12_16"))));
				excel_map.put("coin2_oclh_4_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_12_16"))));
				
				excel_map.put("coin2_oclh_5_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_16_20"))));
				excel_map.put("coin2_oclh_5_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_16_20"))));
				excel_map.put("coin2_oclh_5_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_16_20"))));
				excel_map.put("coin2_oclh_5_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_16_20"))));
				excel_map.put("coin2_oclh_5_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_16_20"))));
				
				excel_map.put("coin2_oclh_6_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_20_24"))));
				excel_map.put("coin2_oclh_6_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_20_24"))));
				excel_map.put("coin2_oclh_6_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_20_24"))));
				excel_map.put("coin2_oclh_6_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_20_24"))));
				excel_map.put("coin2_oclh_6_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_20_24"))));
			} else if(x == 3) {
				excel_map.put("coin3_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin3_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin3_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin3_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin3_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin3_open_close", open_close);
				excel_map.put("coin3_lowest_highest", lowest_highest);
				excel_map.put("coin3_price_min_time", list.get(x).get("coin_price_min_time"));
				excel_map.put("coin3_price_max_time", list.get(x).get("coin_price_max_time"));
				excel_map.put("coin3_volume_min_time", list.get(x).get("coin_volume_min_time") + "시");
				excel_map.put("coin3_volume_max_time", list.get(x).get("coin_volume_max_time") + "시");
				excel_map.put("coin3_summary","3순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("coin_price_min_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("coin_price_max_time")).replace(":", "시 ")+"분에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("date", map.get("date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Daily_Sub(sub_map);
				excel_map.put("coin3_oclh_1_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_0_4"))));
				excel_map.put("coin3_oclh_1_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_0_4"))));
				excel_map.put("coin3_oclh_1_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_0_4"))));
				excel_map.put("coin3_oclh_1_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_0_4"))));
				excel_map.put("coin3_oclh_1_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_0_4"))));
				
				excel_map.put("coin3_oclh_2_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_4_8"))));
				excel_map.put("coin3_oclh_2_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_4_8"))));
				excel_map.put("coin3_oclh_2_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_4_8"))));
				excel_map.put("coin3_oclh_2_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_4_8"))));
				excel_map.put("coin3_oclh_2_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_4_8"))));
				
				excel_map.put("coin3_oclh_3_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_8_12"))));
				excel_map.put("coin3_oclh_3_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_8_12"))));
				excel_map.put("coin3_oclh_3_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_8_12"))));
				excel_map.put("coin3_oclh_3_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_8_12"))));
				excel_map.put("coin3_oclh_3_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_8_12"))));
				
				excel_map.put("coin3_oclh_4_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_12_16"))));
				excel_map.put("coin3_oclh_4_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_12_16"))));
				excel_map.put("coin3_oclh_4_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_12_16"))));
				excel_map.put("coin3_oclh_4_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_12_16"))));
				excel_map.put("coin3_oclh_4_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_12_16"))));
				
				excel_map.put("coin3_oclh_5_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_16_20"))));
				excel_map.put("coin3_oclh_5_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_16_20"))));
				excel_map.put("coin3_oclh_5_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_16_20"))));
				excel_map.put("coin3_oclh_5_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_16_20"))));
				excel_map.put("coin3_oclh_5_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_16_20"))));
				
				excel_map.put("coin3_oclh_6_o_price", df.format(Double.parseDouble(list_sub.get(0).get("o_price_20_24"))));
				excel_map.put("coin3_oclh_6_c_price", df.format(Double.parseDouble(list_sub.get(0).get("c_price_20_24"))));
				excel_map.put("coin3_oclh_6_l_price", df.format(Double.parseDouble(list_sub.get(0).get("l_price_20_24"))));
				excel_map.put("coin3_oclh_6_h_price", df.format(Double.parseDouble(list_sub.get(0).get("h_price_20_24"))));
				excel_map.put("coin3_oclh_6_trade_volume", df.format(Double.parseDouble(list_sub.get(0).get("trade_volume_20_24"))));
			}
		}
		
		
		return excel_map;
	}
	
	public List<HashMap<String, String>> ExcelMake_Weekly_Result(HashMap<String, String> map) throws Exception{
		return CoinMapper.ExcelMake_Weekly_Result(map);
	}
	
	public List<HashMap<String, String>> ExcelMake_Weekly_Ranking(HashMap<String, String> map) throws Exception{
		return CoinMapper.ExcelMake_Weekly_Ranking(map);
	}
	
	public HashMap<String, String> ExcelMake_Weekly(HashMap<String, String> map) throws Exception{
		DecimalFormat df = new DecimalFormat("#,###.########");
		HashMap<String, String> excel_map = new HashMap<String, String>();
		List<HashMap<String, String>> list = CoinMapper.ExcelMake_Weekly(map);
		for(int x=0; x<list.size(); x++) {
			String coin_ticker = list.get(x).get("coin_ticker");
			
			String open_close = df.format(Double.parseDouble(list.get(x).get("open_close_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%]";
			String lowest_highest = df.format(Double.parseDouble(list.get(x).get("lowest_highest_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation"))) + "%]";
			
			if(coin_ticker.equals("KRW-BTC")) {
				excel_map.put("btc_ticker", "비트코인 [BTC]");
				excel_map.put("btc_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("btc_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("btc_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("btc_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("btc_open_close", open_close);
				excel_map.put("btc_lowest_highest", lowest_highest);
				excel_map.put("btc_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("btc_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("btc_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("btc_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("btc_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("btc_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Weekly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
				
				if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 상승하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) < 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 하락하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", "보합이었으며, ");
				}
			}else if(x == 1) {
				excel_map.put("coin1_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin1_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin1_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin1_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin1_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin1_open_close", open_close);
				excel_map.put("coin1_lowest_highest", lowest_highest);
				excel_map.put("coin1_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin1_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin1_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin1_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin1_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin1_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin1_summary", "1순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Weekly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}else if(x == 2) {
				excel_map.put("coin2_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin2_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin2_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin2_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin2_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin2_open_close", open_close);
				excel_map.put("coin2_lowest_highest", lowest_highest);
				excel_map.put("coin2_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin2_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin2_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin2_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin2_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin2_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin2_summary", "2순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");

				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Weekly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}else if(x == 3) {
				excel_map.put("coin3_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin3_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin3_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin3_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin3_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin3_open_close", open_close);
				excel_map.put("coin3_lowest_highest", lowest_highest);
				excel_map.put("coin3_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin3_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin3_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin3_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin3_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin3_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin3_summary", "3순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");

				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Weekly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}
		}
		return excel_map;
	}
	
	public HashMap<String, String> ExcelMake_Monthly(HashMap<String, String> map) throws Exception{
		DecimalFormat df = new DecimalFormat("#,###.########");
		HashMap<String, String> excel_map = new HashMap<String, String>();
		List<HashMap<String, String>> list = CoinMapper.ExcelMake_Weekly(map);
		for(int x=0; x<list.size(); x++) {
			String coin_ticker = list.get(x).get("coin_ticker");
			
			String open_close = df.format(Double.parseDouble(list.get(x).get("open_close_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%]";
			String lowest_highest = df.format(Double.parseDouble(list.get(x).get("lowest_highest_calc"))) + "원 [" + df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation"))) + "%]";
			
			if(coin_ticker.equals("KRW-BTC")) {
				excel_map.put("btc_ticker", "비트코인 [BTC]");
				excel_map.put("btc_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("btc_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("btc_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("btc_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("btc_open_close", open_close);
				excel_map.put("btc_lowest_highest", lowest_highest);
				excel_map.put("btc_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("btc_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("btc_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("btc_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("btc_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("btc_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Monthly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("btc_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
				
				if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 상승하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) < 0) {
					excel_map.put("summary_1_1", df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "% 하락하였으며, ");
				} else if(Double.parseDouble(list.get(x).get("open_close_calc")) > 0) {
					excel_map.put("summary_1_1", "보합이었으며, ");
				}
			}else if(x == 1) {
				excel_map.put("coin1_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin1_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin1_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin1_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin1_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin1_open_close", open_close);
				excel_map.put("coin1_lowest_highest", lowest_highest);
				excel_map.put("coin1_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin1_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin1_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin1_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin1_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin1_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin1_summary", "1순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");
				
				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Monthly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin1_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}else if(x == 2) {
				excel_map.put("coin2_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin2_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin2_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin2_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin2_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin2_open_close", open_close);
				excel_map.put("coin2_lowest_highest", lowest_highest);
				excel_map.put("coin2_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin2_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin2_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin2_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin2_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin2_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin2_summary", "2순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");

				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Monthly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin2_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}else if(x == 3) {
				excel_map.put("coin3_ticker", list.get(x).get("coin_kor_name") + " ["+ (list.get(x).get("coin_ticker")).replace("KRW-","") +"]");
				excel_map.put("coin3_o_price", df.format(Double.parseDouble(list.get(x).get("o_price"))));
				excel_map.put("coin3_c_price", df.format(Double.parseDouble(list.get(x).get("c_price"))));
				excel_map.put("coin3_l_price", df.format(Double.parseDouble(list.get(x).get("l_price"))));
				excel_map.put("coin3_h_price", df.format(Double.parseDouble(list.get(x).get("h_price"))));
				excel_map.put("coin3_open_close", open_close);
				excel_map.put("coin3_lowest_highest", lowest_highest);
				excel_map.put("coin3_5_rate_count", list.get(x).get("o_c_price_rate_5_count")+"번");
				excel_map.put("coin3_10_rate_count", list.get(x).get("o_c_price_rate_10_count")+"번");
				excel_map.put("coin3_15_rate_count", list.get(x).get("o_c_price_rate_15_count")+"번");
				excel_map.put("coin3_20_rate_count", list.get(x).get("o_c_price_rate_20_count")+"번");
				excel_map.put("coin3_25_rate_count", list.get(x).get("o_c_price_rate_25_count")+"번");
				excel_map.put("coin3_30_rate_count", list.get(x).get("o_c_price_rate_30_count")+"번");
				excel_map.put("coin3_summary", "3순위는 "+list.get(x).get("coin_kor_name")+"로, 시가 대비 종가가 " + df.format(Double.parseDouble(list.get(x).get("open_close_rate"))) + "%, 저가 대비 고가가 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%이며, 가장 좋은 타이밍은 "+(list.get(x).get("min_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("l_price")))+"원으로 매수하여 "+(list.get(x).get("max_date")).replace("-", "년 ").replace("-", "월 ")+"일에 "+df.format(Double.parseDouble(list.get(x).get("h_price")))+"원으로 매도하면 대략 "+df.format(Double.parseDouble(list.get(x).get("lowest_highest_fluctuation")))+"%의 수익을 얻을 수 있었습니다.");

				HashMap<String, String> sub_map = new HashMap<String, String>();
				sub_map.put("coin_ticker", coin_ticker);
				sub_map.put("std_date", map.get("std_date"));
				sub_map.put("end_date", map.get("end_date"));
				
				List<HashMap<String, String>> list_sub = CoinMapper.ExcelMake_Monthly_Sub(sub_map);
				for(int y=0; y<list_sub.size(); y++) {
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_date", list_sub.get(y).get("datetime_kst"));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_o_price", df.format(Double.parseDouble(list_sub.get(y).get("o_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_c_price", df.format(Double.parseDouble(list_sub.get(y).get("c_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_l_price", df.format(Double.parseDouble(list_sub.get(y).get("l_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_h_price", df.format(Double.parseDouble(list_sub.get(y).get("h_price"))));
					excel_map.put("coin3_oclh_"+Integer.toString(y+1)+"_o_c_price_rate", df.format(Double.parseDouble(list_sub.get(y).get("o_c_price_rate"))) + "%");
				}
			}
		}
		return excel_map;
	}
}
