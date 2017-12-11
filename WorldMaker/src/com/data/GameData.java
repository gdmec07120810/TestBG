package com.data;

import java.math.BigDecimal;
import java.util.Random;

import android.util.Log;

public class GameData {

	public static Element BACE_ELEMENT[];
	public static Element MAIN_ELEMENT[];
	public static Element BUILD_ELEMENT[];
	public static Element OTHER_ELEMENT[];

	// TODO ������Ӱ����ˮ�����ٶ�
	public static int WEATHER = 0;// 0����1С��2����3����4����
	public static String W[] = { "����", "С��", "����", "����", "����" };
	public static double RainSpeed[] = { 1, 2, 3, 4, 5 };// ����Ӱ����ˮ�ٶ�
	public static double SaplingSpeed[] = { 1, 0.8, 0.6, 0.4, 0.2 };// ����Ӱ����������
	public static int Time = 0;// ������ʱ��60������任һ������

	public static void initElement() {// ��ʼ����ϷԪ��
		WEATHER = new Random().nextInt(4);
		// new Element( ����, ��������, �������, �ϳ����� , �ϳɲ��� )
		// ����
		Rain = new Element(0, 0, 1, 0);// �޷��ϳ�
		Fire = new Element(0, 0.0001, 1, 0, 0, 0);// �ϳ�����ľ����
		Wood = new Element(0, 0.0001, 1, 0);// �޷��ϳ�
		Stone = new Element(0, 0.0001, 1, 0);// �޷��ϳ�
		Fruit = new Element(0, 0.0001, 1, 0);// �޷��ϳ�
		Money = new Element(0, 0.0001, 1, 0);// �޷��ϳ�
		BACE_ELEMENT = new Element[] { Rain, Fire, Wood, Stone, Fruit, Money };

		// ��Ҫ
		Seed = new Element(0, 1, 1, 100, 500);// �ϳ�������ˮ
		Sapling = new Element(0, 0.05, 1, 0, 0);// �ϳ���������
		Tree = new Element(0, 0.025, 1, 0, 0);// �ϳ���������
		Fish = new Element(0, 0.08, 1, 0, 0);// �ϳ���������
		Duck = new Element(0, 0.06, 1, 0, 0);// �ϳ���������
		Monkey = new Element(0, 0.03, 1, 0, 0);// �ϳ�����Ѽ��
		Human = new Element(0, 0.02, 1, 0, 0);// �ϳ�����Գ��
		MAIN_ELEMENT = new Element[] { Seed, Sapling, Tree, Fish, Duck, Monkey,
				Human };
		// ����
		Lumberyard = new Element(0, 0, 1, 0, 0, 0, 1);// �ϳ�����ľ�ġ�ʯ�ġ�����
		Quarry = new Element(0, 0, 1, 0, 0, 0, 1);// �ϳ�����ľ�ġ�ʯ�ġ�����
		House = new Element(0, 0, 1, 0, 0, 0, 1);// �ϳ�����ľ�ġ�ʯ�ġ�����
		Animal = new Element(0, 0, 1, 0, 0, 1);// �ϳ��������ӡ�����
		Farm = new Element(0, 0, 1, 0, 0, 1);// �ϳ��������硢����
		Shop = new Element(0, 0, 1, 0, 0, 0, 0, 1);// �ϳ��������ӡ����硢���硢Ѽ��
		BUILD_ELEMENT = new Element[] { Lumberyard, Quarry, House, Animal,
				Farm, Shop };
		// ����
		Book = new Element(0, 0, 1, 0, 0, 0);// �ϳ�����ľ�ġ�����
		Knowledge = new Element(0, 0, 1, 0);// �޺ϳ�����
		Gods = new Element(0, 0, 1, 0, 0, 0, 0);// �ϳ�������ˮ�����桢֪ʶ
		Reborn = new Element(0, 0, 1, 0, 0);// �ϳ�������ѧ
		Science = new Element(0, 0, 1, 0);// �޺ϳ�����
		OTHER_ELEMENT = new Element[] { Book, Knowledge, Gods, Reborn, Science };
	}

