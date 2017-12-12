package com.data;

import java.math.BigDecimal;

import android.util.Log;

public class ElementFunction {

	// 自增长，通用法 储量 = 原来储量+自增量*速率
	public static void Auto(Element e) {
		BigDecimal s1 = e.speed.multiply(e.rate);
		BigDecimal s2 = e.c_speed.multiply(e.c_rate);
		e.value = e.value.add(s1.subtract(s2));
	}

	// public static void Cost(Element c, Element e, double cost) {
	// if (e.value.compareTo(e.c_speed) == -1) {// 判断被消耗的元素少于消耗量
	// e.c_speed = BigDecimal.valueOf(0);
	// return;
	// }
	// if (c.value.compareTo(BigDecimal.valueOf(1)) == -1) {// 判断主要元素少于1，不触发自消耗
	// e.c_speed = BigDecimal.valueOf(0);
	// return;
	// }
	// e.c_speed = c.value.multiply(BigDecimal.valueOf(cost));
	// e.value = e.value.subtract(e.c_speed.multiply(e.c_rate));
	// }

	// 合成消耗0~4项
	public static boolean Mix(Element c[], Element e) {
		if (c.length < 1 || c == null) {
			e.value = e.value.add(e.reward);// 无合成消耗
			return true;
		}
		for (int i = 0; i < c.length; i++) {
			if (c[i].value.compareTo(e.cost[i]) == -1) {// 判断a少于b
				Log.e("Tips", "资源不足，无法合成");
				return false;// 其中一个条件不符合，跳出程序
			}
		}
		Log.e("???", "够资源了么");
		// 所有条件符合，继续往下
		for (int k = 0; k < c.length; k++) {
			c[k].value = c[k].value.subtract(e.cost[k]);
			Log.e("!!!", "我被消耗了");
		}
		e.value = e.value.add(e.reward);
		return true;
	}

}
