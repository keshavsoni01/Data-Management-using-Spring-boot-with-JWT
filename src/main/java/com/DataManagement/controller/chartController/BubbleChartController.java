package com.DataManagement.controller.chartController;

import com.DataManagement.dto.chartDTO.BubbleChartDTO;
import com.DataManagement.entity.charts.BubbleChart;
import com.DataManagement.service.chartService.BubbleChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BubbleChartController {
    @Autowired
    private BubbleChartService bubbleChartService;

    // get all charts
    @GetMapping("/bubbleChart")
    public ResponseEntity<List<BubbleChartDTO>> getCharts() {
        return ResponseEntity.ok(bubbleChartService.getCharts());
    }
    @GetMapping("/bubbleChart/by-id/{id}")
    public ResponseEntity<BubbleChartDTO> getChartById(@PathVariable int id) {
        return ResponseEntity.ok(bubbleChartService.getChartById(id));
    }
    @GetMapping("/bubbleChart/by-title/{title}")
    public ResponseEntity<BubbleChartDTO> getChartByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bubbleChartService.getChartByTitle(title));
    }

    @DeleteMapping("/bubbleChart/by-id/{id}")
    public ResponseEntity<BubbleChartDTO> deleteChartById(@PathVariable int id) {
        return ResponseEntity.ok(bubbleChartService.deleteChartById(id));
    }

    @DeleteMapping("/bubbleChart/by-title/{title}")
    public ResponseEntity<BubbleChartDTO> deleteChartByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bubbleChartService.deleteChartByTitle(title));
    }

    @PostMapping("/v1/create-chart/bubbleChart")
    public ResponseEntity<BubbleChartDTO> createchart(@RequestBody BubbleChartDTO bubbleChartDTO){
        return ResponseEntity.ok(bubbleChartService.createChart(bubbleChartDTO));
    }

    @PutMapping("/v1/bubbleChart/by-id/{id}")
    public ResponseEntity<BubbleChartDTO> updateChart(@PathVariable int id,@RequestBody BubbleChartDTO updatedChartDTO){
        return ResponseEntity.ok(bubbleChartService.updateChart(id, updatedChartDTO));
    }
}
