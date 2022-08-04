package com.canusta.DayOff;

import com.canusta.DayOff.Enums.UserType;
import com.canusta.DayOff.Model.DayOff;
import com.canusta.DayOff.Model.User;
import com.canusta.DayOff.Repository.DayOffRepository;
import com.canusta.DayOff.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DayOffApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DayOffApplication.class, args);

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        DayOffRepository dayOffRepository = applicationContext.getBean(DayOffRepository.class);
        User user1 = new User();
        user1.setUserType(UserType.PERSONEL);
        userRepository.save(user1);

        DayOff dayOff1 = new DayOff();
        dayOff1.setPersonel(user1);
        dayOffRepository.save(dayOff1);
    }

}
