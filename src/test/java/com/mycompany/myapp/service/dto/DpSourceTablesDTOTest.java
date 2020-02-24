package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DpSourceTablesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DpSourceTablesDTO.class);
        DpSourceTablesDTO dpSourceTablesDTO1 = new DpSourceTablesDTO();
        dpSourceTablesDTO1.setId(1L);
        DpSourceTablesDTO dpSourceTablesDTO2 = new DpSourceTablesDTO();
        assertThat(dpSourceTablesDTO1).isNotEqualTo(dpSourceTablesDTO2);
        dpSourceTablesDTO2.setId(dpSourceTablesDTO1.getId());
        assertThat(dpSourceTablesDTO1).isEqualTo(dpSourceTablesDTO2);
        dpSourceTablesDTO2.setId(2L);
        assertThat(dpSourceTablesDTO1).isNotEqualTo(dpSourceTablesDTO2);
        dpSourceTablesDTO1.setId(null);
        assertThat(dpSourceTablesDTO1).isNotEqualTo(dpSourceTablesDTO2);
    }
}
