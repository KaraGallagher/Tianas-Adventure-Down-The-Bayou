/**
 *  
 * This class creates an instance of the GameEngine class, which is the main class of
 * "Tiana's Adventure Down the Bayou." Users use the Game class to start the game.
 * 
 * @author  Kara
 * @version 6
 */

public class Game
{
    private UserInterface gui;
    private GameEngine engine;

    /**
     * Create the game and initialise its internal map.
     */
    //You can either leave this as a constructor or change
    //the constructor signature to the signature of the main method.
    public Game() 
    {
        engine = new GameEngine();
        gui = new UserInterface(engine);
        engine.setGUI(gui);
    }
}
