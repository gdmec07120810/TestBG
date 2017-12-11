package com.data;

import java.math.BigDecimal;
import java.util.Random;

import android.util.Log;

public class GameData {

	public static Element BACE_ELEMENT[];
	public static Element MAIN_ELEMENT[];
	public static Element BUILD_ELEMENT[];
	public static Element OTHER_ELEMENT[];

	// TODO 天气，影响雨水基础速度
	public static int WEATHER = 0;// 0天晴1小雨2中雨3大雨4暴雨
	public static String W[] = { "天晴", "小雨", "中雨", "大雨", "暴雨" };
	public static double RainSpeed[] = { 1, 2, 3, 4, 5 };// 天气影响雨水速度
	public static double SaplingSpeed[] = { 1, 0.8, 0.6, 0.4, 0.2 };// 天气影响树苗速率
	public static int Time = 0;// 天气计时，60秒随机变换一次天气

	public static void initElement() {// 初始化游戏元素
		WEATHER = new Random().nextInt(4);
		// new Element( 储量, 自增长量, 提高速率, 合成消耗 , 合成产量 )
		// 基础
		Rain = new Element(0, 0, 1, 0);// 无法合成
		Fire = new Element(0, 0.0001, 1, 0, 0, 0);// 合成消耗木、火
		Wood = new Element(0, 0.0001, 1, 0);// 无法合成
		Stone = new Element(0, 0.0001, 1, 0);// 无法合成
		Fruit = new Element(0, 0.0001, 1, 0);// 无法合成
		Money = new Element(0, 0.0001, 1, 0);// 无法合成
		BACE_ELEMENT = new Element[] { Rain, Fire, Wood, Stone, Fruit, Money };

		// 主要
		Seed = new Element(0, 1, 1, 100, 500);// 合成消耗雨水
		Sapling = new Element(0, 0.05, 1, 0, 0);// 合成消耗种子
		Tree = new Element(0, 0.025, 1, 0, 0);// 合成消耗树苗
		Fish = new Element(0, 0.08, 1, 0, 0);// 合成消耗种子
		Duck = new Element(0, 0.06, 1, 0, 0);// 合成消耗鱼苗
		Monkey = new Element(0, 0.03, 1, 0, 0);// 合成消耗鸭子
		Human = new Element(0, 0.02, 1, 0, 0);// 合成消耗猿类
		MAIN_ELEMENT = new Element[] { Seed, Sapling, Tree, Fish, Duck, Monkey,
				Human };
		// 建筑
		Lumberyard = new Element(0, 0, 1, 0, 0, 0, 1);// 合成消耗木材、石材、人类
		Quarry = new Element(0, 0, 1, 0, 0, 0, 1);// 合成消耗木材、石材、人类
		House = new Element(0, 0, 1, 0, 0, 0, 1);// 合成消耗木材、石材、人类
		Animal = new Element(0, 0, 1, 0, 0, 1);// 合成消耗种子、人类
		Farm = new Element(0, 0, 1, 0, 0, 1);// 合成消耗鱼苗、人类
		Shop = new Element(0, 0, 1, 0, 0, 0, 0, 1);// 合成消耗种子、树苗、鱼苗、鸭子
		BUILD_ELEMENT = new Element[] { Lumberyard, Quarry, House, Animal,
				Farm, Shop };
		// 文明
		Book = new Element(0, 0, 1, 0, 0, 0);// 合成消耗木材、货币
		Knowledge = new Element(0, 0, 1, 0);// 无合成消耗
		Gods = new Element(0, 0, 1, 0, 0, 0, 0);// 合成消耗雨水、火焰、知识
		Reborn = new Element(0, 0, 1, 0, 0);// 合成消耗神学
		Science = new Element(0, 0, 1, 0);// 无合成消耗
		OTHER_ELEMENT = new Element[] { Book, Knowledge, Gods, Reborn, Science };
	}

