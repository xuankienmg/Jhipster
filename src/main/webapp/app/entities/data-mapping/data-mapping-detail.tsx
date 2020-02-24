import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './data-mapping.reducer';
import { IDataMapping } from 'app/shared/model/data-mapping.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDataMappingDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DataMappingDetail = (props: IDataMappingDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dataMappingEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dataMapping.detail.title">DataMapping</Translate> [<b>{dataMappingEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="srcColId">
              <Translate contentKey="jhipsterApp.dataMapping.srcColId">Src Col Id</Translate>
            </span>
          </dt>
          <dd>{dataMappingEntity.srcColId}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dataMapping.col">Col</Translate>
          </dt>
          <dd>{dataMappingEntity.colId ? dataMappingEntity.colId : ''}</dd>
        </dl>
        <Button tag={Link} to="/data-mapping" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/data-mapping/${dataMappingEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dataMapping }: IRootState) => ({
  dataMappingEntity: dataMapping.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DataMappingDetail);
