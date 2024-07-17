package com.DataManagement.controller.graphController;

import com.DataManagement.dto.graphDTO.AreaGraphDTO;
import com.DataManagement.dto.graphDTO.BarGraphDTO;
import com.DataManagement.entity.graphs.BarGraph;
import com.DataManagement.service.graphService.BarGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BarGraphController {
    @Autowired
    private BarGraphService barGraphService;

    // get all graph api
    @GetMapping("/barGraph")
    public ResponseEntity<List<BarGraphDTO>> getGraphs(){
        return ResponseEntity.ok(barGraphService.getGraphs());
    }

    @GetMapping("/barGraph/by-id/{id}")
    public ResponseEntity<BarGraphDTO> getGraphById(@PathVariable int id) {
        return ResponseEntity.ok(barGraphService.getGraphById(id));
    }

    @GetMapping("/barGraph/by-title/{title}")
    public ResponseEntity<BarGraphDTO> getGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(barGraphService.getGraphByTitle(title));
    }

    @DeleteMapping("/barGraph/by-id/{id}")
    public ResponseEntity<BarGraphDTO> deleteGraphById(@PathVariable int id) {
        return ResponseEntity.ok(barGraphService.deleteGraphById(id));
    }

    @DeleteMapping("/barGraph/by-title/{title}")
    public ResponseEntity<BarGraphDTO> deleteGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(barGraphService.deleteGraphByTitle(title));
    }

    @PostMapping("/v1/create-graph/barGraph")
    public ResponseEntity<BarGraphDTO> createGraph(@RequestBody BarGraphDTO barGraphDTO){
        return ResponseEntity.ok(barGraphService.createGraph(barGraphDTO));
    }
    @PutMapping("/v1/barGraph/by-id/{id}")
    public ResponseEntity<BarGraphDTO> updateChart(@PathVariable int id, @RequestBody BarGraphDTO barGraphDTO){
        return ResponseEntity.ok(barGraphService.updateGraph(id, barGraphDTO));
    }
}
