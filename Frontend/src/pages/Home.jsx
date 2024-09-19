function Home() {
  return (
    <div
  className="hero min-h-screen"
  style={{
    backgroundImage: "url(https://th.bing.com/th/id/OIP.TnNCb9Q4hRHwEvRiS0habAHaDP?rs=1&pid=ImgDetMain)",
  }}>
  <div className="hero-overlay bg-opacity-60"></div>
  <div className="hero-content text-neutral-content text-center">
    <div className="max-w-md">
      <h1 className="mb-5 text-5xl font-bold">Welcome to ...........</h1>
      <p className="mb-5">
      We are dedicated to providing the best care for your pets, from routine check-ups to specialized treatments. By logging in or signing up, you'll gain access to a comprehensive range of veterinary services tailored to ensure your animal companions' health and well-being.
      Don't wait any longer—click the button below to start giving your pets the care they deserve.
      </p>
      <button className="btn btn-primary">Get Started</button>
    </div>
  </div>
</div>
  );
}
export default Home;