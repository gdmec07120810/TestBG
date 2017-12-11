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
	public BigDecimal c_speed = BigDecimal.valueOf(0);// �������ٶ�
	public BigDecimal c_rate = BigDecimal.valueOf(1);// ����������
	public BigDecimal cost;// �ϳ�����1
	public BigDecimal cost1;// �ϳ�����2
	public BigDecimal cost2;// �ϳ�����3
	public BigDecimal cost3;// �ϳ�����4
	public BigDecimal reward;// �ϳɲ���

	public Element() {

	}

	// �����ĺϳ�
	public Element(double v, double s, double r, double re) {
		this.value = BigDecimal.valueOf(v);
		this.speed = BigDecimal.valueOf(s);
		this.rate = BigDecimal.valueOf(r);
		this.reward = BigDecimal.valueOf(re);
	}

	// �ϳ�����1��
	public Element(double v, double s, double r, double c, double re) {
		this.value = BigDecimal.valueOf(v);
		this.speed = BigDecimal.valueOf(s);
		this.rate = BigDecimal.valueOf(r);
		this.cost = BigDecimal.valueOf(c);
		this.reward = BigDecimal.valueOf(re);
	}

	// �ϳ�����2��
	public Element(double v, double s, double r, double c, double c1, double re) {
		this.value = BigDecimal.valueOf(v);
		this.speed = BigDecimal.valueOf(s);
		this.rate = BigDecimal.valueOf(r);
		this.cost = BigDecimal.valueOf(c);
		this.cost1 = BigDecimal.valueOf(c1);
		this.reward = BigDecimal.valueOf(re);
	}

	// �ϳ�����3��
	public Element(double v, double s, double r, double c, double c1,
			double c2, double re) {
		this.value = BigDecimal.valueOf(v);
		this.speed = BigDecimal.valueOf(s);
		this.rate = BigDecimal.valueOf(r);
		this.cost = BigDecimal.valueOf(c);
		this.cost1 = BigDecimal.valueOf(c1);
		this.cost2 = BigDecimal.valueOf(c2);
		this.reward = BigDecimal.valueOf(re);
	}

	// �ϳ�����4��
	public Element(double v, double s, double r, double c, double c1,
			double c2, double c3, double re) {
		this.value = BigDecimal.valueOf(v);
		this.speed = BigDecimal.valueOf(s);
		this.rate = BigDecimal.valueOf(r);
		this.cost = BigDecimal.valueOf(c);
		this.cost1 = BigDecimal.valueOf(c1);
		this.cost2 = BigDecimal.valueOf(c2);
		this.cost3 = BigDecimal.valueOf(c3);
		this.reward = BigDecimal.valueOf(re);
	}
}
