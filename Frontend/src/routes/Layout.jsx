import { Outlet } from "react-router-dom";
import { AuthProvider } from "../contexts/AuthContext";
import Navbar from "../components/Navbar";
import Sidebar from "../components/SideBar";
function useAuth() {
    const currentUser = true; // Cambia esto a un objeto si deseas simular que el usuario está logueado
    return { currentUser };
}

export default function Layout() {
    const { currentUser } = useAuth(); // Obtén el estado del usuario logueado

    return (
        <AuthProvider>
            <main className="grid grid-rows-[auto_1fr_auto] grid-cols-[200px_auto] h-screen m-0 p-2.5 shadow-lg">
                <div className="row-span-1 col-span-2 m-0 border-x border-t border-gray-600 rounded-t-lg shadow-lg">
                    {/* <p className="text-center font-extrabold text-5xl">NAVBAR</p> */}
                    <Navbar />
                </div>

                {currentUser && (
                    <div className="flex row-span-2 m-0 border-l border-t border-gray-600 shadow-lg">
                        {/* <p className="text-center font-bold text-5xl">SIDEBAR</p> */}
                        <Sidebar />
                    </div>
                )}

                <div className={`row-span-1 bg-opacity-40 ${currentUser ? 'col-span-1' : 'col-span-2'} m-0 border-x border-gray-600 shadow-lg overflow-y-auto border-t`}>
                    {/* <p className="text-center font-extrabold text-5xl">OUTLET</p> */}
                    <Outlet />
                </div>

                <div className="row-span-1 col-span-2 border border-gray-600 rounded-b-lg shadow-lg m-0 h-12">
                    <div className="w-full h-full bg-opacity-45">
                        <p className="text-center font-extrabold text-5xl">FOOTER</p>
                    </div>
                </div>
            </main>
        </AuthProvider>
    );
}