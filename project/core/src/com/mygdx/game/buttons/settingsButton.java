package com.mygdx.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.Main;
import com.mygdx.game.screens.*;


public class settingsButton extends Actor {

    private final Texture texture;

    public settingsButton() {

        texture = new Texture(Main.getLevelText("settings"));

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(275, 40, getWidth(), getHeight());

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            mainMenuScreen.setSettingsScreen();
            return true;
        }
    }
}
