import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEventTypes } from 'app/shared/model/event-types.model';
import { getEntities as getEventTypes } from 'app/entities/event-types/event-types.reducer';
import { IEventCategories } from 'app/shared/model/event-categories.model';
import { getEntities as getEventCategories } from 'app/entities/event-categories/event-categories.reducer';
import { IDataFlows } from 'app/shared/model/data-flows.model';
import { getEntities as getDataFlows } from 'app/entities/data-flows/data-flows.reducer';
import { IDsTables } from 'app/shared/model/ds-tables.model';
import { getEntities as getDsTables } from 'app/entities/ds-tables/ds-tables.reducer';
import { getEntity, updateEntity, createEntity, reset } from './event-logs.reducer';
import { IEventLogs } from 'app/shared/model/event-logs.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEventLogsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EventLogsUpdate = (props: IEventLogsUpdateProps) => {
  const [eventTypeId, setEventTypeId] = useState('0');
  const [eventCatId, setEventCatId] = useState('0');
  const [flowId, setFlowId] = useState('0');
  const [tblId, setTblId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { eventLogsEntity, eventTypes, eventCategories, dataFlows, dsTables, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/event-logs' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getEventTypes();
    props.getEventCategories();
    props.getDataFlows();
    props.getDsTables();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.eventTimestamp = convertDateTimeToServer(values.eventTimestamp);

    if (errors.length === 0) {
      const entity = {
        ...eventLogsEntity,
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
          <h2 id="jhipsterApp.eventLogs.home.createOrEditLabel">
            <Translate contentKey="jhipsterApp.eventLogs.home.createOrEditLabel">Create or edit a EventLogs</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : eventLogsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="event-logs-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="event-logs-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="rowsLabel" for="event-logs-rows">
                  <Translate contentKey="jhipsterApp.eventLogs.rows">Rows</Translate>
                </Label>
                <AvField id="event-logs-rows" type="string" className="form-control" name="rows" />
              </AvGroup>
              <AvGroup>
                <Label id="eventNoteLabel" for="event-logs-eventNote">
                  <Translate contentKey="jhipsterApp.eventLogs.eventNote">Event Note</Translate>
                </Label>
                <AvField id="event-logs-eventNote" type="text" name="eventNote" />
              </AvGroup>
              <AvGroup>
                <Label id="eventTimestampLabel" for="event-logs-eventTimestamp">
                  <Translate contentKey="jhipsterApp.eventLogs.eventTimestamp">Event Timestamp</Translate>
                </Label>
                <AvInput
                  id="event-logs-eventTimestamp"
                  type="datetime-local"
                  className="form-control"
                  name="eventTimestamp"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.eventLogsEntity.eventTimestamp)}
                />
              </AvGroup>
              <AvGroup>
                <Label for="event-logs-eventType">
                  <Translate contentKey="jhipsterApp.eventLogs.eventType">Event Type</Translate>
                </Label>
                <AvInput id="event-logs-eventType" type="select" className="form-control" name="eventTypeId">
                  <option value="" key="0" />
                  {eventTypes
                    ? eventTypes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="event-logs-eventCat">
                  <Translate contentKey="jhipsterApp.eventLogs.eventCat">Event Cat</Translate>
                </Label>
                <AvInput id="event-logs-eventCat" type="select" className="form-control" name="eventCatId">
                  <option value="" key="0" />
                  {eventCategories
                    ? eventCategories.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="event-logs-flow">
                  <Translate contentKey="jhipsterApp.eventLogs.flow">Flow</Translate>
                </Label>
                <AvInput id="event-logs-flow" type="select" className="form-control" name="flowId">
                  <option value="" key="0" />
                  {dataFlows
                    ? dataFlows.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="event-logs-tbl">
                  <Translate contentKey="jhipsterApp.eventLogs.tbl">Tbl</Translate>
                </Label>
                <AvInput id="event-logs-tbl" type="select" className="form-control" name="tblId">
                  <option value="" key="0" />
                  {dsTables
                    ? dsTables.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/event-logs" replace color="info">
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
  eventTypes: storeState.eventTypes.entities,
  eventCategories: storeState.eventCategories.entities,
  dataFlows: storeState.dataFlows.entities,
  dsTables: storeState.dsTables.entities,
  eventLogsEntity: storeState.eventLogs.entity,
  loading: storeState.eventLogs.loading,
  updating: storeState.eventLogs.updating,
  updateSuccess: storeState.eventLogs.updateSuccess
});

const mapDispatchToProps = {
  getEventTypes,
  getEventCategories,
  getDataFlows,
  getDsTables,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EventLogsUpdate);
