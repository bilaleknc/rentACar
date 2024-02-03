package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.core.utilities.results.SuccessDataResult;
import com.tobeto.pair9.core.utilities.results.SuccessResult;
import com.tobeto.pair9.entities.concretes.CorporateCustomer;
import com.tobeto.pair9.repositories.CorporateCustomerRepository;
import com.tobeto.pair9.services.abstracts.CorporateCustomerService;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.AddCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.requests.UpdateCorporateCustomerRequest;
import com.tobeto.pair9.services.dtos.corporateCustomer.responses.GetListCorporateCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetListCorporateCustomerResponse>> getAll() {
        List<CorporateCustomer> corporateCustomers = corporateCustomerRepository.findAll();
        return new SuccessDataResult(corporateCustomers.stream().map(corporateCustomer -> this.modelMapperService.forResponse()
                .map(corporateCustomer, GetListCorporateCustomerResponse.class)).collect(Collectors.toList()),"Tüm data getirildi");
    }

    @Override
    public Result add(AddCorporateCustomerRequest request) {
        CorporateCustomer corporateCustomer=this.modelMapperService.forRequest()
                .map(request,CorporateCustomer.class);
        this.corporateCustomerRepository.save(corporateCustomer);
        return new SuccessResult("Eklendi");
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest request) {
        return new SuccessResult("Güncelledi");
    }

    @Override
    public Result delete(int id) {
        return new SuccessResult("Silindi");
    }
}
