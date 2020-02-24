import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/dq-rule-types">
      <Translate contentKey="global.menu.entities.dqRuleTypes" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-rule-categories">
      <Translate contentKey="global.menu.entities.dqRuleCategories" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-rule-status">
      <Translate contentKey="global.menu.entities.dqRuleStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-rule-risk-levels">
      <Translate contentKey="global.menu.entities.dqRuleRiskLevels" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-rule-actions">
      <Translate contentKey="global.menu.entities.dqRuleActions" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-rules">
      <Translate contentKey="global.menu.entities.dqRules" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-notifications">
      <Translate contentKey="global.menu.entities.dqNotifications" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-standard-types">
      <Translate contentKey="global.menu.entities.dqStandardTypes" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-standards">
      <Translate contentKey="global.menu.entities.dqStandards" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-standard-details-entity-varchar">
      <Translate contentKey="global.menu.entities.dqStandardDetailsEntityVarchar" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-standard-details-entity-text">
      <Translate contentKey="global.menu.entities.dqStandardDetailsEntityText" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-standard-details-entity-int">
      <Translate contentKey="global.menu.entities.dqStandardDetailsEntityInt" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-standard-details-entity-decimal">
      <Translate contentKey="global.menu.entities.dqStandardDetailsEntityDecimal" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-standard-details-entity-datetime">
      <Translate contentKey="global.menu.entities.dqStandardDetailsEntityDatetime" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dq-standard-details-entity-time">
      <Translate contentKey="global.menu.entities.dqStandardDetailsEntityTime" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ds-columns">
      <Translate contentKey="global.menu.entities.dsColumns" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/data-definition">
      <Translate contentKey="global.menu.entities.dataDefinition" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ds-column-types">
      <Translate contentKey="global.menu.entities.dsColumnTypes" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ds-tables">
      <Translate contentKey="global.menu.entities.dsTables" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ds-table-types">
      <Translate contentKey="global.menu.entities.dsTableTypes" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dp-source-tables">
      <Translate contentKey="global.menu.entities.dpSourceTables" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dp-source-columns">
      <Translate contentKey="global.menu.entities.dpSourceColumns" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/data-mapping">
      <Translate contentKey="global.menu.entities.dataMapping" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ds-stores">
      <Translate contentKey="global.menu.entities.dsStores" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ds-dbms-types">
      <Translate contentKey="global.menu.entities.dsDbmsTypes" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/ds-collations">
      <Translate contentKey="global.menu.entities.dsCollations" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/etl-status">
      <Translate contentKey="global.menu.entities.etlStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/etl-packages">
      <Translate contentKey="global.menu.entities.etlPackages" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/data-flows">
      <Translate contentKey="global.menu.entities.dataFlows" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/event-categories">
      <Translate contentKey="global.menu.entities.eventCategories" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/event-types">
      <Translate contentKey="global.menu.entities.eventTypes" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/event-logs">
      <Translate contentKey="global.menu.entities.eventLogs" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
