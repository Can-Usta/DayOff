package com.canusta.DayOff.Model;

import com.canusta.DayOff.Enums.DayOffStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
public class DayOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @Enumerated(EnumType.STRING)
    private DayOffStatus status;
    private Integer dayOffNumber;

    @ManyToOne
    @JoinColumn(name = "user_ıd")
    private User personel;
}
