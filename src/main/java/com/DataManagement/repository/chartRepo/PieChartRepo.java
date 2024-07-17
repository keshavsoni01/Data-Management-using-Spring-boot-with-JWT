package com.DataManagement.repository.chartRepo;

import com.DataManagement.entity.charts.BubbleChart;
import com.DataManagement.entity.charts.FunnelChart;
import com.DataManagement.entity.charts.PieChart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PieChartRepo extends JpaRepository<PieChart,Integer> {
    Optional<PieChart> findByTitleAndIsDeletedFalse(String title);
    List<PieChart> findAllByIsDeletedFalse();
    Optional<PieChart> findByIdAndIsDeletedFalse(int id);

}
