package tikTokSearch;

import java.util.LinkedHashSet;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.bu.met622.finalproject.Constants;

/**
 * This class performs the initial TikTok search for a keyword provided by the user via
 * the webform. It uses JSoup to parse the HTML of the TikTok tags page and stores a list of URLs for videos 
 * in a linked hashset. It then writes these URLs to a separate text file that will be read
 * by the TTDownload shell script.
 * Once the search is complete, it calls the TTDownload shell script via ProcessBuiler.
 * @author jbaca
 *
 */

public class TikTokSearch implements Constants {
	
	//Data Members
	private LinkedHashSet<String> videoLinks;
	private Document doc;
	private Element app;
	private Elements divWrappers;
	private Elements videos;
	
	//Constructors
	
	//Simple constructor
	public TikTokSearch() {
		videoLinks = new LinkedHashSet<>();
	}
	
	//Combined constructor and search
	TikTokSearch(String searchTerm) throws IOException {
						
		doc = Jsoup.connect(TIKTOK_SEARCH_URL+searchTerm).get();
		app = doc.getElementById("app");
		divWrappers = app.select("div[class*=DivWrapper]");
		videos = divWrappers.select("a[href*=/video/]");
		
		
		videoLinks = new LinkedHashSet<>();
		for(Element video : videos) {
			videoLinks.add(video.attr("href"));
			System.out.println(video.attr("href"));
		}
	}
	
	
	/*
	 * This method searches TikTok for the hashtag based on the given search term. It parses the HTML using Jsoup and stores the video URLs in a LinkedHashSet
	 */
	
	public LinkedHashSet<String> search(String searchTerm) throws IOException {
		
		//Clears existing results
		
		videoLinks.clear();
		
		//Get HTML from TikTok Hashtag page
		
		doc = Jsoup.connect(TIKTOK_SEARCH_URL+searchTerm).get();
		app = doc.getElementById("app");
		
		//Collect the video URLS by narrowing the search
		
		divWrappers = app.select("div[class*=DivWrapper]");
		videos = divWrappers.select("a[href*=/video/]");
		
		//Save URLs to results list and write to text file for shell script input
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(TIKTOK_URLS));
		
		for(int i = 0; i < videos.size(); i++) {
			videoLinks.add(videos.get(i).attr("href"));
			System.out.println(videos.get(i).attr("href"));
		}

		Iterator<String> it = videoLinks.iterator();
		int i = 0;
		while(it.hasNext() && i < NUM_OF_RESULTS) {
			writer.write(it.next() + " ");
			i++;
		}
		
		writer.close();
		
		
		//Launch the download by running shell script
		
		String[] commands = {SHELL, TIKTOK_DOWNLOAD};
		ProcessBuilder pb = new ProcessBuilder(commands);
		Map<String, String> env = pb.environment();
		env.put("PATH", "/usr/local/bin/");
		pb.directory(WORKING_DIRECTORY);
		pb.redirectErrorStream(true);
		pb.redirectOutput();
		pb.redirectInput();
		Process p = pb.start();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while((line = reader.readLine()) != null ) {
			System.out.println(line);
		}
		
		
		return videoLinks;
	}
	
	void printResults() {
		for (String videoLink : videoLinks) {
			  System.out.println(videoLink);
			}
	}
	

	public static void main(String[] args) {
		
//		final String SEARCH_TERM = "piano";
//		
//		try {
//		
//			TikTokSearch search = new TikTokSearch();
//			search.search(SEARCH_TERM);
//			search.printResults();
//		} 
//		
//		catch(IOException e) {
//			e.printStackTrace();
//		}

	}

}
