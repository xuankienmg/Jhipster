package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DpSourceColumns.
 */
@Entity
@Table(name = "dp_source_columns")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DpSourceColumns implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_values")
    private Long uniqueValues;

    @Column(name = "min_value")
    private String minValue;

    @Column(name = "max_value")
    private String maxValue;

    @Column(name = "average_value")
    private String averageValue;

    @Column(name = "dp_source_columnscol")
    private String dpSourceColumnscol;

    @Column(name = "max_length")
    private Long maxLength;

    @Column(name = "nulls")
    private Long nulls;

    @ManyToOne
    @JsonIgnoreProperties("dpSourceColumns")
    private DsTables tbl;

    @ManyToOne
    @JsonIgnoreProperties("dpSourceColumns")
    private DsColumns col;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUniqueValues() {
        return uniqueValues;
    }

    public DpSourceColumns uniqueValues(Long uniqueValues) {
        this.uniqueValues = uniqueValues;
        return this;
    }

    public void setUniqueValues(Long uniqueValues) {
        this.uniqueValues = uniqueValues;
    }

    public String getMinValue() {
        return minValue;
    }

    public DpSourceColumns minValue(String minValue) {
        this.minValue = minValue;
        return this;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public DpSourceColumns maxValue(String maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getAverageValue() {
        return averageValue;
    }

    public DpSourceColumns averageValue(String averageValue) {
        this.averageValue = averageValue;
        return this;
    }

    public void setAverageValue(String averageValue) {
        this.averageValue = averageValue;
    }

    public String getDpSourceColumnscol() {
        return dpSourceColumnscol;
    }

    public DpSourceColumns dpSourceColumnscol(String dpSourceColumnscol) {
        this.dpSourceColumnscol = dpSourceColumnscol;
        return this;
    }

    public void setDpSourceColumnscol(String dpSourceColumnscol) {
        this.dpSourceColumnscol = dpSourceColumnscol;
    }

    public Long getMaxLength() {
        return maxLength;
    }

    public DpSourceColumns maxLength(Long maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    public Long getNulls() {
        return nulls;
    }

    public DpSourceColumns nulls(Long nulls) {
        this.nulls = nulls;
        return this;
    }

    public void setNulls(Long nulls) {
        this.nulls = nulls;
    }

    public DsTables getTbl() {
        return tbl;
    }

    public DpSourceColumns tbl(DsTables dsTables) {
        this.tbl = dsTables;
        return this;
    }

    public void setTbl(DsTables dsTables) {
        this.tbl = dsTables;
    }

    public DsColumns getCol() {
        return col;
    }

    public DpSourceColumns col(DsColumns dsColumns) {
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
        if (!(o instanceof DpSourceColumns)) {
            return false;
        }
        return id != null && id.equals(((DpSourceColumns) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DpSourceColumns{" +
            "id=" + getId() +
            ", uniqueValues=" + getUniqueValues() +
            ", minValue='" + getMinValue() + "'" +
            ", maxValue='" + getMaxValue() + "'" +
            ", averageValue='" + getAverageValue() + "'" +
            ", dpSourceColumnscol='" + getDpSourceColumnscol() + "'" +
            ", maxLength=" + getMaxLength() +
            ", nulls=" + getNulls() +
            "}";
    }
}
