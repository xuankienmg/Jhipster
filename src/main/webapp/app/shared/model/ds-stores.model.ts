export interface IDsStores {
  id?: number;
  storeName?: string;
  storeDescription?: string;
  storeSize?: number;
  growthSize?: number;
  storeDbmsTypeId?: number;
  storeCollationId?: number;
}

export const defaultValue: Readonly<IDsStores> = {};
