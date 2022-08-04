package com.canusta.DayOff.Controller;


import com.canusta.DayOff.Enums.UserType;
import com.canusta.DayOff.Model.DayOff;
import com.canusta.DayOff.Model.User;
import com.canusta.DayOff.Service.DayOffService;
import com.canusta.DayOff.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@RestController
@RequestMapping("/personel")
public class PersonelController {
    @Autowired
    private DayOffService dayOffService;
    private UserService userService;

    @PostMapping("/addHoliday")
    public HashMap<String, Object> holiday(@RequestBody DayOff dayOff, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("user");

        HashMap<String, Object> map = new HashMap<>();

        if (object != null) {
            User user = (User) object;

            if (user.getUserType() == UserType.PERSONEL) {
                dayOff.setPersonel(user);
                DayOff savedDayOff = dayOffService.save(dayOff);
                if (savedDayOff != null){
                    map.put("success", true);
                    map.put("data", savedDayOff);
                    map.put("message", "Kayıt Başarılı");
                }else {
                    map.put("success", false);
                    map.put("message", "Kayıt oluşturulurken bir hata oluştu.");

                }

            }
        }
        map.put("success", false);
        map.put("message", "Giriş yapınız");
        return map;
    }

    @PostMapping(value = "/addPersonel")
    public HashMap<String, Object> addPersonel(@RequestBody User usr){
        HashMap<String, Object> map = new HashMap<>();

        User user = userService.addPersonel(usr);

        map.put("success", user != null);
        map.put("data", user);
        map.put("message", user != null ? "Kayıt Başarılı" : "Kayıt sırasında bir hata meydana geldi");
        return map;
    }

}
