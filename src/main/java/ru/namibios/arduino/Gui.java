package ru.namibios.arduino;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.Property;

public class Gui extends JFrame{
	
	final static Logger logger = Logger.getLogger(Gui.class);
	
	private static final String YESNO = "Да/Нет";
	private static final String COM_PORT = "COM7";
	private static final int WINDOW_WIDTH = 200;
	private static final int WINDOW_HEIGHT = 200;

	private static final long serialVersionUID = 1L;
	
	private JLabel lBear = new JLabel("Пиво");
	private JCheckBox jBear = new JCheckBox(YESNO);
	
	private JLabel lMinigame = new JLabel("Мини-игра");
	private JCheckBox jMinigame = new JCheckBox(YESNO);
	
	private JLabel lFood1 = new JLabel("Обед 1");
	private JCheckBox jFood1 = new JCheckBox(YESNO);
	
	private JLabel lFood2 = new JLabel("Обед 2 ");
	private JCheckBox jFood2 = new JCheckBox(YESNO);
	
	private JLabel lFood3 = new JLabel("Обед 3");
	private JCheckBox jFood3 = new JCheckBox(YESNO);
	
	private JButton startButton = new JButton("Старт");
	private JButton stopButton = new JButton("Стоп");
	
	private Container container = this.getContentPane();
	
	private Transfer transfer = new Transfer(COM_PORT);
	
	private Thread threadTransfer;
	
	public Gui() {
	    super("Fishbot");
	    
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    setLocationRelativeTo(null);  
	    
	    setAlwaysOnTop(true);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    container.setLayout(new FlowLayout());

	    jBear.setSelected(true);
	    
		Container paramContainer = new Container();
	    paramContainer.setLayout(new GridLayout(7, 2));
	    
	    paramContainer.add(lBear);
	    paramContainer.add(jBear);
	    
	    paramContainer.add(lMinigame);
	    paramContainer.add(jMinigame);
	    
	    paramContainer.add(lFood1);
	    paramContainer.add(jFood1);
	    
	    paramContainer.add(lFood2);
	    paramContainer.add(jFood2);
	    
	    paramContainer.add(lFood3);
	    paramContainer.add(jFood3);
	    
	    startButton.addActionListener(new StartListener());
	    paramContainer.add(startButton);
	    
	    stopButton.addActionListener(new StopListener());
	    paramContainer.add(stopButton);
	    
	    container.add(paramContainer);
	    
	}
	
	class StartListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			logger.info("Programm start...");
			
			Property property = new Property();
			property.setBear(jBear.isSelected());
			property.setMinigame(jMinigame.isSelected());
			property.setDinner1(jFood1.isSelected());
			property.setDinner2(jFood2.isSelected());
			property.setDinner3(jFood3.isSelected());
			
			transfer.setProperty(property);
			
			threadTransfer = new Thread(transfer);
			threadTransfer.start();
			//transfer.run();
		}
	}
	
	class StopListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//transfer.pause(); 
			threadTransfer.interrupt();
		}
	}
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	Gui app = new Gui();
				app.setVisible(true);
		    }
		});

	}

}