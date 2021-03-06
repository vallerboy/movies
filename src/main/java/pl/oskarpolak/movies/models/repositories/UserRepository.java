package pl.oskarpolak.movies.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.movies.models.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM `user` WHERE `username` = ?1 AND `password` = ?2")
    Optional<UserEntity> findUserByLoginAndPassword(String username, String password);

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(`id`) > 0 THEN 'true' ELSE 'false' END FROM `user` WHERE `username` = ?1")
    boolean isUsernameTaken(String username);

    @Query(nativeQuery = true, value = "SELECT * FROM `user` WHERE `username` = ?1")
    UserEntity findUserByUsername(String username);

    @Query(nativeQuery = true, value = "UPDATE `user` SET `is_deleted` = 1 WHERE `id` = ?1")
    @Modifying
    void setIsDeletedAsTrueByUserId(int userId);
}
