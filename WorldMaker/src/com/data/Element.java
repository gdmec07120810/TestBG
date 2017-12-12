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
	public BigDecimal cost[] = new BigDecimal[5];// �ϳ�����(0��4��)
	public BigDecimal reward;// �ϳɲ���
	public boolean isOpen;// ���ܼ����

	public Element() {

	}

	// �����ĺϳ�
	public Element(double v, double s, double r, double re) {
		this.value = BigDecimal.valueOf(v);
		this.speed = BigDecimal.valueOf(s);
		this.rate = BigDecimal.valueOf(r);
		this.reward = BigDecimal.valueOf(re);
	}

}
