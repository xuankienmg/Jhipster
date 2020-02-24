package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DpSourceColumnsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DpSourceColumnsDTO.class);
        DpSourceColumnsDTO dpSourceColumnsDTO1 = new DpSourceColumnsDTO();
        dpSourceColumnsDTO1.setId(1L);
        DpSourceColumnsDTO dpSourceColumnsDTO2 = new DpSourceColumnsDTO();
        assertThat(dpSourceColumnsDTO1).isNotEqualTo(dpSourceColumnsDTO2);
        dpSourceColumnsDTO2.setId(dpSourceColumnsDTO1.getId());
        assertThat(dpSourceColumnsDTO1).isEqualTo(dpSourceColumnsDTO2);
        dpSourceColumnsDTO2.setId(2L);
        assertThat(dpSourceColumnsDTO1).isNotEqualTo(dpSourceColumnsDTO2);
        dpSourceColumnsDTO1.setId(null);
        assertThat(dpSourceColumnsDTO1).isNotEqualTo(dpSourceColumnsDTO2);
    }
}
