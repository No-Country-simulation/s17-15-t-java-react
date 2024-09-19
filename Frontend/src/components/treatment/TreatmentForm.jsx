import { useState, useEffect } from "react";
import { FaTimes } from "react-icons/fa";
import { useAuth } from "../../contexts/AuthContext";
import useJSON from "../../hooks/useJSON_subfijo.js";
import Navbar from "../Navbar.jsx";
function TreatmentForm({
  modal,
  toggle,
  onSave,
  objTreatment = {},
  isEdit = false,
}) {
  // const [description, setDescription] = useState("");
  // const [duration, setDuration] = useState("");
  // const [additionalObservations, setAdditionalObservations] = useState("");
  // const [treatmentCost, setTreatmentCost] = useState("");
  const [diagnosisId, setDiagnosisId] = useState(0);
  const [hospitalizationId, setHospitalizationId] = useState(0);
  const baseURL = `https://veterinaria-bef3.onrender.com/diagnosis/all`;
  const [formData, setFormData] = useState({
    treatmentDescription: "",
    duration: "",
    additionalObservations: "",
    treatmentCost: "",
    diagnosisId: 4,
  });
  useEffect(() => {
    if (isEdit && objTreatment) {
      // setDescription(objTreatment.description || "");
      // setDuration(objTreatment.duration || "");
      // setAdditionalObservations(objTreatment.additionalObservations || "");
      // setTreatmentCost(objTreatment.treatmentCost || "");
      setDiagnosisId(objTreatment.diagnosisId || 0);
      setHospitalizationId(objTreatment.hospitalizationId || 0);
    }
  }, [isEdit, objTreatment]);

  // const handleChange = (e) => {
  //   const { name, value } = e.target;
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };
  //   // if (name === "description") setDescription(value);
  //   // if (name === "duration") setDuration(value);
  //   // if (name === "additionalObservations") setAdditionalObservations(value);
  //   // if (name === "treatmentCost") setTreatmentCost(value);
  //   if (name === "diagnosisId") setDiagnosisId(value);
  //   if (name === "hospitalizationId") setHospitalizationId(value);
  // };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(formData);

    onSave(formData);
    toggle(false);
  };
  if (!modal) {
    return null;
  }
  return (
    <div>
      <Navbar className="w-full fixed top-0 left-0 z-50" />
      <div className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto bg-white">
        <div className="bg-info-content rounded-lg shadow-lg w-full max-w-4xl">
        
          <form
            onSubmit={handleSubmit}
            className="bg-primary bg-opacity-10 p-4 border-2 rounded-lg"
          >
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="form-control">
                <label className="block text-sm font-medium mb-1">
                  Descripción tratamiento
                </label>

                <input
                  type="text"
                  name="treatmentDescription"
                  value={formData.treatmentDescription}
                  onChange={handleChange}
                  placeholder="Escribe el tipo de estudio a realizar"
                  required
                  className="input input-sm input-bordered w-96"
                />
              </div>

              <div className="form-control">
                <label className="block text-sm font-medium mb-1">
                  Duración
                </label>

                <input
                  type="text"
                  name="duration"
                  value={formData.duration}
                  onChange={handleChange}
                  placeholder="Indica aqui el tratamiento del paciente"
                  required
                  className="input input-sm input-bordered w-96"
                />
              </div>
            </div>

            <div className="form-control">
              <label className="block text-sm font-medium mb-1">
                Observaciones adicionales:
              </label>

              <textarea
                name="additionalObservations"
                value={formData.additionalObservations}
                onChange={handleChange}
                placeholder="Ingresar detalles"
                className="textarea textarea-xs textarea-bordered w-full"
              />
            </div>
            <div className="form-control">
              <label className="block text-sm font-medium mb-1">
                Costo del tratamiento
              </label>

              <input
                type="text"
                name="treatmentCost"
                value={formData.treatmentCost}
                onChange={handleChange}
                placeholder="Indica aqui el costo del paciente"
                required
                className="input input-sm input-bordered w-96"
              />
            </div>
   
            <div className="flex justify-end gap-3 mt-3 pb-2">
              {!isEdit && (
                <button onClick={toggle} type="button" className="btn btn-sm">
                  Cancelar
                </button>
              )}

              <button type="submit" className="btn btn-sm btn-primary">
                {isEdit ? "Guardar" : "Finalizar"}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
export default TreatmentForm;
