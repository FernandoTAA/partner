package com.github.fernandotaa.partner.test.fixture;

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
 * Static method to use in {@link MainFixture} for {@link PartnerBatchRequest} to use in Partner Tests;
 */
public class PartnerBatchRequestFixture {
    static void loadValidPartnerBatchRequest() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("valid", new Rule() {{
                    add("pdvs", function(() -> Fixture.from(PartnerRequest.class).gimme(integer(1, 20), "valid")));
                }});
    }

    static void loadInvalidPartnerBatchRequestPdvNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_pdvs_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", null);
                }});
    }

    static void loadInvalidPartnerBatchRequestPdvEmpty() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_pdvs_empty", new Rule() {{
                    final int bound = 20;
                    add("pdvs", List.of());
                }});
    }

    static void loadInvalidPartnerBatchRequestTradingNameNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_ownerName_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", function(() -> Fixture.from(PartnerRequest.class).gimme(1, "invalid_ownerName_null")));
                }});
    }

    static void loadInvalidPartnerBatchRequestTradingNameEmpty() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_ownerName_empty", new Rule() {{
                    final int bound = 20;
                    add("pdvs", function(() -> Fixture.from(PartnerRequest.class).gimme(1, "invalid_ownerName_empty")));
                }});
    }

    static void loadInvalidPartnerBatchRequestOwnerNameNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_tradingName_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", function(() -> Fixture.from(PartnerRequest.class).gimme(1, "invalid_tradingName_null")));
                }});
    }

    static void loadInvalidPartnerBatchRequestOwnerNameEmpty() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_tradingName_empty", new Rule() {{
                    final int bound = 20;
                    add("pdvs", function(() -> Fixture.from(PartnerRequest.class).gimme(1, "invalid_tradingName_empty")));
                }});
    }

    static void loadInvalidPartnerBatchRequestDocumentNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_document_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", function(() -> Fixture.from(PartnerRequest.class).gimme(1, "invalid_document_null")));
                }});
    }

    static void loadInvalidPartnerBatchRequestDocumentEmpty() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_document_empty", new Rule() {{
                    final int bound = 20;
                    add("pdvs", function(() -> Fixture.from(PartnerRequest.class).gimme(1, "invalid_document_empty")));
                }});
    }

    static void loadInvalidPartnerBatchRequestAddressNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_address_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", function(() -> Fixture.from(PartnerRequest.class).gimme(1, "invalid_address_null")));
                }});
    }

    static void loadInvalidPartnerBatchRequestCoverageAreaNull() {
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_coverageArea_null", new Rule() {{
                    final int bound = 20;
                    add("pdvs", function(() -> Fixture.from(PartnerRequest.class).gimme(1, "invalid_coverageArea_null")));
                }});
    }

    @SneakyThrows
    static void loadInvalidPartnerBatchRequestDocumentDuplicated() {
        PartnerRequest partner = Fixture.from(PartnerRequest.class).gimme("valid");
        var pdvs = List.of(BeanUtils.cloneBean(partner), BeanUtils.cloneBean(partner));
        Fixture.of(PartnerBatchRequest.class)
                .addTemplate("invalid_document_duplicated", new Rule() {{
                    final int bound = 20;
                    add("pdvs", pdvs);
                }});
        loadErrorForInvalidPartnerBatchRequestDocumentDuplicated(partner.getDocument(), pdvs);
    }

    private static void loadErrorForInvalidPartnerBatchRequestDocumentDuplicated(String document, List<Object> pdvs) {
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
