import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, getSearchEntity } from './dq-rule-types.reducer';
import { IDqRuleTypes } from 'app/shared/model/dq-rule-types.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IDqRuleTypesProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const DqRuleTypes = (props: IDqRuleTypesProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));
  const [valueInput, setValueInput] = useState('')

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    props.history.push(
      `${props.location.pathname}?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`
    );
  };

  function handleChange(e) {
    setValueInput(e.target.value);
  } 

  function handleSearch() {
    if (valueInput !== '') {
      props.getSearchEntity(valueInput);
      props.history.push(
        `${props.location.pathname}?typeName.contains=${valueInput}`
      );
    } else {
      getAllEntities();
    }
  }

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  const sort = p => () => {
    window.console.log(paginationState);
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

  const { dqRuleTypesList, match, loading, totalItems } = props;
 
  return (
    <div>
      <h2 id="dq-rule-types-heading">
        <Translate contentKey="jhipsterApp.dqRuleTypes.home.title">Dq Rule Types</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="jhipsterApp.dqRuleTypes.home.createLabel">Create new Dq Rule Types</Translate>
        </Link>
      </h2>
      <div className='form-inline'>
        <input type="text" className="form-control mb-2 mx-sm-3" onChange={handleChange}/>
        <Button onClick={handleSearch} className="btn btn-primary mb-2">
          Tìm kiếm
        </Button>
      </div>
      <div className="table-responsive">
        {dqRuleTypesList && dqRuleTypesList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('typeName')}>
                  <Translate contentKey="jhipsterApp.dqRuleTypes.typeName">Type Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('typeDescription')}>
                  <Translate contentKey="jhipsterApp.dqRuleTypes.typeDescription">Type Description</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dqRuleTypesList.map((dqRuleTypes, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${dqRuleTypes.id}`} color="link" size="sm">
                      {dqRuleTypes.id}
                    </Button>
                  </td>
                  <td>{dqRuleTypes.typeName}</td>
                  <td>{dqRuleTypes.typeDescription}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${dqRuleTypes.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${dqRuleTypes.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${dqRuleTypes.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="jhipsterApp.dqRuleTypes.home.notFound">No Dq Rule Types found</Translate>
            </div>
          )
        )}
      </div>
      <div className={dqRuleTypesList && dqRuleTypesList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ dqRuleTypes }: IRootState) => ({
  dqRuleTypesList: dqRuleTypes.entities,
  loading: dqRuleTypes.loading,
  totalItems: dqRuleTypes.totalItems
});

const mapDispatchToProps = {
  getEntities,
  getSearchEntity
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleTypes);
