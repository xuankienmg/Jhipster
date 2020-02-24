package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityIntDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityIntDTO.class);
        DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO1 = new DqStandardDetailsEntityIntDTO();
        dqStandardDetailsEntityIntDTO1.setId(1L);
        DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO2 = new DqStandardDetailsEntityIntDTO();
        assertThat(dqStandardDetailsEntityIntDTO1).isNotEqualTo(dqStandardDetailsEntityIntDTO2);
        dqStandardDetailsEntityIntDTO2.setId(dqStandardDetailsEntityIntDTO1.getId());
        assertThat(dqStandardDetailsEntityIntDTO1).isEqualTo(dqStandardDetailsEntityIntDTO2);
        dqStandardDetailsEntityIntDTO2.setId(2L);
        assertThat(dqStandardDetailsEntityIntDTO1).isNotEqualTo(dqStandardDetailsEntityIntDTO2);
        dqStandardDetailsEntityIntDTO1.setId(null);
        assertThat(dqStandardDetailsEntityIntDTO1).isNotEqualTo(dqStandardDetailsEntityIntDTO2);
    }
}
