package co.istad.elearningapi.init;


import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.domain.Authority;
import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.domain.Country;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.authority.AuthorityRepository;
import co.istad.elearningapi.features.city.CityRepository;
import co.istad.elearningapi.features.country.CountryRepository;
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
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

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

    @PostConstruct
    void initCountryAndCities(){

        if(countryRepository.count() < 1 && cityRepository.count() < 1){
            Country cambodia = Country.builder()
                    .flag("https://w7.pngwing.com/pngs/930/954/png-transparent-flag-of-cambodia-national-flag-graphy-flag-thumbnail.png")
                    .iso("KHM")
                    .name("CAMBODIA")
                    .numCode(884)
                    .niceName("KHMER")
                    .phoneCode(855)
                    .build();
            countryRepository.save(cambodia);


            Country cam = countryRepository.findByName("CAMBODIA");

            List<City> cambodiaCities = new ArrayList<>();
            City pp = City.builder()
                    .name("PHNOM PENH")
                    .country(cam)
                    .build();

            City shv = City.builder()
                    .name("SIHANOUK PROVINCE")
                    .country(cam)
                    .build();

            City sr = City.builder()
                    .name("SIEM REAP")
                    .country(cam)
                    .build();

            cambodiaCities.add(pp);
            cambodiaCities.add(shv);
            cambodiaCities.add(sr);

            cityRepository.save(pp);
            cityRepository.save(shv);
            cityRepository.save(sr);


            Country singapore = Country.builder()
                    .flag("https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Flag_of_Singapore.svg/800px-Flag_of_Singapore.svg.png?20211012105253")
                    .iso("SGP")
                    .name("SINGAPORE")
                    .niceName("SGP")
                    .phoneCode(65)
                    .numCode(655)
                    .build();
            countryRepository.save(singapore);

            Country sgp = countryRepository.findByName("SINGAPORE");
            List<City> singaporeCities = new ArrayList<>();

            City kg = City.builder()
                    .name("KAMPONG GLAM")
                    .country(sgp)
                    .build();

            City yishun = City.builder()
                    .name("Yishun")
                    .country(sgp)
                    .build();

            singaporeCities.add(kg);
            singaporeCities.add(yishun);



            cityRepository.save(yishun);
            cityRepository.save(kg);

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
