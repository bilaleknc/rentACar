package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.entities.Car;
import com.tobeto.pair9.entities.Rental;
import com.tobeto.pair9.repositories.RentalRepository;
import com.tobeto.pair9.services.abstracts.CarService;
import com.tobeto.pair9.services.abstracts.RentalService;
import com.tobeto.pair9.services.abstracts.UserService;
import com.tobeto.pair9.services.dtos.car.responses.GetListCarResponse;
import com.tobeto.pair9.services.dtos.rental.requests.AddRentalRequest;
import com.tobeto.pair9.services.dtos.rental.requests.UpdateRentalRequest;
import com.tobeto.pair9.services.dtos.rental.responses.GetListRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private RentalRepository rentalRepository;
    private CarService carService;
    private UserService userService;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetListRentalResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(rental->this.modelMapperService.forResponse()
                        .map(rental, GetListRentalResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void add(AddRentalRequest request) {
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        this.rentalRepository.save(rental);
    }

    @Override
    public void update(UpdateRentalRequest request) {
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        this.rentalRepository.save(rental);
    }

    @Override
    public void delete(int id) {
        this.rentalRepository.deleteById(id);
    }
}
