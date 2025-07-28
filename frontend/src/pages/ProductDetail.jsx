import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { productService } from '../services/productService';
import { recommendationService } from '../services/recommendationService';
import ProductCard from '../components/ProductCard';

const ProductDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [recommendations, setRecommendations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [recommendationsLoading, setRecommendationsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchProduct();
  }, [id]);

  useEffect(() => {
    if (product) {
      fetchRecommendations();
      // Track product view activity
      trackProductView();
    }
  }, [product]);

  const fetchProduct = async () => {
    try {
      setLoading(true);
      const data = await productService.getProductById(id);
      setProduct(data);
    } catch (err) {
      setError('Failed to fetch product details');
      console.error('Error fetching product:', err);
    } finally {
      setLoading(false);
    }
  };

  const trackProductView = async () => {
    try {
      await recommendationService.trackActivity(product.id, 'VIEW');
    } catch (err) {
      console.error('Error tracking product view:', err);
    }
  };

  const fetchRecommendations = async () => {
    try {
      setRecommendationsLoading(true);
      const data = await productService.getRecommendations(product.id);
      setRecommendations(data);
    } catch (err) {
      console.error('Error fetching recommendations:', err);
      // Don't show error for recommendations, just log it
    } finally {
      setRecommendationsLoading(false);
    }
  };

  if (loading) return <div className="loading">Loading product details...</div>;
  if (error) return <div className="error">{error}</div>;
  if (!product) return <div className="error">Product not found</div>;

  return (
    <div className="product-detail">
      <div className="container">
        <button onClick={() => navigate(-1)} className="back-button">
          ← Back
        </button>
        
        {/* Product Details Section */}
        <div className="product-detail-content">
          <div className="product-detail-image">
            <img 
              src={product.imageURL} 
              alt={product.name}
              onError={(e) => {
                e.target.src = 'https://via.placeholder.com/500x400?text=No+Image';
              }}
            />
          </div>
          <div className="product-detail-info">
            <div className="product-category-badge">{product.category}</div>
            <h1 className="product-detail-title">{product.name}</h1>
            <div className="product-detail-price">${product.price}</div>
            <div className="product-detail-description">
              <h3>Description</h3>
              <p>{product.description}</p>
            </div>
            <div className="product-actions">
              <button className="btn btn-primary add-to-cart-btn">
                Add to Cart
              </button>
              <button className="btn btn-secondary wishlist-btn">
                Add to Wishlist
              </button>
            </div>
          </div>
        </div>

        {/* Recommendations Section */}
        <div className="recommendations-section">
          <h2 className="recommendations-title">
            You may also like
            <span className="recommendations-subtitle">
              More products from {product.category}
            </span>
          </h2>
          
          {recommendationsLoading ? (
            <div className="recommendations-loading">
              <div className="loading">Loading recommendations...</div>
            </div>
          ) : recommendations.length > 0 ? (
            <div className="recommendations-grid">
              {recommendations.slice(0, 4).map(recommendedProduct => (
                <ProductCard key={recommendedProduct.id} product={recommendedProduct} />
              ))}
            </div>
          ) : (
            <div className="no-recommendations">
              <p>No similar products found in this category.</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;
