package com.tobeto.pair9.controllers;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.abstracts.UserService;
import com.tobeto.pair9.services.dtos.user.requests.AddUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.UpdateUserRequest;
import com.tobeto.pair9.services.dtos.user.responses.GetListUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class UsersController {

    private final UserService userService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin:read')")
    public BaseResult<List<GetListUserResponse>> getAll(){
        return userService.getAll();
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:add')")
    public BaseResult add(@RequestBody @Valid AddUserRequest request){
        return userService.add(request);
    }


    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:update')")
    public BaseResult update(@RequestBody @Valid UpdateUserRequest request){
        return userService.update(request);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public BaseResult delete(@PathVariable Integer id){
        return userService.delete(id);
    }
}
