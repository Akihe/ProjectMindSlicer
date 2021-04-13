package com.mygdx.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.screens.*;


public class returnButton extends Actor {

    private final Texture texture;
    private String position;

    public returnButton(float xPos, float yPos, String screen) {
        position = screen;

        texture = new Texture("arrow.png");

        setWidth(texture.getWidth()/2);
        setHeight(texture.getHeight()/2);
        setBounds(xPos, yPos, getWidth(), getHeight());

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (position.equals("Settings")) {
                settings.setMainMenuScreen();
            }
            if (position.equals("LevelSelect")) {
                levelSelect.setMainMenu();
            }
            if (position.equals("LevelUP")) {
                mainMenuScreen.setPlayScreen();
            }
            return true;
        }
    }
}
