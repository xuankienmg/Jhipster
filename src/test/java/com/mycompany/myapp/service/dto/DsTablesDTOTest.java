package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsTablesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsTablesDTO.class);
        DsTablesDTO dsTablesDTO1 = new DsTablesDTO();
        dsTablesDTO1.setId(1L);
        DsTablesDTO dsTablesDTO2 = new DsTablesDTO();
        assertThat(dsTablesDTO1).isNotEqualTo(dsTablesDTO2);
        dsTablesDTO2.setId(dsTablesDTO1.getId());
        assertThat(dsTablesDTO1).isEqualTo(dsTablesDTO2);
        dsTablesDTO2.setId(2L);
        assertThat(dsTablesDTO1).isNotEqualTo(dsTablesDTO2);
        dsTablesDTO1.setId(null);
        assertThat(dsTablesDTO1).isNotEqualTo(dsTablesDTO2);
    }
}
