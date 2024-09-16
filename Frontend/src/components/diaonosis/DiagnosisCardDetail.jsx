import { Link, useNavigate } from "react-router-dom";
import React, { useState, useEffect } from 'react';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import EditConsultation from './EditConsultation';

import { useAuth } from '../../contexts/AuthContext';
import ConfirmDeleteModal from '../UI/ConfirmDeleteModal';
import { FaPlus } from 'react-icons/fa';
import { MdOutlineEdit } from "react-icons/md";
import GeneratePDF from "../factura/GeneratePDF";


function ConsultationCardDetail({ itemObj, deleteItem, updateListArray }) {
    const navigate = useNavigate();
    const { isAuthenticated } = useAuth('state');
    const [modal, setModal] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false); //  modal de confirm delete


    const [vet, setVet] = useState(null)
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [pet, setPet] = useState(null);
    const [owner, setOwner] = useState(null)



    useEffect(() => {
        const fetchDetails = async () => {
            try {
                if (!itemObj || !itemObj.id_veterinarian) {
                    throw new Error('Veterinarian ID is undefined');
                }

                setLoading(true);

                // 1. Traer la información del veterinario
                const vetResponse = await fetch(`https://veterinaria-bef3.onrender.com/veterinarian/${itemObj.id_veterinarian}`);
                if (!vetResponse.ok) {
                    throw new Error('Failed to fetch vet details');
                }
                const vetData = await vetResponse.json();
                setVet(vetData);

                // 2. Traer la información de la mascota
                const petResponse = await fetch(`https://veterinaria-bef3.onrender.com/pet/${itemObj.id_pet}`);
                if (!petResponse.ok) {
                    throw new Error('Failed to fetch pet details');
                }
                const petData = await petResponse.json();
                setPet(petData);

                // 3. Traer la información del dueño usando el owner_id de la mascota
                const ownerResponse = await fetch(`https://veterinaria-bef3.onrender.com/owner/${petData.owner_id}`);
                if (!ownerResponse.ok) {
                    throw new Error('Failed to fetch owner details');
                }
                const ownerData = await ownerResponse.json();
                setOwner(ownerData);

            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        if (itemObj && itemObj.id_veterinarian && itemObj.id_pet) {
            fetchDetails();
        }
    }, [itemObj]);




    const toggle = () => {
        setModal(!modal);
    };

    const updateItem = (fromData, id) => {
        updateListArray(fromData, id);
    };

    const handleDelete = () => {
        deleteItem(itemObj.id_consultation);
    };

    const fecha = new Date(itemObj?.consultationDate).toLocaleDateString('es-ES', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
    })

    const invoiceData = {
        invoiceDate: fecha,
        totalCost: itemObj?.costConsultation,
        veterinarianName: `${vet?.name} ${vet?.lastname}`, // Nombre y apellido del veterinario
        ownerName: `${owner?.name} ${owner?.lastname}`, // Nombre y apellido del dueño
        petName: pet?.name,
    };

    return (
        <div className="shadow-lg rounded-lg relative gap-1 min-w-96"
        //     onClick={() => {

        //         navigate("/mascota/consultas" + petObj.id)
        //     }
        // }
        >
            <div className="p-6 bg-primary bg-opacity-10 flex w-full flex-col lg:flex-row lg:min-w-[700px] min-h-80 lg:max-w-[700px] max-w-[500px] gap-10 rounded-xl">
                <div className="card rounded-none grid h-auto flex-grow ">
                    {/* Columna 1 */}
                    <div className="md:col-span-1 md:row-start-1">

                        <div className="flex flex-row gap-x-5">
                            <div className="pb-2 w-32">
                                <strong className="text-xs">Fecha:</strong>
                                <p className="text-xs pl-2">
                                    {/* {new Date(itemObj.consultationDate).toLocaleDateString('es-ES', {
                                        day: '2-digit',
                                        month: '2-digit',
                                        year: 'numeric',
                                    })} */}
                                    {fecha}
                                </p>
                            </div>

                            <div className="pb-2">
                                <strong className="text-xs">ID Mascota: </strong>
                                {/* <p className="text-xs">..</p> */}
                                <p className="text-xs pl-2">{itemObj.id_pet ? itemObj.id_pet : "No disponible"}</p>

                            </div>

                        </div>

                        <div className="flex flex-row gap-x-5">
                            <div className="pb-2 w-32">
                                <strong className="text-xs">Valor:</strong>
                                <p className="text-xs pl-2">{itemObj.costConsultation ? itemObj.costConsultation : "No disponible"}</p>
                            </div>
                            <div className="pb-2">
                                <strong className="text-xs">Nombre: </strong>
                                <p className="text-xs pl-2">{pet?.name ? pet?.name : "No disponible"}</p>

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

                                {/* <button className="btn btn-xs bg-orange-400 text-white italic">
                                    Descarga Factura */}
                                <GeneratePDF invoiceData={invoiceData} />

                                {/* </button> */}
                            </div>

                        </div>



                    </div>
                </div>

                <div className="card rounded-none  grid h-auto flex-grow">
                    <div className="md:col-span-1">
                        <div className="pb-2">
                            <strong className="text-xs">Veterinario Asignado: </strong>
                            <p className="text-xs pl-2 truncate">{vet?.name} {vet?.lastname}</p>

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
            </div>

   
                    <Link to={`/consulta/editar/${itemObj.id_consultation}`}>
                        <div className="tooltip tooltip-top btn btn-circle btn-sm absolute top-3 right-3 flex space-x-2 bg-base-300  bg-opacity-100 border-base-300" data-tip="Editar">
                            <MdOutlineEdit
                                className="text-xl cursor-pointer text-white"
                                // onClick={() => setModal(true)}
                                size={20}
                            />
                            {/* <FaTrashAlt
                            className="text-xl cursor-pointer text-red-700"
                            onClick={handleDelete}

                            size={15}

                        /> */}
                        </div>
                    </Link>
                    {/* <FaTrashAlt
                            className="cursor-pointer text-red-700"
                            //onClick={handleDelete}
                            onClick={(e) => {
                                e.stopPropagation();
                                setIsDeleteModalOpen(true)
                            }}

                            size={7}

                        /> */}

              
         
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