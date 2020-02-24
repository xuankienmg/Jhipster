import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-notifications.reducer';
import { IDqNotifications } from 'app/shared/model/dq-notifications.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqNotificationsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqNotificationsDetail = (props: IDqNotificationsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqNotificationsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqNotifications.detail.title">DqNotifications</Translate> [<b>{dqNotificationsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="repicientId">
              <Translate contentKey="jhipsterApp.dqNotifications.repicientId">Repicient Id</Translate>
            </span>
          </dt>
          <dd>{dqNotificationsEntity.repicientId}</dd>
          <dt>
            <span id="repicientTypeId">
              <Translate contentKey="jhipsterApp.dqNotifications.repicientTypeId">Repicient Type Id</Translate>
            </span>
          </dt>
          <dd>{dqNotificationsEntity.repicientTypeId}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dqNotifications.rule">Rule</Translate>
          </dt>
          <dd>{dqNotificationsEntity.ruleId ? dqNotificationsEntity.ruleId : ''}</dd>
        </dl>
        <Button tag={Link} to="/dq-notifications" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-notifications/${dqNotificationsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqNotifications }: IRootState) => ({
  dqNotificationsEntity: dqNotifications.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqNotificationsDetail);
