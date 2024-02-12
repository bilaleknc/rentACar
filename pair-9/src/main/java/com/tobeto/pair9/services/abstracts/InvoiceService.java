package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.dtos.invoice.requests.AddInvoiceRequest;
import com.tobeto.pair9.services.dtos.invoice.requests.UpdateInvoiceRequest;
import com.tobeto.pair9.services.dtos.invoice.responses.GetListInvoiceResponse;

import java.util.List;

public interface InvoiceService {

    BaseResult<List<GetListInvoiceResponse>> getAll();

    BaseResult add(AddInvoiceRequest request);

    BaseResult update(UpdateInvoiceRequest request);

    BaseResult delete(Integer id);

    boolean isExistInvoiceById(Integer id);

}
