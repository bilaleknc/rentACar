package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.*;
import com.tobeto.pair9.entities.concretes.Rental;
import com.tobeto.pair9.repositories.RentalRepository;
import com.tobeto.pair9.services.abstracts.RentalService;
import com.tobeto.pair9.services.dtos.rental.requests.AddRentalRequest;
import com.tobeto.pair9.services.dtos.rental.requests.UpdateRentalRequest;
import com.tobeto.pair9.services.dtos.rental.responses.GetListRentalResponse;
import com.tobeto.pair9.services.rules.RentalBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final RentalBusinessRules rentalBusinessRules;

    @Override
    public BaseResult<List<GetListRentalResponse>> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        var result = rentals.stream()
                .map(rental->this.modelMapperService.forResponse()
                        .map(rental, GetListRentalResponse.class)).toList();
        return new BaseResult<>(true,result);
    }

    @Override
    public BaseResult add(AddRentalRequest request) {
        rentalBusinessRules.isExistUserById(request.getUserId());
        rentalBusinessRules.isExistCarById(request.getCarId());
        rentalBusinessRules.calculateDiff(request.getStartDate(),request.getEndDate());
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        rental.setId(null);
        this.rentalRepository.save(rental);
        return new BaseResult<>(true, Messages.rentalAdded);
    }

    @Override
    public BaseResult update(UpdateRentalRequest request) {
        rentalBusinessRules.isExistRentalById(request.getId());
        rentalBusinessRules.isExistUserById(request.getUserId());
        rentalBusinessRules.isExistCarById(request.getCarId());
        rentalBusinessRules.calculateDiff(request.getStartDate(),request.getEndDate());
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        rental.setId(null);
        this.rentalRepository.save(rental);
        return new BaseResult<>(true, Messages.rentalUpdated);
    }

    @Override
    public BaseResult delete(Integer id) {
        this.rentalRepository.deleteById(id);
        return new BaseResult<>(true, Messages.rentalDeleted);
    }

    @Override
    public boolean isExistRentalById(Integer id){
        return rentalRepository.existsById(id);
    }
}
