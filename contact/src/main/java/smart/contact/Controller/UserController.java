package smart.contact.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import smart.contact.Entities.User;
import smart.contact.Service.UserService;
import smart.contact.helper.Message;

@Controller
public class UserController {
	@Autowired
	private UserService userservice;
    @RequestMapping("/")
	public String home(Model model) {
    	model.addAttribute("title","Home-Smart Contact Manager");
		return "home";
	}
    
    @RequestMapping("/about")
	public String about(Model model) {
    	model.addAttribute("title","About-Smart Contact Manager");
		return "about";
	}
    @RequestMapping("/signup")
	public String signup(Model model) {
    	model.addAttribute("title","Register-Smart Contact Manager");
    	User u=new User();
//    	u.setAbout("hello");
//    	u.setName("Vikram");
//    	u.setEmail("mk@gmail.com");
    	model.addAttribute("user",u);
		return "signup";
	}
    
    
  // handler for registering user
    
    @RequestMapping(value = "/user_register",method = RequestMethod.POST)
    public String userRegister(@Valid @ModelAttribute("user") User user ,BindingResult result1, @RequestParam(value="agreement",defaultValue ="false")boolean agreement,Model model,HttpSession session ){   	
    	
    	try {
    		System.out.println("agreement"+agreement);
        	// send back Response
        	if(!agreement) {
        	 //model.addAttribute("user",user);
        	 throw new Exception("Plz Accept terms and Condition");
        	}
        	if(result1.hasErrors()){
        		System.out.println("Error"+result1.toString());
        		model.addAttribute("user",user);
        		return "signup";
        	}
        	
        	User newUserRegister = userservice.newUserRegister(user);
        	model.addAttribute("user",new User());
        	session.setAttribute("message", new Message("User Register Successfully","alert-success"));
        	
        	return "signup";
    	}catch(Exception e) {
    		e.printStackTrace();
    		model.addAttribute("user",user);
    		session.setAttribute("message",new Message("Something Went Worng "+e.getMessage(),"alert-danger"));
    		return "signup";
    	}
    	
    	//return "signup";
    }
    
    
  
    }
