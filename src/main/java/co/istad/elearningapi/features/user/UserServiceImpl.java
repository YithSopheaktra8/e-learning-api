package co.istad.elearningapi.features.user;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Page<UserDetailsResponse> findAll(int page , int size) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero");
        }

        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }
        Sort sortById = Sort.by("id");
        PageRequest pageRequest =PageRequest.of(page, size , sortById);
        Page<User> users = userRepository.findAll(pageRequest);

        return users.map(userMapper::toUserDetailResponse);
    }

    @Override
    public UserDetailsResponse findUser(String username) {
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User does not exist!"
        ));
       return userMapper.toUserDetailResponse(user);
    }

    @Override
    public BasedMessage disableByUsername(String username) {
        if(!userRepository.existsUserByUserName(username)){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User does not exist!"
            );
        }
        userRepository.disableByUserName(username);
        return new BasedMessage("User has been disable");
    }

    @Override
    public BasedMessage enableByUsername(String username) {
        if(!userRepository.existsUserByUserName(username)){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User does not exist!"
            );
        }
        userRepository.enableUserByUserName(username);
        return new BasedMessage("User has been enable");
    }

    @Override
    public void deleteByUserName(String username) {
        if(!userRepository.existsUserByUserName(username)){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User does not exist!"
            );
        }
        userRepository.deleteByUserName(username);
    }
}
