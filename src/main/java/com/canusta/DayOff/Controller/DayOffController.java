package com.canusta.DayOff.Controller;

import com.canusta.DayOff.Enums.DayOffStatus;
import com.canusta.DayOff.Enums.UserType;
import com.canusta.DayOff.Model.DayOff;
import com.canusta.DayOff.Model.User;
import com.canusta.DayOff.Service.DayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/dayOff")
public class DayOffController {

    @Autowired
    private DayOffService dayOffService;

    @GetMapping(value = "/getByPersonel/{personelId}")
    public List<DayOff> holidaysByPersonelId(@PathVariable("personelId") Long id) {
        List<DayOff> dayOffs = new ArrayList<>();
        dayOffs = dayOffService.getByPersonelId(id);
        return dayOffs;
    }

    @GetMapping("/getAll")
    public List<DayOff> getAll(HttpServletRequest request){
        List<DayOff> dayOffs = new ArrayList<>();

        HttpSession session = request.getSession();
        Object object = session.getAttribute("user");
        if (object != null){
            User user = (User) object;
            if (user.getUserType() == UserType.ADMİN){
                return dayOffService.getAll();
            }
        }

        return dayOffs;
    }

    @GetMapping("/getByID/{id}")
    public DayOff getByID(@PathVariable("id") Long dayOffID, HttpServletRequest request){
        DayOff dayOff = new DayOff();

        HttpSession session = request.getSession();
        Object object = session.getAttribute("user");
        if (object != null){
            User user = (User) object;
            if (user.getUserType() == UserType.ADMİN){
                return dayOffService.findByID(dayOffID);
            }
        }

        return dayOff;
    }

    @PostMapping("/updateStatus/{id}")
    public HashMap<String, Object> updateStatus(@PathVariable("id") Long dayOffID, @RequestParam DayOffStatus status,
                                                HttpServletRequest request){

        HashMap<String, Object> map = new HashMap<>();

        HttpSession session = request.getSession();
        Object object = session.getAttribute("user");
        if (object != null){
            User user = (User) object;
            if (user.getUserType() == UserType.ADMİN){

                DayOff dayOff = dayOffService.updateStatus(dayOffID, status);
                map.put("success", dayOff != null);
                map.put("data", dayOff);
                map.put("message", dayOff != null ? "Güncelleme başarılı" : "Güncelleme sırasında bir hata meydana geldi.");
                return map;
            }
            map.put("message", "Sadece admin kullanıcı güncelleyebilir.");
        }
        map.put("success", false);
        map.put("message", "Giriş yapınız");

        return map;
    }
}
