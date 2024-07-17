package com.DataManagement.controller.graphController;

import com.DataManagement.dto.graphDTO.HistogramGraphDTO;
import com.DataManagement.service.graphService.HistogramGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HistogramGraphController {
    @Autowired
    private HistogramGraphService histogramGraphService;
    // get all graph api
    @GetMapping("/histoGraph")
    public ResponseEntity<List<HistogramGraphDTO>> getGraphs(){
        return ResponseEntity.ok(histogramGraphService.getGraphs());
    }

    @GetMapping("/histoGraph/by-id/{id}")
    public ResponseEntity<HistogramGraphDTO> getGraphById(@PathVariable int id) {
        return ResponseEntity.ok(histogramGraphService.getGraphById(id));
    }

    @GetMapping("/histoGraph/by-title/{title}")
    public ResponseEntity<HistogramGraphDTO> getGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(histogramGraphService.getGraphByTitle(title));
    }

    @DeleteMapping("/histoGraph/by-id/{id}")
    public ResponseEntity<HistogramGraphDTO> deleteGraphById(@PathVariable int id) {
        return ResponseEntity.ok(histogramGraphService.deleteGraphById(id));
    }

    @DeleteMapping("/histoGraph/by-title/{title}")
    public ResponseEntity<HistogramGraphDTO> deleteGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(histogramGraphService.deleteGraphByTitle(title));
    }

    @PostMapping("/v1/create-graph/histoGraph")
    public ResponseEntity<HistogramGraphDTO> createGraph(@RequestBody HistogramGraphDTO histogramGraphDTO){
        return ResponseEntity.ok(histogramGraphService.createGraph(histogramGraphDTO));
    }
    @PutMapping("/v1/histoGraph/by-id/{id}")
    public ResponseEntity<HistogramGraphDTO> updateChart(@PathVariable int id, @RequestBody HistogramGraphDTO histogramGraphDTO){
        return ResponseEntity.ok(histogramGraphService.updateGraph(id, histogramGraphDTO));
    }
}
