import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-rule-types.reducer';
import { IDqRuleTypes } from 'app/shared/model/dq-rule-types.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqRuleTypesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleTypesDetail = (props: IDqRuleTypesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqRuleTypesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqRuleTypes.detail.title">DqRuleTypes</Translate> [<b>{dqRuleTypesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="typeName">
              <Translate contentKey="jhipsterApp.dqRuleTypes.typeName">Type Name</Translate>
            </span>
          </dt>
          <dd>{dqRuleTypesEntity.typeName}</dd>
          <dt>
            <span id="typeDescription">
              <Translate contentKey="jhipsterApp.dqRuleTypes.typeDescription">Type Description</Translate>
            </span>
          </dt>
          <dd>{dqRuleTypesEntity.typeDescription}</dd>
        </dl>
        <Button tag={Link} to="/dq-rule-types" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-rule-types/${dqRuleTypesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqRuleTypes }: IRootState) => ({
  dqRuleTypesEntity: dqRuleTypes.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleTypesDetail);
