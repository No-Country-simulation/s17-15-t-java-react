import React, { useState, useEffect } from 'react';
import { FaPlus } from 'react-icons/fa';
import { useAuth } from '../../contexts/AuthContext.jsx';

// import useFormData from '../../../hooks/useFormData2.js';
import useJSON from '../../hooks/useJSON.js';


import useFetch from '../../hooks/useFetch.js';
import { TbRuler } from 'react-icons/tb';

import { GrFormNextLink } from "react-icons/gr";
import { GrFormPreviousLink } from "react-icons/gr";
import Status from '../UI/Status_7px.jsx';
import ConsultationCard from './ConsultationCard.jsx';
import CreateConsultation from './CreateConsultation.jsx';


function ConsultationList(id) {
    const { jwt } = useAuth("state");
    const [modal, setModal] = useState(false);



    const baseURL = `https://veterinaria-bef3.onrender.com/consultation`;
    const filter = `/pet/${id.id}`


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


            <div className="flex justify-end items-center pb-3">
            <button
                    className="tooltip tooltip-top btn btn-xs bg-base-300 bg-opacity-90 text-base-100 inline-flex items-center justify-center text-[8px] overflow-ellipsis border-base-300" data-tip="Agregar consulta"
                    onClick={() => setModal(true)}
                >
                    <FaPlus size={10} /> Agregar

                </button>
                {/* <h2 className='font-semibold'>Consultas:</h2> */}

                {/* <button className={`${previous ? "text-blue-500" : "text-gray-400 cursor-not-allowed"
                    }`} onClick={handlePreviousPage} disabled={!previous}><GrFormPreviousLink size={20} /></button> */}



                {/* <button className={`${next ? "text-blue-500" : "text-gray-400 cursor-not-allowed"
                    }`} onClick={handleNextPage} disabled={!next}><GrFormNextLink size={20} /></button> */}


            </div>
            {/* </div> */}

            {/* <div className="task-container grid gap-4 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 mt-6"> */}
            <div className="grid grid-cols-3 gap-2">
                {data?.content?.map((obj) => (
                    <ConsultationCard
                        key={obj.id_consultation}
                        itemObj={obj}
                        deleteItem={handleDelete}
                        updateListArray={handleEdit} // Pasar la función handleEdit para abrir el modal de edición
                    // artistName={artistsMap[obj.artist]}
                    />

                ))}

            </div>
            {/* <button
                    className="absolute buttom-0 right-3 tooltip tooltip-right btn btn-xs bg-base-300 bg-opacity-90 text-base-100 inline-flex items-center justify-center text-[8px] overflow-ellipsis" data-tip="Agregar Mascota"
                    onClick={() => setModal(true)}
                >
                    <FaPlus size={10} />Agregar

                </button> */}




            <CreateConsultation toggle={() => setModal(!modal)} modal={modal} save={handleSave} idPet={id.id} />


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

export default ConsultationList;