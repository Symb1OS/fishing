package ru.namibios.arduino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.config.Message;
import ru.namibios.arduino.config.Path;
import ru.namibios.arduino.config.Property;

public class Gui extends JFrame{
	
	final static Logger logger = Logger.getLogger(Gui.class);
	
	private static final String COM_PORT = "COM3";
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 300;
	
	private static final long serialVersionUID = 1L;
	
	private static JLabel lKapchaImg = new JLabel();
	private static JLabel lKapchaText = new JLabel();
	
	private static JLabel lFilterOne = new JLabel();
	private static JLabel lFilterTwo = new JLabel();
	
	private JTextArea aStatus = new JTextArea();
	private JScrollPane sStatusPane = new JScrollPane(aStatus);
	
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
	
	private void initTestData() {

	    lKapchaImg.setIcon(new ImageIcon("resources/debug/20170729_015515_638.jpg"));
	    lKapchaText.setText("wasw");
	    
	    lFilterOne.setIcon(new ImageIcon("resources/loot/ok/fish/ersh.jpg"));
	    lFilterTwo.setIcon(new ImageIcon("resources/loot/ok/fish/cherepaxa.jpg"));
	    
	    aStatus.setEditable(false);
	}

	public Gui() {
	    super("Fishbot");
	    
	    new Thread(new AreaLogger()).start();
	    
	    logger.info("Test");
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    setLocationRelativeTo(null);  
	    
	    setAlwaysOnTop(true);
	    setLayout(new BorderLayout());
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    jBear.setSelected(true);
	    jRock.setSelected(true);
	    jEvent.setSelected(true);
	    jFish.setSelected(true);
	    jKey.setSelected(true);
	    
	    aStatus.setFont(new Font("TimesRoman", Font.PLAIN, 11));
	    aStatus.setBackground(Color.BLACK);
	    aStatus.setForeground(Color.GREEN);
	    
	    initTestData();
			
	    JPanel debug = new JPanel();
	    debug.setLayout(new BorderLayout());
	    debug.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	    
	    JPanel debugContainer = new JPanel();
	    debugContainer.setLayout(new BoxLayout(debugContainer, BoxLayout.Y_AXIS));
	    debugContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	
	    JPanel loot = Builder.config()
				 .setLayout(new FlowLayout())
				 .setComponent(new JLabel("Слот 1: "))
				 .setComponent(lFilterOne)
				 .setComponent(new JLabel("Слот 2: "))
				 .setComponent(lFilterTwo)
				 .build();
	    
	    JPanel kapchaImg = new JPanel();
	    kapchaImg.setLayout(new FlowLayout());
	    kapchaImg.add(lKapchaImg);
	    
	    JPanel kapchaText = new JPanel();
	    kapchaText.setLayout(new FlowLayout());
	    kapchaText.add(new JLabel("Результат: "));
	    kapchaText.add(lKapchaText);
	    
	    debugContainer.add(kapchaImg);
	    debugContainer.add(kapchaText);
	    debugContainer.add(loot);
	    
	    debug.add(debugContainer, BorderLayout.NORTH);
	    debug.add(sStatusPane, BorderLayout.CENTER);
	    
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
	    
	    JPanel filterContainer = Builder.config()
				.setLayout(new GridLayout(10, 1))
				.setComponent(lAutoUse)
				.setComponent(jBear)
				.setComponent(jMinigame)
				.setComponent(lFilter)
				.setComponent(jRock)
				.setComponent(jKey)
				.setComponent(jEvent)
				.setComponent(jFish)
				.build();
	    
	    filterContainer.add(startButton);
	    
	    stopButton.addActionListener(e -> threadTransfer.interrupt());
	    filterContainer.add(stopButton);
	    
	    container.add(filterContainer, BorderLayout.CENTER);
	    container.add(debug,  		   BorderLayout.EAST);
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
	
	private static class Builder{
		
		private LayoutManager layout;
		private List<Component> components;
		
		private Builder() {
			components = new ArrayList<Component>();
		}
		
		private static Builder config() {
			return new Builder();
		}
		
		public Builder setLayout(LayoutManager layout) {
			this.layout = layout;
			return this;
		}
		
		public Builder setComponent(Component component) {
			this.components.add(component);
			return this;
		}
		
		public JPanel build() {
			JPanel jPanel = new JPanel();
			
			jPanel.setLayout(layout);
			for (Component component : components) {
				jPanel.add(component);
			}
			return jPanel;
		}
	}
	
	private class AreaLogger implements Runnable{

		@Override
		public void run() {
			try {
	    		long endFile = 0;
	    		while(true) {
	    			byte[] file = Files.readAllBytes(Paths.get("work.log"));
					if(endFile < file.length) {
						for (int i = 0; i < file.length; i++) {
							aStatus.append(String.valueOf((char)file[i]));
						}
					}
					aStatus.setCaretPosition(aStatus.getDocument().getLength());
					endFile = file.length;
				}
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater( () -> {
		    	Gui app = new Gui();
				app.setVisible(true);
		});
		
	}
}