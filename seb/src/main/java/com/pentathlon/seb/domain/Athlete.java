package com.pentathlon.seb.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {

    private String name;

    private int fencingVictories;

    private Float swimmingTime;

    private int ridingKnockingDown;

    private int ridingRefusal;

    private int ridingDisobedienceLeading;

    private int shootingTargetScore;

    private Float runTime;

    private int totalScore;
}
