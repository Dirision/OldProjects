/*
 * VERSION: 1.2
 * Program: Troopdata.java
 * Author's: Joey Lyon, Nick DiRisio
 * Date Started: May 9th, 2013
 * Modified: May 27th
 * Description: This class will contain troop data and info
 */

public class TroopData
{ // start of class
  AdvanceAndConquerMethods1 methods = new AdvanceAndConquerMethods1(); // used for getting methods  
  
  private int turn = 0; // this is for deciding whose turn it is
  private int player = 0; // this will be found when show turn is called. this will be turn modulous 2 and if it is 0, it is player ones turn, if odd, player twos
  private int troopcount = 0; // this will count the number of troops
  private int basecount1 = 2; // this will count the number of bases
  private int basecount2 = 2; // this will count the number of bases for player2
  private int player1money = 1000; // starting money values for each player
  private int player2money = 1000;
  private int player1Derricks = 0; // this two derrick counters are for giving out money and to check if the user has control over them
  private int player2Derricks = 0;
  private int tracker = 0; // tracks numbers for various things
  private int count = 0; // counter for counting during the attack phase
  private int spot = 0; // for finding the specific spot in the array
  private int damage = 0; //  this is the amount of damage done
  private String xpos = ""; // for recording x position
  private String ypos = ""; // for recording y position  
  
  private String[][] player1TroopBank = new String[12][8]; // this will contain player ones troop information
  private String[][] player2TroopBank = new String[12][8]; // they can have ten troops and start with 2 bases
  
  private boolean game = true; // this boolean is yes or no to next turn and will be returned to the runner
  
  private int moveData = 0; // this int is be used to move things  
  
  private String s = ""; // empty string for user input
  private boolean flag = true; // for control flow
  private boolean flag2 = true; // for various control flow
  private boolean empty = true;// if bank is empty
  private boolean submenu = true; // this is for checking if troops can be created
  private boolean attackloop = true; // variable for attack flow control
  
  private int counter  = 0; // a small counter 
  
  
  // MAP VARIBLES HERE
  
  private int rndm = 0; //random number for map gen
  private String input = ""; //string to hold parsed random number
  private String landscape[] = {".", "~", "^"}; //string to hold generated array landscape types
  
  //# walls, 0 grass, 1 mountains, 2 water
  private String[][] map = new String[10][50];
  private String[][] mapcopy = new String[10][50];
  
  //faction 
  private String[] faction = {"China", "USA", "Russia"};
  private String[] faction_player = new String[2];
  
  
  // START METHODS HERE
  public void baseData1() // fills spots in the troop back for the player1's starting bases
  {
    // fills both players base base stats
    
    for(int i = 0; i < 2; i++)
    {
      player1TroopBank[i][0] = "BASE";
      player1TroopBank[i][1] = "BASE";
      player1TroopBank[i][2] = "100";
      player1TroopBank[i][3] = "0";
      player1TroopBank[i][4] = "75";
      player1TroopBank[i][5] = "no";
      player1TroopBank[i][6] = "1";
      player1TroopBank[i][7] = Integer.toString(Integer.parseInt("3") +(i*3));
    } 
    
  } // end method 
  public void baseData2() // this will fill player 2's bank with base data
  {
    for (int t = 0; t < 2; t++)
    {
      player2TroopBank[t][0] = "BASE";
      player2TroopBank[t][1] = "BASE";
      player2TroopBank[t][2] = "100";
      player2TroopBank[t][3] = "0";
      player2TroopBank[t][4] = "75";
      player2TroopBank[t][5] = "no";
      player2TroopBank[t][6] = "48";
      player2TroopBank[t][7] = Integer.toString(Integer.parseInt("3") +(t*3));
      
      
    }
    
  } // end method
  
  /*
   * Each row in the troop bank array is a unique troops information
   * Data for each coloum position is as following
   * INDEX # OF COLOUM:
   * 0: troops' name
   * 1: type (troop, jet, or tank)
   * 2: the troops HP
   * 3: Atk points
   * 4: Def points
   * 5: used (This is to check is the troop was used that turn, this is either yes or no -> I never actually used this, and then i just used a for loop for troop selection 
   * 6: xposition (This is the troops x position on the map
   * 7: yposition (This is the troops y position on the map
   */
  
  public void makeBankEmpty() // this will fill the troop banks with "nothing"
  {
    for ( int i = 0; i < 12; i++) // this for loop will cycle through the rows
    {
      for (int a = 0; a < 8; a++) // this nested loop will cycle through coloums
      {
        player1TroopBank[i][a] = "nothing";
        player2TroopBank[i][a] = "nothing";
      } // end of nested loop
    } // end of for
  } // end of method
  
  
  public void showTurn() // this will display whose turn it is at the beginning of each turn
  {
    player = turn % 2; // finds even or odd for player turn
    if (player == 0) // if turn is even (can be divisible by two with no remainder) then is is player ones turn
    {
      System.out.println("It is player 1's turn!"); // tells user whose turn it is
      System.out.println("Player 1 has " +player1money +" dollars left");
    } // end of if
    else 
    {
      System.out.println("It is player 2's turn!");
      System.out.println("Player 2 has " +player2money +" dollars left");
    } //end if else 
    
  } // end of method
  
  
  
  
  
