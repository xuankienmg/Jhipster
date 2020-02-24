import { Moment } from 'moment';

export interface IDqStandardDetailsEntityDatetime {
  id?: number;
  stdAttributeName?: string;
  stdAttributeValue?: Moment;
  stdId?: number;
}

export const defaultValue: Readonly<IDqStandardDetailsEntityDatetime> = {};
