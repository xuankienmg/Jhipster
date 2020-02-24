import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './event-logs.reducer';
import { IEventLogs } from 'app/shared/model/event-logs.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEventLogsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EventLogsDetail = (props: IEventLogsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { eventLogsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.eventLogs.detail.title">EventLogs</Translate> [<b>{eventLogsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="rows">
              <Translate contentKey="jhipsterApp.eventLogs.rows">Rows</Translate>
            </span>
          </dt>
          <dd>{eventLogsEntity.rows}</dd>
          <dt>
            <span id="eventNote">
              <Translate contentKey="jhipsterApp.eventLogs.eventNote">Event Note</Translate>
            </span>
          </dt>
          <dd>{eventLogsEntity.eventNote}</dd>
          <dt>
            <span id="eventTimestamp">
              <Translate contentKey="jhipsterApp.eventLogs.eventTimestamp">Event Timestamp</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={eventLogsEntity.eventTimestamp} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <Translate contentKey="jhipsterApp.eventLogs.eventType">Event Type</Translate>
          </dt>
          <dd>{eventLogsEntity.eventTypeId ? eventLogsEntity.eventTypeId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.eventLogs.eventCat">Event Cat</Translate>
          </dt>
          <dd>{eventLogsEntity.eventCatId ? eventLogsEntity.eventCatId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.eventLogs.flow">Flow</Translate>
          </dt>
          <dd>{eventLogsEntity.flowId ? eventLogsEntity.flowId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.eventLogs.tbl">Tbl</Translate>
          </dt>
          <dd>{eventLogsEntity.tblId ? eventLogsEntity.tblId : ''}</dd>
        </dl>
        <Button tag={Link} to="/event-logs" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/event-logs/${eventLogsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ eventLogs }: IRootState) => ({
  eventLogsEntity: eventLogs.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EventLogsDetail);
