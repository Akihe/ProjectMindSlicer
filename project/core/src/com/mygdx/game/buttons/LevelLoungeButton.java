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
        playTexture = new Texture(Gdx.files.internal("levelupbutton.png"));

        setWidth(playTexture.getWidth()/2);
        setHeight(playTexture.getHeight()/2);
        setBounds(265, 400, getWidth()*2, getHeight()*2);

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

