package com.DataManagement.entity.graphs;

import com.DataManagement.service.Colors;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Date;

//      When to use a scatter plot
//      -Show relationships between two variables.
//      -You have two variables of data that complement each other.

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "scatterplot")
public class ScatterPlotGraph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "scatter_title")
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

