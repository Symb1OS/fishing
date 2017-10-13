package ru.namibios.arduino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;

public class View extends JFrame{

	final static Logger logger = Logger.getLogger(View.class);
	
	private static final int WIDTH = 520;
	private static final int HEIGHT = 300;

	private static final long serialVersionUID = 1L;
	
	private JTextArea taLog = new JTextArea();
	
	private Thread threadTransfer;
	private Thread threadAreaLogger;
	
	public View() {
		
		setResizable(false);
		this.setTitle("Fish bot");
		
		this.setSize(new Dimension(WIDTH, HEIGHT));
     	this.setLocationRelativeTo(null);  
	    this.setAlwaysOnTop(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    
	    JPanel workPanel = new JPanel();
	    getContentPane().add(workPanel, BorderLayout.CENTER);
	    workPanel.setLayout(new BorderLayout(0, 0));
	    
	    JPanel kapchaLootPanel = new JPanel();
	    workPanel.add(kapchaLootPanel, BorderLayout.NORTH);
	    GridBagLayout gbl_kapchaLootPanel = new GridBagLayout();
	    gbl_kapchaLootPanel.columnWidths = new int[] {30, 30, 30, 30, 30};
	    gbl_kapchaLootPanel.rowHeights = new int[] {30, 30};
	    gbl_kapchaLootPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
	    gbl_kapchaLootPanel.rowWeights = new double[]{0.0, 0.0};
	    kapchaLootPanel.setLayout(gbl_kapchaLootPanel);
	    
	    JLabel lKapchaImg = new JLabel("");
	    lKapchaImg.setIcon(new ImageIcon("resources/demo/kapcha.jpg"));
	    GridBagConstraints gbc_lKapchaImg = new GridBagConstraints();
	    gbc_lKapchaImg.insets = new Insets(0, 0, 5, 5);
	    gbc_lKapchaImg.gridx = 0;
	    gbc_lKapchaImg.gridy = 0;
	    kapchaLootPanel.add(lKapchaImg, gbc_lKapchaImg);
	    
	    JLabel lLootOne = new JLabel("Слот 1:");
	    lLootOne.setFont(new Font("Tahoma", Font.BOLD, 13));
	    GridBagConstraints gbc_lLootOne = new GridBagConstraints();
	    gbc_lLootOne.insets = new Insets(0, 0, 5, 5);
	    gbc_lLootOne.gridx = 2;
	    gbc_lLootOne.gridy = 0;
	    kapchaLootPanel.add(lLootOne, gbc_lLootOne);
	    
	    JLabel lLootImgOne = new JLabel("");
	    lLootImgOne.setIcon(new ImageIcon("resources/demo/key.jpg"));
	    GridBagConstraints gbc_lLootImgOne = new GridBagConstraints();
	    gbc_lLootImgOne.fill = GridBagConstraints.HORIZONTAL;
	    gbc_lLootImgOne.insets = new Insets(0, 0, 5, 5);
	    gbc_lLootImgOne.gridx = 3;
	    gbc_lLootImgOne.gridy = 0;
	    kapchaLootPanel.add(lLootImgOne, gbc_lLootImgOne);
	    
	    JPanel labelPanel = new JPanel();
	    GridBagConstraints gbc_labelPanel = new GridBagConstraints();
	    gbc_labelPanel.fill = GridBagConstraints.BOTH;
	    gbc_labelPanel.insets = new Insets(0, 0, 5, 5);
	    gbc_labelPanel.gridx = 0;
	    gbc_labelPanel.gridy = 1;
	    kapchaLootPanel.add(labelPanel, gbc_labelPanel);
	    labelPanel.setLayout(new GridLayout(0, 2, 0, 0));
	    
	    JLabel lKapcha = new JLabel("Результат: ");
	    lKapcha.setHorizontalAlignment(SwingConstants.CENTER);
	    lKapcha.setFont(new Font("Tahoma", Font.BOLD, 20));
	    labelPanel.add(lKapcha);
	    
	    JLabel lKapchaText = new JLabel("wasd");
	    lKapchaText.setFont(new Font("Tahoma", Font.BOLD, 20));
	    labelPanel.add(lKapchaText);
	    
	    JLabel lLootTwo = new JLabel("Слот 2:");
	    lLootTwo.setFont(new Font("Tahoma", Font.BOLD, 13));
	    GridBagConstraints gbc_lLootTwo = new GridBagConstraints();
	    gbc_lLootTwo.insets = new Insets(0, 0, 5, 5);
	    gbc_lLootTwo.gridx = 2;
	    gbc_lLootTwo.gridy = 1;
	    kapchaLootPanel.add(lLootTwo, gbc_lLootTwo);
	    
	    JLabel lLootImgTwo = new JLabel("");
	    lLootImgTwo.setIcon(new ImageIcon("resources/demo/scala.jpg"));
	    GridBagConstraints gbc_lLootImgTwo = new GridBagConstraints();
	    gbc_lLootImgTwo.insets = new Insets(0, 0, 5, 5);
	    gbc_lLootImgTwo.gridx = 3;
	    gbc_lLootImgTwo.gridy = 1;
	    kapchaLootPanel.add(lLootImgTwo, gbc_lLootImgTwo);
	    
	    JPanel logPanel = new JPanel();
	    workPanel.add(logPanel);
	    logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.X_AXIS));
	    
