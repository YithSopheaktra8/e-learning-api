package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.domain.*;
import co.istad.elearningapi.features.city.CityRepository;
import co.istad.elearningapi.features.country.CountryRepository;
import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningapi.features.role.RoleRepository;
import co.istad.elearningapi.features.user.UserRepository;
import co.istad.elearningapi.mapper.InstructorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public void createInstructor(InstructorCreateRequest instructorCreateRequest) {


        if(!countryRepository.existsByName(instructorCreateRequest.country().toUpperCase())){

            Country newCountry = Country.builder()
                    .name(instructorCreateRequest.country().toUpperCase())
                    .numCode(855)
                    .phoneCode(999)
                    .niceName("Country")
                    .phoneCode(855)
                    .iso("99-ISO")
                    .flag("flag.png")
                    .build();

            City city = City.builder()
                    .name(instructorCreateRequest.city().toUpperCase())
                    .country(newCountry)
                    .build();

            cityRepository.save(city);
            countryRepository.save(newCountry);
        }

        Country oldCountry = countryRepository.findCountryByNameIgnoreCase(instructorCreateRequest.country().toUpperCase());

        City city = cityRepository.findByName(instructorCreateRequest.city().toUpperCase());

        Role instrutorRole = roleRepository.findByName("INSTRUCTOR");

        User user = User.builder()
                .uuid(UUID.randomUUID().toString())
                .country(oldCountry)
                .city(city)
                .roles(List.of(instrutorRole))
                .isVerified(false)
                .dob(instructorCreateRequest.dob())
                .email(instructorCreateRequest.email())
                .gender(instructorCreateRequest.gender())
                .userName(instructorCreateRequest.username())
                .isDeleted(false)
                .phoneNumber(instructorCreateRequest.phoneNumber())
                .address1(instructorCreateRequest.address1())
                .address2(instructorCreateRequest.address2())
                .password(instructorCreateRequest.password())
                .familyName(instructorCreateRequest.familyName())
                .givenName(instructorCreateRequest.givenName())
                .profile(instructorCreateRequest.profile())
                .nationalIdCard(instructorCreateRequest.nationalIdCard())
                .build();

        Instructor instructor = Instructor.builder()
                .github(instructorCreateRequest.github())
                .biography(instructorCreateRequest.biography())
                .jobTitle(instructorCreateRequest.jobTitle())
                .linkedIn(instructorCreateRequest.linkedIn())
                .profile(instructorCreateRequest.profile())
                .isDeleted(false)
                .website(instructorCreateRequest.website())
                .givenName(instructorCreateRequest.givenName())
                .familyName(instructorCreateRequest.familyName())
                .nationalIdCard(instructorCreateRequest.nationalIdCard())
                .isBlocked(false)
                .user(user)
                .build();

        userRepository.save(user);
       instructorRepository.save(instructor);
    }

    @Override
    public Page<InstructorResponse> findAllInstructors(int page, int size) {

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
        Page<Instructor> instructors = instructorRepository.findAllByIsDeletedFalse(pageRequest);

        if(instructors.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "There is no instructor!"
            );
        }

        return instructors.map(instructorMapper::instructorToInstructorResponse);
    }

    @Override
    public InstructorResponse updateInstructor(String username, InstructorCreateRequest instructorCreateRequest) {

        Instructor instructor = instructorRepository.findInstructorByUserUserName(username)
                .orElseThrow(() -> new RuntimeException(
                        "Instructor not found with username: " + username));

        Instructor updatedInstructor = instructorMapper.instructorCreateRequestToInstructor(instructorCreateRequest, userRepository);
        updatedInstructor.setId(instructor.getId());
        updatedInstructor = instructorRepository.save(updatedInstructor);

        return instructorMapper.instructorToInstructorResponse(updatedInstructor);
    }

    @Override
    public InstructorResponse findInstructorByUsername(String username) {

        Instructor instructor = instructorRepository.findInstructorByUserUserName(username)
                .orElseThrow(() -> new RuntimeException(
                        "Instructor not found with username: " + username));

        return instructorMapper.instructorToInstructorResponse(instructor);
    }
}
