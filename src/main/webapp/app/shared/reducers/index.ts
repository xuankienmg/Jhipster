import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import dqRuleTypes, {
  DqRuleTypesState
} from 'app/entities/dq-rule-types/dq-rule-types.reducer';
// prettier-ignore
import dqRuleCategories, {
  DqRuleCategoriesState
} from 'app/entities/dq-rule-categories/dq-rule-categories.reducer';
// prettier-ignore
import dqRuleStatus, {
  DqRuleStatusState
} from 'app/entities/dq-rule-status/dq-rule-status.reducer';
// prettier-ignore
import dqRuleRiskLevels, {
  DqRuleRiskLevelsState
} from 'app/entities/dq-rule-risk-levels/dq-rule-risk-levels.reducer';
// prettier-ignore
import dqRuleActions, {
  DqRuleActionsState
} from 'app/entities/dq-rule-actions/dq-rule-actions.reducer';
// prettier-ignore
import dqRules, {
  DqRulesState
} from 'app/entities/dq-rules/dq-rules.reducer';
// prettier-ignore
import dqNotifications, {
  DqNotificationsState
} from 'app/entities/dq-notifications/dq-notifications.reducer';
// prettier-ignore
import dqStandardTypes, {
  DqStandardTypesState
} from 'app/entities/dq-standard-types/dq-standard-types.reducer';
// prettier-ignore
import dqStandards, {
  DqStandardsState
} from 'app/entities/dq-standards/dq-standards.reducer';
// prettier-ignore
import dqStandardDetailsEntityVarchar, {
  DqStandardDetailsEntityVarcharState
} from 'app/entities/dq-standard-details-entity-varchar/dq-standard-details-entity-varchar.reducer';
// prettier-ignore
import dqStandardDetailsEntityText, {
  DqStandardDetailsEntityTextState
} from 'app/entities/dq-standard-details-entity-text/dq-standard-details-entity-text.reducer';
// prettier-ignore
import dqStandardDetailsEntityInt, {
  DqStandardDetailsEntityIntState
} from 'app/entities/dq-standard-details-entity-int/dq-standard-details-entity-int.reducer';
// prettier-ignore
import dqStandardDetailsEntityDecimal, {
  DqStandardDetailsEntityDecimalState
} from 'app/entities/dq-standard-details-entity-decimal/dq-standard-details-entity-decimal.reducer';
// prettier-ignore
import dqStandardDetailsEntityDatetime, {
  DqStandardDetailsEntityDatetimeState
} from 'app/entities/dq-standard-details-entity-datetime/dq-standard-details-entity-datetime.reducer';
// prettier-ignore
import dqStandardDetailsEntityTime, {
  DqStandardDetailsEntityTimeState
} from 'app/entities/dq-standard-details-entity-time/dq-standard-details-entity-time.reducer';
// prettier-ignore
import dsColumns, {
  DsColumnsState
} from 'app/entities/ds-columns/ds-columns.reducer';
// prettier-ignore
import dataDefinition, {
  DataDefinitionState
} from 'app/entities/data-definition/data-definition.reducer';
// prettier-ignore
import dsColumnTypes, {
  DsColumnTypesState
} from 'app/entities/ds-column-types/ds-column-types.reducer';
// prettier-ignore
import dsTables, {
  DsTablesState
} from 'app/entities/ds-tables/ds-tables.reducer';
// prettier-ignore
import dsTableTypes, {
  DsTableTypesState
} from 'app/entities/ds-table-types/ds-table-types.reducer';
// prettier-ignore
import dpSourceTables, {
  DpSourceTablesState
} from 'app/entities/dp-source-tables/dp-source-tables.reducer';
// prettier-ignore
import dpSourceColumns, {
  DpSourceColumnsState
} from 'app/entities/dp-source-columns/dp-source-columns.reducer';
// prettier-ignore
import dataMapping, {
  DataMappingState
} from 'app/entities/data-mapping/data-mapping.reducer';
// prettier-ignore
import dsStores, {
  DsStoresState
} from 'app/entities/ds-stores/ds-stores.reducer';
// prettier-ignore
import dsDbmsTypes, {
  DsDbmsTypesState
} from 'app/entities/ds-dbms-types/ds-dbms-types.reducer';
// prettier-ignore
import dsCollations, {
  DsCollationsState
} from 'app/entities/ds-collations/ds-collations.reducer';
// prettier-ignore
import etlStatus, {
  EtlStatusState
} from 'app/entities/etl-status/etl-status.reducer';
// prettier-ignore
import etlPackages, {
  EtlPackagesState
} from 'app/entities/etl-packages/etl-packages.reducer';
// prettier-ignore
import dataFlows, {
  DataFlowsState
} from 'app/entities/data-flows/data-flows.reducer';
// prettier-ignore
import eventCategories, {
  EventCategoriesState
} from 'app/entities/event-categories/event-categories.reducer';
// prettier-ignore
import eventTypes, {
  EventTypesState
} from 'app/entities/event-types/event-types.reducer';
// prettier-ignore
import eventLogs, {
  EventLogsState
} from 'app/entities/event-logs/event-logs.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly dqRuleTypes: DqRuleTypesState;
  readonly dqRuleCategories: DqRuleCategoriesState;
  readonly dqRuleStatus: DqRuleStatusState;
  readonly dqRuleRiskLevels: DqRuleRiskLevelsState;
  readonly dqRuleActions: DqRuleActionsState;
  readonly dqRules: DqRulesState;
  readonly dqNotifications: DqNotificationsState;
  readonly dqStandardTypes: DqStandardTypesState;
  readonly dqStandards: DqStandardsState;
  readonly dqStandardDetailsEntityVarchar: DqStandardDetailsEntityVarcharState;
  readonly dqStandardDetailsEntityText: DqStandardDetailsEntityTextState;
  readonly dqStandardDetailsEntityInt: DqStandardDetailsEntityIntState;
  readonly dqStandardDetailsEntityDecimal: DqStandardDetailsEntityDecimalState;
  readonly dqStandardDetailsEntityDatetime: DqStandardDetailsEntityDatetimeState;
  readonly dqStandardDetailsEntityTime: DqStandardDetailsEntityTimeState;
  readonly dsColumns: DsColumnsState;
  readonly dataDefinition: DataDefinitionState;
  readonly dsColumnTypes: DsColumnTypesState;
  readonly dsTables: DsTablesState;
  readonly dsTableTypes: DsTableTypesState;
  readonly dpSourceTables: DpSourceTablesState;
  readonly dpSourceColumns: DpSourceColumnsState;
  readonly dataMapping: DataMappingState;
  readonly dsStores: DsStoresState;
  readonly dsDbmsTypes: DsDbmsTypesState;
  readonly dsCollations: DsCollationsState;
  readonly etlStatus: EtlStatusState;
  readonly etlPackages: EtlPackagesState;
  readonly dataFlows: DataFlowsState;
  readonly eventCategories: EventCategoriesState;
  readonly eventTypes: EventTypesState;
  readonly eventLogs: EventLogsState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  dqRuleTypes,
  dqRuleCategories,
  dqRuleStatus,
  dqRuleRiskLevels,
  dqRuleActions,
  dqRules,
  dqNotifications,
  dqStandardTypes,
  dqStandards,
  dqStandardDetailsEntityVarchar,
  dqStandardDetailsEntityText,
  dqStandardDetailsEntityInt,
  dqStandardDetailsEntityDecimal,
  dqStandardDetailsEntityDatetime,
  dqStandardDetailsEntityTime,
  dsColumns,
  dataDefinition,
  dsColumnTypes,
  dsTables,
  dsTableTypes,
  dpSourceTables,
  dpSourceColumns,
  dataMapping,
  dsStores,
  dsDbmsTypes,
  dsCollations,
  etlStatus,
  etlPackages,
  dataFlows,
  eventCategories,
  eventTypes,
  eventLogs,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
