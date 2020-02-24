export interface IDsCollations {
  id?: number;
  collationName?: string;
  collationDescription?: string;
  dbmsTypeId?: number;
}

export const defaultValue: Readonly<IDsCollations> = {};
