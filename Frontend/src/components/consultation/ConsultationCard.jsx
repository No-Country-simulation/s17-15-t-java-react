import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import { FaEdit, FaTrashAlt } from "react-icons/fa";
import EditConsultation from "./EditConsultation";

import { useAuth } from "../../contexts/AuthContext";
import ConfirmDeleteModal from "../UI/ConfirmDeleteModal";

function ConsultationCard({ itemObj, deleteItem, updateListArray }) {
  const navigate = useNavigate();
  const { isAuthenticated } = useAuth("state");
  const [modal, setModal] = useState(false);
  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false); //  modal de confirm delete

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
    <div className="shadow-lg rounded-lg relative gap-1">
      <div className="">
        ID Mascota
        <p className="">{itemObj.id_pet ? itemObj.id_pet : "No disponible"}</p>
        Fecha
        <p className="">
          {itemObj.consultationDate
            ? itemObj.consultationDate
            : "No disponible"}
        </p>
        Fecha proximo control
        <p className="">
          {itemObj.anamnesis ? itemObj.anamnesis : "No disponible"}
        </p>
        observations
        <p className="">
          {itemObj.observations ? itemObj.observations : "No disponible"}
        </p>
        state
        <p className="">{itemObj.state ? itemObj.state : "No disponible"}</p>
        costConsultation
        <p className="">
          {itemObj.costConsultation
            ? itemObj.costConsultation
            : "No disponible"}
        </p>
        {isAuthenticated && (
          <div className="absolute bottom-0 flex right-0 px-0.5 space-x-1 rounded-lg bg-opacity-95">
            <FaTrashAlt
              className="cursor-pointer text-red-700"
              //onClick={handleDelete}
              onClick={(e) => {
                e.stopPropagation();
                setIsDeleteModalOpen(true);
              }}
              size={7}
            />
          </div>
        )}
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
