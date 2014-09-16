package com.example.original;

import android.app.Activity;
import android.os.Bundle;

public class OptionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option);

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.option);
		// // アイテムを追加
		// adapter.add("1");
		// adapter.add("2");
		// adapter.add("3");
		// ListView listView = (ListView) findViewById(R.id.listView1);
		// // アダプターの設定
		// listView.setAdapter(adapter);
		// listView.setOnItemClickListener(new OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view, int
		// position, long id) {
		// ListView listView = (ListView) parent;
		//
		// // クリックされたアイテムを取得
		// String item = (String) listView.getItemAtPosition(position);
		//
		// }
		// });
		//
		// // リストビューのアイテムが選択された時に呼び出されるコールバック
		// listView.setOnItemSelectedListener(new OnItemSelectedListener() {
		//
		// @Override
		// public void onItemSelected(AdapterView<?> parent, View arg1, int
		// arg2, long arg3) {
		// // TODO Auto-generated method stub
		// ListView listView = (ListView) parent;
		// // 選択されたアイテムの取得
		// String item = (String) listView.getSelectedItem();
		//
		// }
		//
		// @Override
		// public void onNothingSelected(AdapterView<?> arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
	}
}
