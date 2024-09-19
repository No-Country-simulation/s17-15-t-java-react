import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';


const ClinicHistory = ({ petId }) => {
    const [clinicHistory, setClinicHistory] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        setLoading(true);
        setError(null); // Reiniciar error antes de cada fetch
        fetch(`https://veterinaria-bef3.onrender.com/clinic-history/${petId}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Error en la respuesta de la API');
                }
                return response.json();
            })
            .then((data) => {
                setClinicHistory(data);
                setLoading(false); // Finaliza carga al obtener datos
            })
            .catch((error) => {
                setError(error); // Capturar error
                setLoading(false); // Finaliza carga en caso de error
            });
    }, [petId]);

    // Si está cargando
    if (loading) {
        return <div>Cargando historia clínica...</div>;
    }

    // Si ocurre un error
    if (error) {
        return (
            <div className="flex items-center justify-center text-center">
                <h2>Error al cargar la historia clínica</h2>
                <p>Error: {error.message}</p>
            </div>
        );
    }

    // Si se ha cargado correctamente la historia clínica
    if (!clinicHistory) {
        return <div>No se encontraron datos de la historia clínica.</div>;
    }

    return (
        <div className="p-6 bg-white shadow-md rounded-lg">
            <button
                onClick={() => navigate(-1)}
                className="bg-orange-400 opacity-20 text-white px-4 py-2 rounded-md hover:bg-orange-400 hover:opacity-100 transition-opacity duration-200"
            >
                Regresar
            </button>
            <h1 className="text-2xl font-bold text-base-content mb-4">Historia Médica de la Mascota</h1>

            {/* Información de la mascota */}
            <div className="mb-6">
                <h2 className="text-xl font-semibold text-primary">Información de la Mascota</h2>
                <p><strong>Nombre:</strong> {clinicHistory.pet.name}</p>
                <p><strong>Especie:</strong> {clinicHistory.pet.species}</p>
                <p><strong>Raza:</strong> {clinicHistory.pet.race}</p>
                <p><strong>Sexo:</strong> {clinicHistory.pet.sex}</p>
                <p><strong>Fecha de Nacimiento:</strong> {clinicHistory.pet.birthdate}</p>
                <p><strong>Castrado:</strong> {clinicHistory.pet.castrated ? 'Sí' : 'No'}</p>
                <p><strong>Alergias:</strong> {clinicHistory.pet.allergies}</p>
                <p><strong>Detalles:</strong> {clinicHistory.pet.details}</p>
            </div>

            {/* Información del dueño */}
            <div className="mb-6">
                <h2 className="text-xl font-semibold text-primary">Información del Dueño</h2>
                <p><strong>Nombre:</strong> {clinicHistory.pet.owner.name} {clinicHistory.pet.owner.lastname}</p>
                <p><strong>Teléfono:</strong> {clinicHistory.pet.owner.phone}</p>
                <p><strong>Email:</strong> {clinicHistory.pet.owner.email}</p>
                <p><strong>Dirección:</strong> {clinicHistory.pet.owner.address}</p>
            </div>

            {/* Diagnósticos */}
            <div className="mb-6">
                <h2 className="text-xl font-semibold text-primary">Diagnósticos</h2>
                <table className="table-auto w-full text-left border-collapse">
                    <thead className="bg-base-300">
                        <tr>
                            <th className="border px-4 py-2">Nombre</th>
                            <th className="border px-4 py-2">Fecha de Diagnóstico</th>
                            <th className="border px-4 py-2">Descripción</th>
                            <th className="border px-4 py-2">Severidad</th>
                            <th className="border px-4 py-2">Próximo Chequeo</th>
                        </tr>
                    </thead>
                    <tbody>
                        {clinicHistory.diagnoses.map((diagnosis, index) => (
                            <tr key={index}>
                                <td className="border px-4 py-2">{diagnosis.name}</td>
                                <td className="border px-4 py-2">{diagnosis.diagnosisDate}</td>
                                <td className="border px-4 py-2">{diagnosis.description}</td>
                                <td className="border px-4 py-2">{diagnosis.severidad}</td>
                                <td className="border px-4 py-2">{diagnosis.nextCheckUp}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            {/* Botón de regreso */}
            <button
                className="bg-orange-400 opacity-20 text-white px-4 py-2 rounded-md hover:bg-orange-400 hover:opacity-100 transition-opacity duration-200"
            >
                Regresar
            </button>
        </div>
    );
};

export default ClinicHistory;
