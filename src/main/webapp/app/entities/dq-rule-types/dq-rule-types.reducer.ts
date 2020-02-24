import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqRuleTypes, defaultValue } from 'app/shared/model/dq-rule-types.model';

export const ACTION_TYPES = {
  FETCH_DQRULETYPES_LIST: 'dqRuleTypes/FETCH_DQRULETYPES_LIST',
  FETCH_DQRULETYPES: 'dqRuleTypes/FETCH_DQRULETYPES',
  CREATE_DQRULETYPES: 'dqRuleTypes/CREATE_DQRULETYPES',
  UPDATE_DQRULETYPES: 'dqRuleTypes/UPDATE_DQRULETYPES',
  DELETE_DQRULETYPES: 'dqRuleTypes/DELETE_DQRULETYPES',
  RESET: 'dqRuleTypes/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqRuleTypes>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqRuleTypesState = Readonly<typeof initialState>;

// Reducer

export default (state: DqRuleTypesState = initialState, action): DqRuleTypesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQRULETYPES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQRULETYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQRULETYPES):
    case REQUEST(ACTION_TYPES.UPDATE_DQRULETYPES):
    case REQUEST(ACTION_TYPES.DELETE_DQRULETYPES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQRULETYPES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQRULETYPES):
    case FAILURE(ACTION_TYPES.CREATE_DQRULETYPES):
    case FAILURE(ACTION_TYPES.UPDATE_DQRULETYPES):
    case FAILURE(ACTION_TYPES.DELETE_DQRULETYPES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULETYPES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULETYPES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQRULETYPES):
    case SUCCESS(ACTION_TYPES.UPDATE_DQRULETYPES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQRULETYPES):
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

const apiUrl = 'api/dq-rule-types';

// Actions

export const getEntities: ICrudGetAllAction<IDqRuleTypes> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULETYPES_LIST,
    payload: axios.get<IDqRuleTypes>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqRuleTypes> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULETYPES,
    payload: axios.get<IDqRuleTypes>(requestUrl)
  };
};

export const getSearchEntity: ICrudGetAction<IDqRuleTypes> = value => {
  const requestUrl = `${apiUrl}?typeName.contains=${value}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULETYPES_LIST,
    payload: axios.get<IDqRuleTypes>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqRuleTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQRULETYPES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqRuleTypes> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQRULETYPES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqRuleTypes> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQRULETYPES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
