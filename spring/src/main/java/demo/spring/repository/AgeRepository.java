package demo.spring.repository;

import demo.spring.model.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgeRepository extends JpaRepository<Age, Long>{
    @Query("SELECT a FROM Age a WHERE a.beneficitaire = :type ORDER BY a.idage DESC LIMIT 1")
    Optional<Age> findLastAgeByType(@Param("type") String type);
}
