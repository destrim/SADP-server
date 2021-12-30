package com.destrim.SADP.sensor_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public List<SensorData> getSensorDataByName(@PathVariable String name) {
        return sensorDataService.getSensorDataByName(name);
    }

    @GetMapping(path = "daterange/{name}")
    public List<LocalDateTime> getSensorDateRangeByName(@PathVariable String name) {
        return sensorDataService.getSensorDateRangeByName(name);
    }

    @GetMapping(path = "{name}/datainrange")
    public List<SensorData> getSensorDataByNameAndBetweenRange(
            @PathVariable String name,
            @RequestParam(value = "min") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate minDate,
            @RequestParam(value = "max") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxDate
    ) {
        return sensorDataService.getSensorDataByNameAndBetweenRange(
                name,
                minDate,
                maxDate
        );
    }

    @PostMapping(path="data")
    public void addSensorData(@RequestBody SensorData sensorData) {
        sensorDataService.addSensorData(sensorData);
    }
}
