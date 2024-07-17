package com.DataManagement.repository.chartRepo;

import com.DataManagement.entity.charts.BubbleChart;
import com.DataManagement.entity.charts.PieChart;
import com.DataManagement.entity.charts.RadarChart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RadarChartRepo extends JpaRepository<RadarChart,Integer> {
    Optional<RadarChart> findByTitleAndIsDeletedFalse(String title);
    List<RadarChart> findAllByIsDeletedFalse();
    Optional<RadarChart> findByIdAndIsDeletedFalse(int id);

}
