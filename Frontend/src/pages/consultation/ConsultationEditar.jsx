import { TiArrowBack } from "react-icons/ti";
import ConsultationForm from "../../components/consultation/ConsultationForm.jsx";

import React, { useState, useEffect } from 'react';
import { useAuth } from "../../contexts/AuthContext.jsx"
import useJSON from '../../hooks/useJSON_subfijo.js';
import Status from "../../components/UI/Status.jsx";
import { useParams } from 'react-router-dom';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import PetForm from '../../components/pet/PetForm.jsx';
import { useNavigate } from 'react-router-dom';


function ConsultationEditar() {
    const { id } = useParams();
    const { jwt } = useAuth("state");
    const [modal, setModal] = useState(true);

    const baseURL = `https://veterinaria-bef3.onrender.com/consultation`;
    const filter = `/${id}`

    const navigate = useNavigate();

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
        alert("cambios actualizados")
        navigate(-1)

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

    const toggle = () => {
        setModal(true);
    };
    return (
        <main>

            (
            <button
                onClick={() => navigate(-1)}
                className="pl-10 font-bold flex flex-row"
            >
                <TiArrowBack />
                Volver
            </button>

            )


            <ConsultationForm
                modal={modal}
                toggle={toggle}
                onSave={handleEdit}
                objItem={data}
                isEdit={true}
                idPet={data.id_pet}

            />

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





        </main>
    )
}
export default ConsultationEditar;