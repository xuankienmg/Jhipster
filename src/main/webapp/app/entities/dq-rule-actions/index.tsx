import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqRuleActions from './dq-rule-actions';
import DqRuleActionsDetail from './dq-rule-actions-detail';
import DqRuleActionsUpdate from './dq-rule-actions-update';
import DqRuleActionsDeleteDialog from './dq-rule-actions-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqRuleActionsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqRuleActionsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqRuleActionsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqRuleActionsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqRuleActions} />
    </Switch>
  </>
);

export default Routes;
