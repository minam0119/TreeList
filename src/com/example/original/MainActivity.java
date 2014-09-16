package com.example.original;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	// 今見えてる葉っぱの数
	int i;
	// 制限時間
	int timelimit;
	// 葉っぱの画像をカウントするための変数
	int happagazouwocount;

	// 葉っぱの情報を保存するための配列
	Leaf[] leafs;

	// 葉っぱの画像を表示するためのImageView
	ImageView[] gazous;

	TextView[] wakariyasui;

	// 一定時間ごとに葉っぱの画像を変えるためのTimer
	Handler h;
	Timer timer;
	TimerTask timerTask;

	TextView wakariyasukusurutext;

	// 通知
	private NotificationManager mNotificationmanager;
	private int NOTFICATION_ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// いろんな変数を初期化
		leafs = new Leaf[9];
		gazous = new ImageView[9];
		wakariyasui = new TextView[9];
		h = new Handler();

		// ImageViewのidを取ってくる
		for (int i = 0; i < gazous.length; i++) {
			int id = getResources().getIdentifier("happa" + (i + 1), "id", getPackageName());
			gazous[i] = (ImageView) findViewById(id);
		}

		Log.d("わかりやすい配列", "wakariyasui:" + wakariyasui.length);
		// TextViewのidを取ってくる
		for (int i = 0; i < wakariyasui.length; i++) {
			int id = getResources().getIdentifier("textView" + (i + 1), "id", getPackageName());
			wakariyasui[i] = (TextView) findViewById(id);
		}

		Log.d("分かりやすくする", "text:" + leafs[i]);

		// mNotificationmanager =
		// (NotificationManager)getSystemService(NOTIFICATION SERVICE);
		// final Notification notifyDetails = new
		// Notification(R.drawable.icon_original,"葉が黄色くなりました。",System.currentTimeMillis());
	}

	public void info(View v) {
		if (gazous.length > i) {
			// 葉っぱを増やすためのアクティビティに飛ぶ
			Intent intent = new Intent(this, LeafActivity.class);
			startActivityForResult(intent, 111);
		}
	}

	public void optionView(View v) {
		// オプション画面へ飛ぶ
		Intent intent = new Intent(this, OptionActivity.class);
		startActivity(intent);
	}

	public void happawotouch(View v) {
		// カスタムビューの設定
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.dialog, null);

		// dialog画面のTextViewの関連付け
		TextView nameText = (TextView) layout.findViewById(R.id.dialog_textview_name);
		TextView kigenText = (TextView) layout.findViewById(R.id.dialog_textview_kigen);
		TextView tannitext = (TextView) layout.findViewById(R.id.tannitext);
		TextView timerText = (TextView) layout.findViewById(R.id.dialog_textview_count);

		// int number = 0;

		// 画像のIDの取得
		for (int i = 0; i < gazous.length; i++) {
			if (gazous[i].getId() == v.getId()) {
				// number = i;

				Time time = new Time("Asia/Tokyo");
				time.setToNow();

				// long s = leafs[i].startTime.toMillis(false);
				// long e = time.toMillis(false);
				//
				// leafs[i].timernosuuti = (e - s) / 1000;
				// leafs[i].timernosuuti = (e - s);
				// if (leafs[i].timernosuuti < leafs[i].kigen) {
				// leafs[i].nokorinotime = leafs[i].kigen -
				// leafs[i].timernosuuti;
				// // System.out.println("kigen: " + leafs[i].kigen);
				// // System.out.println("nokori: " + leafs[i].nokorinotime);
				// }

				// テキストにセット
				// Time time = leafs[i].kigen;
				// String date = time.year + "年" + (time.month + 1) + "月"
				// + time.monthDay + "日" + time.hour + "時" + time.minute
				// + "分" + time.second + "秒";

				nameText.setText(leafs[i].title);
				kigenText.setText(String.valueOf(leafs[i].kigen));
				tannitext.setText(leafs[i].tanni);
				timerText.setText(String.valueOf(leafs[i].timernosuuti));
			}
		}

		// アラートログの生成
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("");
		builder.setView(layout);

		// 表示
		final AlertDialog dialog = builder.show();

		// final int number2 = number;
		// FinishButtonの設定
		Button button = (Button) layout.findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// timerをストップ
				// leafs[number2].timer.cancel();
				// leafs[number2].endTime = new Time("Asia/Tokyo");
				// leafs[number2].endTime.setToNow();
			}
		});

		// CancelButtonの設定
		ImageView cancelbutton = (ImageView) layout.findViewById(R.id.cancel);
		cancelbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ダイアログを消す
				dialog.cancel();
			}
		});
	}

	// 数学ボタンを押したときの処理
	public void math(View v) {
		for (int i = 0; i < leafs.length; i++) {
			Leaf leaf = leafs[i];
			if (leafs[i] == null || !leaf.title.contains("数学") || leaf.title.contains("すうがく")
					|| leaf.title.contains("MATH") || leaf.title.contains("math") || leaf.title.contains("Math")
					|| leaf.title.contains("算数") || leaf.title.contains("さんすう")) {
				gazous[i].setVisibility(View.INVISIBLE);
			} else {
				gazous[i].setVisibility(View.VISIBLE);
			}
		}

		// 透明度をいじる
		/*
		 * ImageView imageView = new ImageView(this);
		 * imageView.setImageResource(R.drawable.green2);
		 * imageView.setAlpha(100); setContentView(imageView, new
		 * LayoutParams(WC,WC));
		 */
	}

	// 英語ボタンを押したときの処理
	public void english(View v) {
		for (int i = 0; i < leafs.length; i++) {
			Leaf leaf = leafs[i];

			if (leafs[i] == null || !leaf.title.contains("英語") || leaf.title.contains("えいご")
					|| leaf.title.contains("ENGLISH") || leaf.title.contains("english")
					|| leaf.title.contains("English")) {
				gazous[i].setVisibility(View.INVISIBLE);
			} else {
				gazous[i].setVisibility(View.VISIBLE);
			}
		}
	}

	// 理科ボタンを押したときの処理
	public void science(View v) {
		for (int i = 0; i < leafs.length; i++) {
			Leaf leaf = leafs[i];
			if (leafs[i] == null || !leaf.title.contains("理科") || leaf.title.contains("りか")
					|| leaf.title.contains("SCIENCE") || leaf.title.contains("science")
					|| leaf.title.contains("Science")) {
				gazous[i].setVisibility(View.INVISIBLE);
			} else {
				gazous[i].setVisibility(View.VISIBLE);
			}
		}

	}

	// 社会ボタンを押したときの処理
	public void social(View v) {
		for (int i = 0; i < leafs.length; i++) {
			Leaf leaf = leafs[i];
			if (leafs[i] == null || !leaf.title.contains("社会") || leaf.title.contains("しゃかい")
					|| leaf.title.contains("SOCIAL STUDIES") || leaf.title.contains("social studies")
					|| leaf.title.contains("Social studies") || leaf.title.contains("social")
					|| leaf.title.contains("Social") || leaf.title.contains("SOCIAL")) {
				gazous[i].setVisibility(View.INVISIBLE);
			} else {
				gazous[i].setVisibility(View.VISIBLE);
			}
		}

	}

	// 国語ボタンを押したときの処理
	public void japanese(View v) {
		for (int i = 0; i < leafs.length; i++) {
			Leaf leaf = leafs[i];
			if (leafs[i] == null || !leaf.title.contains("国語") || leaf.title.contains("こくご")
					|| leaf.title.contains("JAPANESE") || leaf.title.contains("japanese")
					|| leaf.title.contains("Japanese")) {
				gazous[i].setVisibility(View.INVISIBLE);
			} else {
				gazous[i].setVisibility(View.VISIBLE);
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 111:
			if (resultCode == RESULT_OK) {

				String title = data.getStringExtra("key.title");
				int limit = data.getIntExtra("key.kigen", 0);

				// 葉っぱの情報を配列のi番目に保存
				leafs[i] = new Leaf(title, limit, data.getStringExtra("key.time"), gazous[i]);

				// 現在時刻の取得
				leafs[i].startTime = new Time("Asia/Tokyo");
				leafs[i].startTime.setToNow();

				Leaf leaf = leafs[i];

				// 分かりやすくするtextの関連付け
				wakariyasui[i].setText(leafs[i].title);
				wakariyasui[i].setTextColor(Color.WHITE);

				if (!leaf.title.equals("") && leaf.kigen != 0) {

					// 葉っぱが見えるようになる
					gazous[i].setVisibility(View.VISIBLE);

					// 数学のワード
					if (title.contains("数学") || title.contains("すうがく") || title.contains("MATH")
							|| title.contains("math") || title.contains("Math") || title.contains("算数")
							|| title.contains("さんすう")) {

						if (i == 0) {
							gazous[i].setImageResource(R.drawable.green2_blue);

						} else if (i == 1) {
							gazous[i].setImageResource(R.drawable.greenrr_math);

						} else if (i == 2) {
							gazous[i].setImageResource(R.drawable.green2_blue);

						} else if (i == 3) {
							gazous[i].setImageResource(R.drawable.green2_blue);

						} else if (i == 4) {
							gazous[i].setImageResource(R.drawable.green1_math);

						} else if (i == 5) {
							gazous[i].setImageResource(R.drawable.green2_blue);

						} else if (i == 6) {
							gazous[i].setImageResource(R.drawable.greenrr_math);

						} else if (i == 7) {
							gazous[i].setImageResource(R.drawable.greenrd_math);

						} else if (i == 8) {
							gazous[i].setImageResource(R.drawable.greenll_math);

						}
					}

					// 国語のワード
					if (title.contains("国語") || title.contains("こくご") || title.contains("JAPANESE")
							|| title.contains("japanese") || title.contains("Japanese")) {

						if (i == 0) {
							gazous[i].setImageResource(R.drawable.green2_japanese);

						} else if (i == 1) {
							gazous[i].setImageResource(R.drawable.greenrr_japanese);

						} else if (i == 2) {
							gazous[i].setImageResource(R.drawable.green2_japanese);

						} else if (i == 3) {
							gazous[i].setImageResource(R.drawable.green2_japanese);

						} else if (i == 4) {
							gazous[i].setImageResource(R.drawable.green1_japanese);

						} else if (i == 5) {
							gazous[i].setImageResource(R.drawable.green2_japanese);

						} else if (i == 6) {
							gazous[i].setImageResource(R.drawable.greenrr_japanese);

						} else if (i == 7) {
							gazous[i].setImageResource(R.drawable.greenrd_japanese);

						} else if (i == 8) {
							gazous[i].setImageResource(R.drawable.greenll_japanese);

						}
					}

					// 英語のワード
					if (title.contains("英語") || title.contains("えいご") || title.contains("ENGLISH")
							|| title.contains("english") || title.contains("English")) {

						if (i == 0) {
							gazous[i].setImageResource(R.drawable.green2_english);

						} else if (i == 1) {
							gazous[i].setImageResource(R.drawable.greenrr_english);

						} else if (i == 2) {
							gazous[i].setImageResource(R.drawable.green2_english);

						} else if (i == 3) {
							gazous[i].setImageResource(R.drawable.green2_english);

						} else if (i == 4) {
							gazous[i].setImageResource(R.drawable.green1_english);

						} else if (i == 5) {
							gazous[i].setImageResource(R.drawable.green2_english);

						} else if (i == 6) {
							gazous[i].setImageResource(R.drawable.greenrr_english);

						} else if (i == 7) {
							gazous[i].setImageResource(R.drawable.greenrd_english);

						} else if (i == 8) {
							gazous[i].setImageResource(R.drawable.greenll_english);

						}
					}

					// 理科のワード
					if (title.contains("理科") || title.contains("りか") || title.contains("SCIENCE")
							|| title.contains("science") || title.contains("Science")) {

						if (i == 0) {
							gazous[i].setImageResource(R.drawable.green2_science);

						} else if (i == 1) {
							gazous[i].setImageResource(R.drawable.greenrr_science);

						} else if (i == 2) {
							gazous[i].setImageResource(R.drawable.green2_science);

						} else if (i == 3) {
							gazous[i].setImageResource(R.drawable.green2_science);

						} else if (i == 4) {
							gazous[i].setImageResource(R.drawable.green1_science);

						} else if (i == 5) {
							gazous[i].setImageResource(R.drawable.green2_science);

						} else if (i == 6) {
							gazous[i].setImageResource(R.drawable.greenrr_science);

						} else if (i == 7) {
							gazous[i].setImageResource(R.drawable.greenrd_science);

						} else if (i == 8) {
							gazous[i].setImageResource(R.drawable.greenll_science);

						}
					}

					// 社会のワード
					if (title.contains("社会") || title.contains("しゃかい") || title.contains("SOCIAL STUDIES")
							|| title.contains("social studies") || title.contains("Social studies")
							|| title.contains("social") || title.contains("Social") || title.contains("SOCIAL")) {

						if (i == 0) {
							gazous[i].setImageResource(R.drawable.green2_social);

						} else if (i == 1) {
							gazous[i].setImageResource(R.drawable.greenrr_social);

						} else if (i == 2) {
							gazous[i].setImageResource(R.drawable.green2_social);

						} else if (i == 3) {
							gazous[i].setImageResource(R.drawable.green2_social);

						} else if (i == 4) {
							gazous[i].setImageResource(R.drawable.green1_social);

						} else if (i == 5) {
							gazous[i].setImageResource(R.drawable.green2_social);

						} else if (i == 6) {
							gazous[i].setImageResource(R.drawable.greenrr_social);

						} else if (i == 7) {
							gazous[i].setImageResource(R.drawable.greenrd_social);

						} else if (i == 8) {
							gazous[i].setImageResource(R.drawable.greenll_social);

						}
					}

					// 葉の色が黄色になる時の方向を設定
					if (i == 0) {
						leafs[i].start(R.drawable.yellow2, R.drawable.kareha2);

					} else if (i == 1) {
						leafs[i].start(R.drawable.yellow_rr, R.drawable.kareha1);

					} else if (i == 2) {
						leafs[i].start(R.drawable.yellow2, R.drawable.kareha2);

					} else if (i == 3) {
						leafs[i].start(R.drawable.yellow2, R.drawable.kareha2);

					} else if (i == 4) {
						leafs[i].start(R.drawable.yellow1, R.drawable.kareha1);

					} else if (i == 5) {
						leafs[i].start(R.drawable.yellow2, R.drawable.kareha2);

					} else if (i == 6) {
						leafs[i].start(R.drawable.yellow_rr, R.drawable.kareha1);

					} else if (i == 7) {
						leafs[i].start(R.drawable.yellow_rd, R.drawable.kareha_rd);

					} else if (i == 8) {
						leafs[i].start(R.drawable.yellow_ll, R.drawable.kareha2);
					}

					// kigentext.setText(kigen);

					// finish() で終わらせて
					// Intent data を送る
					// finish();

					// 葉を枯葉にする
					if (i == 0) {
						leafs[i].kareru(R.drawable.kareha2);

					} else if (i == 1) {
						leafs[i].kareru(R.drawable.kareha1);

					} else if (i == 2) {
						leafs[i].kareru(R.drawable.kareha2);

					} else if (i == 3) {
						leafs[i].kareru(R.drawable.kareha2);

					} else if (i == 4) {
						leafs[i].kareru(R.drawable.kareha1);

					} else if (i == 5) {
						leafs[i].kareru(R.drawable.kareha2);

					} else if (i == 6) {
						leafs[i].kareru(R.drawable.kareha1);

					} else if (i == 7) {
						leafs[i].kareru(R.drawable.kareha_rd);

					} else if (i == 8) {
						leafs[i].kareru(R.drawable.kareha2);
					}

					i++;
				}

			} else if (resultCode == RESULT_CANCELED) {

			}
			break;

		default:
			break;

		}
	}
}
