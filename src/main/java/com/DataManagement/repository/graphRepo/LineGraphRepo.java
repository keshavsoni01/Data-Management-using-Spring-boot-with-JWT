package com.DataManagement.repository.graphRepo;

import com.DataManagement.entity.charts.RadarChart;
import com.DataManagement.entity.graphs.LineGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LineGraphRepo extends JpaRepository<LineGraph,Integer> {
    Optional<LineGraph> findByTitleAndIsDeletedFalse(String title);
    List<LineGraph> findAllByIsDeletedFalse();
    Optional<LineGraph> findByIdAndIsDeletedFalse(int id);
}
