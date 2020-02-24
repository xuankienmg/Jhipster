import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './dq-rule-types.reducer';
import { IDqRuleTypes } from 'app/shared/model/dq-rule-types.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDqRuleTypesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleTypesUpdate = (props: IDqRuleTypesUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dqRuleTypesEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dq-rule-types' + props.location.search);
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
        ...dqRuleTypesEntity,
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
          <h2 id="jhipsterApp.dqRuleTypes.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dqRuleTypes.home.createOrEditLabel">Create or edit a DqRuleTypes</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dqRuleTypesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dq-rule-types-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dq-rule-types-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="typeNameLabel" for="dq-rule-types-typeName">
                  <Translate contentKey="jhipsterApp.dqRuleTypes.typeName">Type Name</Translate>
                </Label>
                <AvField id="dq-rule-types-typeName" type="text" name="typeName" />
              </AvGroup>
              <AvGroup>
                <Label id="typeDescriptionLabel" for="dq-rule-types-typeDescription">
                  <Translate contentKey="jhipsterApp.dqRuleTypes.typeDescription">Type Description</Translate>
                </Label>
                <AvField id="dq-rule-types-typeDescription" type="text" name="typeDescription" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/dq-rule-types" replace color="info">
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
  dqRuleTypesEntity: storeState.dqRuleTypes.entity,
  loading: storeState.dqRuleTypes.loading,
  updating: storeState.dqRuleTypes.updating,
  updateSuccess: storeState.dqRuleTypes.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleTypesUpdate);
