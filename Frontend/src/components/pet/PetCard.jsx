import { useNavigate } from "react-router-dom";
import React, { useState } from 'react';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import EditPet from './EditPet';

import { useAuth } from '../../contexts/AuthContext';
import ConfirmDeleteModal from '../UI/ConfirmDeleteModal';


function PetCard({ petObj, deletePet, updateListArray }) {
    const navigate = useNavigate();
    const { isAuthenticated } = useAuth('state');
    // const imagenDefault = 'https://www.forumchaves.com.br/listach/site/imagens/imagem_indisponivel_es.jpg';
    const [modal, setModal] = useState(false);

    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false); //  modal de confirm delete

    const toggle = () => {
        setModal(!modal);
    };

    const updatePet = (fromData, id) => { 
        updateListArray(fromData, id);
    };

    const handleDelete = () => {
        // e.stopPropagation();
        deletePet(petObj.id);
    };

    return (
        <div className="shadow-xl rounded-lg relative gap-1"
            onClick={() => {
                
                navigate("/mascota/" + petObj.id)
            }}
        >
            {/* <div className="bg-gradient-to-r from-red-400 via-pink-500 to-purple-600 h-2 w-full rounded-t-lg"></div> */}
            {/* <div className="bg-gradient-to-r from-blue-600 to-cyan-400 h-2 w-full rounded-t-lg"></div> */}
            {/* <div className="bg-gradient-to-r from-cyan-400 to-blue-600 w-full rounded-t-lg"></div> */}


            <div className="">
                {/* <span className="glass text-slate-200 text-center text-sm font-semibold bg-gray-700 p-2 bg-opacity-90 rounded-lg block">
                    {songArtistObj.title}
                </span> */}


                <p className="btn btn-xs bg-orange-400 bg-opacity-90 text-white rounded-xl text-center min-w-20  overflow-ellipsis">{petObj.name ? petObj.name : "No disponible"}</p>
                {/* <p className="text-xs overflow-hidden overflow-ellipsis">{songArtistObj.song ? songArtistObj.song : "informacion no disponible"}</p> */}



                {isAuthenticated && (
                    <div className="absolute bottom-0 flex right-0 px-0.5 space-x-1 rounded-lg bg-opacity-95">
                        {/* <FaEdit
                            className=" cursor-pointer text-green-700"
                            onClick={() => {
                                
                                setModal(true)
                            }}

                            size={7}
                        /> */}
                        <FaTrashAlt
                            className="cursor-pointer text-red-700"
                            //onClick={handleDelete}
                            onClick={(e) => {
                                e.stopPropagation();
                                setIsDeleteModalOpen(true)
                            }}

                            size={7}

                        />
                    </div>)}
                {/* <div className="bg-gradient-to-r from-blue-600 to-cyan-400 h-2 w-full rounded-b-lg"></div> */}

                {/* <div className="bg-gradient-to-r from-red-400 via-pink-500 to-purple-600 h-2 w-full rounded-b-lg"></div> */}


                {/* <div className="h-2 w-full bg-blue-500 rounded-b-lg"></div> */}
            </div>
            <EditPet
                modal={modal}
                toggle={toggle}
                updatePet={updatePet}
                objPet={petObj}
            />

            <ConfirmDeleteModal
                isOpen={isDeleteModalOpen}
                onClose={(e) => {
                    e.stopPropagation();
                    setIsDeleteModalOpen(false)
                }}
                onConfirm={(e) => {
                    e.stopPropagation();
                    handleDelete();
                    setIsDeleteModalOpen(false);
                }}
                message="Are you sure you want to delete this Pet?"
            />
        </div>
    );
}

export default PetCard;