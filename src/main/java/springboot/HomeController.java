package springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Spring Boot controller that maps the /home URL to a test HTML file. 
 * Not currently in use in this build, but didn't want to delete.
 * @author jbaca
 *
 */

@Controller
public class HomeController {
	
	@RequestMapping("home")
	
	public String home(HttpServletRequest req) {
		
		String name = req.getParameter("name");
		System.out.println("Hi "+name);
		
		return ("home");
	}

}
