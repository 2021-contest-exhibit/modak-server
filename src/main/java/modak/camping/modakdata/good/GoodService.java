package modak.camping.modakdata.good;

import lombok.RequiredArgsConstructor;
import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.camping.CampingRepository;
import modak.camping.modakdata.dto.request.CreateGoodRequestDto;
import modak.camping.modakdata.dto.request.DeleteGoodRequestDto;
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

        System.out.println("createGoodRequestDto.toString() = " + createGoodRequestDto.toString());

        Optional<User> userOptional = userRepository.findByEmail(createGoodRequestDto.getEmail());
        Optional<Camping> campingOptional = campingRepository.findById(createGoodRequestDto.getContentId());


        if(!userOptional.isPresent() || !campingOptional.isPresent()) throw new IllegalArgumentException();

        User user = userOptional.get();
        Camping camping = campingOptional.get();

        Optional<Good> goodOptional = goodRepository.findByUserAndCamping(user, camping);

        if(goodOptional.isPresent()) {
            return "이미 존재하는 좋아요 입니다.";
        } else {
            goodRepository.save(new Good(user, camping));
            return "ok";
        }
    }

    public String delete(DeleteGoodRequestDto deleteGoodRequestDto) {
        Optional<User> userOptional = userRepository.findByEmail(deleteGoodRequestDto.getEmail());
        Optional<Camping> campingOptional = campingRepository.findById(deleteGoodRequestDto.getContentId());

        if(!userOptional.isPresent() || !campingOptional.isPresent()) throw new IllegalArgumentException();

        Long resultCount = goodRepository.deleteByUserAndCamping(userOptional.get(), campingOptional.get());

        if(resultCount == 0L) {
            return "이미 삭제됐거나 없는 좋아요 데이터 입니다.";
        } else {
            return "ok";
        }
    }

}
