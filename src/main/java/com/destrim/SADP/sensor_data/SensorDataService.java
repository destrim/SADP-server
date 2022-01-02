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
                .getDistinctByOrderByName()
                .stream()
                .map(SensorDataRepository.NamesOnly::getName)
                .collect(Collectors.toList());
    }


    public List<SensorData> getSensorDataByName(String name) {
        return sensorDataRepository.getByName(name);
    }


    public List<LocalDateTime> getSensorDateRangeByName(String name) {
        List<LocalDateTime> dateRangeList = new ArrayList<>();
        dateRangeList.add(sensorDataRepository.getSpecificSensorMinDate(name).orElseThrow());
        dateRangeList.add(sensorDataRepository.getSpecificSensorMaxDate(name).orElseThrow());
        return dateRangeList;
    }


    public List<SensorData> getSensorDataByNameAndBetweenRangeOrderByTimestamp(
            String name,
            LocalDate minDate,
            LocalDate maxDate
    ) {
        return sensorDataRepository.getByNameAndTimestampBetweenOrderByTimestamp(
                name,
                minDate.atStartOfDay(),
                maxDate.atTime(23, 59, 59)
        );
    }


    public SensorData getLatestSensorDataByName(String name) {
        return sensorDataRepository.findTopByNameOrderByIdDesc(name);
    }


    public void addSensorData(SensorData sensorData) {
        sensorDataRepository.save(sensorData);
    }
}
