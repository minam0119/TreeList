package com.example.original;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class LeafActivity extends Activity implements OnTouchListener {
	private EditText edittext;
	private EditText titleedittext;
	private ImageButton backbutoon, savebutton;
	private TextView kigentext;
	Spinner sp;

	// **
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leaf);
		// 以下のコードを呼び出すのは**の位置ではエラーが出る
		SharedPreferences prefs = getSharedPreferences("Setting", Context.MODE_PRIVATE);
		edittext = (EditText) findViewById(R.id.editText1);

		// keyはkigenのこと
		String readkigen = prefs.getString("kigen", "");
		// readkigenは呼び出し用のint型変数
		// edittext.setText(readkigen);

		titleedittext = (EditText) findViewById(R.id.title);

		String readtitle = prefs.getString("title", "");
		// titleedittext.setText(readtitle);
		// backbutoon = (ImageButton) findViewById(R.id.imageButton1);
		// backbutoon.setOnTouchListener(this);
		// savebutton = (ImageButton) findViewById(R.id.imageButton2);
		// savebutton.setOnTouchListener(this);

		// Spinnerの作成
		sp = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		adapter.add("minute");
		adapter.add("hour");
		adapter.add("day");
		int idx = sp.getSelectedItemPosition();
		sp.setAdapter(adapter);

	}

	public boolean onTouch(View v, MotionEvent e) {
		// 戻るボタンが押された時
		// if (v.getId() == R.id.imageButton1) {
		// if (e.getAction() == MotionEvent.ACTION_DOWN) {
		// backbutoon.setImageResource(R.drawable.back_gray);
		// } else if (e.getAction() == MotionEvent.ACTION_UP) {
		// backbutoon.setImageResource(R.drawable.backbt2);
		// finish();
		// }
		// saveボタンが押された時
		// } else if (v.getId() == R.id.imageButton2) {
		// if (e.getAction() == MotionEvent.ACTION_DOWN) {
		// savebutton.setImageResource(R.drawable.checkbt_new);
		// }
		// }
		return true;
	}

	public void makebuttonwotouch(View v) {
		// 文字列を取得してintに変換
		String readkigen = edittext.getText().toString();
		String title = titleedittext.getText().toString();
		int kigen;
		try {
			kigen = Integer.parseInt(readkigen);
		} catch (NumberFormatException ga) {
			kigen = 0;
		}
		// 返すデータ(Intent&Bundle)の作成
		Intent data = new Intent();
		data.putExtra("key.title", title);
		data.putExtra("key.kigen", kigen);

		// Spinnerで選んだ文字をMainActivityに送信
		String item = (String) sp.getSelectedItem();
		data.putExtra("key.time", item);

		// setResult() で bundle を載せた
		// 送るIntent dataをセットする

		// yびb
		// savebutton.setImageResource(R.drawable.checkbt_new);
		// saveのメソッド
		SharedPreferences prefs = getSharedPreferences("Setting", Context.MODE_PRIVATE);
		String saveKigen = edittext.getText().toString();
		String saveTitle = titleedittext.getText().toString();
		// edittextのStringを読み取りsaveKigenに入れる
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("kigen", saveKigen);
		editor.putString("title", saveTitle);

		// saveKigenの値を"kigen"というkey(箱)に入れる
		editor.commit();

		// Taskを保存
		SharedPreferences taskwohozon = getSharedPreferences("taskhozonsuru", Context.MODE_PRIVATE);
		SharedPreferences.Editor henshuu = prefs.edit();
		henshuu.commit();

		// 第一引数は…Activity.RESULT_OK,
		// Activity.RESULT_CANCELED など
		setResult(RESULT_OK, data);
		finish();
	}

	public void cancelbuttonwotouch(View v) {
		finish();
	}
}