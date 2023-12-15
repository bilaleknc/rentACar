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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    private long calculateDiff(LocalDate date1, LocalDate date2){
        // İki tarih arasındaki farkı hesapla

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        if (daysBetween < 0)
            throw new RuntimeException("The end date cannot be earlier than the start date.");
        if (daysBetween > 25)
            throw new RuntimeException("The vehicle cannot be rented for more than 25 days");
        return daysBetween;
    }

    @Override
    public void add(AddRentalRequest request) {

        long daysBetween = calculateDiff(request.getStart_date(), request.getEnd_date());
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        double dailyPrice = carService.getById(request.getCarId()).getDailyPrice();
        rental.setTotal_price(daysBetween * dailyPrice);

        this.rentalRepository.save(rental);
    }

    @Override
    public void update(UpdateRentalRequest request) {
        calculateDiff(request.getStart_date(), request.getEnd_date());

        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        this.rentalRepository.save(rental);
    }

    @Override
    public void delete(int id) {
        this.rentalRepository.deleteById(id);
    }
}
