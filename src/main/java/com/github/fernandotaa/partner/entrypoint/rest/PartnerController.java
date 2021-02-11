package com.github.fernandotaa.partner.entrypoint.rest;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdInputValues;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdOutputValues;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdUseCase;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverUseCase;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchRequest;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchResponse;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Representation of web service that manages domain Partner.
 */
@RestController
@RequestMapping("/api/v1/partner")
@AllArgsConstructor
public class PartnerController {

    private final PartnerSaverUseCase partnerSaverUseCase;
    private final PartnerGetterByIdUseCase partnerGetterByIdUseCase;

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

    @GetMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public PartnerResponse getById(@PathVariable String id) {
        var input = new PartnerGetterByIdInputValues(id);
        var output = partnerGetterByIdUseCase.execute(input);
        var partnerOptional = output.getPartner();
        if (partnerOptional.isEmpty()) {
            //todo : throw notfound
        }
        return PartnerResponse.from(partnerOptional.get());
    }
}

