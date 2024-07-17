package com.DataManagement.controller.graphController;

import com.DataManagement.dto.graphDTO.AreaGraphDTO;
import com.DataManagement.dto.graphDTO.LineGraphDTO;
import com.DataManagement.entity.graphs.LineGraph;
import com.DataManagement.service.graphService.LineGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LineGraphController {
    @Autowired
    private LineGraphService lineGraphService;

    // get all graph api
    @GetMapping("/lineGraphs")
    public ResponseEntity<List<LineGraphDTO>> getGraphs(){
        return ResponseEntity.ok(lineGraphService.getGraphs());
    }

    @GetMapping("/lineGraph/by-id/{id}")
    public ResponseEntity<LineGraphDTO> getGraphById(@PathVariable int id) {
        return ResponseEntity.ok(lineGraphService.getGraphById(id));
    }

    @GetMapping("/lineGraphs/by-title/{title}")
    public ResponseEntity<LineGraphDTO> getGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(lineGraphService.getGraphByTitle(title));
    }

    @DeleteMapping("/lineGraphs/by-id/{id}")
    public ResponseEntity<LineGraphDTO> deleteGraphById(@PathVariable int id) {
        return ResponseEntity.ok(lineGraphService.deleteGraphById(id));
    }

    @DeleteMapping("/lineGraphs/by-title/{title}")
    public ResponseEntity<LineGraphDTO> deleteGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(lineGraphService.deleteGraphByTitle(title));
    }

    @PostMapping("/v1/create-graph/lineGraphs")
    public ResponseEntity<LineGraphDTO> createGraph(@RequestBody LineGraphDTO lineGraphDTO){
        return ResponseEntity.ok(lineGraphService.createGraph(lineGraphDTO));
    }
    @PutMapping("/v1/lineGraph/by-id/{id}")
    public ResponseEntity<LineGraphDTO> updateChart(@PathVariable int id, @RequestBody LineGraphDTO lineGraphDTO){
        return ResponseEntity.ok(lineGraphService.updateGraph(id,lineGraphDTO));
    }
}
