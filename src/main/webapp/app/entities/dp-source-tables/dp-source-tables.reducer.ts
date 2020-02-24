import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDpSourceTables, defaultValue } from 'app/shared/model/dp-source-tables.model';

export const ACTION_TYPES = {
  FETCH_DPSOURCETABLES_LIST: 'dpSourceTables/FETCH_DPSOURCETABLES_LIST',
  FETCH_DPSOURCETABLES: 'dpSourceTables/FETCH_DPSOURCETABLES',
  CREATE_DPSOURCETABLES: 'dpSourceTables/CREATE_DPSOURCETABLES',
  UPDATE_DPSOURCETABLES: 'dpSourceTables/UPDATE_DPSOURCETABLES',
  DELETE_DPSOURCETABLES: 'dpSourceTables/DELETE_DPSOURCETABLES',
  RESET: 'dpSourceTables/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDpSourceTables>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DpSourceTablesState = Readonly<typeof initialState>;

// Reducer

export default (state: DpSourceTablesState = initialState, action): DpSourceTablesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DPSOURCETABLES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DPSOURCETABLES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DPSOURCETABLES):
    case REQUEST(ACTION_TYPES.UPDATE_DPSOURCETABLES):
    case REQUEST(ACTION_TYPES.DELETE_DPSOURCETABLES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DPSOURCETABLES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DPSOURCETABLES):
    case FAILURE(ACTION_TYPES.CREATE_DPSOURCETABLES):
    case FAILURE(ACTION_TYPES.UPDATE_DPSOURCETABLES):
    case FAILURE(ACTION_TYPES.DELETE_DPSOURCETABLES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DPSOURCETABLES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DPSOURCETABLES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DPSOURCETABLES):
    case SUCCESS(ACTION_TYPES.UPDATE_DPSOURCETABLES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DPSOURCETABLES):
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

const apiUrl = 'api/dp-source-tables';

// Actions

export const getEntities: ICrudGetAllAction<IDpSourceTables> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DPSOURCETABLES_LIST,
    payload: axios.get<IDpSourceTables>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDpSourceTables> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DPSOURCETABLES,
    payload: axios.get<IDpSourceTables>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDpSourceTables> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DPSOURCETABLES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDpSourceTables> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DPSOURCETABLES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDpSourceTables> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DPSOURCETABLES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
