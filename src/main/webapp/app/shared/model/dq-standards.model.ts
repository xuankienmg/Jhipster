import { IDqRules } from 'app/shared/model/dq-rules.model';

export interface IDqStandards {
  id?: number;
  stdName?: string;
  stdDescription?: string;
  stdTypeId?: number;
  rules?: IDqRules[];
}

export const defaultValue: Readonly<IDqStandards> = {};
