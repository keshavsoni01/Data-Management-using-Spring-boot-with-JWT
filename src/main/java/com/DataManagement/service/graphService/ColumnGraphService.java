package com.DataManagement.service.graphService;

import com.DataManagement.dto.graphDTO.AreaGraphDTO;
import com.DataManagement.dto.graphDTO.ColumnGraphDTO;
import com.DataManagement.entity.graphs.AreaGraph;
import com.DataManagement.entity.graphs.BarGraph;
import com.DataManagement.entity.graphs.ColumnGraph;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.graphRepo.ColumnGraphRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColumnGraphService {
    @Autowired
    private ColumnGraphRepo columnGraphRepo;
    @Autowired
    private ModelMapper modelMapper;

    // Get all bar Graphs details
    public List<ColumnGraphDTO> getGraphs(){
        return columnGraphRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get bar Graph by Id --------------------------
    public ColumnGraphDTO getGraphById(int id) throws BadRequestException {
        ColumnGraph columnGraph = columnGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id "+id+" not found."));
        return mapToDto(columnGraph);
    }

    // Get bar Graph by Title ----------------------
    public ColumnGraphDTO getGraphByTitle(String title) throws BadRequestException{
        ColumnGraph columnGraph = columnGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name "+title+" not found."));
        return mapToDto(columnGraph);
    }

    // Delete a bar graph by Title name
    public ColumnGraphDTO deleteGraphByTitle(String title) throws BadRequestException{
        ColumnGraph columnGraph = columnGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name " + title + " not found."));
        columnGraph.setDeleted();
        columnGraphRepo.save(columnGraph);
        return mapToDto(columnGraph);
    }

    // Delete a bar Graph by id
    public ColumnGraphDTO deleteGraphById(int id) throws BadRequestException{
        ColumnGraph graph = columnGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id " + id + " not found."));
        graph.setDeleted();
        columnGraphRepo.save(graph);
        return mapToDto(graph);
    }

    // Create new bar graph
    public ColumnGraphDTO createGraph(ColumnGraphDTO columnGraphDTO) {
        ColumnGraph columnGraph = mapToEntity(columnGraphDTO);
        columnGraph.setLastUpdated(new Date());
        columnGraphRepo.save(columnGraph);
        return mapToDto(columnGraph);
    }

    // Update existing bar graph by id
    public ColumnGraphDTO updateGraph(int id, ColumnGraphDTO columnGraphDTO) throws BadRequestException {
        // Fetch the graph from the repository
        ColumnGraph existingGraph = columnGraphRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Graph not found with ID: " + id));

        // Update the graph properties with new values
        existingGraph.setTitle(columnGraphDTO.getTitle());
        existingGraph.setXAxis(columnGraphDTO.getXAxis());
        existingGraph.setYAxis(columnGraphDTO.getYAxis());
        existingGraph.setXAxisLabel(columnGraphDTO.getXAxisLabel());
        existingGraph.setYAxisLabel(columnGraphDTO.getYAxisLabel());
        existingGraph.setXAxisInterval(columnGraphDTO.getXAxisInterval());
        existingGraph.setYAxisInterval(columnGraphDTO.getYAxisInterval());
        existingGraph.setColor(columnGraphDTO.getColor());
        existingGraph.setLastUpdated(new Date());

        // Save the updated graph back to the repository
        ColumnGraph updatedGraph = columnGraphRepo.save(existingGraph);

        // Map the updated entity to DTO and return
        return mapToDto(updatedGraph);
    }

    public ColumnGraph mapToEntity(ColumnGraphDTO columnGraphDTO){
        ColumnGraph columnGraph =  this.modelMapper.map(columnGraphDTO, ColumnGraph.class);
        return columnGraph;
    }
    public ColumnGraphDTO mapToDto(ColumnGraph columnGraph){
        ColumnGraphDTO graphDTO = this.modelMapper.map(columnGraph, ColumnGraphDTO.class);
        return graphDTO;
    }
}
