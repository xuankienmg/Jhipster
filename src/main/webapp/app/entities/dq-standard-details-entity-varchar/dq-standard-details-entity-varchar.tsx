import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './dq-standard-details-entity-varchar.reducer';
import { IDqStandardDetailsEntityVarchar } from 'app/shared/model/dq-standard-details-entity-varchar.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IDqStandardDetailsEntityVarcharProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const DqStandardDetailsEntityVarchar = (props: IDqStandardDetailsEntityVarcharProps) => {
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

  const { dqStandardDetailsEntityVarcharList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="dq-standard-details-entity-varchar-heading">
        <Translate contentKey="jhipsterApp.dqStandardDetailsEntityVarchar.home.title">Dq Standard Details Entity Varchars</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="jhipsterApp.dqStandardDetailsEntityVarchar.home.createLabel">
            Create new Dq Standard Details Entity Varchar
          </Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {dqStandardDetailsEntityVarcharList && dqStandardDetailsEntityVarcharList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('stdAtttributeName')}>
                  <Translate contentKey="jhipsterApp.dqStandardDetailsEntityVarchar.stdAtttributeName">Std Atttribute Name</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('stdAttributeValue')}>
                  <Translate contentKey="jhipsterApp.dqStandardDetailsEntityVarchar.stdAttributeValue">Std Attribute Value</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterApp.dqStandardDetailsEntityVarchar.std">Std</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dqStandardDetailsEntityVarcharList.map((dqStandardDetailsEntityVarchar, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${dqStandardDetailsEntityVarchar.id}`} color="link" size="sm">
                      {dqStandardDetailsEntityVarchar.id}
                    </Button>
                  </td>
                  <td>{dqStandardDetailsEntityVarchar.stdAtttributeName}</td>
                  <td>{dqStandardDetailsEntityVarchar.stdAttributeValue}</td>
                  <td>
                    {dqStandardDetailsEntityVarchar.stdId ? (
                      <Link to={`dq-standards/${dqStandardDetailsEntityVarchar.stdId}`}>{dqStandardDetailsEntityVarchar.stdId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${dqStandardDetailsEntityVarchar.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${dqStandardDetailsEntityVarchar.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${dqStandardDetailsEntityVarchar.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="jhipsterApp.dqStandardDetailsEntityVarchar.home.notFound">
                No Dq Standard Details Entity Varchars found
              </Translate>
            </div>
          )
        )}
      </div>
      <div className={dqStandardDetailsEntityVarcharList && dqStandardDetailsEntityVarcharList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ dqStandardDetailsEntityVarchar }: IRootState) => ({
  dqStandardDetailsEntityVarcharList: dqStandardDetailsEntityVarchar.entities,
  loading: dqStandardDetailsEntityVarchar.loading,
  totalItems: dqStandardDetailsEntityVarchar.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqStandardDetailsEntityVarchar);
