import { useAuth } from "../contexts/AuthContext";
import {useState, useEffect} from "react"

function Avatar() {
  const {user__id} = useAuth('state');
  const [usuario, setUsuario] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const baseURL = `https://veterinaria-bef3.onrender.com/veterinarian/${user__id}`;

  useEffect(() => {
    const fetchAvatar = async () => {
      try {
        const response = await fetch(baseURL);
        if (!response.ok) {
          throw new Error('Failed to fetch pet details');
        }
        const data = await response.json();
        setUsuario(data);
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    };

    fetchAvatar();
  }, [baseURL]);

  const { logout } = useAuth("actions");
  return (
    <div className="dropdown dropdown-end">
      <div tabIndex={0} role="button" className="btn btn-ghost btn-circle avatar">
        <div className="w-24 rounded-full">
          <img
            alt="Tailwind CSS Navbar component"
            src="/logo/image.png" />
            {/* https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp */}
        </div>
      </div>
      
      
      <ul
        tabIndex={0}
        className="text-base-100 menu menu-sm dropdown-content border-[1px] border-base-300 bg-base-300 z-[1] mt-3 p-2 w-44 shadow bg-opacity-95">
        <li><p className="font-semibold truncate pointer-events-non ">{usuario?.name} {usuario?.lastname}</p></li>
        <li><p className="italic pointer-events-none text-xs">{usuario?.specialty}</p></li>
        <li><p className="italic pointer-events-none truncate text-xs">dianaflores@vetcare.com</p></li>
        <li><p className="italic pointer-events-none text-xs">Licencia Nro {usuario?.professionalLicenceNumber}</p></li>
        {/* <li><p className="">Perfil</p></li>
        <li><p className="">Configuración</p></li> */}
        <li><div className="font-semibold" onClick={logout}>Cerrar sesión</div></li>
      </ul>
    </div>
  );
}
export default Avatar;