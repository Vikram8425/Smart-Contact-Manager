package smart.contact.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.contact.Entities.*;
public interface UserRepo extends JpaRepository<User,Integer> {

}
