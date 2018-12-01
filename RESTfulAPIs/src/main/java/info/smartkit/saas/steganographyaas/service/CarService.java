package info.smartkit.saas.steganographyaas.service;

import info.smartkit.saas.steganographyaas.model.Car;
import info.smartkit.saas.steganographyaas.model.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }
}

