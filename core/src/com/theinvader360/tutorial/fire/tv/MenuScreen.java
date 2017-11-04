package com.theinvader360.tutorial.fire.tv;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class MenuScreen extends ScreenAdapter {
    private static final String MENU_OPTION_PLAY = "Play";
    private static final String MENU_OPTION_MORE = "More Games";
    private static final String MENU_OPTION_QUIT = "Quit";
    private MyGdxGame game;
    private Stage stage;
    private Label titleLabel;
    private Array<String> listItems;
    private List<String> listView;

    public MenuScreen(final MyGdxGame game) {
        this.game = game;
        stage = new Stage();
        stage.setViewport(new ScalingViewport(Scaling.fit, 640, 360));
        Table table = new Table();
        table.setFillParent(true);
        titleLabel = new Label("LibGdx FireTV Tutorial by TheInvader360", Assets.skin);
        titleLabel.setColor(Color.GOLD);
        listItems = new Array<String>();
        listView = new List<String>(Assets.skin);
        listView.setAlignment(Align.center);
        listView.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                int selectedIndex = listView.getSelectedIndex();
                if (SimpleInput.isInputMenuUp()) {
                    listView.setSelectedIndex(Math.max(selectedIndex - 1, 0));
                }
                if (SimpleInput.isInputMenuDown()) {
                    listView.setSelectedIndex(Math.min(selectedIndex + 1, listView.getItems().size - 1));
                }
                if (SimpleInput.isInputMenuSelect()) {
                    selected(listView.getSelectedIndex());
                }
                return true;
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selected(listView.getSelectedIndex());
                return true;
            }
        });
        table.add(titleLabel).height(80).align(Align.bottom);
        table.row();
        table.add(listView).expandY().width(300).align(Align.top);
        stage.addActor(table);
    }

    @Override
    public void show() {
        populate();
        listView.setSelectedIndex(0);
        stage.setKeyboardFocus(listView);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    private void populate() {
        listItems.clear();
        listItems.add(MENU_OPTION_PLAY);
        if (!game.isFireTVDevice()) listItems.add(MENU_OPTION_MORE);
        listItems.add(MENU_OPTION_QUIT);
        listView.setItems(listItems);
    }

    private void selected(int index) {
        if (listItems.get(index) == MENU_OPTION_PLAY) {
            game.setScreen(game.gameScreen);
        }
        if (listItems.get(index) == MENU_OPTION_MORE) {
            if (game.isAmazonDevice()) game.actionResolver.openUri("http://www.amazon.com/gp/mas/dl/android?s=com.theinvader360&showAll=1");
            else game.actionResolver.openUri("http://play.google.com/store/apps/developer?id=TheInvader360");
        }
        if (listItems.get(index) == MENU_OPTION_QUIT) {
            Gdx.app.exit();
        }
    }

}
