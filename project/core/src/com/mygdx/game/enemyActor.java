package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class enemyActor extends Actor {

    private Texture enemyTexture;

    private int ENEMY_HEALTH = 100;
    private String healthAmount;

    public enemyActor() {
        enemyTexture = new Texture(Gdx.files.internal("kaljanhimo.png"));

        setWidth(enemyTexture.getWidth()/2);
        setHeight(enemyTexture.getHeight()/2);
        setBounds(600,40, getWidth(), getHeight());

        healthAmount = "" + ENEMY_HEALTH;
    }

    public void reduceHealth(int damageTaken) {
        this.ENEMY_HEALTH = this.ENEMY_HEALTH - damageTaken;
        Gdx.app.log("Enemy class", " HP is " + ENEMY_HEALTH);
        healthAmount = "" + ENEMY_HEALTH;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(enemyTexture,
                this.getX(), this.getY(),
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation(),0,0,
                enemyTexture.getWidth(), enemyTexture.getHeight(), false, false);
        Main.font.draw(Main.getBatch(), healthAmount, 660, 30);
    }


}
