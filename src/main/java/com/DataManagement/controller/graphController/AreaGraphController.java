package com.DataManagement.controller.graphController;

import com.DataManagement.dto.chartDTO.BubbleChartDTO;
import com.DataManagement.dto.graphDTO.AreaGraphDTO;
import com.DataManagement.service.graphService.AreaGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AreaGraphController {
    @Autowired
    private AreaGraphService areaGraphService;

    // get all graph api
    @GetMapping("/areaGraph")
    public ResponseEntity<List<AreaGraphDTO>> getGraphs(){
        return ResponseEntity.ok(areaGraphService.getGraphs());
    }

    @GetMapping("/areaGraph/by-id/{id}")
    public ResponseEntity<AreaGraphDTO> getGraphById(@PathVariable int id) {
        return ResponseEntity.ok(areaGraphService.getGraphById(id));
    }

    @GetMapping("/areaGraph/by-title/{title}")
    public ResponseEntity<AreaGraphDTO> getGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(areaGraphService.getGraphByTitle(title));
    }

    @DeleteMapping("/areaGraph/by-id/{id}")
    public ResponseEntity<AreaGraphDTO> deleteGraphById(@PathVariable int id) {
        return ResponseEntity.ok(areaGraphService.deleteGraphById(id));
    }

    @DeleteMapping("/areaGraph/by-title/{title}")
    public ResponseEntity<AreaGraphDTO> deleteGraphByTitle(@PathVariable String title) {
        return ResponseEntity.ok(areaGraphService.deleteGraphByTitle(title));
    }

    @PostMapping("/v1/create-graph/areaGraph")
    public ResponseEntity<AreaGraphDTO> createGraph(@RequestBody AreaGraphDTO areaGraphDTO){
        return ResponseEntity.ok(areaGraphService.createGraph(areaGraphDTO));
    }
    @PutMapping("/v1/areaGraph/by-id/{id}")
    public ResponseEntity<AreaGraphDTO> updateChart(@PathVariable int id, @RequestBody AreaGraphDTO areaGraphDTO){
        return ResponseEntity.ok(areaGraphService.updateGraph(id, areaGraphDTO));
    }
}
