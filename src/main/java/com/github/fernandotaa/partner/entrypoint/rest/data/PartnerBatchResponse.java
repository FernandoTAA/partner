package com.github.fernandotaa.partner.entrypoint.rest.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Representation of a unique Partner in response.
 */
@Getter
@AllArgsConstructor
public class PartnerBatchResponse {
    private List<String> ids;
}
