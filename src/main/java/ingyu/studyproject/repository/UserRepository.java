package ingyu.studyproject.repository;

import ingyu.studyproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByUsername(String username);
}
