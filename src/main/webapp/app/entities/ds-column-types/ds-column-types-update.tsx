import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './ds-column-types.reducer';
import { IDsColumnTypes } from 'app/shared/model/ds-column-types.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDsColumnTypesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsColumnTypesUpdate = (props: IDsColumnTypesUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dsColumnTypesEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ds-column-types' + props.location.search);
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
        ...dsColumnTypesEntity,
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
          <h2 id="jhipsterApp.dsColumnTypes.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dsColumnTypes.home.createOrEditLabel">Create or edit a DsColumnTypes</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dsColumnTypesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ds-column-types-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ds-column-types-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="colTypeNameLabel" for="ds-column-types-colTypeName">
                  <Translate contentKey="jhipsterApp.dsColumnTypes.colTypeName">Col Type Name</Translate>
                </Label>
                <AvField id="ds-column-types-colTypeName" type="text" name="colTypeName" />
              </AvGroup>
              <AvGroup>
                <Label id="colTypeDescriptionLabel" for="ds-column-types-colTypeDescription">
                  <Translate contentKey="jhipsterApp.dsColumnTypes.colTypeDescription">Col Type Description</Translate>
                </Label>
                <AvField id="ds-column-types-colTypeDescription" type="text" name="colTypeDescription" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ds-column-types" replace color="info">
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
  dsColumnTypesEntity: storeState.dsColumnTypes.entity,
  loading: storeState.dsColumnTypes.loading,
  updating: storeState.dsColumnTypes.updating,
  updateSuccess: storeState.dsColumnTypes.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsColumnTypesUpdate);
