import { Moment } from 'moment';

export interface IDataFlows {
  id?: number;
  flowName?: string;
  flowDescription?: string;
  source?: string;
  destination?: string;
  transformation?: string;
  lSET?: Moment;
  cET?: Moment;
  etlStatusId?: number;
  etlPkgId?: number;
}

export const defaultValue: Readonly<IDataFlows> = {};
