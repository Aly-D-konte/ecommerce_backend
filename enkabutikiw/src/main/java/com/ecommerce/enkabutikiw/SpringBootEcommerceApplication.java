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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

		if (roleRepository.findAll().size() == 0) {
			roleRepository.createRole();


		}



       //Pour envoyer plusieurs mails
	  //	List<String> recipients = Arrays.asList("camaramamady9160@gmail.com", "alykonte19@gmail.com");
	 //	emailSenderService.sendEmailToMultipleRecipients("Test Email", "Bonjour!", recipients);



		//envoie un seul email apres lancement du projet
		//emailSenderService.sendSimpleEmail("coulibalyadamabekaye03@gmail.com","test","Bonjours");
		//System.out.println("Message envoyer !");
	}


}
