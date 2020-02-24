export interface IDpSourceTables {
  id?: number;
  rows?: number;
  rowSize?: number;
  columns?: number;
  hasTimestamp?: boolean;
  tblId?: number;
}

export const defaultValue: Readonly<IDpSourceTables> = {
  hasTimestamp: false
};
