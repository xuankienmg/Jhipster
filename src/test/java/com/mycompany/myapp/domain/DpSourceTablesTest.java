package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DpSourceTablesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DpSourceTables.class);
        DpSourceTables dpSourceTables1 = new DpSourceTables();
        dpSourceTables1.setId(1L);
        DpSourceTables dpSourceTables2 = new DpSourceTables();
        dpSourceTables2.setId(dpSourceTables1.getId());
        assertThat(dpSourceTables1).isEqualTo(dpSourceTables2);
        dpSourceTables2.setId(2L);
        assertThat(dpSourceTables1).isNotEqualTo(dpSourceTables2);
        dpSourceTables1.setId(null);
        assertThat(dpSourceTables1).isNotEqualTo(dpSourceTables2);
    }
}
