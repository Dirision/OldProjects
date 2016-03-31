import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;


public class sqaureRave
{
	private static Color randomColor() {
        return new Color((int) (Math.random() * 256),  // Red
                         (int) (Math.random() * 256),  // Green
                         (int) (Math.random() * 256)); // Blue
    }
	 class squareAttack extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponents(g);
	
				g.setColor(randomColor()); 
				g.drawRect(0,0,1024, 768);
				g.fillRect(0, 0, 1024, 768);
				frame.repaint();
			
		
		}
		
		
	}
	
	public sqaureRave()
	{
		JOptionPane.showMessageDialog(null, "WARNING \nDO NOT CONTINUE RUNNING THIS PROGRAM IF YOU ARE EPILICTIC, OR REACT TO RAPID CHANGES IN COLOR");
		frame.setVisible(true);
		
		
		frame.setResizable(false);
		
		JButton but = new JButton("Rave");
		
		but.setPreferredSize(new Dimension(100,100));
		but.addActionListener(new rave());
		
		frame.add(but, BorderLayout.NORTH);
		frame.setPreferredSize(new Dimension(1024, 768));
		frame.setSize(new Dimension(1024, 768));
		
	}
	
	
	
	
	
	public static void main(String[] args)
	{
		
		new sqaureRave();
		
		
	}
	
	
	
	
	
	
	
	JFrame frame = new JFrame("Rave");
	
	public class rave implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.print("Click");
			frame.add(new squareAttack(), BorderLayout.CENTER);
			frame.validate();
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
