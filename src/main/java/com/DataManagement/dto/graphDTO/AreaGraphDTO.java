package com.DataManagement.dto.graphDTO;

import com.DataManagement.service.Colors;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AreaGraphDTO {
    private int id;
    private String title;
    private int xAxis;
    private int yAxis;
    private String xAxisLabel;
    private String yAxisLabel;
    private int xAxisInterval;
    private int yAxisInterval;
    private Colors color ;
    private Date lastUpdated;
}
