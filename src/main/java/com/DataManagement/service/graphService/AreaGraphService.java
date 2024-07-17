package com.DataManagement.service.graphService;

import com.DataManagement.dto.graphDTO.AreaGraphDTO;
import com.DataManagement.dto.userDTO.UserDTO;
import com.DataManagement.entity.graphs.AreaGraph;
import com.DataManagement.entity.user.User;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.graphRepo.AreaGraphRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaGraphService {
   @Autowired
   private AreaGraphRepo areaGraphRepo;
   @Autowired
   private ModelMapper modelMapper;
    
    // Get all line Graphs details
    public List<AreaGraphDTO> getGraphs(){
        return areaGraphRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get line Graph by Id --------------------------
    public AreaGraphDTO getGraphById(int id) throws BadRequestException {
        AreaGraph areaGraph = areaGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id "+id+" not found."));
        return this.mapToDto(areaGraph);
    }

    // Get line Graph by Title ----------------------
    public AreaGraphDTO getGraphByTitle(String title) throws BadRequestException{
        AreaGraph areaGraph = areaGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name "+title+" not found."));
        return this.mapToDto(areaGraph);
    }

    // Delete a line graph by Title name
    public AreaGraphDTO deleteGraphByTitle(String title) throws BadRequestException{
        AreaGraph graph = areaGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name " + title + " not found."));
        graph.setDeleted();
        areaGraphRepo.save(graph);
        return mapToDto(graph);
    }

    // Delete a line Graph by id
    public AreaGraphDTO deleteGraphById(int id) throws BadRequestException{
        AreaGraph areaGraph = areaGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id " + id + " not found."));
        areaGraph.setDeleted();
        areaGraphRepo.save(areaGraph);
        return mapToDto(areaGraph);
    }

    // Create new line graph
    public AreaGraphDTO createGraph(AreaGraphDTO areaGraphDTO) {
        AreaGraph areaGraph = mapToEntity(areaGraphDTO);
        areaGraph.setLastUpdated(new Date());
        areaGraphRepo.save(areaGraph);
        return mapToDto(areaGraph);
    }

    // Update existing line graph by id
    public AreaGraphDTO updateGraph(int id, AreaGraphDTO updatedAreaGraphDTO) throws BadRequestException {
        // Fetch the graph from the repository
        AreaGraph existingGraph = areaGraphRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Graph not found with ID: " + id));

        // Update the graph properties with new values
        existingGraph.setTitle(updatedAreaGraphDTO.getTitle());
        existingGraph.setXAxis(updatedAreaGraphDTO.getXAxis());
        existingGraph.setYAxis(updatedAreaGraphDTO.getYAxis());
        existingGraph.setXAxisLabel(updatedAreaGraphDTO.getXAxisLabel());
        existingGraph.setYAxisLabel(updatedAreaGraphDTO.getYAxisLabel());
        existingGraph.setXAxisInterval(updatedAreaGraphDTO.getXAxisInterval());
        existingGraph.setYAxisInterval(updatedAreaGraphDTO.getYAxisInterval());
        existingGraph.setColor(updatedAreaGraphDTO.getColor());
        existingGraph.setLastUpdated(new Date());

        // Save the updated graph back to the repository
        AreaGraph updatedGraph = areaGraphRepo.save(existingGraph);

        // Map the updated entity to DTO and return
        return mapToDto(updatedGraph);
    }

    public AreaGraph mapToEntity(AreaGraphDTO areaGraphDTO){
        AreaGraph areaGraph = this.modelMapper.map(areaGraphDTO, AreaGraph.class);
        return areaGraph;
    }
    public AreaGraphDTO mapToDto(AreaGraph areaGraph){
        AreaGraphDTO areaGraphDTO = this.modelMapper.map(areaGraph, AreaGraphDTO.class);
        return areaGraphDTO;
    }
}
