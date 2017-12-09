package com.data;

import java.math.BigDecimal;

/**
 * ��Ϸ����Ԫ��
 * */
// Ԫ�� - ��ˮRain ����Fire��-ľ�� -ʯ�ģ�
// ֲ�� - ����Seed��-��ˮ�� ����Sapling��+��ʵFruit������Tree��+ľ��Wood��
// ���� - ����Fish��-���ӣ� Ѽ��Duck��-���磩 Գ��Monkey��-Ѽ�ӣ� ����Human��-Գ�ࣩ
// �����ʵFruit ľ��Wood ʯ��Stone ����Money

public class Element {

	public BigDecimal value;// ��ǰ����
	public BigDecimal speed;// �������ٶ�
	public BigDecimal rate;// ����������
	public BigDecimal c_speed = new BigDecimal("0");// �������ٶ�
	public BigDecimal c_rate = new BigDecimal("1");// ����������
	public BigDecimal cost;// �ϳ�����1
	public BigDecimal cost1;// �ϳ�����2 
	public BigDecimal cost2;// �ϳ�����3
	public BigDecimal cost3;// �ϳ�����4
	public BigDecimal reward;// �ϳɲ���

	public Element() {

	}

	// �����ĺϳ�
	public Element(String v, String s, String r, String re) {
		this.value = new BigDecimal(v);
		this.speed = new BigDecimal(s);
		this.rate = new BigDecimal(r);
		this.reward = new BigDecimal(re);
	}

	// �ϳ�����1��
	public Element(String v, String s, String r, String c, String re) {
		this.value = new BigDecimal(v);
		this.speed = new BigDecimal(s);
		this.rate = new BigDecimal(r);
		this.cost = new BigDecimal(c);
		this.reward = new BigDecimal(re);
	}

	// �ϳ�����2��
	public Element(String v, String s, String r, String c, String c1, String re) {
		this.value = new BigDecimal(v);
		this.speed = new BigDecimal(s);
		this.rate = new BigDecimal(r);
		this.cost = new BigDecimal(c);
		this.cost1 = new BigDecimal(c1);
		this.reward = new BigDecimal(re);
	}

	// �ϳ�����3��
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

	// �ϳ�����4��
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
