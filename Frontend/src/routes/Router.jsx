import { createBrowserRouter } from "react-router-dom";
import Layout from "./Layout";
import ProtectedRoute from "./ProtectedRoute";

import Login from "../Auth/Login";
import Home from "../pages/Home";

import About from "../pages/About";
import NotFound from "../pages/NotFound";
import Profile from "../pages/Profile";

const Router = createBrowserRouter(
    [
        {
            element: <Layout />,
            children: [
                {
                    path: "/",
                    element: <Login />,
                },
                {
                    path: "/profile",
                    element: (
                        <ProtectedRoute>
                            <Profile />
                        </ProtectedRoute>
                    ),
                },
                {
                    path: "/login",
                    element: <Login />,
                },            

            ],
        },
    ],
    // {
    //     basename: "/....",
    // }
);

export default Router;