import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { isAuthenticated, getCurrentUser, logout } from '../utils/auth';

const Header = () => {
  const navigate = useNavigate();
  const user = getCurrentUser();
  const authenticated = isAuthenticated();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <header className="header">
      <div className="header-container">
        <Link to="/" className="logo">
          Product Recommendation System
        </Link>
        <nav className="nav-links">
          <Link to="/" className="nav-link">Home</Link>
          {authenticated ? (
            <>
              {user?.role === 'ADMIN' && (
                <Link to="/admin" className="nav-link">Admin Dashboard</Link>
              )}
              <span className="nav-link">Welcome, {user?.name}</span>
              <button onClick={handleLogout} className="nav-link btn">
                Logout
              </button>
            </>
          ) : (
            <>
              <Link to="/login" className="nav-link">Login</Link>
              <Link to="/signup" className="nav-link">Sign Up</Link>
            </>
          )}
        </nav>
      </div>
    </header>
  );
};

export default Header;
