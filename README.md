# CS622 FINAL PROJECT  - WEB CRAWLER
##### Author: Jared Baca

Watch the video walkthrough [here] (https://youtu.be/4fIpm_v3rPw)

## Overview
This web application searches TikTok, Instagram, and YouTube for videos based on a keyword provided by the user. It downloads a given number of videos from each platform and displays them in a gallery view. The number of videos downloaded can be set in the Constants.java file. After downloading all available videos, it runs Py Scene Detect on each video and breaks it into shorter segments so that users can easily access individual scenes. These scenes appear in a separate video carousel when the "Scenes" button is clicked. This button appears once the scenes have been processed.

## How to Run
The application can be run in development mode from within Eclipse by selecting Run As > Spring Boot App from the Run menu in Eclipse. When prompted to select a java application to run, select "BacaCs622FinalProjectApplication - springboot". This will launch the back end of the project, which runs on localhost:8080 by default. To launch the front end in a browser, navigate from the project's working directory to the following directory in a Terminal: src/main/webapp/ui/baca-final-ui 
From this directory, enter the command "npm start" to launch the React application on localhost:3000. A browser window should automatically open, however if it does not you can open a web browser and go to localhost:3000. As long as both the front end and back end are running, you can enter search terms in the provided search bar. Download progress can be monitored in the Eclipse console.

## Dependencies
### Included in Build
spring-boot-starter-web
spring-boot-starter-test
tomcat-jasper: 10.1.1
jsoup: 1.15.3
### Not included in Build
[yt-dlp] (https://github.com/yt-dlp/yt-dlp)
[PySceneDetect] (http://scenedetect.com/en/latest/)
Python 3.7+ (https://www.python.org/downloads/)
ffmpeg
### Node Modules
react-bootstrap
Material UI
React Icons
React Player
React HTML5 Video

## Detailed Description
#### Yt-dlp, Java, Shell Scripts
Yt-dlp is the backbone of this project. It is used to perform all video downloads, and in the case of YouTube and Instagram it performs the video search as well. All yt-dlp searching and downloading is done with shell scripts that are called by the Java code via the ProcessBuilder class. The TikTok search takes place in a separate Java class using the Jsoup library to parse the HTML directly from www.tiktok.com. Once the video URLs have been extracted, they are passed to yt-dlp shell script and from there process is the same as the other two platforms.

Yt-dlp download all videos locally to an assets folder within the project directory. It prints the filepath for each file into a separate JS file that will be used as an index for importing the videos into React. It also prints the filename of each downloaded video to a separate text file, which will be read by PySceneDetect. Once all videos have been downloaded, the scene detection process begins. Similarly to the download process, PySceneDetect is run on each video via shell scripts called by Java classes. 

#### PySceneDetect
There was a design decision made here to run SceneDetect after all videos had been downloaded. The time required to download each video and then run it through SceneDetect before displaying it on the UI was significant. For the sake of the user experience, I decided to download all full length videos first and display them in a gallery, and then run the scene breakdowns in the background once all videos had been downloaded. This allows the user to look through the results and begin watching videos while the scenes are still processing. Once the scenes become available, a "Scenes" button appears next to each video. 

#### Spring Boot, Tomcat
The searching, downloading, and scene detection are all initiated by a Java class called VideoSearchController. This class utilizes Spring Boot to make the Java application available on localhost:8080 via a Tomcat Server. This is what allows React to interact with the Java code. The video search controller utilizes some keky Spring Boot features to make this connection possible, namely "@Controller" and "@RequestMapping" to make the code available at a specific URL (localhost:8080/search).

#### React
The UI was built using create-react-app, and is comprised of several Components. The SearchBar component actually functions like as an entire "App" component, and in future revisions could probably be consolidated with the App component. It contains the VideoGallery and VideoCarousel components, which are used for the full-length videos and scene collections respectively. The search bar itself is a form element containing a TextField component from Material UI. The "handleSubmit" method that is called when a form submission takes place makes a fetch request to localhost:8080/search with the search term that was entered. This calls the Java program that has been made available on port 8080 via Spring Boot. 

React imports the videos via the index JS files mentioned above. They are empty to start. When the first video is downloaded, the shell script prints the following text to the index file: 

const YT_VIDEOS = {video1: require($FILE)}; export default YT_VIDEOS; 

Using the useEffect hook in my SearchBar Component allows React to respond to the updates in the index file and import the newly printed JavaScript object. It iterates over the object and passes the video URI to the VideoGallery component, which in turn passes it to the Video component and renders it as soon as it is available. Each time the index file is updated by the shell script, React reimports the const YT_VIDEOS object and assigns the videos it contains to the correct Video components via props. The scene import works the same way, this time passing the video data one step further into the VideoCarousel component contained within the VideoGallery component.


## Included Files
### Java Classes
Constants.java
videoRemover.java
InstagramGoogleSearch.java
InstagramSceneDetect.java
BacaCss622FinalProjectApplication.java
VideoSearchController.java
TikTokSceneDetect.java
TikTokSearch.java
YouTubeSceneDetect.java
YouTubeSearch.java
### Shell Scripts
igSceneDetect
igSearch
TTDownload
ttSceneDetect
ytSceneDetect
ytSearch
### React Components
react-video-gallery.css
SearchBar.js
video.jsx
VideoCarousel.css
VideoCarousel.js
VideoGallery.js

### Notes
- In order to get around the login requirements for Instagram videos, yt-dlp utilizes browser cookies. If the user has successfully logged into Instagram in Chrome, then yt-dlp uses those cookies to authenticate. The functionality of this is somewhat unreliable, and results in many failed download attempts. This does not cause any errors, but instead aborts the current download and moves on to the next. Because of this, the number of Instagram results may be much more limited than those for TikTok, and the desired results parameter should be adjusted accordingly.

- Similarly, because I have imposed a 50MB file size limit on YouTube downloads, many of the URLs found in an initial YouTube search will likely fail. This is intentional, to avoid downloading excessively large videos such as "4 Hours of Classical Piano To study To". Because of this, the desired number of results should also be overshot.

- Scene Detection occurs on one video at a time, however the array of scene videos is loaded for an entire gallery at once. This is unlike the full-length videos, which load individually as they are downloaded. In future iterations, I may be able to make better use of the useEffect hook to render the scenes on a video by video basis.

- The current version of the project runs in development mode in an IDE. The next phase will be to create an executable JAR or WAR file, but that has not yet been implemented.

## Sources

Packaging a React.JS Application into a Java Spring Boot Application - Wazoo Web Bytes (https://www.youtube.com/watch?v=_CLLw3QAuOE&t=1142s)
React JS - React Tutorial for Beginners - Programming with Mosh (https://www.youtube.com/watch?v=Ke90Tje7VS0&t=1734s)
Responsive Video Gallery in React js | Build a Video Gallery With React js | Video Gallery in React - Code With Yd (https://www.youtube.com/watch?v=B_IYFFbKkfY)
Web App using Spring Boot - Telusko (https://www.youtube.com/watch?v=nLbvzF1-vXY&t=304s)
yt-dlp Documentation (https://github.com/yt-dlp/yt-dlp)
PySceneDetect Documentation (http://scenedetect.com/en/latest/)
Material UI Guides (https://mui.com/material-ui/getting-started/usage/)
https://www.w3schools.com
https://developer.mozilla.org/en-US/

