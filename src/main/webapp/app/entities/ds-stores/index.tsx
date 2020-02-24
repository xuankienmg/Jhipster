import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DsStores from './ds-stores';
import DsStoresDetail from './ds-stores-detail';
import DsStoresUpdate from './ds-stores-update';
import DsStoresDeleteDialog from './ds-stores-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DsStoresDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DsStoresUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DsStoresUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DsStoresDetail} />
      <ErrorBoundaryRoute path={match.url} component={DsStores} />
    </Switch>
  </>
);

export default Routes;
