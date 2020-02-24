package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsTablesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsTables.class);
        DsTables dsTables1 = new DsTables();
        dsTables1.setId(1L);
        DsTables dsTables2 = new DsTables();
        dsTables2.setId(dsTables1.getId());
        assertThat(dsTables1).isEqualTo(dsTables2);
        dsTables2.setId(2L);
        assertThat(dsTables1).isNotEqualTo(dsTables2);
        dsTables1.setId(null);
        assertThat(dsTables1).isNotEqualTo(dsTables2);
    }
}
