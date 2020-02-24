package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsColumnsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsColumnsDTO.class);
        DsColumnsDTO dsColumnsDTO1 = new DsColumnsDTO();
        dsColumnsDTO1.setId(1L);
        DsColumnsDTO dsColumnsDTO2 = new DsColumnsDTO();
        assertThat(dsColumnsDTO1).isNotEqualTo(dsColumnsDTO2);
        dsColumnsDTO2.setId(dsColumnsDTO1.getId());
        assertThat(dsColumnsDTO1).isEqualTo(dsColumnsDTO2);
        dsColumnsDTO2.setId(2L);
        assertThat(dsColumnsDTO1).isNotEqualTo(dsColumnsDTO2);
        dsColumnsDTO1.setId(null);
        assertThat(dsColumnsDTO1).isNotEqualTo(dsColumnsDTO2);
    }
}
