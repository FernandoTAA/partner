package com.github.fernandotaa.partner.test.entrypoint.rest;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverUseCase;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchRequest;
import com.github.fernandotaa.partner.entrypoint.rest.handler.data.Error;
import com.github.fernandotaa.partner.util.JsonUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@DisplayName("Test cases of Web Service \"/v1/partner\"")
public class PartnerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PartnerSaverUseCase partnerSaverUseCase;

    @BeforeAll
    static void beforeEach() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture");
    }

    @Test
    @DisplayName("Success test case")
    void postBatch_success() throws Exception {
        var partnerBatchRequest = Fixture.from(PartnerBatchRequest.class).gimme("valid");

        RequestBuilder requestBuilder = post("/api/v1/partner")
                .content(mapper.writeValueAsString(partnerBatchRequest))
                .contentType("application/json");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @ParameterizedTest(name = "Error test case with fixture template {0}")
    @CsvSource({
            "invalid_pdvs_null",
            "invalid_pdvs_empty",
            "invalid_tradingName_null",
            "invalid_tradingName_empty",
            "invalid_ownerName_null",
            "invalid_ownerName_empty",
            "invalid_document_null",
            "invalid_document_empty",
            "invalid_document_duplicated"
    })
    void postBatch_invalid_error(String fixtureTemplate) throws Exception {
        var partnerBatchRequest = Fixture.from(PartnerBatchRequest.class).gimme(fixtureTemplate);
        var error = Fixture.from(Error.class).gimme(fixtureTemplate);

        RequestBuilder requestBuilder = post("/api/v1/partner")
                .content(mapper.writeValueAsString(partnerBatchRequest))
                .contentType("application/json");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("some fields contain errors")))
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors", hasItems(JsonUtils.jsonPath(error))));
    }
}
