package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DpSourceColumnsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DpSourceColumns.class);
        DpSourceColumns dpSourceColumns1 = new DpSourceColumns();
        dpSourceColumns1.setId(1L);
        DpSourceColumns dpSourceColumns2 = new DpSourceColumns();
        dpSourceColumns2.setId(dpSourceColumns1.getId());
        assertThat(dpSourceColumns1).isEqualTo(dpSourceColumns2);
        dpSourceColumns2.setId(2L);
        assertThat(dpSourceColumns1).isNotEqualTo(dpSourceColumns2);
        dpSourceColumns1.setId(null);
        assertThat(dpSourceColumns1).isNotEqualTo(dpSourceColumns2);
    }
}
