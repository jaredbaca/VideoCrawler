package igSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import edu.bu.met622.finalproject.Constants;

/**
 * This class utilizes ProcessBuilder to call the Instagram Search shell script.
 * It passes the shell, script name, number of desired results, Instagram search prefix, and search term
 * from the constants interface.
 * @author jbaca
 *
 */

public class InstagramGoogleSearch implements Constants{
	
public void search(String searchTerm) throws IOException {
		
		String[] commands = {SHELL, IG_SEARCH, NUM_OF_RESULTS_STR, IG_SEARCH_PREFIX+searchTerm};
		ProcessBuilder pb = new ProcessBuilder(commands);
		Map<String, String> env = pb.environment();
		env.put("PATH", "/usr/local/bin");
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

