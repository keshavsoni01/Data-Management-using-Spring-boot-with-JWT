package com.DataManagement.service.graphService;

import com.DataManagement.dto.graphDTO.LineGraphDTO;
import com.DataManagement.dto.graphDTO.ScatterPlotGraphDTO;
import com.DataManagement.entity.graphs.LineGraph;
import com.DataManagement.entity.graphs.ScatterPlotGraph;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.graphRepo.ScatterPlotGraphRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScatterGraphService {
   @Autowired
   private ScatterPlotGraphRepo scatterPlotGraphRepo;
   @Autowired
   private ModelMapper modelMapper;

    // Get all scatter Graphs details
    public List<ScatterPlotGraphDTO> getGraphs(){
        return scatterPlotGraphRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get scatter Graph by Id --------------------------
    public ScatterPlotGraphDTO getGraphById(int id) throws BadRequestException {
        ScatterPlotGraph scatterPlotGraph = scatterPlotGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id "+id+" not found."));
        return mapToDto(scatterPlotGraph);
    }

    // Get scatter Graph by Title ----------------------
    public ScatterPlotGraphDTO getGraphByTitle(String title) throws BadRequestException{
       ScatterPlotGraph scatterPlotGraph = scatterPlotGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name "+title+" not found."));
       return mapToDto(scatterPlotGraph);
    }

    // Delete a scatter graph by Title name
    public ScatterPlotGraphDTO deleteGraphByTitle(String title) throws BadRequestException{
       ScatterPlotGraph scatterPlotGraph = scatterPlotGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name " + title + " not found."));
       scatterPlotGraph.setDeleted();
       scatterPlotGraphRepo.save(scatterPlotGraph);
       return mapToDto(scatterPlotGraph);
    }

    // Delete a scatter Graph by id
    public ScatterPlotGraphDTO deleteGraphById(int id) throws BadRequestException{
        ScatterPlotGraph scatterPlotGraph = scatterPlotGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id " + id + " not found."));
        scatterPlotGraph.setDeleted();
        scatterPlotGraphRepo.save(scatterPlotGraph);
        return mapToDto(scatterPlotGraph);
    }

    // Create new scatter graph
    public ScatterPlotGraphDTO createGraph(ScatterPlotGraphDTO scatterPlotGraphDTO) {
        ScatterPlotGraph scatterPlotGraph = mapToEntity(scatterPlotGraphDTO);
        scatterPlotGraph.setLastUpdated(new Date());
        scatterPlotGraphRepo.save(scatterPlotGraph);
        return mapToDto(scatterPlotGraph);
    }

    // Update existing scatter graph by id
    public ScatterPlotGraphDTO updateGraph(int id, ScatterPlotGraphDTO scatterPlotGraphDTO) throws BadRequestException {
        // Fetch the graph from the repository
        ScatterPlotGraph existingGraph = scatterPlotGraphRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Graph not found with ID: " + id));

        // Update the graph properties with new values
        existingGraph.setTitle(scatterPlotGraphDTO.getTitle());
        existingGraph.setXAxis(scatterPlotGraphDTO.getXAxis());
        existingGraph.setYAxis(scatterPlotGraphDTO.getYAxis());
        existingGraph.setXAxisLabel(scatterPlotGraphDTO.getXAxisLabel());
        existingGraph.setYAxisLabel(scatterPlotGraphDTO.getYAxisLabel());
        existingGraph.setXAxisInterval(scatterPlotGraphDTO.getXAxisInterval());
        existingGraph.setYAxisInterval(scatterPlotGraphDTO.getYAxisInterval());
        existingGraph.setColor(scatterPlotGraphDTO.getColor());
        existingGraph.setLastUpdated(new Date());

        // Save the updated graph back to the repository
        ScatterPlotGraph updatedGraph = scatterPlotGraphRepo.save(existingGraph);

        // Map the updated entity to DTO and return
        return mapToDto(updatedGraph);
    }

    public ScatterPlotGraph mapToEntity(ScatterPlotGraphDTO scatterPlotGraphDTO){
        ScatterPlotGraph scatterPlotGraph =  this.modelMapper.map(scatterPlotGraphDTO, ScatterPlotGraph.class);
        return scatterPlotGraph;
    }
    public ScatterPlotGraphDTO mapToDto(ScatterPlotGraph scatterPlotGraph){
        ScatterPlotGraphDTO graphDTO = this.modelMapper.map(scatterPlotGraph, ScatterPlotGraphDTO.class);
        return graphDTO;
    }

}
