import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ds-table-types.reducer';
import { IDsTableTypes } from 'app/shared/model/ds-table-types.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDsTableTypesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DsTableTypesDetail = (props: IDsTableTypesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dsTableTypesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dsTableTypes.detail.title">DsTableTypes</Translate> [<b>{dsTableTypesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="tblTypeName">
              <Translate contentKey="jhipsterApp.dsTableTypes.tblTypeName">Tbl Type Name</Translate>
            </span>
          </dt>
          <dd>{dsTableTypesEntity.tblTypeName}</dd>
          <dt>
            <span id="tblTypeDescription">
              <Translate contentKey="jhipsterApp.dsTableTypes.tblTypeDescription">Tbl Type Description</Translate>
            </span>
          </dt>
          <dd>{dsTableTypesEntity.tblTypeDescription}</dd>
        </dl>
        <Button tag={Link} to="/ds-table-types" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ds-table-types/${dsTableTypesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dsTableTypes }: IRootState) => ({
  dsTableTypesEntity: dsTableTypes.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DsTableTypesDetail);
