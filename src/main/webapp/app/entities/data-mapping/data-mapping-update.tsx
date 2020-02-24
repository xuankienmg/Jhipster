import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDsColumns } from 'app/shared/model/ds-columns.model';
import { getEntities as getDsColumns } from 'app/entities/ds-columns/ds-columns.reducer';
import { getEntity, updateEntity, createEntity, reset } from './data-mapping.reducer';
import { IDataMapping } from 'app/shared/model/data-mapping.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDataMappingUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DataMappingUpdate = (props: IDataMappingUpdateProps) => {
  const [colId, setColId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dataMappingEntity, dsColumns, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/data-mapping' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...dataMappingEntity,
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
          <h2 id="jhipsterApp.dataMapping.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dataMapping.home.createOrEditLabel">Create or edit a DataMapping</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dataMappingEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="data-mapping-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="data-mapping-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="srcColIdLabel" for="data-mapping-srcColId">
                  <Translate contentKey="jhipsterApp.dataMapping.srcColId">Src Col Id</Translate>
                </Label>
                <AvField id="data-mapping-srcColId" type="string" className="form-control" name="srcColId" />
              </AvGroup>
              <AvGroup>
                <Label for="data-mapping-col">
                  <Translate contentKey="jhipsterApp.dataMapping.col">Col</Translate>
                </Label>
                <AvInput id="data-mapping-col" type="select" className="form-control" name="colId">
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
              <Button tag={Link} id="cancel-save" to="/data-mapping" replace color="info">
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
  dsColumns: storeState.dsColumns.entities,
  dataMappingEntity: storeState.dataMapping.entity,
  loading: storeState.dataMapping.loading,
  updating: storeState.dataMapping.updating,
  updateSuccess: storeState.dataMapping.updateSuccess
});

const mapDispatchToProps = {
  getDsColumns,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DataMappingUpdate);
