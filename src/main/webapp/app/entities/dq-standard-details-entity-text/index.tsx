import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqStandardDetailsEntityText from './dq-standard-details-entity-text';
import DqStandardDetailsEntityTextDetail from './dq-standard-details-entity-text-detail';
import DqStandardDetailsEntityTextUpdate from './dq-standard-details-entity-text-update';
import DqStandardDetailsEntityTextDeleteDialog from './dq-standard-details-entity-text-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqStandardDetailsEntityTextDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqStandardDetailsEntityTextUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqStandardDetailsEntityTextUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqStandardDetailsEntityTextDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqStandardDetailsEntityText} />
    </Switch>
  </>
);

export default Routes;
