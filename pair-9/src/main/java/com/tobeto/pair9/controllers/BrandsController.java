package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.services.abstracts.BrandService;
import com.tobeto.pair9.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair9.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/brands")
public class BrandsController {

    private BrandService brandService;

    @GetMapping("/getAll")
    public DataResult<List<GetListBrandResponse>> getAll(){
        return brandService.getAll();
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result add(@RequestBody @Valid AddBrandRequest request){
        return brandService.add(request);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateBrandRequest request){
        return this.brandService.update(request);
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id){
        return brandService.delete(id);
    }

}
