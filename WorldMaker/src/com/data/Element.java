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
	public BigDecimal c_speed = BigDecimal.valueOf(0);// 自消耗速度
	public BigDecimal c_rate = BigDecimal.valueOf(1);// 自消耗速率
	public BigDecimal cost[] = new BigDecimal[5];// 合成消耗(0到4个)
	public BigDecimal reward;// 合成产量
	public boolean isOpen;// 功能激活开关

	public Element() {

	}

	// 无消耗合成
	public Element(double v, double s, double r, double re) {
		this.value = BigDecimal.valueOf(v);
		this.speed = BigDecimal.valueOf(s);
		this.rate = BigDecimal.valueOf(r);
		this.reward = BigDecimal.valueOf(re);
	}

}
