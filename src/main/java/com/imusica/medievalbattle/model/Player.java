package com.imusica.medievalbattle.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Player {
    @Id
    @Getter@Setter
    private String id;
    @Getter@Setter
    @Indexed(unique = true)
    private String name;

    @Getter@Setter
    private int xp = 0;
}
