import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqRuleTypes from './dq-rule-types';
import DqRuleTypesDetail from './dq-rule-types-detail';
import DqRuleTypesUpdate from './dq-rule-types-update';
import DqRuleTypesDeleteDialog from './dq-rule-types-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqRuleTypesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqRuleTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqRuleTypesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqRuleTypesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqRuleTypes} />
    </Switch>
  </>
);

export default Routes;
