package com.destrim.SADP.sensor_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor")
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

    @GetMapping(path = "all")
    public List<String> getSensorNames() {
        return sensorDataService.getSensorNames();
    }

    @GetMapping(path = "{name}")
    public List<SensorData> getSpecificSensorData(@PathVariable String name) {
        return sensorDataService.getSpecificSensorData(name);
    }

    @PostMapping
    public void addSensorData(@RequestBody SensorData sensorData) {
        sensorDataService.addSensorData(sensorData);
    }
}
