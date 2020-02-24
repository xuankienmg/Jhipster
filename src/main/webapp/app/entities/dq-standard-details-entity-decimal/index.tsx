import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqStandardDetailsEntityDecimal from './dq-standard-details-entity-decimal';
import DqStandardDetailsEntityDecimalDetail from './dq-standard-details-entity-decimal-detail';
import DqStandardDetailsEntityDecimalUpdate from './dq-standard-details-entity-decimal-update';
import DqStandardDetailsEntityDecimalDeleteDialog from './dq-standard-details-entity-decimal-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqStandardDetailsEntityDecimalDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqStandardDetailsEntityDecimalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqStandardDetailsEntityDecimalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqStandardDetailsEntityDecimalDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqStandardDetailsEntityDecimal} />
    </Switch>
  </>
);

export default Routes;
