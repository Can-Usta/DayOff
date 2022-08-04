package com.canusta.DayOff.Service;

import com.canusta.DayOff.Enums.DayOffStatus;
import com.canusta.DayOff.Model.DayOff;
import com.canusta.DayOff.Repository.DayOffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayOffService {

    @Autowired
    private DayOffRepository dayOffRepository;

    public DayOff save(DayOff dayOff) {
        try {
            DayOff savedDayOff = dayOffRepository.save(dayOff);
            return savedDayOff;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public List<DayOff> getByPersonelId(Long id) {
        return dayOffRepository.getByPersonel_UserId(id);
    }

    public List<DayOff> getAll() {
        return dayOffRepository.findAll();
    }

    public DayOff findByID(Long dayOffID) {
        try {
            return dayOffRepository.findById(dayOffID).get();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public DayOff updateStatus(Long dayOffID, DayOffStatus status) {
        try {
            DayOff dayOff = dayOffRepository.findById(dayOffID).get();
            dayOff.setStatus(status);
            DayOff savedDayOff = dayOffRepository.save(dayOff);
            return savedDayOff;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
