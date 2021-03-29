package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class LevelLoungeButton extends Actor {

    private final Texture playTexture;


    public LevelLoungeButton() {
        playTexture = new Texture("Garmfiel.png");

        setWidth(playTexture.getWidth()/2);
        setHeight(playTexture.getHeight()/2);
        setBounds(400, 350, getWidth(), getHeight());

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

