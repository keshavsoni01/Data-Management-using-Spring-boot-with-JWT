package com.DataManagement.repository.graphRepo;

import com.DataManagement.entity.charts.RadarChart;
import com.DataManagement.entity.graphs.BarGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BarGraphRepo extends JpaRepository<BarGraph,Integer> {
    Optional<BarGraph> findByTitleAndIsDeletedFalse(String title);
    List<BarGraph> findAllByIsDeletedFalse();
    Optional<BarGraph> findByIdAndIsDeletedFalse(int id);

}
