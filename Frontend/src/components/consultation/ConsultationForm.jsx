import React, { useState, useEffect } from 'react';
import { FaTimes } from 'react-icons/fa';
import { useAuth } from '../../contexts/AuthContext';

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
        <div className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto bg-black bg-opacity-50">
            <div className="bg-white rounded-lg shadow-lg w-full max-w-lg">
                <div className="flex justify-between items-center p-4 border-b">
                    <h2 className="text-xl font-semibold">{isEdit ? 'Actualizar' : 'Crear'} Consulta</h2>
                    <button onClick={toggle} className="text-gray-500 hover:text-gray-700">
                        <FaTimes size={20} />
                    </button>
                </div>
                <form onSubmit={handleSubmit} className="px-4 pb-4">
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Nombre:</span>
                                <span className="label-text-alt text-red-500">Requerido</span>
                            </div>
                            <input
                                type="text"
                                name="consultationName"
                                value={consultationName}
                                onChange={handleChange}
                                placeholder="Ingresar nombre"
                                required
                                className="input input-bordered w-full"
                            />
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">anamnesis:</span>
                            </div>
                            <input
                                type="text"
                                name="anamnesis"
                                value={anamnesis}
                                onChange={handleChange}
                                placeholder="Ingresar anamnesis"
                                required
                                className="input input-bordered w-full"
                            />
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Estado:</span>
                            </div>
                            <select
                                name="state"
                                value={state}
                                onChange={handleChange}
                                className="input input-bordered w-full"
                            >
                                <option value="PENDIENTE">PENDINTE</option>
                                <option value="EN_PROCESO">EN PROCESO</option>
                                <option value="FINALIZADO">FINALIZADO</option>
                                <option value="CANCELADO">CANCELADO</option>
                            </select>
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Fecha de consulta:</span>
                            </div>
                            <input
                                type="date"
                                name="consultationDate"
                                value={consultationDate}
                                onChange={handleChange}
                                className="input input-bordered w-full"
                            />
                        </div>

                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Observación:</span>
                            </div>
                            <input
                                type="text"
                                name="observations"
                                value={observations}
                                onChange={handleChange}
                                placeholder="observación"
                                required
                                className="input input-bordered w-full"
                            />
                        </div>
                        <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Costo:</span>
                            </div>
                            <input
                                type="text"
                                name="costConsultation"
                                value={costConsultation}
                                onChange={handleChange}
                                placeholder="costo"
                                required
                                className="input input-bordered w-full"
                            />
                        </div>

                        {/* <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Sexo:</span>
                            </div>
                            <div className="join">
                                <label className="cursor-pointer">
                                    <input
                                        type="radio"
                                        name="sex"
                                        value="MACHO"
                                        checked={sex === 'MACHO'}
                                        onChange={handleChange}
                                        className="radio"
                                    />
                                    <span className="ml-2">MACHO</span>
                                </label>
                                <label className="cursor-pointer ml-4">
                                    <input
                                        type="radio"
                                        name="sex"
                                        value="HEMBRA"
                                        checked={sex === 'HEMBRA'}
                                        onChange={handleChange}
                                        className="radio"
                                    />
                                    <span className="ml-2">HEMBRA</span>
                                </label>
                            </div>
                        </div> */}

                        {/* <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Alergias:</span>
                            </div>
                            <input
                                type="text"
                                name="allergies"
                                value={allergies}
                                onChange={handleChange}
                                placeholder="Ingresar alergias"
                                className="input input-bordered w-full"
                            />
                        </div> */}

                        {/* <div className="form-control">
                            <div className="label">
                                <span className="label-text font-semibold text-base-300">Castrado:</span>
                            </div>
                            <input
                                type="checkbox"
                                name="castrated"
                                checked={castrated}
                                onChange={handleChange}
                                className="checkbox"
                            />
                        </div> */}

                    </div>

                    {/* <div className="form-control w-full pb-2">
                        <div className="label">
                            <span className="label-text font-semibold text-base-300">Detalles:</span>
                        </div>
                        <textarea
                            name="details"
                            value={details}
                            onChange={handleChange}
                            placeholder="Ingresar detalles"
                            className="textarea textarea-bordered w-full"
                        />
                    </div> */}

                    <div className="flex justify-end">
                        <button
                            type="submit"
                            className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 mr-2"
                        >
                            {isEdit ? 'Actualizar' : 'Guardar'}
                        </button>
                        <button
                            onClick={toggle}
                            type="button"
                            className="bg-gray-500 text-white py-2 px-4 rounded hover:bg-gray-600"
                        >
                            Cancel
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default ConsultationForm;

