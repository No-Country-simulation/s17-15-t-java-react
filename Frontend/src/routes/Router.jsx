import { createBrowserRouter } from "react-router-dom";
import Layout from "./Layout";
import ProtectedRoute from "./ProtectedRoute";

import Login from "../Auth/Login";
import Home from "../pages/Home";

import About from "../pages/About";
import NotFound from "../pages/NotFound";
import Profile from "../pages/Profile";
import PetDetail2 from "../pages/PetDetail2";
import EditarPet from "../pages/EditarPet";
import ConsultationDetail from "../pages/consultation/ConsultationDetail";
import ConsultationEditar from "../pages/consultation/ConsultationEditar";
import ConsultationCrear from "../pages/consultation/ConsultationCrear";





import ClinicHistoryPage from "../pages/ClincHistoryPage";

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
                    element:
                        (
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
                                    <PetDetail2 />
                                </ProtectedRoute>
                            ),
                        },
                    ]

                },
                {
                    path: "/mascota/editar",
                    children: [
                        {
                            path: ":id",
                            element: (                             
                                    <EditarPet />                            
                            ),
                        },
                    ]

                },

                {
                    path: "/consulta",
                    children: [
                        {
                            path: ":id",
                            element: <ConsultationDetail />,
                        },
                        {
                            path: "crear/:id",
                            element: <ConsultationCrear />,
                        },
                        {
                            path: "editar/:id",
                            element: <ConsultationEditar />,
                        },
                    ]
                },

                {
                    path: "/historia-clinica",
                    children: [
                        {
                            path: ":id",
                            element: (                             
                                    <ClinicHistoryPage />                            
                            ),
                        },
                    ]

                },
                


                
                // {
                //     path: "/login",
                //     element: <Login />,
                // },            

            ],
        },
    ],
    // {
    //     basename: "/....",
    // }
);

export default Router;