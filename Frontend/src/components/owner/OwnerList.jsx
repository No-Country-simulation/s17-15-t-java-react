import React, { useState, useEffect } from 'react';
import { FaPlus } from 'react-icons/fa';
import { useAuth } from '../../contexts/AuthContext.jsx';
// import useFormData from '../../hooks/useFormData.js';
import CreateOwner from './CreateOwner.jsx';
import OwnerCard from './OwnerCard.jsx';

import Status from '../UI/Status.jsx';
import useFormData from '../../hooks/useJSON.js';


function OwnerList() {
    const { jwt, user__id } = useAuth("state");
    const [modal, setModal] = useState(false);
    const baseURL = "https://veterinaria-bef3.onrender.com/owner";
    const filtro = ``;


    const {
        data, isLoading, isError,
        count, next, previous,
        createItem, isCreating, createError, newItemId,
        updateItem, isUpdating, updateError,
        deleteItem, isDeleting, deleteError,
        fetchData, fetchNextPage, fetchPreviousPage
    } = useFormData(baseURL, jwt);


    //READ
    useEffect(() => {
        fetchData(); // Obtener la lista de tareas al montar el componente
    }, [fetchData]);

    // useEffect(() => {
    //     if (data) {
    //         setArtistList(data); // Asumiendo que los datos están en la propiedad'results'
    //     }
    // }, [data]);


    // Para navegar paginacion
    // const handleNextPage = () => {
    //     if (next) fetchNextPage();
    // };

    // const handlePreviousPage = () => {
    //     if (previous) fetchPreviousPage();
    // };



    // CREATE
    const handleSave = (fromData) => {
        if (!isCreating) {
            createItem(fromData);
            setModal(false);

        }
    };

    // UPDATE
    const handleEdit = (fromData, id) => {
        updateItem(fromData, id);


    };

    //DELETE
    const handleDelete = (id) => {
        if (!isDeleting) {
            deleteItem(id);


        }
    };


    // Filtrar los álbumes para mostrar solo los del owner con user__id = 29
    // const filteredArtist= data.filter(artist => artist.owner == user__id);


    /// LUEGO VER LOS ERRORES

    return (
        <>
            <div className="header text-center mt-5">

                <Status
                    isLoading={isLoading}
                    isError={isError}
                    isCreating={isCreating}
                    createError={createError}
                    isUpdating={isUpdating}
                    updateError={updateError}
                    isDeleting={isDeleting}
                    deleteError={deleteError}
                />

                <h3 className="text-2xl font-bold mb-4">

                    {/* <h3 class="text-4xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-red-700 via-green-700 to-blue-700"> */}
                    Aqui va la barra Busqueda</h3>

                <button
                    className="bg-primary text-white p-2 rounded-full inline-flex items-center justify-center bg-opacity-90"
                    onClick={() => setModal(true)}
                >
                    <FaPlus className="mr-2" />
                    Crear Propietario
                </button>
            </div>
            {/* {isLoading && <p className="text-gray-500">Loading...</p>}
            {isError && <p className="text-red-500">Error fetching data</p>} */}
            {/* <div className="task-container grid gap-4 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 mt-6"> */}
            <div className="px-10 grid gap-2 grid-cols-1 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3 mt-6 items-center justify-center">
                {data?.map((obj) => (
                    <OwnerCard
                        key={obj.id}
                        ownerObj={obj}
                        deleteOwner={handleDelete}
                        updateListArray={handleEdit} // Pasar la función handleEdit para abrir el modal de edición
                    />

                ))}
            </div>

            <CreateOwner toggle={() => setModal(!modal)} modal={modal} save={handleSave} />


            {/* <div className="join grid grid-cols-2">
                <button className="join-item btn btn-outline" onClick={handlePreviousPage} disabled={!previous}>Previous page</button>
                <button className="join-item btn btn-outline" onClick={handleNextPage} disabled={!next}>Next</button>
            </div> */}
        </>
    );
};

export default OwnerList;