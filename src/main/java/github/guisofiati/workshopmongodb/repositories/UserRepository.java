package github.guisofiati.workshopmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import github.guisofiati.workshopmongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}