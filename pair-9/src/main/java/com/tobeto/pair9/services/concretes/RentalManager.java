package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.entities.concretes.Rental;
import com.tobeto.pair9.repositories.RentalRepository;
import com.tobeto.pair9.services.abstracts.RentalService;
import com.tobeto.pair9.services.dtos.rental.requests.AddRentalRequest;
import com.tobeto.pair9.services.dtos.rental.requests.UpdateRentalRequest;
import com.tobeto.pair9.services.dtos.rental.responses.AddRentalResponse;
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
    public BaseResponse<List<GetListRentalResponse>> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        var result = rentals.stream()
                .map(rental->this.modelMapperService.forResponse()
                        .map(rental, GetListRentalResponse.class)).toList();
        return new BaseResponse<>(true,result);
    }

    @Override
    public BaseResponse add(AddRentalRequest request) {
        rentalBusinessRules.isExistCarById(request.getCarId());
        rentalBusinessRules.calculateDiff(request.getStartDate(),request.getEndDate());
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        rental.setId(null);
        rental.setUser(rentalBusinessRules.getUserByUsername(request.getUsername()));
        this.rentalRepository.save(rental);
        var result = this.modelMapperService.forResponse().map(rental,AddRentalResponse.class);
        return new BaseResponse<>(true, result,Messages.rentalAdded);
    }

    @Override
    public BaseResponse update(UpdateRentalRequest request) {
        rentalBusinessRules.isExistRentalById(request.getId());
        rentalBusinessRules.isExistCarById(request.getCarId());
        rentalBusinessRules.calculateDiff(request.getStartDate(),request.getEndDate());
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        rental.setId(null);
        rental.setUser(rentalBusinessRules.getUserByUsername(request.getUsername()));
        this.rentalRepository.save(rental);
        return new BaseResponse<>(true, Messages.rentalUpdated);
    }

    @Override
    public BaseResponse delete(Integer id) {
        this.rentalRepository.deleteById(id);
        return new BaseResponse<>(true, Messages.rentalDeleted);
    }

    @Override
    public boolean isExistRentalById(Integer id){
        return rentalRepository.existsById(id);
    }
}
