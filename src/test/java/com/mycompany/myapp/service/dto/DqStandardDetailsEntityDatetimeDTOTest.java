package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityDatetimeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityDatetimeDTO.class);
        DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO1 = new DqStandardDetailsEntityDatetimeDTO();
        dqStandardDetailsEntityDatetimeDTO1.setId(1L);
        DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO2 = new DqStandardDetailsEntityDatetimeDTO();
        assertThat(dqStandardDetailsEntityDatetimeDTO1).isNotEqualTo(dqStandardDetailsEntityDatetimeDTO2);
        dqStandardDetailsEntityDatetimeDTO2.setId(dqStandardDetailsEntityDatetimeDTO1.getId());
        assertThat(dqStandardDetailsEntityDatetimeDTO1).isEqualTo(dqStandardDetailsEntityDatetimeDTO2);
        dqStandardDetailsEntityDatetimeDTO2.setId(2L);
        assertThat(dqStandardDetailsEntityDatetimeDTO1).isNotEqualTo(dqStandardDetailsEntityDatetimeDTO2);
        dqStandardDetailsEntityDatetimeDTO1.setId(null);
        assertThat(dqStandardDetailsEntityDatetimeDTO1).isNotEqualTo(dqStandardDetailsEntityDatetimeDTO2);
    }
}
