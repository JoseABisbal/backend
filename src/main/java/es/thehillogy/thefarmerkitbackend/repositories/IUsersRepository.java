package es.thehillogy.thefarmerkitbackend.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.thehillogy.thefarmerkitbackend.models.User;

@Repository
public interface IUsersRepository extends JpaRepository<User, Long> {

	public Page<User> findAllByOrderByCreatedDateDesc(Pageable pageable);
	
	public Optional<User> findByUserName(String userName);
}