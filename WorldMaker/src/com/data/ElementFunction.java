package com.data;

import java.math.BigDecimal;

import android.util.Log;

public class ElementFunction {

	// ��������ͨ�÷� ���� = ԭ������+������*����
	public static void Auto(Element e) {
		BigDecimal s1 = e.speed.multiply(e.rate);
		BigDecimal s2 = e.c_speed.multiply(e.c_rate);
		e.value = e.value.add(s1.subtract(s2));
	}

	// public static void Cost(Element c, Element e, double cost) {
	// if (e.value.compareTo(e.c_speed) == -1) {// �жϱ����ĵ�Ԫ������������
	// e.c_speed = BigDecimal.valueOf(0);
	// return;
	// }
	// if (c.value.compareTo(BigDecimal.valueOf(1)) == -1) {// �ж���ҪԪ������1��������������
	// e.c_speed = BigDecimal.valueOf(0);
	// return;
	// }
	// e.c_speed = c.value.multiply(BigDecimal.valueOf(cost));
	// e.value = e.value.subtract(e.c_speed.multiply(e.c_rate));
	// }

	// �ϳ�����0~4��
	public static boolean Mix(Element c[], Element e) {
		if (c.length < 1 || c == null) {
			e.value = e.value.add(e.reward);// �޺ϳ�����
			return true;
		}
		for (int i = 0; i < c.length; i++) {
			if (c[i].value.compareTo(e.cost[i]) == -1) {// �ж�a����b
				Log.e("Tips", "��Դ���㣬�޷��ϳ�");
				return false;// ����һ�����������ϣ���������
			}
		}
		Log.e("???", "����Դ��ô");
		// �����������ϣ���������
		for (int k = 0; k < c.length; k++) {
			c[k].value = c[k].value.subtract(e.cost[k]);
			Log.e("!!!", "�ұ�������");
		}
		e.value = e.value.add(e.reward);
		return true;
	}

}
