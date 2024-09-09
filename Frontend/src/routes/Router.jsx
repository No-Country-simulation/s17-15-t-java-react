import { createBrowserRouter } from "react-router-dom";
import Layout from "./Layout";
import ProtectedRoute from "./ProtectedRoute";

import Login from "../Auth/Login";
import Home from "../pages/Home";

import About from "../pages/About";
import NotFound from "../pages/NotFound";
import Profile from "../pages/Profile";
import PetDetail from "../pages/PetDetail";
import EditConsultation from "../components/consultationpet/editConsultation";

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
          path: "/home",
          element: (
            <ProtectedRoute>
              <Home />
            </ProtectedRoute>
          ),
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
          path: "/mascota",
          children: [
            {
              path: ":id",
              element: (
                <ProtectedRoute>
                  <PetDetail />
                </ProtectedRoute>
              ),
            },
          ],
        },
        {
          path: "/editConsult",
          element: <EditConsultation />,
        },
        // {
        //     path: "/login",
        //     element: <Login />,
        // },
      ],
    },
  ]
  // {
  //     basename: "/....",
  // }
);

export default Router;
