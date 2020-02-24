import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDsDbmsTypes } from 'app/shared/model/ds-dbms-types.model';
import { getEntities as getDsDbmsTypes } from 'app/entities/ds-dbms-types/ds-dbms-types.reducer';
import { IDsCollations } from 'app/shared/model/ds-collations.model';
import { getEntities as getDsCollations } from 'app/entities/ds-collations/ds-collations.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ds-stores.reducer';
import { IDsStores } from 'app/shared/model/ds-stores.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDsStoresUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsStoresUpdate = (props: IDsStoresUpdateProps) => {
  const [storeDbmsTypeId, setStoreDbmsTypeId] = useState('0');
  const [storeCollationId, setStoreCollationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dsStoresEntity, dsDbmsTypes, dsCollations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ds-stores' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDsDbmsTypes();
    props.getDsCollations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dsStoresEntity,
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
          <h2 id="jhipsterApp.dsStores.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dsStores.home.createOrEditLabel">Create or edit a DsStores</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dsStoresEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ds-stores-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ds-stores-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="storeNameLabel" for="ds-stores-storeName">
                  <Translate contentKey="jhipsterApp.dsStores.storeName">Store Name</Translate>
                </Label>
                <AvField id="ds-stores-storeName" type="text" name="storeName" />
              </AvGroup>
              <AvGroup>
                <Label id="storeDescriptionLabel" for="ds-stores-storeDescription">
                  <Translate contentKey="jhipsterApp.dsStores.storeDescription">Store Description</Translate>
                </Label>
                <AvField id="ds-stores-storeDescription" type="text" name="storeDescription" />
              </AvGroup>
              <AvGroup>
                <Label id="storeSizeLabel" for="ds-stores-storeSize">
                  <Translate contentKey="jhipsterApp.dsStores.storeSize">Store Size</Translate>
                </Label>
                <AvField id="ds-stores-storeSize" type="string" className="form-control" name="storeSize" />
              </AvGroup>
              <AvGroup>
                <Label id="growthSizeLabel" for="ds-stores-growthSize">
                  <Translate contentKey="jhipsterApp.dsStores.growthSize">Growth Size</Translate>
                </Label>
                <AvField id="ds-stores-growthSize" type="string" className="form-control" name="growthSize" />
              </AvGroup>
              <AvGroup>
                <Label for="ds-stores-storeDbmsType">
                  <Translate contentKey="jhipsterApp.dsStores.storeDbmsType">Store Dbms Type</Translate>
                </Label>
                <AvInput id="ds-stores-storeDbmsType" type="select" className="form-control" name="storeDbmsTypeId">
                  <option value="" key="0" />
                  {dsDbmsTypes
                    ? dsDbmsTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="ds-stores-storeCollation">
                  <Translate contentKey="jhipsterApp.dsStores.storeCollation">Store Collation</Translate>
                </Label>
                <AvInput id="ds-stores-storeCollation" type="select" className="form-control" name="storeCollationId">
                  <option value="" key="0" />
                  {dsCollations
                    ? dsCollations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ds-stores" replace color="info">
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
  dsDbmsTypes: storeState.dsDbmsTypes.entities,
  dsCollations: storeState.dsCollations.entities,
  dsStoresEntity: storeState.dsStores.entity,
  loading: storeState.dsStores.loading,
  updating: storeState.dsStores.updating,
  updateSuccess: storeState.dsStores.updateSuccess
});

const mapDispatchToProps = {
  getDsDbmsTypes,
  getDsCollations,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsStoresUpdate);
