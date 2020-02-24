package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EtlStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtlStatusDTO.class);
        EtlStatusDTO etlStatusDTO1 = new EtlStatusDTO();
        etlStatusDTO1.setId(1L);
        EtlStatusDTO etlStatusDTO2 = new EtlStatusDTO();
        assertThat(etlStatusDTO1).isNotEqualTo(etlStatusDTO2);
        etlStatusDTO2.setId(etlStatusDTO1.getId());
        assertThat(etlStatusDTO1).isEqualTo(etlStatusDTO2);
        etlStatusDTO2.setId(2L);
        assertThat(etlStatusDTO1).isNotEqualTo(etlStatusDTO2);
        etlStatusDTO1.setId(null);
        assertThat(etlStatusDTO1).isNotEqualTo(etlStatusDTO2);
    }
}
