package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DataDefinitionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataDefinitionDTO.class);
        DataDefinitionDTO dataDefinitionDTO1 = new DataDefinitionDTO();
        dataDefinitionDTO1.setId(1L);
        DataDefinitionDTO dataDefinitionDTO2 = new DataDefinitionDTO();
        assertThat(dataDefinitionDTO1).isNotEqualTo(dataDefinitionDTO2);
        dataDefinitionDTO2.setId(dataDefinitionDTO1.getId());
        assertThat(dataDefinitionDTO1).isEqualTo(dataDefinitionDTO2);
        dataDefinitionDTO2.setId(2L);
        assertThat(dataDefinitionDTO1).isNotEqualTo(dataDefinitionDTO2);
        dataDefinitionDTO1.setId(null);
        assertThat(dataDefinitionDTO1).isNotEqualTo(dataDefinitionDTO2);
    }
}
