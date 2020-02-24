package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardsDTO.class);
        DqStandardsDTO dqStandardsDTO1 = new DqStandardsDTO();
        dqStandardsDTO1.setId(1L);
        DqStandardsDTO dqStandardsDTO2 = new DqStandardsDTO();
        assertThat(dqStandardsDTO1).isNotEqualTo(dqStandardsDTO2);
        dqStandardsDTO2.setId(dqStandardsDTO1.getId());
        assertThat(dqStandardsDTO1).isEqualTo(dqStandardsDTO2);
        dqStandardsDTO2.setId(2L);
        assertThat(dqStandardsDTO1).isNotEqualTo(dqStandardsDTO2);
        dqStandardsDTO1.setId(null);
        assertThat(dqStandardsDTO1).isNotEqualTo(dqStandardsDTO2);
    }
}
