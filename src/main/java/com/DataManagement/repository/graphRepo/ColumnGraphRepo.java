package com.DataManagement.repository.graphRepo;

import com.DataManagement.entity.charts.RadarChart;
import com.DataManagement.entity.graphs.ColumnGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColumnGraphRepo extends JpaRepository<ColumnGraph,Integer> {
    Optional<ColumnGraph> findByTitleAndIsDeletedFalse(String title);
    List<ColumnGraph> findAllByIsDeletedFalse();
    Optional<ColumnGraph> findByIdAndIsDeletedFalse(int id);

}
