package edu.bu.met622.finalproject;

import java.io.File;
import java.io.FileWriter;

/**
 * This class removes previously downloaded video files and overwrites the JavaScript index files
 * that are accessed by React.
 * After a particularly distressing file removal catasrophe that deleted the entire project from
 * my hard drive, I have added if statements that only remove files with specific titles or
 * keywords in their names (such as "scene")
 * @author jbaca
 *
 */

public class videoRemover implements Constants {
	
	public void deleteVids(File videoFolder) throws Exception {

		String[] contentsList = videoFolder.list();
		File[] files = videoFolder.listFiles();
		
		for(File file : files) {
			if (file.isDirectory()) {
				File[] subfolderContents = file.listFiles();
				for(File subfile : subfolderContents) {
					if(file.getName().contains("scene")) {
					subfile.delete();
					}
				}
			}
			if(videoFolder.getName().equalsIgnoreCase("TikTok") ||
					videoFolder.getName().equalsIgnoreCase("Instagram") ||
					videoFolder.getName().equalsIgnoreCase("YouTube")) { 
				file.delete();
			}
		}	
	}
	
	/**
	 * Overwrites current index file (either the vids file or scenes file) with empty string. 
	 * Can also be used for filenames.txt files.
	 * @param indexFile
	 * @throws Exception
	 */
	
	public void overwriteVideoIndex(File indexFile) throws Exception {
		
		FileWriter writer = new FileWriter(indexFile);
		writer.write("");
		writer.close();
	}
	
	public static void main(String[] args) {
		try {
			videoRemover delete = new videoRemover();
			delete.overwriteVideoIndex(IG_SCENE_INDEX_FILE);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
