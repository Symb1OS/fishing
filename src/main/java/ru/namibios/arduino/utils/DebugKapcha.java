package ru.namibios.arduino.utils;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ru.namibios.arduino.Kapcha;

public class DebugKapcha extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 150;
	
	private Container container = this.getContentPane();
	
	private JLabel kapchaimage = new JLabel();
	private JTextField kapchaParse = new JTextField();
	
	private JButton startButton = new JButton("Старт");
	
	private JButton correctButton = new JButton("Верно");
	private JButton incorrectButton = new JButton("Неверно");
	
	private JPanel south = new JPanel();
	private JPanel center = new JPanel();
	
	public DebugKapcha() {
		
		super("Debug Kapcha");
	    
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    setLocationRelativeTo(null);  
	    
	    setAlwaysOnTop(true);
	    setLayout(new BorderLayout());
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    center.setLayout(new GridLayout(1, 2));
	    center.add(kapchaimage);
	    center.add(kapchaParse);
	    
	    correctButton.addActionListener(new CorrectListener());
	    incorrectButton.addActionListener(new IncorrectListener());
	   
	    
	    JPanel correctButtonPanel = new JPanel();
	    
	    correctButtonPanel.setLayout(new GridLayout(1, 2));
	    correctButtonPanel.add(correctButton);
	    correctButtonPanel.add(incorrectButton);
	    
	    south.setLayout(new GridLayout(2, 1));
	    south.add(correctButtonPanel);
	    
	    startButton.addActionListener(new StartListener());
	    south.add(startButton);
	    
	    container.add(center, BorderLayout.CENTER);
	    container.add(south, BorderLayout.SOUTH);
	    
	}
	
	private boolean isCorrect;
	private boolean isIncorrect;
	
	class StartListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try{
						
						String filename= "resources/debug";
						File folder = new File(filename);
						for (File file: folder.listFiles()) {
							System.out.println(file);
							if(file.isFile()){
								
								kapchaimage.setIcon(new ImageIcon(file.toString()));
								
								Kapcha kapcha = new Kapcha(file.toString());
								String key = kapcha.getKey("bef1c08eedddbe9f9d83a0f07d0d26ce9b360a55");
								
								kapchaParse.setText(key);	
								
								while(true){
									if(isCorrect){
										file.renameTo(new File("resources/debug/correct/" + file.getName()));
									} 
									
									if(isIncorrect){
										file.renameTo(new File("resources/debug/incorrect/" + file.getName()));
									}
									
									if(isCorrect || isIncorrect){
										isCorrect = isIncorrect = false;
										break;
									} 
									Thread.sleep(200);
									
								}	
							
							}
						}
					
					}catch(Exception e){e.printStackTrace();}
					
				}
			}).start();;
		}
		
	}
	
	class CorrectListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				isCorrect = true;
				
			}
		 
	 } 
	 
	 class IncorrectListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				isIncorrect = true;
				
			}
			 
	 } 
	
	public static void main(String[] args) {
		DebugKapcha debugKapcha = new DebugKapcha();
		debugKapcha.setVisible(true);
	}
	
}