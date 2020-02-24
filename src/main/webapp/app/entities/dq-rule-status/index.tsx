import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqRuleStatus from './dq-rule-status';
import DqRuleStatusDetail from './dq-rule-status-detail';
import DqRuleStatusUpdate from './dq-rule-status-update';
import DqRuleStatusDeleteDialog from './dq-rule-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqRuleStatusDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqRuleStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqRuleStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqRuleStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqRuleStatus} />
    </Switch>
  </>
);

export default Routes;
