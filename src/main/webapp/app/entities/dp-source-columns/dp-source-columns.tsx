import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './dp-source-columns.reducer';
import { IDpSourceColumns } from 'app/shared/model/dp-source-columns.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IDpSourceColumnsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const DpSourceColumns = (props: IDpSourceColumnsProps) => {
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

  const { dpSourceColumnsList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="dp-source-columns-heading">
        <Translate contentKey="jhipsterApp.dpSourceColumns.home.title">Dp Source Columns</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="jhipsterApp.dpSourceColumns.home.createLabel">Create new Dp Source Columns</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {dpSourceColumnsList && dpSourceColumnsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('uniqueValues')}>
                  <Translate contentKey="jhipsterApp.dpSourceColumns.uniqueValues">Unique Values</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('minValue')}>
                  <Translate contentKey="jhipsterApp.dpSourceColumns.minValue">Min Value</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('maxValue')}>
                  <Translate contentKey="jhipsterApp.dpSourceColumns.maxValue">Max Value</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('averageValue')}>
                  <Translate contentKey="jhipsterApp.dpSourceColumns.averageValue">Average Value</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dpSourceColumnscol')}>
                  <Translate contentKey="jhipsterApp.dpSourceColumns.dpSourceColumnscol">Dp Source Columnscol</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('maxLength')}>
                  <Translate contentKey="jhipsterApp.dpSourceColumns.maxLength">Max Length</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nulls')}>
                  <Translate contentKey="jhipsterApp.dpSourceColumns.nulls">Nulls</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.dpSourceColumns.tbl">Tbl</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.dpSourceColumns.col">Col</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dpSourceColumnsList.map((dpSourceColumns, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${dpSourceColumns.id}`} color="link" size="sm">
                      {dpSourceColumns.id}
                    </Button>
                  </td>
                  <td>{dpSourceColumns.uniqueValues}</td>
                  <td>{dpSourceColumns.minValue}</td>
                  <td>{dpSourceColumns.maxValue}</td>
                  <td>{dpSourceColumns.averageValue}</td>
                  <td>{dpSourceColumns.dpSourceColumnscol}</td>
                  <td>{dpSourceColumns.maxLength}</td>
                  <td>{dpSourceColumns.nulls}</td>
                  <td>{dpSourceColumns.tblId ? <Link to={`ds-tables/${dpSourceColumns.tblId}`}>{dpSourceColumns.tblId}</Link> : ''}</td>
                  <td>{dpSourceColumns.colId ? <Link to={`ds-columns/${dpSourceColumns.colId}`}>{dpSourceColumns.colId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${dpSourceColumns.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${dpSourceColumns.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${dpSourceColumns.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="jhipsterApp.dpSourceColumns.home.notFound">No Dp Source Columns found</Translate>
            </div>
          )
        )}
      </div>
      <div className={dpSourceColumnsList && dpSourceColumnsList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ dpSourceColumns }: IRootState) => ({
  dpSourceColumnsList: dpSourceColumns.entities,
  loading: dpSourceColumns.loading,
  totalItems: dpSourceColumns.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DpSourceColumns);
