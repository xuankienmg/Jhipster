package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DataMappingDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataMappingDTO.class);
        DataMappingDTO dataMappingDTO1 = new DataMappingDTO();
        dataMappingDTO1.setId(1L);
        DataMappingDTO dataMappingDTO2 = new DataMappingDTO();
        assertThat(dataMappingDTO1).isNotEqualTo(dataMappingDTO2);
        dataMappingDTO2.setId(dataMappingDTO1.getId());
        assertThat(dataMappingDTO1).isEqualTo(dataMappingDTO2);
        dataMappingDTO2.setId(2L);
        assertThat(dataMappingDTO1).isNotEqualTo(dataMappingDTO2);
        dataMappingDTO1.setId(null);
        assertThat(dataMappingDTO1).isNotEqualTo(dataMappingDTO2);
    }
}
