package smart.contact.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smart.contact.Entities.User;
import smart.contact.Respository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userrepo;
	
	public User newUserRegister(User user) {
		
		//System.out.println("User"+user);
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		User save = this.userrepo.save(user);
		return save;
	}

}
