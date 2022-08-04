package com.canusta.DayOff.Service;

import com.canusta.DayOff.Enums.UserType;
import com.canusta.DayOff.Model.User;
import com.canusta.DayOff.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User getByEmail(String mail) {
        return userRepository.findByMail(mail);
    }

    public User addPersonel(User user) {
        try {
            user.setUserType(UserType.PERSONEL);
            User savedUser = userRepository.save(user);
            return savedUser;

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public boolean getByEmailAndSifre(String email, String sifre) {
        try {
            User user =userRepository.findByMailAndPassword(email, sifre);
            return user != null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }
}
