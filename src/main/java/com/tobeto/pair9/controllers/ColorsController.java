package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResponse;
import com.tobeto.pair9.services.abstracts.ColorService;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@AllArgsConstructor
@CrossOrigin
public class ColorsController {

    private ColorService colorService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin:read')")
    public BaseResponse<List<GetListColorResponse>> getAll(){
        return colorService.getAll();
    }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:add')")
    public BaseResponse add(@RequestBody @Valid AddColorRequest request){
        return colorService.add(request);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin:update')")
    public BaseResponse update(@RequestBody @Valid UpdateColorRequest request){
        return colorService.update(request);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public BaseResponse delete(@PathVariable Integer id){
        return colorService.delete(id);
    }

}
