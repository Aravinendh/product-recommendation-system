import React from 'react';
import { Link } from 'react-router-dom';
import { recommendationService } from '../services/recommendationService';

const ProductCard = ({ product }) => {
  const handleProductClick = async () => {
    try {
      await recommendationService.trackActivity(product.id, 'CLICK');
    } catch (err) {
      console.error('Error tracking product click:', err);
    }
  };

  return (
    <div className="product-card">
      <Link to={`/products/${product.id}`} className="product-link" onClick={handleProductClick}>
        <div className="product-image">
          <img src={product.imageURL} alt={product.name} onError={(e) => {
            e.target.src = 'https://via.placeholder.com/400x200?text=No+Image';
          }} />
        </div>
        <div className="product-info">
          <div className="product-category">{product.category}</div>
          <h3 className="product-title">{product.name}</h3>
          <p className="product-description">
            {product.description.length > 80 
              ? `${product.description.substring(0, 80)}...` 
              : product.description
            }
          </p>
          <div className="product-price">${product.price}</div>
        </div>
      </Link>
    </div>
  );
};

export default ProductCard;
