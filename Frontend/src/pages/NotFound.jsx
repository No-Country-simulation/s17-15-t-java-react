import { Link } from "react-router-dom";
function NotFound() {
    return (
      <div className="bg-opacity-90 bg-base-200 flex flex-col items-center justify-center h-screen">
        <div className="text-center text-custom-primary -translate-y-32">
          <h1 className="mb-4 text-6xl font-semibold text-custom-error">404</h1>
          <p className="mb-4 text-lg text-custom-secondary">Oops! Looks like you're lost.</p>
          <div className="animate-bounce">
            <svg className="mx-auto h-16 w-16 text-custom-error" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8"></path>
            </svg>
          </div>
          <p className="mt-4 text-custom-secondary">
            Let's get you back <Link className="mt-4 text-primary font-semibold" to="/">Home</Link>.

          </p>
        </div>
      </div>
  
  );
  }
  
  export default NotFound;