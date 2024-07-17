package com.DataManagement.service.graphService;

import com.DataManagement.dto.graphDTO.HistogramGraphDTO;
import com.DataManagement.entity.graphs.HistogramGraph;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.graphRepo.HistogramGraphRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistogramGraphService {
    @Autowired
    private HistogramGraphRepo histogramGraphRepo;
    @Autowired
    private ModelMapper modelMapper;

    // Get all scatter Graphs details
    public List<HistogramGraphDTO> getGraphs(){
        return histogramGraphRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get scatter Graph by Id --------------------------
    public HistogramGraphDTO getGraphById(int id) throws BadRequestException {
        HistogramGraph histogramGraph = histogramGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id "+id+" not found."));
        return mapToDto(histogramGraph);
    }

    // Get scatter Graph by Title ----------------------
    public HistogramGraphDTO getGraphByTitle(String title) throws BadRequestException{
        HistogramGraph histogramGraph = histogramGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name "+title+" not found."));
        return mapToDto(histogramGraph);
    }

    // Delete a scatter graph by Title name
    public HistogramGraphDTO deleteGraphByTitle(String title) throws BadRequestException{
        HistogramGraph histogramGraph = histogramGraphRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"Graph with Title name " + title + " not found."));
        histogramGraph.setDeleted();
        histogramGraphRepo.save(histogramGraph);
        return mapToDto(histogramGraph);
    }

    // Delete a scatter Graph by id
    public HistogramGraphDTO deleteGraphById(int id) throws BadRequestException{
        HistogramGraph histogramGraph = histogramGraphRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with id " + id + " not found."));
        histogramGraph.setDeleted();
        histogramGraphRepo.save(histogramGraph);
        return mapToDto(histogramGraph);
    }

    // Create new scatter graph
    public HistogramGraphDTO createGraph(HistogramGraphDTO histogramGraphDTO) {
        HistogramGraph histogramGraph = mapToEntity(histogramGraphDTO);
        histogramGraph.setLastUpdated(new Date());
        histogramGraphRepo.save(histogramGraph);
        return mapToDto(histogramGraph);
    }

    // Update existing scatter graph by id
    public HistogramGraphDTO updateGraph(int id, HistogramGraphDTO histogramGraphDTO) throws BadRequestException {
        // Fetch the graph from the repository
        HistogramGraph existingGraph = histogramGraphRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Graph not found with ID: " + id));

        // Update the graph properties with new values
        existingGraph.setTitle(histogramGraphDTO.getTitle());
        existingGraph.setXAxis(histogramGraphDTO.getXAxis());
        existingGraph.setYAxis(histogramGraphDTO.getYAxis());
        existingGraph.setXAxisLabel(histogramGraphDTO.getXAxisLabel());
        existingGraph.setYAxisLabel(histogramGraphDTO.getYAxisLabel());
        existingGraph.setXAxisInterval(histogramGraphDTO.getXAxisInterval());
        existingGraph.setYAxisInterval(histogramGraphDTO.getYAxisInterval());
        existingGraph.setColor(histogramGraphDTO.getColor());
        existingGraph.setLastUpdated(new Date());

        // Save the updated graph back to the repository
        HistogramGraph updatedGraph = histogramGraphRepo.save(existingGraph);

        // Map the updated entity to DTO and return
        return mapToDto(updatedGraph);
    }

    public HistogramGraph mapToEntity(HistogramGraphDTO histogramGraphDTO){
        HistogramGraph histogramGraph =  this.modelMapper.map(histogramGraphDTO, HistogramGraph.class);
        return histogramGraph;
    }
    public HistogramGraphDTO mapToDto(HistogramGraph histogramGraph){
        HistogramGraphDTO graphDTO = this.modelMapper.map(histogramGraph, HistogramGraphDTO.class);
        return graphDTO;
    }
}
