import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
/**
 *  This class is the main class of the "Tiana's Adventure Down the Bayou" application. 
 *  "Tiana's Adventure Down the Bayou" is a very simple, text based adventure game.  Users 
 *  play as Tiana and work hard to be able to open their very own restaurant.
 *  Users will face challenges, meet new friends, and battle enemies to make it to the end.
 * 
 *  To play this game, users create an instance of this class in the Game
 *  class.
 * 
 *  This main class creates all rooms and the parser.
 *  It also evaluates and executes the commands
 *  that the parser returns. It controls when players can or cannot
 *  move on to the next room as well.
 * 
 * @author  Kara
 * @version 6
 */

public class GameEngine 
{
    private Parser parser;
    private Room nextRoom;
    private Player player;
    private UserInterface gui;
    private boolean dressGuessed;
    private int counter=0;
    private int attempts=0;
    private int turn = 0;
    Random randomGenerator = new Random();
    int randomColor = randomGenerator.nextInt(9);
    int min=10;
    int max=50;
    int randomShakes = randomGenerator.nextInt(max-min+1)+min;
    private int movedOn = 0;
    private boolean recipeGuessed;
    private boolean hintActivated = false;
    private int canUseHint = 0;
    private boolean canMoveToLocation2 = false;
    private boolean canMoveToLocation3 = false;
    private boolean canMoveToLocation8 = false;
    int randomMenu = randomGenerator.nextInt(5);
    private boolean menuGuessed;
    private boolean remember = false;
    private boolean hadToGoBack = false;
    private boolean hadToGoBack2 = false;
    private boolean hadToGoBack3 = false;
    private boolean kissed = false;
    private boolean talkNaveen = false;
    int directionOne = randomGenerator.nextInt(2);
    int directionTwo = randomGenerator.nextInt(2);
    int directionThree = randomGenerator.nextInt(2);
    private boolean escaped = false;
    private int chaseGuessed = 0;
    private boolean canShow = false;
    String options[] = {"R", "L"};
    String flies[] = new String[10];
    int randomFly1 = randomGenerator.nextInt(10);
    int randomFly2 = randomGenerator.nextInt(10);
    int randomFly3 = randomGenerator.nextInt(10);
    private int tries = 0;
    private int fliesEaten = 0;
    private boolean canTalkToLouis = false;
    private boolean canTakeTrumpet = false;
    private int guessCount = 0;
    private boolean canTakeRay = false;
    private boolean canGoToMama = false;
    private boolean riddle2Activated = false;
    private boolean riddleTwo = false;
    private boolean firstTimeHere = true;
    private boolean canMoveToRiverboat = false;
    private boolean firstTimeInRiverboat = true;
    private boolean canAdvanceToParade = false;
    private boolean firstTimeInParade = true;
    private boolean printYes = true;
    private boolean canGoToAlley = false;
    private boolean firstTimeTaking = true;
    private boolean firstTime = true;
    private boolean fight1 = true;
    int min3=10;
    int max3=100;
    int numOfSpirits = randomGenerator.nextInt(max3-min3+1)+min3;
    private boolean canOpenRestaurant = false;
    private boolean canTakeTalisman = false;
    private boolean canMarry = false;
    private boolean firstTimeInRestaurant = true;
    private boolean canMoveOnHere = false;
    private int first = 0;
    private boolean firstDirection = true;
            
