package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsCollationsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsCollationsDTO.class);
        DsCollationsDTO dsCollationsDTO1 = new DsCollationsDTO();
        dsCollationsDTO1.setId(1L);
        DsCollationsDTO dsCollationsDTO2 = new DsCollationsDTO();
        assertThat(dsCollationsDTO1).isNotEqualTo(dsCollationsDTO2);
        dsCollationsDTO2.setId(dsCollationsDTO1.getId());
        assertThat(dsCollationsDTO1).isEqualTo(dsCollationsDTO2);
        dsCollationsDTO2.setId(2L);
        assertThat(dsCollationsDTO1).isNotEqualTo(dsCollationsDTO2);
        dsCollationsDTO1.setId(null);
        assertThat(dsCollationsDTO1).isNotEqualTo(dsCollationsDTO2);
    }
}
