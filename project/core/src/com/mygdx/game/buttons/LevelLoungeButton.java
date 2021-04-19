package com.mygdx.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.screens.*;


public class LevelLoungeButton extends Actor {

    private final Texture playTexture;


    public LevelLoungeButton() {
        playTexture = new Texture(Gdx.files.internal("loungebtn.png"));

        setWidth(playTexture.getWidth());
        setHeight(playTexture.getHeight());
        setBounds(265, 330, getWidth(), getHeight());

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(playTexture, this.getX(), this.getY(), getWidth(), getHeight());

    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            levelSelect.setLevelUP();
            return true;

        }
    }
}

