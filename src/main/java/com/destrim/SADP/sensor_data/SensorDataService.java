package com.destrim.SADP.sensor_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    public List<String> getSensorNames() {
        return sensorDataRepository
                .getDistinctBy()
                .stream()
                .map(SensorDataRepository.NamesOnly::getName)
                .collect(Collectors.toList());
    }


    public List<SensorData> getSensorDataByName(String sensorName) {
        return sensorDataRepository.getByName(sensorName);
    }


    public List<LocalDateTime> getSensorDateRangeByName(String name) {
        List<LocalDateTime> dateRangeList = new ArrayList<>();
        dateRangeList.add(sensorDataRepository.getSpecificSensorMinDate(name).orElseThrow());
        dateRangeList.add(sensorDataRepository.getSpecificSensorMaxDate(name).orElseThrow());
        return dateRangeList;
    }


    public List<SensorData> getSensorDataByNameAndBetweenRange(
            String name,
            LocalDate minDate,
            LocalDate maxDate
    ) {
        return sensorDataRepository.getByNameAndTimestampBetween(
                name,
                minDate.atStartOfDay(),
                maxDate.atTime(23, 59, 59)
        );
    }


    public void addSensorData(SensorData sensorData) {
        sensorDataRepository.save(sensorData);
    }
}
