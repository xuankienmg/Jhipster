package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityTimeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityTimeDTO.class);
        DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO1 = new DqStandardDetailsEntityTimeDTO();
        dqStandardDetailsEntityTimeDTO1.setId(1L);
        DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO2 = new DqStandardDetailsEntityTimeDTO();
        assertThat(dqStandardDetailsEntityTimeDTO1).isNotEqualTo(dqStandardDetailsEntityTimeDTO2);
        dqStandardDetailsEntityTimeDTO2.setId(dqStandardDetailsEntityTimeDTO1.getId());
        assertThat(dqStandardDetailsEntityTimeDTO1).isEqualTo(dqStandardDetailsEntityTimeDTO2);
        dqStandardDetailsEntityTimeDTO2.setId(2L);
        assertThat(dqStandardDetailsEntityTimeDTO1).isNotEqualTo(dqStandardDetailsEntityTimeDTO2);
        dqStandardDetailsEntityTimeDTO1.setId(null);
        assertThat(dqStandardDetailsEntityTimeDTO1).isNotEqualTo(dqStandardDetailsEntityTimeDTO2);
    }
}
