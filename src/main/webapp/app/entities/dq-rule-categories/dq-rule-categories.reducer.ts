import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqRuleCategories, defaultValue } from 'app/shared/model/dq-rule-categories.model';

export const ACTION_TYPES = {
  FETCH_DQRULECATEGORIES_LIST: 'dqRuleCategories/FETCH_DQRULECATEGORIES_LIST',
  FETCH_DQRULECATEGORIES: 'dqRuleCategories/FETCH_DQRULECATEGORIES',
  CREATE_DQRULECATEGORIES: 'dqRuleCategories/CREATE_DQRULECATEGORIES',
  UPDATE_DQRULECATEGORIES: 'dqRuleCategories/UPDATE_DQRULECATEGORIES',
  DELETE_DQRULECATEGORIES: 'dqRuleCategories/DELETE_DQRULECATEGORIES',
  RESET: 'dqRuleCategories/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqRuleCategories>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqRuleCategoriesState = Readonly<typeof initialState>;

// Reducer

export default (state: DqRuleCategoriesState = initialState, action): DqRuleCategoriesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQRULECATEGORIES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQRULECATEGORIES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQRULECATEGORIES):
    case REQUEST(ACTION_TYPES.UPDATE_DQRULECATEGORIES):
    case REQUEST(ACTION_TYPES.DELETE_DQRULECATEGORIES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQRULECATEGORIES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQRULECATEGORIES):
    case FAILURE(ACTION_TYPES.CREATE_DQRULECATEGORIES):
    case FAILURE(ACTION_TYPES.UPDATE_DQRULECATEGORIES):
    case FAILURE(ACTION_TYPES.DELETE_DQRULECATEGORIES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULECATEGORIES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQRULECATEGORIES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQRULECATEGORIES):
    case SUCCESS(ACTION_TYPES.UPDATE_DQRULECATEGORIES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQRULECATEGORIES):
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

const apiUrl = 'api/dq-rule-categories';

// Actions

export const getEntities: ICrudGetAllAction<IDqRuleCategories> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULECATEGORIES_LIST,
    payload: axios.get<IDqRuleCategories>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqRuleCategories> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQRULECATEGORIES,
    payload: axios.get<IDqRuleCategories>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqRuleCategories> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQRULECATEGORIES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqRuleCategories> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQRULECATEGORIES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqRuleCategories> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQRULECATEGORIES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
