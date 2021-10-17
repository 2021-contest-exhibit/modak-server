package modak.camping.modakdata.dto.mapper;

import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.dto.response.CampingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampingDtoMapper {

    public Page<CampingResponseDto> CampingToResponse(Page<Camping> campingPage) {

        List<Camping> campingList = campingPage.getContent();

        List<CampingResponseDto> result = campingList.stream()
                .map(camping ->
                        new CampingResponseDto(
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
                                new Long(camping.getGoods().size()),
                                camping.getEnvironments(),
                                camping.getCampingImages()
                        )
                )
                .collect(Collectors.toList());

        return new PageImpl<>(result, campingPage.getPageable(), result.size());
    }
}
