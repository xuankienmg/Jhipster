import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DsCollations from './ds-collations';
import DsCollationsDetail from './ds-collations-detail';
import DsCollationsUpdate from './ds-collations-update';
import DsCollationsDeleteDialog from './ds-collations-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DsCollationsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DsCollationsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DsCollationsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DsCollationsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DsCollations} />
    </Switch>
  </>
);

export default Routes;
