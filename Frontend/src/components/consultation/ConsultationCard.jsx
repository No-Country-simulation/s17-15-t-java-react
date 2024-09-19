import { Link, useNavigate } from "react-router-dom";
import React, { useState, useEffect } from 'react';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import EditConsultation from './EditConsultation';

import { useAuth } from '../../contexts/AuthContext';
import ConfirmDeleteModal from '../UI/ConfirmDeleteModal';
import { FaPlus } from 'react-icons/fa';


function ConsultationCard({ itemObj, deleteItem, updateListArray }) {
    const navigate = useNavigate();
    const { isAuthenticated } = useAuth('state');
    const [modal, setModal] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false); //  modal de confirm delete


    const [vet, setVet] = useState(null)
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const URL = `https://veterinaria-bef3.onrender.com/veterinarian/${itemObj.id_veterinarian}`;


    useEffect(() => {
        const fetchVet = async () => {
            try {
                setLoading(true);

                const vetResponse = await fetch(URL);
                if (!vetResponse.ok) {
                    throw new Error('Failed to fetch vet details');
                }
                const vetData = await vetResponse.json();
                setVet(vetData);
                setLoading(false);

            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
            finally {
                setLoading(false);
            }
        };

        fetchVet();
    }, [URL]);



  const toggle = () => {
    setModal(!modal);
  };

  const updateItem = (fromData, id) => {
    updateListArray(fromData, id);
  };

  const handleDelete = () => {
    deleteItem(itemObj.id_consultation);
  };

    return (
        <div className="shadow-lg rounded-lg relative gap-1"
        //     onClick={() => {

        //         navigate("/mascota/consultas" + petObj.id)
        //     }
        // }
        >

            <div className="p-3 bg-white bg-opacity-20 rounded-lg shadow-xl min-h-36">
                <p className="text-sm pb-2 font-semibold text-primary">ID consulta: {itemObj.id_consultation}</p>


                {/* <p className="">{itemObj.id_veterinarian ? itemObj.id_veterinarian : "No disponible"}</p>
                id_pet */}
                {/* <p className="">{itemObj.id_pet ? itemObj.id_pet : "No disponible"}</p>
                name */}
                {/* <p className="">{itemObj.name ? itemObj.name : "No disponible"}</p>
                consultationDate */}
                {/* <p className="">{itemObj.consultationDate ? itemObj.consultationDate : "No disponible"}</p> */}
                <div className="flex flex-row gap-2">
                    <strong className="text-xs pb-2">Fecha:</strong>
                    <p className="text-xs pb-2">
                        {new Date(itemObj.consultationDate).toLocaleDateString('es-ES', {
                            day: '2-digit',
                            month: '2-digit',
                            year: 'numeric',
                        })}
                    </p>
                </div>

                <div className="flex flex-row gap-2">
                    <strong className="text-xs pb-2">Veterinario Asignado:</strong>
                    <p className="text-xs pb-2"> {vet?.name} {vet?.lastname}</p>
                </div>


                {/* <p className="">{itemObj.anamnesis ? itemObj.anamnesis : "No disponible"}</p>
                observations */}
                {/* <p className="">{itemObj.observations ? itemObj.observations : "No disponible"}</p>
                state */}
                {/* <p className="">{itemObj.state ? itemObj.state : "No disponible"}</p> */}
                <div className="flex flex-row gap-2">
                    <strong className="text-xs pb-2">costo:</strong>
                    <p className="text-xs">
                        {itemObj.costConsultation ? `$${itemObj.costConsultation}` : "$0"}
                    </p>
                </div>

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

                <Link
                    className="absolute bottom-3 right-3 tooltip tooltip-top btn btn-xs bg-base-300 bg-opacity-90 text-base-100 inline-flex items-center justify-center overflow-ellipsis" data-tip=""
                    // onClick={() => setModal(true)}
                    to={`/consulta/${itemObj.id_consultation}`}
                >
                    <span className='text-[8px] text-white'>Ver Mas</span>
                    <FaPlus className="text-white" size={8} />

                </Link>


                {/* <div className="bg-gradient-to-r from-blue-600 to-cyan-400 h-2 w-full rounded-b-lg"></div> */}

                {/* <div className="bg-gradient-to-r from-red-400 via-pink-500 to-purple-600 h-2 w-full rounded-b-lg"></div> */}


                {/* <div className="h-2 w-full bg-blue-500 rounded-b-lg"></div> */}
            </div>
            <EditConsultation
                modal={modal}
                toggle={toggle}
                updateItem={updateItem}
                objItem={itemObj}
            />

      <ConfirmDeleteModal
        isOpen={isDeleteModalOpen}
        onClose={() => setIsDeleteModalOpen(false)}
        onConfirm={() => {
          handleDelete();
          setIsDeleteModalOpen(false);
        }}
        message="Are you sure you want to delete this CONSULTATION?"
      />
    </div>
  );
}

export default ConsultationCard;
