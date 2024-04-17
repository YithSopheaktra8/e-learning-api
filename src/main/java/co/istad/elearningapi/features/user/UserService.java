package co.istad.elearningapi.features.user;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    Page<UserDetailsResponse> findAll(int page , int size, String sortDirection, String userName,
                                      String email,  String nationalIdCard,
                                      String phoneNumber,
                                      String name,
                                      String gender,
                                      Role role);

    UserDetailsResponse findUser(String username);

    BasedMessage disableByUsername(String username);

    BasedMessage enableByUsername(String username);

    BasedMessage deleteByUserName(String username);
}
