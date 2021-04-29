package com.mygdx.MindSlicer.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.MindSlicer.Main;
import com.mygdx.MindSlicer.defaultValues;
import com.mygdx.MindSlicer.screens.mainMenuScreen;


public class playButton extends Actor {

    private final Texture texture;

    public playButton() {
        texture = new Texture(Gdx.files.internal(Main.getLevelText("start")));

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(275, 110f, getWidth(), getHeight());

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

        class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            mainMenuScreen.setPlayScreen();
            defaultValues.firstSaveDone = true;
            Main.save();
            return true;
        }
    }
}
