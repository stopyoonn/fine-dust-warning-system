package com.example.finedust.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "warning_entity")
public class WarningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String stationName;

    @Column(nullable = false)
    private LocalDateTime dateTime;

//    /* Waring:warningLevel = N:1*/
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "warninglevel_id", referencedColumnName = "id")
//    private WarningLevelEntity warningLevelEntity;

}
