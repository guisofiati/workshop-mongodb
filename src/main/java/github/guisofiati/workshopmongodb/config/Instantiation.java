package github.guisofiati.workshopmongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import github.guisofiati.workshopmongodb.domain.User;
import github.guisofiati.workshopmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Joséfa", "maria@gmail.com");
		User jose = new User(null, "José Enelson", "jose@gmail.com");
		User joao = new User(null, "João dos Santos", "joao@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, jose, joao));
	}
}