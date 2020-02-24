import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqRuleActions, defaultValue } from 'app/shared/model/dq-rule-actions.model';

export const ACTION_TYPES = {
  FETCH_DQRULEACTIONS_LIST: 'dqRuleActions/FETCH_DQRULEACTIONS_LIST',
  FETCH_DQRULEACTIONS: 'dqRuleActions/FETCH_DQRULEACTIONS',
  CREATE_DQRULEACTIONS: 'dqRuleActions/CREATE_DQRULEACTIONS',
  UPDATE_DQRULEACTIONS: 'dqRuleActions/UPDATE_DQRULEACTIONS',
  DELETE_DQRULEACTIONS: 'dqRuleActions/DELETE_DQRULEACTIONS',
  RESET: 'dqRuleActions/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqRuleActions>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqRuleActionsState = Readonly<typeof initialState>;

// Reducer

export default (state: DqRuleActionsState = initialState, action): DqRuleActionsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQRULEACTIONS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQRULEACTIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQRULEACTIONS):
    case REQUEST(ACTION_TYPES.UPDATE_DQRULEACTIONS):
    case REQUEST(ACTION_TYPES.DELETE_DQRULEACTIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQRULEACTIONS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQRULEACTIONS):
    case FAILURE(ACTION_TYPES.CREATE_DQRULEACTIONS):
    case FAILURE(ACTION_TYPES.UPDATE_DQRULEACTIONS):
    case FAILURE(ACTION_TYPES.DELETE_DQRULEACTIONS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULEACTIONS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULEACTIONS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQRULEACTIONS):
    case SUCCESS(ACTION_TYPES.UPDATE_DQRULEACTIONS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQRULEACTIONS):
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

const apiUrl = 'api/dq-rule-actions';

// Actions

export const getEntities: ICrudGetAllAction<IDqRuleActions> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULEACTIONS_LIST,
    payload: axios.get<IDqRuleActions>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqRuleActions> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULEACTIONS,
    payload: axios.get<IDqRuleActions>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqRuleActions> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQRULEACTIONS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqRuleActions> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQRULEACTIONS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqRuleActions> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQRULEACTIONS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
