package ru.namibios.arduino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.Property;
import ru.namibios.arduino.utils.Message;
import ru.namibios.arduino.utils.Path;

public class Gui extends JFrame{
	
	final static Logger logger = Logger.getLogger(Gui.class);
	
	private static final String YESNO = "Да/Нет";
	private static final String COM_PORT = "COM7";
	private static final int WINDOW_WIDTH = 250;
	private static final int WINDOW_HEIGHT = 250;

	private static final long serialVersionUID = 1L;
	
	JTabbedPane tabPane = new JTabbedPane();
	
	private JLabel lAutoUse = new JLabel("Автоюз:");
	
	private JLabel lBear = new JLabel("Пиво");
	private JCheckBox jBear = new JCheckBox(YESNO);
	
	private JLabel lMinigame = new JLabel("Мини-игра");
	private JCheckBox jMinigame = new JCheckBox(YESNO);
	
	private JLabel lFilter = new JLabel("Фильтр лута:");
	
	private JLabel lRock = new JLabel("Камни");
	private JCheckBox jRock = new JCheckBox(YESNO);
	
	private JLabel lFish = new JLabel("Рыба");
	private JCheckBox jFish = new JCheckBox(YESNO);
	
	private JLabel lKey = new JLabel("Ключи");
	private JCheckBox jKey = new JCheckBox(YESNO);
	
	private JLabel lEvent = new JLabel("Ивент");
	private JCheckBox jEvent= new JCheckBox(YESNO);

	private JButton startButton = new JButton("Старт");
	private JButton stopButton = new JButton("Стоп");
	
	private Container container = this.getContentPane();
	
	private Transfer transfer = new Transfer();
	
	private Thread threadTransfer;

	public Gui() {
	    super("Fishbot");
	    
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    setLocationRelativeTo(null);  
	    
	    setAlwaysOnTop(true);
	    setLayout(new BorderLayout());
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    jBear.setSelected(true);
	    
		JPanel fishContainer = new JPanel();
	    fishContainer.setLayout(new GridLayout(3, 2));
	    fishContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
	    
	    fishContainer.add(lAutoUse);
	    fishContainer.add(new JLabel());
	    
	    fishContainer.add(lBear);
	    fishContainer.add(jBear);
	    
	    fishContainer.add(lMinigame);
	    fishContainer.add(jMinigame);
	    
	    JPanel filterContainer = new JPanel();
	    filterContainer.setLayout(new GridLayout(7, 2));
	    
	    filterContainer.add(lFilter);
	    filterContainer.add(new JLabel());
	
	    filterContainer.add(lRock);
	    filterContainer.add(jRock);
	    
	    filterContainer.add(lKey);
	    filterContainer.add(jKey);
	    
	    filterContainer.add(lEvent);
	    filterContainer.add(jEvent);
	    
	    filterContainer.add(lFish);
	    filterContainer.add(jFish);
	    
	    startButton.addActionListener(e -> {
	    	logger.info("Programm start...");
			
	    	SerialPort port = SerialPort.getCommPort(COM_PORT);
	    	port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
	    	
	    	Property.setPort(port);
			Property.setHash(getAuthKey());
			
			Property.setBear(jBear.isSelected());
			Property.setMinigame(jMinigame.isSelected());
			
			Property.setRock(jRock.isSelected());
			Property.setFish(jFish.isSelected());
			Property.setKeys(jKey.isSelected());
			Property.setEvent(jEvent.isSelected());
			
			threadTransfer = new Thread(transfer);
			threadTransfer.start();
	    });
	    
	    filterContainer.add(startButton);
	    
	    stopButton.addActionListener(e -> threadTransfer.interrupt());
	    filterContainer.add(stopButton);
	    
	    container.add(fishContainer, BorderLayout.NORTH);
	    container.add(filterContainer, BorderLayout.CENTER);
	}
	
	private String getAuthKey(){
		String key = null;
		try {
			key = new String(Files.readAllBytes(Paths.get(Path.RESOURCES_KEY)));
			logger.info("key=  " + key);
			if(key.isEmpty()){
				JOptionPane.showMessageDialog(this, Message.KEY_EMPTY);
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, Message.KEY_NOT_FOUND);
			logger.error("Exception: " + e);
			System.exit(1);
		}
		return key;
	}
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater( () -> {
		    	Gui app = new Gui();
				app.setVisible(true);
		});

	}

}