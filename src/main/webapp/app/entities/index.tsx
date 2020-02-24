import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqRuleTypes from './dq-rule-types';
import DqRuleCategories from './dq-rule-categories';
import DqRuleStatus from './dq-rule-status';
import DqRuleRiskLevels from './dq-rule-risk-levels';
import DqRuleActions from './dq-rule-actions';
import DqRules from './dq-rules';
import DqNotifications from './dq-notifications';
import DqStandardTypes from './dq-standard-types';
import DqStandards from './dq-standards';
import DqStandardDetailsEntityVarchar from './dq-standard-details-entity-varchar';
import DqStandardDetailsEntityText from './dq-standard-details-entity-text';
import DqStandardDetailsEntityInt from './dq-standard-details-entity-int';
import DqStandardDetailsEntityDecimal from './dq-standard-details-entity-decimal';
import DqStandardDetailsEntityDatetime from './dq-standard-details-entity-datetime';
import DqStandardDetailsEntityTime from './dq-standard-details-entity-time';
import DsColumns from './ds-columns';
import DataDefinition from './data-definition';
import DsColumnTypes from './ds-column-types';
import DsTables from './ds-tables';
import DsTableTypes from './ds-table-types';
import DpSourceTables from './dp-source-tables';
import DpSourceColumns from './dp-source-columns';
import DataMapping from './data-mapping';
import DsStores from './ds-stores';
import DsDbmsTypes from './ds-dbms-types';
import DsCollations from './ds-collations';
import EtlStatus from './etl-status';
import EtlPackages from './etl-packages';
import DataFlows from './data-flows';
import EventCategories from './event-categories';
import EventTypes from './event-types';
import EventLogs from './event-logs';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}dq-rule-types`} component={DqRuleTypes} />
      <ErrorBoundaryRoute path={`${match.url}dq-rule-categories`} component={DqRuleCategories} />
      <ErrorBoundaryRoute path={`${match.url}dq-rule-status`} component={DqRuleStatus} />
      <ErrorBoundaryRoute path={`${match.url}dq-rule-risk-levels`} component={DqRuleRiskLevels} />
      <ErrorBoundaryRoute path={`${match.url}dq-rule-actions`} component={DqRuleActions} />
      <ErrorBoundaryRoute path={`${match.url}dq-rules`} component={DqRules} />
      <ErrorBoundaryRoute path={`${match.url}dq-notifications`} component={DqNotifications} />
      <ErrorBoundaryRoute path={`${match.url}dq-standard-types`} component={DqStandardTypes} />
      <ErrorBoundaryRoute path={`${match.url}dq-standards`} component={DqStandards} />
      <ErrorBoundaryRoute path={`${match.url}dq-standard-details-entity-varchar`} component={DqStandardDetailsEntityVarchar} />
      <ErrorBoundaryRoute path={`${match.url}dq-standard-details-entity-text`} component={DqStandardDetailsEntityText} />
      <ErrorBoundaryRoute path={`${match.url}dq-standard-details-entity-int`} component={DqStandardDetailsEntityInt} />
      <ErrorBoundaryRoute path={`${match.url}dq-standard-details-entity-decimal`} component={DqStandardDetailsEntityDecimal} />
      <ErrorBoundaryRoute path={`${match.url}dq-standard-details-entity-datetime`} component={DqStandardDetailsEntityDatetime} />
      <ErrorBoundaryRoute path={`${match.url}dq-standard-details-entity-time`} component={DqStandardDetailsEntityTime} />
      <ErrorBoundaryRoute path={`${match.url}ds-columns`} component={DsColumns} />
      <ErrorBoundaryRoute path={`${match.url}data-definition`} component={DataDefinition} />
      <ErrorBoundaryRoute path={`${match.url}ds-column-types`} component={DsColumnTypes} />
      <ErrorBoundaryRoute path={`${match.url}ds-tables`} component={DsTables} />
      <ErrorBoundaryRoute path={`${match.url}ds-table-types`} component={DsTableTypes} />
      <ErrorBoundaryRoute path={`${match.url}dp-source-tables`} component={DpSourceTables} />
      <ErrorBoundaryRoute path={`${match.url}dp-source-columns`} component={DpSourceColumns} />
      <ErrorBoundaryRoute path={`${match.url}data-mapping`} component={DataMapping} />
      <ErrorBoundaryRoute path={`${match.url}ds-stores`} component={DsStores} />
      <ErrorBoundaryRoute path={`${match.url}ds-dbms-types`} component={DsDbmsTypes} />
      <ErrorBoundaryRoute path={`${match.url}ds-collations`} component={DsCollations} />
      <ErrorBoundaryRoute path={`${match.url}etl-status`} component={EtlStatus} />
      <ErrorBoundaryRoute path={`${match.url}etl-packages`} component={EtlPackages} />
      <ErrorBoundaryRoute path={`${match.url}data-flows`} component={DataFlows} />
      <ErrorBoundaryRoute path={`${match.url}event-categories`} component={EventCategories} />
      <ErrorBoundaryRoute path={`${match.url}event-types`} component={EventTypes} />
      <ErrorBoundaryRoute path={`${match.url}event-logs`} component={EventLogs} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
