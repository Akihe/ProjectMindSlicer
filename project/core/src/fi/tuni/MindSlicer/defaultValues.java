package fi.tuni.MindSlicer;

/**
 * a class for important values and parameters of the game and the player
 * <p>defaultValues houses many important stats for the game, such as starting stats, updated stats, and booleans, so a popup isn't shown unnecessarily (introShown,loungeEntry, completionShown).
 * has values to track the players progression, and if the music is on.</p>
 */
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

    public static boolean loungeEntry = false;
    public static boolean introShown = false;
    public static boolean completionShown = false;

    public static boolean musicOn = true;

    public static boolean firstSaveDone = false;

    public static boolean level1Defeated = false;
    public static boolean level2Defeated = false;
    public static boolean level3Defeated = false;
}
