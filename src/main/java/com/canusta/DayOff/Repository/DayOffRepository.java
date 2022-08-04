package com.canusta.DayOff.Repository;

import com.canusta.DayOff.Model.DayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayOffRepository extends JpaRepository <DayOff,Long>{
    List<DayOff> getByPersonel_UserId(Long userID);
}
