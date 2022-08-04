package com.canusta.DayOff.Repository;

import com.canusta.DayOff.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByMail(String mail);

    User findByMailAndPassword(String mail, String password);
}
