package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsStoresDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsStoresDTO.class);
        DsStoresDTO dsStoresDTO1 = new DsStoresDTO();
        dsStoresDTO1.setId(1L);
        DsStoresDTO dsStoresDTO2 = new DsStoresDTO();
        assertThat(dsStoresDTO1).isNotEqualTo(dsStoresDTO2);
        dsStoresDTO2.setId(dsStoresDTO1.getId());
        assertThat(dsStoresDTO1).isEqualTo(dsStoresDTO2);
        dsStoresDTO2.setId(2L);
        assertThat(dsStoresDTO1).isNotEqualTo(dsStoresDTO2);
        dsStoresDTO1.setId(null);
        assertThat(dsStoresDTO1).isNotEqualTo(dsStoresDTO2);
    }
}
