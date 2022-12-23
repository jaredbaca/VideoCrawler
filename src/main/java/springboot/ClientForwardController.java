package springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Simple Spring Boot Controller designed to redirect pages to localhost:8080. 
 * Not currently used in this build of the project, but was part of an earlier
 * experiment. Did not delete for the sake of not breaking the project.
 * @author jbaca
 *
 */

@Controller
public class ClientForwardController {
	
	@GetMapping(value = "/**/{path:[^\\.]*}")

	public String forward() {
		return ("forward:/");
	}
}
