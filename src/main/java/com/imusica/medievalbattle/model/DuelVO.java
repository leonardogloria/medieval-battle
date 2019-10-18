package com.imusica.medievalbattle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DuelVO {
    @Getter@Setter
    private UUID uuid;
    @Getter@Setter
    private Character winner;
    @Getter@Setter
    private Integer xp;
    @Getter@Setter
    private Integer rounds;

    @Getter@Setter
    private String status;

}