	// TODO 元素自增长
	public static void ElementAuto() {
		Rain.speed = BigDecimal.valueOf(RainSpeed[WEATHER]);

		Seed.rate = Rain.value.multiply(BigDecimal.valueOf(0.0156852));// 种子产量：0.35/秒（×雨水量）
		Sapling.rate = Seed.value.multiply(BigDecimal.valueOf(0.025362));// 树苗产量：0.005/秒（×种子量）
		Sapling.rate = Sapling.rate.multiply(BigDecimal
				.valueOf(SaplingSpeed[WEATHER]));// 天气影响树苗速率
		Fish.rate = Seed.value.multiply(BigDecimal.valueOf(0.02381));// 鱼苗产量：0.015/秒（×种子量）
		Tree.rate = Sapling.value.multiply(BigDecimal.valueOf(0.01386));// 大树产量：0.005/秒（×树苗量）
		Duck.rate = Fish.value.multiply(BigDecimal.valueOf(0.006392));// 鸭子产量：0.0006/秒（×鱼苗量）
		Monkey.rate = Duck.value.multiply(BigDecimal.valueOf(0.005326));// 猿类产量：0.0003/秒（×鸭子量）
		Human.rate = Monkey.value.multiply(BigDecimal.valueOf(0.0032852));// 人类产量：0.00002/秒（×猿类量）
		//
		Fruit.rate = Sapling.value.multiply(BigDecimal.valueOf(0.55));// 果实产量：0.0012/秒（×树苗量）
		
		Wood.rate = Lumberyard.value.multiply(BigDecimal.valueOf(0.35));// 木材产量：0.05/秒（×伐木场数量）
		Stone.rate = Quarry.value.multiply(BigDecimal.valueOf(0.25));// 石材产量：0.03/秒（×采石场数量）
		Money.rate = Shop.value.multiply(BigDecimal.valueOf(1.55));// 货币产量：1.15/秒（×商业数量）
		Knowledge.rate = Book.value.multiply(BigDecimal.valueOf(0.55));// 知识产量：0.25/秒（×书籍量）
		//

		// Seed.c_speed = Fish.rate.multiply(BigDecimal.valueOf(3.5));
		// Fish.c_speed = Duck.rate.multiply(BigDecimal.valueOf(2.5));
		// Sapling.c_speed = Tree.rate.multiply(BigDecimal.valueOf(1.5));
		// Duck.c_speed = Monkey.rate.multiply(BigDecimal.valueOf(1.05));
		// Monkey.c_speed = Human.rate.multiply(BigDecimal.valueOf(1.0));

		ElementFunction.Auto(Rain);
		// ElementFunction.Auto(Fire);火苗必须合成
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

	// 元素消耗（自动消耗其他元素）
	public static void ElementCost() {
		// ElementFunction.Cost(Fish, Seed, 0.002865); // 鱼苗自消耗种子 = 鱼苗 *
		// 0.0065/s
		// ElementFunction.Cost(Duck, Fish, 0.014865); // 鸭子自消耗鱼苗 = 鸭子 *
		// 0.0050/s
		// ElementFunction.Cost(Tree, Sapling, 0.00865); // 大树自消树苗 = 大树 *
		// 0.0050/s
		// ElementFunction.Cost(Monkey, Duck, 0.01865); // 猿类自消耗鸭子 = 猿类 *0.025/s
		// ElementFunction.Cost(Human, Monkey, 0.02865); // 人类自消耗猿类 = 人类 *0.05/s
	}

	// TODO 长数字转科学计数法
	public static String get_eMode(BigDecimal bd, int i) {
		// BigDecimal bd = element.value;
		String n = bd.setScale(i, BigDecimal.ROUND_HALF_UP).toString();
		String str = bd.toString();
		int c = str.indexOf(".");// 获取小数点位置，判断整数位大小
		c = c == -1 ? bd.toString().length() : c;// 三目运算，无小数点则直接取整
		if (c > 5) {
			StringBuffer bf = new StringBuffer();
			bf.append(n.substring(0, 1)).append(".").append(n.substring(1, 3));
			return bf.toString() + "e" + (c - 1);
		} else {
			return n;
		}
	}

	// TODO 基础元素
	public static Element Rain, Fire, Wood, Stone, Fruit, Money;
	public static String[] BACE = { "雨水Rain", "火焰Fire", "木材Wood", "石头Stone",
			"果实Fruit", "货币Money" };

	public static Element getBACE(int id) {
		return BACE_ELEMENT[id];
	}

	public static void BACE_Mix(int id) {
		switch (id) {
		case 0://雨水Rain
				// ElementFunction.Mix(Rain, Seed);
			break;
		case 1:// 火焰Fire
			ElementFunction.Mix(Wood, Stone, Fire);
			break;
		case 2://木材Wood
			//ElementFunction.Mix(Sapling, Tree);
			break;
		case 3://石头Stone
			//ElementFunction.Mix(Seed, Fish);
			break;
		case 4://果实Fruit
			//ElementFunction.Mix(Fish, Duck);
			break;
		case 5://货币Money
			//ElementFunction.Mix(Duck, Monkey);
			break;
		default:
			break;
		}
	}

	// TODO 主要元素
	public static Element Seed, Sapling, Tree, Fish, Duck, Monkey, Human;
	public static String[] MAIN = { "种子Seed", "树苗Sapling", "大树Tree", "鱼苗Fish",
			"鸭子Duck", "猿类Monkey", "人类Human" };

	public static Element getMAIN(int id) {
		return MAIN_ELEMENT[id];
	}

	public static void MAIN_Mix(int id) {// 主要元素 合成

		switch (id) {
		case 0:// 消耗雨水合成种子
			ElementFunction.Mix(Rain, Seed);
			break;
		case 1:// 消耗种子合成树苗
			ElementFunction.Mix(Seed, Sapling);

			break;
		case 2:// 消耗树苗合成大树
			ElementFunction.Mix(Sapling, Tree);
			break;
		case 3:// 消耗种子合成鱼苗
			ElementFunction.Mix(Seed, Fish);

			break;
		case 4:// 消耗鱼苗合成鸭子
			ElementFunction.Mix(Fish, Duck);
			break;
		case 5:// 消耗鸭子合成猿类
			ElementFunction.Mix(Duck, Monkey);
			break;
		case 6:// 消耗猿类合成人类
			ElementFunction.Mix(Monkey, Human);
			break;
		default:
			break;
		}
	}

	// TODO 建筑元素
	public static Element Lumberyard, Quarry, House, Animal, Farm, Shop;
	public static String[] BUILD = { "木场Lumberyard", "石场Quarry", "房屋House",
			"牧业Animal", "农业Farm", "商业Shop" };

	public static Element getBUILD(int id) {
		return BUILD_ELEMENT[id];
	}

	public static void BUILD_Mix(int id) {// 合成
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

	// TODO 文明元素
	public static Element Book, Knowledge, Gods, Reborn, Science;
	public static String[] OTHER = { "书籍Book", "知识Knowledge", "神学Gods",
			"重生Reborn", "科技Science" };

}
