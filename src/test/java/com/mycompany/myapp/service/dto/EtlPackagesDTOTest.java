package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EtlPackagesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtlPackagesDTO.class);
        EtlPackagesDTO etlPackagesDTO1 = new EtlPackagesDTO();
        etlPackagesDTO1.setId(1L);
        EtlPackagesDTO etlPackagesDTO2 = new EtlPackagesDTO();
        assertThat(etlPackagesDTO1).isNotEqualTo(etlPackagesDTO2);
        etlPackagesDTO2.setId(etlPackagesDTO1.getId());
        assertThat(etlPackagesDTO1).isEqualTo(etlPackagesDTO2);
        etlPackagesDTO2.setId(2L);
        assertThat(etlPackagesDTO1).isNotEqualTo(etlPackagesDTO2);
        etlPackagesDTO1.setId(null);
        assertThat(etlPackagesDTO1).isNotEqualTo(etlPackagesDTO2);
    }
}
