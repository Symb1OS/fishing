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

public class View extends JFrame{

	final static Logger logger = Logger.getLogger(View.class);
	
	private static final int WIDTH = 520;
	private static final int HEIGHT = 300;

	private static final long serialVersionUID = 1L;
	
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
	    lKapchaImg.setIcon(new ImageIcon("D:\\work\\workspace\\eclipse\\fishing\\resources\\kapcha\\20170729_015515_638.jpg"));
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
	    lLootImgOne.setIcon(new ImageIcon("D:\\work\\workspace\\eclipse\\fishing\\resources\\loot\\ok\\key\\key.jpg"));
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
	    lLootImgTwo.setIcon(new ImageIcon("D:\\work\\workspace\\eclipse\\fishing\\resources\\loot\\ok\\scala\\scala.jpg"));
	    GridBagConstraints gbc_lLootImgTwo = new GridBagConstraints();
	    gbc_lLootImgTwo.insets = new Insets(0, 0, 5, 5);
	    gbc_lLootImgTwo.gridx = 3;
	    gbc_lLootImgTwo.gridy = 1;
	    kapchaLootPanel.add(lLootImgTwo, gbc_lLootImgTwo);
	    
	    JPanel logPanel = new JPanel();
	    workPanel.add(logPanel);
	    logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.X_AXIS));
	    
	    JTextArea textArea = new JTextArea();
	    textArea.setFont(new Font("Times New Roman", Font.PLAIN, 11));
	    textArea.setText("[11.10.2017 21:31:43] - Port is open...\r\r\n[11.10.2017 21:31:43] - Start Fish...\r\r\n[11.10.2017 21:31:46] - Sended message: space\r\r\n[11.10.2017 21:31:51] - Wait fish..\r\r\n[11.10.2017 21:31:54] - Wait fish..\r\r\n[11.10.2017 21:31:57] - Wait fish..\r\r\n[11.10.2017 21:32:00] - Wait fish..\r\r\n[11.10.2017 21:32:03] - Wait fish..\r\r\n[11.10.2017 21:32:06] - Sended message: space\r\r\n[11.10.2017 21:32:08] - Sended message: space\r\r\n[11.10.2017 21:32:12] - Cut the fish...\r\r\n[11.10.2017 21:32:12] - Parsing kapcha...\r\r\n[11.10.2017 21:32:12] - Clean the noise...\r\r\n[11.10.2017 21:32:13] - Clean ended...\r\r\n[11.10.2017 21:32:14] - Sended message: \"sw\"\r\r\n[11.10.2017 21:32:24] - Check loot...\r\r\n[11.10.2017 21:32:29] - Trash. Throw out.\r\r\n[11.10.2017 21:32:29] - Check loot...\r\r\n[11.10.2017 21:32:34] - Trash. Throw out.\r\r\n[11.10.2017 21:32:34] - Check loot...\r\r\n[11.10.2017 21:32:39] - Trash. Throw out.\r\r\n[11.10.2017 21:32:39] - Check loot...\r\r\n[11.10.2017 21:32:44] - Trash. Throw out.\r\r\n[11.10.2017 21:32:44] - Check loot...\r\r\n[11.10.2017 21:32:49] - Trash. Throw out.\r\r\n[11.10.2017 21:32:49] - Check loot...\r\r\n[11.10.2017 21:32:54] - Trash. Throw out.\r\r\n[11.10.2017 21:32:54] - Check loot...\r\r\n[11.10.2017 21:32:59] - Trash. Throw out.\r\r\n[11.10.2017 21:32:59] - Check loot...\r\r\n[11.10.2017 21:33:03] - Trash. Throw out.\r\r\n[11.10.2017 21:33:03] - Check loot...\r\r\n[11.10.2017 21:43:15] - Test\r\r\n[11.10.2017 21:43:18] - Programm start...\r\r\n[11.10.2017 21:43:18] - key=  bef1c08eedddbe9f9d83a0f07d0d26ce9b360a55\r\r\n[11.10.2017 21:43:18] - Start...\r\r\n[11.10.2017 21:43:22] - Port is open...\r\r\n[11.10.2017 21:43:22] - Start Fish...\r\r\n[11.10.2017 21:43:25] - Sended message: space\r\r\n[11.10.2017 21:43:30] - Wait fish..\r\r\n[11.10.2017 21:43:33] - Wait fish..\r\r\n[11.10.2017 21:43:36] - Wait fish..\r\r\n[11.10.2017 21:43:39] - Wait fish..\r\r\n[11.10.2017 21:43:42] - Wait fish..\r\r\n[11.10.2017 21:43:45] - Wait fish..\r\r\n[11.10.2017 21:43:48] - Wait fish..\r\r\n[11.10.2017 21:43:51] - Wait fish..\r\r\n[11.10.2017 21:43:54] - Wait fish..\r\r\n[11.10.2017 21:43:57] - Wait fish..\r\r\n[11.10.2017 21:44:00] - Wait fish..\r\r\n[11.10.2017 21:44:03] - Wait fish..\r\r\n[11.10.2017 21:44:06] - Wait fish..\r\r\n[11.10.2017 21:44:09] - Wait fish..\r\r\n[11.10.2017 21:44:12] - Wait fish..\r\r\n[11.10.2017 21:44:15] - Wait fish..\r\r\n[11.10.2017 21:44:18] - Wait fish..\r\r\n[11.10.2017 21:44:21] - Wait fish..\r\r\n[11.10.2017 21:44:24] - Wait fish..\r\r\n[11.10.2017 21:44:27] - Wait fish..\r\r\n[11.10.2017 21:44:30] - Wait fish..\r\r\n[11.10.2017 21:44:33] - Wait fish..\r\r\n[11.10.2017 21:44:36] - Wait fish..\r\r\n[11.10.2017 21:44:39] - Wait fish..\r\r\n[11.10.2017 21:44:42] - Wait fish..\r\r\n[11.10.2017 21:44:45] - Wait fish..\r\r\n[11.10.2017 21:44:48] - Wait fish..\r\r\n[11.10.2017 21:44:51] - Wait fish..\r\r\n[11.10.2017 21:44:54] - Wait fish..\r\r\n[11.10.2017 21:44:57] - Wait fish..\r\r\n[11.10.2017 21:45:00] - Wait fish..\r\r\n[11.10.2017 21:45:03] - Wait fish..\r\r\n[11.10.2017 21:45:06] - Wait fish..\r\r\n[11.10.2017 21:45:09] - Wait fish..\r\r\n[11.10.2017 21:45:12] - Wait fish..\r\r\n[11.10.2017 21:45:15] - Sended message: space\r\r\n[11.10.2017 21:45:17] - Sended message: space\r\r\n[11.10.2017 21:45:21] - Cut the fish...\r\r\n[11.10.2017 21:45:21] - Parsing kapcha...\r\r\n[11.10.2017 21:45:21] - Clean the noise...\r\r\n[11.10.2017 21:45:22] - Clean ended...\r\r\n[11.10.2017 21:45:23] - Sended message: \"swww\"\r\r\n[11.10.2017 21:45:33] - Check loot...\r\r\n[11.10.2017 21:45:38] - Loot is not recognized... Take.\r\r\n[11.10.2017 21:45:38] - Sended message: r\r\r\n[11.10.2017 21:45:38] - Start Fish...\r\r\n[11.10.2017 21:45:41] - Sended message: space\r\r\n[11.10.2017 21:45:46] - Wait fish..\r\r\n[11.10.2017 21:45:49] - Wait fish..\r\r\n[11.10.2017 21:45:52] - Wait fish..\r\r\n[11.10.2017 21:45:55] - Wait fish..\r\r\n[11.10.2017 21:45:58] - Wait fish..\r\r\n[11.10.2017 21:46:01] - Wait fish..\r\r\n[11.10.2017 21:46:04] - Wait fish..\r\r\n[11.10.2017 21:46:07] - Wait fish..\r\r\n[11.10.2017 21:46:10] - Wait fish..\r\r\n[11.10.2017 21:46:13] - Wait fish..\r\r\n[11.10.2017 21:46:16] - Wait fish..\r\r\n[11.10.2017 21:46:19] - Wait fish..\r\r\n[11.10.2017 21:46:22] - Wait fish..\r\r\n[11.10.2017 21:46:25] - Wait fish..\r\r\n[11.10.2017 21:46:28] - Wait fish..\r\r\n[11.10.2017 21:46:31] - Wait fish..\r\r\n[11.10.2017 21:46:34] - Wait fish..\r\r\n[11.10.2017 21:46:37] - Wait fish..\r\r\n[11.10.2017 21:46:40] - Wait fish..\r\r\n[11.10.2017 21:46:43] - Wait fish..\r\r\n[11.10.2017 21:46:46] - Wait fish..\r\r\n[11.10.2017 21:46:49] - Wait fish..\r\r\n[11.10.2017 21:46:52] - Wait fish..\r\r\n[11.10.2017 21:46:55] - Wait fish..\r\r\n[11.10.2017 21:46:58] - Wait fish..\r\r\n[11.10.2017 21:47:01] - Wait fish..\r\r\n[11.10.2017 21:47:04] - Wait fish..\r\r\n[11.10.2017 21:47:07] - Wait fish..\r\r\n[11.10.2017 21:47:10] - Wait fish..\r\r\n[11.10.2017 21:47:13] - Wait fish..\r\r\n[11.10.2017 21:47:16] - Wait fish..\r\r\n[11.10.2017 21:47:19] - Wait fish..\r\r\n[11.10.2017 21:47:23] - Wait fish..\r\r\n[11.10.2017 21:47:26] - Wait fish..\r\r\n[11.10.2017 21:47:29] - Wait fish..\r\r\n[11.10.2017 21:47:32] - Wait fish..\r\r\n[11.10.2017 21:47:35] - Sended message: space\r\r\n[11.10.2017 21:47:36] - Sended message: space\r\r\n[11.10.2017 21:47:40] - Cut the fish...\r\r\n[11.10.2017 21:47:40] - Parsing kapcha...\r\r\n[11.10.2017 21:47:40] - Clean the noise...\r\r\n[11.10.2017 21:47:41] - Clean ended...\r\r\n[11.10.2017 21:47:42] - Sended message: \"sadw\"\r\r\n[11.10.2017 21:47:52] - Check loot...\r\r\n[11.10.2017 21:47:57] - Loot is not recognized... Take.\r\r\n[11.10.2017 21:47:57] - Sended message: r\r\r\n[11.10.2017 21:47:57] - Start Fish...\r\r\n[11.10.2017 21:48:00] - Sended message: space\r\r\n[11.10.2017 21:48:05] - Wait fish..\r\r\n[11.10.2017 21:48:08] - Wait fish..\r\r\n[11.10.2017 21:48:11] - Wait fish..");
	    textArea.setBackground(Color.BLACK);
	    textArea.setForeground(Color.GREEN);
	    textArea.setEditable(false);
	   
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    logPanel.add(scrollPane);
	    
	    textArea.setCaretPosition(textArea.getDocument().getLength());
	    
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
	    butonPanel.add(bStart, gbc_bStart);
	    
	    JButton bStop = new JButton("Стоп");
	    GridBagConstraints gbc_bStop = new GridBagConstraints();
	    gbc_bStop.fill = GridBagConstraints.HORIZONTAL;
	    gbc_bStop.gridx = 3;
	    gbc_bStop.gridy = 0;
	    butonPanel.add(bStop, gbc_bStop);
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
