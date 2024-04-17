package co.istad.elearningapi.init;


import co.istad.elearningapi.domain.*;
import co.istad.elearningapi.features.city.CityRepository;
import co.istad.elearningapi.features.country.CountryRepository;
import co.istad.elearningapi.features.role.RoleRepository;
import co.istad.elearningapi.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
@Slf4j
public class DataInit {

    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    @PostConstruct
    void initRole() {
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

//    @PostConstruct
//    void initCountryAndCities() {
//
//        if (countryRepository.count() < 1 && cityRepository.count() < 1) {
//            Country cambodia = Country.builder()
//                    .flag("https://w7.pngwing.com/pngs/930/954/png-transparent-flag-of-cambodia-national-flag-graphy-flag-thumbnail.png")
//                    .iso("KHM")
//                    .name("CAMBODIA")
//                    .numCode(884)
//                    .niceName("KHMER")
//                    .phoneCode(855)
//                    .build();
//            countryRepository.save(cambodia);
//
//
//            Country cam = countryRepository.findByName("CAMBODIA")
//                    .orElseThrow();
//
//            List<City> cambodiaCities = new ArrayList<>();
//            City pp = City.builder()
//                    .name("PHNOM PENH")
//                    .country(cam)
//                    .build();
//
//            City shv = City.builder()
//                    .name("SIHANOUK PROVINCE")
//                    .country(cam)
//                    .build();
//
//            City sr = City.builder()
//                    .name("SIEM REAP")
//                    .country(cam)
//                    .build();
//
//            cambodiaCities.add(pp);
//            cambodiaCities.add(shv);
//            cambodiaCities.add(sr);
//
//            cityRepository.save(pp);
//            cityRepository.save(shv);
//            cityRepository.save(sr);
//
//
//            Country singapore = Country.builder()
//                    .flag("https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Flag_of_Singapore.svg/800px-Flag_of_Singapore.svg.png?20211012105253")
//                    .iso("SGP")
//                    .name("SINGAPORE")
//                    .niceName("SGP")
//                    .phoneCode(65)
//                    .numCode(655)
//                    .build();
//            countryRepository.save(singapore);
//
//            Country sgp = countryRepository.findByName("SINGAPORE")
//                    .orElseThrow(()-> new RuntimeException("can not init country"));;
//            List<City> singaporeCities = new ArrayList<>();
//
//            City kg = City.builder()
//                    .name("KAMPONG GLAM")
//                    .country(sgp)
//                    .build();
//
//            City yishun = City.builder()
//                    .name("Yishun")
//                    .country(sgp)
//                    .build();
//
//            singaporeCities.add(kg);
//            singaporeCities.add(yishun);
//
//
//            cityRepository.save(yishun);
//            cityRepository.save(kg);
//
//        }
//    }

//    @PostConstruct
//    void initAdmin(){
//
//        if(userRepository.count() < 1){
//            LocalDate dob = LocalDate.of(1999,7,1);
//            Country cam = countryRepository.findByName("CAMBODIA")
//                    .orElseThrow();
//            City city = cityRepository.findByName("PHNOM PENH");
//            Role role = roleRepository.findByName("ADMIN");
//
//            log.info("city {}",city);
//            log.info("country {}",cam);
//
//            User admin = User.builder()
//                    .uuid(UUID.randomUUID().toString())
//                    .isDeleted(false)
//                    .isVerified(true)
//                    .password("tra1122")
//                    .profile("AVATAR.PNG")
//                    .dob(dob)
//                    .userName("pheaktra")
//                    .email("pheaktra@gmail.com")
//                    .familyName("yith")
//                    .givenName("sopheaktra")
//                    .address1("Phnom Penh")
//                    .gender("Male")
//                    .country(cam)
//                    .city(city)
//                    .roles(List.of(role))
//                    .nationalIdCard("12311461")
//                    .phoneNumber("0967174832")
//                    .build();
//
//            userRepository.save(admin);
//        }
//    }

    private List<Authority> createUserAuthorities() {
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
        return List.of(userRead, userWrite, userDelete, userUpdate, progressRead, eLearningRead);
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
