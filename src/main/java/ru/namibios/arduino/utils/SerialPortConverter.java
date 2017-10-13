package ru.namibios.arduino.utils;

import java.lang.reflect.Method;

import org.aeonbits.owner.Converter;

import com.fazecast.jSerialComm.SerialPort;

public class SerialPortConverter implements Converter<SerialPort>{

	@Override
	public SerialPort convert(Method method, String text) {
		System.out.println(text);
		SerialPort serialPort = SerialPort.getCommPort(text.trim());
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		return serialPort;
	}

}