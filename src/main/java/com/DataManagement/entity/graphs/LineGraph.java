package com.DataManagement.entity.graphs;

import com.DataManagement.service.Colors;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Date;

//  When to use line charts
//  -Compare and present lots of data at once.
//  -Show trends or progress over time.
//  -Highlight deceleration.
//  -Present forecast data and share uncertainty in a single line chart.

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "linegraph")
public class LineGraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "line_title")
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
