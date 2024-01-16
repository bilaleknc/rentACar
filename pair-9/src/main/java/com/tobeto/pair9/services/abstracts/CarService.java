package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.entities.concretes.Car;
import com.tobeto.pair9.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair9.services.dtos.car.requests.UpdateCarRequest;
import com.tobeto.pair9.services.dtos.car.responses.GetByIdCarResponse;
import com.tobeto.pair9.services.dtos.car.responses.GetListCarResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarService{

    DataResult<List<GetListCarResponse>> getAll();

    DataResult<GetByIdCarResponse> getById(int id);

    Result add(AddCarRequest request);

    Result update(UpdateCarRequest request);

    Result delete(int id);

    boolean existsId(int id);

    void entryCheck(String plate, int modelId, int colorId);
}
