package co.istad.elearningapi.features.user;


import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    // Find all users
    @GetMapping
    List<UserDetailsResponse> findAll(){
        return userService.findAll();
    }
    // Find user detail by username
    @GetMapping("/{username}")
    UserDetailsResponse findUserDetail(@PathVariable String username){
        return userService.findUser(username);
    }

    // Disable a user
    @PutMapping("/{username}/disable")
    void disableUser (@PathVariable String username){
        userService.disableUserByUsername(username);
    }

    // Enable a user
    @PutMapping("/{username}/enable")
    void enableUser (@PathVariable String username){
        userService.enableUserByUsername(username);
    }

    // Permanently delete a use
    @DeleteMapping("/{username}")
    void deleteUser (@PathVariable String username){
        userService.deleteUserByUserName(username);
    }


}
