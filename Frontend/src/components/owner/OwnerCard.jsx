import React, { useState } from 'react';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import { MdOutlineEdit } from "react-icons/md";
import EditOwner from './EditOwner';
import { useAuth } from '../../contexts/AuthContext';
import PetList from '../pet/PetList';

// taskObj = artistObjDDDDDD    DDDDDDDDDD                                                                                                                                                          SSSDDDDDD S DSSSSSSSSSSSSSSSSSSSSS 
// deleteTask = deleteArtist
// updateListArray = updateArtist

function OwnerCard({ ownerObj, deleteOwner, updateListArray }) {
    const { isAuthenticated } = useAuth('state');
    const imagenDefault = 'https://www.forumchaves.com.br/listach/site/imagens/imagem_indisponivel_es.jpg';
    const [modal, setModal] = useState(false);

    const toggle = () => {
        setModal(!modal);
    };

    const updateOwner = (fromData, id) => {
        updateListArray(fromData, id);
    };

    const handleDelete = () => {
        deleteOwner(OwnerObj.id);
    };

    return (
        <div className="card card-compact bg-base-300 bg-opacity-10 text-base-300 w-80 shadow-xl border-base-300 border-[1px] border-opacity-35 min-h-60">
            {/* <div className="bg-gradient-to-r from-red-400 via-pink-500 to-purple-600 h-2 w-full rounded-t-lg"></div> */}
            {/* <div className="bg-gradient-to-r from-blue-600 to-cyan-400 h-2 w-full rounded-t-lg"></div> */}
            {/* <div className="bg-gradient-to-r from-cyan-400 to-blue-600 h-2 w-full rounded-t-2xl"></div> */}

            <div className=" card-body pt-2">
                <h2 className="card-title">
                    {ownerObj.name} {ownerObj.lastname}
                </h2>

                <div className="flex items-start gap-4">
                    <figure className="h-20 w-20 rounded-sm flex-shrink-0">
                        <img
                            src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp"
                            alt="Imagen"
                            className="h-full w-full object-cover rounded-lg"
                        />
                    </figure>
  
                    <div className="grid grid-cols-1 gap-2">
                        <div>
                            {/* <p className='font-bold'>Email:</p> */}
                            <p className="text-xs overflow-hidden overflow-ellipsis">{ownerObj.email ? ownerObj.email : "No disponible"}</p>
                        </div>
                        <div>
                            {/* <p className='font-bold'>Phone:</p> */}
                            <p className="text-xs overflow-hidden overflow-ellipsis">{ownerObj.phone ? ownerObj.phone : "No disponible"}</p>
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
                    <div className="tooltip tooltip-right btn btn-circle btn-sm absolute top-3 right-3 flex space-x-2 bg-base-300  bg-opacity-100" data-tip="Editar Dueño">
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
        </div>
    );
}

export default OwnerCard;