package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsColumnsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsColumns.class);
        DsColumns dsColumns1 = new DsColumns();
        dsColumns1.setId(1L);
        DsColumns dsColumns2 = new DsColumns();
        dsColumns2.setId(dsColumns1.getId());
        assertThat(dsColumns1).isEqualTo(dsColumns2);
        dsColumns2.setId(2L);
        assertThat(dsColumns1).isNotEqualTo(dsColumns2);
        dsColumns1.setId(null);
        assertThat(dsColumns1).isNotEqualTo(dsColumns2);
    }
}
