package com.destrim.SADP.sensor_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensorDataController {

    private final SensorDataService sensorDataService;

    @Autowired
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @GetMapping
    public List<SensorData> getSensorData() {
        return sensorDataService.getSensorData();
    }

    @GetMapping(path = "sensors")
    public List<String> getSensorNames() { return  sensorDataService.getSensorNames(); }

    @PostMapping
    public void addSensorData(@RequestBody SensorData sensorData) {
        sensorDataService.addSensorData(sensorData);
    }
}
