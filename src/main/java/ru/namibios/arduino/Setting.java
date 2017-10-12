package ru.namibios.arduino;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;

import ru.namibios.arduino.config.Application;

public class Setting extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField tHash;
	private JTextField tPort;
	private JTextField tStartDelayAfter;
	private JTextField tStartDelayBefore;
	private JTextField tWaitDelayAfter;
	private JTextField tWaitDelayBefore;
	private JTextField tCutDelayAfter;
	private JTextField tCutDelayBefore;
	private JTextField tKapchaDelayBefore;
	private JTextField tKapchaDelayAfter;
	private JTextField tFilterDelayAfter;
	private JTextField tFilterDelayBefore;
	
	private JCheckBox cbFish;
	private JCheckBox cbRock;
	private JCheckBox cbEvent;
	private JCheckBox cbKey;
	
	private JCheckBox cbMinigame;
	private JCheckBox cbBeer;

	public Setting() {
		
		this.setTitle("Настройки");
		this.setLocationRelativeTo(null);  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 52, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lHash = new JLabel("Ключ:");
		lHash.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lHash = new GridBagConstraints();
		gbc_lHash.insets = new Insets(0, 0, 5, 5);
		gbc_lHash.anchor = GridBagConstraints.WEST;
		gbc_lHash.gridx = 0;
		gbc_lHash.gridy = 0;
		getContentPane().add(lHash, gbc_lHash);
		
		tHash = new JTextField();
		tHash.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_tHash = new GridBagConstraints();
		gbc_tHash.anchor = GridBagConstraints.WEST;
		gbc_tHash.insets = new Insets(0, 0, 5, 0);
		gbc_tHash.fill = GridBagConstraints.HORIZONTAL;
		gbc_tHash.gridx = 1;
		gbc_tHash.gridy = 0;
		getContentPane().add(tHash, gbc_tHash);
		tHash.setColumns(10);
		
		JLabel lPort = new JLabel("Порт:");
		GridBagConstraints gbc_lPort = new GridBagConstraints();
		gbc_lPort.anchor = GridBagConstraints.WEST;
		gbc_lPort.insets = new Insets(0, 0, 5, 5);
		gbc_lPort.gridx = 0;
		gbc_lPort.gridy = 1;
		getContentPane().add(lPort, gbc_lPort);
		
		tPort = new JTextField();
		tPort.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_tPort = new GridBagConstraints();
		gbc_tPort.anchor = GridBagConstraints.WEST;
		gbc_tPort.insets = new Insets(0, 0, 5, 0);
		gbc_tPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_tPort.gridx = 1;
		gbc_tPort.gridy = 1;
		getContentPane().add(tPort, gbc_tPort);
		tPort.setColumns(10);
		
		JLabel lLoot = new JLabel("Лут:");
		GridBagConstraints gbc_lLoot = new GridBagConstraints();
		gbc_lLoot.anchor = GridBagConstraints.NORTHWEST;
		gbc_lLoot.gridheight = 2;
		gbc_lLoot.insets = new Insets(0, 0, 5, 5);
		gbc_lLoot.gridx = 0;
		gbc_lLoot.gridy = 3;
		getContentPane().add(lLoot, gbc_lLoot);
		
		JPanel cbPanel = new JPanel();
		cbPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_cbPanel = new GridBagConstraints();
		gbc_cbPanel.gridheight = 2;
		gbc_cbPanel.insets = new Insets(0, 0, 5, 0);
		gbc_cbPanel.fill = GridBagConstraints.BOTH;
		gbc_cbPanel.gridx = 1;
		gbc_cbPanel.gridy = 3;
		getContentPane().add(cbPanel, gbc_cbPanel);
		cbPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		cbFish = new JCheckBox("Рыба");
		cbPanel.add(cbFish);
		
		cbRock = new JCheckBox("Камни");
		cbPanel.add(cbRock);
		
		cbEvent = new JCheckBox("Ивент");
		cbPanel.add(cbEvent);
		
		cbKey = new JCheckBox("Ключи");
		cbPanel.add(cbKey);
		
		JLabel label_1 = new JLabel("Автоюз:");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 6;
		getContentPane().add(label_1, gbc_label_1);
		
		JPanel autoUsePanel = new JPanel();
		autoUsePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_autoUsePanel = (FlowLayout) autoUsePanel.getLayout();
		fl_autoUsePanel.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_autoUsePanel = new GridBagConstraints();
		gbc_autoUsePanel.insets = new Insets(0, 0, 5, 0);
		gbc_autoUsePanel.fill = GridBagConstraints.BOTH;
		gbc_autoUsePanel.gridx = 1;
		gbc_autoUsePanel.gridy = 6;
		getContentPane().add(autoUsePanel, gbc_autoUsePanel);
		
		cbBeer = new JCheckBox("Пиво");
		autoUsePanel.add(cbBeer);
		
		cbMinigame = new JCheckBox("Мини-игра");
		autoUsePanel.add(cbMinigame);
		
		JLabel lDelay = new JLabel("Задержки:");
		lDelay.setHorizontalAlignment(SwingConstants.LEFT);
		lDelay.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lDelay = new GridBagConstraints();
		gbc_lDelay.anchor = GridBagConstraints.NORTHWEST;
		gbc_lDelay.insets = new Insets(0, 0, 5, 5);
		gbc_lDelay.gridx = 0;
		gbc_lDelay.gridy = 8;
		getContentPane().add(lDelay, gbc_lDelay);
		
		JPanel delayPanel = new JPanel();
		delayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_delayPanel = new GridBagConstraints();
		gbc_delayPanel.insets = new Insets(0, 0, 5, 0);
		gbc_delayPanel.fill = GridBagConstraints.BOTH;
		gbc_delayPanel.gridx = 1;
		gbc_delayPanel.gridy = 8;
		getContentPane().add(delayPanel, gbc_delayPanel);
		delayPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel startPanel = new JPanel();
		delayPanel.add(startPanel);
		GridBagLayout gbl_startPanel = new GridBagLayout();
		gbl_startPanel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_startPanel.rowHeights = new int[]{0, 0};
		gbl_startPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0};
		gbl_startPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		startPanel.setLayout(gbl_startPanel);
		
		JLabel lDelayStart = new JLabel("Старт:");
		GridBagConstraints gbc_lDelayStart = new GridBagConstraints();
		gbc_lDelayStart.gridwidth = 3;
		gbc_lDelayStart.anchor = GridBagConstraints.WEST;
		gbc_lDelayStart.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayStart.gridx = 0;
		gbc_lDelayStart.gridy = 0;
		startPanel.add(lDelayStart, gbc_lDelayStart);
		
		tStartDelayBefore = new JTextField();
		GridBagConstraints gbc_tStartDelayBefore = new GridBagConstraints();
		gbc_tStartDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tStartDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tStartDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tStartDelayBefore.gridx = 4;
		gbc_tStartDelayBefore.gridy = 0;
		startPanel.add(tStartDelayBefore, gbc_tStartDelayBefore);
		tStartDelayBefore.setColumns(10);
		
		tStartDelayAfter = new JTextField();
		tStartDelayAfter.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_tStartDelayAfter = new GridBagConstraints();
		gbc_tStartDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tStartDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tStartDelayAfter.gridx = 5;
		gbc_tStartDelayAfter.gridy = 0;
		startPanel.add(tStartDelayAfter, gbc_tStartDelayAfter);
		tStartDelayAfter.setColumns(10);
		
		JPanel waitPanel = new JPanel();
		delayPanel.add(waitPanel);
		GridBagLayout gbl_waitPanel = new GridBagLayout();
		gbl_waitPanel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_waitPanel.rowHeights = new int[] {0, 0};
		gbl_waitPanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_waitPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		waitPanel.setLayout(gbl_waitPanel);
		
		JLabel lDelayWait = new JLabel("Ожидание:");
		GridBagConstraints gbc_lDelayWait = new GridBagConstraints();
		gbc_lDelayWait.gridwidth = 3;
		gbc_lDelayWait.anchor = GridBagConstraints.WEST;
		gbc_lDelayWait.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayWait.gridx = 0;
		gbc_lDelayWait.gridy = 0;
		waitPanel.add(lDelayWait, gbc_lDelayWait);
		
		tWaitDelayBefore = new JTextField();
		GridBagConstraints gbc_tWaitDelayBefore = new GridBagConstraints();
		gbc_tWaitDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tWaitDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tWaitDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tWaitDelayBefore.gridx = 3;
		gbc_tWaitDelayBefore.gridy = 0;
		waitPanel.add(tWaitDelayBefore, gbc_tWaitDelayBefore);
		tWaitDelayBefore.setColumns(10);
		
		tWaitDelayAfter = new JTextField();
		GridBagConstraints gbc_tWaitDelayAfter = new GridBagConstraints();
		gbc_tWaitDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tWaitDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tWaitDelayAfter.gridx = 4;
		gbc_tWaitDelayAfter.gridy = 0;
		waitPanel.add(tWaitDelayAfter, gbc_tWaitDelayAfter);
		tWaitDelayAfter.setColumns(10);
		
		JPanel cutPanel = new JPanel();
		delayPanel.add(cutPanel);
		GridBagLayout gbl_cutPanel = new GridBagLayout();
		gbl_cutPanel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_cutPanel.rowHeights = new int[]{0, 0};
		gbl_cutPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0};
		gbl_cutPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		cutPanel.setLayout(gbl_cutPanel);
		
		JLabel lDelayCut = new JLabel("Подсечка:");
		GridBagConstraints gbc_lDelayCut = new GridBagConstraints();
		gbc_lDelayCut.gridwidth = 3;
		gbc_lDelayCut.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayCut.anchor = GridBagConstraints.WEST;
		gbc_lDelayCut.gridx = 0;
		gbc_lDelayCut.gridy = 0;
		cutPanel.add(lDelayCut, gbc_lDelayCut);
		
		tCutDelayBefore = new JTextField();
		GridBagConstraints gbc_tCutDelayBefore = new GridBagConstraints();
		gbc_tCutDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tCutDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tCutDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tCutDelayBefore.gridx = 4;
		gbc_tCutDelayBefore.gridy = 0;
		cutPanel.add(tCutDelayBefore, gbc_tCutDelayBefore);
		tCutDelayBefore.setColumns(10);
		
		tCutDelayAfter = new JTextField();
		GridBagConstraints gbc_tCutDelayAfter = new GridBagConstraints();
		gbc_tCutDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tCutDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tCutDelayAfter.gridx = 5;
		gbc_tCutDelayAfter.gridy = 0;
		cutPanel.add(tCutDelayAfter, gbc_tCutDelayAfter);
		tCutDelayAfter.setColumns(10);
		
		JPanel kapchaPanel = new JPanel();
		delayPanel.add(kapchaPanel);
		GridBagLayout gbl_kapchaPanel = new GridBagLayout();
		gbl_kapchaPanel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_kapchaPanel.rowHeights = new int[]{0, 0};
		gbl_kapchaPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_kapchaPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		kapchaPanel.setLayout(gbl_kapchaPanel);
		
		JLabel lDelayKapcha = new JLabel("Капча:");
		GridBagConstraints gbc_lDelayKapcha = new GridBagConstraints();
		gbc_lDelayKapcha.anchor = GridBagConstraints.WEST;
		gbc_lDelayKapcha.gridwidth = 3;
		gbc_lDelayKapcha.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayKapcha.gridx = 0;
		gbc_lDelayKapcha.gridy = 0;
		kapchaPanel.add(lDelayKapcha, gbc_lDelayKapcha);
		
		tKapchaDelayAfter = new JTextField();
		GridBagConstraints gbc_tKapchaDelayAfter = new GridBagConstraints();
		gbc_tKapchaDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tKapchaDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tKapchaDelayAfter.gridx = 5;
		gbc_tKapchaDelayAfter.gridy = 0;
		kapchaPanel.add(tKapchaDelayAfter, gbc_tKapchaDelayAfter);
		tKapchaDelayAfter.setColumns(10);
		
		tKapchaDelayBefore = new JTextField();
		GridBagConstraints gbc_tKapchaDelayBefore = new GridBagConstraints();
		gbc_tKapchaDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tKapchaDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tKapchaDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tKapchaDelayBefore.gridx = 4;
		gbc_tKapchaDelayBefore.gridy = 0;
		kapchaPanel.add(tKapchaDelayBefore, gbc_tKapchaDelayBefore);
		tKapchaDelayBefore.setColumns(10);
		
		JPanel filterPanel = new JPanel();
		delayPanel.add(filterPanel);
		GridBagLayout gbl_filterPanel = new GridBagLayout();
		gbl_filterPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_filterPanel.rowHeights = new int[]{0, 0};
		gbl_filterPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_filterPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		filterPanel.setLayout(gbl_filterPanel);
		
		JLabel lDelayFilter = new JLabel("Фильтр");
		GridBagConstraints gbc_lDelayFilter = new GridBagConstraints();
		gbc_lDelayFilter.anchor = GridBagConstraints.WEST;
		gbc_lDelayFilter.gridwidth = 3;
		gbc_lDelayFilter.insets = new Insets(0, 0, 0, 5);
		gbc_lDelayFilter.gridx = 0;
		gbc_lDelayFilter.gridy = 0;
		filterPanel.add(lDelayFilter, gbc_lDelayFilter);
		
		tFilterDelayBefore = new JTextField();
		GridBagConstraints gbc_tFilterDelayBefore = new GridBagConstraints();
		gbc_tFilterDelayBefore.anchor = GridBagConstraints.EAST;
		gbc_tFilterDelayBefore.insets = new Insets(0, 0, 0, 5);
		gbc_tFilterDelayBefore.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFilterDelayBefore.gridx = 4;
		gbc_tFilterDelayBefore.gridy = 0;
		filterPanel.add(tFilterDelayBefore, gbc_tFilterDelayBefore);
		tFilterDelayBefore.setColumns(10);
		
		tFilterDelayAfter = new JTextField();
		GridBagConstraints gbc_tFilterDelayAfter = new GridBagConstraints();
		gbc_tFilterDelayAfter.anchor = GridBagConstraints.EAST;
		gbc_tFilterDelayAfter.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFilterDelayAfter.gridx = 5;
		gbc_tFilterDelayAfter.gridy = 0;
		filterPanel.add(tFilterDelayAfter, gbc_tFilterDelayAfter);
		tFilterDelayAfter.setColumns(10);
		
		JPanel bPanel = new JPanel();
		GridBagConstraints gbc_bPanel = new GridBagConstraints();
		gbc_bPanel.anchor = GridBagConstraints.EAST;
		gbc_bPanel.fill = GridBagConstraints.VERTICAL;
		gbc_bPanel.gridx = 1;
		gbc_bPanel.gridy = 9;
		getContentPane().add(bPanel, gbc_bPanel);
		
		JButton bSave = new JButton("Сохранить");
		bSave.addActionListener(new SaveAction());
		bPanel.add(bSave);
		
		JButton bCancel = new JButton("Отмена");
		bCancel.addActionListener(new CancelAction());
		bPanel.add(bCancel);
		
		init();
	}
	
	class SaveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Application.getInstance().setProperty("HASH", tHash.getText().trim());
			Application.getInstance().setProperty("PORT", tPort.getText().trim());
			
			Application.getInstance().setProperty("KEY",   String.valueOf(cbKey.isSelected()));
			Application.getInstance().setProperty("ROCK",  String.valueOf(cbRock.isSelected()));
			Application.getInstance().setProperty("FISH",  String.valueOf(cbFish.isSelected()));
			Application.getInstance().setProperty("EVENT", String.valueOf(cbEvent.isSelected()));
			
			Application.getInstance().setProperty("BEER",  String.valueOf(cbBeer.isSelected()));
			Application.getInstance().setProperty("MINIGAME", String.valueOf(cbMinigame.isSelected()));
			
			Application.getInstance().setProperty("DELAY_BEFORE_START", tStartDelayBefore.getText().trim());
			Application.getInstance().setProperty("DELAY_AFTER_START", tStartDelayAfter.getText().trim());
			
			Application.getInstance().setProperty("DELAY_BEFORE_WAIT_FISH", tWaitDelayBefore.getText().trim());
			Application.getInstance().setProperty("DELAY_AFTER_WAIT_FISH", tWaitDelayAfter.getText().trim());
			
			Application.getInstance().setProperty("DELAY_BEFORE_CUT_FISH", tCutDelayBefore.getText().trim());
			Application.getInstance().setProperty("DELAY_AFTER_CUT_FISH", tCutDelayAfter.getText().trim());
			
			Application.getInstance().setProperty("DELAY_BEFORE_KAPCHA", tKapchaDelayBefore.getText().trim());
			Application.getInstance().setProperty("DELAY_AFTER_KAPCHA", tKapchaDelayAfter.getText().trim());
			
			Application.getInstance().setProperty("DELAY_BEFORE_FILTER_LOOT", tFilterDelayBefore.getText().trim());
			Application.getInstance().setProperty("DELAY_AFTER_FILTER_LOOT", tFilterDelayAfter.getText().trim());
			
			Application.record();
			dispose();
		}
	} 
	
	
	class CancelAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
	public void init() {
		Application.getInstance();
		tHash.setText(Application.getInstance().HASH());
		tPort.setText(Application.getInstance().PORT());
		
		cbFish.setSelected(Application.getInstance().FISH());
		cbKey.setSelected(Application.getInstance().KEY());
		cbEvent.setSelected(Application.getInstance().EVENT());
		cbRock.setSelected(Application.getInstance().ROCK());
		
		cbBeer.setSelected(Application.getInstance().BEER());
		cbMinigame.setSelected(Application.getInstance().MINIGAME());
		
		tStartDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_START()));
		tStartDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_START()));
		
		tWaitDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_WAIT_FISH()));
		tWaitDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_WAIT_FISH()));
		
		tCutDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_CUT_FISH()));
		tCutDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_CUT_FISH()));
		
		tKapchaDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_KAPCHA()));
		tKapchaDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_KAPCHA()));
		
		tFilterDelayBefore.setText(String.valueOf(Application.getInstance().DELAY_BEFORE_FILTER_LOOT()));
		tFilterDelayAfter.setText(String.valueOf(Application.getInstance().DELAY_AFTER_FILTER_LOOT()));
	}
	
	public static void main(String[] args) {
		
		Setting setting = new Setting();
		setting.pack();
		setting.setVisible(true);
	
	}

}
