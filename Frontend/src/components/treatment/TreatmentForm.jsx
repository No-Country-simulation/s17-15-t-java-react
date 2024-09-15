import { useState, useEffect } from "react";
import { FaTimes } from "react-icons/fa";
function TreatmentForm({
  modal,
  toggle,
  onSave,
  objTreatment = {},
  isEdit = false,
  idPet = null,
}) {
  const [petID, setPetID] = useState(0);
  const [consultationDate, setConsultationDate] = useState("");
  const [nextControlDate, setNextControlDate] = useState("");
  const [description, setDescription] = useState("");
  const [treatment, setTreatment] = useState("");
  const [surgery, setSurgery] = useState("");
  const today = new Date();

  useEffect(() => {
    console.log(idPet);
    if (isEdit && objTreatment) {
      setPetID(objTreatment.id_pet || idPet);

      setConsultationDate(objTreatment.consultationDate || "");
      setNextControlDate(objTreatment.lastname || "");
      setDescription(objTreatment.phone || "");
      setSurgery(objTreatment.email || "");
    }
  }, [isEdit, objTreatment]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === "nextControlDate") setNextControlDate(value);
    if (name === "description") setDescription(value);
    if (name === "treatment") setTreatment(value);
    if (name === "surgery") setSurgery(value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const treatmentData = {
      nextdate: nextControlDate,
      description: description,
      treatment: treatment,
      surgery: surgery,
    };

    onSave(treatmentData, isEdit ? objTreatment.id : null);
    toggle(false);
  };

  if (!modal) {
    return null;
  }

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto bg-black bg-opacity-50">
      <div className="bg-white rounded-lg shadow-lg w-full max-w-lg">
        <div className="flex justify-between items-center p-4 border-b">
          <h2 className="text-xl font-semibold">
            {isEdit ? "Actualizar" : "Crear"} Consulta
          </h2>
          <button
            onClick={toggle}
            className="text-gray-500 hover:text-gray-700"
          >
            <FaTimes size={20} />
          </button>
        </div>
        <form onSubmit={handleSubmit} className="px-4 pb-4 w-full">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div className="flex justify-center ">
              <div className="form-control">
                <div className="label">
                  <span className="label-text font-semibold text-base-300">
                    ID Mascota
                  </span>
                </div>
                <label>{idPet}</label>
              </div>
              <div className="form-control">
                <div className="label">
                  <span className="label-text font-semibold text-base-300">
                    Fecha
                  </span>
                </div>
                <label>{today.toLocaleDateString()}</label>
              </div>
            </div>
            <div className="">
              <div className="form-control">
                <div className="label">
                  <span className="label-text font-semibold text-base-300">
                    Fecha de consulta:
                  </span>
                </div>
                <input
                  type="date"
                  name="nextControlDate"
                  value={nextControlDate}
                  onChange={handleChange}
                  className="input input-bordered w-full"
                />
              </div>
              <div className="form-control ">
                <div className="label">
                  <span className="label-text font-semibold text-base-300">
                    Descripcion
                  </span>
                  <span className="label-text-alt text-red-500">Requerido</span>
                </div>
                <input
                  type="text"
                  name="description"
                  value={description}
                  onChange={handleChange}
                  placeholder="Escribe el tipo de estudio a realizar"
                  required
                  className="input input-bordered w-full "
                />
              </div>

              <div className="form-control ">
                <div className="label">
                  <span className="label-text font-semibold text-base-300">
                    Tratamiento
                  </span>
                </div>
                <input
                  type="text"
                  name="treatment"
                  value={treatment}
                  onChange={handleChange}
                  placeholder="Indica aqui el tratamiento del paciente"
                  required
                  className="input input-bordered w-full"
                />
              </div>
            </div>
          </div>
          <div className="form-control w-full pb-2">
            <div className="label">
              <span className="label-text font-semibold text-base-300">
                Detalles:
              </span>
            </div>
            <textarea
              name="surgery"
              value={surgery}
              onChange={handleChange}
              placeholder="Ingresar detalles"
              className="textarea textarea-bordered w-full"
            />
          </div>

          <div className="flex justify-end">
            <button
              type="submit"
              className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 mr-2"
            >
              {isEdit ? "Actualizar" : "Guardar"}
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
export default TreatmentForm;
