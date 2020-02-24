export interface IEtlPackages {
  id?: number;
  etlPkgName?: string;
  etlPkgDescription?: string;
  etlPkgSchedule?: string;
}

export const defaultValue: Readonly<IEtlPackages> = {};
