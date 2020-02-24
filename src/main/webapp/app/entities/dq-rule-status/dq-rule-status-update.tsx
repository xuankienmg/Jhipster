import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './dq-rule-status.reducer';
import { IDqRuleStatus } from 'app/shared/model/dq-rule-status.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDqRuleStatusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleStatusUpdate = (props: IDqRuleStatusUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dqRuleStatusEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dq-rule-status' + props.location.search);
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
        ...dqRuleStatusEntity,
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
          <h2 id="jhipsterApp.dqRuleStatus.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dqRuleStatus.home.createOrEditLabel">Create or edit a DqRuleStatus</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dqRuleStatusEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dq-rule-status-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dq-rule-status-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="statusNameLabel" for="dq-rule-status-statusName">
                  <Translate contentKey="jhipsterApp.dqRuleStatus.statusName">Status Name</Translate>
                </Label>
                <AvField id="dq-rule-status-statusName" type="text" name="statusName" />
              </AvGroup>
              <AvGroup>
                <Label id="statusDescriptionLabel" for="dq-rule-status-statusDescription">
                  <Translate contentKey="jhipsterApp.dqRuleStatus.statusDescription">Status Description</Translate>
                </Label>
                <AvField id="dq-rule-status-statusDescription" type="text" name="statusDescription" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/dq-rule-status" replace color="info">
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
  dqRuleStatusEntity: storeState.dqRuleStatus.entity,
  loading: storeState.dqRuleStatus.loading,
  updating: storeState.dqRuleStatus.updating,
  updateSuccess: storeState.dqRuleStatus.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleStatusUpdate);
