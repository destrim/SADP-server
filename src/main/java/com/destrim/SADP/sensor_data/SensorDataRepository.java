package com.destrim.SADP.sensor_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

//    @Query("SELECT distinct name from SensorData")
//    Optional<List<String>> getSensorNames();
    List<NamesOnly> getDistinctBy();

//    @Query("SELECT s from SensorData s where s.name = :name")
//    Optional<List<SensorData>> getSpecificSensorData(@Param("name") String sensorName);
    List<SensorData> getByName(String name);


    interface NamesOnly {
        String getName();
    }
}
