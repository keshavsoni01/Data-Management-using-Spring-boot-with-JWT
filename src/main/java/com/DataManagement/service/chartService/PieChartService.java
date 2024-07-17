package com.DataManagement.service.chartService;

import com.DataManagement.dto.chartDTO.FunnelChartDTO;
import com.DataManagement.dto.chartDTO.PieChartDTO;
import com.DataManagement.entity.charts.FunnelChart;
import com.DataManagement.entity.charts.PieChart;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.chartRepo.PieChartRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PieChartService {

    @Autowired
    private PieChartRepo pieChartRepo;
    @Autowired
    private ModelMapper modelMapper;

    // get all chart
    public List<PieChartDTO> getCharts(){
        return pieChartRepo.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // get chart by id
    public PieChartDTO getChartById(int id) throws BadRequestException {
       PieChart pieChart = pieChartRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"There is no chart with id "+id));
       return mapToDto(pieChart);
    }

    // get chart by title
    public PieChartDTO getChartByTitle( String title) throws BadRequestException{
        PieChart pieChart = pieChartRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"There is no title of name " +title+" in chart"));
        return mapToDto(pieChart);
    }

    // delete chart by id
    public PieChartDTO deleteChartById(int id) throws BadRequestException{
        PieChart pieChart = pieChartRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(400,"Chart with " + id + " not found."));
        pieChart.setDeleted();
        pieChartRepo.save(pieChart);
        return mapToDto(pieChart);
    }

    // delete chart by title
    public PieChartDTO deleteChartByTitle(String title) throws BadRequestException{
         PieChart pieChart = pieChartRepo.findByTitleAndIsDeletedFalse(title).orElseThrow(()->new BadRequestException(400,"chart with title"+title+"not found"));
         pieChart.setDeleted();
         pieChartRepo.save(pieChart);
         return mapToDto(pieChart);
    }
    // Create new graph
    public PieChartDTO createChart(PieChartDTO pieChartDTO) {
        PieChart pieChart = mapToEntity(pieChartDTO);
        pieChart.setLastUpdate(new Date());
        pieChartRepo.save(pieChart);
        return mapToDto(pieChart);
    }

    // update existing chart by id
    public PieChartDTO updateChart(int id, PieChartDTO updatedChartDTO) throws BadRequestException {
        // Fetch the chart from the repository
        PieChart existingChart = pieChartRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(400, "Chart not found with ID: " + id));

        // Update the chart properties with new values
        existingChart.setTitle(updatedChartDTO.getTitle());
        existingChart.setData(updatedChartDTO.getData());
        existingChart.setLabels(updatedChartDTO.getLabels());
        existingChart.setColor(updatedChartDTO.getColor());
        existingChart.setLastUpdate(new Date());

        // Save the updated chart back to the repository
        PieChart updatedChart = pieChartRepo.save(existingChart);

        // Map the updated entity to DTO and return
        return mapToDto(updatedChart);
    }
    public PieChart mapToEntity(PieChartDTO  pieChartDTO){
        PieChart pieChart = this.modelMapper.map(pieChartDTO, PieChart.class);
        return pieChart;
    }
    public PieChartDTO mapToDto(PieChart pieChart){
        PieChartDTO pieChartDTO = this.modelMapper.map(pieChart, PieChartDTO.class);
        return pieChartDTO;
    }
}
