import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqRuleStatus, defaultValue } from 'app/shared/model/dq-rule-status.model';

export const ACTION_TYPES = {
  FETCH_DQRULESTATUS_LIST: 'dqRuleStatus/FETCH_DQRULESTATUS_LIST',
  FETCH_DQRULESTATUS: 'dqRuleStatus/FETCH_DQRULESTATUS',
  CREATE_DQRULESTATUS: 'dqRuleStatus/CREATE_DQRULESTATUS',
  UPDATE_DQRULESTATUS: 'dqRuleStatus/UPDATE_DQRULESTATUS',
  DELETE_DQRULESTATUS: 'dqRuleStatus/DELETE_DQRULESTATUS',
  RESET: 'dqRuleStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqRuleStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqRuleStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: DqRuleStatusState = initialState, action): DqRuleStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQRULESTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQRULESTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQRULESTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_DQRULESTATUS):
    case REQUEST(ACTION_TYPES.DELETE_DQRULESTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQRULESTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQRULESTATUS):
    case FAILURE(ACTION_TYPES.CREATE_DQRULESTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_DQRULESTATUS):
    case FAILURE(ACTION_TYPES.DELETE_DQRULESTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULESTATUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULESTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQRULESTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_DQRULESTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQRULESTATUS):
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

const apiUrl = 'api/dq-rule-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IDqRuleStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULESTATUS_LIST,
    payload: axios.get<IDqRuleStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqRuleStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULESTATUS,
    payload: axios.get<IDqRuleStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqRuleStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQRULESTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqRuleStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQRULESTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqRuleStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQRULESTATUS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
