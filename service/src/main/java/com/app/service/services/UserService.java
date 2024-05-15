package com.app.service.services;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.UserRequest;
import com.app.model.Role;
import com.app.model.User;
import com.app.service.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleService roleService;
	@Autowired (required = false)
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	public User findOne(Integer id) {
		return userRepository.findById(id).orElseGet(null);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findByRole() {
		return userRepository.findByRole();
	}
	
	public List<User> findAllUsers(){
		return userRepository.findAllUsers();
	}

	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

//	public void sendNotificaition(User user) throws MailException, InterruptedException, MessagingException {
//
//
//		MimeMessage message = javaMailSender.createMimeMessage();
//		
//		message.setSubject("You are registered");
//		message.setFrom(env.getProperty("spring.mail.username"));
//		message.setRecipients(MimeMessage.RecipientType.TO, user.getEmail());
//		
//
//		String htmlContent = "<h1>This is a test Spring Boot email</h1><a href='http://localhost:3000/activationLink/" + user.getId() + "'>activation link</a>"
//				+ "<p>It can contain <strong>HTML</strong> content.</p>";
//		message.setContent(htmlContent, "text/html; charset=utf-8");
//
//		javaMailSender.send(message);
//
//	}

	public User create(UserRequest userRequest) {
		User u = userRequest.toUser();

		if (u == null || u.getEmail() == null || u.getPassword() == null) {
			return null;
		}

//		if (isEmailRegistered(u.getEmail())) {
//			return null;
//		}

		u.setEmail(userRequest.getEmail());

		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));

		u.setName(userRequest.getName());
		u.setSurname(userRequest.getSurname());
		u.setUsername(userRequest.getUsername());
		

		List<Role> roles = roleService.findByName("ROLE_USER");
		u.setRoles(roles);
		
		u = userRepository.save(u);
//		try {
//			sendNotificaition(u);
//		} catch (MailException | InterruptedException | MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return u;
	}
	
	public User createAdmin(UserRequest userRequest) {
		User u = userRequest.toUser();

		if (u == null || u.getEmail() == null || u.getPassword() == null) {
			return null;
		}

//		if (isEmailRegistered(u.getEmail())) {
//			return null;
//		}

		u.setEmail(userRequest.getEmail());

		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));

		u.setName(userRequest.getName());
		u.setSurname(userRequest.getSurname());
		u.setUsername(userRequest.getUsername());
		u.setEmail(userRequest.getEmail());
		

		List<Role> roles = roleService.findByName("ROLE_ADMIN");
		u.setRoles(roles);
		u = userRepository.save(u);
//		try {
//			sendNotificaition(u);
//		} catch (MailException | InterruptedException | MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return u;
	}

	public User update(UserRequest userRequest, int id) {
		User user = findOne(id);

		if (user == null) {
			return null;
		}

		user.setId(userRequest.getId());
		user.setName(userRequest.getName());
		user.setSurname(userRequest.getSurname());
		// Provera da li se korisnicko ime promenilo
	    if (!user.getUsername().equals(userRequest.getUsername())) {
	        // Ako se korisnicko ime promenilo, ažuriraj ga
	        user.setUsername(userRequest.getUsername());
	    }
		user.setEmail(userRequest.getEmail());

		return userRepository.save(user);
	}

	public User activate( int id) {
		User user = findOne(id);

		if (user == null) {
			return null;
		}
		List<Role> roleUser = roleService.findByName("ROLE_USER");
		List<Role> roleAdmin = roleService.findByName("ROLE_ADMIN");
		
		if("ROLE_USER".equals(user.getRoles().get(0).getName())) {
			user.setRoles(roleAdmin);
		}else if("ROLE_ADMIN".equals(user.getRoles().get(0).getName())) {
			user.setRoles(roleUser);
		}
		
		//List<Role> roles = roleService.findByName(user.getRoles().get(0).getName());
		//user.setRoles(roles);
		return userRepository.save(user);
	}
	
	
//	public boolean forgotPassword(String email) {
//	    User user = findByEmail(email);
//
//	    if (user == null) {
//	        return false;
//	    }
//
//	    String temporaryPassword = generateTemporaryPassword();
//
//	    try {
//	        sendTemporaryPasswordEmail(user, temporaryPassword);
//	    } catch (Exception e) {
//	        return false;
//	    }
//
//	    user.setPassword(passwordEncoder.encode(temporaryPassword));
//	    save(user);
//
//	    return true;
//	}

//    private String generateTemporaryPassword() {
//        return RandomStringUtils.randomAlphanumeric(6);
//    }
//
//    private void sendTemporaryPasswordEmail(User user, String temporaryPassword) throws MessagingException {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        message.setSubject("Temporary Password");
//        message.setFrom(env.getProperty("spring.mail.username"));
//        message.setRecipients(MimeMessage.RecipientType.TO, user.getEmail());
//
//        String htmlContent = "Your temporary password is: " + temporaryPassword;
//        message.setContent(htmlContent, "text/html; charset=utf-8");
//
//        javaMailSender.send(message);
//    }
	

	public void remove(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
		}
	}

//	public boolean isEmailRegistered(String email) {
//
//		User user = userRepository.findByEmail(email);
//		return user != null;
//	}

}
