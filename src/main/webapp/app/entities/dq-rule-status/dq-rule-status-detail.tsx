import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-rule-status.reducer';
import { IDqRuleStatus } from 'app/shared/model/dq-rule-status.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqRuleStatusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleStatusDetail = (props: IDqRuleStatusDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqRuleStatusEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqRuleStatus.detail.title">DqRuleStatus</Translate> [<b>{dqRuleStatusEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="statusName">
              <Translate contentKey="jhipsterApp.dqRuleStatus.statusName">Status Name</Translate>
            </span>
          </dt>
          <dd>{dqRuleStatusEntity.statusName}</dd>
          <dt>
            <span id="statusDescription">
              <Translate contentKey="jhipsterApp.dqRuleStatus.statusDescription">Status Description</Translate>
            </span>
          </dt>
          <dd>{dqRuleStatusEntity.statusDescription}</dd>
        </dl>
        <Button tag={Link} to="/dq-rule-status" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-rule-status/${dqRuleStatusEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqRuleStatus }: IRootState) => ({
  dqRuleStatusEntity: dqRuleStatus.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleStatusDetail);
