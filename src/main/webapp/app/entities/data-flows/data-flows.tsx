import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './data-flows.reducer';
import { IDataFlows } from 'app/shared/model/data-flows.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IDataFlowsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const DataFlows = (props: IDataFlowsProps) => {
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

  const { dataFlowsList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="data-flows-heading">
        <Translate contentKey="jhipsterApp.dataFlows.home.title">Data Flows</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="jhipsterApp.dataFlows.home.createLabel">Create new Data Flows</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {dataFlowsList && dataFlowsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('flowName')}>
                  <Translate contentKey="jhipsterApp.dataFlows.flowName">Flow Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('flowDescription')}>
                  <Translate contentKey="jhipsterApp.dataFlows.flowDescription">Flow Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('source')}>
                  <Translate contentKey="jhipsterApp.dataFlows.source">Source</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('destination')}>
                  <Translate contentKey="jhipsterApp.dataFlows.destination">Destination</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('transformation')}>
                  <Translate contentKey="jhipsterApp.dataFlows.transformation">Transformation</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lSET')}>
                  <Translate contentKey="jhipsterApp.dataFlows.lSET">L SET</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('cET')}>
                  <Translate contentKey="jhipsterApp.dataFlows.cET">C ET</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.dataFlows.etlStatus">Etl Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.dataFlows.etlPkg">Etl Pkg</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dataFlowsList.map((dataFlows, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${dataFlows.id}`} color="link" size="sm">
                      {dataFlows.id}
                    </Button>
                  </td>
                  <td>{dataFlows.flowName}</td>
                  <td>{dataFlows.flowDescription}</td>
                  <td>{dataFlows.source}</td>
                  <td>{dataFlows.destination}</td>
                  <td>{dataFlows.transformation}</td>
                  <td>
                    <TextFormat type="date" value={dataFlows.lSET} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={dataFlows.cET} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{dataFlows.etlStatusId ? <Link to={`etl-status/${dataFlows.etlStatusId}`}>{dataFlows.etlStatusId}</Link> : ''}</td>
                  <td>{dataFlows.etlPkgId ? <Link to={`etl-packages/${dataFlows.etlPkgId}`}>{dataFlows.etlPkgId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${dataFlows.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${dataFlows.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${dataFlows.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="jhipsterApp.dataFlows.home.notFound">No Data Flows found</Translate>
            </div>
          )
        )}
      </div>
      <div className={dataFlowsList && dataFlowsList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ dataFlows }: IRootState) => ({
  dataFlowsList: dataFlows.entities,
  loading: dataFlows.loading,
  totalItems: dataFlows.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DataFlows);
