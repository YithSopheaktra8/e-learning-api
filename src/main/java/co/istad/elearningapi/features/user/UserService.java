package co.istad.elearningapi.features.user;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    Page<UserDetailsResponse> findAll(int page , int size);

    UserDetailsResponse findUser(String username);

    @Transactional
    BasedMessage disableByUsername(String username);

    @Transactional
    BasedMessage enableByUsername(String username);

    @Transactional
    void deleteByUserName(String username);
}
