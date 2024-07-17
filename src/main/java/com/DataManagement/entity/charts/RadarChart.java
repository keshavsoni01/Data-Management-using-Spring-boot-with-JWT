package com.DataManagement.entity.charts;


import com.DataManagement.service.Colors;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Date;

//  When to use radar charts
//  -When comparing multiple quantitative variables.
//  -If you need to analyze performance in several categories simultaneously.
//  -When you want to visualize multidimensional data.

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "radarChart")
public class RadarChart{
    @Id //
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "radar_title")
    private String title;
    private int data;
    private String labels;
    private Colors color;
    private Date lastUpdate;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    // getter setter for is_deleted
    public boolean isDeleted() {
        return this.isDeleted;
    }
    public void setDeleted() {
        this.isDeleted = true;
    }


}
