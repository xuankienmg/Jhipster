import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtlPackages from './etl-packages';
import EtlPackagesDetail from './etl-packages-detail';
import EtlPackagesUpdate from './etl-packages-update';
import EtlPackagesDeleteDialog from './etl-packages-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EtlPackagesDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtlPackagesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtlPackagesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtlPackagesDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtlPackages} />
    </Switch>
  </>
);

export default Routes;
