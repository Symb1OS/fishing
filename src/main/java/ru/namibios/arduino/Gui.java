package ru.namibios.arduino;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.fazecast.jSerialComm.SerialPort;

public class Gui extends JFrame{
	
	private static final String YESNO = "Да/Нет";
	private static final String COM_PORT = "COM7";
	private static final int WINDOW_WIDTH = 300;
	private static final int WINDOW_HEIGHT = 200;

	private static final long serialVersionUID = 1L;
	
	private JLabel lPort = new JLabel("Порт");
	private JComboBox<String> cPortList = new JComboBox<String>();
	
	private JLabel lGarbage = new JLabel("Выбрасывать мусор?");
	private JCheckBox jGarbage = new JCheckBox(YESNO);
	
	private JLabel lWorder = new JLabel("Пиво");
	private JCheckBox jWorker = new JCheckBox(YESNO);
	
	private JLabel lFood = new JLabel("Обеды");
	private JCheckBox jFood = new JCheckBox(YESNO);
	
	private JButton startButton = new JButton("Старт");
	private JButton stopButton = new JButton("Стоп");
	
	private JLabel lText = new JLabel("Тестовое поле");
	private JTextField tText = new JTextField();
	
	private Container container = this.getContentPane();
	
	private Transfer transfer = new Transfer(COM_PORT);
	
	public Gui() {
	    super("Fishbot");
	    
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    setLocationRelativeTo(null);  
	    
	    setAlwaysOnTop(true);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    container.setLayout(new FlowLayout());

		Container paramContainer = new Container();
	    paramContainer.setLayout(new GridLayout(7, 2));
	    
	    // инициализация доступных портов
	    SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i = 0; i < portNames.length; i++)
			cPortList.addItem(portNames[i].getSystemPortName());
		
	    paramContainer.add(lPort);
	    paramContainer.add(cPortList);
	    
	    paramContainer.add(lText);
	    paramContainer.add(tText);
	    
	    paramContainer.add(lGarbage);
	    paramContainer.add(jGarbage);
	    
	    paramContainer.add(lFood);
	    paramContainer.add(jFood);
	    
	    paramContainer.add(lWorder);
	    paramContainer.add(jWorker);
	    
	    startButton.addActionListener(new StartListener());
	    paramContainer.add(startButton);
	    
	    stopButton.addActionListener(new StopListener());
	    paramContainer.add(stopButton);
	    
	    container.add(paramContainer);
	    
	}
	
	class StartListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.out.println("start");
			//tText.requestFocus();
			transfer.run();
		}
	}
	
	class StopListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			transfer.pause();
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