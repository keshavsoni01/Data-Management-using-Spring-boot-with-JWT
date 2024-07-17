package com.DataManagement.entity.graphs;

import com.DataManagement.service.Colors;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Date;

//  When to use column charts
//  -Display comparison between categories or things (qualitative data).
//  -Show the situation at one point in time using various data points.
//  -Share relatively large differences in your numeric data values.

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "columngraph")
public class ColumnGraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "column_title")
    private String title;
    private int xAxis;
    private int yAxis;
    private String xAxisLabel;
    private String yAxisLabel;
    private int xAxisInterval;
    private int yAxisInterval;
    private Colors color ;
    private Date lastUpdated;
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
