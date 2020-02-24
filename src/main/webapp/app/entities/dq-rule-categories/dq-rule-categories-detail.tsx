import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dq-rule-categories.reducer';
import { IDqRuleCategories } from 'app/shared/model/dq-rule-categories.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDqRuleCategoriesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DqRuleCategoriesDetail = (props: IDqRuleCategoriesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dqRuleCategoriesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterApp.dqRuleCategories.detail.title">DqRuleCategories</Translate> [<b>{dqRuleCategoriesEntity.id}</b>
          ]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="catName">
              <Translate contentKey="jhipsterApp.dqRuleCategories.catName">Cat Name</Translate>
            </span>
          </dt>
          <dd>{dqRuleCategoriesEntity.catName}</dd>
          <dt>
            <span id="catDescription">
              <Translate contentKey="jhipsterApp.dqRuleCategories.catDescription">Cat Description</Translate>
            </span>
          </dt>
          <dd>{dqRuleCategoriesEntity.catDescription}</dd>
        </dl>
        <Button tag={Link} to="/dq-rule-categories" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dq-rule-categories/${dqRuleCategoriesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dqRuleCategories }: IRootState) => ({
  dqRuleCategoriesEntity: dqRuleCategories.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DqRuleCategoriesDetail);
