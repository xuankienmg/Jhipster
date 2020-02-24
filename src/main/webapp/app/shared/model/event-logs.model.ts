import { Moment } from 'moment';

export interface IEventLogs {
  id?: number;
  rows?: number;
  eventNote?: string;
  eventTimestamp?: Moment;
  eventTypeId?: number;
  eventCatId?: number;
  flowId?: number;
  tblId?: number;
}

export const defaultValue: Readonly<IEventLogs> = {};
