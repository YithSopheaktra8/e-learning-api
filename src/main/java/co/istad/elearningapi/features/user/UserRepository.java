package co.istad.elearningapi.features.user;


import co.istad.elearningapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Boolean existsUserByUserName(String userName);

    @Modifying
    @Query("DELETE FROM User AS u WHERE u.userName = ?1")
    void deleteByUserName(String userName);

    @Modifying
    @Query("UPDATE User AS u SET u.isDeleted = TRUE WHERE u.userName = ?1")
    void disableByUserName(String userName);

    @Modifying
    @Query("UPDATE User AS u SET u.isDeleted = false WHERE u.userName = ?1")
    void enableUserByUserName(String userName);
}
