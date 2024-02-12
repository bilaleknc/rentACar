package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.*;
import com.tobeto.pair9.entities.concretes.User;
import com.tobeto.pair9.repositories.UserRepository;
import com.tobeto.pair9.services.abstracts.UserService;
import com.tobeto.pair9.services.dtos.user.requests.AddUserRequest;
import com.tobeto.pair9.services.dtos.user.requests.UpdateUserRequest;
import com.tobeto.pair9.services.dtos.user.responses.GetListUserResponse;
import com.tobeto.pair9.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;
    private final ModelMapperService modelMapperService;

    @Override
    public BaseResult<List<GetListUserResponse>> getAll() {
        List<User> users = userRepository.findAll();
        var result = users.stream()
                .map(user -> this.modelMapperService.forResponse().map(user, GetListUserResponse.class)).toList();
        return new BaseResult<>(true,result);
    }

    @Override
    public BaseResult add(AddUserRequest request) {
        userBusinessRules.isExistUserByEmail(request.getEmail());
        User user = this.modelMapperService.forRequest().map(request,User.class);
        user.setId(null);
        this.userRepository.save(user);
        return new BaseResult<>(true, Messages.userAdded);
    }

    @Override
    public BaseResult update(UpdateUserRequest request) {
        userBusinessRules.findUserById(request.getId());
        User user = this.modelMapperService.forRequest().map(request,User.class);
        this.userRepository.save(user);
        return new BaseResult<>(true, Messages.userUpdated);
    }

    @Override
    public BaseResult delete(Integer id) {
        this.userRepository.deleteById(id);
        return  new BaseResult<>(true, Messages.userDeleted);
    }

    @Override
    public boolean isExistUserById(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(Messages.userIsNotFound));
    }
}
