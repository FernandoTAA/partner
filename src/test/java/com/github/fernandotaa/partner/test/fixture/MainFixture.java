package com.github.fernandotaa.partner.test.fixture;

import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * Fixture template loader;
 */
public class MainFixture implements TemplateLoader {
    @Override
    public void load() {
        geoJsonFixture();
        partnerFixture();
        partnerMongoDBFixture();
        partnerRequestFixture();
        partnerBatchRequestFixture();
        inputValuesFixture();
        outputValuesFixture();
        errorFixture();
    }

    private void geoJsonFixture() {
        GeoJsonFixture.loadInCoverageAreaGeoJsonPoint();
        GeoJsonFixture.loadOutOfCoverageAreaGeoJsonPoint();
    }

    private void partnerFixture() {
        PartnerFixture.loadValidPartner();
        PartnerFixture.loadValidPartnerBase();
        PartnerFixture.loadInCoverageAreaPartnerBase();
    }

    private void partnerMongoDBFixture() {
        PartnerMongoDBFixture.loadValidPartner();
        PartnerMongoDBFixture.loadSavedPartner();
    }

    private void partnerRequestFixture() {
        PartnerRequestFixture.loadValidPartnerRequest();
        PartnerRequestFixture.loadInvalidPartnerRequestTradingNameNull();
        PartnerRequestFixture.loadInvalidPartnerRequestTradingNameEmpty();
        PartnerRequestFixture.loadInvalidPartnerRequestOwnerNameNull();
        PartnerRequestFixture.loadInvalidPartnerRequestOwnerNameEmpty();
        PartnerRequestFixture.loadInvalidPartnerRequestDocumentNull();
        PartnerRequestFixture.loadInvalidPartnerRequestDocumentEmpty();
        PartnerRequestFixture.loadInvalidPartnerRequestAddressNull();
        PartnerRequestFixture.loadInvalidPartnerRequestCoverageAreaNull();
    }

    private void partnerBatchRequestFixture() {
        PartnerBatchRequestFixture.loadValidPartnerBatchRequest();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestPdvNull();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestPdvEmpty();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestTradingNameNull();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestTradingNameEmpty();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestOwnerNameNull();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestOwnerNameEmpty();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestDocumentNull();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestDocumentEmpty();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestAddressNull();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestCoverageAreaNull();
        PartnerBatchRequestFixture.loadInvalidPartnerBatchRequestDocumentDuplicated();
    }

    private void inputValuesFixture() {
        InputValuesFixture.loadValidPartnerSaverInputValues();
    }

    private void outputValuesFixture() {
        OutputValuesFixture.loadValidPartnerGetterByIdOutputValues();
        OutputValuesFixture.loadInvalidPartnerGetterByIdOutputValuesEmpty();
    }

    private void errorFixture() {
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestPdvNull();
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestPdvEmpty();
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestTradingNameNull();
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestTradingNameEmpty();
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestOwnerNameNull();
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestOwnerNameEmpty();
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestDocumentNull();
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestAddressNull();
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestCoverageAreaNull();
        ErrorFixture.loadErrorForInvalidPartnerBatchRequestDocumentEmpty();
        ErrorFixture.loadErrorForInvalidPointLatitudeRequired();
        ErrorFixture.loadErrorForInvalidPointLongitudeRequired();
        ErrorFixture.loadErrorForInvalidPointLongitudeMax();
        ErrorFixture.loadErrorForInvalidPointLongitudeMin();
        ErrorFixture.loadErrorForInvalidPointLatitudeMax();
        ErrorFixture.loadErrorForInvaltidPointLatitudeMin();
    }
}
