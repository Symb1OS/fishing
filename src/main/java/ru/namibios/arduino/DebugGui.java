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
import java.nio.charset.StandardCharsets;
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
		
	    lKapchaImg.setIcon(new ImageIcon("resources/debug/20170913_145148_581.jpg"));
	    lKapchaText.setText("aaasss");
	    
	    lFilterOne.setIcon(new ImageIcon("resources/loot/ok/fish/ersh.jpg"));
	    lFilterTwo.setIcon(new ImageIcon("resources/loot/ok/fish/cherepaxa.jpg"));
	    
	    aStatus.append("[14.09.2017 18:18:47] - Wait fish... \n");
	    aStatus.append("[14.09.2017 18:18:47] - Wait fish...\n");
	    aStatus.append("[14.09.2017 18:18:47] - Wait fish...\n");
	    aStatus.append("[14.09.2017 18:18:47] - Cut the fish...\n");
		
	}

	public DebugGui() {
	    super("Fishbot");
	    
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
	    
	    Runnable readLog = () -> {
	    	try {
	    		
		    	List<String> list = Files.readAllLines(Paths.get("work.log"), StandardCharsets.UTF_8);
		    	for (String line : list) {
					aStatus.append(line + "\n");
				}
	    	
	    	}catch (IOException e) {
				e.printStackTrace();
			}
	    };
	    
	    readLog.run();
			
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
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater( () -> {
		    	DebugGui app = new DebugGui();
				app.setVisible(true);
		});
		
	}
}