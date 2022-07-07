package com.kaamcube.truelysell.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kaamcube.truelysell.model.request.dto.AvailabilityRequestDto;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvailabilityRequest {
    List<AvailabilityRequestDto> availabilityRequestDtos;
}
