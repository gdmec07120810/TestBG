package com.data;

import java.math.BigDecimal;

import android.util.Log;

public class ElementFunction {

	// 自增长，通用法 储量 = 原来储量+自增量*速率
	public static void Auto(Element e) {
		e.value = e.value.add(e.speed.multiply(e.rate));
	}

	public static void Cost(Element c, Element e, String cost) {
		if (e.value.compareTo(e.c_speed) == -1) {// 判断被消耗的元素少于消耗量
			e.c_speed = new BigDecimal("0");
			return;
		}
		if (c.value.compareTo(new BigDecimal("1")) == -1) {// 判断主要元素少于1，不触发自消耗
			e.c_speed = new BigDecimal("0");
			return;
		}
		e.c_speed = c.value.multiply(new BigDecimal(cost));
		e.value = e.value.subtract(e.c_speed.multiply(e.c_rate));
	}

	// 合成消耗1项
	public static void Mix(Element c, Element e) {
		if (c.value.compareTo(e.cost) == -1) {// 判断a少于b
			return;
		}
		c.value = c.value.subtract(e.cost);
		e.value = e.value.add(e.reward);
	}

	// 合成消耗2项
	public static void Mix(Element c, Element c1, Element e) {
		if (c.value.compareTo(e.cost) == -1) {// 判断a少于b
			return;
		}
		if (c1.value.compareTo(e.cost1) == -1) {// 判断a少于b
			return;
		}
		c.value = c.value.subtract(e.cost);
		c1.value = c1.value.subtract(e.cost1);
		e.value = e.value.add(e.reward);
	}

	// 合成消耗3项
	public static void Mix(Element c, Element c1, Element c2, Element e) {
		if (c.value.compareTo(e.cost) == -1) {// 判断a少于b
			return;
		}
		if (c1.value.compareTo(e.cost1) == -1) {// 判断a少于b
			return;
		}
		if (c2.value.compareTo(e.cost2) == -1) {// 判断a少于b
			return;
		}
		c.value = c.value.subtract(e.cost);
		c1.value = c1.value.subtract(e.cost1);
		c2.value = c2.value.subtract(e.cost2);
		e.value = e.value.add(e.reward);
	}

	// 合成消耗4项
	public static void Mix(Element c, Element c1, Element c2, Element c3,
			Element e) {
		if (c.value.compareTo(e.cost) == -1) {// 判断a少于b
			return;
		}
		if (c1.value.compareTo(e.cost1) == -1) {// 判断a少于b
			return;
		}
		if (c2.value.compareTo(e.cost2) == -1) {// 判断a少于b
			return;
		}
		if (c3.value.compareTo(e.cost3) == -1) {// 判断a少于b
			return;
		}
		c.value = c.value.subtract(e.cost);
		c1.value = c1.value.subtract(e.cost1);
		c2.value = c2.value.subtract(e.cost2);
		c3.value = c3.value.subtract(e.cost3);
		e.value = e.value.add(e.reward);
	}

}
