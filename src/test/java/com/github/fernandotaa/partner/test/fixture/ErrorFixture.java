package com.github.fernandotaa.partner.test.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchRequest;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerRequest;
import com.github.fernandotaa.partner.entrypoint.rest.handler.data.Error;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import java.text.MessageFormat;
import java.util.List;

import static com.github.fernandotaa.partner.util.RandomTestUtils.integer;

/**
 * Fixture template loader for {@link PartnerBatchRequest} to use in Partner Tests;
 */
public class ErrorFixture implements TemplateLoader {
    @Override
    public void load() {
        loadErrorForInvalidPartnerBatchRequestPdvNull();
        loadErrorForInvalidPartnerBatchRequestPdvEmpty();
        loadErrorForInvalidPartnerBatchRequestTradingNameNull();
        loadErrorForInvalidPartnerBatchRequestTradingNameEmpty();
        loadErrorForInvalidPartnerBatchRequestOwnerNameNull();
        loadErrorForInvalidPartnerBatchRequestOwnerNameEmpty();
        loadErrorForInvalidPartnerBatchRequestDocumentNull();
        loadErrorForInvalidPartnerBatchRequestAddressNull();
        loadErrorForInvalidPartnerBatchRequestCoverageAreaNull();
        loadErrorForInvalidPartnerBatchRequestDocumentEmpty();
        loadErrorForInvalidPointLatitudeRequired();
        loadErrorForInvalidPointLongitudeRequired();
        loadErrorForInvalidPointLongitudeMax();
        loadErrorForInvalidPointLongitudeMin();
        loadErrorForInvalidPointLatitudeMax();
        loadErrorForInvalidPointLatitudeMin();
    }

    private void loadErrorForInvalidPartnerBatchRequestPdvNull() {
        Fixture.of(Error.class)
                .addTemplate("invalid_pdvs_null", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs");
                    add("value", "null");
                    add("message", "must not be empty");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestPdvEmpty() {
        Fixture.of(Error.class)
                .addTemplate("invalid_pdvs_empty", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs");
                    add("value", "[]");
                    add("message", "must not be empty");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestTradingNameNull() {
        Fixture.of(Error.class)
                .addTemplate("invalid_ownerName_null", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs[0].tradingName");
                    add("value", "null");
                    add("message", "must not be blank");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestTradingNameEmpty() {
        Fixture.of(Error.class)
                .addTemplate("invalid_ownerName_empty", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs[0].tradingName");
                    add("value", "");
                    add("message", "must not be blank");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestOwnerNameNull() {
        Fixture.of(Error.class)
                .addTemplate("invalid_tradingName_null", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs[0].ownerName");
                    add("value", "null");
                    add("message", "must not be blank");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestOwnerNameEmpty() {
        Fixture.of(Error.class)
                .addTemplate("invalid_tradingName_empty", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs[0].ownerName");
                    add("value", "");
                    add("message", "must not be blank");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestDocumentNull() {
        Fixture.of(Error.class)
                .addTemplate("invalid_document_null", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs[0].document");
                    add("value", "null");
                    add("message", "must not be blank");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestDocumentEmpty() {
        Fixture.of(Error.class)
                .addTemplate("invalid_document_empty", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs[0].document");
                    add("value", "");
                    add("message", "must not be blank");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestAddressNull() {
        Fixture.of(Error.class)
                .addTemplate("invalid_address_null", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs[0].address");
                    add("value", "null");
                    add("message", "must not be null");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestCoverageAreaNull() {
        Fixture.of(Error.class)
                .addTemplate("invalid_coverageArea_null", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs[0].coverageArea");
                    add("value", "null");
                    add("message", "must not be null");
                }});
    }

    private void loadErrorForInvalidPointLatitudeRequired() {
        Fixture.of(Error.class)
                .addTemplate("invalid_latitude_required", new Rule() {{
                    add("scope", "path");
                    add("field", "latitude");
                    add("message", "latitude parameter is missing");
                }});
    }

    private void loadErrorForInvalidPointLongitudeMax() {
        Fixture.of(Error.class)
                .addTemplate("invalid_longitude_max", new Rule() {{
                    add("scope", "path");
                    add("field", "getByPoint.longitude");
                    add("value", "180.1");
                    add("message", "must be less than or equal to 180");
                }});
    }

    private void loadErrorForInvalidPointLongitudeMin() {
        Fixture.of(Error.class)
                .addTemplate("invalid_longitude_min", new Rule() {{
                    add("scope", "path");
                    add("field", "getByPoint.longitude");
                    add("value", "-180.1");
                    add("message", "must be greater than or equal to -180");
                }});
    }

    private void loadErrorForInvalidPointLongitudeRequired() {
        Fixture.of(Error.class)
                .addTemplate("invalid_longitude_required", new Rule() {{
                    add("scope", "path");
                    add("field", "longitude");
                    add("message", "longitude parameter is missing");
                }});
    }

    private void loadErrorForInvalidPointLatitudeMax() {
        Fixture.of(Error.class)
                .addTemplate("invalid_latitude_max", new Rule() {{
                    add("scope", "path");
                    add("field", "getByPoint.latitude");
                    add("value", "90.1");
                    add("message", "must be less than or equal to 90");
                }});
    }

    private void loadErrorForInvalidPointLatitudeMin() {
        Fixture.of(Error.class)
                .addTemplate("invalid_latitude_min", new Rule() {{
                    add("scope", "path");
                    add("field", "getByPoint.latitude");
                    add("value", "-90.1");
                    add("message", "must be greater than or equal to -90");
                }});
    }

    private void loadErrorForInvalidPartnerBatchRequestDocumentDuplicated(String document, List<Object> pdvs) {
        var message = MessageFormat.format("must not be repeatable values: {0}", document);
        Fixture.of(Error.class)
                .addTemplate("invalid_document_duplicated", new Rule() {{
                    add("scope", "attribute");
                    add("field", "pdvs");
                    add("value", pdvs.toString());
                    add("message", message);
                }});
    }
}
