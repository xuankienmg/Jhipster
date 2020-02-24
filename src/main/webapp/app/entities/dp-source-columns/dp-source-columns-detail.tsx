import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dp-source-columns.reducer';
import { IDpSourceColumns } from 'app/shared/model/dp-source-columns.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDpSourceColumnsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DpSourceColumnsDetail = (props: IDpSourceColumnsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dpSourceColumnsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dpSourceColumns.detail.title">DpSourceColumns</Translate> [<b>{dpSourceColumnsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uniqueValues">
              <Translate contentKey="jhipsterApp.dpSourceColumns.uniqueValues">Unique Values</Translate>
            </span>
          </dt>
          <dd>{dpSourceColumnsEntity.uniqueValues}</dd>
          <dt>
            <span id="minValue">
              <Translate contentKey="jhipsterApp.dpSourceColumns.minValue">Min Value</Translate>
            </span>
          </dt>
          <dd>{dpSourceColumnsEntity.minValue}</dd>
          <dt>
            <span id="maxValue">
              <Translate contentKey="jhipsterApp.dpSourceColumns.maxValue">Max Value</Translate>
            </span>
          </dt>
          <dd>{dpSourceColumnsEntity.maxValue}</dd>
          <dt>
            <span id="averageValue">
              <Translate contentKey="jhipsterApp.dpSourceColumns.averageValue">Average Value</Translate>
            </span>
          </dt>
          <dd>{dpSourceColumnsEntity.averageValue}</dd>
          <dt>
            <span id="dpSourceColumnscol">
              <Translate contentKey="jhipsterApp.dpSourceColumns.dpSourceColumnscol">Dp Source Columnscol</Translate>
            </span>
          </dt>
          <dd>{dpSourceColumnsEntity.dpSourceColumnscol}</dd>
          <dt>
            <span id="maxLength">
              <Translate contentKey="jhipsterApp.dpSourceColumns.maxLength">Max Length</Translate>
            </span>
          </dt>
          <dd>{dpSourceColumnsEntity.maxLength}</dd>
          <dt>
            <span id="nulls">
              <Translate contentKey="jhipsterApp.dpSourceColumns.nulls">Nulls</Translate>
            </span>
          </dt>
          <dd>{dpSourceColumnsEntity.nulls}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dpSourceColumns.tbl">Tbl</Translate>
          </dt>
          <dd>{dpSourceColumnsEntity.tblId ? dpSourceColumnsEntity.tblId : ''}</dd>
          <dt>
            <Translate contentKey="jhipsterApp.dpSourceColumns.col">Col</Translate>
          </dt>
          <dd>{dpSourceColumnsEntity.colId ? dpSourceColumnsEntity.colId : ''}</dd>
        </dl>
        <Button tag={Link} to="/dp-source-columns" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dp-source-columns/${dpSourceColumnsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dpSourceColumns }: IRootState) => ({
  dpSourceColumnsEntity: dpSourceColumns.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DpSourceColumnsDetail);
