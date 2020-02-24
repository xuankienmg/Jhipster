import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDqStandardDetailsEntityText, defaultValue } from 'app/shared/model/dq-standard-details-entity-text.model';

export const ACTION_TYPES = {
  FETCH_DQSTANDARDDETAILSENTITYTEXT_LIST: 'dqStandardDetailsEntityText/FETCH_DQSTANDARDDETAILSENTITYTEXT_LIST',
  FETCH_DQSTANDARDDETAILSENTITYTEXT: 'dqStandardDetailsEntityText/FETCH_DQSTANDARDDETAILSENTITYTEXT',
  CREATE_DQSTANDARDDETAILSENTITYTEXT: 'dqStandardDetailsEntityText/CREATE_DQSTANDARDDETAILSENTITYTEXT',
  UPDATE_DQSTANDARDDETAILSENTITYTEXT: 'dqStandardDetailsEntityText/UPDATE_DQSTANDARDDETAILSENTITYTEXT',
  DELETE_DQSTANDARDDETAILSENTITYTEXT: 'dqStandardDetailsEntityText/DELETE_DQSTANDARDDETAILSENTITYTEXT',
  RESET: 'dqStandardDetailsEntityText/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDqStandardDetailsEntityText>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DqStandardDetailsEntityTextState = Readonly<typeof initialState>;

// Reducer

export default (state: DqStandardDetailsEntityTextState = initialState, action): DqStandardDetailsEntityTextState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTEXT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTEXT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYTEXT):
    case REQUEST(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYTEXT):
    case REQUEST(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYTEXT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTEXT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTEXT):
    case FAILURE(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYTEXT):
    case FAILURE(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYTEXT):
    case FAILURE(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYTEXT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTEXT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTEXT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYTEXT):
    case SUCCESS(ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYTEXT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYTEXT):
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

const apiUrl = 'api/dq-standard-details-entity-texts';

// Actions

export const getEntities: ICrudGetAllAction<IDqStandardDetailsEntityText> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTEXT_LIST,
    payload: axios.get<IDqStandardDetailsEntityText>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDqStandardDetailsEntityText> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DQSTANDARDDETAILSENTITYTEXT,
    payload: axios.get<IDqStandardDetailsEntityText>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDqStandardDetailsEntityText> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DQSTANDARDDETAILSENTITYTEXT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDqStandardDetailsEntityText> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DQSTANDARDDETAILSENTITYTEXT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDqStandardDetailsEntityText> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DQSTANDARDDETAILSENTITYTEXT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
