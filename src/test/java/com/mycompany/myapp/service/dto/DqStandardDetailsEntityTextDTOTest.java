package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityTextDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityTextDTO.class);
        DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO1 = new DqStandardDetailsEntityTextDTO();
        dqStandardDetailsEntityTextDTO1.setId(1L);
        DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO2 = new DqStandardDetailsEntityTextDTO();
        assertThat(dqStandardDetailsEntityTextDTO1).isNotEqualTo(dqStandardDetailsEntityTextDTO2);
        dqStandardDetailsEntityTextDTO2.setId(dqStandardDetailsEntityTextDTO1.getId());
        assertThat(dqStandardDetailsEntityTextDTO1).isEqualTo(dqStandardDetailsEntityTextDTO2);
        dqStandardDetailsEntityTextDTO2.setId(2L);
        assertThat(dqStandardDetailsEntityTextDTO1).isNotEqualTo(dqStandardDetailsEntityTextDTO2);
        dqStandardDetailsEntityTextDTO1.setId(null);
        assertThat(dqStandardDetailsEntityTextDTO1).isNotEqualTo(dqStandardDetailsEntityTextDTO2);
    }
}
