import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDsTables } from 'app/shared/model/ds-tables.model';
import { getEntities as getDsTables } from 'app/entities/ds-tables/ds-tables.reducer';
import { IDsColumns } from 'app/shared/model/ds-columns.model';
import { getEntities as getDsColumns } from 'app/entities/ds-columns/ds-columns.reducer';
import { getEntity, updateEntity, createEntity, reset } from './dp-source-columns.reducer';
import { IDpSourceColumns } from 'app/shared/model/dp-source-columns.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDpSourceColumnsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DpSourceColumnsUpdate = (props: IDpSourceColumnsUpdateProps) => {
  const [tblId, setTblId] = useState('0');
  const [colId, setColId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dpSourceColumnsEntity, dsTables, dsColumns, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dp-source-columns' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDsTables();
    props.getDsColumns();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dpSourceColumnsEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jhipsterApp.dpSourceColumns.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dpSourceColumns.home.createOrEditLabel">Create or edit a DpSourceColumns</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dpSourceColumnsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dp-source-columns-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dp-source-columns-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uniqueValuesLabel" for="dp-source-columns-uniqueValues">
                  <Translate contentKey="jhipsterApp.dpSourceColumns.uniqueValues">Unique Values</Translate>
                </Label>
                <AvField id="dp-source-columns-uniqueValues" type="string" className="form-control" name="uniqueValues" />
              </AvGroup>
              <AvGroup>
                <Label id="minValueLabel" for="dp-source-columns-minValue">
                  <Translate contentKey="jhipsterApp.dpSourceColumns.minValue">Min Value</Translate>
                </Label>
                <AvField id="dp-source-columns-minValue" type="text" name="minValue" />
              </AvGroup>
              <AvGroup>
                <Label id="maxValueLabel" for="dp-source-columns-maxValue">
                  <Translate contentKey="jhipsterApp.dpSourceColumns.maxValue">Max Value</Translate>
                </Label>
                <AvField id="dp-source-columns-maxValue" type="text" name="maxValue" />
              </AvGroup>
              <AvGroup>
                <Label id="averageValueLabel" for="dp-source-columns-averageValue">
                  <Translate contentKey="jhipsterApp.dpSourceColumns.averageValue">Average Value</Translate>
                </Label>
                <AvField id="dp-source-columns-averageValue" type="text" name="averageValue" />
              </AvGroup>
              <AvGroup>
                <Label id="dpSourceColumnscolLabel" for="dp-source-columns-dpSourceColumnscol">
                  <Translate contentKey="jhipsterApp.dpSourceColumns.dpSourceColumnscol">Dp Source Columnscol</Translate>
                </Label>
                <AvField id="dp-source-columns-dpSourceColumnscol" type="text" name="dpSourceColumnscol" />
              </AvGroup>
              <AvGroup>
                <Label id="maxLengthLabel" for="dp-source-columns-maxLength">
                  <Translate contentKey="jhipsterApp.dpSourceColumns.maxLength">Max Length</Translate>
                </Label>
                <AvField id="dp-source-columns-maxLength" type="string" className="form-control" name="maxLength" />
              </AvGroup>
              <AvGroup>
                <Label id="nullsLabel" for="dp-source-columns-nulls">
                  <Translate contentKey="jhipsterApp.dpSourceColumns.nulls">Nulls</Translate>
                </Label>
                <AvField id="dp-source-columns-nulls" type="string" className="form-control" name="nulls" />
              </AvGroup>
              <AvGroup>
                <Label for="dp-source-columns-tbl">
                  <Translate contentKey="jhipsterApp.dpSourceColumns.tbl">Tbl</Translate>
                </Label>
                <AvInput id="dp-source-columns-tbl" type="select" className="form-control" name="tblId">
                  <option value="" key="0" />
                  {dsTables
                    ? dsTables.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="dp-source-columns-col">
                  <Translate contentKey="jhipsterApp.dpSourceColumns.col">Col</Translate>
                </Label>
                <AvInput id="dp-source-columns-col" type="select" className="form-control" name="colId">
                  <option value="" key="0" />
                  {dsColumns
                    ? dsColumns.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/dp-source-columns" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  dsTables: storeState.dsTables.entities,
  dsColumns: storeState.dsColumns.entities,
  dpSourceColumnsEntity: storeState.dpSourceColumns.entity,
  loading: storeState.dpSourceColumns.loading,
  updating: storeState.dpSourceColumns.updating,
  updateSuccess: storeState.dpSourceColumns.updateSuccess
});

const mapDispatchToProps = {
  getDsTables,
  getDsColumns,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DpSourceColumnsUpdate);