  public void createTroop() // this method is for spawning troops
  {
    if (player == 0)
    {
      if(empty && player1money > 350)
      {
        System.out.println("Would you like to create any troops? (yes/no)");
        flag = methods.yesNo();
        if (flag != true) // if yes, its like this becuase yesNo() was originally created for loop control and the menu, but then we decided to use it for other things
        {
          
          
          for (int b = 0; b < 10; b++)
          {
            if(player1TroopBank[b][0].equalsIgnoreCase("nothing"))
            {
              System.out.println("What is the troops name?");
              player1TroopBank[b][0] = methods.getInput();
              
              System.out.println("What type of troop will it be? \"Jet\" = $700, \"Tank\" = $1000, and \"Infantry\" = $350"); // gives cost and diffrent troops
              flag2 = true; 
              do
              {
                s = methods.getInput();
                if (s.equalsIgnoreCase("tank") & player1money >= 1000) // if tank
                {
                  flag2 = false; // ends loop
                  player1money -= 1000; // subtracts cost of troop
                  
                  player1TroopBank[b][1] = "Tank";
                  player1TroopBank[b][2] = "200";
                  player1TroopBank[b][3] = "150";
                  player1TroopBank[b][4] = "75";
                  player1TroopBank[b][5] = "no";
                  player1TroopBank[b][6] = "2";
                  player1TroopBank[b][7] = Integer.toString(Integer.parseInt(player1TroopBank[tracker][7]) );
                  
                  spawnTroop(Integer.parseInt(player1TroopBank[b][7]), Integer.parseInt(player1TroopBank[b][6]), 1); // this line of code sends coordinates and a type to the spawn troop method, so things are created on the map
                  b = 13; // to leave the loop
                } // end of if for creating tank
                
                else if (s.equalsIgnoreCase("jet") & player1money>= 700) //if the user wants a jet
                {
                  
                  player1money -= 700;
                  flag2 = false;
                  // this fills the bank with the troops data
                  player1TroopBank[b][1] = "Jet";
                  player1TroopBank[b][2] = "150";
                  player1TroopBank[b][3] = "125";
                  player1TroopBank[b][4] = "65";
                  player1TroopBank[b][5] = "no";
                  player1TroopBank[b][6] = "2";
                  player1TroopBank[b][7] = Integer.toString(Integer.parseInt(player1TroopBank[tracker][7]) );
                  spawnTroop(Integer.parseInt(player1TroopBank[b][7]), Integer.parseInt(player1TroopBank[b][6]), 2);
                  b = 13; // to leave the loop
                }
                
                else if(s.equalsIgnoreCase("infantry") & player1money >= 350)
                {
                  player1money -= 350; // subtracts the cost for infantry
                  flag2 = false; // this and the next line are for ending loops
                  
                  player1TroopBank[b][1] = "Infantry";
                  player1TroopBank[b][2] = "125";
                  player1TroopBank[b][3] = "100";
                  player1TroopBank[b][4] = "55";
                  player1TroopBank[b][5] = "no";
                  player1TroopBank[b][6] = "2";
                  player1TroopBank[b][7] = Integer.toString(Integer.parseInt(player1TroopBank[tracker][7]) );
                  
                  spawnTroop(Integer.parseInt(player1TroopBank[b][7]), Integer.parseInt(player1TroopBank[b][6]), 3);
                  b = 13;
                } // end of else if infantry
                
                else
                {
                  System.out.println("Sorry, but that is not a type of troop. Try: Infantry, Jet, or Tank, or you cannot afford that troop");
                  
                  
                } // end of else 
              }
              while(flag2); // end of loop
            } // end of if for finding empty space
          } // end of searching for open spot
        } // end of if
        else
          System.out.println("Okay, Next Troop!");
      } // if nothing is in troop bank & money
      else
      {
        System.out.println("Sorry, you can't make any troops because you don't have enough money or you have the max number of troops!");
      } // end of else
    } //  end of if player 1
    else
    {
      if(empty && player2money > 350)
      {
        System.out.println("Would you like to create any troops? (yes/no)");
        flag = methods.yesNo();
        if (flag != true) // if yes, its like this becuase yesNo() was originally created for loop control and the menu, but then we decided to use it for other things
        {
          
          System.out.println("What is the troops name?");
          for (int b = 0; b < 12; b++)
          {
            if(player2TroopBank[b][1].equalsIgnoreCase("nothing"))
            {
              
              player2TroopBank[b][0] = methods.getInput();
              
              System.out.println("What type of troop will it be? \"Jet\" = $700, \"Tank\" = $1000, and \"Infantry\" = $350"); // gives cost and diffrent troops
              flag2 = true; 
              do
              {
                s = methods.getInput();
                if (s.equalsIgnoreCase("tank") & player2money >= 1000) // if tank
                {
                  flag2 = false; // ends loop
                  player2money -= 1000; // subtracts cost of troop
                  
                  player2TroopBank[b][1] = "Tank";
                  player2TroopBank[b][2] = "200";
                  player2TroopBank[b][3] = "150";
                  player2TroopBank[b][4] = "75";
                  player2TroopBank[b][5] = "no";
                  player2TroopBank[b][6] = "47";
                  player2TroopBank[b][7] = Integer.toString(Integer.parseInt(player2TroopBank[tracker][7]) );
                  spawnTroop(Integer.parseInt(player2TroopBank[b][7]), Integer.parseInt(player2TroopBank[b][6]), 1);
                  b = 13; // to leave the loop
                } // end of if for creating tank
                
                else if (s.equalsIgnoreCase("jet") & player2money>= 700) //if the user wants a jet
                {
                  
                  player2money -= 700;
                  flag2 = false;
                  // this fills the bank with the troops data
                  player2TroopBank[b][1] = "Jet";
                  player2TroopBank[b][2] = "150";
                  player2TroopBank[b][3] = "125";
                  player2TroopBank[b][4] = "65";
                  player2TroopBank[b][5] = "no";
                  player2TroopBank[b][6] = "47";
                  player2TroopBank[b][7] = Integer.toString(Integer.parseInt(player2TroopBank[tracker][7])  );
                  spawnTroop(Integer.parseInt(player2TroopBank[b][7]), Integer.parseInt(player2TroopBank[b][6]), 2);
                  b = 13; // to leave the loop
                }
                
                else if(s.equalsIgnoreCase("infantry") & player2money >= 350)
                {
                  player2money -= 350; // subtracts the cost for infantry
                  flag2 = false; // this and the next line are for ending loops
                  
                  player2TroopBank[b][1] = "Infantry";
                  player2TroopBank[b][2] = "125";
                  player2TroopBank[b][3] = "100";
                  player2TroopBank[b][4] = "55";
                  player2TroopBank[b][5] = "no";
                  player2TroopBank[b][6] = "47";
                  player2TroopBank[b][7] = Integer.toString(Integer.parseInt(player2TroopBank[tracker][7]) );
                  
                  spawnTroop(Integer.parseInt(player2TroopBank[b][7]), Integer.parseInt(player2TroopBank[b][6]), 3);
                  b = 13;
                  
                } // end of else if infantry
                
                else
                {
                  System.out.println("Sorry, but that is not a type of troop. Try: Infantry, Jet, or Tank, or you cannot afford that troop");
                  
                  
                } // end of else 
              }
              while(flag2); // end of loop
            } // end of if for finding empty space
          } // end of searching for open spot
        } // end of if
        else
          System.out.println("Okay, Next Troop!");
      } // if nothing is in troop bank & money
      else
      {
        System.out.println("Sorry, you can't make any troops because you have no money or you have the max number of troops!");
      } // end of else
      
    } // end of else for player2 
    
  } // end method
  
  
  
