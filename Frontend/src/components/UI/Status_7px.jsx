import React from "react";
import { FaSpinner, FaExclamationTriangle } from "react-icons/fa";



const Status = ({ isLoading, isError, isCreating, isUpdating, isDeleting, createError, updateError, deleteError }) => {
    const smallTextStyle = { fontSize: '9px' };
    return (
        <>
            {isLoading && (
                <div className="flex items-center space-x-2 text-gray-500" style={smallTextStyle}>
                    <FaSpinner className="animate-spin" />
                    <p>Cargando...</p>
                </div>
            )}
            {isError && (
                <div className="flex items-center space-x-2 text-red-500" style={smallTextStyle}>
                    <FaExclamationTriangle />
                    <p>Error cargar datos</p>
                </div>
            )}
            {isCreating && (
                <div className="flex items-center space-x-2 text-blue-500" style={smallTextStyle}>
                    <FaSpinner className="animate-spin" />
                    <p>Creando...</p>
                </div>
            )}
            {createError && (
                <div className="flex items-center space-x-2 text-red-500" style={smallTextStyle}>
                    <FaExclamationTriangle />
                    <p>Error al crear</p>
                </div>
            )}
            {isUpdating && (
                <div className="flex items-center space-x-2 text-green-500" style={smallTextStyle}>
                    <FaSpinner className="animate-spin" />
                    <p>Actualizando...</p>
                </div>
            )}
            {updateError && (
                <div className="flex items-center space-x-2 text-red-500" style={smallTextStyle}>
                    <FaExclamationTriangle />
                    <p>Error al actualzar</p>
                </div>
            )}
            {isDeleting && (
                <div className="flex items-center space-x-2 text-gray-500" style={smallTextStyle}>
                    <FaSpinner className="animate-spin" />
                    <p>Eliminando...</p>
                </div>
            )}
            {deleteError && (
                <div className="flex items-center space-x-2 text-red-500" style={smallTextStyle}>
                    <FaExclamationTriangle />
                    <p>Error al elimnar</p>
                </div>
            )}
        </>
    );
};

export default Status;
