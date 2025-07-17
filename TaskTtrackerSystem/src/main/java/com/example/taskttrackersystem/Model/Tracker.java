package com.example.taskttrackersystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tracker {
    private String id;
    private String title;
    private String description;
    private String status;

}
