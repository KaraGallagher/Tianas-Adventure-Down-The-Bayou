
/**
 * Class Item - items in an adventure game
 * 
 * This class creates descriptions of items in "Tiana's Adventure Down the Bayou" application. 
 * "Tiana's Adventure Down the Bayou" is a very simple, text based adventure game.  
 *
 * @author Kara
 * @version 6
 */
public class Item
{
    private String description;

    public Item(String description)
    {
        this.description = description;
    }

    public String getItemDescription(){
        return "a " + description;
    }

}
