package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityDecimalDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityDecimalDTO.class);
        DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO1 = new DqStandardDetailsEntityDecimalDTO();
        dqStandardDetailsEntityDecimalDTO1.setId(1L);
        DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO2 = new DqStandardDetailsEntityDecimalDTO();
        assertThat(dqStandardDetailsEntityDecimalDTO1).isNotEqualTo(dqStandardDetailsEntityDecimalDTO2);
        dqStandardDetailsEntityDecimalDTO2.setId(dqStandardDetailsEntityDecimalDTO1.getId());
        assertThat(dqStandardDetailsEntityDecimalDTO1).isEqualTo(dqStandardDetailsEntityDecimalDTO2);
        dqStandardDetailsEntityDecimalDTO2.setId(2L);
        assertThat(dqStandardDetailsEntityDecimalDTO1).isNotEqualTo(dqStandardDetailsEntityDecimalDTO2);
        dqStandardDetailsEntityDecimalDTO1.setId(null);
        assertThat(dqStandardDetailsEntityDecimalDTO1).isNotEqualTo(dqStandardDetailsEntityDecimalDTO2);
    }
}
