package com.mygdx.MindSlicer;

public class defaultValues {

    //Use these when creating a new character
    //Example = int currentPlayerAttack = defaultvalues.playerAttack

    public static int levelInd;
    //index used for player actor to see which level the player is on(for enemy's damage taken)

    //Starting values for the player
    public static int startingMoney = 100;
    public static final int playerDefaultAttack = 25;
    public static final int playerDefaultDefence = 5;

    public static int currentAttack = 25;
    public static int currentDefence = 5;

    public static boolean LoungeEntry = false;
    public static boolean introShown = false;
    public static boolean musicOn = true;

    public static boolean firstSaveDone = false;
    public static boolean level1Defeated = false;
    public static boolean level2Defeated = false;
    public static boolean level3Defeated = false;
}
