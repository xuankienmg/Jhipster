package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsTableTypesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsTableTypes.class);
        DsTableTypes dsTableTypes1 = new DsTableTypes();
        dsTableTypes1.setId(1L);
        DsTableTypes dsTableTypes2 = new DsTableTypes();
        dsTableTypes2.setId(dsTableTypes1.getId());
        assertThat(dsTableTypes1).isEqualTo(dsTableTypes2);
        dsTableTypes2.setId(2L);
        assertThat(dsTableTypes1).isNotEqualTo(dsTableTypes2);
        dsTableTypes1.setId(null);
        assertThat(dsTableTypes1).isNotEqualTo(dsTableTypes2);
    }
}
