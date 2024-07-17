package com.DataManagement.service.chartService;

import com.DataManagement.dto.chartDTO.BubbleChartDTO;
import com.DataManagement.dto.chartDTO.FunnelChartDTO;
import com.DataManagement.entity.charts.BubbleChart;
import com.DataManagement.entity.charts.FunnelChart;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.chartRepo.FunnelChartRepo;
import org.hibernate.sql.ast.tree.expression.FunctionExpression;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FunnelChartService{

    @Autowired
    private FunnelChartRepo funnelChartRepo;
    @Autowired
    private ModelMapper modelMapper;

    // get all charts
    public List<FunnelChartDTO> getCharts(){
        return funnelChartRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // get chart by id
    public FunnelChartDTO getChartById(int id) throws BadRequestException {
        FunnelChart funnelChart = funnelChartRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"There is no chart with id "+id));
        return mapToDto(funnelChart);
    }

    // get chart by title
    public FunnelChartDTO getChartByTitle( String title) throws BadRequestException{
        FunnelChart funnelChart = funnelChartRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"There is no title of name " +title+" in chart"));
        return mapToDto(funnelChart);
    }

    // delete chart by id
    public FunnelChartDTO deleteChartById(int id) throws BadRequestException{
        FunnelChart funnelChart = funnelChartRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Chart with " + id + " not found."));
        funnelChart.setDeleted();
        funnelChartRepo.save(funnelChart);
        return mapToDto(funnelChart);
    }

    // delete chart by title
    public FunnelChartDTO deleteChartByTitle(String title) throws BadRequestException{
       FunnelChart funnelChart = funnelChartRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"chart with title"+title+"not found"));
       funnelChart.setDeleted();
       funnelChartRepo.save(funnelChart);
       return mapToDto(funnelChart);
    }
    // Create new graph
    public FunnelChartDTO createChart(FunnelChartDTO funnelChartDTO) {
        FunnelChart funnelChart = mapToEntity(funnelChartDTO);
        funnelChart.setLastUpdate(new Date());
        funnelChartRepo.save(funnelChart);
        return mapToDto(funnelChart);
    }

    // update existing chart by id
    public FunnelChartDTO updateChart(int id, FunnelChartDTO updatedChartDTO) throws BadRequestException {
        // Fetch the chart from the repository
        FunnelChart existingChart = funnelChartRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Chart not found with ID: " + id));

        // Update the chart properties with new values
        existingChart.setTitle(updatedChartDTO.getTitle());
        existingChart.setData(updatedChartDTO.getData());
        existingChart.setLabels(updatedChartDTO.getLabels());
        existingChart.setColor(updatedChartDTO.getColor());
        existingChart.setLastUpdate(new Date());

        // Save the updated chart back to the repository
        FunnelChart updatedChart = funnelChartRepo.save(existingChart);

        // Map the updated entity to DTO and return
        return mapToDto(updatedChart);
    }

    public FunnelChart mapToEntity(FunnelChartDTO funnelChartDTO){
        FunnelChart funnelChart = this.modelMapper.map(funnelChartDTO, FunnelChart.class);
        return funnelChart;
    }
    public FunnelChartDTO mapToDto(FunnelChart funnelChart){
        FunnelChartDTO funnelChartDTO = this.modelMapper.map(funnelChart, FunnelChartDTO.class);
        return funnelChartDTO;
    }
}
