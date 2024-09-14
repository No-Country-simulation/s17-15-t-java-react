import React, { useState, useEffect } from 'react';
import { FaTimes } from 'react-icons/fa';
import { useAuth } from '../../contexts/AuthContext';
import Navbar from '../Navbar';
import { Link } from "react-router-dom"
import { TiArrowBack } from "react-icons/ti";
import { useNavigate } from 'react-router-dom';


function ConsultationForm({ modal, toggle, onSave, objItem = {}, isEdit = false, idPet = null }) {
    //id_veterinario
    //id_pet
    const { user__id } = useAuth('state') //id del veterinario (usuario)

    const [petID, setPetID] = useState(0)
    const [veterinarioID, setVeterinarioID] = useState(0)
    const [consultationName, setConsultationName] = useState('');
    const [consultationDate, setConsultationDate] = useState('');
    const [anamnesis, setAnamnesis] = useState('');
    const [observations, setObservations] = useState('');
    const [state, setState] = useState('PENDIENTE');
    const [costConsultation, setCostConsultation] = useState(0);
    const {navigate} = useNavigate();

    // setPetID(idPet);
    // setVeterinarioID(user__id);


    useEffect(() => {
        if (isEdit && objItem) {
            setPetID(objItem.veterinarian || user__id);
            setVeterinarioID(objItem.id_pet || idPet);
            setConsultationName(objItem.name || '');
            setConsultationDate(objItem.consultationDate || '');
            setAnamnesis(objItem.anamnesis || '');
            setObservations(objItem.observations || '');
            setState(objItem.state || 'PENDIENTE');
            setCostConsultation(objItem.costConsultation || 0);

        } else {
            setPetID(idPet);
            setVeterinarioID(user__id);
        }
    }, [isEdit, objItem]);

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        switch (name) {
            case 'consultationName':
                setConsultationName(value);
                break;
            case 'consultationDate':
                setConsultationDate(value);
                break;
            case 'anamnesis':
                setAnamnesis(value);
                break;
            case 'observations':
                setObservations(value);
                break;
            case 'state':
                setState(value);
                break;
            case 'costConsultation':
                setCostConsultation(value);
                break;


            // case 'active':
            //     setActive(type === 'checkbox' ? checked : value);
            //     break;
            case 'details':
                setDetails(value);
                break;
            default:
                break;
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const itemData = {

            id_veterinarian: veterinarioID,
            id_pet: petID,
            name: consultationName,
            consultationDate: consultationDate,
            anamnesis: anamnesis,
            observations: observations,
            state: state,
            costConsultation: costConsultation,

        };

        onSave(itemData, isEdit ? objItem.id_consultation : null);
        toggle(false);
    };

    if (!modal) {
        return null;
    }

    return (

        <div className="fixed inset-0 z-50 overflow-hidden bg-white bg-opacity-100">
            <Navbar className="w-full fixed top-0 left-0 z-50" />
            {isEdit && (
                <button
                onClick={() => navigate(-1)}
                className="pl-10 mt-4 font-bold flex flex-row"
            >
                <TiArrowBack />
                Volver
            </button>
        
        )}

            <div className="w-screen h-screen flex justify-center items-start overflow-x-hidden">
                {/* Ajuste de pt-20 para asegurar que el contenido no quede detrás del Navbar */}
                <div className="max-w-3xl w-full">
                    <div className="flex justify-center items-center pb-5 col-2 row-0">
                        <div>
                            {isEdit && (
                                <h2 className="text-center text-lg font-semibold" >
                                    Editar Mascota
                                </h2>
                            )}

                            {!isEdit && (
                                <div>
                                    <h2 className="text-center text-lg font-semibold" >
                                        Registrar Mascota
                                    </h2>

                                    <p className="text-xs italic">
                                        Asocia una mascota al propietario y haz click en "finalizar"
                                    </p></div>
                            )}
                        </div>
                        {/* <button onClick={toggle} className="btn btn-link">
            <FaTimes size={20} />
          </button> */}
                    </div>
                    <form onSubmit={handleSubmit} className="bg-primary bg-opacity-10 p-4 border-2 rounded-lg">
                        <div className="grid grid-cols-1 sm:grid-cols-2 gap-x-4 gap-y-4">
                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    ID Mascota: {petID}
                                </label>
                                {/* <input
                                    type="text"
                                    name="petID"
                                    value={petID}
                                    onChange={handleChange}
                                    placeholder="Ingresar nombre"
                                    required
                                    className="input input-sm input-bordered w-full"
                                /> */}
                            </div>
                            {/* <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Sexo:
                                </label>
                                <div className="join px-10">
                                    <label className="cursor-pointer">
                                        <input
                                            type="radio"
                                            name="sex"
                                            value="MACHO"
                                            checked={sex === 'MACHO'}
                                            onChange={handleChange}
                                            className="radio radio-sm"
                                        // defaultChecked
                                        />
                                        <span className="ml-2 text-xs">MACHO</span>
                                    </label>
                                    <label className="cursor-pointer ml-4">
                                        <input
                                            type="radio"
                                            name="sex"
                                            value="HEMBRA"
                                            checked={sex === 'HEMBRA'}
                                            onChange={handleChange}
                                            className="radio radio-sm"
                                        />
                                        <span className="ml-2 text-xs">HEMBRA</span>
                                    </label>
                                </div>
                            </div> */}
                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Estado de la consulta:
                                </label>
                                <select
                                    name="state"
                                    value={state}
                                    onChange={handleChange}
                                    className="select select-bordered select-sm w-full max-w-full"
                                >
                                    <option value="PERRO">PEENDIENTE</option>
                                    <option value="GATO">GATO</option>
                                    <option value="REPTIL">REPTIL</option>
                                    <option value="AVE">AVE</option>
                                    <option value="TORTUGA">TORTUGA</option>
                                    <option value="OTRO">OTRO</option>
                                </select>
                            </div>


                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Raza:
                                </label>
                                <input
                                    type="text"
                                    name="race"
                                    value={race}
                                    onChange={handleChange}
                                    placeholder="Ingresar raza"
                                    required
                                    className="input input-sm input-bordered w-full"
                                />
                            </div>


                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Fecha de Nacimiento:
                                </label>
                                <input
                                    type="date"
                                    name="birthdate"
                                    value={birthdate}
                                    onChange={handleChange}
                                    className="input input-sm input-bordered w-full"
                                />
                            </div>



                            <div className="form-control">
                                <label className="block text-sm font-medium mb-1">
                                    Alergias:
                                </label>
                                <input
                                    type="text"
                                    name="allergies"
                                    value={allergies}
                                    onChange={handleChange}
                                    placeholder="Ingresar alergias"
                                    className="input input-sm input-bordered w-full"
                                />
                            </div>

                            {/* <div className="flex flex-row form-control">
                                <label className="block text-sm font-medium mb-1 pr-2">
                                    Castrado:
                                </label>
                                <input
                                    type="checkbox"
                                    name="castrated"
                                    checked={castrated}
                                    onChange={handleChange}
                                    className="checkbox checkbox-xs"
                                />
                            </div> */}
                            <div className="flex flex-row form-control">
                                <label className="block text-sm font-medium mb-1 pr-2">
                                    Castrado:
                                </label>
                                <select
                                    name="castrated"
                                    value={castrated}
                                    onChange={handleChange}
                                    className="select select-bordered select-xs"
                                >
                                    <option value="true">Sí</option>
                                    <option value="false">No</option>
                                </select>
                            </div>


                            <div className="flex flex-row form-control">
                                <label className="block text-sm font-medium mb-1 pr-2">
                                    Activo:
                                </label>
                                <input
                                    type="checkbox"
                                    name="active"
                                    checked={active}
                                    onChange={handleChange}
                                    className="checkbox checkbox-xs"
                                />
                            </div>
                        </div>

                        <div className="form-control w-full pb-2">
                            <label className="block text-sm font-medium mb-1 mt-1">
                                Detalles:
                            </label>
                            <textarea
                                name="details"
                                value={details}
                                onChange={handleChange}
                                placeholder="Ingresar detalles"
                                className="textarea textarea-xs textarea-bordered w-full"
                            />
                        </div>

                        <div className="flex justify-end gap-3 mt-3 pb-2">
                            {!isEdit && (<button onClick={toggle} type="button" className="btn btn-sm">
                                Cancelar
                            </button>)}

                            <button type="submit" className="btn btn-sm btn-primary">
                                {isEdit ? 'Guardar' : 'Finalizar'}
                            </button>
                        </div>
                    </form>
            
                </div>

            </div>
  
        </div>
    );
}


export default ConsultationForm;

