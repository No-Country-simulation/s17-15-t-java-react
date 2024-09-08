import ThemeMenu from "./ThemeMenu";
import Avatar from "./Avatar";
import { Link } from "react-router-dom";
function Navbar() {
    return (
        <div className="navbar bg-base-300 bg-opacity-100 h-[100px]">
            <div className="navbar-start"></div>
            <div className="navbar-center hidden lg:flex">
                <Link to="/home">
                    <img src="/logo/logo_variant.png" alt="Logo" className="w-[213px] h-[101px]" /></Link>
            </div>

            <div className="navbar-end gap-3 pr-5">
                <ThemeMenu />
                <Avatar />

            </div>
        </div>

    )
}
export default Navbar;