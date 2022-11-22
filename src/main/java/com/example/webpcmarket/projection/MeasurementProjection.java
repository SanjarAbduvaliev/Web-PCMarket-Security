package com.example.webpcmarket.projection;

import com.example.webpcmarket.entity.Measurement;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "measurment",types = Measurement.class)
public interface MeasurementProjection {
    Integer getId();
    String getName();

}
