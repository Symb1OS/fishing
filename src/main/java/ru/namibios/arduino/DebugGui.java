package ru.namibios.arduino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.Property;
import ru.namibios.arduino.utils.Message;
import ru.namibios.arduino.utils.Path;

public class DebugGui extends JFrame{
	
	final static Logger logger = Logger.getLogger(Gui.class);
	
	private static final String COM_PORT = "COM7";
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 300;
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lKapchaImg = new JLabel();
	private JLabel lKapchaText = new JLabel();
	
	private JLabel lFilterOne = new JLabel();
	private JLabel lFilterTwo = new JLabel();
	
	private JLabel lAutoUse = new JLabel("Автоюз:");
	private JCheckBox jBear = new JCheckBox("Пиво");
	private JCheckBox jMinigame = new JCheckBox("Мини-игра");
	
	private JLabel lFilter = new JLabel("Фильтр лута:");
	private JCheckBox jRock = new JCheckBox("Камни");
	private JCheckBox jFish = new JCheckBox("Рыба");
	private JCheckBox jKey = new JCheckBox("Ключи");
	private JCheckBox jEvent= new JCheckBox("Ивент");
	
	private JButton startButton = new JButton("Старт");
	private JButton stopButton = new JButton("Стоп");
	
	private Container container = this.getContentPane();
	
	private Transfer transfer = new Transfer();
	
	private Thread threadTransfer;

	public DebugGui() {
	    super("Fishbot");
	    
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    setLocationRelativeTo(null);  
	    
	    setAlwaysOnTop(true);
	    setLayout(new BorderLayout());
	    this.setResizable(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    jBear.setSelected(true);
	    
	    JPanel debugContainer = new JPanel();
	    debugContainer.setLayout(new BoxLayout(debugContainer, BoxLayout.PAGE_AXIS));
	    debugContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
	    
	    JPanel kapchaContainer = new JPanel();
	    kapchaContainer.setLayout(new FlowLayout());
	    
	    lKapchaImg.setIcon(new ImageIcon("resources/debug/20170705_214741_c.jpg"));
	    kapchaContainer.add(lKapchaImg);

	    lKapchaText.setText("aaasss");
	    kapchaContainer.add(lKapchaText);

	    JPanel lootContainer = new JPanel();
	    lootContainer.setLayout(new FlowLayout());
	    
	    lFilterOne.setIcon(new ImageIcon("resources/loot/ok/fish/ersh.jpg"));
	    lFilterTwo.setIcon(new ImageIcon("resources/loot/ok/fish/cherepaxa.jpg"));
	    
	    lootContainer.add(lFilterOne);
	    lootContainer.add(lFilterTwo);
	    
	    debugContainer.add(kapchaContainer);
	    debugContainer.add(lootContainer);
	    
	    JPanel filterContainer = new JPanel();
	    filterContainer.setLayout(new GridLayout(10, 1));
	    
	    filterContainer.add(lAutoUse);
	    filterContainer.add(jBear);
	    filterContainer.add(jMinigame);
	    
	    filterContainer.add(lFilter);
	    filterContainer.add(jRock);
	    filterContainer.add(jKey);
	    filterContainer.add(jEvent);
	    filterContainer.add(jFish);
	    
	    JPanel buttonContainer = new JPanel();
	    buttonContainer.add(startButton);
	    buttonContainer.add(stopButton);
	    
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
	    
	    container.add(filterContainer, BorderLayout.CENTER);
	    container.add(debugContainer,  BorderLayout.EAST);
	    container.add(buttonContainer, BorderLayout.SOUTH);
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
		    	DebugGui app = new DebugGui();
				app.setVisible(true);
		});
	}
}