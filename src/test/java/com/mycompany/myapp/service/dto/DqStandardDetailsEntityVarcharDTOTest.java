package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DqStandardDetailsEntityVarcharDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DqStandardDetailsEntityVarcharDTO.class);
        DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO1 = new DqStandardDetailsEntityVarcharDTO();
        dqStandardDetailsEntityVarcharDTO1.setId(1L);
        DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO2 = new DqStandardDetailsEntityVarcharDTO();
        assertThat(dqStandardDetailsEntityVarcharDTO1).isNotEqualTo(dqStandardDetailsEntityVarcharDTO2);
        dqStandardDetailsEntityVarcharDTO2.setId(dqStandardDetailsEntityVarcharDTO1.getId());
        assertThat(dqStandardDetailsEntityVarcharDTO1).isEqualTo(dqStandardDetailsEntityVarcharDTO2);
        dqStandardDetailsEntityVarcharDTO2.setId(2L);
        assertThat(dqStandardDetailsEntityVarcharDTO1).isNotEqualTo(dqStandardDetailsEntityVarcharDTO2);
        dqStandardDetailsEntityVarcharDTO1.setId(null);
        assertThat(dqStandardDetailsEntityVarcharDTO1).isNotEqualTo(dqStandardDetailsEntityVarcharDTO2);
    }
}
