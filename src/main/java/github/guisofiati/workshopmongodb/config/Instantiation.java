package github.guisofiati.workshopmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import github.guisofiati.workshopmongodb.domain.Post;
import github.guisofiati.workshopmongodb.domain.User;
import github.guisofiati.workshopmongodb.dto.AuthorDTO;
import github.guisofiati.workshopmongodb.dto.CommentDTO;
import github.guisofiati.workshopmongodb.repositories.PostRepository;
import github.guisofiati.workshopmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Joséfa", "maria@gmail.com");
		User jose = new User(null, "José Enelson", "jose@gmail.com");
		User joao = new User(null, "João dos Santos", "joao@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, jose, joao));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo. Abraçaos!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia.", "Acordei feliz hoje kkk", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(jose));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(joao));
		CommentDTO c3 = new CommentDTO("Boa viagem mano!", sdf.parse("23/03/2018"), new AuthorDTO(jose));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(maria);
	}
}