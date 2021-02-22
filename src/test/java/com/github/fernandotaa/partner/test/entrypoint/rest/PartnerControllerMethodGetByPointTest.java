package com.github.fernandotaa.partner.test.entrypoint.rest;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.usecase.PartnerGetterPartnerOutputValues;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdUseCase;
import com.github.fernandotaa.partner.core.usecase.getterbypoint.PartnerGetterByPointInputValues;
import com.github.fernandotaa.partner.core.usecase.getterbypoint.PartnerGetterByPointUseCase;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverUseCase;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerResponse;
import com.github.fernandotaa.partner.entrypoint.rest.handler.data.Error;
import com.github.fernandotaa.partner.util.JsonUtils;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@DisplayName("Test cases of Web Service [GET] \"/v1/partner/\"")
public class PartnerControllerMethodGetByPointTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PartnerSaverUseCase partnerSaverUseCase;

    @MockBean
    PartnerGetterByIdUseCase partnerGetterByIdUseCase;

    @MockBean
    PartnerGetterByPointUseCase partnerGetterByPointUseCase;

    @BeforeAll
    static void beforeAll() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.fixture");
    }

    @Test
    @DisplayName("Success test case")
    void success() throws Exception {
        PartnerGetterPartnerOutputValues output = Fixture.from(PartnerGetterPartnerOutputValues.class).gimme("valid");
        var partnerResponse = PartnerResponse.from(output.getPartner().get());
        doReturn(output).when(partnerGetterByPointUseCase).execute(ArgumentMatchers.any(PartnerGetterByPointInputValues.class));

        final List<Double> point = RandomTestUtils.point().getCoordinates();
        var path = MessageFormat.format("/api/v1/partner/?longitude={0}&latitude={1}", point.get(0).toString(), point.get(1).toString());
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
    @DisplayName("Error test case where it do not found partner by point")
    void not_found_error() throws Exception {
        PartnerGetterPartnerOutputValues output = Fixture.from(PartnerGetterPartnerOutputValues.class).gimme("empty");
        doReturn(output).when(partnerGetterByPointUseCase).execute(ArgumentMatchers.any(PartnerGetterByPointInputValues.class));

        final List<Double> point = RandomTestUtils.point().getCoordinates();
        var path = MessageFormat.format("/api/v1/partner/?longitude={0}&latitude={1}", point.get(0).toString(), point.get(1).toString());
        RequestBuilder requestBuilder = get(path);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());

    }

    @ParameterizedTest(name = "Error test case where path is equals to \"{0}\" and fixtureErrorTemplate equals to \"{1}\"")
    @MethodSource("validation_error_provider")
    void validation_error(String path, String fixtureErrorTemplate) throws Exception {
        var error = Fixture.from(Error.class).gimme(fixtureErrorTemplate);

        final List<Double> point = RandomTestUtils.point().getCoordinates();
        RequestBuilder requestBuilder = get(path);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("some fields contain errors")))
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors", hasItems(JsonUtils.jsonPath(error))));

    }

    private static Stream<Arguments> validation_error_provider() {
        final List<Double> point = RandomTestUtils.point().getCoordinates();
        return Stream.of(
                Arguments.of(MessageFormat.format("/api/v1/partner/?longitude={0}", point.get(0).toString()), "invalid_latitude_required"),
                Arguments.of(MessageFormat.format("/api/v1/partner/?latitude={0}", point.get(1).toString()), "invalid_longitude_required"),
                Arguments.of(MessageFormat.format("/api/v1/partner/?longitude={0}&latitude={1}", String.valueOf(180.1D), point.get(1).toString()), "invalid_longitude_max"),
                Arguments.of(MessageFormat.format("/api/v1/partner/?longitude={0}&latitude={1}", String.valueOf(-180.1D), point.get(1).toString()), "invalid_longitude_min"),
                Arguments.of(MessageFormat.format("/api/v1/partner/?longitude={0}&latitude={1}", point.get(0).toString(), String.valueOf(90.1D)), "invalid_latitude_max"),
                Arguments.of(MessageFormat.format("/api/v1/partner/?longitude={0}&latitude={1}", point.get(0).toString(), String.valueOf(-90.1D)), "invalid_latitude_min")
        );
    }
}
