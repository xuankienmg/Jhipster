import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DsColumns from './ds-columns';
import DsColumnsDetail from './ds-columns-detail';
import DsColumnsUpdate from './ds-columns-update';
import DsColumnsDeleteDialog from './ds-columns-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DsColumnsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DsColumnsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DsColumnsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DsColumnsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DsColumns} />
    </Switch>
  </>
);

export default Routes;
