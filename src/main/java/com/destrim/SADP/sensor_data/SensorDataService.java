package com.destrim.SADP.sensor_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public List<SensorData> getSensorData() {
        return sensorDataRepository.findAll();
    }

    public void addSensorData(SensorData sensorData) {
        sensorDataRepository.save(sensorData);
    }


}