  public void troopTurn() // creates turns for each troop so that each troop does things
  {
    if (player == 0)
    {
      for (int l = 0; l < 12; l++)
      {
        if (player1TroopBank[l][1].equalsIgnoreCase("base")) // bases create troops so on a base turn you can create troops
        {
          tracker = l;
          createTroop();
          
        }
        else 
        { // if not base
          if( !(player1TroopBank[l][1].equalsIgnoreCase("nothing")))
          { // and if not nothing
            
            {
              tracker = l; // this tracker will be refrenced in other methods 
              System.out.println("Would you like to move " +player1TroopBank[l][0] +"?" +" (TYPE: " +player1TroopBank[l][1] +", Hp: " +player1TroopBank[l][2] +")");
              System.out.println("Coordinates: (X:" +player1TroopBank[l][6] +", Y:" +(9-Integer.parseInt(player1TroopBank[l][7])) +")"); // I learned that to find the y-pos in an upwards manner, I would take the max value you can go, added one, then subtracted the amount.
              flag = methods.yesNo();
              if (flag != true) // if yes
              {
                moveTroop();
               
              } // end of if for troop movement
               attackTroop();
            } // end of else if for if the troop is a troop and not a base 
            
          }
        } // end of else 
        
      } // end of searching through troop bank 
      player1money = player1money + (100 + player1Derricks*100);
      System.out.println("You gained " +(100 + player1Derricks*100) +" dollars");
    } // end of player 1 
    else
    {
      for (int w = 0; w < 12; w++)
      {
        if (player2TroopBank[w][1].equalsIgnoreCase("base")) // bases create troops so on a base turn you can create troops
        {
          
          tracker = w;
          createTroop();
          
        }
        else 
        { // if not base
          if( !(player2TroopBank[w][1].equalsIgnoreCase("nothing")))
          { // and if not nothing
            
            {
              tracker = w; // this tracker will be refrenced in other methods 
              System.out.println("Would you like to move " +player2TroopBank[w][0] +"?" +" (TYPE: " +player2TroopBank[w][1] +", Hp: " +player2TroopBank[w][2] +")");
              System.out.println("Coordinates: (X:" +player2TroopBank[w][6] +", Y:" +(9-Integer.parseInt(player2TroopBank[w][7])) +")"); // I learned that to find the y-pos in an upwards manner, I would take the max value you can go, added one, then subtracted the amount.
              flag = methods.yesNo();
              if (!flag) // if yes
              {
                moveTroop();
              } // end of if yes for move troop
              attackTroop(); // for attacking
            } // end of else if for if the troop is a troop and not a base 
            
          }
        }
        
      } // end of searching through troop bank 
      player2money =player2money +(100 + player2Derricks*100);
      System.out.println("You gained " +(100 + player2Derricks*100) +" dollars");
      
    } // end of else
    turn++;
  } // end method 
  
  
  
  
  
  
  private boolean isBankEmpty() // checks to see if there if the troopbank is full
  {
    counter = 0;
    if (player == 0)
    {
      for(int a = 0; a < 12; a++) 
      {
        if(player1TroopBank[a][0].equalsIgnoreCase("nothing"))
          counter++;
      }// end of for
      if (counter == 10)
        empty = false; // if there are ten empties, then it is empty and troops cannot be produced
      else if (counter != 10)
        empty = true; // if counter is not 10, then troops can be produced
      
    } // end of if
    else
    {
      for(int a = 0; a < 12; a++) 
      {
        if(player2TroopBank[a][0].equalsIgnoreCase("nothing"))
          counter++;
      }// end of for
      if (counter == 10)
        empty = false; // if there are ten empties, then it is empty and troops cannot be produced
      else if (counter != 10)
        empty = true; // if counter is not 10, then troops can be produced
      
    } // end of if
    return empty;
  } //end of method 
  
  
  public void moveTroop() // this method will move a troop, i also had it print the new troops position too
  {
    if (player == 0)
    { 
      
      flag2 = true;
      while(flag2)
      {
        System.out.println("Which direction would you like to move " +player1TroopBank[tracker][0] +"? (up, left, down, right)"); // intro to move + directions
        s = methods.getInput(); // gets input
        if(s.equalsIgnoreCase("up")) // if up
        {
          System.out.println("By how much? (troops cannot move move than 3 spaces up or down)");
          s = methods.getInput();
          moveData = Integer.parseInt(s);
          if ((Integer.parseInt(player1TroopBank[tracker][7]) - moveData) <= 0 | moveData > 3  )
          {
            System.out.println("Sorry, but that move is out of bounds, try moving less or in another direction");
          }
          // I created this if statement to check if there is already something there before the troop moves. It simply looks at the future position of the troop
          
          else if (!mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) -moveData][Integer.parseInt(player1TroopBank[tracker][6])].equals(" ") & !mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) -moveData ][Integer.parseInt(player1TroopBank[tracker][6]) ].equalsIgnoreCase("D"))       
          {
            
            System.out.println("Sorry,there is already something there");
            
          } 
          else
          {
            
            mapcopy[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6])] = map[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6])];
            player1TroopBank[tracker][7] = Integer.toString(Integer.parseInt(player1TroopBank[tracker][7]) - moveData); // by subtracting from the y coordinate, we move up
            if (player1TroopBank[tracker][1].equalsIgnoreCase("tank")) // checks to see if tank
            {
              counter = 1; // sets the counter so that a tank is spawned 
            }
            else if (player1TroopBank[tracker][1].equalsIgnoreCase("jet")) // checks to see if jet
            {
              counter = 2; // sets the counter so that a jet is spawned 
            }
            else if (player1TroopBank[tracker][1].equalsIgnoreCase("infantry")) // checks to see if infantry
            {
              counter = 3; // sets the counter so that infantry is spawned 
            }
            spawnTroop(Integer.parseInt(player1TroopBank[tracker][7]), Integer.parseInt(player1TroopBank[tracker][6]), counter);// throws specific data to the movetroop method. this data includes, y pos, x pos, and type. 
            
            flag2 = false; // ends loop
            checkCapOil(Integer.parseInt(player1TroopBank[tracker][7]), Integer.parseInt(player1TroopBank[tracker][6]));
          } // end of else 
        } // end of if for up
        
        else if (s.equalsIgnoreCase("down"))
        {
          System.out.println("By how much? (troops cannot move move than 3 spaces up or down)");
          s = methods.getInput(); // gets the value for distance
          moveData = Integer.parseInt(s); 
          if ((Integer.parseInt(player1TroopBank[tracker][7]) +moveData) >= 10 | moveData > 3)
          {
            System.out.println("Sorry, but that move is out of bounds, try moving less or in another direction");
          }
          else if (!mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) +moveData][Integer.parseInt(player1TroopBank[tracker][6])].equals(" ") & !mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) +moveData ][Integer.parseInt(player1TroopBank[tracker][6]) ].equalsIgnoreCase("D"))       
          {
            
            System.out.println("Sorry,there is already something there");
            
          } 
          else
          {
            
            mapcopy[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6])] = map[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6])];
            player1TroopBank[tracker][7] = Integer.toString(Integer.parseInt(player1TroopBank[tracker][7]) +moveData); // by adding to the y coordinate, we move down
            if (player1TroopBank[tracker][1].equalsIgnoreCase("tank")) // checks to see if tank
            {
              counter = 1; // sets the counter so that a tank is spawned 
            }
            else if (player1TroopBank[tracker][1].equalsIgnoreCase("jet")) // checks to see if jet
            {
              counter = 2; // sets the counter so that a jet is spawned 
            }
            else if (player1TroopBank[tracker][1].equalsIgnoreCase("infantry")) // checks to see if infantry
            {
              counter = 3; // sets the counter so that infantry is spawned 
            }
            spawnTroop(Integer.parseInt(player1TroopBank[tracker][7]), Integer.parseInt(player1TroopBank[tracker][6]), counter);// throws specific data to the movetroop method. this data includes, y pos, x pos, and type. 
            flag2 = false; // ends loop
            checkCapOil(Integer.parseInt(player1TroopBank[tracker][7]), Integer.parseInt(player1TroopBank[tracker][6]));
          } // end of else 
          
        } // end of down
        
        else if (s.equalsIgnoreCase("right"))
        {
          System.out.println("By how much? (troops cannot move move than 10 spaces left or right)");
          
          
          s = methods.getInput();
          moveData = Integer.parseInt(s);
          if ((Integer.parseInt(player1TroopBank[tracker][6]) +moveData) >= 50 | moveData > 15)
          {
            System.out.println("Sorry, but that move is out of bounds, try moving less or in another direction");
          }
          else if (!mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6]) +moveData].equals(" ") & !mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6]) +moveData].equalsIgnoreCase("D"))
          {
            
            System.out.println("Sorry,there is already something there");
            
          } 
          else
          {
            
            mapcopy[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6])] = map[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6])];
            player1TroopBank[tracker][6] = Integer.toString(Integer.parseInt(player1TroopBank[tracker][6]) +moveData); // by adding to the x coordinate, we move right
            if (player1TroopBank[tracker][1].equalsIgnoreCase("tank")) // checks to see if tank
            {
              counter = 1; // sets the counter so that a tank is spawned 
            }
            else if (player1TroopBank[tracker][1].equalsIgnoreCase("jet")) // checks to see if jet
            {
              counter = 2; // sets the counter so that a jet is spawned 
            }
            else if (player1TroopBank[tracker][1].equalsIgnoreCase("infantry")) // checks to see if infantry
            {
              counter = 3; // sets the counter so that infantry is spawned 
            }
            spawnTroop(Integer.parseInt(player1TroopBank[tracker][7]), Integer.parseInt(player1TroopBank[tracker][6]), counter);// throws specific data to the movetroop method. this data includes, y pos, x pos, and type. 
            flag2 = false; // ends loop
            checkCapOil(Integer.parseInt(player1TroopBank[tracker][7]), Integer.parseInt(player1TroopBank[tracker][6]));
          }
        } // end of right
        
        else if (s.equalsIgnoreCase("left"))
        {
          System.out.println("By how much? (troops cannot move move than 10 spaces left or right)");
          
          
          s = methods.getInput();
          moveData = Integer.parseInt(s);
          if ((Integer.parseInt(player1TroopBank[tracker][6]) -moveData) <=0 | moveData > 15 )
          {
            System.out.println("Sorry, but that move is out of bounds, try moving less or in another direction");
          }
          else if (!mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6])-moveData].equals(" ") & !mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6]) -moveData].equalsIgnoreCase("D"))
          {
            
            System.out.println("Sorry,there is already something there");
            
          } 
          else
          {
            // the line below this is to revert the position on the map back to its original way before a troop was on it
            mapcopy[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6])] = map[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6])];
            player1TroopBank[tracker][6] = Integer.toString(Integer.parseInt(player1TroopBank[tracker][6]) -moveData); // by subtracting from the x coordinate, we move left
            if (player1TroopBank[tracker][1].equalsIgnoreCase("tank")) // checks to see if tank
            {
              counter = 1; // sets the counter so that a tank is spawned 
            }
            else if (player1TroopBank[tracker][1].equalsIgnoreCase("jet")) // checks to see if jet
            {
              counter = 2; // sets the counter so that a jet is spawned 
            }
            else if (player1TroopBank[tracker][1].equalsIgnoreCase("infantry")) // checks to see if infantry
            {
              counter = 3; // sets the counter so that infantry is spawned 
            }
            spawnTroop(Integer.parseInt(player1TroopBank[tracker][7]), Integer.parseInt(player1TroopBank[tracker][6]), counter);// throws specific data to the movetroop method. this data includes, y pos, x pos, and type. 
            flag2 = false; // ends loop
            checkCapOil(Integer.parseInt(player1TroopBank[tracker][7]), Integer.parseInt(player1TroopBank[tracker][6]));
          }
          
        } // end of left
        
        else
        {
          System.out.println("Sorry, but directions are up, down, left, and right. Please input first the direction, then by the number of spaces");
        } // end of else
      }// end of while for inpput
      
    } // end of if p1
    
    else
    {
      flag2 = true;
      while(flag2)
      {
        System.out.println("Which direction would you like to move " +player2TroopBank[tracker][0] +"? (up, left, down, right)"); // intro to move + directions
        s = methods.getInput(); // gets input
        if(s.equalsIgnoreCase("up")) // if up
        {
          System.out.println("By how much? (troops cannot move move than 3 spaces up or down)");
          s = methods.getInput();
          moveData = Integer.parseInt(s);
          if ((Integer.parseInt(player2TroopBank[tracker][7]) - moveData) <= 0 | moveData > 3  )
          {
            System.out.println("Sorry, but that move is out of bounds, try moving less or in another direction");
          }
          else if (!mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) -moveData ][Integer.parseInt(player2TroopBank[tracker][6])].equals(" ") & !mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) -moveData ][Integer.parseInt(player2TroopBank[tracker][6]) ].equalsIgnoreCase("D"))
          {
            
            System.out.println("Sorry,there is already something there");
            
          } 
          else
          {
            
            mapcopy[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6])] = map[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6])];
            player2TroopBank[tracker][7] = Integer.toString(Integer.parseInt(player2TroopBank[tracker][7]) - moveData); // by subtracting from the y coordinate, we move up
            if (player2TroopBank[tracker][1].equalsIgnoreCase("tank")) // checks to see if tank
            {
              counter = 1; // sets the counter so that a tank is spawned 
            }
            else if (player2TroopBank[tracker][1].equalsIgnoreCase("jet")) // checks to see if jet
            {
              counter = 2; // sets the counter so that a jet is spawned 
            }
            else if (player2TroopBank[tracker][1].equalsIgnoreCase("infantry")) // checks to see if infantry
            {
              counter = 3; // sets the counter so that infantry is spawned 
            }
            spawnTroop(Integer.parseInt(player2TroopBank[tracker][7]), Integer.parseInt(player2TroopBank[tracker][6]), counter);// throws specific data to the movetroop method. this data includes, y pos, x pos, and type. 
            flag2 = false; // ends loop
            checkCapOil(Integer.parseInt(player2TroopBank[tracker][7]), Integer.parseInt(player2TroopBank[tracker][6]));
          } // end of else 
        } // end of if for up
        
        else if (s.equalsIgnoreCase("down"))
        {
          System.out.println("By how much? (troops cannot move move than 3 spaces up or down)");
          s = methods.getInput(); // gets the value for distance
          moveData = Integer.parseInt(s); 
          if ((Integer.parseInt(player2TroopBank[tracker][7]) +moveData) >= 10 | moveData > 3)
          {
            System.out.println("Sorry, but that move is out of bounds, try moving less or in another direction");
          }
          
          else if (!mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) +moveData ][Integer.parseInt(player2TroopBank[tracker][6])].equals(" ") & !mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) + moveData][Integer.parseInt(player2TroopBank[tracker][6])].equalsIgnoreCase("D"))
          {
            
            System.out.println("Sorry,there is already something there");
            
          } 
          else
          {
            mapcopy[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6])] = map[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6])];
            player2TroopBank[tracker][7] = Integer.toString(Integer.parseInt(player2TroopBank[tracker][7]) +moveData); // by adding to the y coordinate, we move down
            if (player2TroopBank[tracker][1].equalsIgnoreCase("tank")) // checks to see if tank
            {
              counter = 1; // sets the counter so that a tank is spawned 
            }
            else if (player2TroopBank[tracker][1].equalsIgnoreCase("jet")) // checks to see if jet
            {
              counter = 2; // sets the counter so that a jet is spawned 
            }
            else if (player2TroopBank[tracker][1].equalsIgnoreCase("infantry")) // checks to see if infantry
            {
              counter = 3; // sets the counter so that infantry is spawned 
            }
            spawnTroop(Integer.parseInt(player2TroopBank[tracker][7]), Integer.parseInt(player2TroopBank[tracker][6]), counter);// throws specific data to the movetroop method. this data includes, y pos, x pos, and type. 
            flag2 = false; // ends loop
            checkCapOil(Integer.parseInt(player2TroopBank[tracker][7]), Integer.parseInt(player2TroopBank[tracker][6]));
          } // end of else 
          
        } // end of down
        
        else if (s.equalsIgnoreCase("right"))
        {
          System.out.println("By how much? (troops cannot move move than 10 spaces left or right)");
          
          
          s = methods.getInput();
          moveData = Integer.parseInt(s);
          if ((Integer.parseInt(player2TroopBank[tracker][6]) +moveData) >= 50 | moveData > 15)
          {
            System.out.println("Sorry, but that move is out of bounds, try moving less or in another direction");
          }
          else if (!mapcopy[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6]) +moveData ].equals(" ") & !mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) ][Integer.parseInt(player2TroopBank[tracker][6])+moveData ].equalsIgnoreCase("D"))
          {
            
            System.out.println("Sorry,there is already something there");
            
          } 
          else
          {
            
            mapcopy[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6])] = map[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6])];
            player2TroopBank[tracker][6] = Integer.toString(Integer.parseInt(player2TroopBank[tracker][6]) +moveData); // by adding to the x coordinate, we move right
            if (player2TroopBank[tracker][1].equalsIgnoreCase("tank")) // checks to see if tank
            {
              counter = 1; // sets the counter so that a tank is spawned 
            }
            else if (player2TroopBank[tracker][1].equalsIgnoreCase("jet")) // checks to see if jet
            {
              counter = 2; // sets the counter so that a jet is spawned 
            }
            else if (player2TroopBank[tracker][1].equalsIgnoreCase("infantry")) // checks to see if infantry
            {
              counter = 3; // sets the counter so that infantry is spawned 
            }
            spawnTroop(Integer.parseInt(player2TroopBank[tracker][7]), Integer.parseInt(player2TroopBank[tracker][6]), counter);// throws specific data to the movetroop method. this data includes, y pos, x pos, and type. 
            flag2 = false; // ends loop
            checkCapOil(Integer.parseInt(player2TroopBank[tracker][7]), Integer.parseInt(player2TroopBank[tracker][6]));
          }
        } // end of right
        
        else if (s.equalsIgnoreCase("left"))
        {
          System.out.println("By how much? (troops cannot move move than 10 spaces left or right)");
          
          
          s = methods.getInput();
          moveData = Integer.parseInt(s);
          if ((Integer.parseInt(player2TroopBank[tracker][6]) -moveData) <= 0 | moveData > 15)
          {
            System.out.println("Sorry, but that move is out of bounds, try moving less or in another direction");
          }
          else if (!mapcopy[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6]) -moveData ].equals(" ") & !mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) ][Integer.parseInt(player2TroopBank[tracker][6])-moveData ].equalsIgnoreCase("D"))
          {
            
            System.out.println("Sorry,there is already something there");
            
          }  
          else
          {
            // the line below this is to revert the position on the map back to its original way before a troop was on it
            mapcopy[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6])] = map[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6])];
            player2TroopBank[tracker][6] = Integer.toString(Integer.parseInt(player2TroopBank[tracker][6]) -moveData); // by subtracting from the x coordinate, we move left
            if (player2TroopBank[tracker][1].equalsIgnoreCase("tank")) // checks to see if tank
            {
              counter = 1; // sets the counter so that a tank is spawned 
            }
            else if (player2TroopBank[tracker][1].equalsIgnoreCase("jet")) // checks to see if jet
            {
              counter = 2; // sets the counter so that a jet is spawned 
            }
            else if (player2TroopBank[tracker][1].equalsIgnoreCase("infantry")) // checks to see if infantry
            {
              counter = 3; // sets the counter so that infantry is spawned 
            }
            spawnTroop(Integer.parseInt(player2TroopBank[tracker][7]), Integer.parseInt(player2TroopBank[tracker][6]), counter);// throws specific data to the movetroop method. this data includes, y pos, x pos, and type. 
            flag2 = false; // ends loop
            checkCapOil(Integer.parseInt(player2TroopBank[tracker][7]), Integer.parseInt(player2TroopBank[tracker][6]));
          }
          
        } // end of left
        
        else
        {
          System.out.println("Sorry, but directions are up, down, left, and right, and troops can only move 4 spaces. Please input first the direction, then by the number of spaces");
        } // end of else
      }// end of while for inpput
      
    } // end of if p2
  } // end method
  
  
  /////////////////////////////////////////////////////// Attacking
  // attacking works like this: You have your troop position, and it looks in four directions around the troop for an enemy troop.
  // Then, damage is calculated, and applied.
  
  public void attackTroop() // this method is for attacking
  { 
    
    if ( player == 0) // if player 1
    {
      count = 0; // to allow things to be picked up
      attackloop = true; // for flow
      submenu = true; // for yes/no
      while(attackloop)
      { // the line below this checks for an enemy troop above
        if ((mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) - 1][Integer.parseInt(player1TroopBank[tracker][6])].equals("i") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) - 1][Integer.parseInt(player1TroopBank[tracker][6])].equals("j") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) - 1][Integer.parseInt(player1TroopBank[tracker][6])].equals("t") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) - 1][Integer.parseInt(player1TroopBank[tracker][6])].equals("b")) & count < 1)
        {
          // I have it so that it looks for a specific letter, so that you can't attack friendly units
          System.out.println("Would you like to attack the troop above " +player1TroopBank[tracker][0] +"?");
          submenu = methods.yesNo();
          if (submenu != true) // if yes
          {
            attackloop = false; // ends loop
            
            spot = searchBank(Integer.parseInt(player1TroopBank[tracker][7]) - 1,Integer.parseInt(player1TroopBank[tracker][6])); // throwing coordinates to the search bank method and finding the correct spot
            dmgCalc(tracker, spot); // throws the two positions for the sttacking troops and calculates damage
            applyDamage(spot); // apply damage will apply the damage to the troop and remove it if nesscisary
            
          } // if conditions are met and yes
          else
          {
            count++; 
          } // if not there
          
        } // end of if for up
        // the else if below is for down
        else if ((mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) + 1][Integer.parseInt(player1TroopBank[tracker][6])].equals("i") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) + 1][Integer.parseInt(player1TroopBank[tracker][6])].equals("j") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) + 1][Integer.parseInt(player1TroopBank[tracker][6])].equals("t") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) + 1][Integer.parseInt(player1TroopBank[tracker][6])].equals("b")) & count < 2)
        {
          System.out.println("Would you like to attack the troop below " +player1TroopBank[tracker][0] +"?");
          
          submenu = methods.yesNo();
          if (submenu != true) // if yes
          {
            attackloop = false; // ends loop
            
            spot = searchBank(Integer.parseInt(player1TroopBank[tracker][7]) + 1,Integer.parseInt(player1TroopBank[tracker][6])); // throwing coordinates to the search bank method and finding the correct spot
            dmgCalc(tracker, spot); // throws the two positions for the sttacking troops and calculates damage
            applyDamage(spot);
          } // if conditions are met and yes
          else
          {
            count++; 
          } // if not there
          
        }
        // the else if below is for attacking to the right 
        else if((mapcopy[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6]) +1].equals("i") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6])+ 1].equals("j") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6])+ 1].equals("t") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6])+ 1].equals("b")) & count < 3)
        {
          System.out.println("Would you like to attack the troop to the right of " +player1TroopBank[tracker][0] +"?");
          
          submenu = methods.yesNo();
          if (submenu != true) // if yes
          {
            attackloop = false; // ends loop
            
            spot = searchBank(Integer.parseInt(player1TroopBank[tracker][7]) ,Integer.parseInt(player1TroopBank[tracker][6])+ 1); // throwing coordinates to the search bank method and finding the correct spot
            dmgCalc(tracker, spot); // throws the two positions for the sttacking troops and calculates damage
            applyDamage(spot);
          } // if conditions are met and yes
          else
          {
            count++; 
          } // if not there
          
        }
        // this else if is for attacking to the left
        else if ((mapcopy[Integer.parseInt(player1TroopBank[tracker][7])][Integer.parseInt(player1TroopBank[tracker][6]) -1].equals("i") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6])- 1].equals("j") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6])- 1].equals("t") | mapcopy[Integer.parseInt(player1TroopBank[tracker][7]) ][Integer.parseInt(player1TroopBank[tracker][6])- 1].equals("b")) & count < 3)
        {
          System.out.println("Would you like to attack the troop to the left of " +player1TroopBank[tracker][0] +"?");
          
          submenu = methods.yesNo();
          if (submenu != true) // if yes
          {
            attackloop = false; // ends loop
            
            spot = searchBank(Integer.parseInt(player1TroopBank[tracker][7]) ,Integer.parseInt(player1TroopBank[tracker][6])- 1); // throwing coordinates to the search bank method and finding the correct spot
            dmgCalc(tracker, spot); // throws the two positions for the sttacking troops and calculates damage
            applyDamage(spot);
          } // if conditions are met and yes
          else
          {
            count++; 
          } // if not there
          
        } // end of attacking to the left 
        
        else
        {
          System.out.println("No attack"); // tells user there was no attack done at all
          attackloop = false; // ends loop
        } // end of else
      } //end of loop
      
    } // end of p1 
    else // this else is for player 2
    {
      count = 0; // to allow things to be picked up
      attackloop = true; // for flow
      submenu = true; // for yes/no
      while(attackloop)
      { // the line below this checks for an enemy troop above
        if ((mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) - 1][Integer.parseInt(player2TroopBank[tracker][6])].equals("I") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) - 1][Integer.parseInt(player2TroopBank[tracker][6])].equals("J") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) - 1][Integer.parseInt(player2TroopBank[tracker][6])].equals("T") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) - 1][Integer.parseInt(player2TroopBank[tracker][6])].equals("B")) & count < 1)
        {
          System.out.println("Would you like to attack the troop above " +player2TroopBank[tracker][0] +"?");
          submenu = methods.yesNo();
          if (submenu != true) // if yes
          {
            attackloop = false; // ends loop
            
            spot = searchBank(Integer.parseInt(player2TroopBank[tracker][7]) - 1,Integer.parseInt(player2TroopBank[tracker][6])); // throwing coordinates to the search bank method and finding the correct spot
            dmgCalc(tracker, spot); // throws the two positions for the sttacking troops and calculates damage
            applyDamage(spot);
          } // if conditions are met and yes
          else
          {
            count++; 
          } // if not there
          
        } // end of if for up
        // the else if below is for down
        else if ((mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) + 1][Integer.parseInt(player2TroopBank[tracker][6])].equals("I") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) + 1][Integer.parseInt(player2TroopBank[tracker][6])].equals("J") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) + 1][Integer.parseInt(player2TroopBank[tracker][6])].equals("T") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) + 1][Integer.parseInt(player2TroopBank[tracker][6])].equals("B")) & count < 2)
        {
          System.out.println("Would you like to attack the troop below " +player2TroopBank[tracker][0] +"?");
          
          submenu = methods.yesNo();
          if (submenu != true) // if yes
          {
            attackloop = false; // ends loop
            
            spot = searchBank(Integer.parseInt(player2TroopBank[tracker][7]) + 1,Integer.parseInt(player2TroopBank[tracker][6])); // throwing coordinates to the search bank method and finding the correct spot
            dmgCalc(tracker, spot); // throws the two positions for the sttacking troops and calculates damage
            applyDamage(spot);
          } // if conditions are met and yes
          else
          {
            count++; 
          } // if not there
          
        }
        // the else if below is for attacking to the right 
        else if((mapcopy[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6]) +1].equals("I") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) ][Integer.parseInt(player2TroopBank[tracker][6])+ 1].equals("J") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) ][Integer.parseInt(player2TroopBank[tracker][6])+ 1].equals("T") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) ][Integer.parseInt(player2TroopBank[tracker][6])+ 1].equals("B")) & count < 3)
        {
          System.out.println("Would you like to attack the troop to the right of " +player2TroopBank[tracker][0] +"?");
          
          submenu = methods.yesNo();
          if (submenu != true) // if yes
          {
            attackloop = false; // ends loop
            
            spot = searchBank(Integer.parseInt(player2TroopBank[tracker][7]) ,Integer.parseInt(player2TroopBank[tracker][6])+ 1); // throwing coordinates to the search bank method and finding the correct spot
            dmgCalc(tracker, spot); // throws the two positions for the sttacking troops and calculates damage
            applyDamage(spot);
          } // if conditions are met and yes
          else
          {
            count++; 
          } // if not there
          
        }
        // this else if is for attacking to the left
        else if ((mapcopy[Integer.parseInt(player2TroopBank[tracker][7])][Integer.parseInt(player2TroopBank[tracker][6]) -1].equals("I") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) ][Integer.parseInt(player2TroopBank[tracker][6])- 1].equals("J") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) ][Integer.parseInt(player2TroopBank[tracker][6])- 1].equals("T") | mapcopy[Integer.parseInt(player2TroopBank[tracker][7]) ][Integer.parseInt(player2TroopBank[tracker][6])- 1].equals("B")) & count < 3)
        {
          System.out.println("Would you like to attack the troop to the left of " +player2TroopBank[tracker][0] +"?");
          
          submenu = methods.yesNo();
          if (submenu != true) // if yes
          {
            attackloop = false; // ends loop
            
            spot = searchBank(Integer.parseInt(player2TroopBank[tracker][7]) ,Integer.parseInt(player2TroopBank[tracker][6])- 1); // throwing coordinates to the search bank method and finding the correct spot
            dmgCalc(tracker, spot); // throws the two positions for the sttacking troops and calculates damage
            applyDamage(spot);
          } // if conditions are met and yes
          else
          {
            count++; 
          } // if not there
          
        } // end of attacking to the left 
        
        else
        {
          System.out.println("No attack"); // tells user there was no attack done at all
          attackloop = false; // ends loop
        } // end of else
      } //end of loop
      
      
      
      
    } // end of else for player 2 
    
    
  } // end method
   // ATTTTTACKKKIIIIIIIIIINNNNNNNNNGGGGGGG IS  DONW HERe
  
  public int dmgCalc(int t, int v) // calculates damage // accepts postions for attacker and defender
  {
    if (player == 0)
    {
      // random number between (the diffrence of atk of troop & def of defending) +10
      damage =  (int)(Math.random() * (Integer.parseInt(player1TroopBank[t][3]) - Integer.parseInt(player2TroopBank[spot][4])) +10);
      // I want it so that when you attack certain enemy types with a type of troop, you do double damage, so i created this set of if and else ifs
      if (player1TroopBank[t][1].equalsIgnoreCase("infantry") & player2TroopBank[spot][1].equalsIgnoreCase("jet"))
      {
       System.out.println("Surface to air missles used!!!! Double damage!!!!");
       damage = damage * 2; // doubles the damage
      } // end of if for inf & jet
      else if (player1TroopBank[t][1].equalsIgnoreCase("jet") & player2TroopBank[spot][1].equalsIgnoreCase("tank"))
      {
       System.out.println("Bombs dropped!!! Double Damage!!!");
       damage = damage * 2;
      }
      else if (player1TroopBank[t][1].equalsIgnoreCase("tank") & player2TroopBank[spot][1].equalsIgnoreCase("infantry"))
      {
       System.out.println("Tank Assult!!! Double Damage!!!");
       damage = damage * 2;
      }
    } // end of if for p1
    else
    {
      damage =  (int)(Math.random() * (Integer.parseInt(player2TroopBank[t][3]) - Integer.parseInt(player1TroopBank[spot][4])) +10);
      if (player2TroopBank[t][1].equalsIgnoreCase("infantry") & player1TroopBank[spot][1].equalsIgnoreCase("jet"))
      {
       System.out.println("Surface to air missles used!!!! Double damage!!!!");
       damage = damage * 2;
      } // end of if for inf & jet
      else if (player2TroopBank[t][1].equalsIgnoreCase("jet") & player1TroopBank[spot][1].equalsIgnoreCase("tank"))
      {
       System.out.println("Bombs dropped!!! Double Damage!!!");
       damage = damage * 2;
      }
      else if (player2TroopBank[t][1].equalsIgnoreCase("tank") & player1TroopBank[spot][1].equalsIgnoreCase("infantry"))
      {
       System.out.println("Tank Assult!!! Double Damage!!!");
       damage = damage * 2;
      }
    } //end of else for p2
    return damage;
  } // end of method for damage calculation
  
  
  
  public int searchBank(int y, int x) // looks for the horizontal array where the coordinates are stored
  {
    if (player == 0)
    {
      for (int q = 0; q < 12; q++) // this for loop searches through the enemys troop bank for the troop with the coordinates
      {
        //      looks at y here  | turning values into string here | x here 
        if (player2TroopBank[q][7].equals(Integer.toString(y)) && player2TroopBank[q][6].equals(Integer.toString(x))) // if coordinates match
        {
          spot = q; // found the index in the opponents bank that matches this 
        } // if the coordinates match
      } // end of for loop 
      
      
      
    } // end of player 1
    else
    {
      
      for (int q = 0; q < 12; q++) // this for loop searches through the enemys troop bank for the troop with the coordinates
      {
        //      looks at y here  | turning values into string here | x here 
        if (player1TroopBank[q][7].equals(Integer.toString(y)) && player1TroopBank[q][6].equals(Integer.toString(x))) // if coordinates match
        {
          spot = q; // found the index in the opponents bank that matches this 
        } // if the coordinates match
      } // end of for loop 
      
      
      
    } // end of else for p2
    
    return spot; // returns the position 
  } // end of method
  
  
  public void applyDamage(int x) // appiles the damage to the defending troop and if hp <= 0, it is destroyed. accepts the position of the troop being hit
  {
    if (player == 0) // if p1
    {
      System.out.println(player1TroopBank[tracker][0] +" has dealt " +damage +" damage!"); // shows Dmg dealt
      player2TroopBank[x][2] = Integer.toString(Integer.parseInt(player2TroopBank[x][2]) -damage); // does damage
      System.out.println(player2TroopBank[x][0] +" has " +player2TroopBank[x][2] +" health left."); // tells user rming health
      if (Integer.parseInt(player2TroopBank[x][2]) <= 0) // if the defending troops health is < or = 0, the unit is destroyed so....
      {
        if (player2TroopBank[x][1].equalsIgnoreCase("Base"))
        {
         basecount2--; // subtracts a base count from p2. This is for win credentials 
        }
        System.out.println(player1TroopBank[tracker][0] +" has defeated " +player2TroopBank[x][0] +"!");
        // the line under this makes the position what it originally was before the troop was there
        mapcopy[Integer.parseInt(player2TroopBank[x][7])][Integer.parseInt(player2TroopBank[x][6])] =  map[Integer.parseInt(player2TroopBank[x][7])][Integer.parseInt(player2TroopBank[x][6])];
        for (int k = 0; k < 8; k++) // this for loop will cycle through each element in player2TroopBank[x] and turn it back into nothing, so that the troop no longer exists
        {
          player2TroopBank[x][k] = "nothing"; // fills with nothing
        }
      } // end of if for if troop dead
    } // end of if for p1
    else // the else for p2
    {
      System.out.println(player2TroopBank[tracker][0] +" has dealt " +damage +" damage!");
      player1TroopBank[x][2] = Integer.toString(Integer.parseInt(player1TroopBank[x][2]) -damage);
      System.out.println(player1TroopBank[x][0] +" has " +player1TroopBank[x][2] +" health left.");
      if (Integer.parseInt(player1TroopBank[x][2]) <= 0) // if the defending troops health is < or = 0, the unit is destroyed so....
      {
        if (player1TroopBank[x][1].equalsIgnoreCase("Base"))
        {
         basecount1--; // subtracts a base count from p2. This is for win credentials 
        }
        System.out.println(player2TroopBank[tracker][0] +" has defeated " +player1TroopBank[x][0] +"!");
        // the line under this makes the position what it originally was before the troop was there
        mapcopy[Integer.parseInt(player1TroopBank[x][7])][Integer.parseInt(player1TroopBank[x][6])] =  map[Integer.parseInt(player1TroopBank[x][7])][Integer.parseInt(player1TroopBank[x][6])];
        for (int k = 0; k < 8; k++) // this for loop will cycle through each element in player2TroopBank[x] and turn it back into nothing, so that the troop no longer exists
        {
          player1TroopBank[x][k] = "nothing"; // fills with nothing
        }
      } // end of if for if troop dead 
      
    } // end of else for p2
  } // end of method
  
  
  
  
  //////////////////
  
  //+!@#!@#$!@%$!@#^!%$^!$%#$%!@#$% MAPPPPPPPPPPPPPPPP DATAAAAAAAAAAAAA HEEEEEEEEEERRRRRRRRRRREEEEEEEEEEEE \\
  
  
  //////////////////  
  
  
  // Map data we had to merge into this class. Earlier we had mapdata, a class that held data for the map. After having errors with it, we merged it to make one "Super Class"
  
  
  public void getFaction() // this method gets the faction for each player 
  {
    for (int i = 0; i < 2; i++) // i created this for loop to cycle for player one and player 2
    {
      System.out.println("What faction does player " +(i +1)  +" want to command? Type the corresponding number.");
      System.out.println("[1] China, [2] USA, [3] Russia");
      s = methods.getInput();
      
      if (s.equalsIgnoreCase("1"))
      {
        System.out.println("You have chosen China.");
        System.out.println(" ______________________"); 
        System.out.println("|   ..                 |"); 
        System.out.println("|  * .                 |"); 
        System.out.println("|   ..                 |"); 
        System.out.println("|                      |");  
        System.out.println("|                      |");
        System.out.println("|                      |");
        System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Lead your army well General.");
        
        faction_player[i] = faction[0]; //Set player 1 faction to China
      }
      else if (s.equalsIgnoreCase("2"))
      {
        System.out.println("You have chosen the United States of America.");
        System.out.println(" ______________________");
        System.out.println("|XXXXXXX|==============|");
        System.out.println("|XXXXXXX|==============|");
        System.out.println("|XXXXXXX|==============|");  
        System.out.println("|======================|");
        System.out.println("|======================|");
        System.out.println("|======================|");
        System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Blow some stuff up!");
        faction_player[i] = faction[1]; //Set player 1 faction to America
      }
      else if (s.equalsIgnoreCase("3"))
      {
        System.out.println("You have chosen Russia.");
        System.out.println(" ______________________"); 
        System.out.println("|                      |"); 
        System.out.println("|                      |"); 
        System.out.println("|!!!!!!!!!!!!!!!!!!!!!!|"); 
        System.out.println("|!!!!!!!!!!!!!!!!!!!!!!|");  
        System.out.println("|######################|");
        System.out.println("|######################|");
        System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Fight with pride!");
        faction_player[i] = faction[2]; //Set player 1 faction to Russia
      }
      else
      {
        methods.failSafe(); //throw error message
        System.out.println("Becuase you typed in something random, your faction is now Canada!");
        faction_player[i] = "Canada";
      }
    } //  end of for loop
    System.out.println(); // creates a return
    if (faction_player[0].equals(faction_player[1])) // a little easter egg in case the users pick the same faction, Civil war actually dowsnt mean anything.
      System.out.println("Civil War Enabled!");
  } // end of method
  
  public void wasteland()
  {
//horizontal walls
    
    for (int x = 0; x < 50; x++)
    {
      map[0][x] = "#";
      map[9][x] = "#";
    }
    
    //vertical walls
    for (int x = 1; x < 9; x++)
    {
      map[x][0] = "#";
      map[x][49] = "#";
    }
    
    //empty space
    for (int y = 1; y < 9; y++)
    {    
      for (int x = 1; x < 49; x++)
      {      
        map[y][x] = " ";     
      }
    }
    
    //oil derricks and bases
    {      
      //master bases
      map[6][1] = "B";
      map[3][1] = "B";
      map[3][48] = "b";
      map[6][48] = "b";
      
      
      //oil derricks
      map[1][13] = "D";
      map[1][36] = "D";      
      map[8][13] = "D";
      map[8][36] = "D"; 
    }
    mapCopy(); //make copy of current map
    
  } // end of mehod
  
  public void mountainRange() // creates the mountarin range map
  {
    //horizontal walls
    for (int x = 0; x < 50; x++)
    {
      map[0][x] = "#";
      map[9][x] = "#";
    }
    
    //vertical walls
    for (int x = 1; x < 9; x++)
    {
      map[x][0] = "#";
      map[x][49] = "#";
    }
    
    //randomized grass, mountain, water
    for (int y = 1; y < 9; y++)
    {    
      for (int x = 1; x < 49; x++)
      {      
        rndm = (int)(Math.random() * 3); //generate and store random number between 0 and 2    
        
        //print conversions 0 = "."  1 = "~" 2 = "^"      
        map[y][x] = landscape[rndm];     
      }
    } // end of loops
    
    //oil derricks and bases
    {      
      //master bases
      map[6][1] = "B";
      map[3][1] = "B";
      map[3][48] = "b";
      map[6][48] = "b";
      
      
      //oil derricks
      map[1][13] = "D";
      map[1][36] = "D";      
      map[8][13] = "D";
      map[8][36] = "D"; 
    }  
    mapCopy();
  } // end of method
  public void printMap()
  {
    for (int y = 0; y < 10; y++) // 10 horizontal row line spacing
    {
      System.out.println();
      for (int x = 0; x < 50; x++) //50 vertical column spacing
      {
        System.out.print(mapcopy[y][x]);        
      }
    } // end of for loop
    System.out.println(); // creates a return
  } // end of method
  
  
  public void mapCopy() // this method will fill the map copy array with a copy of the map
  {
    for (int y = 0; y < 10; y++)
    {
      for (int x = 0; x < 50; x++)
      {
        mapcopy[y][x] = map[y][x]; // creates a copy at each position        
      } // end of for
    } // end of for loop
  } // end method  
  
  
  public void spawnTroop(int y, int x, int z) // prints a troop on the map
  {
    // I 
    if(player == 0) // if player 1
    {
      
      if (z == 3)
      {
        mapcopy[y][x] = "I";
      } // end of if for displaying the infantry 
      else if(z == 2)
      {
        mapcopy[y][x] = "J"; 
      }
      else if(z == 1)
      {
        mapcopy[y][x] = "T"; 
      }
    }
    else // if not player1
    {
      if (z == 3)
      {
        mapcopy[y][x] = "i";
      } // end of if for displaying the infantry 
      else if(z == 2)
      {
        mapcopy[y][x] = "j"; 
      }
      else if(z == 1)
      {
        mapcopy[y][x] = "t"; 
      }      
    }
    printMap();
  } // end of method 
  
  public void checkCapOil(int y, int x) // checks to see if the place landed on is an oil derrick, recieves coordinates
  {
    if (player == 0) // if player1 
    {
      if(  mapcopy[y][x].equals("c") || map[y][x].equals("D")) // This checks if the space thrown is an oil derrick
      {
        System.out.println("You have Captured this oil derrick!");
        mapcopy[y][x] = "C";
        map[y][x] = "C";
        oilCount(); //count all captured oil derricks
      } // end of finding letters
      
      
    } // end of if player1 
    else
    {
      if(  mapcopy[y][x].equals("c") || map[y][x].equals("D")) // This checks if the space thrown is an oil derrick
      {
        System.out.println("You have Captured this oil derrick!");
        mapcopy[y][x] = "c";
        map[y][x] = "c";
        oilCount();
      } // end of finding letters
      
    }
  } // end of method
  
  public void oilCount() // counts the number of oil derricks for each player
  {
    player1Derricks = 0; //variable to hold p1 derrick count, set to zero because it I dont want the player having a stacking derrick amount
    player2Derricks = 0; //variable to hold p2 derrick count
    if (player == 0)
    {
      for (int g = 0; g < 10; g++)
      {
        for (int h = 0; h < 50; h++)
        {
          if (map[g][h].equals("C")) // this finds a place on the map that belongs to the specific player, if so, adds a p1 derrick to the count so more are made.
          {
            player1Derricks++; 
          } // end of if
        } // end of for
      } // end of for loop
      
      
    } // end if for player1
    else
    {
      for (int g = 0; g < 10; g++)
      {
        for (int h = 0; h < 50; h++)
        {
          if (mapcopy[g][h].equals("c"))
          {
            player2Derricks++; 
          } // end of if
        } // end of for
      } // end of for loop
    }
  } // end method
  
  
  // WIN CHECK
  public boolean winCheck() // this will check weather or not a player has won
  {
    
    if (basecount2 == 0) // player 1 wins
    {
      // ends the game
      game = false;
      // shows a victory message
      System.out.println(" _    ___      __                   __");
      System.out.println("| |  / (_)____/ /_____  _______  __/ /");
      System.out.println("| | / / / ___/ __/ __ \\/ ___/ / / / / ");
      System.out.println("| |/ / / /__/ /_/ /_/ / /  / /_/ /_/  ");
      System.out.println("|___/_/\\___/\\__/\\____/_/   \\__, (_)   ");
      System.out.println("                          /____/      ");
      System.out.println();
      System.out.println("Player 1 Wins!!!!" );
      System.out.println(faction_player[0] +" has defeated " +faction_player[1] +"!!!");
    }
    else if (basecount1 == 0)
    {
      // ends game
           game = false;
           // this is all a victory message, showing who won etc 
      System.out.println(" _    ___      __                   __");
      System.out.println("| |  / (_)____/ /_____  _______  __/ /");
      System.out.println("| | / / / ___/ __/ __ \\/ ___/ / / / / ");
      System.out.println("| |/ / / /__/ /_/ /_/ / /  / /_/ /_/  ");
      System.out.println("|___/_/\\___/\\__/\\____/_/   \\__, (_)   ");
      System.out.println("                          /____/      ");
      System.out.println();
      System.out.println("Player 2 Wins!!!!" );
      System.out.println(faction_player[1] +" has defeated " +faction_player[0] +"!!!"); 
    }
    return game;  // throws back the game variable
  }
  
} //  end of class