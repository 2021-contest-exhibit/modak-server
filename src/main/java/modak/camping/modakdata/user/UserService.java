package modak.camping.modakdata.user;

import lombok.RequiredArgsConstructor;
import modak.camping.modakdata.request.CreateUserRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String save(CreateUserRequestDto createUserRequestDto) {
        userRepository.save(new User(createUserRequestDto.getEmail()));
        return "ok";
    }

    public Page<User> findPageByEmail(String email) {
        return userRepository.findAll(email);
    }
}
