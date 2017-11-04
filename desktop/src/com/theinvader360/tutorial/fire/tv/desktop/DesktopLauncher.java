package com.theinvader360.tutorial.fire.tv.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.theinvader360.tutorial.fire.tv.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 360;
		new LwjglApplication(new MyGdxGame(new ActionResolverDesktop(), isAmazonDevice(), isAmazonFireTVDevice()), config);
	}

	private static boolean isAmazonDevice() {
		return false;
	}

	private static boolean isAmazonFireTVDevice() {
		return false;
	}

}
