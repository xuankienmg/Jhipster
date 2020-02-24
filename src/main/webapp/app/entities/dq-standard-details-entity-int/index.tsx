import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqStandardDetailsEntityInt from './dq-standard-details-entity-int';
import DqStandardDetailsEntityIntDetail from './dq-standard-details-entity-int-detail';
import DqStandardDetailsEntityIntUpdate from './dq-standard-details-entity-int-update';
import DqStandardDetailsEntityIntDeleteDialog from './dq-standard-details-entity-int-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqStandardDetailsEntityIntDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqStandardDetailsEntityIntUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqStandardDetailsEntityIntUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqStandardDetailsEntityIntDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqStandardDetailsEntityInt} />
    </Switch>
  </>
);

export default Routes;
