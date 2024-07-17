package com.DataManagement.controller.chartController;


import com.DataManagement.dto.chartDTO.BubbleChartDTO;
import com.DataManagement.dto.chartDTO.RadarChartDTO;
import com.DataManagement.service.chartService.RadarChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RadarChartContoller {
    @Autowired
    private RadarChartService radarChartService;

    // get all charts
    @GetMapping("/radarChart")
    public ResponseEntity<List<RadarChartDTO>> getCharts() {
        return ResponseEntity.ok(radarChartService.getCharts());
    }
    @GetMapping("/radarChart/by-id/{id}")
    public ResponseEntity<RadarChartDTO> getChartById(@PathVariable int id) {
        return ResponseEntity.ok(radarChartService.getChartById(id));
    }

    @GetMapping("/radarChart/by-title/{title}")
    public ResponseEntity<RadarChartDTO> getChartByTitle(@PathVariable String title) {
        return ResponseEntity.ok(radarChartService.getChartByTitle(title));
    }

    @DeleteMapping("/radarChart/by-id/{id}")
    public ResponseEntity<RadarChartDTO> deleteChartById(@PathVariable int id) {
        return ResponseEntity.ok(radarChartService.deleteChartById(id));
    }

    @DeleteMapping("/radarChart/by-title/{title}")
    public ResponseEntity<RadarChartDTO> deleteChartByTitle(@PathVariable String title) {
        return ResponseEntity.ok(radarChartService.deleteChartByTitle(title));
    }

    @PostMapping("/v1/create-chart/radarChart")
    public ResponseEntity<RadarChartDTO> createchart(@RequestBody RadarChartDTO radarChartDTO){
        return ResponseEntity.ok(radarChartService.createChart(radarChartDTO));
    }
    @PutMapping("/v1/radarChart/by-id/{id}")
    public ResponseEntity<RadarChartDTO> updateChart(@PathVariable int id, @RequestBody RadarChartDTO updatedChartDTO){
        return ResponseEntity.ok(radarChartService.updateChart(id, updatedChartDTO));
    }
}
