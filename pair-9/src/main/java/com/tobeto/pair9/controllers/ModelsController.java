package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.abstracts.ModelService;
import com.tobeto.pair9.services.dtos.model.requests.AddModelRequest;
import com.tobeto.pair9.services.dtos.model.requests.UpdateModelRequest;
import com.tobeto.pair9.services.dtos.model.responses.GetListModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/models")
@CrossOrigin
public class ModelsController {

    private ModelService modelService;

    @GetMapping("/getAll")
    public BaseResponse<List<GetListModelResponse>> getAll(){
        return modelService.getAll();
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public BaseResponse add(@RequestBody @Valid AddModelRequest request){
        return modelService.add(request);
    }


    @PutMapping("/update")
    public BaseResponse update(@RequestBody @Valid UpdateModelRequest request){
        return this.modelService.update(request);
    }


    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable Integer id){
        return modelService.delete(id);
    }
}
