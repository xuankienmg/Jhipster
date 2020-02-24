import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqRules from './dq-rules';
import DqRulesDetail from './dq-rules-detail';
import DqRulesUpdate from './dq-rules-update';
import DqRulesDeleteDialog from './dq-rules-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqRulesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqRulesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqRulesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqRulesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqRules} />
    </Switch>
  </>
);

export default Routes;
