package com.theinvader360.tutorial.fire.tv;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Assets {
    public static Skin skin;

    public static void load() {
        skin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        Drawable drawableClear = skin.newDrawable("white", Color.CLEAR);

        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);

        List.ListStyle listStyle = new List.ListStyle();
        listStyle.font = skin.getFont("default");
        listStyle.fontColorSelected = Color.GREEN;
        listStyle.background = drawableClear;
        listStyle.selection = drawableClear;
        skin.add("default", listStyle);
    }

    public static void dispose() {
        skin.dispose();
    }

}
