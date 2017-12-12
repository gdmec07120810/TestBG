package com.example.worldmaker;

import java.util.Random;
import com.data.GameData;
import com.data.GifView;

import adapter.Build_ListAdapter;
import adapter.Main_ListAdapter;
import adapter.Other_ListAdapter;
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
	Button mainTo;
	Button btn_main, btn_build, btn_other;
	Main_ListAdapter main_adapter;
	Build_ListAdapter build_adapter;
	Other_ListAdapter other_adapter;
	int mainN = -1;
	int page = 0;
	GifView weather_gif;
	int W[] = { R.drawable.tianqing, R.drawable.xiaoyu, R.drawable.zhongyu,
			R.drawable.dayugif };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		GameData.initElement();
		setContentView(R.layout.activity_main);
		weather_gif = (GifView) findViewById(R.id.weather_gif);
		textview = (TextView) findViewById(R.id.textView1);
		list = (ListView) findViewById(R.id.listView1);
		mainTo = (Button) findViewById(R.id.mainTo);
		btn_main = (Button) findViewById(R.id.btn_main);
		btn_build = (Button) findViewById(R.id.btn_build);
		btn_other = (Button) findViewById(R.id.btn_other);

		main_adapter = new Main_ListAdapter(this, handler);
		build_adapter = new Build_ListAdapter(this, handler);
		other_adapter = new Other_ListAdapter(this, handler);
		list.setAdapter(main_adapter);
		// ͨ��Handler�����߳�
		mHandler.post(mRunnable); // ������Ϣ�������߳�����

		updateView();

		mainTo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO
				if (mainN == -1) {
					mainN++;
					GameData.Rain.isOpen = true;
				}
				if (mainN < GameData.MAIN_ELEMENT.length) {
					GameData.MAIN_ELEMENT[mainN].isOpen = true;
					mainN++;
				}
				if (mainN == GameData.MAIN_ELEMENT.length) {
					mainTo.setVisibility(View.GONE);
				}
				main_adapter.notifyDataSetChanged();
			}
		});

		btn_main.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				list.setAdapter(main_adapter);
				page = 0;
			}
		});
		btn_build.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				list.setAdapter(build_adapter);
				page = 1;
			}
		});
		btn_other.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				list.setAdapter(other_adapter);
				page = 2;
			}
		});
	}

	private void updateView() {
		// ���ñ���gifͼƬ��Դ
		weather_gif.setMovieResource(W[GameData.WEATHER]);
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

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// DecimalFormat df = new DecimalFormat("0.##E0");

			if (page == 0) {
				main_adapter.notifyDataSetChanged();
			}
			if (page == 1) {
				build_adapter.notifyDataSetChanged();
			}
			if (page == 2) {
				other_adapter.notifyDataSetChanged();
			}
			updateView();
		}
	};

	private Handler mHandler = new Handler();
	private Runnable mRunnable = new Runnable() {
		public void run() {
			if (GameData.Rain.isOpen) {
				GameData.ElementAuto();// Ԫ��������
				GameData.ElementCost();// Ԫ��������

			}
			if (GameData.Time < 60) {// ÿ60�뻻һ������
				GameData.Time++;
			} else {
				GameData.WEATHER = new Random().nextInt(4);
				GameData.Time = 0;
			}
			handler.sendEmptyMessage(0);// ÿ��ִ��һ��
			mHandler.postDelayed(mRunnable, 1000); // ���Լ�������Ϣ��������
		}
	};

	@Override
	protected void onDestroy() {
		// ���߳����ٵ�
		mHandler.removeCallbacks(mRunnable);
		super.onDestroy();
	}

}
