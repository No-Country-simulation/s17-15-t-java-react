import React, { useState, useEffect } from 'react';
import { FaPlus } from 'react-icons/fa';
import { useAuth } from '../../contexts/AuthContext.jsx';

// import useFormData from '../../../hooks/useFormData2.js';
import useJSON from '../../hooks/useJSON.js';
import CreatePet from './CreatePet.jsx';

import PetCard from './PetCard.jsx';
import useFetch from '../../hooks/useFetch.js';
import { TbRuler } from 'react-icons/tb';

import { GrFormNextLink } from "react-icons/gr";
import { GrFormPreviousLink } from "react-icons/gr";
import Status from '../UI/Status_7px.jsx';


function PetList(id) {
    const { jwt } = useAuth("state");
    const [modal, setModal] = useState(false);

    // if ( isArtistsLoading ) return <p>Cargando Artists...</p>;
    // if (isArtistsError ) return <p>Error al cargar artists</p>;
    // if (!artistsData) return <p>No hay datos disponibles de artist</p>;


    const baseURL = `https://veterinaria-bef3.onrender.com/`;
    const filter = `pet/owner/${id.id}`


    const {
        data, isLoading, isError,
        count, next, previous,
        createItem, isCreating, createError, newItemId,
        updateItem, isUpdating, updateError,
        deleteItem, isDeleting, deleteError,
        fetchData, fetchNextPage, fetchPreviousPage
    } = useJSON(baseURL, jwt, filter);



    // const [editModal, setEditModal] = useState(false);
    // const [currentAlbum, setCurrentAlbum] = useState(null);
    // const [taskList, setTaskList] = useState([]);

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

    // useEffect(() => {
    //     if (newItemId) {
    //         console.log(`Nuevo elemento creado con ID: ${newItemId}`);
    //         //para SONGS newItemId
    //     }
    // }, [newItemId]);


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

    // For artists
    // const artistsMap = {};
    // petsData?.results?.forEach(artist => {
    //     artistsMap[artist.id] = artist.name;
    // });

    /// LUEGO VER LOS ERRORES


    return (
        <>
            {/* <div className="header text-center mt-1"> */}
            {/* <h3 className="text-2xl font-bold mb-4">Albums List</h3> */}


            <div className="flex justify-between items-center pr-5 pb-2">
                <h2 className='font-semibold'>Mascota:</h2>
                <button
                    className="tooltip tooltip-right btn btn-xs bg-base-300 bg-opacity-90 text-base-100 px-2 inline-flex items-center justify-center text-[8px]" data-tip="Agregar Mascota"
                    onClick={() => setModal(true)}
                >

                    <FaPlus size={10} />



                </button>
                {/* <button className={`${previous ? "text-blue-500" : "text-gray-400 cursor-not-allowed"
                    }`} onClick={handlePreviousPage} disabled={!previous}><GrFormPreviousLink size={20} /></button> */}



                {/* <button className={`${next ? "text-blue-500" : "text-gray-400 cursor-not-allowed"
                    }`} onClick={handleNextPage} disabled={!next}><GrFormNextLink size={20} /></button> */}
            </div>
            {/* </div> */}

            {/* <div className="task-container grid gap-4 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 mt-6"> */}
            <div className="grid grid-cols-3 gap-2">
                {data?.map((obj) => (
                    <PetCard
                        key={obj.id}
                        petObj={obj}
                        deletePet={handleDelete}
                        updateListArray={handleEdit} // Pasar la función handleEdit para abrir el modal de edición
                    // artistName={artistsMap[obj.artist]}
                    />

                ))}
            </div>


            <CreatePet toggle={() => setModal(!modal)} modal={modal} save={handleSave} idpet={id.id} />


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

        </>
    );
};

export default PetList;