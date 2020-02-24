package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DsColumnTypesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DsColumnTypes.class);
        DsColumnTypes dsColumnTypes1 = new DsColumnTypes();
        dsColumnTypes1.setId(1L);
        DsColumnTypes dsColumnTypes2 = new DsColumnTypes();
        dsColumnTypes2.setId(dsColumnTypes1.getId());
        assertThat(dsColumnTypes1).isEqualTo(dsColumnTypes2);
        dsColumnTypes2.setId(2L);
        assertThat(dsColumnTypes1).isNotEqualTo(dsColumnTypes2);
        dsColumnTypes1.setId(null);
        assertThat(dsColumnTypes1).isNotEqualTo(dsColumnTypes2);
    }
}
