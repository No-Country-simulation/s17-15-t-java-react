import { createContext, useReducer, useContext } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const AuthContext = createContext({
    state: {},
    actions: {},
});

const ACTIONS = {
    LOGIN: "LOGIN",
    LOGOUT: "LOGOUT",
};

function reducer(state, action) {
    switch (action.type) {
        case ACTIONS.LOGIN:
            return {
                ...state,
                user__id: action.payload.user__id,
                jwt: action.payload.jwt,
                isAuthenticated: true,
            };
        case ACTIONS.LOGOUT:
            return {
                isAuthenticated: false,
            };
        default:
            return state;
    }
}

function AuthProvider({ children }) {
    const [state, dispatch] = useReducer(reducer, {
        user__id: localStorage.getItem("user__id"),
        jwt: localStorage.getItem("authToken"),
        isAuthenticated: localStorage.getItem("authToken") ? true : false,
      
    });
    const navigate = useNavigate();
    const location = useLocation();



    const actions = {
        login: (jwt, user__id) => {
            dispatch({
                type: ACTIONS.LOGIN,
                payload: { jwt, user__id },
            });
            localStorage.setItem("authToken", jwt);
            localStorage.setItem("user__id", user__id);
            const origin = location.state?.from?.pathname || "/home";
            navigate(origin);
        },
        logout: () => {
            dispatch({ type: ACTIONS.LOGOUT });
            localStorage.removeItem("authToken");
            localStorage.removeItem("user__id");
            navigate("/");  // Redirigir a 
        },
    };

    return (
        <AuthContext.Provider value={{ state, actions }}>
            {children}
        </AuthContext.Provider>
    );
}

function useAuth(type) {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error("useAuth must be used within an AuthProvider");
    }
    return context[type];
}

export { AuthContext, AuthProvider, useAuth };