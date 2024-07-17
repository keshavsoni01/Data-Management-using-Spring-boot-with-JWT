package com.DataManagement.entity.charts;

import com.DataManagement.service.Colors;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "funnelchart")
public class FunnelChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "funnel_title")
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
