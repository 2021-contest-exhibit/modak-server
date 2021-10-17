package modak.camping.modakdata.dto.mapper;

import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.dto.response.CampingResponseDto;
import modak.camping.modakdata.good.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CampingDtoMapper {

    public Page<CampingResponseDto> CampingToResponse(Page<Camping> campingPage, String email) {

        List<Camping> campingList = campingPage.getContent();

        List<CampingResponseDto> result = campingList.stream()
                .map(camping -> {

                    Set<String> emailSetInGood = camping.getGoods().stream()
                            .map(good -> good.getUser().getEmail())
                            .collect(Collectors.toSet());

                    return new CampingResponseDto(
                            camping.getContentId(),
                            camping.getName(),
                            camping.getViewCount(),
                            camping.getAddr(),
                            camping.getPhoneNumber(),
                            camping.getType(),
                            camping.getOperationSeasons(),
                            camping.getOperationDays(),
                            camping.getReservationWay(),
                            camping.getNearbyFacilitiesAvailable(),
                            camping.getFacilities(),
                            camping.getLongitude(),
                            camping.getLatitude(),
                            camping.getOperationType(),
                            camping.getShortDescription(),
                            camping.getLongDescription(),
                            camping.getThumbnailImageUrl(),
                            camping.getEnvironments(),
                            camping.getCampingImages(),
                            new Long(camping.getGoods().size()),
                            emailSetInGood.contains(email) );
                        }
                )
                .collect(Collectors.toList());


        return new PageImpl<>(result, campingPage.getPageable(), result.size());
    }
}
