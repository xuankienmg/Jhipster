import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './data-definition.reducer';
import { IDataDefinition } from 'app/shared/model/data-definition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDataDefinitionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DataDefinitionDetail = (props: IDataDefinitionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dataDefinitionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dataDefinition.detail.title">DataDefinition</Translate> [<b>{dataDefinitionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="srcColId">
              <Translate contentKey="jhipsterApp.dataDefinition.srcColId">Src Col Id</Translate>
            </span>
          </dt>
          <dd>{dataDefinitionEntity.srcColId}</dd>
          <dt>
            <span id="defDescription">
              <Translate contentKey="jhipsterApp.dataDefinition.defDescription">Def Description</Translate>
            </span>
          </dt>
          <dd>{dataDefinitionEntity.defDescription}</dd>
          <dt>
            <span id="defSampleData">
              <Translate contentKey="jhipsterApp.dataDefinition.defSampleData">Def Sample Data</Translate>
            </span>
          </dt>
          <dd>{dataDefinitionEntity.defSampleData}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dataDefinition.col">Col</Translate>
          </dt>
          <dd>{dataDefinitionEntity.colId ? dataDefinitionEntity.colId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dataDefinition.type">Type</Translate>
          </dt>
          <dd>{dataDefinitionEntity.typeId ? dataDefinitionEntity.typeId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dataDefinition.tbl">Tbl</Translate>
          </dt>
          <dd>{dataDefinitionEntity.tblId ? dataDefinitionEntity.tblId : ''}</dd>
        </dl>
        <Button tag={Link} to="/data-definition" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/data-definition/${dataDefinitionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dataDefinition }: IRootState) => ({
  dataDefinitionEntity: dataDefinition.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DataDefinitionDetail);
