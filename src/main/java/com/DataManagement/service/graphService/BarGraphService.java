package com.DataManagement.service.graphService;

import com.DataManagement.dto.graphDTO.AreaGraphDTO;
import com.DataManagement.dto.graphDTO.BarGraphDTO;
import com.DataManagement.entity.graphs.AreaGraph;
import com.DataManagement.entity.graphs.BarGraph;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.graphRepo.BarGraphRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarGraphService {
   @Autowired
   private BarGraphRepo barGraphRepo;
   @Autowired
   private ModelMapper modelMapper;

    // Get all bar Graphs details
    public List<BarGraphDTO> getGraphs(){
        return barGraphRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get bar Graph by Id --------------------------
    public BarGraphDTO getGraphById(int id) throws BadRequestException {
        BarGraph graph = barGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id "+id+" not found."));
        return mapToDto(graph);
    }

    // Get bar Graph by Title ----------------------
    public BarGraphDTO getGraphByTitle(String title) throws BadRequestException{
       BarGraph graph = barGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name "+title+" not found."));
       return  mapToDto(graph);
    }

    // Delete a bar graph by Title name
    public BarGraphDTO deleteGraphByTitle(String title) throws BadRequestException{
       BarGraph graph = barGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name " + title + " not found."));
        graph.setDeleted();
        barGraphRepo.save(graph);
       return mapToDto(graph);
    }

    // Delete a bar Graph by id
    public BarGraphDTO deleteGraphById(int id) throws BadRequestException{
        BarGraph graph = barGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id " + id + " not found."));
        graph.setDeleted();
        barGraphRepo.save(graph);
        return mapToDto(graph);
    }

    // Create new bar graph
    public BarGraphDTO createGraph(BarGraphDTO barGraphDTO ) {
        BarGraph barGraph = mapToEntity(barGraphDTO);
        barGraph.setLastUpdate(new Date());
        barGraphRepo.save(barGraph);
        return mapToDto(barGraph);
    }

    // Update existing bar graph by id
    public BarGraphDTO updateGraph(int id, BarGraphDTO barGraphDTO) throws BadRequestException {
        // Fetch the graph from the repository
        BarGraph existingGraph = barGraphRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Graph not found with ID: " + id));

        // Update the graph properties with new values
        existingGraph.setTitle(barGraphDTO.getTitle());
        existingGraph.setXAxis(barGraphDTO.getXAxis());
        existingGraph.setYAxis(barGraphDTO.getYAxis());
        existingGraph.setXAxisLabel(barGraphDTO.getXAxisLabel());
        existingGraph.setYAxisLabel(barGraphDTO.getYAxisLabel());
        existingGraph.setXAxisInterval(barGraphDTO.getXAxisInterval());
        existingGraph.setYAxisInterval(barGraphDTO.getYAxisInterval());
        existingGraph.setColor(barGraphDTO.getColor());
        existingGraph.setLastUpdate(new Date());

        // Save the updated graph back to the repository
        BarGraph updatedGraph = barGraphRepo.save(existingGraph);

        // Map the updated entity to DTO and return
        return mapToDto(updatedGraph);
    }
    public BarGraphDTO mapToDto(BarGraph barGraph){
        BarGraphDTO barGraphDTO = this.modelMapper.map(barGraph, BarGraphDTO.class);
        return barGraphDTO;
    }
    public BarGraph mapToEntity(BarGraphDTO barGraphDTO){
        BarGraph barGraph = this.modelMapper.map(barGraphDTO, BarGraph.class);
        return barGraph;
    }

}
