import { useEffect } from "react";
import { Outlet, useLocation, useNavigate } from "react-router-dom";
import { AuthProvider } from "../contexts/AuthContext";
import Navbar from "../components/Navbar";
import { useAuth } from "../contexts/AuthContext";


export default function Layout() {
    const { isAuthenticated } = useAuth("state");
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        if (location.pathname === "/" && isAuthenticated) {
            navigate("/home"); // Redirige a /home si está autenticado y está en "/"
        }
    }, [isAuthenticated, location, navigate]);

    return (
        <AuthProvider>
            <main>

                {location.pathname !== "/" && (
                    <div className="bg-black">
                        <Navbar />
                    </div>
                )}


                <div>
                    <Outlet />
                </div>
            </main>
        </AuthProvider>
    );
}