    String colors[] = {"Alizarian-Crimson", "Bright-Red", "Cad-Yellow", "Dark-Sienna", "Midnight-Black", "Sap-Green", "Titanium-White", "Van-Dyke-Brown", "Gesin-Turquoise"};
    
    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
        dressGuessed = false;
    }
    
    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room mansion, home, restaurant, streets, ball, bedroom, louis, mama, ray, riverboat, parade, alley, place;
      
        // create the rooms
        mansion = new Room("at your best friend Charlotte's mansion", "mansion", true, "bedroomImage.jpg");
        home = new Room("at your house", "home", true, "homeImage.jpg");
        streets = new Room("out and about in the streets of New Orleans", "streets", true, "streetsImage.jpg");
        restaurant = new Room("at the restaurant where you work", "restaurant", true, "restaurantImage.jpg");
        ball = new Room("at a ball hosted by Charlotte's family", "ball", true, "ballImage.jpg");
        bedroom = new Room("on the balcony attached to Charlotte's bedroom", "bedroom", true, "balconyImage.jpg");
        louis = new Room("in a new section of the bayou filled with alligators", "louis", true, "louisImage.jpg");
        mama = new Room("at a house in a tree in the bayou", "mama", true, "mamaImage.jpg");
        ray = new Room("in a new section of the bayou filled with lightning bugs", "ray", true, "rayImage.jpg");
        riverboat = new Room("on a riverboat celebrating Mardi Gras", "riverboat", true, "riverboatImage.jpg");
        parade = new Room("at the Mardi Gras parade", "parade", true, "paradeImage.jpg");
        alley = new Room("in a dark alley", "alley", true, "alleyImage.jpg");
        place = new Room("in your very own restaurant, Tiana's Place", "place", true, "placeImage.jpg");
        
        // initialise room exits
        mansion.setExits(home, null);
        home.setExits(streets, mansion);
        streets.setExits(restaurant, home);
        restaurant.setExits(ball, streets);
        ball.setExits(bedroom, restaurant);
        bedroom.setExits(louis, ball);
        louis.setExits(ray, bedroom);
        ray.setExits(mama, louis);
        mama.setExits(riverboat, ray);
        riverboat.setExits(parade, mama);
        parade.setExits(alley, riverboat);
        alley.setExits(place, parade);
        place.setExits(null, alley);
        

        player.setCurrentRoom(mansion);
        
        //add items
        mansion.addItem(new Item("dress"));
        home.addItem(new Item("gumbo-pot"));
        louis.addItem(new Item("trumpet"));
        ray.addItem(new Item("lightning-bug"));
        alley.addItem(new Item("talisman"));
        
        bedroom.addCharacters(new Characters("Naveen", "frog"));
        louis.addCharacters(new Characters("Louis", "alligator"));
        riverboat.addCharacters(new Characters("Evangeline", "star"));
        mama.addCharacters(new Characters("Mama-Odie", "voodoo priestess"));
        alley.addCharacters(new Characters("Doctor-Facilier", "voodoo conjurer"));
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        gui.println("");
        gui.println("Welcome to Tiana's Adventure Down the Bayou!");
        gui.println("You are Tiana. Work hard to make it to the end of the game");
        gui.println("to open your own restaurant!");
        gui.println("Type 'help' if you need help.");
        gui.println("");
        gui.println("---------------------------------------------------------");
        
        gui.println(player.getCurrentRoom().getLongDescription());
        gui.showImage(player.getCurrentRoom().getImageName());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public boolean interpretCommand(String commandLine) 
    {
        boolean wantToQuit = false;
        Command command = parser.getCommand(commandLine);
        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            
            ArrayList <Item> tempArrayList = new ArrayList(player.getInventory());
            String itemDropped = "a " + command.getSecondWord();
            
            for(int x = 0; x < tempArrayList.size(); x++){
                Item itemInList = (Item)tempArrayList.get(x);
                if(itemInList.getItemDescription().equals("a dress")){
                    canMoveToLocation2 = true;
                }
                else if(itemInList.getItemDescription().equals("a gumbo-pot")){
                    canMoveToLocation3 = true;
                }
                else if(itemInList.getItemDescription().equals("a trumpet")){
                    canMoveToLocation8 = true;
                }
                else if(itemInList.getItemDescription().equals("a talisman")){
                    canOpenRestaurant = true;
                }
                else{
                    canMoveToLocation2 = false;
                    canOpenRestaurant = false;
                }
            }
            
            if ((canMoveToLocation2==false) && (movedOn == 0)){
                gui.println("You cannot leave Charlotte's mansion until you take the dress item!");
            }
            else if ((canMoveToLocation2==true) && (movedOn == 0) && (player.getCurrentRoom().getDescription().equals("at your best friend Charlotte's mansion"))){
                gui.println("---------------------------------------------------------");
                gui.println("You may leave Charlotte's mansion since you took the dress item!");
                String direction = command.getSecondWord();
                gui.println(player.walk(direction));
                gui.showImage(player.getCurrentRoom().getImageName());
                movedOn = movedOn + 1;
            }
            else if ((canMoveToLocation3==false) && (movedOn > 0) && (player.getCurrentRoom().getDescription().equals("at your house"))){
                gui.println("You cannot leave your house until you take the gumbo-pot item!");
            }
            else if ((canMoveToLocation2==true) && (movedOn > 0) && (player.getCurrentRoom().getDescription().equals("at your house"))){
                gui.println("---------------------------------------------------------");
                gui.println("You may leave your house since you took the gumbo-pot item!");
                String direction = command.getSecondWord();
                
                movedOn = movedOn + 1;
                if (command.hasSecondWord()){
                    if ((menuGuessed == false) && (command.getSecondWord().equals("forward"))){
                        
                        gui.println(player.walk(direction));
                        gui.showImage(player.getCurrentRoom().getImageName());
                        
                        gui.println("Oh no! You just remembered you have to");
                        gui.println("work a shift at the restaurant before you can");
                        gui.println("enjoy the sweet jazz on the streets!");
                        gui.println("");
                        gui.println("You will now be sent to the restaurant!");
                        
                        if (!command.hasSecondWord()){
                            gui.println("Enter a word after the go command.");
                        }
                        else if (direction.equals("forward")){
                            gui.println(player.walk(direction));
                            gui.showImage(player.getCurrentRoom().getImageName());
                        }
                        else{
                            gui.println("You must go forward to the restaurant!");
                        }
                    }
                    else if (command.getSecondWord().equals("backward")){
                        
                        if (!command.hasSecondWord()){
                            gui.println("Enter a word after the go command.");
                        }
                        else{
                            gui.println(player.walk(direction));
                            gui.showImage(player.getCurrentRoom().getImageName());
                        }
                        
                    }
                    else if (command.getSecondWord().equals("forward")){
                        gui.println(player.walk(direction));
                        gui.showImage(player.getCurrentRoom().getImageName());
                    }
                }
                else{
                    gui.println("Go where?");
                }
                
            }
            else if (player.getCurrentRoom().getDescription().equals("at the restaurant where you work")){
                if (menuGuessed == true){
                    String direction = command.getSecondWord();
                    gui.println(player.walk(direction));
                    gui.showImage(player.getCurrentRoom().getImageName());
                    //players then enter the ball
                    if (remember==false){
                        if ((hadToGoBack == false) && (player.getCurrentRoom().getDescription().equals("at a ball hosted by Charlotte's family"))){
                            gui.println("Welcome, Tiana! Now that you're at");
                            gui.println("the ball, Charlotte reminds you that");
                            gui.println("Prince Naveen of Maldonia will be in");
                            gui.println("attendance and you must dress to impress!");
                            gui.println("Your current dress is just not up to par.");
                            gui.println("Can you remember the dress you designed back");
                            gui.println("in Charlotte's house?");
                            gui.println("");
                            gui.println("Use the guess command followed by the color.");
                        }
                        
                    }
                }
                else{
                    gui.println("You cannot leave work until you do some work!");
                    gui.println("Use the order command.");
                }
            }
            else if(player.getCurrentRoom().getDescription().equals("at a ball hosted by Charlotte's family")){
                String toPrint = null;
                for(int x = 0; x < tempArrayList.size(); x++){
                    Item itemInList = (Item)tempArrayList.get(x);
                    if(itemInList.getItemDescription().equals("a dress")){
                        //item in inventory
                        toPrint = "You must drop the dress to put it on and go forward!";
                    }
                    else{
                        if (remember == true){
                            String direction = command.getSecondWord();
                            
                            String ifInside = player.getCurrentRoom().roomContains();
                            if (ifInside.equals("here")){
                                //if they've dropped it in the correct place they can move forward
                                
                                canMoveOnHere = true;
                                if(!command.hasSecondWord()){
                                    toPrint = "Go where?";
                                }
                                else if ((direction.equals("forward")) && (first==0)){
                                    first = first+1;
                                    gui.println("---------------------------------------------------------");
                                    toPrint = colors[randomColor] + " is definitely your color!" + "\n" + "Now, you're ready! Wait, what's that on the window?" + "\n" + "It kind of looks like a frog... ew!" + "\n" + "Go scare it away!" + "\n" + player.walk(direction);
                                    gui.showImage(player.getCurrentRoom().getImageName());
                                }
                                else{
                                    toPrint = player.walk(direction);
                                    gui.showImage(player.getCurrentRoom().getImageName());
                                }
                                
                            }
                            else{
                                
                                if(!command.hasSecondWord()){
                                    toPrint = "Go where?";
                                }
                                else if (direction.equals("backward")){
                                    toPrint = "Go get your dress!" + "\n" + player.walk(direction);
                                    gui.showImage(player.getCurrentRoom().getImageName());
                                }
                                else{
                                    toPrint = "You must drop the dress item at the ball to move on." + "\n" + "You can go backward to get the dress." + "\n" + "If you already dropped the dress," + "\n" + "go forward again!";
                                    hadToGoBack = true;
                                }
                            }
                        }
                        else{
                            toPrint = "You have to guess the correct color to go!";
                        }
                        
                    }
                }
                if ((canMoveOnHere == true) && (first==0)){
                    first=first+1;
                    String direction = command.getSecondWord();
                    toPrint = colors[randomColor] + " is definitely your color!" + "\n" + "Now, you're ready! Wait, what's that on the window?" + "\n" + "It kind of looks like a frog... ew!" + "\n" + "Go scare it away!" + "\n" + player.walk(direction);
                    gui.showImage(player.getCurrentRoom().getImageName());
                    
                }
                gui.println(toPrint);
                
            }
            else if (player.getCurrentRoom().getDescription().equals("on the balcony attached to Charlotte's bedroom")){
                if (kissed == true){
                    if (escaped == true){
                        String direction = command.getSecondWord();
                        if(!command.hasSecondWord()){
                            gui.println("Go where?");
                        }
                        else{
                            gui.println(player.walk(direction));
                            gui.showImage(player.getCurrentRoom().getImageName());
                            if ((tries == 0) && (direction.equals("forward"))){
                                gui.println("");
                                gui.println("Welcome to the bayou! In order to have enough");
                                gui.println("energy to continue on your adventure, you must");
                                gui.println("learn how to eat like a frog! There are 10 spots");
                                gui.println("where flies could be located. It is up to you");
                                gui.println("to locate 3 flies and eat them with your tongue!");
                                gui.println("");
                                gui.println("Flies travel in swarms so when you find a fly at");
                                gui.println("one location, you can keep eating at that location");
                                gui.println("until you eat three flies!");
                                gui.println("");
                                gui.println("Enter the eat command followed by a number to guess");
                                gui.println("if a fly is in that location!");
                                gui.println("");
                            }
                        }
                    }
                    else{
                        gui.println("You must escape with Naveen before moving!");
                    }
                }
                else{
                    gui.println("You cannot move on until you talk with Naveen!");
                }
            }
            else if(player.getCurrentRoom().getDescription().equals("in a new section of the bayou filled with alligators")){
                String direction = command.getSecondWord();
                if (direction.equals("backward")){
                    gui.println(player.walk(direction));
                    gui.showImage(player.getCurrentRoom().getImageName());
                }
                if (canTakeTrumpet == true){
                    
                    if (canMoveToLocation8==false){
                        if (direction.equals("forward")){
                            gui.println("You cannot leave Louis's section of the bayou");
                            gui.println("until you take the trumpet item!");
                        }
                        
                    }
                    else{
                        if (!command.hasSecondWord()){
                            gui.println("Go where?");
                        }
                        else if (guessCount == 0){
                            gui.println("---------------------------------------------------------");
                            gui.println("While taking you and Naveen to Mama Odie,");
                            gui.println("Louis took a wrong turn! You ended up in");
                            gui.println("a new section of the bayou far from Mama Odie.");
                            
                            gui.println(player.walk(direction));
                            gui.showImage(player.getCurrentRoom().getImageName());
                            gui.println("");
                            gui.println("This part of the bayou is home to a huge family");
                            gui.println("of lightning-bugs! Maybe they can give you some");
                            gui.println("directions to Mama Odie!");
                            gui.println("");
                            gui.println("A lightning-bug named Ray approaches you.");
                            gui.println("");
                            gui.println("Ray: Well looky here! Let me shine a little light");
                            gui.println("on the situation. I'm Raymond, but everyone calls");
                            gui.println("me Ray.");
                            gui.println("Naveen: Pardon me, but your accent it's funny, no?");
                            gui.println("Ray: I'm a Cajun, brah. Born and bred in the bayou.");
                            gui.println("Where y'all from?");
                            gui.println("Tiana: We ain't from around here. Prince Charming here");
                            gui.println("got himself turned into a frog by a voodoo witch doctor.");
                            gui.println("Ray: Oh, y'all must've been caught up with Facilier.");
                            gui.println("Naveen: Facili-who?");
                            gui.println("Ray: Doctor Facilier. He always be messing with voodoo.");
                            gui.println("Tiana: We were on our way to Mama Odie to reverse his spell");
                            gui.println("but got a little lost.");
                            gui.println("Ray: Well y'all headed in the wrong direction, chére. What");
                            gui.println("kinda knucklehead took y'all this way?");
                            gui.println("Louis: Well, listen. I was confused by the topography and the");
                            gui.println("geography and the choreography...");
                            gui.println("Ray: First rule of the bayou, never take direction from a gator.");
                            gui.println("But, me and my relatives will help show y'all the way.");
                            gui.println("That is, if you can answer my riddles!");
                            gui.println("");
                            gui.println("Ray: Hey, Cousin Randy! You ready for a");
                            gui.println("little bayou zydeco?");
                            gui.println("Randy: Ready when you are, Cousin Ray!");
                            gui.println("");
                            gui.println("Ray: Here is your first riddle.");
                            gui.println("");
                            gui.println("A family has five sons, each of them has a sister.");
                            gui.println("How many kids does the family have in total?");
                            gui.println("");
                            gui.println("Use the answer command to attempt the riddles!");
                            guessCount = guessCount+1;
                        }
                        else{
                            gui.println(player.walk(direction));
                            gui.showImage(player.getCurrentRoom().getImageName());
                        }
                    }
                }
                else{
                    gui.println("You cannot move on until you eat and talk with Louis!");
                }
            }
            else if(player.getCurrentRoom().getDescription().equals("in a new section of the bayou filled with lightning bugs")){
                if ((canGoToMama == false) && (player.getCurrentRoom().roomContains().equals("has"))){
                    if (command.getSecondWord().equals("forward")){
                        gui.println("You cannot move yet!");
                    }
                    else{
                        gui.println("You cannot move yet!");
                    }
                }
                else{
                    ArrayList <Item> tempArrayList2 = new ArrayList(player.getInventory());
                    String direction = command.getSecondWord();
                    
                    String toPrint = null;
                        
                    for(int x = 0; x < tempArrayList2.size(); x++){
                        Item itemInList = (Item)tempArrayList2.get(x);
                        if(itemInList.getItemDescription().equals("a trumpet")){
                            //item in inventory
                            
                            toPrint = "You must take a lightning-bug and beat some Frog Hunters to go forward!";
                        }
                        else{
                            
                            String ifInside = player.getCurrentRoom().roomContains();
                            if (ifInside.equals("available")){
                                //if they've dropped it in the correct place they can move forward
                                
                                if(!command.hasSecondWord()){
                                    toPrint = "Go where?";
                                }
                                else if (direction.equals("forward")){
                                    canGoToMama = true;
                                    if (firstTimeHere == true){
                                        firstTimeHere = false;
                                        toPrint = "You played the trumpet loud enough to" + "\n" + "scare away the frog hunters!" + "\n" + "Ray: Great job,Tiana! I just told Louis" + "\n" + "the directions you need to reach Mama Odie!" + "\n" + "You can continue on now." + "\n";
                                        gui.println(toPrint);
                                    }
                                    else{
                                        toPrint = " ";
                                    }
                                }
                                else if (direction.equals("backward")){
                                    canGoToMama = true;
                                }
                                
                            }
                            else{
                                
                                if(!command.hasSecondWord()){
                                    toPrint = "Go where?";
                                }
                                else if (direction.equals("backward")){
                                    toPrint = "Go get the trumpet!";
                                }
                                else{
                                    toPrint = "You must use the trumpet to scare the frog hunters." + "\n" + "You can go backward to get the trumpet if you need to.";
                                    hadToGoBack2 = true;
                                }
                            }
                        }
                            
                    }
                    
                    if ((canGoToMama == true) && (command.getSecondWord().equals("forward"))){
                        gui.println(player.walk(direction));
                        gui.showImage(player.getCurrentRoom().getImageName());
                        gui.println("");
                        gui.println("This is Mama Odie's house! Talk to Mama Odie to learn");
                        gui.println("valuable information.");
                    }
                    else if (command.getSecondWord().equals("backward")){
                        gui.println(player.walk(direction));
                        gui.showImage(player.getCurrentRoom().getImageName());
                    }
                    else{
                        toPrint = toPrint + "\n" + "You cannot move forward yet!";
                        gui.println(toPrint);
                    }
                    
                }
                
            }
            else if ((player.getCurrentRoom().getDescription().equals("at a house in a tree in the bayou")) && (command.getSecondWord().equals("backward"))){
                String direction = command.getSecondWord();
                gui.println(player.walk(direction));
                gui.showImage(player.getCurrentRoom().getImageName());
            }
            else if(player.getCurrentRoom().getDescription().equals("at a house in a tree in the bayou")){
                if (canMoveToRiverboat == true){
                    String direction = command.getSecondWord();
                    
                    gui.println(player.walk(direction));
                    gui.showImage(player.getCurrentRoom().getImageName());

                    if ((firstTimeInRiverboat == true) && (command.getSecondWord().equals("forward"))){
                        gui.println("");
                        gui.println("Oh no! Naveen has been captured by the evil spirits on 'the");
                        gui.println("other side' that are helping Doctor Facilier!");
                        gui.println("");
                        gui.println("Quick! Talk to the star, Evangeline, to get a clue on how you can help!");
                        gui.println("");
                        firstTimeInRiverboat = false;
                    }
                    
                }
                else{
                    gui.println("You cannot move forward until you get a clue from Mama Odie!");
                }
            }
            else if(player.getCurrentRoom().getDescription().equals("on a riverboat celebrating Mardi Gras")){
                if (canAdvanceToParade == true){
                    String direction = command.getSecondWord();
                    gui.println(player.walk(direction));
                    gui.showImage(player.getCurrentRoom().getImageName());
                    //now at parade
                    
                    if (firstTimeInParade == true){
                        gui.println("Naveen is trapped in a box on the Mardi Gras float! Drop an item");
                        gui.println("from your inventory that can twist and stretch to act like a key!");
                        gui.println("");
                        gui.println("Only one item will fit in the keyhole!");
                        gui.println("Beware! If you drop an item that");
                        gui.println("does not work, you should pick");
                        gui.println("it up again!");
                        gui.println("");
                        firstTimeInParade = false;
                    }
                    
                    ArrayList <Item> tempArrayList3 = new ArrayList(player.getInventory());
                    
                    String toPrint = null;
                        
                    for(int x = 0; x < tempArrayList3.size(); x++){
                        Item itemInList = (Item)tempArrayList3.get(x);
                        if(itemInList.getItemDescription().equals("a lightning-bug")){
                            //item in inventory
                            toPrint = "You must unlock Naveen to go forward!";
                        }
                        else{
                            String ifInside = player.getCurrentRoom().roomContains();
                            if (ifInside.equals("has")){
                                //if they've dropped it in the correct place they can move forward
                                
                                if(!command.hasSecondWord()){
                                    toPrint = "Go where?";
                                }
                                else if (direction.equals("forward")){
                                    canGoToAlley = true;
                                    if (printYes == true){
                                        printYes = false;
                                        toPrint = "You already saved Naveen! Onwards!";
                                        gui.println(toPrint);
                                        
                                    }
                                    else{
                                        toPrint = " ";
                                    }
                                }
                                else if (direction.equals("backward")){
                                    canGoToAlley = true;
                                }
                                
                            }
                            else{
                                
                                if(!command.hasSecondWord()){
                                    toPrint = "Go where?";
                                }
                                else if (direction.equals("backward")){
                                    toPrint = "Go get the lightning-bug!" + "\n" + player.walk(direction);
                                    gui.showImage(player.getCurrentRoom().getImageName());
                                }
                                else{
                                    if (firstTimeInParade == true){
                                        gui.println("You must use Ray's tongue to unlock the box." + "\n" + "You can go backward to get the lightning-bug if you need to.");
                                        hadToGoBack3 = true;
                                    }
                                }
                            }
                        }
                        firstTimeInParade = false;
                        
                    }
                    
                    if ((canGoToAlley == true) && (command.getSecondWord().equals("forward"))){
                        gui.println(player.walk(direction));
                        gui.println("");
                        gui.showImage(player.getCurrentRoom().getImageName());
                    }
                    
                    else if (command.getSecondWord().equals("forward")){
                        if (toPrint==null){
                            toPrint = "You cannot move until you save Naveen!";
                        }
                        toPrint = toPrint + "\n" + "You cannot move yet!";
                        gui.println(toPrint);
                    }
                    else if ((canGoToAlley == false) && (command.getSecondWord().equals("backward"))){
                        gui.println(player.walk(direction));
                        gui.showImage(player.getCurrentRoom().getImageName());
                    }
                    else{
                        if (toPrint==null){
                            toPrint = "You cannot move until you save Naveen!";
                        }
                        gui.println(toPrint);
                    }
                    
                }
                else{
                    gui.println("You must talk to Evangeline before moving on!");
                }
            }
            else if (player.getCurrentRoom().getDescription().equals("at the Mardi Gras parade")){
                String direction = command.getSecondWord();
                   
                ArrayList <Item> tempArrayList3 = new ArrayList(player.getInventory());
                
                String toPrint = null;
                    
                for(int x = 0; x < tempArrayList3.size(); x++){
                    Item itemInList = (Item)tempArrayList3.get(x);
                    if(itemInList.getItemDescription().equals("a lightning-bug")){
                        //item in inventory
                        toPrint = "You must unlock Naveen to go forward!";
                    }
                    else{
                        String ifInside = player.getCurrentRoom().roomContains();
                        if (ifInside.equals("has")){
                            //if they've dropped it in the correct place they can move forward
                            
                            if(!command.hasSecondWord()){
                                toPrint = "Go where?";
                            }
                            else if (direction.equals("forward")){
                                
                                if (printYes == true){
                                    printYes = false;
                                    canGoToAlley = true;
                                    toPrint = "Ray: There's the talisman! You need it to end Facilier!";
                                    gui.println("---------------------------------------------------------");
                                    gui.println(toPrint);
                                    
                                }
                                else{
                                    toPrint = " ";
                                }
                            }
                            else if (direction.equals("backward")){
                                canGoToAlley = true;
                            }
                            
                        }
                        else{
                            
                            if(!command.hasSecondWord()){
                                toPrint = "Go where?";
                            }
                            else if (direction.equals("backward")){
                                toPrint = "Go get the lightning-bug!" + "\n" + player.walk(direction);
                                gui.showImage(player.getCurrentRoom().getImageName());
                            }
                            else if (firstTime == false){
                                toPrint = "You must use Ray's tongue to unlock the box." + "\n" + "You can go backward to get the lightning-bug if you need to.";
                                hadToGoBack3 = true;
                            }
                            else{
                                
                                hadToGoBack3 = true;
                            }
                        }
                    }
                    firstTime = false;
                    firstTimeInParade = false;
                }
                
                if ((canGoToAlley == true) && (command.getSecondWord().equals("forward"))){
                    gui.println(player.walk(direction));
                    gui.showImage(player.getCurrentRoom().getImageName());
                    gui.println("");
                    if (firstDirection == true){
                        gui.println("In order for Naveen and Charlotte's kiss to work, you must destroy");
                        gui.println("the talisman! Enter the fight command to start your final battle");
                        gui.println("to steal the talisman from Doctor Facilier!");
                        firstDirection = false;
                    }
                    
                }
                else if ((canGoToAlley == true) && (command.getSecondWord().equals("backward"))){
                    gui.println(player.walk(direction));
                    gui.showImage(player.getCurrentRoom().getImageName());
                }
                else if ((canGoToAlley == false) && (command.getSecondWord().equals("backward"))){
                    gui.println(player.walk(direction));
                    gui.showImage(player.getCurrentRoom().getImageName());
                }
                else{
                    toPrint = toPrint + "\n" + "You cannot move yet!";
                    gui.println(toPrint);
                }
            }
            else if (player.getCurrentRoom().getDescription().equals("in a dark alley")){
                if (canOpenRestaurant == true){
                    String direction = command.getSecondWord();
                    gui.println(player.walk(direction));
                    gui.showImage(player.getCurrentRoom().getImageName());
                    
                    if (firstTimeInRestaurant == true){
                        gui.println("You made it to Tiana's Place! You are");
                        gui.println("almost ready to open your own restaurant");
                        gui.println("but in order to do so, you must drop a");
                        gui.println("lucky item! You can't achieve your dream");
                        gui.println("without your father's gumbo pot!");
                        gui.println("");
                        gui.println("If you dropped the gumbo-pot");
                        gui.println("at an earlier point in the game,");
                        gui.println("you can go back and get it now!");
                        gui.println("");
                        gui.println("Caution! Going backwards in the bayou");
                        gui.println("is a dangerous task! The sections of the");
                        gui.println("bayou may be in a different order than when");
                        gui.println("you went forwards! You may be sent backwards");
                        gui.println("two times in some locations when you only");
                        gui.println("want to go back one. In that case, go forward again!");
                        gui.println("");
                                    
                        firstTimeInRestaurant = false;
                        
                        for(int x = 0; x < tempArrayList.size(); x++){
                            Item itemInList = (Item)tempArrayList.get(x);
                            if(itemInList.getItemDescription().equals("a gumbo-pot")){
                                //item in inventory
                                gui.println("You must drop the gumbo-pot to win!");
                            }
                            else{
                                
                                String ifInside = player.getCurrentRoom().roomContains();
                                if (ifInside.equals("present")){
                                    //if they've dropped it in the correct place they can move forward
                                    gui.println("");
                                    gui.println("You have won Tiana's Adventure Down the Bayou!");
                                    gui.println("");
                                    gui.println("All of the dialogue, characters, and story line were");
                                    gui.println("created by Disney.");
                                    
                                    wantToQuit = true;
                                    endGame();
                                }
                                
                            }
                        }
                        
                    }
                }
                else{
                    gui.println("You must collect the talisman to move on!");
                }
            }
            else if (movedOn > 0){
                String direction = command.getSecondWord();
                gui.println(player.walk(direction));
                gui.showImage(player.getCurrentRoom().getImageName());
            }
            
        }
        else if (commandWord.equals("quit")) {
            endGame();
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("talk")){
            if(!command.hasSecondWord()){
                gui.println("Talk with who?");
            }
            else if (command.getSecondWord().equals("Naveen")){
                if (player.getCurrentRoom().getDescription().equals("on the balcony attached to Charlotte's bedroom")){
                    gui.println("");
                    gui.println("Tiana: I reckon you want a kiss...");
                    gui.println("Naveen: Kissing would be nice... yeah?");
                    gui.println("");
                    gui.println("Oh my goodness! A frog just spoke to you!");
                    gui.println("");
                    gui.println("Naveen: I'm sorry, I did not mean to scare you!");
                    gui.println("Oh...you have a very strong arm princess!");
                    gui.println("Allow me to introduce myself, I am Prince Naveen!");
                    gui.println("Tiana: If you're the prince, then who was down");
                    gui.println("waltzing with Lottie?!");
                    gui.println("Naveen: All I know is one minute I am a prince,");
                    gui.println("and then the next thing I know, I am a frog!");
                    gui.println("You know the story of The Frog Prince? The answer");
                    gui.println("is simple! You must kiss me!");
                    gui.println("Tiana: I'm sorry, I would really like");
                    gui.println("to help you, I just don't kiss frogs!");
                    gui.println("");
                    gui.println("So, what do you say? Enter the command");
                    gui.println("kiss with either yes or no following.");
                    talkNaveen = true;
                }
                else{
                    gui.println("You must be on the balcony to talk to Naveen!");
                }
            }
            else if (command.getSecondWord().equals("Louis")){
                if ((player.getCurrentRoom().getDescription().equals("in a new section of the bayou filled with alligators")) && (canTalkToLouis == true)){
                    gui.println("");
                    gui.println("Louis: I know that tune! Dippermount Blues!");
                    gui.println("");
                    gui.println("Louis plays his trumpet along with Naveen.");
                    gui.println("");
                    gui.println("Naveen: Play it brother!");
                    gui.println("Tiana: Where did you learn how to play like that?");
                    gui.println("Louis: Why, the bayou's the best jazz school in the world!");
                    gui.println("Oh, but Old Louis would give anything to be up there jamming with");
                    gui.println("the big boys.");
                    gui.println("Tiana: Well, it was nice meeting you Louis, but we best be on our way.");
                    gui.println("Louis: Where y'all going!");
                    gui.println("Tiana: To find somebody to break this spell!");
                    gui.println("Louis: What spell?");
                    gui.println("Naveen: Brace yourself, scaly friend. We are not frogs!");
                    gui.println("Tiana: We're humans! Voodoo magic turned us into frogs!");
                    gui.println("Louis: Voodoo? Like the kind Mama Odie do?");
                    gui.println("Naveen: Mama who-dee?");
                    gui.println("Louis: Mama Odie! She the voodoo queen of the bayou!");
                    gui.println("Tiana: Could you take us to her?");
                    gui.println("Louis: Through the deepest, darkest parts of the bayou?!");
                    gui.println("Well, maybe she can turn me into a human too so I can");
                    gui.println("play in a band...");
                    gui.println("Alright! Let's go!");
                    gui.println("");
                    gui.println("Louis: Would you do me a favor and hang onto my trumpet?");
                    canTakeTrumpet = true;
                }
                else{
                    gui.println("You cannot talk to Louis yet!");
                }
            }
            else if (command.getSecondWord().equals("Mama-Odie")){
                if (player.getCurrentRoom().getDescription().equals("at a house in a tree in the bayou")){
                    gui.println("");
                    gui.println("Mama Odie: Now which one of you been messing with the");
                    gui.println("Shadow Man?");
                    gui.println("Tiana: We're so glad we found you Mama Odie! Louis here has");
                    gui.println("been telling us all about you.");
                    gui.println("Mama Odie: Good to see you! I just have to finish up");
                    gui.println("this batch of gumbo. How's that taste?");
                    gui.println("Tiana: Hit it hard with a couple shots of Tabasco and it's");
                    gui.println("the bee's knees!");
                    gui.println("Mama Odie: That's just what it needed! Now y'all figure out");
                    gui.println("what you need.");
                    gui.println("Tiana: We just need to be human again!");
                    gui.println("Mama Odie: Y'all WANT to be human but you're blind to what");
                    gui.println("you need!");
                    gui.println("Naveen: But what we want, what we need is all the same thing.");
                    gui.println("Mama Odie: Don't matter what you look like! Don't even matter");
                    gui.println("what you are! A dog, a pig, a cow, a goat - had 'em all in here!");
                    gui.println("And they all knew what they wanted. I told them what they needed");
                    gui.println("just like I be telling you! You gotta dig a little deeper! Find");
                    gui.println("out who you are!");
                    gui.println("");
                    gui.println("Mama Odie: Now, since y'all still think turning into humans is what");
                    gui.println("you need, use the stir command to learn a clue!");
                    gui.println("");
                }
                else{
                    gui.println("You cannot talk to Mama Odie yet!");
                }
            }
            else if (command.getSecondWord().equals("Evangeline")){
                if (player.getCurrentRoom().getDescription().equals("on a riverboat celebrating Mardi Gras")){
                    gui.println("");
                    gui.println("Evangeline: Tiana! I'm so glad you came to talk.");
                    gui.println("Tiana: Oh, please help! I'm still stuck as a frog");
                    gui.println("and now Naveen has been captured! I've always been so");
                    gui.println("sure of what I wanted but now I don't know.");
                    gui.println("Evangeline: Listen to your heart, Tiana! Who is");
                    gui.println("willing to do whatever it takes to make your dreams");
                    gui.println("come true? Who would be willing to stay as a frog forever");
                    gui.println("if it meant staying with you? Tiana, you've known what is");
                    gui.println("needed to break this spell the whole time... love!");
                    gui.println("Tiana: You're right! Thank you, Evangeline!");
                    gui.println("Evangeline: Go save your prince!");
                    gui.println("");
                    gui.println("Naveen has been taken to the Mardi Gras parade!");
                    gui.println("Time is running out, go!");
                    canAdvanceToParade = true;
                }
                else{
                    gui.println("You cannot talk to Evangeline yet!");
                }
            }
            else if (command.getSecondWord().equals("Doctor-Facilier")){
                if (player.getCurrentRoom().getDescription().equals("in a dark alley")){
                    gui.println("Facilier: You'll never be able to get my talisman!");
                }
                else{
                    gui.println("You cannot talk to Doctor-Facilier yet!");
                }
            }
            else{
                gui.println("That character does not exist!");
            }
        }
        else if (commandWord.equals("take")){
            boolean open = getDressGuessed();
            boolean allowed = getRecipeGuessed();
            
            if(!command.hasSecondWord()){
                gui.println("Take what?");
            }
            else{
                if ((open == false) && command.getSecondWord().equals("dress")){
                    gui.println("You cannot take the dress until you sew it!");
                }
                else if ((open == true) && command.getSecondWord().equals("dress")){
                    gui.println(player.take(command));
                }
                else if ((allowed == false) && command.getSecondWord().equals("gumbo-pot")){
                    gui.println("You cannot take the gumbo-pot until you perfect the gumbo recipe!");
                    gui.println("To do that, you must add spices!");
                }
                else if ((allowed == true) && command.getSecondWord().equals("dress")){
                    gui.println("Good job making the gumbo!");
                    gui.println(player.take(command));
                }
                else if ((canTakeTrumpet==true) && command.getSecondWord().equals("trumpet")){
                    gui.println(player.take(command));
                }
                else if ((canTakeTrumpet==false) && command.getSecondWord().equals("trumpet")){
                    gui.println("You cannot take the trumpet yet!");
                }
                else if ((canTakeRay==false) && (command.getSecondWord().equals("lightning-bug"))){
                    gui.println("You cannot take Ray yet!");
                }
                else if ((canTakeRay==true) && (command.getSecondWord().equals("lightning-bug"))){
                    String answer = player.take(command);
                    if (answer.equals("This item does not exist.")){
                        gui.println(player.take(command));
                    }
                    else{
                        player.take(command);
                        gui.println("You've taken a lightning-bug");
                        if (firstTimeTaking == true){
                            gui.println("");
                            gui.println("Watch out! Frog hunters are after you!");
                            gui.println("Drop an item from your inventory to scare");
                            gui.println("them away!");
                            gui.println("");
                            gui.println("Only one item will work!");
                            gui.println("Beware! If you drop an item that");
                            gui.println("does not work, you should pick");
                            gui.println("it up again!");
                            gui.println("");
                            firstTimeTaking = false;
                        }
                        
                    }
                }
                else if ((canTakeTalisman == false) && (command.getSecondWord().equals("talisman"))){
                    gui.println("You cannot take the talisman yet!");
                }
                else if ((canTakeTalisman==true) && (command.getSecondWord().equals("talisman"))){
                    gui.println(player.take(command));
                }
                else{
                    gui.println(player.take(command));
                }
            }
        }
        else if (commandWord.equals("drop")){
            String returned = player.drop(command);
            gui.println(returned);
                    
            if ((returned.equals("You've dropped a gumbo-pot")) && (player.getCurrentRoom().getDescription().equals("in your very own restaurant, Tiana's Place"))){
                gui.println("");
                gui.println("You have won Tiana's Adventure Down the Bayou!");
                
                gui.println("");
                gui.println("All dialogue, characters, and the story line was");
                gui.println("created by Disney.");
                wantToQuit = true;
                endGame();
            }
            else if ((!returned.equals("You've dropped a gumbo-pot")) && (player.getCurrentRoom().getDescription().equals("in your very own restaurant, Tiana's Place"))){
                gui.println("Try again!");
            }
            else if ((returned.equals("You've dropped a dress")) && (player.getCurrentRoom().getDescription().equals("at a ball hosted by Charlotte's family"))){
                canMoveOnHere = true;
                gui.println("Move forward!");
            }
            
            else if ((returned.equals("You've dropped a trumpet")) && (player.getCurrentRoom().getDescription().equals("in a new section of the bayou filled with lightning bugs"))){
                canGoToMama = true;
                if (firstTimeHere == true){
                    firstTimeHere = false;
                    gui.println("");
                    gui.println("You played the trumpet loud enough to");
                    gui.println("scare away the frog hunters!");
                    gui.println("Ray: Great job,Tiana! I just told Louis the");
                    gui.println("directions you need to reach Mama Odie!");
                    gui.println("You can continue on now.");
                }
            }
            else if ((returned.equals("You've dropped a lightning-bug")) && (player.getCurrentRoom().getDescription().equals("at the Mardi Gras parade"))){
                canGoToAlley = true;
                gui.println("");
                gui.println("You saved Naveen!");
                gui.println("Ray: Look at y'all reunited like a perfect couple.");
                gui.println("But, no time to waste! In order to destroy Doctor Facilier's");
                gui.println("voodoo magic, y'all must retrieve the voodoo talisman!");
                gui.println("Go forward!");
                gui.println("");
                
            }
            
        }
        else if (commandWord.equals("inventory")){
            gui.println(player.seeInventory());
        }
        else if (commandWord.equals("sew")){
            if (player.getCurrentRoom().getDescription().equals("at your best friend Charlotte's mansion")){
                if (counter == 0){
                    gui.println("In order to take the dress");
                    gui.println("to move onto the next location,");
                    gui.println("you must design a dress to your mother’s");
                    gui.println("satisfaction by choosing the correct color fabric.");
                    gui.println("");
                    gui.println("Here are the options:");
                    
                    for (int x=0; x<colors.length; x++){
                        gui.print(colors[x] + "\n");
                    }
                    gui.println(createDress(command));
                }
                else{
                    gui.println(createDress(command));
                }
            }
            else{
                gui.println("You must be in Charlotte's Mansion to use the sew command!");
            }
        }
        else if (commandWord.equals("add")){
            if (player.getCurrentRoom().getDescription().equals("at your house")){
                if (attempts == 0){
                    gui.println("In order to take your father's");
                    gui.println("lucky gumbo-pot, you must perfect");
                    gui.println("your gumbo recipe by adding the correct");
                    gui.println("amount of cayenne pepper!");
                    gui.println("Guess between 10 and 50 shakes of cayenne pepper!");
                    canUseHint = canUseHint + 1;
                }
                gui.println(makeGumbo(command));
            }
            else{
                gui.println("You must be in your house to use the add command!");
            }
        }
        else if (commandWord.equals("hint")){
            if ((player.getCurrentRoom().getDescription().equals("at your house")) && (canUseHint>0) && (hintActivated == false)){
                getHint();
            }
            else if ((player.getCurrentRoom().getDescription().equals("at your house")) && (hintActivated == true)){
                gui.println("You already used the hint!");
            }
            else{
                gui.println("You must be cooking in your house to use the hint command!");
            }
        }
        else if(commandWord.equals("order")){
            if (player.getCurrentRoom().getDescription().equals("at the restaurant where you work")){
                gui.println(work(command));
            }
            else{
                gui.println("You cannot order unless you are at the restaurant where you work!");
            }
        }
        else if(commandWord.equals("guess")){
            if (player.getCurrentRoom().getDescription().equals("at a ball hosted by Charlotte's family")){
                gui.println(rememberDress(command));
            }
            else{
                gui.println("You cannot guess unless you are at the ball!");
            }
        }
        else if(commandWord.equals("kiss")){
            if (player.getCurrentRoom().getDescription().equals("on the balcony attached to Charlotte's bedroom")){
                if (talkNaveen == true){
                    if(!command.hasSecondWord()){
                        gui.println("Please enter either yes or no after the kiss command.");
                    }
                    
                    else if (command.getSecondWord().equals("yes")){
                        gui.println("");
                        gui.println("Naveen: Faldi Faldonza!!!");
                        gui.println("(Translation from Maledonian: OMG!)");
                        gui.println("Tiana: Y'all don't look much different.");
                        gui.println("But how'd you get way up there? And");
                        gui.println("how'd I get way down here?");
                        gui.println("");
                        gui.println("You have been transformed into a frog!");
                        gui.println("");
                        gui.println("No time to waste! You and Naveen landed");
                        gui.println("in the band's setup and are being chased through the ball!");
                        kissed = true;
                        gui.println("");
                        gui.println("Use the turn command to try to escape!");
                        gui.println("You need to guess the correct 3-step sequence");
                        gui.println("of moves in order to escape!");
                        gui.println("Enter three letters (R or L) to indicate");
                        gui.println("a sequence of turning right or left.");
                    }
                    else if(command.getSecondWord().equals("no")){
                        gui.println("");
                        gui.println("Naveen: Please, you must kiss me!");
                        gui.println("Besides from being unbelievably handsome,");
                        gui.println("I also happen to come from a fabulously wealthy");
                        gui.println("family. Surely I could offer you some type of reward.");
                        gui.println("Is there a wish I could grant...perhaps?");
                        gui.println("");
                        gui.println("Think of your dream of opening a restaurant...");
                        gui.println("this could be the boost your piggy banks need!");
                        gui.println("You can't keep overworking yourself... it's too much!");
                        gui.println("You must kiss Naveen to continue!");
                    }
                    else{
                        gui.println("Please enter yes or no");
                    }
                }
                else{
                    gui.println("You must talk with Naveen before you kiss!");
                }
            }
            else{
                gui.println("You cannot kiss unless you are on the balcony!");
            }
        }
        
        else if(commandWord.equals("turn")){
            if ((player.getCurrentRoom().getDescription().equals("on the balcony attached to Charlotte's bedroom")) && (kissed==true)){
                if (!command.hasSecondWord()){
                    gui.println("Turn in what sequence?");
                }
                else{
                    chase(command);
                }
            }
            else{
                gui.println("You cannot use this command yet!");
            }
        }
        else if(commandWord.equals("show")){
            if ((player.getCurrentRoom().getDescription().equals("on the balcony attached to Charlotte's bedroom")) && (canShow==true)){
                gui.println("");
                gui.println("Here are the first two directions,");
                gui.println("now just guess the third!");
                gui.println("");
                gui.println(options[directionOne] + options[directionTwo]);
            }
            else{
                gui.println("You cannot use this command yet!");
            }
        }
        else if(commandWord.equals("eat")){
            if (player.getCurrentRoom().getDescription().equals("in a new section of the bayou filled with alligators")){
                flies();
                guessFlies(command);
            }
            else{
                gui.println("You cannot use this command yet!");
            }
        }
        else if(commandWord.equals("answer")){
            if (player.getCurrentRoom().getDescription().equals("in a new section of the bayou filled with lightning bugs")){
                riddles(command);
            }
            else{
                gui.println("You cannot use this command yet!");
            }
        }
        else if (commandWord.equals("stir")){
            if (player.getCurrentRoom().getDescription().equals("at a house in a tree in the bayou")){
                gui.println("Mama Odie: Let me stir my good ol' gumbo pot to look into the future!");
                gui.println("To turn back into humans, Naveen must kiss Charlotte before midnight,");
                gui.println("while she is the princess of the Mardi Gras parade.");
                gui.println("");
                gui.println("Knowing this, you can now advance!");
                canMoveToRiverboat = true;
            }
            else{
                gui.println("You cannot use this command yet!");
            }
        }
        else if (commandWord.equals("fight")){
            if (player.getCurrentRoom().getDescription().equals("in a dark alley")){
                fight(command);
            }
            else{
                gui.println("You cannot use this command yet!");
            }
        }
        else if (commandWord.equals("marry")){
            if ((player.getCurrentRoom().getDescription().equals("in a dark alley")) && (canMarry == true)){
                gui.println("");
                gui.println("You shared your vows and now you're married to Prince Naveen!");
                gui.println("With the true love kiss between you and Naveen,");
                gui.println("the spell has been broken! You and Naveen have turned");
                gui.println("back into humans! You destroyed the talisman!");
                gui.println("");
                gui.println("Pick up the pieces of the talisman and move forward!");
                canTakeTalisman = true;
            }
            else{
                gui.println("You cannot use this command yet!");
            }
        }
        
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     */
    private void printHelp() 
    {
        gui.println("Your wish upon the Evening Star worked!");
        gui.println("Here is some help.");
        gui.println("");
        gui.println("Please note, some commands may not work in every room!");
        gui.println("");
        gui.println("Your command words are:");
        gui.println(parser.showCommands());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            gui.println("Quit what?");
            return false;
        }
        else {
            endGame();
            return true;  // signal that we want to quit
        }
    }
    
    private void printLocationInfo(){
        gui.println(player.getCurrentRoom().getLongDescription());
    }
    
    private void look(){
        gui.println(player.getCurrentRoom().getLongDescription());
    }
    
    private String printItems(){
        return player.getCurrentRoom().roomContains();
    }
    
    private String printCharacters(){
        return player.getCurrentRoom().meetCharacters();
    }    
    
    private String createDress(Command command){
        String printToReturn=null;
    
        counter = counter+1;
        
        String colorGuessed = command.getSecondWord();
        
        if (!command.hasSecondWord()){
            printToReturn = "\n" + "sew what color?";
        }
        else{
            printToReturn = "\n"+"Please enter the color fabric you would like to guess after the word sew.";
            if (colorGuessed.equals(colors[randomColor])){
                dressGuessed = true;
                printToReturn = "You've guessed the correct color: " + colorGuessed;
            }
            else if ((counter>1) && (!colorGuessed.equals(colors[randomColor]))){
                printToReturn = "Try again!";
            }

            if ((dressGuessed == true) && (canMoveToLocation2==true)){
                printToReturn = "You already took the dress!";
            }
            else if ((dressGuessed == true) && (canMoveToLocation2==false)){
                printToReturn = printToReturn + "\n" + "In order to move onto Tiana's Home, you must take the dress!";
            }
        }
        
        return printToReturn;
    }
    
    private boolean getDressGuessed(){
        return dressGuessed;
    }
    
    private String makeGumbo(Command command){
        int intNumberGuessed = 0;
        String numberGuessed = command.getSecondWord();
        try{
            intNumberGuessed = Integer.parseInt(numberGuessed);
        }
        catch(NumberFormatException exc) {
            gui.println("");
            gui.println("Please enter a number after the command add");
            intNumberGuessed = 0;
        }
        
        String printToReturn=null;
    
        attempts = attempts+1;
        
        if (!command.hasSecondWord()){
            printToReturn = "\n" + "add how many shakes of cayenne pepper?";
        }
        else{
            printToReturn = "\n"+"add how many shakes of cayenne pepper?";
            if (intNumberGuessed == randomShakes){
                recipeGuessed = true;
                printToReturn = "\n" + "You've guessed the correct number of shakes: " + numberGuessed + "\n" + "Your father approves of your gumbo and says it's the best" + "\n" + "he's ever tasted! He encourages you" + "\n" + "to open your own place and when you do," + "\n" + "make sure to cook with his lucky gumbo-pot!";
            }
            else if ((attempts==2) && (intNumberGuessed != randomShakes)){
                printToReturn = "Try again!" + "\n"+ "Or, if you would like a hint, enter hint.";
            }
            else if ((attempts>2) && (attempts<5) && (intNumberGuessed != randomShakes)){
                printToReturn = "Try again!";
            }
            else if ((attempts==5) && (intNumberGuessed != randomShakes) && (hintActivated == true)){
                //Check to see if hint already activated and player still cannot solve riddle, then I will give another hint.
                printToReturn = "Try again!" + "\n"+ "Remember, Evangeline is a star." + "\n" + "Can stars eat food?";
            }
            else if((attempts>=5) && (intNumberGuessed != randomShakes)){
                printToReturn = "Try again!";
            }
            if ((recipeGuessed == true) && (canMoveToLocation3==false)){
                printToReturn = printToReturn + "\n" + "\n" + "In order to go to the Streets of New Orleans," + "\n" + "you must take the gumbo-pot!";
            }
            else if ((recipeGuessed == true) && (canMoveToLocation3==true)){
                printToReturn = "You already took the gumbo-pot!";
            }
        }
        return printToReturn;
    }
    
    private void getHint(){
        gui.println("If you figure out this riddle,");
        gui.println("you will know the correct number of shakes!");
        gui.println("");
        gui.println("There are "  + randomShakes + " shrimp in your gumbo.");
        gui.println("Evangeline said she ate 5 shrimp. How many shrimp are left?");
        gui.println("The number left is the correct number of shakes!");
        hintActivated = true;
    }
    
    private boolean getRecipeGuessed(){
        return recipeGuessed;
    }
    
    private String work(Command command){
        HashMap<String, String> menu = new HashMap<String, String>();
        menu.put("netgeib", "beignet");
        menu.put("ajaalyamb", "jambalaya");
        menu.put("muobg", "gumbo");
        menu.put("whrfiacs", "crawfish");
        menu.put("cfefoe", "coffee");
        
        String answer = null;
        
        if (turn == 0){
            gui.println("");
            gui.println("Good thing you're here, Tiana!");
            gui.println("Charlotte's father, Eli 'Big Daddy' La Bouff,");
            gui.println("ordered something off of the menu. But, his");
            gui.println("order got scrambled! Figure out what Mr. La Bouff");
            gui.println("wanted in order to get to go to the ball!");  
            
            if (randomMenu == 0){
                gui.println("Unscramble this: netgeib");
            }
            else if (randomMenu == 1){
                gui.println("Unscramble this: ajaalyamb");
            }
            else if (randomMenu == 2){
                gui.println("Unscramble this: muobg");
            }
            else if (randomMenu == 3){
                gui.println("Unscramble this: whrfiacs");
            }
            else if (randomMenu == 4){
                gui.println("Unscramble this: cfefoe");
            }
        }
        
        if (randomMenu == 0){
            answer = (String)menu.get("netgeib");
        }
        else if (randomMenu == 1){
            answer = (String)menu.get("ajaalyamb");
        }
        else if (randomMenu == 2){
            answer = (String)menu.get("muobg");
        }
        else if (randomMenu == 3){
            answer = (String)menu.get("whrfiacs");
        }
        else if (randomMenu == 4){
            answer = (String)menu.get("cfefoe");
        }
        
        String printToReturn = null;
        String wordGuessed = command.getSecondWord();
        turn = turn+1;
        
        if (!command.hasSecondWord()){
            printToReturn = "\n" + "What order would you like to put in?" + "\n" + "Enter order and the unscrambled word";
        }
        else{
            printToReturn = "\n" + "What order would you like to put in?" + "\n" + "Enter order and the unscrambled word";
            if (wordGuessed.equals(answer)){
                menuGuessed = true;
                printToReturn = "\n" + "You unscrambled Mr. La Bouff's order!" + "\n" + "As a token of his appreciation," + "\n" + "Mr. La Bouff invites you to his Mardi Gras ball!";
            }
            else if ((!wordGuessed.equals(answer)) && (turn!=6)){
                printToReturn = "Try again!";
            }
            else if ((!wordGuessed.equals(answer)) && (turn==6)){
                gui.println("Here are all of the possible menu items: ");
                gui.println("beignet, jambalaya, gumbo, crawfish, coffee");
                printToReturn = "Try again!";
            }
        }
        return printToReturn;
    }
    
    private String rememberDress(Command command){
        String printToReturn = null;
        String guess = command.getSecondWord();
        if (!command.hasSecondWord()){
            printToReturn = "Enter a color after guess!";
        }
        else if (guess.equals(colors[randomColor])){
            printToReturn = "You did it! This dress is much better." + "\n" + "Now drop the dress and put it on!";
            remember = true;
        }
        else{
            printToReturn = "Try again!";
        }
        
        return printToReturn;
    }
    
    private void chase(Command command){
        String direction = command.getSecondWord();
        
        chaseGuessed = chaseGuessed + 1;
        
        String answer = options[directionOne] + options[directionTwo] + options[directionThree];
        
        //the user will need to enter chase followed by three directions
        
        //ex: turn RLR
        
        if (!command.hasSecondWord()){
            gui.println("Turn which direction?");
        }
        else{
            if (direction.equals(options[directionOne] + options[directionTwo] + options[directionThree])){
                gui.println("You guessed the correct sequence of directions and escaped the chase!");
                escaped = true;
            }
            else{
                gui.println("Try again!");
                gui.println("Enter turn followed by three directional letters (either L or R)");
                if (chaseGuessed==2){
                    gui.println("Use the show command for a hint!");
                    canShow = true;
                }
            } 
        }
    }
    
    private void flies(){
        for (int x=0; x<flies.length; x++){
            flies[x] = "X";
        }
        flies[randomFly1] = "O";
        flies[randomFly2] = "O";
        flies[randomFly3] = "O";
    }
    
    private void guessFlies(Command command){
        int intNumberGuessed = -1;
        String numberGuessed = command.getSecondWord();
        try{
            intNumberGuessed = Integer.parseInt(numberGuessed);
        }
        catch(NumberFormatException exc) {
            gui.println("Please enter a number after the command eat");
            intNumberGuessed = -1;
        }
        
        if (intNumberGuessed == -1){
            gui.println("Please enter a number from 0 to 9");
        }
        else if((intNumberGuessed < 0) || (intNumberGuessed > 9)){
            gui.println("Please enter a number from 0 to 9");
        }
        else if(flies[intNumberGuessed].equals("X")){
            gui.println("Try again!");
        }
        else if (flies[intNumberGuessed].equals("O")){
            gui.println("You ate a fly!");
            fliesEaten = fliesEaten + 1;
            gui.println("You have eaten " + fliesEaten + " flies.");
        }
        else{
            gui.println("Try again!");
        }
        
        tries = tries+1;
        
        if ((tries == 3) && (fliesEaten == 0)){
            gui.println("Looks like you need some help.");
            gui.println("Here is the array of flies.");
            gui.println("The O's represent the flies and");
            gui.println("the X's are empty spaces. The locations");
            gui.println("are numbered 0-9.");
            gui.println("");
            gui.println("Remember, even if there are less than three locations marked,");
            gui.println("you can still eat three flies by eating multiple flies at");
            gui.println("the marked locations.");
            gui.println("");
            gui.println("0 1 2 3 4 5 6 7 8 9");
            
            for (int x=0; x<flies.length; x++){
                gui.print(flies[x] + " ");
            }
            
            gui.println("");
        }
        
        if (fliesEaten == 3){
            gui.println("You now have the energy to continue!");
            gui.println("Naveen is so excited he starts playing");
            gui.println("a little tune on his twig guitar!");
            gui.println("");
            gui.println("It's time to keep going!");
            gui.println("How do you know where to get some help");
            gui.println("to transform back into a human?");
            gui.println("Maybe that alligator will know!");
            canTalkToLouis = true;
        }
    }
    
    private void riddles(Command command){
        String guess = command.getSecondWord();
        
        if (!command.hasSecondWord()){
            gui.println("Enter your answer after the command answer:");
        }
        else{
            
            if (riddleTwo == false){
                if (guess.equals("6")){
                    gui.println("Ray: Very good! Here is the next one.");
                    gui.println("");
                    riddleTwo = true;
                    if (riddleTwo == true){
                        if (riddle2Activated == false){
                            gui.println("Randy has a huge family: 20 cousins, 10 aunts,");
                            gui.println("10 cousins, and 10 uncles. Each cousin has an aunt");
                            gui.println("who's not Randy's. What is this aunt's relation to Randy?");
                            gui.println("");
                            gui.println("Enter either mother, sister, or grandma after the answer command.");
                            gui.println("");
                            riddle2Activated = true;
                            
                        }
                        else{
                            if (guess.equals("mother")){
                                gui.println("Well done!");
                                canTakeRay = true;
                                gui.println("In order to move on, you must take Ray with you!");
                                gui.println("He is the lightning-bug item!");
                            }
                            else{
                                gui.println("Try again!");
                            }
                        }
                    }
                    
                }
                else{
                    if (guess.equals("mother")){
                        gui.println("Well done!");
                        canTakeRay = true;
                        gui.println("In order to move on, you must take Ray with you!");
                        gui.println("He is the lightning-bug item!");
                    }
                    else{
                        gui.println("Try again!");
                    }
                }
            }
            else{
                if (guess.equals("mother")){
                    gui.println("Well done!");
                    canTakeRay = true;
                    gui.println("In order to move on, you must take Ray with you!");
                    gui.println("He is the lightning-bug item!");
                }
                else{
                    gui.println("Try again!");
                }
            }
            
        }  
    }

    private void fight(Command command){
        
        if (fight1 == true){
            gui.println("Doctor Facilier is using evil spirits on");
            gui.println("'the other side' to help him protect the");
            gui.println("talisman. Figure out how many evil spirits");
            gui.println("you are fighting.");
            gui.println("");
            gui.println("Guess a number between 10-100. If you correctly");
            gui.println("guess the amount of evil spirits, with a 5 number");
            gui.println("buffer on each side, you will be able to defeat them");
            gui.println("and earn the talisman!");
            fight1 = false;
        }
        
        int numOfSpiritsPlus5 = numOfSpirits + 5;
        int numOfSpiritsMinus5 = numOfSpirits - 5;
        
        if (!command.hasSecondWord()){
            gui.println("Enter a guess after the command fight.");
        }
        else{
            int intNumberGuessed = -1;
            String numberGuessed = command.getSecondWord();
            try{
                intNumberGuessed = Integer.parseInt(numberGuessed);
            }
            catch(NumberFormatException exc) {
                gui.println("Please enter a number after the command fight");
                intNumberGuessed = -1;
            }
            
            if (intNumberGuessed == -1){
                gui.println("Please enter a number from 10 to 100");
            }
            else if((intNumberGuessed < 10) || (intNumberGuessed > 100)){
                gui.println("Please enter a number from 10 to 100");
            }
            else if(intNumberGuessed == numOfSpirits){
                gui.println("");
                gui.println("You were within the range!");
                gui.println("The answer was " + numOfSpirits);
                gui.println("");
                gui.println("Doctor Facilier: You're going to spend the rest of");
                gui.println("your life being a slimy frog!");
                gui.println("Tiana: It's not slime, it's mucus!");
                gui.println("And, I can use the sticky mucus to do this!");
                gui.println("");
                gui.println("You were able to use your tongue to snatch the talisman!");
                gui.println("");
                gui.println("You smashed the talisman and destroyed Doctor Facilier!");
                gui.println("Charlotte kisses Naveen as a frog and the curse is broken!");
                gui.println("");
                gui.println("Pick up the talisman pieces and move forward!");
                canTakeTalisman = true;
            }
            else if((intNumberGuessed < numOfSpirits) && (intNumberGuessed >= numOfSpiritsMinus5)){
                gui.println("");
                gui.println("You were within the range!");
                gui.println("The answer was " + numOfSpirits);
                gui.println("");
                gui.println("Doctor Facilier: Now you'll spend the rest of your life");
                gui.println("being a slimy frog!");
                gui.println("Tiana: It's not slime, it's mucus!");
                gui.println("");
                gui.println("You were able to use your tongue to snatch the talisman!");
                gui.println("");
                gui.println("You smashed the talisman and destroyed Doctor Facilier!");
                gui.println("Charlotte kisses Naveen as a frog and the curse is broken!");
                gui.println("");
                gui.println("Pick up the talisman pieces and move forward!");
                canTakeTalisman = true;
            }
            else if((intNumberGuessed > numOfSpirits) && (intNumberGuessed <= numOfSpiritsPlus5)){
                gui.println("");
                gui.println("You were within the range!");
                gui.println("The answer was " + numOfSpirits);
                gui.println("");
                gui.println("Doctor Facilier: Now you'll spend the rest of your life");
                gui.println("being a slimy frog!");
                gui.println("Tiana: It's not slime, it's mucus!");
                gui.println("");
                gui.println("You were able to use your tongue to snatch the talisman!");
                gui.println("");
                gui.println("You smashed the talisman and destroyed Doctor Facilier!");
                gui.println("Charlotte kisses Naveen as a frog and the curse is broken!");
                gui.println("");
                gui.println("Pick up the talisman pieces and move forward!");
                canTakeTalisman = true;
            }
            else{
                gui.println("");
                gui.println("You did not earn the talisman on your first try,");
                gui.println("but after some hard work you got it!");
                gui.println("The answer was " + numOfSpirits);
                gui.println("");
                gui.println("Since you did not get the talisman on your first try,");
                gui.println("by the time you got it and Charlotte kissed Naveen as");
                gui.println("a frog, it was past midnight! The curse was not broken!");
                gui.println("");
                gui.println("You must remember the final clue you learned, that love is");
                gui.println("all you need! With that, use the marry command to break the curse!");
                canMarry = true;
            }
        }
        
    }
    
    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }
    
}
