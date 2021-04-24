package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class turnTeller extends Actor {

    Skin skin;
    Label playerTurn;
    Label enemyTurn;

    public turnTeller(Skin getSkin) {
        skin = getSkin;
        playerTurn = new Label("Its your turn!", skin);
        enemyTurn = new Label("Its the enemies turn!", skin);
    }






}
