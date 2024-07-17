package com.DataManagement.controller.graphController;

import com.DataManagement.dto.graphDTO.AreaGraphDTO;
import com.DataManagement.dto.graphDTO.ScatterPlotGraphDTO;
import com.DataManagement.entity.graphs.ScatterPlotGraph;
import com.DataManagement.service.graphService.ScatterGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScatterGraphController {
    @Autowired
    private ScatterGraphService scatterGraphService;

    // get all graph api
    @GetMapping("/scatterGraph")
    public ResponseEntity<List<ScatterPlotGraphDTO>> getGraphs(){
        return ResponseEntity.ok(scatterGraphService.getGraphs());
    }

    @GetMapping("/scatterGraph/by-id/{id}")
    public ResponseEntity<ScatterPlotGraphDTO> getGraphById(@PathVariable int id) {
        return ResponseEntity.ok(scatterGraphService.getGraphById(id));
    }

    @GetMapping("/scatterGraph/by-title/{title}")
    public ResponseEntity<ScatterPlotGraphDTO> getGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(scatterGraphService.getGraphByTitle(title));
    }

    @DeleteMapping("/scatterGraph/by-id/{id}")
    public ResponseEntity<ScatterPlotGraphDTO> deleteGraphById(@PathVariable int id) {
        return ResponseEntity.ok(scatterGraphService.deleteGraphById(id));
    }

    @DeleteMapping("/scatterGraph/by-title/{title}")
    public ResponseEntity<ScatterPlotGraphDTO> deleteGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(scatterGraphService.deleteGraphByTitle(title));
    }

    @PostMapping("/v1/create-graph/scatterGraph")
    public ResponseEntity<ScatterPlotGraphDTO> createGraph(@RequestBody ScatterPlotGraphDTO scatterPlotGraphDTO){
        return ResponseEntity.ok(scatterGraphService.createGraph(scatterPlotGraphDTO));
    }
    @PutMapping("/v1/scatterGraph/by-id/{id}")
    public ResponseEntity<ScatterPlotGraphDTO> updateChart(@PathVariable int id, @RequestBody ScatterPlotGraphDTO scatterPlotGraphDTO){
        return ResponseEntity.ok(scatterGraphService.updateGraph(id, scatterPlotGraphDTO));
    }
}
