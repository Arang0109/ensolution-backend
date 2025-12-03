package com.project.easywork.agency.repository;

import com.project.easywork.agency.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
  List<Vehicle> findByTeam_Id(Long teamId);
}