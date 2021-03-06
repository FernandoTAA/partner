package com.github.fernandotaa.partner.test.gateway.repository;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.gateway.repository.PartnerGatewayRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(classes = PartnerGatewayRepository.class)
@DisplayName("Test cases of PartnerGatewayRepository and method save")
public class PartnerGatewayRepositoryMethodSaveTest {
    @Autowired
    PartnerGatewayRepository partnerGatewayRepository;

    @MockBean
    PartnerMongoDBRepository partnerMongoDBRepository;

    @MockBean
    MongoTemplate mongoTemplate;

    @BeforeAll
    static void beforeAll() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.fixture");
    }

    @BeforeEach
    void beforeEach() {
        when(partnerMongoDBRepository.save(any(PartnerMongoDB.class))).thenAnswer(PartnerGatewayRepositoryMethodSaveTest::mockSave);
    }

    @SneakyThrows
    private static Object mockSave(InvocationOnMock invocationOnMock) {
        var partner = BeanUtils.cloneBean(invocationOnMock.getArgument(0));
        FieldUtils.writeDeclaredField(partner, "id", RandomTestUtils.uuid(), true);
        return partner;
    }

    @Test
    @DisplayName("Success test case")
    void success() {
        PartnerBase partner = Fixture.from(PartnerBase.class).gimme("valid");
        var id = partnerGatewayRepository.save(partner);
        assertThat(id).isNotNull();
    }
}
