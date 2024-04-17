package co.istad.elearningapi.features.user;


import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    // Find all users
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    Page<UserDetailsResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String userName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String nationalIdCard,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Role role
    ){
        return userService.findAll(page, size, sortDirection, userName, email, nationalIdCard, phoneNumber, name, gender, role);
    }
    // Find user detail by username
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{username}")
    UserDetailsResponse findUserDetail(@PathVariable String username){
        return userService.findUser(username);
    }

    // Disable a user
    @PutMapping("/{username}/disable")
    void disableUser (@PathVariable String username){
        userService.disableByUsername(username);
    }

    // Enable a user
    @PutMapping("/{username}/enable")
    void enableUser (@PathVariable String username){
        userService.enableByUsername(username);
    }

    // Permanently delete a use
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}")
    void deleteUser (@PathVariable String username){
        userService.deleteByUserName(username);
    }


}
