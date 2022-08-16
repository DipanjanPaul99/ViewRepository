package  com.example.demo.entity;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
    private String city;
    private String state;
    private Long pincode;
    private String country;


}