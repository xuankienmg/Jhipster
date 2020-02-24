export interface IDataDefinition {
  id?: number;
  srcColId?: number;
  defDescription?: string;
  defSampleData?: string;
  colId?: number;
  typeId?: number;
  tblId?: number;
}

export const defaultValue: Readonly<IDataDefinition> = {};
