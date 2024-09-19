import { useRef, useState } from "react";
import {useAuth}  from "../contexts/AuthContext";
import { FaUser, FaLock } from "react-icons/fa";

function Login() {
    const usernameRef = useRef("");
    const passwordRef = useRef("");
    const [isError, setIsError] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    const { login } = useAuth("actions");

    function handleSubmit(event) {
        event.preventDefault();
        if (!isLoading) {
            setIsLoading(true);
            fetch(`${import.meta.env.VITE_API_BASE_URL}auth/login`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    username: usernameRef.current.value,
                    password: passwordRef.current.value,
                }),
            })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error("No se pudo iniciar sesión");
                    }
                    return response.json();
                })
                .then((responseData) => {
                    login(responseData.jwt);
                    // if (responseData.jwt) {
                    //     fetch(
                    //         `${
                    //             import.meta.env.VITE_API_BASE_URL
                    //         }users/profiles/profile_data/`,
                    //         {
                    //             method: "GET",
                    //             headers: {
                    //                 Authorization: `Token ${responseData.jwt}`,
                    //             },
                    //         }
                    //     )
                    //         .then((profileResponse) => {
                    //             if (!profileResponse.ok) {
                    //                 throw new Error(
                    //                     "Error al obtener id de usuario"
                    //                 );
                    //             }
                    //             return profileResponse.json();
                    //         })
                    //         .then((profileData) =>
                    //             login(responseData.token, profileData.user__id)
                    //         )
                    //         .catch((error) => {
                    //             console.error(
                    //                 "Error al obtener id de usuario",
                    //                 error
                    //             );
                    //             setIsError(true);
                    //         });
                    // }
                })
                .catch((error) => {
                    console.error("Error al iniciar sesión", error);
                    setIsError(true);
                })
                .finally(() => {
                    setIsLoading(false);
                });
        }
    }

    return (
        <section className="flex justify-center items-center min-h-screen bg-base-100 bg-opacity-80">
            <div className="w-full max-w-md">
                <form
                    onSubmit={handleSubmit}
                    className="bg-base-300 shadow-lg rounded-lg px-8 pt-6 pb-8 mb-4"
                >
                    <div className="mb-4">
                        <label
                            htmlFor="username"
                            className="block  text-sm font-bold mb-2"
                        >
                            Username:
                        </label>
                        <div className="relative">
                            <input
                                className="input input-bordered w-full pl-10"
                                type="text"
                                id="username"
                                name="username"
                                ref={usernameRef}
                            />
                            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <FaUser className="" />
                            </div>
                        </div>
                    </div>
                    <div className="mb-6">
                        <label
                            htmlFor="password"
                            className="block  text-sm font-bold mb-2"
                        >
                            Password:
                        </label>
                        <div className="relative">
                            <input
                                className="input input-bordered w-full pl-10"
                                type="password"
                                id="password"
                                name="password"
                                ref={passwordRef}
                            />
                            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <FaLock className="text-gray-400" />
                            </div>
                        </div>
                    </div>
                    <div className="flex items-center justify-between">
                        <button
                            type="submit"
                            className="btn btn-primary w-full">
                            {isLoading ? <div className="inline-flex"><span className="loading loading-spinner text-success"></span> <p>Loading...</p> </div>: "Login"}
                        </button>
                    </div>
                    {isError && (
                        <p className="mt-4 text-red-500 text-xs italic">
                            Error al cargar los datos.
                        </p>
                    )}
                </form>
            </div>
        </section>
    );
}

export default Login;