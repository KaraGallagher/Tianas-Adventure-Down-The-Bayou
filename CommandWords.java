/**
 * This class is part of the "Tiana's Adventure Down the Bayou" application. 
 * "Tiana's Adventure Down the Bayou" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Kara
 * @version 6
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "talk","take", "drop", "inventory", "sew", "add", "hint","order", "guess", "kiss", "turn", "show", "eat", "answer", "stir", "fight", "marry"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    public String showAll(){
        String command = " ";
        for(int x=0; x<validCommands.length; x++){
            command += validCommands[x] + " ";
        }
        return command;
    }
}
