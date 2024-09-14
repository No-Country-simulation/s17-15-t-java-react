import { Link, useNavigate } from "react-router-dom";
import React, { useState, useEffect } from 'react';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import EditConsultation from './EditConsultation';

import { useAuth } from '../../contexts/AuthContext';
import ConfirmDeleteModal from '../UI/ConfirmDeleteModal';
import { FaPlus } from 'react-icons/fa';
import { MdOutlineEdit } from "react-icons/md";


function ConsultationCardDetail({ itemObj, deleteItem, updateListArray }) {
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
        <div className="shadow-lg rounded-lg relative gap-1 min-w-96"
        //     onClick={() => {

        //         navigate("/mascota/consultas" + petObj.id)
        //     }
        // }
        >

            <div className="p-5 bg-primary bg-opacity-10 rounded-lg shadow-xl min-w-[700px] min-h-80 max-w-[700px]">
                <div className="grid grid-cols-1 md:grid-cols-2 gap-32">
                    {/* Columna 1 */}
                    <div className="md:col-span-1 md:row-start-1">

                        <div className="flex flex-row gap-x-20">
                            <div className="pb-2">
                                <strong className="text-xs">Fecha:</strong>
                                <p className="text-xs pl-2">
                                    {new Date(itemObj.consultationDate).toLocaleDateString('es-ES', {
                                        day: '2-digit',
                                        month: '2-digit',
                                        year: 'numeric',
                                    })}
                                </p>
                            </div>

                            <div className="pb-2">
                                <strong className="text-xs">ID Mascota: </strong>
                                {/* <p className="text-xs">..</p> */}
                                <p className="text-xs pl-2">{itemObj.id_pet ? itemObj.id_pet : "No disponible"}</p>

                            </div>

                        </div>

                        <div className="flex flex-row gap-x-16">
                            <div className="pb-2">
                                <strong className="text-xs">Valor:</strong>
                                <p className="text-xs pl-2">{itemObj.costConsultation ? itemObj.costConsultation : "No disponible"}</p>
                            </div>
                            <div className="pb-2">
                                <strong className="text-xs">Nombre: </strong>
                                <p className="text-xs pl-2">{itemObj.name ? itemObj.name : "No disponible"}</p>

                            </div>

                        </div>
                        <div className="pb-2">
                            <strong className="text-xs">Estado de la Consulta:</strong>
                            <p className="text-xs pl-2"> {itemObj.state ? itemObj.state : "No disponible"}</p>
                        </div>

                        <div className="pb-2">
                            <strong className="text-xs">Observaciones: </strong>
                            <p className="text-xs pl-2 break-words whitespace-normal"> {itemObj.observations ? itemObj.observations : "No disponible"}</p>
                        </div>

                        <div className="pb-2">
                            <strong className="text-xs">Facturación: </strong>
                            {/* <p className="text-xs pl-2">.........</p> */}
                            <div>

                                <button className="btn btn-xs bg-orange-400 text-white italic">
                                    Descarga Factura

                                </button>
                            </div>

                        </div>



                    </div>

                    {/* Columna 2 */}
                    {/* <div className="md:col-span-1 ">
                        <strong className="text-xs">ID Mascota: </strong>
                        <p className="text-xs">{itemObj.id_pet ? itemObj.id_pet : "No disponible"}</p>
                        <strong className="text-xs">Nombre: </strong>
                        <p className="text-xs">{itemObj.name ? itemObj.name : "No disponible"}</p>
                      
                        
                    </div> */}

                    {/* Columna 3 */}
                    <div className="md:col-span-1">
                        <div className="pb-2">
                            <strong className="text-xs">Veterinario Asignado: </strong>
                            <p className="text-xs pl-2">{vet?.name} {vet?.lastname}</p>

                        </div>

                        <div className="pb-2">
                            <strong className="text-xs">Anamenesis: </strong>
                            <p className="text-xs pl-2 break-words whitespace-normal">{itemObj.anamnesis ? itemObj.anamnesis : "No disponible"}</p>
                            {/* <p className="text-xs pl-2 break-words whitespace-normal">Este es un texto muy largo que se ajustará en múltiples líneas si es necesario, sin desbordarse fuera de la caja contenedora. Así que no hay problemas con el desbordamiento.</p> */}

                        </div>


                        <div className="pb-2">
                            <strong className="text-xs">Estudios complementarios: </strong>
                            <div>

                                <button className="btn btn-xs bg-orange-400 text-white italic">
                                    Estudios
                                </button>
                            </div>
                        </div>


                        <div className="pb-2">
                            <strong className="text-xs">Diagnóstico: </strong>
                            <p className="text-xs pl-2">.............</p>
                        </div>

                    </div>


                </div>











                <div className="tooltip tooltip-top btn btn-circle btn-sm absolute top-3 right-3 flex space-x-2 bg-base-300  bg-opacity-100 border-base-300" data-tip="Editar Propietario">
                    <MdOutlineEdit
                        className="text-xl cursor-pointer text-white"
                        onClick={() => setModal(true)}
                        size={20}
                    />
                    {/* <FaTrashAlt
                            className="text-xl cursor-pointer text-red-700"
                            onClick={handleDelete}

                            size={15}

                        /> */}
                </div>
                {/* <FaTrashAlt
                            className="cursor-pointer text-red-700"
                            //onClick={handleDelete}
                            onClick={(e) => {
                                e.stopPropagation();
                                setIsDeleteModalOpen(true)
                            }}

                            size={7}

                        /> */}





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
        </div >
    );
}

export default ConsultationCardDetail;