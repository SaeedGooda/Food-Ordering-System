package com.saeed.food_ordering_system.service;

import com.saeed.food_ordering_system.dto.RestaurantDto;
import com.saeed.food_ordering_system.model.Address;
import com.saeed.food_ordering_system.model.Restaurant;
import com.saeed.food_ordering_system.model.User;
import com.saeed.food_ordering_system.repository.AddressRepository;
import com.saeed.food_ordering_system.repository.RestaurantRepository;
import com.saeed.food_ordering_system.repository.UserRepository;
import com.saeed.food_ordering_system.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setName(req.getName());
        restaurant.setImages(req.getImages());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(updatedRestaurant.getCuisineType() != null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(updatedRestaurant.getDescription() != null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if(updatedRestaurant.getName() != null){
            restaurant.setName(updatedRestaurant.getName());
        }
        return restaurant;
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isEmpty()){
            throw new Exception("Restaurant Not Found with id " + id);
        }
        return restaurant.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant == null){
            throw new Exception("Restaurant Not Found with user id " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setId(restaurantId);

        boolean isFavourite = false;
        List<RestaurantDto> favourites = user.getFavourites();
        for(RestaurantDto favourite: favourites){
            if(favourite.getId().equals(restaurantId)){
                isFavourite = true;
                break;
            }
        }

        if(isFavourite){
            favourites.removeIf(favourite -> favourite.getId().equals(restaurantId));
        } else{
            favourites.add(restaurantDto);
        }

        userRepository.save(user);

        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
