import { Outlet } from "react-router-dom";
import { AuthProvider } from "../contexts/AuthContext";
import Navbar from "../components/Navbar";

export default function Layout() {
    const currentUser = null;

    return (
        <AuthProvider>
            <main>
                {currentUser && (
                    <div className="">
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
