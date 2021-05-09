package com.mygdx.game;

public class defaultValues {

    //Use these when creating a new character
    //Example = int currentPlayerAttack = defaultvalues.playerAttack;

    public static int levelInd;
    //index used for player actor to see which level the player is on(for enemy's damage taken)

    //Starting values for the player
    public static int startingMoney = 100;
    public static final int playerDefaultAttack = 20;
    public static final int playerDefaultDefence = 5;

    public static int currentAttack = 20;
    public static int currentDefence = 5;

    //Starting values for Level 1 monster
    public static int monsterAttack = 15;
    public static int monsterDefence = 15;

    public static boolean LoungeEntry = false;
    public static boolean introShown = false;
    public static boolean musicOn = true;

    public static boolean level1Defeated = false;
    public static boolean level2Defeated = false;
    public static boolean level3Defeated = false;
}
