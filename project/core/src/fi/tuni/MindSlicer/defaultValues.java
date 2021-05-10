package fi.tuni.MindSlicer;

/**
 * a class for important values and parameters of the game and the player
 * <p>defaultValues houses many important stats for the game, such as starting stats, updated stats, and booleans, so a popup isn't shown unnecessarily (introShown,loungeEntry, completionShown).
 * has values to track the players progression, and if the music is on.</p>
 */
public class defaultValues {

    //Use these when creating a new character
    //Example = int currentPlayerAttack = defaultvalues.playerAttack

    /**
     * Changed to the value of the current level you are in.
     */
    public static int levelInd;
    //index used for player actor to see which level the player is on(for enemy's damage taken)

    //Starting values for the player

    /**
     * Players starting money amount.
     */
    public static int startingMoney = 100;

    /**
     * Players default attack value.
     */
    public static final int playerDefaultAttack = 25;

    /**
     * Players default defence value.
     */
    public static final int playerDefaultDefence = 5;

    /**
     * Players current attack value.
     */
    public static int currentAttack = 25;

    /**
     * Players current defence value.
     */
    public static int currentDefence = 5;

    /**
     * Boolean that tells have you visited the lounge yet.
     */
    public static boolean loungeEntry = false;

    /**
     * Boolean that tells have you seen the intro popup.
     */
    public static boolean introShown = false;

    /**
     * Boolean that tells if youve seen the game completed window.
     */
    public static boolean completionShown = false;

    /**
     * Boolean that tells if the music is on or off.
     */
    public static boolean musicOn = true;

    /**
     * Tells if the game has been saved the first time.
     */
    public static boolean firstSaveDone = false;

    /**
     * Tells has the player defeated level 1.
     */
    public static boolean level1Defeated = false;

    /**
     * Tells has the player defeated level 2.
     */
    public static boolean level2Defeated = false;

    /**
     * Tells has the player defeated level 3.
     */
    public static boolean level3Defeated = false;
}
