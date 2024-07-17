package com.DataManagement.repository.chartRepo;

import com.DataManagement.entity.charts.BubbleChart;
import com.DataManagement.entity.charts.FunnelChart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FunnelChartRepo extends JpaRepository<FunnelChart,Integer> {
    Optional<FunnelChart> findByTitleAndIsDeletedFalse(String title);
    List<FunnelChart> findAllByIsDeletedFalse();
    Optional<FunnelChart> findByIdAndIsDeletedFalse(int id);

}
