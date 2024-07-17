package com.DataManagement.dto.chartDTO;


import com.DataManagement.service.Colors;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BubbleChartDTO {
    private int id;
    private String title;
    private int data;
    private String labels;
    private Colors color;
    private Date lastUpdate;
}
