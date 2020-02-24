import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ds-columns.reducer';
import { IDsColumns } from 'app/shared/model/ds-columns.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDsColumnsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsColumnsDetail = (props: IDsColumnsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dsColumnsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dsColumns.detail.title">DsColumns</Translate> [<b>{dsColumnsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="colName">
              <Translate contentKey="jhipsterApp.dsColumns.colName">Col Name</Translate>
            </span>
          </dt>
          <dd>{dsColumnsEntity.colName}</dd>
          <dt>
            <span id="colDataType">
              <Translate contentKey="jhipsterApp.dsColumns.colDataType">Col Data Type</Translate>
            </span>
          </dt>
          <dd>{dsColumnsEntity.colDataType}</dd>
          <dt>
            <span id="isPrimaryKey">
              <Translate contentKey="jhipsterApp.dsColumns.isPrimaryKey">Is Primary Key</Translate>
            </span>
          </dt>
          <dd>{dsColumnsEntity.isPrimaryKey ? 'true' : 'false'}</dd>
          <dt>
            <span id="isForeignKey">
              <Translate contentKey="jhipsterApp.dsColumns.isForeignKey">Is Foreign Key</Translate>
            </span>
          </dt>
          <dd>{dsColumnsEntity.isForeignKey ? 'true' : 'false'}</dd>
          <dt>
            <span id="isIdentity">
              <Translate contentKey="jhipsterApp.dsColumns.isIdentity">Is Identity</Translate>
            </span>
          </dt>
          <dd>{dsColumnsEntity.isIdentity ? 'true' : 'false'}</dd>
          <dt>
            <span id="isNull">
              <Translate contentKey="jhipsterApp.dsColumns.isNull">Is Null</Translate>
            </span>
          </dt>
          <dd>{dsColumnsEntity.isNull ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dsColumns.colTbl">Col Tbl</Translate>
          </dt>
          <dd>{dsColumnsEntity.colTblId ? dsColumnsEntity.colTblId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dsColumns.rule">Rule</Translate>
          </dt>
          <dd>
            {dsColumnsEntity.rules
              ? dsColumnsEntity.rules.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {i === dsColumnsEntity.rules.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/ds-columns" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ds-columns/${dsColumnsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dsColumns }: IRootState) => ({
  dsColumnsEntity: dsColumns.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsColumnsDetail);
