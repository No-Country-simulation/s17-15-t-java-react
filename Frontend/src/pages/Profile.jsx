import logo from "../assets/logo.png";
import mascot from "../assets/mascot.jpg";
function Profile() {
  return (
    <div>
      <div className="bg-base-300">
        <div className="flex justify-center items-center">
          <img src={logo} alt="logo" />
        </div>
      </div>
      <div className="flex justify-between items-center w-full">
        <div className="flex-1 flex justify-center">
          <div className="flex flex-col items-center">
            <h1>Mi mascota</h1>
          </div>
        </div>

        <div className="mr-4 flex justify-center items-center bg-primary rounded-full space-x-5 h-6 w-28">
          <button className="text-white text-xs">Crear consulta +</button>
        </div>
      </div>
      <div className="bg-gray-500 rounded-lg m-5 grid grid-cols-3">
        <div className="flex justify-start items-start">
          <img src={mascot} alt="mascot" className="w-52 ml-4 mb-4 mt-4" />
        </div>
        <div className="flex flex-col justify-center space-y-5">
          <section>
            <h3>ID: </h3>
          </section>
          <section>
            <h3>Especie: </h3>
          </section>
          <section>
            <h3>Raza: </h3>
          </section>
          <section>
            <h3>Sexo: </h3>
          </section>
          <section>
            <h3>Fecha nacimiento: </h3>
          </section>
        </div>
        <div className="flex flex-col justify-center space-y-5">
          <section className="flex items-start mr-">
            <h3>Nombre: </h3>
          </section>
          <section>
            <h3>Castrado: </h3>
          </section>
          <section>
            <h3>Alergias: </h3>
          </section>
          <section>
            <h3>Propietario: </h3>
          </section>
          <section>
            <h3>Activo: </h3>
          </section>
        </div>
      </div>
      <div className="flex justify-center items-center">
         <button className="bg-primary rounded-lg w-96 h-11">Historial medico</button>
      </div>
     
    </div>
  );
}
export default Profile;