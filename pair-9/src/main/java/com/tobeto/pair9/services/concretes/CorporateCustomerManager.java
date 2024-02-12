package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.entities.concretes.CorporateCustomer;
import com.tobeto.pair9.repositories.CorporateCustomerRepository;
import com.tobeto.pair9.services.abstracts.CorporateCustomerService;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.AddCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.UpdateCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.responses.GetListCorporateCustomerResponse;
import com.tobeto.pair9.services.rules.CorporateCustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {

    private final CorporateCustomerRepository corporateCustomerRepository;
    private final ModelMapperService modelMapperService;
    private final CorporateCustomerBusinessRules corporateCustomerBusinessRules;

    @Override
    public BaseResult<List<GetListCorporateCustomerResponse>> getAll() {
        List<CorporateCustomer> corporateCustomers = corporateCustomerRepository.findAll();
        var result = corporateCustomers.stream().map(corporateCustomer -> this.modelMapperService.forResponse()
                .map(corporateCustomer, GetListCorporateCustomerResponse.class)).toList();
        return new BaseResult<>(true,result);
    }

    @Override
    public BaseResult add(AddCorporateCustomerRequest request) {
        corporateCustomerBusinessRules.isExistCorporateCustomerByName(request.getCompanyName());
        corporateCustomerBusinessRules.isExistUserById(request.getUserId());
        CorporateCustomer corporateCustomer=this.modelMapperService.forRequest()
                .map(request,CorporateCustomer.class);
        corporateCustomer.setId(null);
        this.corporateCustomerRepository.save(corporateCustomer);
        return new BaseResult<>(true, Messages.corporateCustomerAdded);
    }

    @Override
    public BaseResult update(UpdateCorporateCustomerRequest request) {
        corporateCustomerBusinessRules.isExistCorporateCustomerById(request.getId());
        corporateCustomerBusinessRules.isExistUserById(request.getUserId());
        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(request,CorporateCustomer.class);
        this.corporateCustomerRepository.save(corporateCustomer);
        return new BaseResult<>(true, Messages.corporateCustomerUpdated);
    }

    @Override
    public BaseResult delete(Integer id) {
        this.corporateCustomerRepository.deleteById(id);
        return new BaseResult<>(true, Messages.corporateCustomerDeleted);
    }
    @Override
    public boolean isExistCorporateCustomerById(Integer id) {
        return corporateCustomerRepository.existsById(id);
    }
}
