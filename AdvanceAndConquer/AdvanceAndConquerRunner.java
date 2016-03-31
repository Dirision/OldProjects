  /*
 * VERSION: 1.2
 * Program: AdvanceAndConquerRunner.java
 * Author's: Joey Lyon, Nick DiRisio
 * Date Started: May 9th, 2013
 * Modified: May 27th
 * Description: Main runner class of our game
 */

public class AdvanceAndConquerRunner // start of class
{
  
  public static void main(String[] args)// creationg of main. the "ignition switch" of the program
  {
    AdvanceAndConquerMethods1 methods = new AdvanceAndConquerMethods1(); // creates an object of the methods1 class so that we can get methods and data from it 
    TroopData troop = new TroopData();
    
    
    
    
    String s = ""; // This string will hold user empty data
    boolean menu = true; // booleans will control the flow 
    boolean submenu = true;
    boolean enableCpu = false; // this boolean will be the yes/no factor to play against the computer
    boolean game = true; // 
    methods.startMsg();
    
    do // this do loop is for the cycling of options 
    {
      s = methods.getInput(); // gets input and assigns it to s
      if (s.equalsIgnoreCase("help")) // if the user wants help
      {
        methods.helpMsg(); // display help
      } // end of if
      
      else if (s.equalsIgnoreCase("quit"))
      {
        menu = false; // ends the loop
        System.out.println("Thank you for trying Advance And Conquer!");
      } // end of else if 
      
      else if (s.equalsIgnoreCase("start")) // if the user wants to start
      {
        menu = false;
//  START BEGINS HERE
        System.out.println("Start!");
       
        // Deciding to play against pc or human here
        enableCpu = methods.pVp();






// Map selection
        do
        {
          System.out.println("What map would you like? Current maps are: Wasteland, Mountain Range");
          menu = true;
          s = methods.getInput();
          if (s.equalsIgnoreCase("wasteland"))
          {
            troop.wasteland(); // fills map with wastelands data
           troop.printMap(); // prints the map
       
          System.out.println("Is this the map you want?"); // asks if the user wants this map
          menu = methods.yesNo(); // this is for confirmation of the users choices
          }
          else if (s.equalsIgnoreCase("mountain range"))
          {
            
            troop.mountainRange();
            troop.printMap();
            System.out.println("This map is coming soon in a future update! Please select wasteland!");
                                 }
          
        } // end of loop
        while(menu);
         // END OF PICKING MAP OMG THAT TOOK TOO LONG
        
        troop.getFaction();
        
        // THIS IS WHERE IF HUMAN ELSE IF COMPUTER
        if (enableCpu == false) // if cpu is disabled
        {
          
                       //         ORDER:
          troop.printMap(); //    Prints map
          troop.makeBankEmpty();//Empties both of the players banks
          troop.baseData1(); //   Gets data for each base
          troop.baseData2(); //   
          while(game)         //  the game is in a while loop until broken
          {
          
          troop.showTurn(); //    Shows turn info; who and how much $
          troop.troopTurn();//    Each troop gets their turn
          game = troop.winCheck();// checks if the player won that turn
          
          
          } // end of while
        } // end of if
        
        
      } // end of start
      
      else // in case the user inputs anything odd
      {
        System.out.println("Sorry, that is not a command, try start, quit, or help!");
      } // end of else
      
    } // end of loop
    while(menu);
  } // end of main
  
} // end of class
