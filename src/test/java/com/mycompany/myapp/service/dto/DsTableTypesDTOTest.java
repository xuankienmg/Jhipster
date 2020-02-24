package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsTableTypesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsTableTypesDTO.class);
        DsTableTypesDTO dsTableTypesDTO1 = new DsTableTypesDTO();
        dsTableTypesDTO1.setId(1L);
        DsTableTypesDTO dsTableTypesDTO2 = new DsTableTypesDTO();
        assertThat(dsTableTypesDTO1).isNotEqualTo(dsTableTypesDTO2);
        dsTableTypesDTO2.setId(dsTableTypesDTO1.getId());
        assertThat(dsTableTypesDTO1).isEqualTo(dsTableTypesDTO2);
        dsTableTypesDTO2.setId(2L);
        assertThat(dsTableTypesDTO1).isNotEqualTo(dsTableTypesDTO2);
        dsTableTypesDTO1.setId(null);
        assertThat(dsTableTypesDTO1).isNotEqualTo(dsTableTypesDTO2);
    }
}
