package com.DataManagement.controller.chartController;

import com.DataManagement.dto.chartDTO.BubbleChartDTO;
import com.DataManagement.dto.chartDTO.PieChartDTO;
import com.DataManagement.entity.charts.PieChart;
import com.DataManagement.service.chartService.PieChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PieChartController {
    @Autowired
    private PieChartService pieChartService;

    // get all charts
    @GetMapping("/pieChart")
    public ResponseEntity<List<PieChartDTO>> getCharts() {
        return ResponseEntity.ok(pieChartService.getCharts());
    }
    @GetMapping("/pieChart/by-id/{id}")
    public ResponseEntity<PieChartDTO> getChartById(@PathVariable int id) {
        return ResponseEntity.ok(pieChartService.getChartById(id));
    }

    @GetMapping("/pieChart/by-title/{title}")
    public ResponseEntity<PieChartDTO> getChartByTitle(@PathVariable String title) {
        return ResponseEntity.ok(pieChartService.getChartByTitle(title));
    }

    @DeleteMapping("/pieChart/by-id/{id}")
    public ResponseEntity<PieChartDTO> deleteChartById(@PathVariable int id) {
        return ResponseEntity.ok(pieChartService.deleteChartById(id));
    }

    @DeleteMapping("/pieChart/by-title/{title}")
    public ResponseEntity<PieChartDTO> deleteChartByTitle(@PathVariable String title) {
        return ResponseEntity.ok(pieChartService.deleteChartByTitle(title));
    }

    @PostMapping("/v1/create-chart/pieChart")
    public ResponseEntity<PieChartDTO> createchart(@RequestBody PieChartDTO pieChartDTO){
        return ResponseEntity.ok(pieChartService.createChart(pieChartDTO));
    }
    @PutMapping("/v1/pieChart/by-id/{id}")
    public ResponseEntity<PieChartDTO> updateChart(@PathVariable int id, @RequestBody PieChartDTO updatedChartDTO){
        return ResponseEntity.ok(pieChartService.updateChart(id, updatedChartDTO));
    }
}
