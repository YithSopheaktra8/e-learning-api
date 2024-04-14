package co.istad.elearningapi.features.user;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDetailsResponse> findAll() {
        return null;
    }

    @Override
    public UserDetailsResponse findUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User does not exist!"
        ));
       return userMapper.toUserDetailResponse(user);
    }

    @Override
    public BasedMessage disableUserByUsername(String username) {
        if(!findUser(username).equals(username)){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User does not exist!"
            );
        }
        return new BasedMessage("User has been disable");
    }

    @Override
    public BasedMessage enableUserByUsername(String username) {
        if(!findUser(username).equals(username)){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User does not exist!"
            );
        }
        return new BasedMessage("User has been enable");
    }

    @Override
    public void deleteUserByUserName(String username) {
        if(!findUser(username).equals(username)){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User does not exist!"
            );
        }
        userRepository.deleteByUsername(username);
    }
}
