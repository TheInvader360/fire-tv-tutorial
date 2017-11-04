package com.theinvader360.tutorial.fire.tv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements ActionResolver {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		config.useRotationVectorSensor = false;
		initialize(new MyGdxGame(this, isAmazonDevice(), isAmazonFireTVDevice()), config);
	}

	private boolean isAmazonDevice() {
		return android.os.Build.MANUFACTURER.equalsIgnoreCase("amazon");
	}

	private boolean isAmazonFireTVDevice() {
		return android.os.Build.MODEL.contains("AFT");
	}

	public void openUri(String uri) {
		Uri myUri = Uri.parse(uri);
		Intent intent = new Intent(Intent.ACTION_VIEW, myUri);
		startActivity(intent);
	}

}
