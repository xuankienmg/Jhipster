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
import { getEntity, updateEntity, createEntity, reset } from './ds-collations.reducer';
import { IDsCollations } from 'app/shared/model/ds-collations.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDsCollationsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsCollationsUpdate = (props: IDsCollationsUpdateProps) => {
  const [dbmsTypeId, setDbmsTypeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dsCollationsEntity, dsDbmsTypes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ds-collations' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDsDbmsTypes();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dsCollationsEntity,
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
          <h2 id="jhipsterApp.dsCollations.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dsCollations.home.createOrEditLabel">Create or edit a DsCollations</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dsCollationsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ds-collations-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ds-collations-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="collationNameLabel" for="ds-collations-collationName">
                  <Translate contentKey="jhipsterApp.dsCollations.collationName">Collation Name</Translate>
                </Label>
                <AvField id="ds-collations-collationName" type="text" name="collationName" />
              </AvGroup>
              <AvGroup>
                <Label id="collationDescriptionLabel" for="ds-collations-collationDescription">
                  <Translate contentKey="jhipsterApp.dsCollations.collationDescription">Collation Description</Translate>
                </Label>
                <AvField id="ds-collations-collationDescription" type="text" name="collationDescription" />
              </AvGroup>
              <AvGroup>
                <Label for="ds-collations-dbmsType">
                  <Translate contentKey="jhipsterApp.dsCollations.dbmsType">Dbms Type</Translate>
                </Label>
                <AvInput id="ds-collations-dbmsType" type="select" className="form-control" name="dbmsTypeId">
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
              <Button tag={Link} id="cancel-save" to="/ds-collations" replace color="info">
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
  dsCollationsEntity: storeState.dsCollations.entity,
  loading: storeState.dsCollations.loading,
  updating: storeState.dsCollations.updating,
  updateSuccess: storeState.dsCollations.updateSuccess
});

const mapDispatchToProps = {
  getDsDbmsTypes,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsCollationsUpdate);
