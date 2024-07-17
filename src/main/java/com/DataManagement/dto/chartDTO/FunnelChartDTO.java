package com.DataManagement.dto.chartDTO;

import com.DataManagement.service.Colors;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class FunnelChartDTO {
    private int id;
    private String title;
    private int data;
    private String labels;
    private Colors color;
    private Date lastUpdate;
}
