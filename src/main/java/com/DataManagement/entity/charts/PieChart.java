package com.DataManagement.entity.charts;

import com.DataManagement.service.Colors;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Date;

//  When to use pie charts
//  -Illustrate part-to-whole comparisons — from business to classroom charts and graphs.
//  -Identify the smallest and largest items within data sets.
//  -Compare differences between multiple data points in a pie chart.

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "piechart") // @Table is used for change the name of table when it save in DB
public class PieChart {
    @Id // @ID means , make it primary key when hibernate create table in DB
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it will autoincrement the id value in DB
    private int id;
    @Column(name = "pie_title") // @Column is used to change name and other things in column when it saves in DB
    private String title;
    @Column(name = "pie_data")
    private int data;
    @Column(name = "pie_labels")
    private String labels;
    @Column(name = "pie_color")
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
