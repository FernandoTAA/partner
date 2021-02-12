package com.github.fernandotaa.partner.gateway.repository.mongodb.data;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representation of Partner in MongoDB.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("partner")
public class PartnerMongoDB {
    @Id
    private String id;
    private String tradingName;
    private String ownerName;
    private String document;

    public static PartnerMongoDB from(PartnerBase partner) {
        return new PartnerMongoDB(
                null,
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument()
        );
    }

    public static Partner toEntity(PartnerMongoDB partner) {
        return new Partner(
                partner.getId(),
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument()
        );
    }
}
