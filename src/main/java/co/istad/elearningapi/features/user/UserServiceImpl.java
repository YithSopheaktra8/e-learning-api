package co.istad.elearningapi.features.user;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserDetailsResponse> findAll(int page, int size, String sortDirection, String userName,
                                             String email, String nationalIdCard,
                                             String phoneNumber,
                                             String name,
                                             String gender,
                                             Role role) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero");
        }

        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }
        Sort.Direction direction = Sort.Direction.DESC;
        if (sortDirection.equalsIgnoreCase("ASC")) {
            direction = Sort.Direction.ASC;
        }
        Sort sortById = Sort.by(direction, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //filter
        Page<User> users;
        if (userName != null && !userName.isEmpty()) {
            users = userRepository.findAllByUserName(userName, pageRequest);
        } else if (email != null && !email.isEmpty()) { // Filter by email
            users = userRepository.findAllByEmail(email, pageRequest);
        } else if (nationalIdCard != null && !nationalIdCard.isEmpty()) { // Filter by national ID card
            users = userRepository.findAllByNationalIdCard(nationalIdCard, pageRequest);
        } else if (phoneNumber != null && !phoneNumber.isEmpty()) { // Filter by phone number
            users = userRepository.findAllByPhoneNumber(phoneNumber, pageRequest);
        } else if (name != null && !name.isEmpty()) { // Filter by name
            users = userRepository.findAllByGivenName(name, pageRequest);
        } else if (gender != null && !gender.isEmpty()) { // Filter by gender
            users = userRepository.findAllByGender(gender, pageRequest);
        } else if (role != null) { // Filter by role
            users = userRepository.findALlByRoles(role, pageRequest);
        } else {
            users = userRepository.findAll(pageRequest);
        }

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

    @Transactional
    @Override
    public BasedMessage disableByUsername(String username) {
        if (!userRepository.existsUserByUserName(username)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User does not exist!"
            );
        }
        userRepository.disableByUserName(username);
        return new BasedMessage("User has been disable");
    }

    @Transactional
    @Override
    public BasedMessage enableByUsername(String username) {
        if (!userRepository.existsUserByUserName(username)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User does not exist!"
            );
        }
        userRepository.enableUserByUserName(username);
        return new BasedMessage("User has been enable");
    }

    @Transactional
    @Override
    public BasedMessage deleteByUserName(String username) {
        if (!userRepository.existsUserByUserName(username)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User does not exist!"
            );
        }
        userRepository.deleteByUserName(username);
        return new BasedMessage("User has been deleted successfully");
    }
}
