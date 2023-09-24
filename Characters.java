
/**
 * Class Characters - characters in an adventure game
 *
 * This class describes the characters of the "Tiana's Adventure Down the Bayou" application. 
 * "Tiana's Adventure Down the Bayou" is a very simple, text based adventure game.  
 *
 * @author Kara
 * @version 6
 */
public class Characters
{
    private String characterName;
    private String species;

    public Characters(String characterName, String species)
    {
        this.characterName = characterName;
        this.species = species;
    }

    public String getCharacterName(){
        return characterName + " a " + species;
    }
}
