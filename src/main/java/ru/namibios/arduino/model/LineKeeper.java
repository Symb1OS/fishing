package ru.namibios.arduino.model;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import ru.namibios.arduino.model.ImageParser.ImageType;
import ru.namibios.arduino.utils.Path;

public class LineKeeper {
	
	private long workTime;
	
	public LineKeeper(long time) throws Exception {
		this.workTime = time;
	}
	
	private String getNewFolderName() throws IOException {
		long count = 0;
		
		String path = Path.RESOURCES + Path.DEBUG_LINE;
		
		count = Stream.of(new File(path).listFiles())
						.filter(File::isDirectory)
						.count();
		
		File newFolder = new File(path + String.valueOf(count + 1));
		newFolder.mkdir();
		
		return newFolder.getName();
	}
	
	public void saveImages() throws Exception {
		
		String folder = getNewFolderName();
		String imagesFolder = Path.DEBUG_LINE + folder + "/"; 
		
		long start = System.currentTimeMillis();
		
		while(System.currentTimeMillis() - start < workTime) {
			Screen screen = new Screen(ImageType.LINE);
			screen.saveImage(imagesFolder);
		}
		
	}
}