import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './ds-dbms-types.reducer';
import { IDsDbmsTypes } from 'app/shared/model/ds-dbms-types.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDsDbmsTypesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsDbmsTypesUpdate = (props: IDsDbmsTypesUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dsDbmsTypesEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ds-dbms-types' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dsDbmsTypesEntity,
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
          <h2 id="jhipsterApp.dsDbmsTypes.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dsDbmsTypes.home.createOrEditLabel">Create or edit a DsDbmsTypes</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dsDbmsTypesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ds-dbms-types-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ds-dbms-types-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dbmsTypeNameLabel" for="ds-dbms-types-dbmsTypeName">
                  <Translate contentKey="jhipsterApp.dsDbmsTypes.dbmsTypeName">Dbms Type Name</Translate>
                </Label>
                <AvField id="ds-dbms-types-dbmsTypeName" type="text" name="dbmsTypeName" />
              </AvGroup>
              <AvGroup>
                <Label id="dbsmTypeDescriptionLabel" for="ds-dbms-types-dbsmTypeDescription">
                  <Translate contentKey="jhipsterApp.dsDbmsTypes.dbsmTypeDescription">Dbsm Type Description</Translate>
                </Label>
                <AvField id="ds-dbms-types-dbsmTypeDescription" type="text" name="dbsmTypeDescription" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ds-dbms-types" replace color="info">
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
  dsDbmsTypesEntity: storeState.dsDbmsTypes.entity,
  loading: storeState.dsDbmsTypes.loading,
  updating: storeState.dsDbmsTypes.updating,
  updateSuccess: storeState.dsDbmsTypes.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsDbmsTypesUpdate);
