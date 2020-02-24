import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqStandards from './dq-standards';
import DqStandardsDetail from './dq-standards-detail';
import DqStandardsUpdate from './dq-standards-update';
import DqStandardsDeleteDialog from './dq-standards-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqStandardsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqStandardsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqStandardsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqStandardsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqStandards} />
    </Switch>
  </>
);

export default Routes;
