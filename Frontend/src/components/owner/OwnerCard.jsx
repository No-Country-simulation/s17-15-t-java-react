import React, { useState } from 'react';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import { MdOutlineEdit } from "react-icons/md";
import EditOwner from './EditOwner';
import { useAuth } from '../../contexts/AuthContext';
import PetList from '../pet/PetList';
import ConfirmDeleteModal from '../UI/ConfirmDeleteModal';

// taskObj = artistObj                                                                                                                                                     SSSDDDDDD S DSSSSSSSSSSSSSSSSSSSSS 
// deleteTask = deleteArtist
// updateListArray = updateArtist

const formatPhoneNumber = (phoneNumber) => {
    if (!phoneNumber) return "No disponible";
    const country = phoneNumber.slice(0, 3); // (+549)
    const area = phoneNumber.slice(3, 5); // 22
    const part1 = phoneNumber.slice(5, 9); // 2222
    const part2 = phoneNumber.slice(9, 13); // 2222
    return `(+${country}) ${area} ${part1} ${part2}`;
};

function OwnerCard({ ownerObj, deleteOwner, updateListArray }) {
    const { isAuthenticated } = useAuth('state');
    const imagenDefault = 'https://www.forumchaves.com.br/listach/site/imagens/imagem_indisponivel_es.jpg';
    const [modal, setModal] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false); //  modal de confirm delete
    const [showPetModal, setShowPetModal] = useState(false);

    const toggle = () => {
        setModal(!modal);
    };

    const updateOwner = (fromData, id) => {
        updateListArray(fromData, id);
    };

    const handleDelete = () => {
        deleteOwner(ownerObj.id);
    };

    return (
        <div className="card card-compact rounded-md relative bg-primary bg-opacity-[40%] text-base-300 w-72 shadow-xl border-base-300 border-[1px] border-opacity-35 min-h-72">
            {/* <div className="bg-gradient-to-r from-red-400 via-pink-500 to-purple-600 h-2 w-full rounded-t-lg"></div> */}
            {/* <div className="bg-gradient-to-r from-blue-600 to-cyan-400 h-2 w-full rounded-t-lg"></div> */}
            {/* <div className="bg-gradient-to-r from-cyan-400 to-blue-600 h-2 w-full rounded-t-2xl"></div> */}

            <div className=" card-body pt-2">
                <h2 className="card-title truncate">
                    {ownerObj.name} {ownerObj.lastname}
                </h2>

                <div className="flex items-start gap-4">
                    {/* <figure className="h-20 w-20 rounded-sm flex-shrink-0">
                        <img
                            src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp"
                            alt="Imagen"
                            className="h-full w-full object-cover rounded-lg"
                        />
                    </figure> */}

                    <div className="grid grid-cols-1 gap-2 pl-4">
                        <div>
                            {/* <p className='font-bold'>Email:</p> */}
                            <p className="text-xs overflow-hidden overflow-ellipsis">{ownerObj.email ? ownerObj.email : "No disponible"}</p>
                        </div>
                        <div>
                            <p className="text-xs overflow-hidden overflow-ellipsis">
                                {ownerObj.phone ? formatPhoneNumber(ownerObj.phone) : "No disponible"}
                            </p>
                        </div>
                        <div>
                            {/* <p className='font-bold'>Direccion:</p> */}
                            <p className="text-xs overflow-hidden overflow-ellipsis">{ownerObj.address ? ownerObj.address : "No disponible"}</p>
                        </div>
                        {/* <div>
            <p className='font-bold'>Cant. Mascota:</p>
            <p className="text-xs max-h-32 h-32 overflow-hidden overflow-ellipsis">{ownerObj?.pets?.length ? ownerObj.pets.length : "0"}</p>
        </div> */}
                    </div>
                </div>


                {/* Tarjetas de mascotas */}
                {/* <h2 className='font-semibold'>Mascota:</h2> */}
                {/* <div className='bg-primary bg-opacity-90 rounded-xl text-base-100 shadow-2xl text-center min-w-20 border-[1px] border-primary border-opacity-60'>Rocco</div> */}
                <div>
                    <PetList id={ownerObj.id} />
                </div>





                {isAuthenticated && (
                    <div className="tooltip tooltip-top btn btn-circle btn-sm absolute top-3 right-3 flex space-x-2 bg-base-300  bg-opacity-100 border-base-300" data-tip="Editar Propietario">
                        <MdOutlineEdit
                            className="text-xl cursor-pointer text-neutral"
                            onClick={() => setModal(true)}
                            size={20}
                        />
                        {/* <FaTrashAlt
                            className="text-xl cursor-pointer text-red-700"
                            onClick={handleDelete}

                            size={15}

                        /> */}
                    </div>)}

                <FaTrashAlt
                    className="absolute top-16 right-5 text-xl cursor-pointer text-red-700"
                    //onClick={handleDelete}
                    onClick={() => setIsDeleteModalOpen(true)}

                    size={15}

                />
                {/* <div className="bg-gradient-to-r from-blue-600 to-cyan-400 h-2 w-full rounded-b-lg"></div> */}

                {/* <div className="bg-gradient-to-r from-red-400 via-pink-500 to-purple-600 h-2 w-full rounded-b-lg"></div> */}


                {/* <div className="h-2 w-full bg-blue-500 rounded-b-lg"></div> */}
            </div>
            <EditOwner
                modal={modal}
                toggle={toggle}
                updateOwner={updateOwner}
                objOwner={ownerObj}
            />

            <ConfirmDeleteModal
                isOpen={isDeleteModalOpen}
                onClose={() => setIsDeleteModalOpen(false)}
                onConfirm={() => {
                    handleDelete();
                    setIsDeleteModalOpen(false);
                }}
                message="Are you sure you want to delete this Owner?"
            />
        </div>
    );
}

export default OwnerCard;