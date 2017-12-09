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
		// ͨ��Handler�����߳�
		mHandler.post(mRunnable); // ������Ϣ�������߳�����

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// DecimalFormat df = new DecimalFormat("0.##E0");
			// rain = getNumber(String.valueOf(rain), "5000");

			// rain = rain.add(speed).add(rain.multiply(y)); // �� +
			// bigDecimalA = bigDecimalA.subtract(bigDecimalB);// �� -
			// bigDecimalA = bigDecimalA.multiply(bigDecimalB);// �� *
			// bigDecimalA = bigDecimalA.divide(bigDecimalB); // �� /
			adapter.notifyDataSetChanged();
			textview.setText("������" + GameData.W[GameData.WEATHER] + "(+"
					+ GameData.RainSpeed[GameData.WEATHER] + "/��)\n" + "��ˮRain��"
					+ GameData.get_eMode(GameData.Rain.value, 2));
		}
	};

	private Handler mHandler = new Handler();
	private Runnable mRunnable = new Runnable() {
		public void run() {
			GameData.ElementAuto();// Ԫ��������
			GameData.ElementCost();// Ԫ��������
			handler.sendEmptyMessage(0);
			// ÿ3��ִ��һ��
			mHandler.postDelayed(mRunnable, 1000); // ���Լ�������Ϣ��������
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
		// ���߳����ٵ�
		mHandler.removeCallbacks(mRunnable);
		super.onDestroy();
	}

}
