package com.github.fernandotaa.partner.entrypoint.rest;

import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdInputValues;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdUseCase;
import com.github.fernandotaa.partner.core.usecase.getterbypoint.PartnerGetterByPointInputValues;
import com.github.fernandotaa.partner.core.usecase.getterbypoint.PartnerGetterByPointUseCase;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverUseCase;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchRequest;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchResponse;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerResponse;
import com.github.fernandotaa.partner.entrypoint.rest.exception.NotFoundException;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Representation of web service that manages domain Partner.
 */
@RestController
@Validated
@RequestMapping("/api/v1/partner")
@AllArgsConstructor
public class PartnerController {

    private final PartnerSaverUseCase partnerSaverUseCase;
    private final PartnerGetterByIdUseCase partnerGetterByIdUseCase;
    private final PartnerGetterByPointUseCase partnerGetterByPointUseCase;

    /**
     * Create a collection of partner.
     *
     * @param partnerBatchRequest Request Payload
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PartnerBatchResponse postBatch(@Valid @RequestBody PartnerBatchRequest partnerBatchRequest) {
        var input = new PartnerSaverInputValues(partnerBatchRequest.toEntity());
        var output = partnerSaverUseCase.execute(input);
        return new PartnerBatchResponse(output.getPartnerIds());
    }

    /**
     * Get {@link PartnerResponse} by identification.
     *
     * @param id - Identification
     * @return - {@link PartnerResponse}
     */
    @GetMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public PartnerResponse getById(@PathVariable String id) {
        var input = new PartnerGetterByIdInputValues(id);
        var output = partnerGetterByIdUseCase.execute(input);
        var partnerOptional = output.getPartner();
        if (partnerOptional.isEmpty()) {
            throw new NotFoundException("partner not found");
        }
        return PartnerResponse.from(partnerOptional.get());
    }

    /**
     * Get {@link PartnerResponse} by longitude and latitude.
     *
     * @param longitude
     * @param latitude
     * @return - {@link PartnerResponse}
     */
    @GetMapping
    public ResponseEntity<PartnerResponse> getByPoint(@RequestParam @Valid @Min(-180L) @Max(180L) Double longitude, @RequestParam @Valid @Min(-90L) @Max(90L) Double latitude) {
        var point = new GeoJsonPoint(longitude, latitude);
        var input = new PartnerGetterByPointInputValues(point);
        var output = partnerGetterByPointUseCase.execute(input);
        if (output.getPartner().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        var partner = output.getPartner().get();
        var partnerResponse = PartnerResponse.from(partner);
        return ResponseEntity.ok().body(partnerResponse);
    }
}
