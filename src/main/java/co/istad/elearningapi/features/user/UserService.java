package co.istad.elearningapi.features.user;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;

import java.util.List;

public interface UserService {
    List<UserDetailsResponse> findAll();
    UserDetailsResponse findUser(String username);

    BasedMessage disableUserByUsername(String username);

    BasedMessage enableUserByUsername(String username);

    void deleteUserByUserName(String username);
}
