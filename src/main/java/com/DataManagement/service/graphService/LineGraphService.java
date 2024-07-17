package com.DataManagement.service.graphService;


import com.DataManagement.dto.graphDTO.ColumnGraphDTO;
import com.DataManagement.dto.graphDTO.LineGraphDTO;
import com.DataManagement.entity.graphs.ColumnGraph;
import com.DataManagement.entity.graphs.LineGraph;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.graphRepo.LineGraphRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LineGraphService {
    @Autowired
    private LineGraphRepo lineGraphRepo;
    @Autowired
    private ModelMapper modelMapper;

    // Get all line Graphs details
    public List<LineGraphDTO> getGraphs(){
        return lineGraphRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get line Graph by Id --------------------------
    public LineGraphDTO getGraphById(int id) throws BadRequestException {
       LineGraph lineGraph = lineGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id "+id+" not found."));
       return mapToDto(lineGraph);
    }

    // Get line Graph by Title ----------------------
    public LineGraphDTO getGraphByTitle(String title) throws BadRequestException{
       LineGraph lineGraph = lineGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name "+title+" not found."));
       return mapToDto(lineGraph);
    }

    // Delete a line graph by Title name
    public LineGraphDTO deleteGraphByTitle(String title) throws BadRequestException{
       LineGraph lineGraph = lineGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name " + title + " not found."));
       lineGraph.setDeleted();
       lineGraphRepo.save(lineGraph);
       return mapToDto(lineGraph);
    }

    // Delete a line Graph by id
    public LineGraphDTO deleteGraphById(int id) throws BadRequestException{
        LineGraph lineGraph = lineGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id " + id + " not found."));
        lineGraph.setDeleted();
        lineGraphRepo.save(lineGraph);
        return mapToDto(lineGraph);
    }

    // Create new line graph
    public LineGraphDTO createGraph(LineGraphDTO lineGraphDTO) {
        LineGraph lineGraph = mapToEntity(lineGraphDTO);
        lineGraph.setLastUpdated(new Date());
        lineGraphRepo.save(lineGraph);
        return mapToDto(lineGraph);
    }

    // Update existing line graph by id
    public LineGraphDTO updateGraph(int id, LineGraphDTO lineGraphDTO) throws BadRequestException {
        // Fetch the graph from the repository
        LineGraph existingGraph = lineGraphRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Graph not found with ID: " + id));

        // Update the graph properties with new values
        existingGraph.setTitle(lineGraphDTO.getTitle());
        existingGraph.setXAxis(lineGraphDTO.getXAxis());
        existingGraph.setYAxis(lineGraphDTO.getYAxis());
        existingGraph.setXAxisLabel(lineGraphDTO.getXAxisLabel());
        existingGraph.setYAxisLabel(lineGraphDTO.getYAxisLabel());
        existingGraph.setXAxisInterval(lineGraphDTO.getXAxisInterval());
        existingGraph.setYAxisInterval(lineGraphDTO.getYAxisInterval());
        existingGraph.setColor(lineGraphDTO.getColor());
        existingGraph.setLastUpdated(new Date());

        // Save the updated graph back to the repository
        LineGraph updatedGraph = lineGraphRepo.save(existingGraph);

        // Map the updated entity to DTO and return
        return mapToDto(updatedGraph);
    }

    public LineGraph mapToEntity(LineGraphDTO lineGraphDTO){
        LineGraph lineGraph =  this.modelMapper.map(lineGraphDTO, LineGraph.class);
        return lineGraph;
    }
    public LineGraphDTO mapToDto(LineGraph lineGraph){
        LineGraphDTO graphDTO = this.modelMapper.map(lineGraph, LineGraphDTO.class);
        return graphDTO;
    }
}
