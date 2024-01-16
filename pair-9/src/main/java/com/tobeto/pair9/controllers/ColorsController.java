package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.services.abstracts.ColorService;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@AllArgsConstructor

public class ColorsController {

    private ColorService colorService;

    @GetMapping("/getAll")
    public DataResult<List<GetListColorResponse>> getAll(){
        return colorService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result add(@RequestBody @Valid AddColorRequest request){
        return colorService.add(request);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateColorRequest request){
        return colorService.update(request);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id){
        return colorService.delete(id);
    }



}
