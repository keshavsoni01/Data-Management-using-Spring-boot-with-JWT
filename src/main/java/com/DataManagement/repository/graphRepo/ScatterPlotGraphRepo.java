package com.DataManagement.repository.graphRepo;

import com.DataManagement.entity.charts.RadarChart;
import com.DataManagement.entity.graphs.ScatterPlotGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScatterPlotGraphRepo extends JpaRepository<ScatterPlotGraph,Integer> {
    Optional<ScatterPlotGraph> findByTitleAndIsDeletedFalse(String title);
    List<ScatterPlotGraph> findAllByIsDeletedFalse();
    Optional<ScatterPlotGraph> findByIdAndIsDeletedFalse(int id);
}
