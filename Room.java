
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Tiana's Adventure Down the Bayou" application. 
 * "Tiana's Adventure Down the Bayou" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled forward 
 * and backward  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Kara
 * @version 6
 */
public class Room 
{
    public String description;
    public String nameOfRoom;
    private String imageName;
    public boolean open;
    public Room forwardExit;
    public Room backwardExit;
    private HashMap <String, Room> exits = new HashMap <String, Room>();
    private ArrayList <Item> items = new ArrayList <Item>();
    private ArrayList <Characters> characters = new ArrayList <Characters>();
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String nameOfroom, boolean open, String image) 
    {
        this.description = description;
        this.nameOfRoom = nameOfRoom;
        this.open=open;
        imageName = image;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param forward The forward exit.
     * @param backward The backward east.
     * 
     */
    public void setExits(Room forward, Room backward) 
    {
        if(forward != null) {
            forwardExit = forward;
        }
        if(backward != null) {
            backwardExit = backward;
        }
        
        if(forward != null){
            exits.put("forward", forward);
        }
        if(backward != null){
            exits.put("backward", backward);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public String getName(){
        return nameOfRoom;
    }
    
    public boolean getOpen(){
        return open;
    }
    
    public String getExitString(){
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for (String exit : keys){
            returnString += " " + exit;
        }
        return returnString;
    }
    
    public Room getExit(String direction){
        return (Room)exits.get(direction);
    }
    
    public void setExit(String direction, Room neighbor){
        exits.put(direction, neighbor);
    }
    
    public String getLongDescription(){
        String resultString = "You are " + description + ".\n" + getExitString() + "\n ";
        if (items.size() != 0){
            resultString += "This room contains: ";
            
            Iterator iter = items.iterator();
            while (iter.hasNext()){
                Item nextItem = (Item) iter.next();
                resultString += "\n " + "\t" + nextItem.getItemDescription();
            }
        }
        
        String charResultString = "";
        if (characters.size() != 0){
            charResultString = "\n" + "Characters in this room: ";
            Iterator iter= characters.iterator();
            while (iter.hasNext()){
                Characters nextChar = (Characters) iter.next();
                charResultString += "\n" + "\t" + nextChar.getCharacterName();
            }
        }
        resultString += charResultString;
    
        return resultString;
    }
    
    public void addItem(Item item){
        items.add(item);
    }
    
    public String roomContains(){
        String resultString = "";
        if (items.size() != 0){
            resultString = "This room contains: ";
            Iterator iter= items.iterator();
            while (iter.hasNext()){
                Item nextItem = (Item) iter.next();
                if (nextItem.getItemDescription().equals("a dress")){
                    resultString = "here";
                }
                else if (nextItem.getItemDescription().equals("a trumpet")){
                    resultString = "available";
                }
                else if (nextItem.getItemDescription().equals("a lightning-bug")){
                    resultString = "has";
                }
                else if (nextItem.getItemDescription().equals("a gumbo-pot")){
                    resultString = "present";
                }
                else{
                    resultString += "\n" + "\t" + nextItem.toString();
                }
            }
        }
        return resultString;
    }
    
    public void addCharacters(Characters character){
        characters.add(character);
    }
    
    public String meetCharacters(){
        String resultString = "";
        if (characters.size() != 0){
            resultString = "Characters in this room: ";
            Iterator iter= characters.iterator();
            while (iter.hasNext()){
                Characters nextChar = (Characters) iter.next();
                resultString += "\n" + "\t" + nextChar.toString();
            }
        }
        return resultString;
    }
    
    public ArrayList getItems(){
        return items;
    }
    public String getImageName()
    {
        return imageName;
    }
}
