import { useState, useEffect } from "react";
import { FaTimes } from "react-icons/fa";
function TreatmentForm({
  modal,
  toggle,
  onSave,
  objTreatment = {},
  idDiagnosis,
  isEdit = false,
  idHospitalization,
}) {
  const [description, setDescription] = useState("");
  const [duration, setDuration] = useState("");
  const [additionalObservations, setAdditionalObservations] = useState("");
  const [treatmentCost, setTreatmentCost] = useState("");
  const [diagnosisId, setDiagnosisId] = useState(0);
  const [hospitalizationId, setHospitalizationId] = useState(0);
  useEffect(() => {
    if (isEdit && objTreatment) {
      setDescription(objTreatment.description || "");
      setDuration(objTreatment.duration || "");
      setAdditionalObservations(objTreatment.additionalObservations || "");
      setTreatmentCost(objTreatment.treatmentCost || "");
      setDiagnosisId(objTreatment.diagnosisId || 0);
      setHospitalizationId(objTreatment.hospitalizationId || 0);
    }
  }, [isEdit, objTreatment]);

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === "description") setDescription(value);
    if (name === "duration") setDuration(value);
    if (name === "additionalObservations") setAdditionalObservations(value);
    if (name === "treatmentCost") setTreatmentCost(value);
    if (name === "diagnosisId") setDiagnosisId(value);
    if (name === "hospitalizationId") setHospitalizationId(value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const treatmentData = {
      description: description,
      duration: duration,
      additionalObservations: additionalObservations,
      treatmentCost: treatmentCost,
      diagnosisId: diagnosisId,
      hospitalizationId: hospitalizationId,
    };
    console.log(treatmentData);
    onSave(treatmentData, isEdit ? objTreatment.id : null);
    toggle(false);
  };

  if (!modal) {
    return null;
  }

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto bg-black bg-opacity-50">
      <div className="bg-info-content rounded-lg shadow-lg w-full max-w-4xl">
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
            <div className="">
              <div className="form-control ">
                <div className="label">
                  <span className="label-text font-semibold text-base-300">
                    Descripción tratamiento
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
                    Duración
                  </span>
                </div>
                <input
                  type="text"
                  name="duration"
                  value={duration}
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
                Observaciones adicionales:
              </span>
            </div>
            <textarea
              name="additionalObservations"
              value={additionalObservations}
              onChange={handleChange}
              placeholder="Ingresar detalles"
              className="textarea textarea-bordered w-full"
            />
          </div>
          <div className="form-control w-full pb-2">
            <div className="label">
              <span className="label-text font-semibold text-base-300">
                Costo del tratamiento
              </span>
            </div>
            <input
              type="number"
              name="treatmentCost"
              value={treatmentCost}
              onChange={handleChange}
              placeholder="Indica aqui el tratamiento del paciente"
              required
              className="input input-bordered w-full"
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
