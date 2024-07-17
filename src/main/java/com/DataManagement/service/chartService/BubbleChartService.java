package com.DataManagement.service.chartService;

import com.DataManagement.dto.chartDTO.BubbleChartDTO;
import com.DataManagement.entity.charts.BubbleChart;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.chartRepo.BubbleChartRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BubbleChartService {

    @Autowired
    private BubbleChartRepo bubbleChartRepo;
    @Autowired
    private ModelMapper modelMapper;

    // get all charts

    public List<BubbleChartDTO> getCharts(){
        return bubbleChartRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // get chart by id

    public BubbleChartDTO getChartById(int id) throws BadRequestException {
       BubbleChart bubbleChart = bubbleChartRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"There is no chart with id "+id));
       return mapToDto(bubbleChart);
    }

    // get chart by title
    public BubbleChartDTO getChartByTitle( String title) throws BadRequestException{
        BubbleChart bubbleChart = bubbleChartRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"There is no title of name " +title+" in chart"));
        return mapToDto(bubbleChart);
    }

    // delete chart by id
    public BubbleChartDTO deleteChartById(int id) throws BadRequestException{
       BubbleChart bubbleChart = bubbleChartRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with Title name " + id + " not found."));
       bubbleChart.setDeleted();
       bubbleChartRepo.save(bubbleChart);
       return mapToDto(bubbleChart);
    }

    // delete chart by title
    public BubbleChartDTO deleteChartByTitle(String title) throws BadRequestException{
        BubbleChart bubbleChart = bubbleChartRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"chart with title"+title+"not found"));
        bubbleChart.setDeleted();
        bubbleChartRepo.save(bubbleChart);
        return mapToDto(bubbleChart);
    }
    // Create new graph
    public BubbleChartDTO createChart(BubbleChartDTO bubbleChartDTO) {
        BubbleChart bubbleChart = mapToEntity(bubbleChartDTO);
        bubbleChart.setLastUpdate(new Date());
        bubbleChartRepo.save(bubbleChart);
        return mapToDto(bubbleChart);
    }

    // update existing chart by id
    public BubbleChartDTO updateChart(int id, BubbleChartDTO updatedChartDTO) throws BadRequestException {
        // Fetch the chart from the repository
        BubbleChart existingChart = bubbleChartRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Chart not found with ID: " + id));

        // Update the chart properties with new values
        existingChart.setTitle(updatedChartDTO.getTitle());
        existingChart.setData(updatedChartDTO.getData());
        existingChart.setLabels(updatedChartDTO.getLabels());
        existingChart.setColor(updatedChartDTO.getColor());
        existingChart.setLastUpdate(new Date());

        // Save the updated chart back to the repository
        BubbleChart updatedChart = bubbleChartRepo.save(existingChart);

        // Map the updated entity to DTO and return
        return mapToDto(updatedChart);
    }

    public BubbleChart mapToEntity(BubbleChartDTO bubbleChartDTO){
        BubbleChart bubbleChart = this.modelMapper.map(bubbleChartDTO, BubbleChart.class);
        return bubbleChart;
    }
    public BubbleChartDTO mapToDto(BubbleChart bubbleChart){
        BubbleChartDTO bubbleChartDTO = this.modelMapper.map(bubbleChart, BubbleChartDTO.class);
        return bubbleChartDTO;
    }



}
