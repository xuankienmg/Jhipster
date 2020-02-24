package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsColumnTypesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsColumnTypesDTO.class);
        DsColumnTypesDTO dsColumnTypesDTO1 = new DsColumnTypesDTO();
        dsColumnTypesDTO1.setId(1L);
        DsColumnTypesDTO dsColumnTypesDTO2 = new DsColumnTypesDTO();
        assertThat(dsColumnTypesDTO1).isNotEqualTo(dsColumnTypesDTO2);
        dsColumnTypesDTO2.setId(dsColumnTypesDTO1.getId());
        assertThat(dsColumnTypesDTO1).isEqualTo(dsColumnTypesDTO2);
        dsColumnTypesDTO2.setId(2L);
        assertThat(dsColumnTypesDTO1).isNotEqualTo(dsColumnTypesDTO2);
        dsColumnTypesDTO1.setId(null);
        assertThat(dsColumnTypesDTO1).isNotEqualTo(dsColumnTypesDTO2);
    }
}
