package com.mygdx.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.screens.levelSelect;
import com.mygdx.game.screens.mainMenuScreen;
import com.mygdx.game.screens.settings;

public class soundOnOff extends Actor {

    boolean musicPlaying;
    boolean fightSounds;

    private final Texture texture;

    public soundOnOff() {
        texture = new Texture("speaker_fullsound2.png");

        setWidth(texture.getWidth()/2);
        setHeight(texture.getHeight()/2);

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return true;
        }
    }
}
