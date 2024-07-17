package com.DataManagement.repository.graphRepo;

import com.DataManagement.entity.charts.RadarChart;
import com.DataManagement.entity.graphs.AreaGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AreaGraphRepo extends JpaRepository<AreaGraph,Integer> {
    Optional<AreaGraph> findByTitleAndIsDeletedFalse(String title);
    List<AreaGraph> findAllByIsDeletedFalse();
    Optional<AreaGraph> findByIdAndIsDeletedFalse(int id);
}
