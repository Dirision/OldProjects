/*
 * Author: Nick DiRisio
 * Date: May 20th 2014
 * Program: kidGameModes.java
 * Description: Modes for the kidGame application. 
 * 				this class contains the data to build the game window and the methods, actionlisteners, and draw classes with it  
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// sound.sampled needed for audio
import javax.sound.sampled.*;
import java.io.*;

public class kidGameModes 
{
	/// VARIABLES
	////////////////////////////////////////////////////////
	// game JFrame
	JFrame gameWin = new JFrame("Building Blocks");
	// PANEL FOR QUIZ
	JPanel quizPanel = new JPanel();
	// PANEL FOR BUILD 
	JPanel buildPanel = new JPanel();
	//////// OBJECTS
	refresher re = new refresher();
	// calling start will draw a random shape
	drawRanShape start = new  drawRanShape();
	// calling builder will gen a shape based on a selection
	drawShape builder = new drawShape();
	
	/////// INTEGERS 
	// holds number of shapes they got right
	public int right = 0;
	// holds number of shapes guessed 
	public int attempts = 0; 
	// holds the value of the random color
	public int colorNum = 0;
	// holds the value of the random shape 
	public int shapeNum = 0;
	
	// this int array holds the values for the building shapes
	public int[] buildValues = new int[3];
	// position 0: shape, pos1: color, pos2: size
	
	// STRING ARRAY OF BASIC SHAPE NAMES
	public String[] basicShapes = {"Square!","Rectangle!","Circle!","Triangle!", "Pentagon!"};
	// COLORS
	public String[] colors = {"Blue!","Green!","Yellow!","Red!","Purple!", "Orange!"};
	// SIZES
	public String[] sizes = {"Small!", "Medium!", "Large!"};
	///////// int ARRAY FOR POINTS OF OBJECTS
	// 4 triangle
	public int[] trianglex = {0, 100, 200};
	public int[] triangley = {200, 0, 200};
	public int[] buildtrix = new int[3];
	public int[] buildtriy = new int[3];
	// 4 pentagon
	public int[] pentagonx = {0, 35, 175, 215, 100};
	public int[] pentagony = {50, 150, 150, 50, 0};
	public int[] buildpentx = new int[5];
	public int[] buildpenty = new int[5];
	//////Labels
	JLabel scoreLabel = new JLabel("Score: "+ right +" Right from +" +attempts +" Guesses!");
	
	// build combo box's for quiz mode and build mode
	final JComboBox quizShapes = new JComboBox(basicShapes);
	final JComboBox quizColors = new JComboBox(colors);
	final JComboBox buildShapes = new JComboBox(basicShapes);
	final JComboBox buildColors = new JComboBox(colors);
	final JComboBox comboBoxSizes = new JComboBox(sizes);
	
	
	// color 
	Color c = new Color(0,0,0);
	// default color
	Color cc = new Color(102,255,102);
	
	////////////////////////////////////////////////////////
	
	
	// BUILDING METHODS
	///////////////////////////////////////////////////////
	
	// constructor
	kidGameModes(int x)
	{
		//System.out.println("HI");
		// game JFrame initialization
		gameWin.setLayout(null);
		gameWin.getContentPane().setBackground(Color.cyan);
		gameWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWin.setResizable(false);
		gameWin.setSize(new Dimension(800, 460));
		gameWin.setVisible(true);
		// build each panel 
		buildQuiz();
		buildBuild();
		// booting up music
		// playMusic();
		// if initialized to quiz 
		if(x == 1)
		{
			quizON();
			
		}
		else
		{
			buildON();
		}
	}
	// when quiz is initialized
	public void quizON()
	{	
		System.out.println("going to quiz");
		buildPanel.setVisible(false);
		gameWin.remove(buildPanel);
		gameWin.add(quizPanel);
		quizPanel.setVisible(true);
	}
	// When BUILD IS INITIALIZED
	public void buildON()
	{
		System.out.println("going to build");
		quizPanel.setVisible(false);
		gameWin.remove(quizPanel);
		gameWin.add(buildPanel);
		buildPanel.setVisible(true);
	}

	// ADDING THE ELEMENTS TO QUIZPANEL
	public void buildQuiz() 
	{
		System.out.println("Build Quiz");
		// set basic panel traits
		quizPanel.setSize(800,460);
		quizPanel.setBackground(new Color(102,255,102));
		quizPanel.setLayout(null);
	
		
		//Buttons 
		// this button is for when the user wants to guess
		JButton check = new JButton("Check To See If You're Right!");
		// this JButton switches the game mode to build 
		JButton gotoBuild = new JButton("Switch to Build Mode!");
		
		
		// Labels
		JLabel shapeLabel = new JLabel("Shapes!");
		JLabel colorLabel = new JLabel("Colors!");
		JLabel titleLabel = new JLabel("Quiz Mode! \n Guess the Shape!");
		
		
		// add action listeners
		gotoBuild.addActionListener(new switchToB());
		check.addActionListener(new checkInput());
		
		
		// add them to quiz panel
		quizPanel.add(quizShapes);
		quizPanel.add(quizColors);
		quizPanel.add(check);
		quizPanel.add(gotoBuild);
		quizPanel.add(shapeLabel);
		quizPanel.add(colorLabel);
		quizPanel.add(titleLabel);
		quizPanel.add(scoreLabel);
		//
		
		//set bounds for checkbox's
		quizShapes.setBounds(20, 51, 200, 51);
		quizColors.setBounds(20, 153, 200, 51);
		check.setBounds(20, 255, 200, 51);
		gotoBuild.setBounds(20, 357, 200, 51);
		//bounds for labels
		shapeLabel.setBounds(90, 25, 160, 20);
		colorLabel.setBounds(90, 126,160, 20);
		titleLabel.setBounds(396, 25, 266, 20);
		scoreLabel.setBounds(393, 45, 266, 20);
		// Creating starting shape
		c = getColor();
		getShape();
		quizPanel.add(start);
		start.setBounds(350, 146, 800, 314);
		quizPanel.updateUI();
		quizPanel.repaint();
		
		
	}
	
	// ADDING THE ELEMENTS and BUILDING THE BUILDPANEL
	public void buildBuild()
	{
		// panel characteristics
		System.out.println("Constructing Build Mode");
		buildPanel.setSize(new Dimension(800, 460));
		//buildPanel.setBackground(Color.cyan);
		buildPanel.setLayout(null);
		
		// Labels!
		JLabel shapeLabel = new JLabel("Shapes!");
		JLabel colorLabel = new JLabel("Colors!");
		JLabel sizeLabel = new JLabel("Sizes!");
		
		// buttons!
		JButton quizSwitch = new JButton("Switch to Quiz Mode!");
		
		
		// add action listeners
		quizSwitch.addActionListener(new switchToQ());
		buildColors.addActionListener(new buildShape());
		buildShapes.addActionListener(new buildShape());
		comboBoxSizes.addActionListener(new buildShape());
		
		// Adding things
		buildPanel.add(buildColors);
		buildPanel.add(buildShapes);
		buildPanel.add(quizSwitch);
		buildPanel.add(comboBoxSizes);
		buildPanel.add(shapeLabel);
		buildPanel.add(colorLabel);
		buildPanel.add(sizeLabel);
		
		/// set bounds
		buildShapes.setBounds(20, 51, 200, 51);
		buildColors.setBounds(20, 153, 200, 51);
		comboBoxSizes.setBounds(20, 255, 200, 51);
		quizSwitch.setBounds(20, 357, 200, 51);
		shapeLabel.setBounds(90, 25, 160, 20);
		colorLabel.setBounds(90, 126,160, 20);
		sizeLabel.setBounds(90, 227,160, 20);
	}
	
	///////////////////////////////////////////////////////////////
	// ACTION LISTENERS
	
	// this action listener, when activated, will switch the game mode to build mode.
	public class switchToB implements ActionListener
	{
		public void actionPerformed(ActionEvent ee)
		{
			System.out.println("Switch Clicked");
			// turns on build mode
			buildON();
			
		}
	}
	public class switchToQ implements ActionListener
	{
		public void actionPerformed(ActionEvent ee)
		{
			System.out.println("Switch Clicked");
			// turns on build mode
			quizON();
			
		}
	}
	// checks the values the user input, and then adds another shape
	public class checkInput implements ActionListener
	{
		public void actionPerformed(ActionEvent checker)
		{
			
			checkValues();
			
			// refresh and redraw new shape
			c = getColor();
			getShape();
			quizPanel.add(start);
			start.setBounds(350, 146, 800, 314);
			quizPanel.updateUI();
			
		}
		
	}
	// this is triggered whenever the user selects something 
	public class buildShape implements ActionListener
	{
		public void actionPerformed(ActionEvent eee)
		{
			System.out.println("Combo box activated");
			//buildPanel.add(re);
			//re.setBounds(350, 100, 800, 400);
			buildPanel.updateUI();
			getTraits();
			buildPanel.add(builder);
			// rectangle & circle are special cases
		//	if(buildValues[0] != 2)
				builder.setBounds(250, 10, 800, 460); 
						//100*buildValues[2], 100*buildValues[2]);
		//	else
			//	builder.setBounds(265, 25,  800, 314); 
						//200*buildValues[2], 100*buildValues[2]);
			buildPanel.updateUI();
			buildPanel.revalidate();
			builder.revalidate();
			
		}
	}
	
	///////////////////////////////////////////////////////////////////
	
	///////////////////////// METHODS 
	// This method will check if the values in the combo boxes are equal to the currently drawn shape
	public void checkValues()
	{
		
	 System.out.println("comparing");
	 int shapeVal = quizShapes.getSelectedIndex()+1;
	 int colorVal = quizColors.getSelectedIndex()+1;
	 
	 if(shapeVal == shapeNum && colorVal == colorNum)
	 {
		 JOptionPane.showMessageDialog(null, "You are Correct!");
		 right++;
		 attempts++;
	 }
	 else
	 {
		 // these are to tell the user whats wrong
		 if(shapeVal != shapeNum && colorVal != colorNum)
			 JOptionPane.showMessageDialog(null, "Wrong! Check shape and color!");
		 else if(shapeVal != shapeNum)
			 JOptionPane.showMessageDialog(null, "Wrong! Check the shape!");
		 else if(colorVal != colorNum)
			 JOptionPane.showMessageDialog(null, "Wrong! Check the color!");
		 attempts++;
	 }
	 scoreLabel.setText("Score: "+ right +" Right from " +attempts +" Guesses!");
	}
	
	// this method gets the traits from the combo box
	public void getTraits()
	{
		System.out.println("getting traits");
		buildValues[0] = buildShapes.getSelectedIndex()+1;
		buildValues[1] = buildColors.getSelectedIndex()+1;
		buildValues[2] = comboBoxSizes.getSelectedIndex()+1;
		// setting color here
		if(buildValues[1] == 1)//
		{	
		cc = Color.blue;	
		}
		else if(buildValues[1] == 2)
		{	
			cc = new Color(0,204,0);
		}
		else if(buildValues[1] == 3)
		{	
			cc = new Color(255,255,0);
		}
		else if(buildValues[1]== 4)
		{	
			cc = new Color(255, 0, 0);
		}
		else if(buildValues[1] == 5)
		{	
			cc = new Color(255, 0, 255);
		}
		else if(buildValues[1] == 6)
		{	
			cc = new Color(255, 153, 51);
		}
		// define sizes for polygons that are complex
		// for triangle
		//if(buildValues[0]== 4)
		//{
			//trianglex = {0, 100, 200};
			buildtrix[0] = 0;
			buildtrix[1]= 100*(buildValues[2]);
			buildtrix[2] = 100*(buildValues[2]);
	
			//triangley ={200, 0, 200};
			buildtriy[0] = 100+200*(buildValues[2]/3);
			buildtriy[1] = 0;
			buildtriy[2] =  100+200*(buildValues[2]/3);
	////	} 
		// I have to hard code all the elements 
		// for pentagon
		//else if(buildValues[0]== 5)
		//{
			// pentagonx = {0, 35, 175, 215, 100};
			buildpentx[0] = 0;
			buildpentx[1] = 35*(buildValues[2]);
			buildpentx[2] = 175*(buildValues[2]);
			buildpentx[3] = 215*(buildValues[2]);
			buildpentx[4] = 100*(buildValues[2]);
			// pentagony = {50, 150, 150, 50, 0};
			buildpenty[0] = 50+50*(buildValues[2]/2);
		  buildpenty[1] = 150+150*(buildValues[2]/2); 
			 buildpenty[2] = 150+150*(buildValues[2]/2);
			  buildpenty[3] = 50+50*(buildValues[2]/2);
			  buildpenty[4] = 0;
			 
			
			
		//}
		
	}
	
	// this method will generate a random color
	public Color getColor()
	{
		System.out.println("Color gen step");
		// build a default blank color
		Color c = Color.white;
		// random number to choose from the list of colors
		colorNum = (int)((Math.random()*6)+1);
		// colors = {"Blue","Green","Yellow","Red","Purple", "Orange"};
		// ifs for each color
		
		if(colorNum == 1)
		{	
		c = Color.blue;	
		}
		else if(colorNum == 2)
		{	
			c = new Color(0,204,0);
		}
		else if(colorNum == 3)
		{	
			c = new Color(255,255,0);
		}
		else if(colorNum == 4)
		{	
			c = new Color(255, 0, 0);
		}
		else if(colorNum == 5)
		{	
			c = new Color(255, 0, 255);
		}
		else if(colorNum == 6)
		{	
			c = new Color(255, 153, 51);
		}
		return c;
	}
	//gets the shape and its coordinates.
	public void getShape()
	{
		System.out.println("Get ran shape");
		// basicShapes = {"Square","Rectangle","Circle","Triangle", "Pentagon"};
		shapeNum = (int)(Math.random()*5 +1);
	}
	
	// this method will play the background music
	// for anyone wanting to try out this block of code
	// ask me for this URL:
	// https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
	public void playMusic()
	{
	
		try { 
			// get sound file
		// Song is a bit rendition of "A message to you Rudy" by The Specials
		//URL //url = this.class.getProtectionDomain().getCodeSource().getLocation();
		
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("music1.wav")); 
				//getClass().getResource("./music1.wav");
        AudioFormat format = inputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip clip = (Clip)AudioSystem.getLine(info);
        clip.open(inputStream);
        clip.start();
		} 
		// these catches are required
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} 
		catch (UnsupportedAudioFileException e)
		{	
		e.printStackTrace();
		} 
		catch (LineUnavailableException e)
		{
		e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////
	// drawing classes
	public class drawRanShape extends JPanel
	{
		
		public void paintComponent(Graphics g)
		{
			super.paintComponents(g);
			
			g.setColor(c);
			System.out.println("Building ran shape");
			// get random features
			
			System.out.println("Painting");

			// square
			if(shapeNum == 1)
			{
				g.fillRect(0, 0, 200,  200);
				
				g.setColor(Color.black);
				g.drawRect(0, 0, 200,  200);
			}
			// rectangle
			else if(shapeNum == 2)
			{
				g.fillRect(0,0, 400,200);
				
				g.setColor(Color.black);
				g.drawRect(0,0, 400,200);
			}
			// circle
			else if(shapeNum == 3)
			{
				g.fillOval(0, 0, 200, 200);
				g.setColor(Color.black);
				g.drawOval(0, 0, 200, 200);
			}
			//triangle
			else if(shapeNum == 4)
			{
				g.fillPolygon(trianglex, triangley, 3);
				g.setColor(Color.black);
				g.drawPolygon(trianglex, triangley, 3);
			}
			else if(shapeNum == 5)
			{
				
				g.fillPolygon(pentagonx, pentagony, 5);
				g.setColor(Color.black);
				g.drawPolygon(pentagonx, pentagony, 5);
			}
			// end of method
			
		}
	}
	
	// draws a shape with parameters
	public class drawShape extends JPanel
	{
		public void paintComponent(Graphics gg)
		{
			super.paintComponent(gg);
		//gg.
			gg.setColor(cc);
			System.out.println("Painting");

			// square
			if(buildValues[0] == 1)
			{
				gg.fillRect(0, 0, 100*buildValues[2],  100*buildValues[2]);
				
				gg.setColor(Color.black);
				gg.drawRect(0, 0, 100*buildValues[2],  100*buildValues[2]);
			}
			// rectangle
			else if(buildValues[0] == 2)
			{
				gg.fillRect(0,0, 200*buildValues[2],100*buildValues[2]);
				
				gg.setColor(Color.black);
				gg.drawRect(0,0, 200*buildValues[2],100*buildValues[2]);
			}
			// circle
			else if(buildValues[0] == 3)
			{
				gg.fillOval(0, 0, 100*buildValues[2], 100*buildValues[2]);
				gg.setColor(Color.black);
				gg.drawOval(0, 0, 100*buildValues[2], 100*buildValues[2]);
			}
			//triangle
			else if(buildValues[0] == 4)
			{
				gg.fillPolygon(buildtrix, buildtriy, 3);
				gg.setColor(Color.black);
				gg.drawPolygon(buildtrix, buildtriy, 3);
			}
			else if(buildValues[0] == 5)
			{
				
				gg.fillPolygon( buildpentx,  buildpenty, 5);
				gg.setColor(Color.black);
				gg.drawPolygon( buildpentx,  buildpenty, 5);
			}
			// end of method
			
		}
		
		
	}
	
	// refresher
	public class refresher extends JPanel
	{
		// refresher
				public void paintComponent(Graphics gg)
				{
					super.paintComponent(gg);
					System.out.println("Refreshing");
					// To refresh the frame, I'm going to draw over the frame with a big rectangle.
					gg.setColor(Color.cyan);
					gg.drawRect(0, 0, 600, 314);
					gg.fillRect(0, 0, 600, 314);
				}			
				
	}
	
	
	
	
	
	
}




