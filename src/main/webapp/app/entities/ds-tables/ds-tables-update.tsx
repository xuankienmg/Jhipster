import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDsTableTypes } from 'app/shared/model/ds-table-types.model';
import { getEntities as getDsTableTypes } from 'app/entities/ds-table-types/ds-table-types.reducer';
import { IDsStores } from 'app/shared/model/ds-stores.model';
import { getEntities as getDsStores } from 'app/entities/ds-stores/ds-stores.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ds-tables.reducer';
import { IDsTables } from 'app/shared/model/ds-tables.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDsTablesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsTablesUpdate = (props: IDsTablesUpdateProps) => {
  const [tblTypeId, setTblTypeId] = useState('0');
  const [storeId, setStoreId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dsTablesEntity, dsTableTypes, dsStores, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ds-tables' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDsTableTypes();
    props.getDsStores();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dsTablesEntity,
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
          <h2 id="jhipsterApp.dsTables.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dsTables.home.createOrEditLabel">Create or edit a DsTables</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dsTablesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ds-tables-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ds-tables-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="tblNameLabel" for="ds-tables-tblName">
                  <Translate contentKey="jhipsterApp.dsTables.tblName">Tbl Name</Translate>
                </Label>
                <AvField id="ds-tables-tblName" type="text" name="tblName" />
              </AvGroup>
              <AvGroup>
                <Label id="tblDescriptionLabel" for="ds-tables-tblDescription">
                  <Translate contentKey="jhipsterApp.dsTables.tblDescription">Tbl Description</Translate>
                </Label>
                <AvField id="ds-tables-tblDescription" type="text" name="tblDescription" />
              </AvGroup>
              <AvGroup>
                <Label for="ds-tables-tblType">
                  <Translate contentKey="jhipsterApp.dsTables.tblType">Tbl Type</Translate>
                </Label>
                <AvInput id="ds-tables-tblType" type="select" className="form-control" name="tblTypeId">
                  <option value="" key="0" />
                  {dsTableTypes
                    ? dsTableTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="ds-tables-store">
                  <Translate contentKey="jhipsterApp.dsTables.store">Store</Translate>
                </Label>
                <AvInput id="ds-tables-store" type="select" className="form-control" name="storeId">
                  <option value="" key="0" />
                  {dsStores
                    ? dsStores.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ds-tables" replace color="info">
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
  dsTableTypes: storeState.dsTableTypes.entities,
  dsStores: storeState.dsStores.entities,
  dsTablesEntity: storeState.dsTables.entity,
  loading: storeState.dsTables.loading,
  updating: storeState.dsTables.updating,
  updateSuccess: storeState.dsTables.updateSuccess
});

const mapDispatchToProps = {
  getDsTableTypes,
  getDsStores,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsTablesUpdate);
