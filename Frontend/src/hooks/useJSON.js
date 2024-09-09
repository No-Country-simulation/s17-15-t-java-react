import { useReducer, useCallback } from 'react';

const initialState = {
    data: [],
    isLoading: false,
    isError: false,
    isCreating: false,
    isUpdating: false,
    isDeleting: false,
    createError: null,
    updateError: null,
    deleteError: null,
    newItemId: null, // ID de nuevo elemento creado
    count: 0,        // Cantidad total de elementos
    next: null,      // pagina siguiente
    previous: null,  // pagina anterior
};

const actionTypes = {
    FETCH_INIT: 'FETCH_INIT',
    FETCH_SUCCESS: 'FETCH_SUCCESS',
    FETCH_FAILURE: 'FETCH_FAILURE',
    CREATE_INIT: 'CREATE_INIT',
    CREATE_SUCCESS: 'CREATE_SUCCESS',
    CREATE_FAILURE: 'CREATE_FAILURE',
    UPDATE_INIT: 'UPDATE_INIT',
    UPDATE_SUCCESS: 'UPDATE_SUCCESS',
    UPDATE_FAILURE: 'UPDATE_FAILURE',
    DELETE_INIT: 'DELETE_INIT',
    DELETE_SUCCESS: 'DELETE_SUCCESS',
    DELETE_FAILURE: 'DELETE_FAILURE',
};

const apiReducer = (state, action) => {
    switch (action.type) {
        case actionTypes.FETCH_INIT:
            return {
                ...state,
                isLoading: true,
                isError: false
            };

        case actionTypes.FETCH_SUCCESS:
            return {
                ...state,
                isLoading: false,
                data: action.payload,
                count: action.payload.count,
                next: action.payload.next ? action.payload.next.replace('http://', 'https://') : null,
                previous: action.payload.previous ? action.payload.previous.replace('http://', 'https://') : null
            };

        case actionTypes.FETCH_FAILURE:
            return { ...state, isLoading: false, isError: true };

        case actionTypes.CREATE_INIT:
            return { ...state, isCreating: true, createError: null };
        case actionTypes.CREATE_SUCCESS:
            return { ...state, isCreating: false, data: [...state.data, action.payload] };
        case actionTypes.CREATE_FAILURE:
            return { ...state, isCreating: false, createError: action.error };

        case actionTypes.UPDATE_INIT:
            return { ...state, isUpdating: true, updateError: null };
        case actionTypes.UPDATE_SUCCESS:
            return {
                ...state,
                isUpdating: false,
                data: state.data.map(item =>
                    item.id === action.payload.id ? action.payload : item
                ),
            };
        case actionTypes.UPDATE_FAILURE:
            return { ...state, isUpdating: false, updateError: action.error };

        case actionTypes.DELETE_INIT:
            return { ...state, isDeleting: true, deleteError: null };
        case actionTypes.DELETE_SUCCESS:
            return {
                ...state,
                isDeleting: false,
                data: state.data.filter(item => item.id !== action.payload),
            };
        case actionTypes.DELETE_FAILURE:
            return { ...state, isDeleting: false, deleteError: action.error };
        default:
            throw new Error(`Unknown action type: ${action.type}`);
    }
};

const useFormData = (baseURL, token, filter = '') => {
    const [state, dispatch] = useReducer(apiReducer, initialState);

    const fetchRequest = async (url, options) => {
        try {
            const response = await fetch(url, options);
            if (!response.ok) {
                const errorData = await response.json();
                console.error('Server responded with:', errorData);
                throw new Error('Request failed');
            }
            const text = await response.text();
            return text ? JSON.parse(text) : null;
        } catch (error) {
            console.error('Error:', error);
            throw error;
        }
    };

    // GET - PAGINACIÓN
    const fetchData = useCallback(async (pageURL = `${baseURL}${filter}`) => {
        dispatch({ type: actionTypes.FETCH_INIT });
        try {
            const result = await fetchRequest(pageURL, {
                headers: {
                    'Authorization': `Token ${token}`
                }
            });
            dispatch({ type: actionTypes.FETCH_SUCCESS, payload: result });
        } catch (error) {
            dispatch({ type: actionTypes.FETCH_FAILURE });
        }
    }, [baseURL, token]);

    // NEXT
    const fetchNextPage = useCallback(async () => {
        if (state.next) {
            await fetchData(state.next);
        }
    }, [state.next, fetchData]);

    // PREVIOUS
    const fetchPreviousPage = useCallback(async () => {
        if (state.previous) {
            await fetchData(state.previous);
        }
    }, [state.previous, fetchData]);

    // CREATE
    const createItem = useCallback(async (jsonData) => {
        dispatch({ type: actionTypes.CREATE_INIT });
        try {
            const result = await fetchRequest(baseURL, {
                method: 'POST',
                headers: {
                    'Authorization': `Token ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(jsonData) // Enviamos datos como JSON
            });
            dispatch({ type: actionTypes.CREATE_SUCCESS, payload: result });
        } catch (error) {
            dispatch({ type: actionTypes.CREATE_FAILURE, error: error.message });
        }
    }, [baseURL, token]);

    // UPDATE
    const updateItem = useCallback(async (jsonData, id) => {
        dispatch({ type: actionTypes.UPDATE_INIT });
        try {
            const result = await fetchRequest(`${baseURL}/${id}`, {
                method: 'PUT',
                headers: {
                    'Authorization': `Token ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(jsonData) // Enviamos datos como JSON
            });
            dispatch({ type: actionTypes.UPDATE_SUCCESS, payload: result });
        } catch (error) {
            dispatch({ type: actionTypes.UPDATE_FAILURE, error: error.message });
        }
    }, [baseURL, token]);

    // DELETE
    const deleteItem = useCallback(async (id) => {
        dispatch({ type: actionTypes.DELETE_INIT });
        try {
            await fetchRequest(`${baseURL}/${id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Token ${token}`
                }
            });
            dispatch({ type: actionTypes.DELETE_SUCCESS, payload: id });
        } catch (error) {
            dispatch({ type: actionTypes.DELETE_FAILURE, error: error.message });
        }
    }, [baseURL, token]);

    return {
        ...state, // TODOS LOS ESTADOS
        fetchData,
        fetchNextPage,
        fetchPreviousPage,
        createItem,
        updateItem,
        deleteItem
    };
};

export default useFormData;
