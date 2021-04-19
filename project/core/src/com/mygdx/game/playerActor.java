package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.screens.*;


public class playerActor extends Actor {

    private Texture playerTexture;
    private Texture hitTexture;
    private Texture recentTexture;

    public int PLAYER_HEALTH = 100;
    private String healthAmount;

    public static int PLAYER_ATK;

    public static int MONEY = defaultValues.startingMoney;
    public int enemyAttacksAfter;
    public static int PLAYER_DEF;
    public static boolean shield_ON = false;
    public static boolean playerActionDone = false;

    public static int statPointsBought = 0;

    public playerActor() {
        playerTexture = new Texture(Gdx.files.internal("playercharacter.png"));
        healthAmount = "" + PLAYER_HEALTH;

        PLAYER_ATK = defaultValues.currentAttack;
        PLAYER_DEF = defaultValues.currentDefence;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());
        setBounds(100,40, getWidth(), getHeight());

    }

    public void updateStats(){
        PLAYER_ATK = defaultValues.currentAttack;
        PLAYER_DEF = defaultValues.currentDefence;
    }

    public void resetStats(){
        PLAYER_ATK = defaultValues.playerAttack;
    }
//Liitetty reducehealth metodi pelaajalle myös. Voiko käyttää samaa metodia jos sen tekee uuteen luokkaan, ja kutsuu arvoja mm this.health (täytyy tehdä olio-ohjelmoinnilla parent olio, jolla on attribbutti HEALTH)
    public void reduceHealth(int damageTaken) {
        if (shield_ON){
            damageTaken = damageTaken / 3;
            shield_ON = false;
        }
        int totalDamage = PLAYER_DEF-damageTaken;
        if(totalDamage >= 0){
            totalDamage = -1;

        }
        this.PLAYER_HEALTH = this.PLAYER_HEALTH + totalDamage;
        healthAmount = "" + PLAYER_HEALTH;

    }


    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(playerTexture,
                this.getX(), this.getY(),
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation(),0,0,
                playerTexture.getWidth(), playerTexture.getHeight(), false, false);

        Main.font.draw(batch, healthAmount, 50, 30);
    }

    public void hitAction() {
        hitTexture = new Texture("thumbs_up.png");
        recentTexture = playerTexture;
        playerTexture = hitTexture;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());

        level1.enemy.reduceHealth(PLAYER_ATK);

        playerActionDone = true;
        enemyActor.allowPlayerAttack = false;
        enemyAttacksAfter = 4;
    }

    public void superShield(){
        hitTexture = new Texture("main_def.png");
        recentTexture = playerTexture;
        playerTexture = hitTexture;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());

        shield_ON = true;

        playerActionDone = true;
        enemyAttacksAfter = 3;
    }

    public void MunkkiHeal(){
        hitTexture = new Texture("sweet_health_buff.png");
        recentTexture = playerTexture;
        playerTexture = hitTexture;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());

        PLAYER_HEALTH = PLAYER_HEALTH + 25;
        playerActionDone = true;
        enemyAttacksAfter = 3;
    }
    public void resetPlayer() {
            playerTexture = recentTexture;
            setWidth(playerTexture.getWidth());
            setHeight(playerTexture.getHeight());
    }

    public void thinkAction() {
        hitTexture = new Texture("drink_coffee_buff1.png");
        recentTexture = playerTexture;
        playerTexture = hitTexture;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());

        double ATK_RISE = PLAYER_ATK * 1.5;
        PLAYER_ATK = (int) ATK_RISE;
        playerActionDone = true;
        enemyAttacksAfter = 3;
    }

}
