import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './ds-table-types.reducer';
import { IDsTableTypes } from 'app/shared/model/ds-table-types.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDsTableTypesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsTableTypesUpdate = (props: IDsTableTypesUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dsTableTypesEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ds-table-types' + props.location.search);
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
        ...dsTableTypesEntity,
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
          <h2 id="jhipsterApp.dsTableTypes.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dsTableTypes.home.createOrEditLabel">Create or edit a DsTableTypes</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dsTableTypesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ds-table-types-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ds-table-types-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="tblTypeNameLabel" for="ds-table-types-tblTypeName">
                  <Translate contentKey="jhipsterApp.dsTableTypes.tblTypeName">Tbl Type Name</Translate>
                </Label>
                <AvField id="ds-table-types-tblTypeName" type="text" name="tblTypeName" />
              </AvGroup>
              <AvGroup>
                <Label id="tblTypeDescriptionLabel" for="ds-table-types-tblTypeDescription">
                  <Translate contentKey="jhipsterApp.dsTableTypes.tblTypeDescription">Tbl Type Description</Translate>
                </Label>
                <AvField id="ds-table-types-tblTypeDescription" type="text" name="tblTypeDescription" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ds-table-types" replace color="info">
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
  dsTableTypesEntity: storeState.dsTableTypes.entity,
  loading: storeState.dsTableTypes.loading,
  updating: storeState.dsTableTypes.updating,
  updateSuccess: storeState.dsTableTypes.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsTableTypesUpdate);
