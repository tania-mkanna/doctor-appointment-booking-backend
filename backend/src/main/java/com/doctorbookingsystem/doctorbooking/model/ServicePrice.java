package com.doctorbookingsystem.doctorbooking.model;


import lombok.*;

/**
 * Class representing a service and its associated price.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicePrice {
    private String serviceName;
    private Double price;

}
