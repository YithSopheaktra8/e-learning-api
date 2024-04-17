package co.istad.elearningapi.init;


import co.istad.elearningapi.domain.Authority;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.authority.AuthorityRepository;
import co.istad.elearningapi.features.role.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;

    @PostConstruct
    void initRole () {
        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");
            user.setAuthorities(createUserAuthorities());

            Role student = new Role();
            student.setName("STUDENT");
            student.setAuthorities(createStudentAuthorities());

            Role instructor = new Role();
            instructor.setName("INSTRUCTOR");
            instructor.setAuthorities(createInstructorAuthorities());

            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setAuthorities(createAdminAuthorities());


            roleRepository.saveAll(List.of(user, student, instructor, admin));

        }
    }

    private List<Authority> createUserAuthorities(){
        Authority userRead = new Authority();
        userRead.setName("user:read");


        Authority userWrite = new Authority();
        userWrite.setName("user:write");


        Authority userDelete = new Authority();
        userDelete.setName("user:delete");


        Authority userUpdate = new Authority();
        userUpdate.setName("user:update");


        Authority progressRead = new Authority();
        progressRead.setName("progress:read");


        Authority eLearningRead = new Authority();
        eLearningRead.setName("elearning:read");
        return List.of(userRead,userWrite, userDelete, userUpdate, progressRead, eLearningRead);
    }
    private List<Authority> createStudentAuthorities() {
        Authority progressWrite = new Authority();
        progressWrite.setName("progress:write");
        return List.of(progressWrite);
    }
    private List<Authority> createInstructorAuthorities() {
        Authority eLearningWrite = new Authority();
        eLearningWrite.setName("elearning:write");


        Authority eLearningDelete = new Authority();
        eLearningDelete.setName("elearning:delete");


        Authority eLearningUpdate = new Authority();
        eLearningUpdate.setName("elearning:update");

       return List.of(eLearningWrite, eLearningDelete, eLearningUpdate);
    }
    private List<Authority> createAdminAuthorities() {
        List<Authority> authorities = new ArrayList<>();
        authorities.addAll(createUserAuthorities());
        authorities.addAll(createStudentAuthorities());
        authorities.addAll(createInstructorAuthorities());
        Authority progressWrite = new Authority();
        progressWrite.setName("progress:write");
        return authorities;
    }

}
