package com.canusta.DayOff.Controller;


import com.canusta.DayOff.Model.User;
import com.canusta.DayOff.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@RestController
public class MainController {


    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestParam String mail, @RequestParam String password, HttpServletRequest request) {
        boolean isExist = userService.getByEmailAndSifre(mail, password);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", isExist);

        if (isExist) {
            User user = userService.getByEmail(mail);

            map.put("message", "Giriş başarılı");
            map.put("data", user);

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } else {
            map.put("message", "Kullanıcı adı veya şifre yanlış");
        }

        return map;
    }

    @PostMapping("/logout")
    public HashMap<String, Object> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        HashMap<String, Object> map = new HashMap<>();

        Object object = session.getAttribute("user");
        if (object != null){
            session.removeAttribute("user");
            map.put("success", true);
            map.put("message", "Çıkış yapıldı");
        }else {
            map.put("success", false);
            map.put("message", "Önce giriş yapmalısınız");
        }

        return map;
    }

}
