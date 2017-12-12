package com.example.worldmaker;

import java.util.Random;
import com.data.GameData;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textview;
	ListView list;
	Button btn_main, btn_build, btn_other;
	Main_ListAdapter main_adapter;
	Build_ListAdapter build_adapter;
	Other_ListAdapter other_adapter;
	int k = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		GameData.initElement();
		setContentView(R.layout.activity_main);
		textview = (TextView) findViewById(R.id.textView1);
		list = (ListView) findViewById(R.id.listView1);
		btn_main = (Button) findViewById(R.id.btn_main);
		btn_build = (Button) findViewById(R.id.btn_build);
		btn_other = (Button) findViewById(R.id.btn_other);

		main_adapter = new Main_ListAdapter(this, handler);
		build_adapter = new Build_ListAdapter(this, handler);
		other_adapter = new Other_ListAdapter(this, handler);

		// ͨ��Handler�����߳�
		mHandler.post(mRunnable); // ������Ϣ�������߳�����
		list.setAdapter(main_adapter);
		btn_main.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				list.setAdapter(main_adapter);
				k = 0;
			}
		});
		btn_build.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				list.setAdapter(build_adapter);
				k = 1;
			}
		});
		btn_other.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				list.setAdapter(other_adapter);
				k = 2;
			}
		});

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// DecimalFormat df = new DecimalFormat("0.##E0");

			// rain = rain.add(speed).add(rain.multiply(y)); // �� +
			// bigDecimalA = bigDecimalA.subtract(bigDecimalB);// �� -
			// bigDecimalA = bigDecimalA.multiply(bigDecimalB);// �� *
			// bigDecimalA = bigDecimalA.divide(bigDecimalB); // �� /
			if (k == 0) {
				main_adapter.notifyDataSetChanged();
			}
			if (k == 1) {
				build_adapter.notifyDataSetChanged();
			}
			if (k == 2) {
				other_adapter.notifyDataSetChanged();
			}
			StringBuffer bf = new StringBuffer();
			bf.append("������" + GameData.W[GameData.WEATHER] + "(+"
					+ GameData.RainSpeed[GameData.WEATHER] + "/��)" + "��ˮRain��"
					+ GameData.get_eMode(GameData.Rain.value, 2));
			bf.append("\n");
			bf.append("ľ��" + GameData.get_eMode(GameData.Wood.value, 0) + "  ʯ��"
					+ GameData.get_eMode(GameData.Stone.value, 0) + " ��"
					+ GameData.get_eMode(GameData.Fire.value, 0) + "  ����"
					+ GameData.get_eMode(GameData.Fruit.value, 0) + "  Ǯ��"
					+ GameData.get_eMode(GameData.Money.value, 0) + "  ֪��"
					+ GameData.get_eMode(GameData.Knowledge.value, 0));
			textview.setText(bf.toString());
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
