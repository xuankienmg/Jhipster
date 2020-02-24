package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsStoresTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsStores.class);
        DsStores dsStores1 = new DsStores();
        dsStores1.setId(1L);
        DsStores dsStores2 = new DsStores();
        dsStores2.setId(dsStores1.getId());
        assertThat(dsStores1).isEqualTo(dsStores2);
        dsStores2.setId(2L);
        assertThat(dsStores1).isNotEqualTo(dsStores2);
        dsStores1.setId(null);
        assertThat(dsStores1).isNotEqualTo(dsStores2);
    }
}
