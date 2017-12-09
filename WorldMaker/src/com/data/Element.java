package com.data;

import java.math.BigDecimal;

/**
 * 游戏基本元素
 * */
// 元素 - 雨水Rain 火苗Fire（-木材 -石材）
// 植物 - 种子Seed（-雨水） 树苗Sapling（+果实Fruit）大树Tree（+木材Wood）
// 生物 - 鱼苗Fish（-种子） 鸭子Duck（-鱼苗） 猿类Monkey（-鸭子） 人类Human（-猿类）
// 产物：果实Fruit 木材Wood 石材Stone 货币Money

public class Element {

	public BigDecimal value;// 当前储量
	public BigDecimal speed;// 自增长速度
	public BigDecimal rate;// 自增长速率
	public BigDecimal c_speed = new BigDecimal("0");// 自消耗速度
	public BigDecimal c_rate = new BigDecimal("1");// 自消耗速率
	public BigDecimal cost;// 合成消耗1
	public BigDecimal cost1;// 合成消耗2 
	public BigDecimal cost2;// 合成消耗3
	public BigDecimal cost3;// 合成消耗4
	public BigDecimal reward;// 合成产量

	public Element() {

	}

	// 无消耗合成
	public Element(String v, String s, String r, String re) {
		this.value = new BigDecimal(v);
		this.speed = new BigDecimal(s);
		this.rate = new BigDecimal(r);
		this.reward = new BigDecimal(re);
	}

	// 合成消耗1项
	public Element(String v, String s, String r, String c, String re) {
		this.value = new BigDecimal(v);
		this.speed = new BigDecimal(s);
		this.rate = new BigDecimal(r);
		this.cost = new BigDecimal(c);
		this.reward = new BigDecimal(re);
	}

	// 合成消耗2项
	public Element(String v, String s, String r, String c, String c1, String re) {
		this.value = new BigDecimal(v);
		this.speed = new BigDecimal(s);
		this.rate = new BigDecimal(r);
		this.cost = new BigDecimal(c);
		this.cost1 = new BigDecimal(c1);
		this.reward = new BigDecimal(re);
	}

	// 合成消耗3项
	public Element(String v, String s, String r, String c, String c1,
			String c2, String re) {
		this.value = new BigDecimal(v);
		this.speed = new BigDecimal(s);
		this.rate = new BigDecimal(r);
		this.cost = new BigDecimal(c);
		this.cost1 = new BigDecimal(c1);
		this.cost2 = new BigDecimal(c2);
		this.reward = new BigDecimal(re);
	}

	// 合成消耗4项
	public Element(String v, String s, String r, String c, String c1,
			String c2, String c3, String re) {
		this.value = new BigDecimal(v);
		this.speed = new BigDecimal(s);
		this.rate = new BigDecimal(r);
		this.cost = new BigDecimal(c);
		this.cost1 = new BigDecimal(c1);
		this.cost2 = new BigDecimal(c2);
		this.cost3 = new BigDecimal(c3);
		this.reward = new BigDecimal(re);
	}
}
