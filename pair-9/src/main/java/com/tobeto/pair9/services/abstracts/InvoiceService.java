package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.dtos.invoice.requests.AddInvoiceRequest;
import com.tobeto.pair9.services.dtos.invoice.requests.UpdateInvoiceRequest;
import com.tobeto.pair9.services.dtos.invoice.responses.GetListInvoiceResponse;

import java.util.List;

public interface InvoiceService {

    BaseResponse<List<GetListInvoiceResponse>> getAll();

    BaseResponse add(AddInvoiceRequest request);

    BaseResponse update(UpdateInvoiceRequest request);

    BaseResponse delete(Integer id);

    boolean isExistInvoiceById(Integer id);

}
