package com.example.original;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.text.format.Time;
import android.widget.ImageView;

//葉っぱのクラスを作って予定と日程を管理
public class Leaf {
	public String title;
	public int kigen;
	public String tanni;

	Time startTime, endTime;

	Handler h = new Handler();
	Timer timer;
	TimerTask timerTask;

	ImageView imageView;

	int nokorinotime;

	// Timerの数値
	int timernosuuti;

	Leaf(String title, int kigen, String tanni, ImageView imageView) {
		this.title = title;
		this.kigen = kigen;
		this.tanni = tanni;
		this.imageView = imageView;

		// if (tanni.equals("hour")) {
		// this.kigen = kigen * 60 * 60;
		// } else if (tanni.equals("minute")) {
		// this.kigen = kigen * 60;
		// } else if (tanni.equals("day")) {
		// this.kigen = kigen * 60 * 60 * 24;
		// }
		// }
		//
		// public String getKigen() {
		// if (tanni.equals("hour")) {
		// return String.valueOf(kigen / 60 / 60);
		// } else if (tanni.equals("minute")) {
		// return String.valueOf(kigen / 60);
		// } else if (tanni.equals("day")) {
		// return String.valueOf(kigen / 60 / 60 / 24);
		// } else {
		// return String.valueOf("");
		// }
		//
		// }
		//
		// public String nokorinoyatu() {
		// if (tanni.equals("hour")) {
		// return String.valueOf(kigen / 60 / 60);
		// } else if (tanni.equals("minute")) {
		// return String.valueOf(kigen / 60);
		// } else if (tanni.equals("day")) {
		// return String.valueOf(kigen / 60 / 60 / 24);
		// } else {
		// return String.valueOf("");
		// }
	}

	public void start(final int resourceId, final int kareta) {
		// timer
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				h.post(new Runnable() {

					@Override
					public void run() {

						// 葉っぱの画像を黄色にする
						imageView.setImageResource(resourceId);
						kareru(kareta);
					}
				});
			}
		};

		// 分単位にする
		// 6割を越したら黄色
		if ("minute".equals(tanni)) {
			timer.schedule(timerTask, (long) (kigen * 60 * 1000 * 0.3));
		} else if ("hour".equals(tanni)) {
			timer.schedule(timerTask, (long) (kigen * 60 * 60 * 1000 * 0.6));
		} else if ("day".equals(tanni)) {
			timer.schedule(timerTask, (long) (kigen * 60 * 60 * 24 * 1000 * 0.6));
		}
	}

	public void kareru(final int resourceId) {
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				h.post(new Runnable() {

					@Override
					public void run() {

						// 葉っぱの画像を黄色にする
						imageView.setImageResource(resourceId);
					}
				});
			}
		};

		// 分単位にする
		// 時間を過ぎたら枯葉になる
		if ("minute".equals(tanni)) {
			timer.schedule(timerTask, (long) (kigen * 60 * 1000 * 0.6));
		} else if ("hour".equals(tanni)) {
			timer.schedule(timerTask, (long) (kigen * 60 * 60 * 1000 * 1.0));
		} else if ("day".equals(tanni)) {
			timer.schedule(timerTask, (long) (kigen * 60 * 60 * 24 * 1000 * 1.0));
		}
	}
}
