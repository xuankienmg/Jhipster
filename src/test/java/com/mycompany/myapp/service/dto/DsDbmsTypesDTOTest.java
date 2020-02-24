package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsDbmsTypesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsDbmsTypesDTO.class);
        DsDbmsTypesDTO dsDbmsTypesDTO1 = new DsDbmsTypesDTO();
        dsDbmsTypesDTO1.setId(1L);
        DsDbmsTypesDTO dsDbmsTypesDTO2 = new DsDbmsTypesDTO();
        assertThat(dsDbmsTypesDTO1).isNotEqualTo(dsDbmsTypesDTO2);
        dsDbmsTypesDTO2.setId(dsDbmsTypesDTO1.getId());
        assertThat(dsDbmsTypesDTO1).isEqualTo(dsDbmsTypesDTO2);
        dsDbmsTypesDTO2.setId(2L);
        assertThat(dsDbmsTypesDTO1).isNotEqualTo(dsDbmsTypesDTO2);
        dsDbmsTypesDTO1.setId(null);
        assertThat(dsDbmsTypesDTO1).isNotEqualTo(dsDbmsTypesDTO2);
    }
}
