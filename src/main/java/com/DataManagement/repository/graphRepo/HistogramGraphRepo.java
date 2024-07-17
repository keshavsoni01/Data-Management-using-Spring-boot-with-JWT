package com.DataManagement.repository.graphRepo;

import com.DataManagement.entity.charts.RadarChart;
import com.DataManagement.entity.graphs.HistogramGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistogramGraphRepo extends JpaRepository<HistogramGraph,Integer> {
    Optional<HistogramGraph> findByTitleAndIsDeletedFalse(String title);
    List<HistogramGraph> findAllByIsDeletedFalse();
    Optional<HistogramGraph> findByIdAndIsDeletedFalse(int id);
}
