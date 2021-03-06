package com.destrim.SADP.sensor_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<NamesOnly> getDistinctByOrderByName();

    List<SensorData> getByName(String name);

    @Query("SELECT max(s.timestamp) from SensorData s where s.name = :name")
    Optional<LocalDateTime> getSpecificSensorMaxDate(@Param("name") String name);

    @Query("SELECT min(s.timestamp) from SensorData s where s.name = :name")
    Optional<LocalDateTime> getSpecificSensorMinDate(@Param("name") String name);

    List<SensorData> getByNameAndTimestampBetweenOrderByTimestamp(
            String name,
            LocalDateTime minDate,
            LocalDateTime maxDate
    );

    SensorData findTopByNameOrderByIdDesc(String name);

    interface NamesOnly {
        String getName();
    }
}
