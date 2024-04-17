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

    @Override
    public InstructorResponse createInstructor(InstructorCreateRequest instructorCreateRequest) {

        Country newCountry = new Country();

        if(!countryRepository.existsByName(instructorCreateRequest.country().toUpperCase())){
            newCountry = Country.builder()
                    .name(instructorCreateRequest.country().toUpperCase())
                    .cities(List.of(City.builder()
                            .country(newCountry)
                            .name(instructorCreateRequest.city().toUpperCase())
                            .build()))
                    .numCode(855)
                    .phoneCode(999)
                    .niceName("Country")
                    .phoneCode(855)
                    .iso("99-ISO")
                    .build();
        }

        Country oldCountry = countryRepository.findCountryByName(instructorCreateRequest.country().toUpperCase());


        if(!cityRepository.existsByName(instructorCreateRequest.city().toUpperCase()))  {

        }



        Role instrutorRole = roleRepository.findByName("INSTRUCTOR");

        User user = User.builder()
                .uuid(UUID.randomUUID().toString())
                .country(countryRepository.existsByName(instructorCreateRequest.country().toUpperCase()) ? newCountry : oldCountry)
                .city(City.builder()
                        .name(instructorCreateRequest.city())
                        .build())
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

        Instructor savedInstructor = instructorRepository.save(instructor);
        return instructorMapper.instructorToInstructorResponse(savedInstructor);
    }

    @Override
    public List<InstructorResponse> findAllInstructors(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Instructor> instructorPage = instructorRepository.findAll(pageable);

        return instructorPage.stream()
                .map(instructorMapper::instructorToInstructorResponse)
                .collect(Collectors.toList());
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
