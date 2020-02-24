package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtlPackagesMapperTest {

    private EtlPackagesMapper etlPackagesMapper;

    @BeforeEach
    public void setUp() {
        etlPackagesMapper = new EtlPackagesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etlPackagesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etlPackagesMapper.fromId(null)).isNull();
    }
}
