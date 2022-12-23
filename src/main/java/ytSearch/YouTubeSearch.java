package ytSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import edu.bu.met622.finalproject.Constants;

/**
 * This class calls the YouTube scene detect shell script using Java ProcessBuilder.
 * @author jbaca
 *
 */

public class YouTubeSearch implements Constants{
	
public void search(String searchTerm) throws IOException {
		
		String[] commands = {SHELL, YT_SEARCH, NUM_OF_RESULTS_STR, searchTerm, MAX_FILE_SIZE};
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
