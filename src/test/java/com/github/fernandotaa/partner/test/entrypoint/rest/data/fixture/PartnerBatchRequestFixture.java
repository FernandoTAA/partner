package com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchRequest;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerRequest;
import com.github.fernandotaa.partner.entrypoint.rest.handler.data.Error;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import java.text.MessageFormat;
import java.util.List;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;
import static com.github.fernandotaa.partner.util.RandomTestUtils.integer;

/**
 * Fixture template loader for {@link PartnerBatchRequest} to use in Partner Tests;
 */
public class PartnerBatchRequestFixture implements TemplateLoader {
    @Override
    public void load() {
        loadValidPartnerRequest();
        loadValidPartnerBatchRequest();
        loadInvalidPartnerBatchRequestPdvNull();
        loadErrorForInvalidPartnerBatchRequestPdvNull();
        loadInvalidPartnerBatchRequestPdvEmpty();
        loadErrorForInvalidPartnerBatchRequestPdvEmpty();
        loadInvalidPartnerRequestTradingNameNull();
        loadInvalidPartnerBatchRequestTradingNameNull();
        loadErrorForInvalidPartnerBatchRequestTradingNameNull();
        loadInvalidPartnerRequestTradingNameEmpty();
        loadInvalidPartnerBatchRequestTradingNameEmpty();
        loadErrorForInvalidPartnerBatchRequestTradingNameEmpty();
        loadInvalidPartnerRequestOwnerNameNull();
        loadInvalidPartnerBatchRequestOwnerNameNull();
        loadErrorForInvalidPartnerBatchRequestOwnerNameNull();
        loadInvalidPartnerRequestOwnerNameEmpty();
        loadInvalidPartnerBatchRequestOwnerNameEmpty();
        loadErrorForInvalidPartnerBatchRequestOwnerNameEmpty();
        loadInvalidPartnerRequestDocumentNull();
        loadInvalidPartnerBatchRequestDocumentNull();
        loadErrorForInvalidPartnerBatchRequestDocumentNull();
        loadInvalidPartnerRequestDocumentEmpty();
        loadInvalidPartnerBatchRequestDocumentEmpty();
        loadInvalidPartnerRequestAddressNull();
        loadInvalidPartnerBatchRequestAddressNull();
        loadErrorForInvalidPartnerBatchRequestAddressNull();
        loadInvalidPartnerRequestCoverageAreaNull();
        loadInvalidPartnerBatchRequestCoverageAreaNull();
        loadErrorForInvalidPartnerBatchRequestCoverageAreaNull();
        loadErrorForInvalidPartnerBatchRequestDocumentEmpty();
        loadInvalidPartnerBatchRequestDocumentDuplicated();
        loadErrorForInvalidPointLatitudeRequired();
        loadErrorForInvalidPointLongitudeRequired();
        loadErrorForInvalidPointLongitudeMax();
        loadErrorForInvalidPointLongitudeMin();
        loadErrorForInvalidPointLatitudeMax();
        loadErrorForInvalidPointLatitudeMin();
    }

    private void loadValidPartnerRequest() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("valid", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    private void loadValidPartnerBatchRequest() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("valid", new Rule() {{
                    add("pdvs", Fixture.from(PartnerRequest.class).gimme(integer(1, 20), "valid"));
                }});
    }

    private void loadInvalidPartnerBatchRequestPdvNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_pdvs_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", null);
                }});
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

    private void loadInvalidPartnerBatchRequestPdvEmpty() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_pdvs_empty", new Rule() {{
                    final int bound = 20;
                    add("pdvs", List.of());
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

    private void loadInvalidPartnerRequestTradingNameNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_ownerName_null", new Rule() {{
                    add("tradingName", null);
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    private void loadInvalidPartnerBatchRequestTradingNameNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_ownerName_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", Fixture.from(PartnerRequest.class).gimme(1, "invalid_ownerName_null"));
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

    private void loadInvalidPartnerRequestTradingNameEmpty() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_ownerName_empty", new Rule() {{
                    add("tradingName", "");
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    private void loadInvalidPartnerBatchRequestTradingNameEmpty() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_ownerName_empty", new Rule() {{
                    final int bound = 20;
                    add("pdvs", Fixture.from(PartnerRequest.class).gimme(1, "invalid_ownerName_empty"));
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

    private void loadInvalidPartnerRequestOwnerNameNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_tradingName_null", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", null);
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    private void loadInvalidPartnerBatchRequestOwnerNameNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_tradingName_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", Fixture.from(PartnerRequest.class).gimme(1, "invalid_tradingName_null"));
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

    private void loadInvalidPartnerRequestOwnerNameEmpty() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_tradingName_empty", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", "");
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    private void loadInvalidPartnerBatchRequestOwnerNameEmpty() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_tradingName_empty", new Rule() {{
                    final int bound = 20;
                    add("pdvs", Fixture.from(PartnerRequest.class).gimme(1, "invalid_tradingName_empty"));
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

    private void loadInvalidPartnerRequestDocumentNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_document_null", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", null);
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    private void loadInvalidPartnerBatchRequestDocumentNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_document_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", Fixture.from(PartnerRequest.class).gimme(1, "invalid_document_null"));
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

    private void loadInvalidPartnerRequestDocumentEmpty() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_document_empty", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", "");
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    private void loadInvalidPartnerBatchRequestDocumentEmpty() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_document_empty", new Rule() {{
                    final int bound = 20;
                    add("pdvs", Fixture.from(PartnerRequest.class).gimme(1, "invalid_document_empty"));
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

    private void loadInvalidPartnerRequestAddressNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_address_null", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(Faker.instance().name()::name));
                    add("address", null);
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    private void loadInvalidPartnerBatchRequestAddressNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_address_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", Fixture.from(PartnerRequest.class).gimme(1, "invalid_address_null"));
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

    private void loadInvalidPartnerRequestCoverageAreaNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_coverageArea_null", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(Faker.instance().name()::name));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", null);
                }});
    }

    private void loadInvalidPartnerBatchRequestCoverageAreaNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_coverageArea_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", Fixture.from(PartnerRequest.class).gimme(1, "invalid_coverageArea_null"));
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

    @SneakyThrows
    private void loadInvalidPartnerBatchRequestDocumentDuplicated() {
        PartnerRequest partner = Fixture.from(PartnerRequest.class).gimme("valid");
        var pdvs = List.of(BeanUtils.cloneBean(partner), BeanUtils.cloneBean(partner));
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_document_duplicated", new Rule() {{
                    final int bound = 20;
                    add("pdvs", pdvs);
                }});
        loadErrorForInvalidPartnerBatchRequestDocumentDuplicated(partner.getDocument(), pdvs);
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
