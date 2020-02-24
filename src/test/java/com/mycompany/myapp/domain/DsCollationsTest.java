package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsCollationsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsCollations.class);
        DsCollations dsCollations1 = new DsCollations();
        dsCollations1.setId(1L);
        DsCollations dsCollations2 = new DsCollations();
        dsCollations2.setId(dsCollations1.getId());
        assertThat(dsCollations1).isEqualTo(dsCollations2);
        dsCollations2.setId(2L);
        assertThat(dsCollations1).isNotEqualTo(dsCollations2);
        dsCollations1.setId(null);
        assertThat(dsCollations1).isNotEqualTo(dsCollations2);
    }
}
