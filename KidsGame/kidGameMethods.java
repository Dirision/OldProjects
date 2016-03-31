/*
 * Author: Nick DiRisio
 * Date: May 20th 2014
 * Program: kidGameMethods.java
 * Description: Methods for the kidGame application
 */
//imports
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;

public class kidGameMethods
{
	/////////////////////////////////////////////////////// VariAbles
	JFrame mainMenu = new JFrame("Main Menu");
	
	public Image startPic;
	
	public String s ="";
	drawPic pic = new drawPic();
	////////////////////////////
	
	
	
	
	//////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////// INITIALIZATION METHODS
	// constructor builds main menu
	kidGameMethods()
	{
		// We will build the initial frame and give it traits here
		mainMenu.setLayout(null);
		mainMenu.setVisible(true);
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setResizable(false);
		mainMenu.setSize(new Dimension(340,460));
		mainMenu.getContentPane().setBackground(Color.white);
	}
		
	//builds main menu's buttons and help 
	public void initializeMMbh()
	{
		//build Jbutton for starting quiz or build mode
		JButton startQ = new JButton("Start Quiz!");
		JButton startB = new JButton("Start Build!");
		// As requested
		JButton rave = new JButton("?");
		// button for help
		JButton help = new JButton("Need Help?");
		
		// image for intro
		try {
			// this.getClass().getResource("Filename"); 
			// is used because when the program is exported the locations of files changes
			startPic = ImageIO.read( this.getClass().getResourceAsStream("background.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//// add them to mainMenu frame
	//	mainMenu(startPic);
		mainMenu.add(startQ);
		mainMenu.add(startB);
		mainMenu.add(help);
		mainMenu.add(rave);
		mainMenu.add(pic);
		mainMenu.repaint();
		// give the buttons position and size
		pic.setBounds(0,0,320,460);
		startQ.setBounds(10, 340, 155, 25);
		startB.setBounds(10, 365, 155, 25);
		help.setBounds(170, 340, 155, 50);
		rave.setBounds(290, 0,50,30);
		
		// add action listens
		help.addActionListener(new helpClick());
		startQ.addActionListener(new quizStart());
		startB.addActionListener(new buildStart());
		rave.addActionListener(new mysteryButton());
	}
	
	// build a method that creates a dialog box that contains the text of a file
	// This method spawns a help box each time it is called
	public void buildHelp()
	{
		// this frame will be created each time help is clicked
		JFrame helpWin = new JFrame("Help");
		helpWin.setResizable(false);
		helpWin.setSize(340, 460);
		
		// JText area that holds input. I will be adding a text doc to it
		JTextArea helpText = new JTextArea();
		helpText.setEnabled(false);
		helpText.setBackground(Color.BLACK);
		// go through a text doc and add each line to the text area
		try
		{
			//FileReader fr = new FileReader("help.txt");
			BufferedReader br = new BufferedReader((new InputStreamReader(
                    this.getClass().getResourceAsStream("help.txt"))));
			s = br.readLine();
			while(s != null)
			{
				helpText.append(s);
				helpText.append("\n");
				s = br.readLine();
			}
			br.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		// 	//make it scrollable by adding the jtextarea to a scroll pane
		JScrollPane help = new JScrollPane(helpText);
		// add it
		helpWin.add(help);
		helpWin.setVisible(true);

	}


	///////////////////////////////////////////////////
	/// ACTION LISTENERS
	
	/// this is for when help is clicked, it builds a help window
	public class helpClick implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			buildHelp();
		}
	}	
	
	// this is for when startQuiz is clicked. In this, we need to run a method that would boot directly to the quiz
	public class quizStart implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mainMenu.setVisible(false);
			// This will initialize the game window, which will contain quiz and build mode.
			// This will be done by building an object of another class. This other class will contain game data 
			// The variable in the parameters will tell the other class whether to boot to build or quiz mode 
			
			new kidGameModes(1);
			
		}	
	}
	
	// this is for when startBuild is clicked. In this, we need to run a method that would boot directly to the quiz
	public class buildStart implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mainMenu.setVisible(false);
			new kidGameModes(0);
			
		}	
	}
	// This was for the requested feature
	public class mysteryButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			new sqaureRave();
			
		
		}
		
	}
	
	///////////////////////////////////////////////////
	/// PAINT CLASS
	public class drawPic extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(startPic, 0, 0, null);
		}
	}
	

}
