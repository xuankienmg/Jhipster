import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqRuleRiskLevels from './dq-rule-risk-levels';
import DqRuleRiskLevelsDetail from './dq-rule-risk-levels-detail';
import DqRuleRiskLevelsUpdate from './dq-rule-risk-levels-update';
import DqRuleRiskLevelsDeleteDialog from './dq-rule-risk-levels-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqRuleRiskLevelsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqRuleRiskLevelsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqRuleRiskLevelsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqRuleRiskLevelsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqRuleRiskLevels} />
    </Switch>
  </>
);

export default Routes;
