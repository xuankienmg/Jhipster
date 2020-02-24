package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardTypesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardTypesDTO.class);
        DqStandardTypesDTO dqStandardTypesDTO1 = new DqStandardTypesDTO();
        dqStandardTypesDTO1.setId(1L);
        DqStandardTypesDTO dqStandardTypesDTO2 = new DqStandardTypesDTO();
        assertThat(dqStandardTypesDTO1).isNotEqualTo(dqStandardTypesDTO2);
        dqStandardTypesDTO2.setId(dqStandardTypesDTO1.getId());
        assertThat(dqStandardTypesDTO1).isEqualTo(dqStandardTypesDTO2);
        dqStandardTypesDTO2.setId(2L);
        assertThat(dqStandardTypesDTO1).isNotEqualTo(dqStandardTypesDTO2);
        dqStandardTypesDTO1.setId(null);
        assertThat(dqStandardTypesDTO1).isNotEqualTo(dqStandardTypesDTO2);
    }
}
