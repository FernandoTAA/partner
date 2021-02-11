package com.github.fernandotaa.partner.entrypoint.rest.data;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.entrypoint.rest.validation.NotRepeatable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representation of Partner to run batch process in requests.
 */
@Getter
@Setter
public class PartnerBatchRequest {
    @Valid
    @NotEmpty
    @NotRepeatable(field = "document")
    private List<PartnerRequest> pdvs;

    /**
     * Convert {@link List} of {@link PartnerRequest} to {@link List} of {@link Partner}.
     *
     * @return - {@link List} of {@link PartnerBase}
     */
    public List<PartnerBase> toEntity() {
        return pdvs.stream()
                .map(PartnerRequest::toEntity)
                .collect(Collectors.toList());
    }
}
