export interface IDpSourceColumns {
  id?: number;
  uniqueValues?: number;
  minValue?: string;
  maxValue?: string;
  averageValue?: string;
  dpSourceColumnscol?: string;
  maxLength?: number;
  nulls?: number;
  tblId?: number;
  colId?: number;
}

export const defaultValue: Readonly<IDpSourceColumns> = {};
