package com.mine.dojo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
// @Accessors
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_sequence")
    //@SequenceGenerator(name="user_sequence", sequenceName = "user_seq", allocationSize=1)
    private Long id;
    private String name;
    private String password;
}
