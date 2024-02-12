package com.tobeto.pair9.services.abstracts;

import com.tobeto.pair9.core.utilities.results.BaseResult;
import com.tobeto.pair9.services.dtos.user.requests.AddUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.UpdateUserRequest;
import com.tobeto.pair9.services.dtos.user.responses.GetListUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    BaseResult<List<GetListUserResponse>> getAll();

    BaseResult add (AddUserRequest request);

    BaseResult update (UpdateUserRequest request);

    BaseResult delete(Integer id);

    boolean isExistUserById(Integer id);

}