	// TODO Ԫ��������
	public static void ElementAuto() {
		Rain.speed = BigDecimal.valueOf(RainSpeed[WEATHER]);

		Seed.rate = Rain.value.multiply(BigDecimal.valueOf(0.0156852));// ���Ӳ�����0.35/�루����ˮ����
		Sapling.rate = Seed.value.multiply(BigDecimal.valueOf(0.025362));// ���������0.005/�루����������
		Sapling.rate = Sapling.rate.multiply(BigDecimal
				.valueOf(SaplingSpeed[WEATHER]));// ����Ӱ����������
		Fish.rate = Seed.value.multiply(BigDecimal.valueOf(0.02381));// ���������0.015/�루����������
		Tree.rate = Sapling.value.multiply(BigDecimal.valueOf(0.01386));// ����������0.005/�루����������
		Duck.rate = Fish.value.multiply(BigDecimal.valueOf(0.006392));// Ѽ�Ӳ�����0.0006/�루����������
		Monkey.rate = Duck.value.multiply(BigDecimal.valueOf(0.005326));// Գ�������0.0003/�루��Ѽ������
		Human.rate = Monkey.value.multiply(BigDecimal.valueOf(0.0032852));// ���������0.00002/�루��Գ������
		//
		Fruit.rate = Sapling.value.multiply(BigDecimal.valueOf(0.55));// ��ʵ������0.0012/�루����������
		
		Wood.rate = Lumberyard.value.multiply(BigDecimal.valueOf(0.35));// ľ�Ĳ�����0.05/�루����ľ��������
		Stone.rate = Quarry.value.multiply(BigDecimal.valueOf(0.25));// ʯ�Ĳ�����0.03/�루����ʯ��������
		Money.rate = Shop.value.multiply(BigDecimal.valueOf(1.55));// ���Ҳ�����1.15/�루����ҵ������
		Knowledge.rate = Book.value.multiply(BigDecimal.valueOf(0.55));// ֪ʶ������0.25/�루���鼮����
		//

		// Seed.c_speed = Fish.rate.multiply(BigDecimal.valueOf(3.5));
		// Fish.c_speed = Duck.rate.multiply(BigDecimal.valueOf(2.5));
		// Sapling.c_speed = Tree.rate.multiply(BigDecimal.valueOf(1.5));
		// Duck.c_speed = Monkey.rate.multiply(BigDecimal.valueOf(1.05));
		// Monkey.c_speed = Human.rate.multiply(BigDecimal.valueOf(1.0));

		ElementFunction.Auto(Rain);
		// ElementFunction.Auto(Fire);�������ϳ�
		ElementFunction.Auto(Seed);
		ElementFunction.Auto(Sapling);
		ElementFunction.Auto(Tree);
		ElementFunction.Auto(Fish);
		ElementFunction.Auto(Duck);
		ElementFunction.Auto(Monkey);
		ElementFunction.Auto(Human);

		 ElementFunction.Auto(Wood);
		 ElementFunction.Auto(Stone);
		 ElementFunction.Auto(Fruit);
		 ElementFunction.Auto(Money);

	}

	// Ԫ�����ģ��Զ���������Ԫ�أ�
	public static void ElementCost() {
		// ElementFunction.Cost(Fish, Seed, 0.002865); // �������������� = ���� *
		// 0.0065/s
		// ElementFunction.Cost(Duck, Fish, 0.014865); // Ѽ������������ = Ѽ�� *
		// 0.0050/s
		// ElementFunction.Cost(Tree, Sapling, 0.00865); // ������������ = ���� *
		// 0.0050/s
		// ElementFunction.Cost(Monkey, Duck, 0.01865); // Գ��������Ѽ�� = Գ�� *0.025/s
		// ElementFunction.Cost(Human, Monkey, 0.02865); // ����������Գ�� = ���� *0.05/s
	}

	// TODO ������ת��ѧ������
	public static String get_eMode(BigDecimal bd, int i) {
		// BigDecimal bd = element.value;
		String n = bd.setScale(i, BigDecimal.ROUND_HALF_UP).toString();
		String str = bd.toString();
		int c = str.indexOf(".");// ��ȡС����λ�ã��ж�����λ��С
		c = c == -1 ? bd.toString().length() : c;// ��Ŀ���㣬��С������ֱ��ȡ��
		if (c > 5) {
			StringBuffer bf = new StringBuffer();
			bf.append(n.substring(0, 1)).append(".").append(n.substring(1, 3));
			return bf.toString() + "e" + (c - 1);
		} else {
			return n;
		}
	}

