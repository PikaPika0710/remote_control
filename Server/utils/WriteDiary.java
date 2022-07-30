package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteDiary {
	
	private static final String PATH = "C:/Diary";
	private static final String FILENAME = "Diary.txt";

	private WriteDiary() {

	}

	public static void writeDiary(String message) {
		try {
			createFolder();
			FileWriter writer = new FileWriter(PATH + "/" + FILENAME, true);
			BufferedWriter buffer = new BufferedWriter(writer);
			buffer.write(message);
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void createFolder() {
		File folderUpload = new File(PATH);
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
	}
}
