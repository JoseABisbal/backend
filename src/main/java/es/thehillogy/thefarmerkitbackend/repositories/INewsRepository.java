package es.thehillogy.thefarmerkitbackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.thehillogy.thefarmerkitbackend.models.News;

@Repository
public interface INewsRepository extends JpaRepository<News, Long> {	
	
	@Query(value = "FROM News n WHERE n.status.statusName = :statusName")
	public Page<News> findAllByStatus(Pageable pageable, String statusName);
	
	@Query(value = "FROM News n WHERE n.publicationDate IS NOT NULL AND n.publicationDate <= GETDATE() AND n.status.id = 2")
	public Page<News> findAllPublished(Pageable pageable);
}
