package com.example.webpcmarket.repository;

import com.example.webpcmarket.entity.Measurement;
import com.example.webpcmarket.projection.MeasurementProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "measurment",excerptProjection = MeasurementProjection.class)
public interface MeasurmentRepository extends JpaRepository<Measurement,Integer> {
}
