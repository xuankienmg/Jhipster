import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtlStatus, defaultValue } from 'app/shared/model/etl-status.model';

export const ACTION_TYPES = {
  FETCH_ETLSTATUS_LIST: 'etlStatus/FETCH_ETLSTATUS_LIST',
  FETCH_ETLSTATUS: 'etlStatus/FETCH_ETLSTATUS',
  CREATE_ETLSTATUS: 'etlStatus/CREATE_ETLSTATUS',
  UPDATE_ETLSTATUS: 'etlStatus/UPDATE_ETLSTATUS',
  DELETE_ETLSTATUS: 'etlStatus/DELETE_ETLSTATUS',
  RESET: 'etlStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtlStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type EtlStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: EtlStatusState = initialState, action): EtlStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETLSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETLSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETLSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_ETLSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_ETLSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETLSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETLSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_ETLSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_ETLSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_ETLSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETLSTATUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETLSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETLSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_ETLSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETLSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/etl-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IEtlStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ETLSTATUS_LIST,
    payload: axios.get<IEtlStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IEtlStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETLSTATUS,
    payload: axios.get<IEtlStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtlStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETLSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtlStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETLSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtlStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETLSTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
