package com.github.fernandotaa.partner.test.entrypoint.rest;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdInputValues;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdOutputValues;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdUseCase;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverUseCase;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerResponse;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.text.MessageFormat;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@DisplayName("Test cases of Web Service [GET] \"/v1/partner/{id}/\"")
public class PartnerControllerMethodGetByIdTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PartnerSaverUseCase partnerSaverUseCase;

    @MockBean
    PartnerGetterByIdUseCase partnerGetterByIdUseCase;

    @BeforeAll
    static void beforeAll() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture");
    }

    @Test
    @DisplayName("Success test case")
    void success() throws Exception {
        PartnerGetterByIdOutputValues output = Fixture.from(PartnerGetterByIdOutputValues.class).gimme("valid");
        var partnerResponse = PartnerResponse.from(output.getPartner().get());
        Mockito.doReturn(output).when(partnerGetterByIdUseCase).execute(ArgumentMatchers.any(PartnerGetterByIdInputValues.class));

        var path = MessageFormat.format("/api/v1/partner/{0}/", partnerResponse.getId());
        RequestBuilder requestBuilder = get(path);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(partnerResponse.getId())))
                .andExpect(jsonPath("$.tradingName", is(partnerResponse.getTradingName())))
                .andExpect(jsonPath("$.ownerName", is(partnerResponse.getOwnerName())))
                .andExpect(jsonPath("$.document", is(partnerResponse.getDocument())));
    }

    @Test
    @DisplayName("Error test case where it do not found partner by id")
    void not_found_error() throws Exception {
        PartnerGetterByIdOutputValues output = Fixture.from(PartnerGetterByIdOutputValues.class).gimme("empty");
        Mockito.doReturn(output).when(partnerGetterByIdUseCase).execute(ArgumentMatchers.any(PartnerGetterByIdInputValues.class));

        var path = MessageFormat.format("/api/v1/partner/{0}/", RandomTestUtils.uuid());
        RequestBuilder requestBuilder = get(path);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("partner not found")));

    }
}
