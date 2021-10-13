package modak.camping.modakdata.good;

import lombok.RequiredArgsConstructor;
import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.camping.CampingRepository;
import modak.camping.modakdata.dto.request.CreateGoodRequestDto;
import modak.camping.modakdata.user.User;
import modak.camping.modakdata.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodService {
    private final GoodRepository goodRepository;
    private final UserRepository userRepository;
    private final CampingRepository campingRepository;

    public String save(CreateGoodRequestDto createGoodRequestDto) {
        Optional<User> userOptional = userRepository.findByEmail(createGoodRequestDto.getEmail());
        Optional<Camping> campingOptional = campingRepository.findById(createGoodRequestDto.getContentId());

        if(!userOptional.isPresent() || !campingOptional.isPresent()) throw new IllegalArgumentException();

        goodRepository.save(new Good(userOptional.get(), campingOptional.get()));

        return "ok";
    }
}
