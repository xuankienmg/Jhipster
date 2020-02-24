import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './event-logs.reducer';
import { IEventLogs } from 'app/shared/model/event-logs.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IEventLogsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const EventLogs = (props: IEventLogsProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    props.history.push(
      `${props.location.pathname}?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`
    );
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage
    });

  const { eventLogsList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="event-logs-heading">
        <Translate contentKey="jhipsterApp.eventLogs.home.title">Event Logs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="jhipsterApp.eventLogs.home.createLabel">Create new Event Logs</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {eventLogsList && eventLogsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rows')}>
                  <Translate contentKey="jhipsterApp.eventLogs.rows">Rows</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('eventNote')}>
                  <Translate contentKey="jhipsterApp.eventLogs.eventNote">Event Note</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('eventTimestamp')}>
                  <Translate contentKey="jhipsterApp.eventLogs.eventTimestamp">Event Timestamp</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.eventLogs.eventType">Event Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.eventLogs.eventCat">Event Cat</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.eventLogs.flow">Flow</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.eventLogs.tbl">Tbl</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {eventLogsList.map((eventLogs, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${eventLogs.id}`} color="link" size="sm">
                      {eventLogs.id}
                    </Button>
                  </td>
                  <td>{eventLogs.rows}</td>
                  <td>{eventLogs.eventNote}</td>
                  <td>
                    <TextFormat type="date" value={eventLogs.eventTimestamp} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{eventLogs.eventTypeId ? <Link to={`event-types/${eventLogs.eventTypeId}`}>{eventLogs.eventTypeId}</Link> : ''}</td>
                  <td>{eventLogs.eventCatId ? <Link to={`event-categories/${eventLogs.eventCatId}`}>{eventLogs.eventCatId}</Link> : ''}</td>
                  <td>{eventLogs.flowId ? <Link to={`data-flows/${eventLogs.flowId}`}>{eventLogs.flowId}</Link> : ''}</td>
                  <td>{eventLogs.tblId ? <Link to={`ds-tables/${eventLogs.tblId}`}>{eventLogs.tblId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${eventLogs.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${eventLogs.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${eventLogs.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="jhipsterApp.eventLogs.home.notFound">No Event Logs found</Translate>
            </div>
          )
        )}
      </div>
      <div className={eventLogsList && eventLogsList.length > 0 ? '' : 'd-none'}>
        <Row className="justify-content-center">
          <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
        </Row>
        <Row className="justify-content-center">
          <JhiPagination
            activePage={paginationState.activePage}
            onSelect={handlePagination}
            maxButtons={5}
            itemsPerPage={paginationState.itemsPerPage}
            totalItems={props.totalItems}
          />
        </Row>
      </div>
    </div>
  );
};

const mapStateToProps = ({ eventLogs }: IRootState) => ({
  eventLogsList: eventLogs.entities,
  loading: eventLogs.loading,
  totalItems: eventLogs.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EventLogs);
