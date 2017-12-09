package com.data;

import java.math.BigDecimal;

import android.util.Log;

public class ElementFunction {

	// ��������ͨ�÷� ���� = ԭ������+������*����
	public static void Auto(Element e) {
		e.value = e.value.add(e.speed.multiply(e.rate));
	}

	public static void Cost(Element c, Element e, String cost) {
		if (e.value.compareTo(e.c_speed) == -1) {// �жϱ����ĵ�Ԫ������������
			e.c_speed = new BigDecimal("0");
			return;
		}
		if (c.value.compareTo(new BigDecimal("1")) == -1) {// �ж���ҪԪ������1��������������
			e.c_speed = new BigDecimal("0");
			return;
		}
		e.c_speed = c.value.multiply(new BigDecimal(cost));
		e.value = e.value.subtract(e.c_speed.multiply(e.c_rate));
	}

	// �ϳ�����1��
	public static void Mix(Element c, Element e) {
		if (c.value.compareTo(e.cost) == -1) {// �ж�a����b
			return;
		}
		c.value = c.value.subtract(e.cost);
		e.value = e.value.add(e.reward);
	}

	// �ϳ�����2��
	public static void Mix(Element c, Element c1, Element e) {
		if (c.value.compareTo(e.cost) == -1) {// �ж�a����b
			return;
		}
		if (c1.value.compareTo(e.cost1) == -1) {// �ж�a����b
			return;
		}
		c.value = c.value.subtract(e.cost);
		c1.value = c1.value.subtract(e.cost1);
		e.value = e.value.add(e.reward);
	}

	// �ϳ�����3��
	public static void Mix(Element c, Element c1, Element c2, Element e) {
		if (c.value.compareTo(e.cost) == -1) {// �ж�a����b
			return;
		}
		if (c1.value.compareTo(e.cost1) == -1) {// �ж�a����b
			return;
		}
		if (c2.value.compareTo(e.cost2) == -1) {// �ж�a����b
			return;
		}
		c.value = c.value.subtract(e.cost);
		c1.value = c1.value.subtract(e.cost1);
		c2.value = c2.value.subtract(e.cost2);
		e.value = e.value.add(e.reward);
	}

	// �ϳ�����4��
	public static void Mix(Element c, Element c1, Element c2, Element c3,
			Element e) {
		if (c.value.compareTo(e.cost) == -1) {// �ж�a����b
			return;
		}
		if (c1.value.compareTo(e.cost1) == -1) {// �ж�a����b
			return;
		}
		if (c2.value.compareTo(e.cost2) == -1) {// �ж�a����b
			return;
		}
		if (c3.value.compareTo(e.cost3) == -1) {// �ж�a����b
			return;
		}
		c.value = c.value.subtract(e.cost);
		c1.value = c1.value.subtract(e.cost1);
		c2.value = c2.value.subtract(e.cost2);
		c3.value = c3.value.subtract(e.cost3);
		e.value = e.value.add(e.reward);
	}

}
