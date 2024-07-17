package com.DataManagement.controller.graphController;

import com.DataManagement.dto.graphDTO.AreaGraphDTO;
import com.DataManagement.dto.graphDTO.ColumnGraphDTO;
import com.DataManagement.entity.graphs.ColumnGraph;
import com.DataManagement.service.graphService.ColumnGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ColumnGraphController {
    @Autowired
    private ColumnGraphService columnGraphService;

    // get all graph api
    @GetMapping("/columnGraph")
    public ResponseEntity<List<ColumnGraphDTO>> getGraphs(){
        return ResponseEntity.ok(columnGraphService.getGraphs());
    }

    @GetMapping("/columnGraph/by-id/{id}")
    public ResponseEntity<ColumnGraphDTO> getGraphById(@PathVariable int id) {
        return ResponseEntity.ok(columnGraphService.getGraphById(id));
    }

    @GetMapping("/columnGraph/by-title/{title}")
    public ResponseEntity<ColumnGraphDTO> getGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(columnGraphService.getGraphByTitle(title));
    }

    @DeleteMapping("/columnGraph/by-id/{id}")
    public ResponseEntity<ColumnGraphDTO> deleteGraphById(@PathVariable int id) {
        return ResponseEntity.ok(columnGraphService.deleteGraphById(id));
    }

    @DeleteMapping("/columnGraph/by-title/{title}")
    public ResponseEntity<ColumnGraphDTO> deleteGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(columnGraphService.deleteGraphByTitle(title));
    }

    @PostMapping("/v1/create-graph/columnGraph")
    public ResponseEntity<ColumnGraphDTO> createGraph(@RequestBody ColumnGraphDTO columnGraphDTO){
        return ResponseEntity.ok(columnGraphService.createGraph(columnGraphDTO));
    }
    @PutMapping("/v1/columnGraph/by-id/{id}")
    public ResponseEntity<ColumnGraphDTO> updateChart(@PathVariable int id, @RequestBody ColumnGraphDTO columnGraphDTO){
        return ResponseEntity.ok(columnGraphService.updateGraph(id, columnGraphDTO));
    }
}
