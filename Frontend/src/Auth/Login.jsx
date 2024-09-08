import { useRef, useState } from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import { FaUser, FaLock, FaEye, FaEyeSlash } from 'react-icons/fa';

function Login() {
    const usernameRef = useRef("");
    const passwordRef = useRef("");
    const [isError, setIsError] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    const { login } = useAuth("actions");

    const [passwordVisible, setPasswordVisible] = useState(false);

    const togglePasswordVisibility = () => {
        setPasswordVisible(!passwordVisible);
    }

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
                    login(responseData.jwt, responseData.id);
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
                    //             login(responseData.jwt, profileData.user__id)
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
        // <section className="h-screen w-auto flex flex-col lg:flex-row justify-center items-center overflow-hidden">
        //     <div className="w-1/2 flex flex-col justify-center items-center">

        //         <form
        //             onSubmit={handleSubmit}
        //             className="flex flex-col justify-center items-center w-1/2"
        //         >
        //             <picture className="rounded-full">
        //                 <img src="/logo/Logo_maybe_2.png" alt="logo" />
        //             </picture>


        //             <div className="mb-4 w-full">
        //                 <label
        //                     htmlFor="username"
        //                     className="block text-sm font-bold mb-2"
        //                 >
        //                     Usuario:
        //                 </label>
        //                 <div className="relative">
        //                     <input
        //                         className="input input-bordered w-full pl-10"
        //                         type="text"
        //                         id="username"
        //                         name="username"
        //                         ref={usernameRef}
        //                     />
        //                     <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
        //                         <FaUser />
        //                     </div>
        //                 </div>
        //             </div>
        //             <div className="mb-6 w-full">
        //                 <label
        //                     htmlFor="password"
        //                     className="block text-sm font-bold mb-2"
        //                 >
        //                     Contraseña:
        //                 </label>
        //                 <div className="relative">
        //                     <input
        //                         className="input input-bordered w-full pl-10"
        //                         type={passwordVisible ? "text" : "password"}
        //                         id="password"
        //                         name="password"
        //                         ref={passwordRef}
        //                     />
        //                     <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
        //                         <FaLock />
        //                     </div>
        //                     <div
        //                         className="absolute inset-y-0 right-0 pr-3 flex items-center cursor-pointer"
        //                         onClick={togglePasswordVisibility}
        //                     >
        //                         {passwordVisible ? <FaEyeSlash /> : <FaEye />}
        //                     </div>
        //                 </div>
        //             </div>
        //             <div className="flex items-center justify-between w-full">
        //                 <button
        //                     type="submit"
        //                     className="btn btn-primary w-full">
        //                     {isLoading ? <div className="inline-flex"><span className="loading loading-spinner text-success"></span> <p>Loading...</p> </div> : "Login"}
        //                 </button>
        //             </div>
        //             {isError && (
        //                 <p className="mt-4 text-red-500 text-xs italic">
        //                     Error al cargar los datos.
        //                 </p>
        //             )}
        //         </form>
        //     </div>

        //     <div className="hidden md:block h-full border-primary border-[10px] m-[0px] p-[0px]"></div>

        //     <picture className="hidden md:block h-screen w-1/2">
        //         <img src="/img_login.png" alt="Login Image" />
        //     </picture>

        // </section>

        <div className="min-h-screen flex items-center justify-center overflow-hidden">
            <div className="card flex-col-reverse lg:flex-row-reverse">
                <div className="w-full lg:w-1/2 m-0 p-0">
                    <figure className="h-full">
                        <img
                            className="h-full w-full object-cover lg:border-l-[10px] border-primary"
                            src="/img_login.png"
                            alt="Login Image"
                        />
                    </figure>
                </div>

                <div className="bg-base-100 w-full lg:w-1/2 flex items-center justify-center">
                    <form onSubmit={handleSubmit} className="card-body max-w-sm mx-auto">
                        <img
                            className="w-[40vh] mx-auto mb-4"
                            src="/logo/Logo_maybe_2.png"
                            alt="Logo"
                        />

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text">Usuario:</span>
                            </label>
                            <input
                                id="username"
                                name="username"
                                ref={usernameRef}
                                type="text"
                                placeholder="Usuario"
                                className="input input-bordered"
                                required
                            />
                        </div>

                        <div className="form-control relative">
                            <label className="label">
                                <span className="label-text">Contraseña:</span>
                            </label>
                            <input
                                type={passwordVisible ? "text" : "password"}
                                id="password"
                                name="password"
                                ref={passwordRef}
                                placeholder="contraseña"
                                className="input input-bordered"
                                required
                            />
                            <div
                                className="absolute inset-y-0 right-0 pr-3 flex items-center cursor-pointer"
                                onClick={togglePasswordVisibility}
                            >
                                {passwordVisible ? <FaEyeSlash /> : <FaEye />}
                            </div>
                            <label className="label">
                                <Link to="/" className="label-text-alt link link-hover">¿Olvidaste tu contraseña?</Link>
                            </label>
                        </div>

                        <div className="form-control mt-4 flex items-center justify-between w-full">
                            <button
                                type="submit"
                                className="btn btn-primary btn-xs sm:btn-sm md:btn-md lg:btn-sm w-1/2 opacity-90 text-base-100">
                                {isLoading ? (
                                    <div className="inline-flex items-center">
                                        <span className="loading loading-spinner text-success"></span>
                                        <p className="ml-2">Iniciar...</p>
                                    </div>
                                ) : "Iniciar"}
                            </button>
                        </div>

                        {isError && (
                            <p className="mt-4 text-red-500 text-xs italic">
                                Error al cargar los datos.
                            </p>
                        )}
                    </form>
                </div>
            </div>
        </div>


    );
}

export default Login;