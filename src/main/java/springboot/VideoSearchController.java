package springboot;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.bu.met622.finalproject.Constants;
import edu.bu.met622.finalproject.videoRemover;
import igSearch.InstagramGoogleSearch;
import jakarta.servlet.http.HttpServletRequest;
import tikTokSearch.TikTokSearch;
import ytSearch.YouTubeSearch;
import ytSearch.YouTubeSceneDetect;
import tikTokSearch.TikTokSceneDetect;
import igSearch.InstagramSceneDetect;

/**
 * Spring Boot controller that performs the full Java video search code when the browser
 * makes a request to localhost:8080/search
 * @author jbaca
 *
 */

@Controller
public class VideoSearchController implements Constants {
	
	@RequestMapping("search")
	
	public void search(HttpServletRequest req) {
		String keyword = req.getParameter("keyword");
		TikTokSearch ttSearch = new TikTokSearch();
		YouTubeSearch ytSearch = new YouTubeSearch();
		InstagramGoogleSearch igSearch = new InstagramGoogleSearch();
		YouTubeSceneDetect ytSceneDetect = new YouTubeSceneDetect();
		TikTokSceneDetect ttSceneDetect = new TikTokSceneDetect();
		InstagramSceneDetect igSceneDetect = new InstagramSceneDetect();
		
		try {
			
			//Delete Existing Videos
			videoRemover deleteExistingVids = new videoRemover();
			
			deleteExistingVids.overwriteVideoIndex(TT_INDEX_FILE);
			deleteExistingVids.overwriteVideoIndex(TT_SCENE_INDEX_FILE);
			deleteExistingVids.overwriteVideoIndex(TT_FILENAMES);
			deleteExistingVids.deleteVids(TT_VID_FOLDER);
			
			deleteExistingVids.overwriteVideoIndex(IG_INDEX_FILE);
			deleteExistingVids.overwriteVideoIndex(IG_SCENE_INDEX_FILE);
			deleteExistingVids.overwriteVideoIndex(IG_FILENAMES);
			deleteExistingVids.deleteVids(IG_VID_FOLDER);
			
			deleteExistingVids.overwriteVideoIndex(YT_INDEX_FILE);
			deleteExistingVids.overwriteVideoIndex(YT_SCENE_INDEX_FILE);
			deleteExistingVids.overwriteVideoIndex(YT_FILENAMES);
			deleteExistingVids.deleteVids(YT_VID_FOLDER);
			
			
			//Perform Searches
			ttSearch.search(keyword);
			igSearch.search(keyword);
			ytSearch.search(keyword);
			
			//Perform Scene Detect
			ttSceneDetect.detectScenes();
			igSceneDetect.detectScenes();
			ytSceneDetect.detectScenes();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
