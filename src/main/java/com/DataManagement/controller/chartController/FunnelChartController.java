package com.DataManagement.controller.chartController;

import com.DataManagement.dto.chartDTO.BubbleChartDTO;
import com.DataManagement.dto.chartDTO.FunnelChartDTO;
import com.DataManagement.entity.charts.FunnelChart;
import com.DataManagement.service.chartService.FunnelChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FunnelChartController {
    @Autowired
    private FunnelChartService funnelChartService;

    // get all charts
    @GetMapping("/funnelChart")
    public ResponseEntity<List<FunnelChartDTO>> getCharts() {
        return ResponseEntity.ok(funnelChartService.getCharts());
    }
    @GetMapping("/funnelChart/by-id/{id}")
    public ResponseEntity<FunnelChartDTO> getChartById(@PathVariable int id) {
        return ResponseEntity.ok(funnelChartService.getChartById(id));
    }

    @GetMapping("/funnelChart/by-title/{title}")
    public ResponseEntity<FunnelChartDTO> getChartByTitle(@PathVariable String title) {
        return ResponseEntity.ok(funnelChartService.getChartByTitle(title));
    }

    @DeleteMapping("/funnelChart/by-id/{id}")
    public ResponseEntity<FunnelChartDTO> deleteChartById(@PathVariable int id) {
        return ResponseEntity.ok(funnelChartService.deleteChartById(id));
    }

    @DeleteMapping("/funnelChart/by-title/{title}")
    public ResponseEntity<FunnelChartDTO> deleteChartByTitle(@PathVariable String title) {
        return ResponseEntity.ok(funnelChartService.deleteChartByTitle(title));
    }

    @PostMapping("/v1/create-chart/funnelChart")
    public ResponseEntity<FunnelChartDTO> createchart(@RequestBody FunnelChartDTO funnelChartDTO){
        return ResponseEntity.ok(funnelChartService.createChart(funnelChartDTO));
    }
    @PutMapping("/v1/funnelChart/by-id/{id}")
    public ResponseEntity<FunnelChartDTO> updateChart(@PathVariable int id, @RequestBody FunnelChartDTO updatedChartDTO){
        return ResponseEntity.ok(funnelChartService.updateChart(id, updatedChartDTO));
    }

}
