import React, { useState, useEffect } from 'react';
import { FaPlus } from 'react-icons/fa';
import { useAuth } from '../../contexts/AuthContext.jsx';
import Status from '../UI/Status_7px.jsx';
// import useFormData from '../../../hooks/useFormData2.js';
import useJSON from '../../hooks/useJSON_subfijo.js';	
import TreatmentCard from './TreatmentCard.jsx';
import CreateTreatment from './CreateTreatment.jsx';
const TreatmentList = (id) => {
    const { jwt } = useAuth("state");
    const [modal, setModal] = useState(false);

  console.log(id.id);

    const baseURL = `https://veterinaria-bef3.onrender.com/treatment`;
    const filter = `/pet/${id.id}`

    const {
        data, isLoading, isError,

        createItem, isCreating, createError, newItemId,
        updateItem, isUpdating, updateError,
        deleteItem, isDeleting, deleteError,

    } = useJSON(baseURL, jwt, filter);

    const handleSave = (fromData) => {
      if (!isCreating) {
        
          console.log(fromData);
          createItem(fromData);
          alert("Creado")
          setModal(false);

      }
  };

  // useEffect(() => {
  //     if (newItemId) {
  //         console.log(`Nuevo elemento creado con ID: ${newItemId}`);
  //         //para SONGS newItemId
  //     }
  // }, [newItemId]);


  // UPDATE
  const handleEdit = (fromData, id) => {
      updateItem(fromData, id);

  };

  //DELETE
  const handleDelete = (id) => {
      if (!isDeleting) {
          deleteItem(id);

      }
  };
  return (
    <>
      {/* <div className="header text-center mt-1"> */}
      {/* <h3 className="text-2xl font-bold mb-4">Albums List</h3> */}

      <div className="flex justify-end items-center pb-3">
        <button
          className="tooltip tooltip-top btn btn-xs bg-base-300 bg-opacity-90 text-base-100 inline-flex items-center justify-center text-[8px] overflow-ellipsis border-base-300"
          onClick={() => setModal(true)}
        >
          <FaPlus size={10} /> Agregar
        </button>
    
      </div>
  
      <div className="grid grid-cols-3 gap-2">
        {data?.content?.map((obj) => (
          <TreatmentCard
            key={obj.id_consultation}
            itemObj={obj}
            deleteItem={handleDelete}
            updateListArray={handleEdit} // Pasar la función handleEdit para abrir el modal de edición
            // artistName={artistsMap[obj.artist]}
          />
        ))}
      </div>
      {/* <button
            className="absolute buttom-0 right-3 tooltip tooltip-right btn btn-xs bg-base-300 bg-opacity-90 text-base-100 inline-flex items-center justify-center text-[8px] overflow-ellipsis" data-tip="Agregar Mascota"
            onClick={() => setModal(true)}
        >
            <FaPlus size={10} />Agregar

        </button> */}

      <CreateTreatment
        toggle={() => setModal(!modal)}
        modal={modal}
        save={handleSave}
        idPet={id.id}
      />

      <Status
        isLoading={isLoading}
        isError={isError}
        isCreating={isCreating}
        createError={createError}
        isUpdating={isUpdating}
        updateError={updateError}
        isDeleting={isDeleting}
        deleteError={deleteError}
      />
    </>
  );
};

export default TreatmentList;
