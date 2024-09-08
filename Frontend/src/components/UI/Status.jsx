import React from "react";
import { FaSpinner, FaExclamationTriangle } from "react-icons/fa";

const Status = ({ isLoading, isError, isCreating, isUpdating, isDeleting, createError, updateError, deleteError }) => {
    return (
        <>
            {isLoading && (
                <div className="flex items-center space-x-2 text-gray-500">
                    <FaSpinner className="animate-spin" />
                    <p>Loading...</p>
                </div>
            )}
            {isError && (
                <div className="flex items-center space-x-2 text-red-500">
                    <FaExclamationTriangle />
                    <p>Error fetching data</p>
                </div>
            )}
            {isCreating && (
                <div className="flex items-center space-x-2 text-blue-500">
                    <FaSpinner className="animate-spin" />
                    <p>Creating item...</p>
                </div>
            )}
            {createError && (
                <div className="flex items-center space-x-2 text-red-500">
                    <FaExclamationTriangle />
                    <p>Error creating item</p>
                </div>
            )}
            {isUpdating && (
                <div className="flex items-center space-x-2 text-green-500">
                    <FaSpinner className="animate-spin" />
                    <p>Updating item...</p>
                </div>
            )}
            {updateError && (
                <div className="flex items-center space-x-2 text-red-500">
                    <FaExclamationTriangle />
                    <p>Error updating item</p>
                </div>
            )}
            {isDeleting && (
                <div className="flex items-center space-x-2 text-gray-500">
                    <FaSpinner className="animate-spin" />
                    <p>Deleting item...</p>
                </div>
            )}
            {deleteError && (
                <div className="flex items-center space-x-2 text-red-500">
                    <FaExclamationTriangle />
                    <p>Error deleting item</p>
                </div>
            )}
        </>
    );
};

export default Status;