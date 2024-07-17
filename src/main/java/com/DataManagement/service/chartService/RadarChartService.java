package com.DataManagement.service.chartService;

import com.DataManagement.controller.chartController.RadarChartContoller;
import com.DataManagement.dto.chartDTO.FunnelChartDTO;
import com.DataManagement.dto.chartDTO.PieChartDTO;
import com.DataManagement.dto.chartDTO.RadarChartDTO;
import com.DataManagement.entity.charts.FunnelChart;
import com.DataManagement.entity.charts.PieChart;
import com.DataManagement.entity.charts.RadarChart;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.chartRepo.RadarChartRepo;
import org.h2.engine.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RadarChartService {
    @Autowired
    private RadarChartRepo radarChartRepo;
    @Autowired
    private ModelMapper modelMapper;

    // get all charts
    public List<RadarChartDTO> getCharts(){
        return radarChartRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // get chart by id
    public RadarChartDTO getChartById(int id) throws BadRequestException {
        RadarChart radarChart = radarChartRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"There is no chart with id "+id));
        return mapToDto(radarChart);
    }

    // get chart by title
    public RadarChartDTO getChartByTitle( String title) throws BadRequestException{
        RadarChart radarChart =  radarChartRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"There is no title of name " +title+" in chart"));
        return mapToDto(radarChart);
    }

    // delete chart by id
    public RadarChartDTO deleteChartById(int id) throws BadRequestException{
        RadarChart radarChart = radarChartRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Graph with Title name " + id + " not found."));
        radarChart.setDeleted();
        radarChartRepo.save(radarChart);
        return mapToDto(radarChart);
    }

    // delete chart by title
    public RadarChartDTO deleteChartByTitle(String title) throws BadRequestException{
        RadarChart radarChart = radarChartRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"chart with title"+title+"not found"));
        radarChart.setDeleted();
        radarChartRepo.save(radarChart);
        return mapToDto(radarChart);
    }
    // Create new graph
    public RadarChartDTO createChart(RadarChartDTO radarChartDTO) {
        RadarChart radarChart = mapToEntity(radarChartDTO);
        radarChart.setLastUpdate(new Date());
        radarChartRepo.save(radarChart);
        return mapToDto(radarChart);
    }

    // update existing chart by id
    public RadarChartDTO updateChart(int id, RadarChartDTO updatedChartDTO) throws BadRequestException {
        // Fetch the chart from the repository
        RadarChart existingChart = radarChartRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Chart not found with ID: " + id));

        // Update the chart properties with new values
        existingChart.setTitle(updatedChartDTO.getTitle());
        existingChart.setData(updatedChartDTO.getData());
        existingChart.setLabels(updatedChartDTO.getLabels());
        existingChart.setColor(updatedChartDTO.getColor());
        existingChart.setLastUpdate(new Date());

        // Save the updated chart back to the repository
        RadarChart updatedChart = radarChartRepo.save(existingChart);

        // Map the updated entity to DTO and return
        return mapToDto(updatedChart);
    }
    public RadarChart mapToEntity(RadarChartDTO radarChartDTO){
        RadarChart radarChart = this.modelMapper.map(radarChartDTO, RadarChart.class);
        return radarChart;
    }
    public RadarChartDTO mapToDto(RadarChart radarChart){
        RadarChartDTO radarChartDTO = this.modelMapper.map(radarChart, RadarChartDTO.class);
        return radarChartDTO;
    }
}
