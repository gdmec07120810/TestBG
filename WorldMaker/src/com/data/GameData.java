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
	public static double RainSpeed[] = { 10, 20, 80, 160, 280 };// 天气影响雨水速度
	public static double SaplingSpeed[] = { 1, 0.8, 0.6, 0.4, 0.2 };// 天气影响树苗速率
	public static int Time = 0;// 天气计时，60秒随机变换一次天气

	public static void initElement() {// 初始化游戏元素

		WEATHER = new Random().nextInt(4);
		// new Element( 储量, 自增长量, 提高速率, 合成产量 )
		// 基础
		Rain = new Element(0, 0, 1, 0);// 无法合成
		Fire = new Element(0, 0.0001, 1, 1);// 合成消耗木、火
		Wood = new Element(0, 0.0001, 1, 0);// 无法合成
		Stone = new Element(0, 0.0001, 1, 0);// 无法合成
		Fruit = new Element(0, 0.0001, 1, 0);// 无法合成
		Money = new Element(0, 0.0001, 1, 0);// 无法合成
		Knowledge = new Element(0, 0.0001, 1, 1);// 无合成消耗
		BACE_ELEMENT = new Element[] { Rain, Fire, Wood, Stone, Fruit, Money,
				Knowledge };
		
		// 主要
		Seed = new Element(0, 1, 1, 1000);// 合成消耗雨水
		Sapling = new Element(0, 0.05, 1, 1000);// 合成消耗种子
		Tree = new Element(0, 0.025, 1, 1000);// 合成消耗树苗
		Fish = new Element(0, 0.08, 1, 1000);// 合成消耗种子
		Duck = new Element(0, 0.06, 1, 1000);// 合成消耗鱼苗
		Monkey = new Element(0, 0.03, 1, 1000);// 合成消耗鸭子
		Human = new Element(0, 0.02, 1, 1000);// 合成消耗猿类
		MAIN_ELEMENT = new Element[] { Seed, Sapling, Tree, Fish, Duck, Monkey,
				Human };
		
		// 建筑
		Lumberyard = new Element(0, 0, 1, 1000);// 合成消耗木材、石材、人类
		Quarry = new Element(0, 0, 1, 1000);// 合成消耗木材、石材、人类
		House = new Element(0, 0, 1, 1000);// 合成消耗木材、石材、人类
		Animal = new Element(0, 0, 1, 1000);// 合成消耗种子、人类
		Farm = new Element(0, 0, 1, 1000);// 合成消耗鱼苗、人类
		Shop = new Element(0, 0, 1, 1000);// 合成消耗种子、树苗、鱼苗、鸭子
		BUILD_ELEMENT = new Element[] { Lumberyard, Quarry, House, Animal,
				Farm, Shop };

		// 文明
		Book = new Element(0, 0, 1, 1000);// 合成消耗木材、货币
		Gods = new Element(0, 0, 1, 1000);// 合成消耗雨水、火焰、知识
		Reborn = new Element(0, 0, 1, 1000);// 合成消耗神学
		Science = new Element(0, 0, 1, 1000);// 无合成消耗
		OTHER_ELEMENT = new Element[] { Book, Gods, Reborn, Science };

	}

	// TODO 元素自增长
	public static void ElementAuto() {
		if (Seed.isOpen) {
			Seed.rate = Rain.value.multiply(BigDecimal.valueOf(0.0156852));// 种子产量：0.35/秒（×雨水量）
			ElementFunction.Auto(Seed);
		}
		if (Sapling.isOpen) {
			Sapling.rate = Seed.value.multiply(BigDecimal.valueOf(0.025362));// 树苗产量：0.005/秒（×种子量）
			Sapling.rate = Sapling.rate.multiply(BigDecimal
					.valueOf(SaplingSpeed[WEATHER]));// 天气影响树苗速率
			ElementFunction.Auto(Sapling);
		}
		if (Tree.isOpen) {
			Tree.rate = Sapling.value.multiply(BigDecimal.valueOf(0.01386));// 大树产量：0.005/秒（×树苗量）
			ElementFunction.Auto(Tree);
		}
		if (Fish.isOpen) {
			Fish.rate = Seed.value.multiply(BigDecimal.valueOf(0.02381));// 鱼苗产量：0.015/秒（×种子量）
			ElementFunction.Auto(Fish);
		}
		if (Duck.isOpen) {
			Duck.rate = Fish.value.multiply(BigDecimal.valueOf(0.006392));// 鸭子产量：0.0006/秒（×鱼苗量）
			ElementFunction.Auto(Duck);
		}
		if (Monkey.isOpen) {
			Monkey.rate = Duck.value.multiply(BigDecimal.valueOf(0.005326));// 猿类产量：0.0003/秒（×鸭子量）
			ElementFunction.Auto(Monkey);
		}
		if (Human.isOpen) {
			Human.rate = Monkey.value.multiply(BigDecimal.valueOf(0.0032852));// 人类产量：0.00002/秒（×猿类量）
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
			Wood.rate = Lumberyard.value.multiply(BigDecimal.valueOf(0.0875));// 木材产量：0.05/秒（×伐木场数量）
			ElementFunction.Auto(Wood);
		}
		if (Stone.isOpen) {
			Stone.rate = Quarry.value.multiply(BigDecimal.valueOf(0.0563));// 石材产量：0.03/秒（×采石场数量）
			ElementFunction.Auto(Stone);
		}
		if (Fruit.isOpen) {
			Fruit.rate = Sapling.value.multiply(BigDecimal.valueOf(0.0322));// 果实产量：0.0012/秒（×树苗量）
			ElementFunction.Auto(Fruit);
		}
		if (Money.isOpen) {
			Money.rate = Shop.value.multiply(BigDecimal.valueOf(0.153));// 货币产量：1.15/秒（×商业数量）
			ElementFunction.Auto(Money);
		}
		if (Knowledge.isOpen) {
			Knowledge.rate = Book.value.multiply(BigDecimal.valueOf(0.0185));// 知识产量：0.25/秒（×书籍量）
			ElementFunction.Auto(Knowledge);
		}

	}

	// 元素消耗（自动消耗其他元素）
	public static void ElementCost() {
		// Seed.c_speed = Fish.rate.multiply(BigDecimal.valueOf(0.086));
		// Fish.c_speed = Duck.rate.multiply(BigDecimal.valueOf(0.35));
		// Sapling.c_speed = Tree.rate.multiply(BigDecimal.valueOf(0.48));
		// Duck.c_speed = Monkey.rate.multiply(BigDecimal.valueOf(0.65));
		// Monkey.c_speed = Human.rate.multiply(BigDecimal.valueOf(0.87));
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
	public static Element Rain, Fire, Wood, Stone, Fruit, Money, Knowledge;
	public static String[] BACE = { "雨水Rain", "火焰Fire", "木材Wood", "石头Stone",
			"果实Fruit", "货币Money", "知识Knowledge" };

	public static Element getBACE(int id) {
		return BACE_ELEMENT[id];
	}

	public static void BACE_Mix(int id) {
		switch (id) {
		case 0:// 雨水Rain
				// ElementFunction.Mix(Rain, Seed);
			break;
		case 1:// 火焰Fire
				// ElementFunction.Mix(Wood, Stone, Fire);
			Fire.cost[0] = BigDecimal.valueOf(250);
			Fire.cost[1] = BigDecimal.valueOf(250);
			ElementFunction.Mix(new Element[] { Wood, Stone }, Fire);
			break;
		case 2:// 木材Wood
				// ElementFunction.Mix(Sapling, Tree);
			break;
		case 3:// 石头Stone
				// ElementFunction.Mix(Seed, Fish);
			break;
		case 4:// 果实Fruit
				// ElementFunction.Mix(Fish, Duck);
			break;
		case 5:// 货币Money
				// ElementFunction.Mix(Duck, Monkey);
			break;
		case 6:// 知识Knowledge
				// ElementFunction.Mix(Duck, Monkey);
				// Book.value = Book.value.subtract(BigDecimal.valueOf(880));
				// Knowledge.value = Knowledge.value.add(Fire.reward);
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
			Seed.cost[0] = BigDecimal.valueOf(100);
			ElementFunction.Mix(new Element[] { Rain }, Seed);
			break;
		case 1:// 消耗种子合成树苗
			Sapling.cost[0] = BigDecimal.valueOf(200);
			ElementFunction.Mix(new Element[] { Seed }, Sapling);
			break;
		case 2:// 消耗树苗合成大树
			Tree.cost[0] = BigDecimal.valueOf(300);
			ElementFunction.Mix(new Element[] { Sapling }, Tree);
			break;
		case 3:// 消耗种子合成鱼苗
			Fish.cost[0] = BigDecimal.valueOf(400);
			ElementFunction.Mix(new Element[] { Seed }, Fish);
			break;
		case 4:// 消耗鱼苗合成鸭子
			Duck.cost[0] = BigDecimal.valueOf(500);
			ElementFunction.Mix(new Element[] { Fish }, Duck);
			break;
		case 5:// 消耗鸭子合成猿类
			Monkey.cost[0] = BigDecimal.valueOf(600);
			ElementFunction.Mix(new Element[] { Duck }, Monkey);
			break;
		case 6:// 消耗猿类合成人类
			Human.cost[0] = BigDecimal.valueOf(700);
			ElementFunction.Mix(new Element[] { Monkey }, Human);
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

	// TODO 文明元素
	public static Element Book, Gods, Reborn, Science;
	public static String[] OTHER = { "书籍Book", "神学Gods", "重生Reborn",
			"科技Science" };

	public static Element getOTHER(int id) {
		return OTHER_ELEMENT[id];
	}

	public static void OTHER_Mix(int id) {// 合成

		switch (id) {
		case 0:// 书籍，消耗木材 金钱
			Book.cost[0] = BigDecimal.valueOf(500);
			Book.cost[1] = BigDecimal.valueOf(100);
			ElementFunction.Mix(new Element[] { Wood, Money }, Book);
			break;
		case 1:// 神学，消耗雨水 火焰
			Gods.cost[0] = BigDecimal.valueOf(3000);
			Gods.cost[1] = BigDecimal.valueOf(3000);
			ElementFunction.Mix(new Element[] { Rain, Fire }, Gods);
			break;
		case 2:// 重新，神学转化为科技点，消耗所有神学，重新+1
			Reborn.cost[0] = BigDecimal.valueOf(10000);
			if (Gods.value.compareTo(Reborn.cost[0]) == -1) {// 判断a少于b
				Log.e("Tips", "资源不足，无法合成");
				return;// 条件不符合，跳出程序
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
