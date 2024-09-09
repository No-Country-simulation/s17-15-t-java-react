const EditConsultation = () => {
  return (
    <>
      <div className="flex items-start justify-start">
        <h1 className="text-2xl text-warning-content font-bold">
          Editar Consulta
        </h1>
      </div>

      <div className="flex items-center justify-center ">
        <div className="bg-primary-content w-3/4">
          <form className="grid grid-cols-2 gap-4 p-4">
            {/* Primera columna */}
            <div className="flex flex-col">
              <label className="mb-2">Propietario</label>
              <input
                type="text"
                className="mb-4 p-2 border w-96 h-7 bg-white border-gray-300 rounded"
              />

              <label className="mb-2">Especie</label>
              <input
                type="text"
                className="mb-4 p-2 border w-96 h-7 bg-white border-gray-300 rounded"
              />
              <label className="mb-2">Fecha nacimiento</label>
              <input
                type="text"
                className="p-2 border w-96 h-7 bg-white border-gray-300 rounded"
              />
              <label className="mb-2">Castrado</label>
              <select className="bg-white border border-gray-300 rounded w-56 h-7">
                <option>Si</option>
                <option>No</option>
              </select>
            </div>

            {/* Segunda columna */}
            <div className="flex flex-col">
              <label className="mb-2">Sexo</label>

              <div className="flex items-center space-x-4 mb-4">
                {/* Radio Macho */}
                <div className="flex items-center">
                  <input
                    id="radio-macho"
                    type="radio"
                    value="macho"
                    name="default-radio"
                    className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                  />
                  <label
                    htmlFor="radio-macho"
                    className="ml-2 text-sm font-medium text-gray-900 dark:text-black"
                  >
                    Macho
                  </label>
                </div>

                {/* Radio Hembra */}
                <div className="flex items-center">
                  <input
                    id="radio-hembra"
                    type="radio"
                    value="hembra"
                    name="default-radio"
                    className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                  />
                  <label
                    htmlFor="radio-hembra"
                    className="ml-2 text-sm font-medium text-gray-900 dark:text-black"
                  >
                    Hembra
                  </label>
                </div>
              </div>

              <label className="mb-2">Raza</label>
              <input
                type="text"
                className="mb-4 p-2 border w-96 h-7 bg-white border-gray-300 rounded"
              />

              <label className="mb-2">Alergias</label>
              <input
                type="text"
                className="mb-4 p-2 border w-96 h-7 bg-white border-gray-300 rounded"
              />
            </div>
          </form>
          <div className="flex justify-end p-4 space-x-8">
            <div className="mr-4 flex justify-center items-center bg-white rounded-md space-x-5 mt-3 h-8 w-28">
              <button className="text-warning-content text-sm flex items-center justify-center">
                Cancelar
              </button>
            </div>
            <div className="mr-4 flex justify-center items-center bg-primary rounded-md space-x-5 mt-3 h-8 w-28">
              <button className="text-white text-sm flex items-center justify-center">
                Finalizar
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default EditConsultation;
