package adapter;

import com.data.GameData;
import com.example.worldmaker.R;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main_ListAdapter extends BaseAdapter {
	Context context;
	Handler handler;

	public Main_ListAdapter(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int K = 0;
		for (int i = 0; i < GameData.MAIN_ELEMENT.length; i++) {
			if (GameData.MAIN_ELEMENT[i].isOpen) {
				K++;
			}
		}
		return K;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static class ViewHolder {
		public ImageView img;
		public TextView name;
		public Button btn;
	}

	@Override
	public View getView(int id, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.element_list,
					null);
			holder = new ViewHolder();
			holder.btn = (Button) view.findViewById(R.id.btn);
			// holder.img = (ImageView) view.findViewById(R.id.item_img);
			holder.name = (TextView) view.findViewById(R.id.name);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		String str = GameData.get_eMode(GameData.getMAIN(id).value, 0);

		String speed = GameData.get_eMode(GameData.MAIN_ELEMENT[id].speed
				.multiply(GameData.MAIN_ELEMENT[id].rate), 2);
		String cost = GameData.get_eMode(GameData.MAIN_ELEMENT[id].c_speed
				.multiply(GameData.MAIN_ELEMENT[id].c_rate), 3);
		holder.name.setText(GameData.MAIN[id] + "：" + str + "(+" + speed
				+ "/秒)" + "(-" + cost + "/秒)");

		final int i = id;
		holder.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO
				GameData.MAIN_Mix(i);
				handler.sendEmptyMessage(0);// 刷新整个界面
			}
		});

		return view;
	}

}
