package com.mygdx.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.*;
import com.mygdx.game.screens.*;



public class playButton extends Actor {

    private final Texture texture;

    public playButton() {
        texture = new Texture(Gdx.files.internal("start.png"));

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(275, 270f, getWidth(), getHeight());

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

        class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            mainMenuScreen.setPlayScreen();
            return true;
        }
    }
}
