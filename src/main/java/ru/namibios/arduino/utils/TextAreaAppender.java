package ru.namibios.arduino.utils;


import javax.swing.JTextArea;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import ru.namibios.arduino.gui.Gui;

public class TextAreaAppender extends AppenderSkeleton {
	
	private final JTextArea jTextA;

	public TextAreaAppender(Gui gui) {
		jTextA = gui.getTaLog();
	}

	@Override
	protected void append(LoggingEvent event) {
		 
		Layout layout = getLayout();
		 if(layout == null)
			 return;
		 
		if (event.getLevel().equals(Level.INFO)) {
			jTextA.append(layout.format(event));
			jTextA.setCaretPosition(jTextA.getDocument().getLength());
		}
	}

	public void close() {
	}

	public boolean requiresLayout() {
		return false;
	}

}
