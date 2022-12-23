package igSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import edu.bu.met622.finalproject.Constants;

public class InstagramSceneDetect implements Constants{

/**
 * Calls the Instagram scene detect shell script. 	
 * @throws IOException
 */
	
public void detectScenes() throws IOException {
		
		String[] commands = {SHELL, IG_SCENE_DETECT};
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