	    taLog.setFont(new Font("Times New Roman", Font.PLAIN, 11));
	    taLog.setBackground(Color.BLACK);
	    taLog.setForeground(Color.GREEN);
	    taLog.setEditable(false);
	   
	    JScrollPane scrollPane = new JScrollPane(taLog);
	    logPanel.add(scrollPane);
	    
	    taLog.setCaretPosition(taLog.getDocument().getLength());
	    
	    JPanel butonPanel = new JPanel();
	    getContentPane().add(butonPanel, BorderLayout.SOUTH);
	    GridBagLayout gbl_butonPanel = new GridBagLayout();
	    gbl_butonPanel.columnWidths = new int[]{116, 155, 115, 120, 0};
	    gbl_butonPanel.rowHeights = new int[]{23, 0};
	    gbl_butonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    gbl_butonPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	    butonPanel.setLayout(gbl_butonPanel);
	    
	    JButton bSetting = new JButton("Настройки");
	    
	    bSetting.addActionListener(new SettingAction());
	    GridBagConstraints gbc_bSetting = new GridBagConstraints();
	    gbc_bSetting.fill = GridBagConstraints.HORIZONTAL;
	    gbc_bSetting.insets = new Insets(0, 0, 0, 5);
	    gbc_bSetting.gridx = 0;
	    gbc_bSetting.gridy = 0;
	    butonPanel.add(bSetting, gbc_bSetting);
	    
	    JButton bStart = new JButton("Старт");
	    GridBagConstraints gbc_bStart = new GridBagConstraints();
	    gbc_bStart.fill = GridBagConstraints.HORIZONTAL;
	    gbc_bStart.insets = new Insets(0, 0, 0, 5);
	    gbc_bStart.gridx = 2;
	    gbc_bStart.gridy = 0;
	    
	    bStart.addActionListener(new StartAction());
	    
	    butonPanel.add(bStart, gbc_bStart);
	    
	    JButton bStop = new JButton("Стоп");
	    GridBagConstraints gbc_bStop = new GridBagConstraints();
	    gbc_bStop.fill = GridBagConstraints.HORIZONTAL;
	    gbc_bStop.gridx = 3;
	    gbc_bStop.gridy = 0;
	    butonPanel.add(bStop, gbc_bStop);
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
							taLog.append(String.valueOf((char)file[i]));
						}
					}
					taLog.setCaretPosition(taLog.getDocument().getLength());
					endFile = file.length;
				}
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	class StartAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
	    	threadTransfer =  new Thread(new Transfer());
	    	threadAreaLogger = new Thread(new AreaLogger());
	    	
	    	threadAreaLogger.start();
	    	threadTransfer.start();
		}
		
	}
	
	class StopAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			threadAreaLogger.interrupt();
			threadTransfer.interrupt();
		}
		
	}
	
	class SettingAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			Setting setting = new Setting();
			setting.setVisible(true);
		}
		
	}
	
	public static void main(String[] args) {
		View view = new View();
		view.setVisible(true);
	}

}
