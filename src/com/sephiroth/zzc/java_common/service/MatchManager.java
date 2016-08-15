package com.sephiroth.zzc.java_common.service;

import com.sephiroth.zzc.java_common.entity.Match;
import com.sephiroth.zzc.java_common.util.Util;

public class MatchManager {
	// 返奖率
	private float RETURN_RATE = 0.91f;
	public Match genMatch() {

		// 取值算法1
		// float rate_1 = (float) Math.random();
		// float rate_2 = (float) Math.random()*(1-rate_1);
		// float rate_3 = 1-rate_1-rate_2;

		// 取值算法2

		// 算法限定的最大赔率
		float max_odds = 10.0f;
		float min_rate = RETURN_RATE / max_odds;
		float rate_1 = 0.0f;
		float rate_2 = 0.0f;
		float rate_3 = 0.0f;
		while (true) {
			rate_1 = (float) Math.random();
			rate_2 = (float) Math.random();
			if (rate_1 + rate_2 < 1) {
				rate_3 = 1 - rate_1 - rate_2;
				// 排除可能产生超大赔率的比赛
				if (rate_1 > min_rate && rate_2 > min_rate && rate_3 > min_rate) {
					break;
				}
			}
		}

		Util.pl("=========取值结果=========");

		Util.pl(rate_1);
		Util.pl(rate_2);
		Util.pl(rate_3);
		Util.pl(rate_1);
		Util.pl(rate_2);
		Util.pl(rate_3);

		// float rate_max =
		// ((rate_1>rate_2)&&(rate_1>rate_3))?rate_1:(rate_2>rate_3?rate_2:rate_3);

		// 排序算法1
		// float tmp;
		// if(rate_1>rate_2&&rate_1>rate_3){
		// if(rate_2<rate_3){
		// tmp=rate_2;rate_2=rate_3;rate_3=tmp;
		// }
		// }else{
		// if(rate_2>rate_3){
		// tmp=rate_1;rate_2=rate_1;rate_1=tmp;
		// }else{
		// tmp=rate_1;rate_1=rate_3;rate_3=tmp;
		// }
		// if(rate_2<rate_3){
		// tmp=rate_2;rate_2=rate_3;rate_3=tmp;
		// }
		// }

		// 排序算法2
		float tmp;
		if (rate_1 < rate_2 || rate_1 < rate_3) {
			if (rate_2 > rate_3) {
				tmp = rate_1;
				rate_1 = rate_2;
				rate_2 = tmp;
			} else {
				tmp = rate_1;
				rate_1 = rate_3;
				rate_3 = tmp;
			}
		}

		if (rate_2 < rate_3) {
			tmp = rate_2;
			rate_2 = rate_3;
			rate_3 = tmp;
		}

		Util.pl("=========after range=========");

		Util.pl(rate_1);
		Util.pl(rate_2);
		Util.pl(rate_3);

		float odd_min = RETURN_RATE / rate_1;
		float odd_middle = RETURN_RATE / rate_2;
		float odd_max = RETURN_RATE / rate_3;

		Util.pl("=========odds=========");
		Util.pl(odd_min);
		Util.pl(odd_middle);
		Util.pl(odd_max);

		// 赔率模式 共4种1：123 2：132 3：231 4：321
		float odd_1 = 0.0f;
		float odd_2 = 0.0f;
		float odd_3 = 0.0f;
		
		int mode = (int) (Math.random() * 4);
		switch (mode) {
		case 0:
			odd_1 = odd_min;
			odd_2 = odd_middle;
			odd_3 = odd_max;
			break;
		case 1:
			odd_1 = odd_min;
			odd_2 = odd_max;
			odd_3 = odd_middle;
			break;
		case 2:
			odd_1 = odd_middle;
			odd_2 = odd_max;
			odd_3 = odd_min;
			break;
		case 3:
			odd_1 = odd_max;
			odd_2 = odd_middle;
			odd_3 = odd_min;
			break;
		default:
			break;
		}

		Match m = new Match("home", "guest", odd_1, odd_2, odd_3);
		return m;
	}
	
	public Match genMatchResult(Match m){
		float rate = (float) Math.random();
		
		float rate_win = RETURN_RATE/m.oddWin;
		float rate_plain = RETURN_RATE/m.oddPlain;
		float rate_lose = RETURN_RATE/m.oddLose;
		
		if(rate<rate_win){
			m.setResult(3);
		}else if(rate<rate_win+rate_plain){
			m.setResult(1);
		}else{
			m.setResult(0);
		}
		
		return m;
	}
}
