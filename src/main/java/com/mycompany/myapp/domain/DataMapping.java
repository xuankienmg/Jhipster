package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DataMapping.
 */
@Entity
@Table(name = "data_mapping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DataMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "src_col_id")
    private Integer srcColId;

    @ManyToOne
    @JsonIgnoreProperties("dataMappings")
    private DsColumns col;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSrcColId() {
        return srcColId;
    }

    public DataMapping srcColId(Integer srcColId) {
        this.srcColId = srcColId;
        return this;
    }

    public void setSrcColId(Integer srcColId) {
        this.srcColId = srcColId;
    }

    public DsColumns getCol() {
        return col;
    }

    public DataMapping col(DsColumns dsColumns) {
        this.col = dsColumns;
        return this;
    }

    public void setCol(DsColumns dsColumns) {
        this.col = dsColumns;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataMapping)) {
            return false;
        }
        return id != null && id.equals(((DataMapping) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DataMapping{" +
            "id=" + getId() +
            ", srcColId=" + getSrcColId() +
            "}";
    }
}
