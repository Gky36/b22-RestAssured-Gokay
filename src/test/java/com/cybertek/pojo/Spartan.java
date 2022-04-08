package com.cybertek.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
{
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
}
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = "id",allowSetters = true)
public class Spartan
{
    // getter and setter
    // toString()
    private int id;
    private String name;
    private String gender;
    private long phone;


}
