package ru.namibios.arduino.config;

public class Message {
	
	public static final int AUTH_BAD = 0;
	public static final int AUTH_OK = 1;
	
	public static final String KEY_NOT_FOUND = "Отсутствует ключ доступа. Файл resources/key not found! Обратитесь к разработчику!";
	public static final String KEY_EMPTY = "Ключ не может быть пустым";
	public static final String KEY_INVALID = "Невалидный ключ";
	public static final Object SERVER_NOT_RESPONDING = "Сервер не доступен, попробуйте позже";
	
}
