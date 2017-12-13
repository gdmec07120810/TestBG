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
	public static double RainSpeed[] = { 10, 20, 80, 160, 280 };// ����Ӱ����ˮ�ٶ�
	public static double SaplingSpeed[] = { 1, 0.8, 0.6, 0.4, 0.2 };// ����Ӱ����������
	public static int Time = 0;// ������ʱ��60������任һ������

	public static void initElement() {// ��ʼ����ϷԪ��

		WEATHER = new Random().nextInt(4);
		// new Element( ����, ��������, �������, �ϳɲ��� )
		// ����
		Rain = new Element(0, 0, 1, 0);// �޷��ϳ�
		Fire = new Element(0, 0.0001, 1, 1);// �ϳ�����ľ����
		Wood = new Element(0, 0.0001, 1, 0);// �޷��ϳ�
		Stone = new Element(0, 0.0001, 1, 0);// �޷��ϳ�
		Fruit = new Element(0, 0.0001, 1, 0);// �޷��ϳ�
		Money = new Element(0, 0.0001, 1, 0);// �޷��ϳ�
		Knowledge = new Element(0, 0.0001, 1, 1);// �޺ϳ�����
		BACE_ELEMENT = new Element[] { Rain, Fire, Wood, Stone, Fruit, Money,
				Knowledge };
		
		// ��Ҫ
		Seed = new Element(0, 1, 1, 1000);// �ϳ�������ˮ
		Sapling = new Element(0, 0.05, 1, 1000);// �ϳ���������
		Tree = new Element(0, 0.025, 1, 1000);// �ϳ���������
		Fish = new Element(0, 0.08, 1, 1000);// �ϳ���������
		Duck = new Element(0, 0.06, 1, 1000);// �ϳ���������
		Monkey = new Element(0, 0.03, 1, 1000);// �ϳ�����Ѽ��
		Human = new Element(0, 0.02, 1, 1000);// �ϳ�����Գ��
		MAIN_ELEMENT = new Element[] { Seed, Sapling, Tree, Fish, Duck, Monkey,
				Human };
		
		// ����
		Lumberyard = new Element(0, 0, 1, 1000);// �ϳ�����ľ�ġ�ʯ�ġ�����
		Quarry = new Element(0, 0, 1, 1000);// �ϳ�����ľ�ġ�ʯ�ġ�����
		House = new Element(0, 0, 1, 1000);// �ϳ�����ľ�ġ�ʯ�ġ�����
		Animal = new Element(0, 0, 1, 1000);// �ϳ��������ӡ�����
		Farm = new Element(0, 0, 1, 1000);// �ϳ��������硢����
		Shop = new Element(0, 0, 1, 1000);// �ϳ��������ӡ����硢���硢Ѽ��
		BUILD_ELEMENT = new Element[] { Lumberyard, Quarry, House, Animal,
				Farm, Shop };

		// ����
		Book = new Element(0, 0, 1, 1000);// �ϳ�����ľ�ġ�����
		Gods = new Element(0, 0, 1, 1000);// �ϳ�������ˮ�����桢֪ʶ
		Reborn = new Element(0, 0, 1, 1000);// �ϳ�������ѧ
		Science = new Element(0, 0, 1, 1000);// �޺ϳ�����
		OTHER_ELEMENT = new Element[] { Book, Gods, Reborn, Science };

	}

	// TODO Ԫ��������
	public static void ElementAuto() {
		if (Seed.isOpen) {
			Seed.rate = Rain.value.multiply(BigDecimal.valueOf(0.0156852));// ���Ӳ�����0.35/�루����ˮ����
			ElementFunction.Auto(Seed);
		}
		if (Sapling.isOpen) {
			Sapling.rate = Seed.value.multiply(BigDecimal.valueOf(0.025362));// ���������0.005/�루����������
			Sapling.rate = Sapling.rate.multiply(BigDecimal
					.valueOf(SaplingSpeed[WEATHER]));// ����Ӱ����������
			ElementFunction.Auto(Sapling);
		}
		if (Tree.isOpen) {
			Tree.rate = Sapling.value.multiply(BigDecimal.valueOf(0.01386));// ����������0.005/�루����������
			ElementFunction.Auto(Tree);
		}
		if (Fish.isOpen) {
			Fish.rate = Seed.value.multiply(BigDecimal.valueOf(0.02381));// ���������0.015/�루����������
			ElementFunction.Auto(Fish);
		}
		if (Duck.isOpen) {
			Duck.rate = Fish.value.multiply(BigDecimal.valueOf(0.006392));// Ѽ�Ӳ�����0.0006/�루����������
			ElementFunction.Auto(Duck);
		}
		if (Monkey.isOpen) {
			Monkey.rate = Duck.value.multiply(BigDecimal.valueOf(0.005326));// Գ�������0.0003/�루��Ѽ������
			ElementFunction.Auto(Monkey);
		}
		if (Human.isOpen) {
			Human.rate = Monkey.value.multiply(BigDecimal.valueOf(0.0032852));// ���������0.00002/�루��Գ������
			ElementFunction.Auto(Human);
		}
		if (Rain.isOpen) {
			Rain.speed = BigDecimal.valueOf(RainSpeed[WEATHER]);
			ElementFunction.Auto(Rain);
		}
		if (Fire.isOpen) {
			Fire.speed = BigDecimal.valueOf(0.00001);
			ElementFunction.Auto(Fire);
		}
		//
		if (Wood.isOpen) {
			Wood.rate = Lumberyard.value.multiply(BigDecimal.valueOf(0.0875));// ľ�Ĳ�����0.05/�루����ľ��������
			ElementFunction.Auto(Wood);
		}
		if (Stone.isOpen) {
			Stone.rate = Quarry.value.multiply(BigDecimal.valueOf(0.0563));// ʯ�Ĳ�����0.03/�루����ʯ��������
			ElementFunction.Auto(Stone);
		}
		if (Fruit.isOpen) {
			Fruit.rate = Sapling.value.multiply(BigDecimal.valueOf(0.0322));// ��ʵ������0.0012/�루����������
			ElementFunction.Auto(Fruit);
		}
		if (Money.isOpen) {
			Money.rate = Shop.value.multiply(BigDecimal.valueOf(0.153));// ���Ҳ�����1.15/�루����ҵ������
			ElementFunction.Auto(Money);
		}
		if (Knowledge.isOpen) {
			Knowledge.rate = Book.value.multiply(BigDecimal.valueOf(0.0185));// ֪ʶ������0.25/�루���鼮����
			ElementFunction.Auto(Knowledge);
		}

	}

	// Ԫ�����ģ��Զ���������Ԫ�أ�
	public static void ElementCost() {
		// Seed.c_speed = Fish.rate.multiply(BigDecimal.valueOf(0.086));
		// Fish.c_speed = Duck.rate.multiply(BigDecimal.valueOf(0.35));
		// Sapling.c_speed = Tree.rate.multiply(BigDecimal.valueOf(0.48));
		// Duck.c_speed = Monkey.rate.multiply(BigDecimal.valueOf(0.65));
		// Monkey.c_speed = Human.rate.multiply(BigDecimal.valueOf(0.87));
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
	public static Element Rain, Fire, Wood, Stone, Fruit, Money, Knowledge;
	public static String[] BACE = { "��ˮRain", "����Fire", "ľ��Wood", "ʯͷStone",
			"��ʵFruit", "����Money", "֪ʶKnowledge" };

	public static Element getBACE(int id) {
		return BACE_ELEMENT[id];
	}

	public static void BACE_Mix(int id) {
		switch (id) {
		case 0:// ��ˮRain
				// ElementFunction.Mix(Rain, Seed);
			break;
		case 1:// ����Fire
				// ElementFunction.Mix(Wood, Stone, Fire);
			Fire.cost[0] = BigDecimal.valueOf(250);
			Fire.cost[1] = BigDecimal.valueOf(250);
			ElementFunction.Mix(new Element[] { Wood, Stone }, Fire);
			break;
		case 2:// ľ��Wood
				// ElementFunction.Mix(Sapling, Tree);
			break;
		case 3:// ʯͷStone
				// ElementFunction.Mix(Seed, Fish);
			break;
		case 4:// ��ʵFruit
				// ElementFunction.Mix(Fish, Duck);
			break;
		case 5:// ����Money
				// ElementFunction.Mix(Duck, Monkey);
			break;
		case 6:// ֪ʶKnowledge
				// ElementFunction.Mix(Duck, Monkey);
				// Book.value = Book.value.subtract(BigDecimal.valueOf(880));
				// Knowledge.value = Knowledge.value.add(Fire.reward);
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
			Seed.cost[0] = BigDecimal.valueOf(100);
			ElementFunction.Mix(new Element[] { Rain }, Seed);
			break;
		case 1:// �������Ӻϳ�����
			Sapling.cost[0] = BigDecimal.valueOf(200);
			ElementFunction.Mix(new Element[] { Seed }, Sapling);
			break;
		case 2:// ��������ϳɴ���
			Tree.cost[0] = BigDecimal.valueOf(300);
			ElementFunction.Mix(new Element[] { Sapling }, Tree);
			break;
		case 3:// �������Ӻϳ�����
			Fish.cost[0] = BigDecimal.valueOf(400);
			ElementFunction.Mix(new Element[] { Seed }, Fish);
			break;
		case 4:// ��������ϳ�Ѽ��
			Duck.cost[0] = BigDecimal.valueOf(500);
			ElementFunction.Mix(new Element[] { Fish }, Duck);
			break;
		case 5:// ����Ѽ�Ӻϳ�Գ��
			Monkey.cost[0] = BigDecimal.valueOf(600);
			ElementFunction.Mix(new Element[] { Duck }, Monkey);
			break;
		case 6:// ����Գ��ϳ�����
			Human.cost[0] = BigDecimal.valueOf(700);
			ElementFunction.Mix(new Element[] { Monkey }, Human);
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

		switch (id) {
		case 0:
			Lumberyard.cost[0] = BigDecimal.valueOf(10);
			ElementFunction.Mix(new Element[] { Human }, Lumberyard);
			break;
		case 1:
			Quarry.cost[0] = BigDecimal.valueOf(20);
			ElementFunction.Mix(new Element[] { Human }, Quarry);
			break;
		case 2:
			House.cost[0] = BigDecimal.valueOf(100);
			ElementFunction.Mix(new Element[] { Wood }, House);
			break;
		case 3:
			Animal.cost[0] = BigDecimal.valueOf(200);
			ElementFunction.Mix(new Element[] { Human }, Animal);
			break;
		case 4:
			Farm.cost[0] = BigDecimal.valueOf(200);
			ElementFunction.Mix(new Element[] { Human }, Farm);
			break;
		case 5:
			Shop.cost[0] = BigDecimal.valueOf(1000);
			Shop.cost[1] = BigDecimal.valueOf(1000);
			Shop.cost[2] = BigDecimal.valueOf(1000);
			Shop.cost[3] = BigDecimal.valueOf(1000);
			ElementFunction.Mix(new Element[] { Fish, Duck, Fruit, Human },
					Shop);
			break;
		default:
			break;
		}
	}

	// TODO ����Ԫ��
	public static Element Book, Gods, Reborn, Science;
	public static String[] OTHER = { "�鼮Book", "��ѧGods", "����Reborn",
			"�Ƽ�Science" };

	public static Element getOTHER(int id) {
		return OTHER_ELEMENT[id];
	}

	public static void OTHER_Mix(int id) {// �ϳ�

		switch (id) {
		case 0:// �鼮������ľ�� ��Ǯ
			Book.cost[0] = BigDecimal.valueOf(500);
			Book.cost[1] = BigDecimal.valueOf(100);
			ElementFunction.Mix(new Element[] { Wood, Money }, Book);
			break;
		case 1:// ��ѧ��������ˮ ����
			Gods.cost[0] = BigDecimal.valueOf(3000);
			Gods.cost[1] = BigDecimal.valueOf(3000);
			ElementFunction.Mix(new Element[] { Rain, Fire }, Gods);
			break;
		case 2:// ���£���ѧת��Ϊ�Ƽ��㣬����������ѧ������+1
			Reborn.cost[0] = BigDecimal.valueOf(10000);
			if (Gods.value.compareTo(Reborn.cost[0]) == -1) {// �ж�a����b
				Log.e("Tips", "��Դ���㣬�޷��ϳ�");
				return;// ���������ϣ���������
			}
			Science.value = Science.value.add(Gods.value.multiply(BigDecimal
					.valueOf(0.00375)));
			ElementFunction.Mix(new Element[] { Gods }, Reborn);
			break;
		default:
			break;
		}

	}
}
