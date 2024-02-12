package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.abstracts.ModelService;
import com.tobeto.pair9.services.dtos.model.requests.AddModelRequest;
import com.tobeto.pair9.services.dtos.model.requests.UpdateModelRequest;
import com.tobeto.pair9.services.dtos.model.responses.GetListModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/models")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class ModelsController {

    private ModelService modelService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin:read')")
    public BaseResult<List<GetListModelResponse>> getAll(){
        return modelService.getAll();
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:add')")
    public void add(@RequestBody @Valid AddModelRequest request){
        modelService.add(request);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin:update')")
    public void update(@RequestBody @Valid UpdateModelRequest request){
        this.modelService.update(request);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void delete(@PathVariable Integer id){
        modelService.delete(id);
    }
}
