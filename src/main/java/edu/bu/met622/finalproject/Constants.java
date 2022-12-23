package edu.bu.met622.finalproject;

import java.io.File;

/**
 * This interface contains all constants used throughout the project, such as files/filepaths, URLs, and
 * parameters such as max video filesize and desired number of results. 
 * All classes implement this interface in order to access the constants within.
 * @author jbaca
 *
 */

public interface Constants {
	
	//Shell Scripting
	public static String SHELL = "/bin/sh";
	public static String TIKTOK_DOWNLOAD = "scripts/TTDownload";
	public static String IG_SEARCH = "scripts/igSearch";
	public static String YT_SEARCH = "scripts/ytSearch";
	public static String YT_SCENE_DETECT = "scripts/ytSceneDetect";
	public static String TT_SCENE_DETECT = "scripts/ttSceneDetect";
	public static String IG_SCENE_DETECT = "scripts/igSceneDetect";
	public static String DETECT_SCENES = "scripts/detectScenes";
	public static File WORKING_DIRECTORY = new File(System.getProperty("user.dir"));
	
	
	//TikTok Search 
	public static File TIKTOK_URLS = new File("scripts/tiktok_urls.txt");
	public static String TIKTOK_SEARCH_URL = "https://www.tiktok.com/tag/";
	public static int NUM_OF_RESULTS = 3;
	public static String NUM_OF_RESULTS_STR = "8";
	
	//Instagram Search
	public static File INSTAGRAM_URLS = new File("scripts/instagram_urls.txt");
	public static String IG_SEARCH_URL = "https://www.instagram.com/explore/tags/";
	public static String IG_SEARCH_PREFIX = "Instagram ";
	
	//Youtube Search
	public static String MAX_FILE_SIZE = "50M";
	
	//Video folder locations (used for deleting existing files before each download
	public static final String IG_VIDEO_FOLDER_PATH = "src/main/webapp/ui/baca-final-ui/src/videos/Instagram";
	public static final File IG_VID_FOLDER = new File(IG_VIDEO_FOLDER_PATH);
	
	public static final String TT_VIDEO_FOLDER_PATH = "src/main/webapp/ui/baca-final-ui/src/videos/TikTok";
	public static final File TT_VID_FOLDER = new File(TT_VIDEO_FOLDER_PATH);
	
	public static final String YT_VIDEO_FOLDER_PATH = "src/main/webapp/ui/baca-final-ui/src/videos/YouTube";
	public static final File YT_VID_FOLDER = new File(YT_VIDEO_FOLDER_PATH);
	
	//Video Index Files
	public static final String YT_INDEX_FILEPATH = "src/main/webapp/ui/baca-final-ui/src/videos/ytvids.js";
	public static final File YT_INDEX_FILE = new File(YT_INDEX_FILEPATH);
	
	public static final String TT_INDEX_FILEPATH = "src/main/webapp/ui/baca-final-ui/src/videos/ttvids.js";
	public static final File TT_INDEX_FILE = new File(TT_INDEX_FILEPATH);
	
	public static final String IG_INDEX_FILEPATH = "src/main/webapp/ui/baca-final-ui/src/videos/igvids.js";
	public static final File IG_INDEX_FILE = new File(IG_INDEX_FILEPATH);
	
	//Scene Index Files
	public static final String TT_SCENE_INDEX_FILEPATH = "src/main/webapp/ui/baca-final-ui/src/videos/ttscenes.js";
	public static final File TT_SCENE_INDEX_FILE = new File(TT_SCENE_INDEX_FILEPATH);
	
	public static final String YT_SCENE_INDEX_FILEPATH = "src/main/webapp/ui/baca-final-ui/src/videos/ytscenes.js";
	public static final File YT_SCENE_INDEX_FILE = new File(YT_SCENE_INDEX_FILEPATH);
	
	public static final String IG_SCENE_INDEX_FILEPATH = "src/main/webapp/ui/baca-final-ui/src/videos/igscenes.js";
	public static final File IG_SCENE_INDEX_FILE = new File(IG_SCENE_INDEX_FILEPATH);
	
	//Filename TXT Files
	public static final File TT_FILENAMES = new File("src/main/webapp/ui/baca-final-ui/src/videos/tt_filenames.txt");
	public static final File YT_FILENAMES = new File("src/main/webapp/ui/baca-final-ui/src/videos/yt_filenames.txt");
	public static final File IG_FILENAMES = new File("src/main/webapp/ui/baca-final-ui/src/videos/ig_filenames.txt");



	public static void main(String[] args) {

	}

}
