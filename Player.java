import java.util.ArrayList;
import java.util.Iterator;
/**
 * Class Player - player in an adventure game, "Tiana's Adventure Down the Bayou."
 *
 * @author Kara
 * @version 6
 */
public class Player
{
    private Room currentRoom;
    private String print;
    private Room previousRoom;
    private ArrayList <Item> inventory = new ArrayList <Item>();
    
    public Player(){
        currentRoom=null;
    }
    
    public Room getCurrentRoom(){
        return currentRoom;
    }
    public void setCurrentRoom(Room room){
        currentRoom = room;
    }
    public String walk(String direction){
        Room nextRoom = null;

        if(direction == null){
            print = "Go where?" + "\n";
        }
        else{
            if(direction.equalsIgnoreCase("forward")) {
                nextRoom = currentRoom.getExit("forward");
            }
            if(direction.equalsIgnoreCase("backward")) {
                nextRoom = currentRoom.getExit("backward");
            }
            
            if (nextRoom == null) {
                print = "There is no door!";
            }
            else {
                setCurrentRoom(nextRoom);
                getCurrentRoom();
                
                print = "---------------------------------------------------------" + "\n" + getCurrentRoom().getLongDescription();
                
            }
        }
        
        if (nextRoom != null){
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            setCurrentRoom(previousRoom);
            getCurrentRoom();
        }
        
        return print;
    }
    
    public String take(Command command){
        command = command;
        if(!command.hasSecondWord()){
            return "Take what?";
        }
        else{
            ArrayList <Item> itemList = new ArrayList(currentRoom.getItems());
            String itemTaken = "a " + command.getSecondWord();
            Item itemToTake = null;
            for(int x = 0; x < itemList.size(); x++){
                itemToTake = (Item)itemList.get(x);
                if (itemToTake.getItemDescription().equals(itemTaken)){
                    inventory.add(itemToTake);
                    currentRoom.getItems().remove(itemToTake);
                    return "You've taken " + itemToTake.getItemDescription();
                }
            }
        }
        return "This item does not exist.";
    }
    
    public String drop(Command command){
        command = command;
        if(!command.hasSecondWord()){
            return "Drop what?";
        }
        else{
            ArrayList <Item> itemList = new ArrayList(getInventory());
            String itemDropped = "a " + command.getSecondWord();
            Item itemToDrop = null;
            for(int x = 0; x < itemList.size(); x++){
                itemToDrop = (Item)itemList.get(x);
                if (itemToDrop.getItemDescription().equals(itemDropped)){
                    inventory.remove(itemToDrop);
                    currentRoom.getItems().add(itemToDrop);
                    return "You've dropped " + itemToDrop.getItemDescription();
                }
            }
        }
        return "This item does not exist.";
    }
    
    public String seeInventory(){
        String resultString = "";
        resultString = "Inventory: ";
        if (inventory.size() != 0){
            Iterator iter= inventory.iterator();
            while (iter.hasNext()){
                Item nextItem = (Item)iter.next();
                resultString += "\n" + "\t" + nextItem.getItemDescription();
            }
        }
        else{
            resultString += "\n" + "\t" + "no items";
        }
        return resultString;
    }
    
    public ArrayList getInventory(){
        return inventory;
    }
    
}
