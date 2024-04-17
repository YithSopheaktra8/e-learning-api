package co.istad.elearningapi.features.user;


import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Boolean existsUserByUserName(String userName);

    Page<User> findAllByUserName(String userName, Pageable pageable);

    Page<User> findAllByEmail(String email, Pageable pageable);

    Page<User> findAllByNationalIdCard(String nationalIdCard, Pageable pageable);

    Page<User> findAllByPhoneNumber(String phoneNumber, Pageable pageable);

    Page<User> findAllByGivenName(String giveName, Pageable pageable);

    Page<User> findAllByGender(String gender, Pageable pageable);
    Page<User> findALlByRoles (Role roles, Pageable pageable);

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
