package com.github.fernandotaa.partner.entrypoint.rest;

import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverUseCase;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Representation of web service that manages domain Partner.
 */
@RestController
@RequestMapping("/api/v1/partner")
@AllArgsConstructor
public class PartnerController {

    private final PartnerSaverUseCase partnerSaverUseCase;

    /**
     * Create a collection of partner.
     *
     * @param partnerBatchRequest Request Payload
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postBatch(@Valid @RequestBody PartnerBatchRequest partnerBatchRequest) {
        var input = new PartnerSaverInputValues(partnerBatchRequest.toEntity());
        partnerSaverUseCase.execute(input);
    }
}

