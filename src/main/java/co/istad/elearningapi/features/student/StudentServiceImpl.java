package co.istad.elearningapi.features.student;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import co.istad.elearningapi.features.role.RoleRepository;
import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import co.istad.elearningapi.features.student.dto.StudentUpdateRequest;
import co.istad.elearningapi.features.user.UserRepository;
import co.istad.elearningapi.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentMapper studentMapper;


    @Transactional
    @Override
    public void createStudent(StudentCreateRequest studentCreateRequest) {

        if(userRepository.existsUserByUserName(studentCreateRequest.username())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User is already existed!"
            );
        }

        User user = new User();
        user.setUserName(studentCreateRequest.username());
        user.setEmail(studentCreateRequest.email());
        user.setDob(studentCreateRequest.dob());
        user.setPassword(studentCreateRequest.password());
        user.setAddress1(studentCreateRequest.address1());
        user.setAddress2(studentCreateRequest.address2());
        user.setUuid(UUID.randomUUID().toString());
        user.setPhoneNumber(studentCreateRequest.phoneNumber());
        user.setGender(studentCreateRequest.gender());
        user.setFamilyName(studentCreateRequest.familyName());
        user.setGivenName(studentCreateRequest.givenName());
        user.setProfile(studentCreateRequest.profile());
        user.setNationalIdCard(studentCreateRequest.nationalIdCard());
        user.setIsDeleted(false);
        user.setIsVerified(false);
        // role country

        List<Role> roles = new ArrayList<>();

        Role userRole = roleRepository.findByName("USER");

        Role studentRole = roleRepository.findByName("STUDENT");

        roles.add(userRole);
        roles.add(studentRole);

        // set student role
        user.setRoles(roles);

        log.info("user info:  {}",user);

        Student student = new Student();
        student.setHighSchool(studentCreateRequest.highSchool());
        student.setUniversity(studentCreateRequest.university());
        student.setIsBlock(false);
        student.setUser(user);

        log.info("student info:  {}",student);

        userRepository.save(user);
        studentRepository.save(student);

    }

    @Override
    public Page<StudentResponse> findAllStudent(int page, int size) {

        if(page < 0 ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "page number must be greater than 0"
            );
        }

        if(size < 1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Size must be greater than 1!"
            );
        }

        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Student> students = studentRepository.findAll(pageRequest);

        return students.map(studentMapper::toStudentResponse);
    }

    @Override
    public StudentResponse findByUsername(String username) {

        User user = userRepository.findByUserName(username)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "This user does not exist"
                ));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "This student does not exist"
                ));

        return studentMapper.toStudentResponse(student);
    }


    @Override
    public void editStudentProfile(String username , StudentUpdateRequest studentUpdateRequest) {

        User user = userRepository.findByUserName(username)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "This user does not exist"
                ));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "This student does not exist"
                ));


        studentMapper.editUserStudentProfile(studentUpdateRequest,user);
        studentMapper.editStudentProfile(studentUpdateRequest,student);

        studentRepository.save(student);
        userRepository.save(user);

    }
}
