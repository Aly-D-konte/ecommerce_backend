package com.ecommerce.enkabutikiw;

import com.ecommerce.enkabutikiw.models.ERole;
import com.ecommerce.enkabutikiw.models.Role;
import com.ecommerce.enkabutikiw.models.User;
import com.ecommerce.enkabutikiw.repository.RoleRepository;
import com.ecommerce.enkabutikiw.repository.UserRepository;
import com.ecommerce.enkabutikiw.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootEcommerceApplication implements  CommandLineRunner{

	public static void main(String[] args) {
    SpringApplication.run(SpringBootEcommerceApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EmailSenderService emailSenderService;

	@Autowired
	PasswordEncoder encoder;


	@Override
	public void run(String... args) throws Exception {

		Role role = new Role();
		User user = new User();
		role.setName(ERole.ROLE_ADMIN);
		Set<Role> roles = new HashSet<>();

		roles.add(role);
		if (userRepository.findAll().size() == 0) {

			user.setEmail("alykonte@gmail.com");
			user.setRoles(roles);
			user.setUsername("aly");
//
//			user.setEmail("adama@gmail.com");
//			user.setRoles(roles);
//			user.setUsername("adama");

			user.setPassword(encoder.encode("aly@123"));
			//	user.setPassword(encoder.encode("adama@123"));


			roleRepository.save(role);
			userRepository.save(user);

		}
		//envoie de l'email apres lancement du projet
		emailSenderService.sendSimpleEmail("test@gmail.com","test","Bonjours");
		System.out.println("Message envoyer !");
	}


}
