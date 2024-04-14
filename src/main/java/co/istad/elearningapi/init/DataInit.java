package co.istad.elearningapi.init;


import co.istad.elearningapi.domain.Authority;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.authority.AuthorityRepository;
import co.istad.elearningapi.features.role.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class DataInit {
        private final RoleRepository roleRepository;
        private final AuthorityRepository authorityRepository;
    @PostConstruct
    void initRole () {
        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");


            Role student = new Role();
            student.setName("STUDENT");

            Role instructor = new Role();
            instructor.setName("INSTRUCTOR");

            Role admin = new Role();
            admin.setName("ADMIN");

            roleRepository.saveAll(List.of(user, student, instructor, admin));

            for (Role role : List.of(user, student, instructor, admin)) {
                if (role.getName().equals("USER")) {
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

                    authorityRepository.saveAll(List.of(userRead,userWrite, userDelete, userUpdate, progressRead, eLearningRead));
                } else if(role.getName().equals("STUDENT")){
                    Authority progressWrite = new Authority();
                    progressWrite.setName("progress:write");

                    authorityRepository.save(progressWrite);


                }else if(role.getName().equals("INSTRUCTOR")) {
                    Authority eLearningWrite = new Authority();
                    eLearningWrite.setName("elearning:write");


                    Authority eLearningDelete = new Authority();
                    eLearningDelete.setName("elearning:delete");


                    Authority eLearningUpdate = new Authority();
                    eLearningUpdate.setName("elearning:update");

                    authorityRepository.saveAll(List.of(eLearningWrite, eLearningDelete, eLearningUpdate));
                } else {
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


                    Authority progressWrite = new Authority();
                    progressWrite.setName("progress:write");

                    Authority eLearningRead = new Authority();
                    eLearningRead.setName("elearning:read");


                    Authority eLearningWrite = new Authority();
                    eLearningWrite.setName("elearning:write");


                    Authority eLearningDelete = new Authority();
                    eLearningDelete.setName("elearning:delete");


                    Authority eLearningUpdate = new Authority();
                    eLearningUpdate.setName("elearning:update");

                    authorityRepository.saveAll(List.of(userRead,userWrite, userDelete, userUpdate, progressRead, progressWrite , eLearningRead, eLearningDelete, eLearningUpdate, eLearningWrite));
                }

            }
        }
    }
}
