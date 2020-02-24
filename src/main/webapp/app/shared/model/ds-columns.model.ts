import { IDqRules } from 'app/shared/model/dq-rules.model';

export interface IDsColumns {
  id?: number;
  colName?: string;
  colDataType?: string;
  isPrimaryKey?: boolean;
  isForeignKey?: boolean;
  isIdentity?: boolean;
  isNull?: boolean;
  colTblId?: number;
  rules?: IDqRules[];
}

export const defaultValue: Readonly<IDsColumns> = {
  isPrimaryKey: false,
  isForeignKey: false,
  isIdentity: false,
  isNull: false
};
