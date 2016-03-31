/*
 * VERSION: 1.2
 * Program: AdvanceAndConquerMethods1.java
 * Author's: Joey Lyon, Nick DiRisio
 * Date Started: May 9th, 2013
 * Modified: May 27th
 * Description: class full of needed methods. the focus of this class is for basic methods for the menu, input, etc
 */
//Public Methods Private Data!!!!
import java.io.*; // imports nesscisary files from the api needed for user input

public class AdvanceAndConquerMethods1 // creation of methods 1
{
  InputStreamReader isr = new InputStreamReader(System.in); // creates an object of input streamreader, which allows for user input
  BufferedReader br = new BufferedReader(isr); // the buffered reader constructs data from the Input stream Reader so the the user can input
  
  //Variables
  private String s = ""; // an empty string for user input
  private boolean submenu = true;
  private boolean flag = true;
  private boolean enableCpu = false; // This boolean will be the yes or no for player vs computer
  private boolean select = true;
  
  
  
  // Methods Start here
  
  public String getInput() // this method will get input and return the string it gets
  {
    try
    {
      s = br.readLine(); // creates a line where the user can input stuff
    } // end of try
    
    catch(IOException ex) // this is for if user input goes wrong
    {
      System.out.println("Oops! The game has crashed, you might have inputted a letter when it asked for a number. Please restart the game. :)");
      ex.printStackTrace();  // prints a stack trace if theres an error
    } // end of catch
    
    
    return s; // throws back the string s. Even if it doesnt land in anything, it will still be changed in this class
    
  } // end of method
  
  
  public void startMsg() // this method displays the intro message
  {
    // These are just some messaegs that are displayed
    System.out.println("Welcome to AdvanceAndConquer!");
    System.out.println("This is a game designed by Nick DiRisio and Joey Lyon at ClubCool Games!");
    System.out.println("To start, input start, for some help, input help, and to quit, input quit");
    
  } // end of method
  
  
  public void helpMsg() // this method will display a help message
  {
    System.out.println("*********HELP*********");
    System.out.println("Two maps have been created. Currently only Wasteland is available for play. ");
    System.out.println("All units and bases are capitalized for player 1 (left side) and lower case for player 2 (right side).");
    System.out.println("You begin with 1000 dollars starting gold. Each turn you are given 100 charity gold.");
    System.out.println("Troops can be created by typing yes when prompted.");
    System.out.println("You can purchase three different types of units: Jets ($700, 150hp, 125 atk, 65def), Tanks($1000, 200 hp, 150 atk, 75 def), and Infantry ($350, 125hp, 100 atk, and 55 def).");
    System.out.println("They will be spawned next to either of your homebases.");
    System.out.println("The weakest troop is infantry, the next strongest is the jet, and the strongest is the tank.");
    System.out.println("There are two oil derricks available on each faction's side available forcapture. Capture by simply moving a unit onto the your sides \"D\".");
    System.out.println("When captured these derricks change to \"C/c\" respectively derricks generate an additional 100 gold each turn each. You can also capture opponent derricks.");
    System.out.println("A troops damage is based on the troop who's attacking and who's defending. Double damage is awarded to the attacking troop if:");
    System.out.println("Infantry Attacks Jet");
    System.out.println("Jet Attacks Tank");
    System.out.println("Tank Attacks Troop");
    System.out.println("A unit can move a maximum of 3 spaces up or down and 10 spaces left or right.");
    System.out.println("A unit may attack an enemy unit or base when they are within a 1 square unit range up, down, right or left.");
    System.out.println("You win by destroying both of your opponents bases.");
    System.out.println("Please refrece the readme for any extra help.");
  } // end of help msg
  
  public boolean yesNo() // this method is for confirmation of input
  {
    submenu = true;
    do
    { // start of nested menu loop
      s = getInput();
      if (s.equalsIgnoreCase("yes"))
      { 
        // setting these booleans to false will leave the loops
        select = false;
        submenu = false; 
      } // end of if
      else if (s.equalsIgnoreCase("no")) // if the user does not want the selection
      {
        select = true;
        submenu = false;
      } //  end of submenu
      else
      {
        System.out.println("Sorry, try yes or no"); 
      }
    } 
    while (submenu);
    
    return select;
  } // end of method
  
  public void failSafe() //   for incase the user inputs something odd
  {
    System.out.println("Sorry, that is not a command");
  }
  
  
  
  
  public boolean pVp() // this method will decide if the user wants PVP or Player vs computer
  {
    System.out.println("Would you like to play against another human, or play the computer. (input \"Human\" or \"computer\")");
    do 
    {
      s = getInput();// gets input
      
      if (s.equalsIgnoreCase("human")) // if the user wants to play against a human
      {
        System.out.println("Player Vs. Player Selected");
// enable cpu is natually set to false so we do not have to change it
        flag = false; // ends loop
      } //  end of if
      
      else if (s.equalsIgnoreCase("computer")) // if the user wants to play against the computer...
      {
        System.out.println("Computer opponent coming soon!!!! In the mean time, please select human opponent.");
        // ends loop
      } // end of else if 
      
      else 
      {
        failSafe(); // In case the user inputs something off
      }
    } // 
    while (flag);
    return enableCpu; // throws back the boolean for player control
  } //  end of method 
  
// if enableCpu is false, then its player vs player. if it is true, then it is player vs computer  
  
  
  
  
  
  
} // end of class 
