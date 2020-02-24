import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DqStandardDetailsEntityVarchar from './dq-standard-details-entity-varchar';
import DqStandardDetailsEntityVarcharDetail from './dq-standard-details-entity-varchar-detail';
import DqStandardDetailsEntityVarcharUpdate from './dq-standard-details-entity-varchar-update';
import DqStandardDetailsEntityVarcharDeleteDialog from './dq-standard-details-entity-varchar-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DqStandardDetailsEntityVarcharDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DqStandardDetailsEntityVarcharUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DqStandardDetailsEntityVarcharUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DqStandardDetailsEntityVarcharDetail} />
      <ErrorBoundaryRoute path={match.url} component={DqStandardDetailsEntityVarchar} />
    </Switch>
  </>
);

export default Routes;
