import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './dq-rule-categories.reducer';
import { IDqRuleCategories } from 'app/shared/model/dq-rule-categories.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDqRuleCategoriesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleCategoriesUpdate = (props: IDqRuleCategoriesUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dqRuleCategoriesEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dq-rule-categories' + props.location.search);
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
        ...dqRuleCategoriesEntity,
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
          <h2 id="jhipsterApp.dqRuleCategories.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.dqRuleCategories.home.createOrEditLabel">Create or edit a DqRuleCategories</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dqRuleCategoriesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dq-rule-categories-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dq-rule-categories-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="catNameLabel" for="dq-rule-categories-catName">
                  <Translate contentKey="jhipsterApp.dqRuleCategories.catName">Cat Name</Translate>
                </Label>
                <AvField id="dq-rule-categories-catName" type="text" name="catName" />
              </AvGroup>
              <AvGroup>
                <Label id="catDescriptionLabel" for="dq-rule-categories-catDescription">
                  <Translate contentKey="jhipsterApp.dqRuleCategories.catDescription">Cat Description</Translate>
                </Label>
                <AvField id="dq-rule-categories-catDescription" type="text" name="catDescription" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/dq-rule-categories" replace color="info">
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
  dqRuleCategoriesEntity: storeState.dqRuleCategories.entity,
  loading: storeState.dqRuleCategories.loading,
  updating: storeState.dqRuleCategories.updating,
  updateSuccess: storeState.dqRuleCategories.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleCategoriesUpdate);
