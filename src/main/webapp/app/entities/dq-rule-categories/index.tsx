import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqRuleCategories from './dq-rule-categories';
import DqRuleCategoriesDetail from './dq-rule-categories-detail';
import DqRuleCategoriesUpdate from './dq-rule-categories-update';
import DqRuleCategoriesDeleteDialog from './dq-rule-categories-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqRuleCategoriesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqRuleCategoriesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqRuleCategoriesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqRuleCategoriesDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqRuleCategories} />
    </Switch>
  </>
);

export default Routes;