	// TODO ����Ԫ��
	public static Element Rain, Fire, Wood, Stone, Fruit, Money;
	public static String[] BACE = { "��ˮRain", "����Fire", "ľ��Wood", "ʯͷStone",
			"��ʵFruit", "����Money" };

	public static Element getBACE(int id) {
		return BACE_ELEMENT[id];
	}

	public static void BACE_Mix(int id) {
		switch (id) {
		case 0://��ˮRain
				// ElementFunction.Mix(Rain, Seed);
			break;
		case 1:// ����Fire
			ElementFunction.Mix(Wood, Stone, Fire);
			break;
		case 2://ľ��Wood
			//ElementFunction.Mix(Sapling, Tree);
			break;
		case 3://ʯͷStone
			//ElementFunction.Mix(Seed, Fish);
			break;
		case 4://��ʵFruit
			//ElementFunction.Mix(Fish, Duck);
			break;
		case 5://����Money
			//ElementFunction.Mix(Duck, Monkey);
			break;
		default:
			break;
		}
	}

	// TODO ��ҪԪ��
	public static Element Seed, Sapling, Tree, Fish, Duck, Monkey, Human;
	public static String[] MAIN = { "����Seed", "����Sapling", "����Tree", "����Fish",
			"Ѽ��Duck", "Գ��Monkey", "����Human" };

	public static Element getMAIN(int id) {
		return MAIN_ELEMENT[id];
	}

	public static void MAIN_Mix(int id) {// ��ҪԪ�� �ϳ�

		switch (id) {
		case 0:// ������ˮ�ϳ�����
			ElementFunction.Mix(Rain, Seed);
			break;
		case 1:// �������Ӻϳ�����
			ElementFunction.Mix(Seed, Sapling);

			break;
		case 2:// ��������ϳɴ���
			ElementFunction.Mix(Sapling, Tree);
			break;
		case 3:// �������Ӻϳ�����
			ElementFunction.Mix(Seed, Fish);

			break;
		case 4:// ��������ϳ�Ѽ��
			ElementFunction.Mix(Fish, Duck);
			break;
		case 5:// ����Ѽ�Ӻϳ�Գ��
			ElementFunction.Mix(Duck, Monkey);
			break;
		case 6:// ����Գ��ϳ�����
			ElementFunction.Mix(Monkey, Human);
			break;
		default:
			break;
		}
	}

	// TODO ����Ԫ��
	public static Element Lumberyard, Quarry, House, Animal, Farm, Shop;
	public static String[] BUILD = { "ľ��Lumberyard", "ʯ��Quarry", "����House",
			"��ҵAnimal", "ũҵFarm", "��ҵShop" };

	public static Element getBUILD(int id) {
		return BUILD_ELEMENT[id];
	}

	public static void BUILD_Mix(int id) {// �ϳ�
		if (id == 0) {
			Human.value = Human.value.subtract(BigDecimal.valueOf(10));
			Lumberyard.value = Lumberyard.value.add(Lumberyard.reward);
		} else if (id == 1) {
			Human.value = Human.value.subtract(BigDecimal.valueOf(20));
			Quarry.value = Quarry.value.add(Quarry.reward);
		} else if (id == 2) {
			Wood.value = Wood.value.subtract(BigDecimal.valueOf(100));
			House.value = House.value.add(House.reward);
		} else if (id == 3) {
			Human.value = Human.value.subtract(BigDecimal.valueOf(200));
			Animal.value = Animal.value.add(Animal.reward);
		} else if (id == 4) {
			Human.value = Human.value.subtract(BigDecimal.valueOf(200));
			Farm.value = Farm.value.add(Farm.reward);
		} else {
			Fish.value = Fish.value.subtract(BigDecimal.valueOf(10000));
			Duck.value = Duck.value.subtract(BigDecimal.valueOf(10000));
			Sapling.value = Sapling.value.subtract(BigDecimal.valueOf(10000));
			Fruit.value = Fruit.value.subtract(BigDecimal.valueOf(10000));
			Wood.value = Wood.value.subtract(BigDecimal.valueOf(10000));
			Stone.value = Stone.value.subtract(BigDecimal.valueOf(10000));
			Shop.value = Shop.value.add(Shop.reward);
		}
	}

	// TODO ����Ԫ��
	public static Element Book, Knowledge, Gods, Reborn, Science;
	public static String[] OTHER = { "�鼮Book", "֪ʶKnowledge", "��ѧGods",
			"����Reborn", "�Ƽ�Science" };

}
