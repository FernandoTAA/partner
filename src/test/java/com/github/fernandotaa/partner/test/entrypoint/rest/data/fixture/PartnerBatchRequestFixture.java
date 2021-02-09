package com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchRequest;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerRequest;
import com.github.fernandotaa.partner.entrypoint.rest.handler.data.Error;
import com.github.fernandotaa.partner.util.RandomUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.text.MessageFormat;
import java.util.List;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;
import static com.github.fernandotaa.partner.util.RandomUtils.integer;

/**
 * Fixture template loader for PartnerBatchRequest to use in Partner Tests;
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
        loadErrorForInvalidPartnerBatchRequestDocumentEmpty();
        loadInvalidPartnerBatchRequestDocumentDuplicated();
    }

    private void loadValidPartnerRequest() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("valid", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomUtils::document));
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
                    add("document", function(RandomUtils::document));
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
                    add("document", function(RandomUtils::document));
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
                    add("document", function(RandomUtils::document));
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
                    add("document", function(RandomUtils::document));
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
