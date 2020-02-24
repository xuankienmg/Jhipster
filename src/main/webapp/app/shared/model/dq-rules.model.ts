import { IDqStandards } from 'app/shared/model/dq-standards.model';
import { IDsColumns } from 'app/shared/model/ds-columns.model';

export interface IDqRules {
  id?: number;
  ruleName?: string;
  ruleDescription?: string;
  typeId?: number;
  riskId?: number;
  statusId?: number;
  catId?: number;
  actionId?: number;
  stds?: IDqStandards[];
  cols?: IDsColumns[];
}

export const defaultValue: Readonly<IDqRules> = {};
