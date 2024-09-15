import React, { useState, useEffect } from 'react';
import { FaPlus } from 'react-icons/fa';
import { useAuth } from '../../contexts/AuthContext.jsx';
// import useFormData from '../../hooks/useFormData.js';
import CreateOwner from './CreateOwner.jsx';
import OwnerCard from './OwnerCard.jsx';

import Status from '../UI/Status.jsx';
import useFormData from '../../hooks/useJSON.js';

import PetList from '../pet/PetList.jsx';
import CreatePet from '../pet/CreatePet.jsx';

import { FaUserPlus } from "react-icons/fa6";


function OwnerList() {
    const { jwt, user__id } = useAuth("state");
    const [modal, setModal] = useState(false);
    const baseURL = "https://veterinaria-bef3.onrender.com/owner";
    const filtro = ``;

    const [showPetModal, setShowPetModal] = useState(false);
    const [newOwnerId, setNewOwnerId] = useState(null);


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

    useEffect(() => {
        if (newItemId) {
            setNewOwnerId(newItemId); // para guardar el ID del nuevo owner
            setShowPetModal(true); // para mostrar el modal para crear mascota
        }
    }, [newItemId]);

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
        <h1 className='card-title pl-10 pt-10 font-bold'>¡Hola de Nuevo, Diana!</h1>
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


                <div className='flex justify-end mr-10'>
                    <button
                        className="btn bg-orange-400 bg-opacity-90 text-white p-2 inline-flex items-center justify-center  shadow-xl"
                        onClick={() => setModal(true)}
                    >
                        <span className='font-semibold pr-1'>Nuevo registro </span>
                        <FaUserPlus className="font-semibold mr-2" size={20} />

                    </button>
                </div>

                <h3 className="text-2xl font-bold mb-4">

                    {/* <h3 class="text-4xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-red-700 via-green-700 to-blue-700"> */}
                    Aqui va la barra Busqueda
                </h3>

            </div>
            {/* {isLoading && <p className="text-gray-500">Loading...</p>}
            {isError && <p className="text-red-500">Error fetching data</p>} */}
            {/* <div className="task-container grid gap-4 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 mt-6"> */}
            <div className="px-14 pb-5 grid gap-4 grid-cols-1 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-4 mt-6 items-center justify-center">
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
            {/* Modal de Crear Mascota */}
            {/* {showPetModal && (
                <CreatePet
                    toggle={() => setShowPetModal(!showPetModal)}
                    modal={showPetModal}
                    idOwner={newOwnerId}
                    save={PetList.handleSave} // Pasar la función handleSave para guardar la mascota
                />
            )} */}


        </>
    );
};

export default OwnerList;