import java.util.Scanner;

/**
 * This class is part of the "Tiana's Adventure Down the Bayou" application. 
 * "Tiana's Adventure Down the Bayou" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as a
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Kara
 * @version 6
 */
public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandWords();
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand(String inputLine) 
    {
        
        String word1 = null;
        String word2 = null;
        String [] arrOfStr = inputLine.split(" ");
        
        if (arrOfStr.length == 1){
            word1 = arrOfStr[0];
            word2 = null;
        }
        else{
            word1 = arrOfStr[0];
            word2 = arrOfStr[1];
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }
    
    public String showCommands(){
        return commands.showAll();
    }
}
