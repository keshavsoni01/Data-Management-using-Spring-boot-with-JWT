package com.DataManagement.entity.graphs;

import com.DataManagement.service.Colors;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

//  When to use pie charts
//  -it is used to summarize discrete or continuous data that are measured on an interval scale

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "histogram")
public class HistogramGraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "histogram_title")
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