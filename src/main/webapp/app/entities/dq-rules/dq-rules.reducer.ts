import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqRules, defaultValue } from 'app/shared/model/dq-rules.model';

export const ACTION_TYPES = {
  FETCH_DQRULES_LIST: 'dqRules/FETCH_DQRULES_LIST',
  FETCH_DQRULES: 'dqRules/FETCH_DQRULES',
  CREATE_DQRULES: 'dqRules/CREATE_DQRULES',
  UPDATE_DQRULES: 'dqRules/UPDATE_DQRULES',
  DELETE_DQRULES: 'dqRules/DELETE_DQRULES',
  RESET: 'dqRules/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqRules>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqRulesState = Readonly<typeof initialState>;

// Reducer

export default (state: DqRulesState = initialState, action): DqRulesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQRULES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQRULES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQRULES):
    case REQUEST(ACTION_TYPES.UPDATE_DQRULES):
    case REQUEST(ACTION_TYPES.DELETE_DQRULES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQRULES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQRULES):
    case FAILURE(ACTION_TYPES.CREATE_DQRULES):
    case FAILURE(ACTION_TYPES.UPDATE_DQRULES):
    case FAILURE(ACTION_TYPES.DELETE_DQRULES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQRULES):
    case SUCCESS(ACTION_TYPES.UPDATE_DQRULES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQRULES):
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

const apiUrl = 'api/dq-rules';

// Actions

export const getEntities: ICrudGetAllAction<IDqRules> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULES_LIST,
    payload: axios.get<IDqRules>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqRules> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULES,
    payload: axios.get<IDqRules>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqRules> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQRULES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqRules> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQRULES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqRules> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQRULES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
