package com.example.worldmaker;

import java.util.Random;
import com.data.GameData;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textview;
	ListView list;
	ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		GameData.initElement();
		setContentView(R.layout.activity_main);
		textview = (TextView) findViewById(R.id.textView1);
		list = (ListView) findViewById(R.id.listView1);

		adapter = new ListAdapter(this, handler);
		list.setAdapter(adapter);
		// 通过Handler启动线程
		mHandler.post(mRunnable); // 发送消息，启动线程运行

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// DecimalFormat df = new DecimalFormat("0.##E0");
			// rain = getNumber(String.valueOf(rain), "5000");

			// rain = rain.add(speed).add(rain.multiply(y)); // 加 +
			// bigDecimalA = bigDecimalA.subtract(bigDecimalB);// 减 -
			// bigDecimalA = bigDecimalA.multiply(bigDecimalB);// 乘 *
			// bigDecimalA = bigDecimalA.divide(bigDecimalB); // 除 /
			adapter.notifyDataSetChanged();
			textview.setText("天气：" + GameData.W[GameData.WEATHER] + "(+"
					+ GameData.RainSpeed[GameData.WEATHER] + "/秒)\n" + "雨水Rain："
					+ GameData.get_eMode(GameData.Rain.value, 2));
		}
	};

	private Handler mHandler = new Handler();
	private Runnable mRunnable = new Runnable() {
		public void run() {
			GameData.ElementAuto();// 元素自增长
			GameData.ElementCost();// 元素自消耗
			handler.sendEmptyMessage(0);
			// 每3秒执行一次
			mHandler.postDelayed(mRunnable, 1000); // 给自己发送消息，自运行
			if (GameData.Time < 60) {
				GameData.Time++;
			} else {
				GameData.WEATHER = new Random().nextInt(4);
				GameData.Time = 0;
			}
		}
	};

	@Override
	protected void onDestroy() {
		// 将线程销毁掉
		mHandler.removeCallbacks(mRunnable);
		super.onDestroy();
	}

}
