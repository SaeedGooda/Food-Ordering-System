package com.saeed.food_ordering_system.request;

import com.saeed.food_ordering_system.model.Address;
import com.saeed.food_ordering_system.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
