package com.destrim.SADP.sensor_data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class SensorData {

    @Id
    @SequenceGenerator(
            name = "sensor_data_seq",
            sequenceName = "sensor_data_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sensor_data_seq"
    )
    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private Double temp;
    private Double hum;

    public SensorData(String name, String timestamp, Double temp, Double hum) {
        this.name = name;
        this.timestamp = LocalDateTime.parse(timestamp);
        this.temp = temp;
        this.hum = hum;
    }
}
