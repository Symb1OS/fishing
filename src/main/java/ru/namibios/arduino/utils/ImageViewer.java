package ru.namibios.arduino.utils;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ImageViewer extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 372;
	private static final int WINDOW_HEIGHT = 58;

	private Container container = this.getContentPane();
	
	private JLabel image = new JLabel();
	
	public ImageViewer() throws IOException {
	    super("Viewer");
	    
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    setLocationRelativeTo(null);  
	    
	    setAlwaysOnTop(true);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    container.setLayout(new FlowLayout());
	    ImageIcon icon = new ImageIcon(ImageIO.read(new File("resources/debug/8/20170714_223632_860.jpg")));
	    image.setIcon(icon);
	    container.addMouseListener(new MouseListener());
	    container.add(image);
	    
	}
	
	class MouseListener extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			BufferedImage image=null;
			try {
				image= ImageIO.read(new File("resources/debug/2/20170711_222802_871.jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Color color = new Color(image.getRGB(e.getX(), e.getY()));
			System.out.println("R= " + color.getRed() + " G= " + color.getGreen() + " B= " + color.getBlue());
		}
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	try{
			    	ImageViewer app = new ImageViewer();
					app.setVisible(true);
		    	}catch(IOException e){
		    		e.printStackTrace();
		    	}
		    }
		});

	}

}