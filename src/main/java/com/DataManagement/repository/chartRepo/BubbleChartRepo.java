package com.DataManagement.repository.chartRepo;

import com.DataManagement.entity.charts.BubbleChart;
import com.DataManagement.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BubbleChartRepo extends JpaRepository<BubbleChart,Integer> {
    Optional<BubbleChart> findByTitleAndIsDeletedFalse(String title);
    List<BubbleChart> findAllByIsDeletedFalse();
    Optional<BubbleChart> findByIdAndIsDeletedFalse(int id);
}
