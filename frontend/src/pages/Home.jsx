import React, { useState, useEffect } from 'react';
import { productService } from '../services/productService';
import { recommendationService } from '../services/recommendationService';
import ProductCard from '../components/ProductCard';

const Home = () => {
  const [personalizedProducts, setPersonalizedProducts] = useState([]);
  const [youMayAlsoLikeProducts, setYouMayAlsoLikeProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [recommendationsLoading, setRecommendationsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [hasActivity, setHasActivity] = useState(false);
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [categoryProducts, setCategoryProducts] = useState([]);
  const [showCategoryProducts, setShowCategoryProducts] = useState(false);

  const categories = ['Electronics', 'Home & Kitchen', 'Sports & Fitness', 'Fashion & Clothing', 'Books & Education', 'Beauty & Personal Care', 'Automotive', 'Pet Supplies'];

  useEffect(() => {
    fetchInitialData();
  }, []);

  const fetchInitialData = async () => {
    try {
      setLoading(true);
      
      // Check if user has activity
      const userHasActivity = await recommendationService.hasUserActivity();
      setHasActivity(userHasActivity);
      
      // Get personalized recommendations
      const personalizedData = await recommendationService.getPersonalizedRecommendations(8);
      setPersonalizedProducts(personalizedData);
      
      // Get "You may also like" recommendations if user has activity
      if (userHasActivity) {
        setRecommendationsLoading(true);
        const youMayAlsoLikeData = await recommendationService.getYouMayAlsoLikeRecommendations(4);
        setYouMayAlsoLikeProducts(youMayAlsoLikeData);
        setRecommendationsLoading(false);
      }
      
    } catch (err) {
      setError('Failed to fetch recommendations');
      console.error('Error fetching recommendations:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleCategoryClick = async (category) => {
    try {
      setSelectedCategory(category);
      setShowCategoryProducts(true);
      
      // Track category browse activity
      await recommendationService.trackActivity(0, 'CATEGORY_BROWSE');
      
      // Fetch products for this category
      const products = await productService.getProductsByCategory(category);
      setCategoryProducts(products);
      
      // Update activity status and refresh recommendations if needed
      if (!hasActivity) {
        const userHasActivity = await recommendationService.hasUserActivity();
        if (userHasActivity) {
          setHasActivity(true);
          const youMayAlsoLikeData = await recommendationService.getYouMayAlsoLikeRecommendations(4);
          setYouMayAlsoLikeProducts(youMayAlsoLikeData);
        }
      }
      
    } catch (err) {
      console.error('Error fetching category products:', err);
    }
  };

  const handleBackToHome = () => {
    setShowCategoryProducts(false);
    setSelectedCategory('All');
  };

  if (loading) return <div className="loading">Loading recommendations...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="home">
      <div className="container">
        <div className="hero-section">
          <h1>Welcome to Our Store</h1>
          <p>Discover amazing products tailored just for you</p>
        </div>

        {!showCategoryProducts ? (
          <>
            {/* Personalized Recommendations Section */}
            <div className="recommendations-section">
              <h2 className="section-title">
                {hasActivity ? 'Recommended for You' : 'Popular Products'}
                <span className="section-subtitle">
                  {hasActivity ? 'Based on your browsing history' : 'Trending items you might like'}
                </span>
              </h2>
              
              {personalizedProducts.length > 0 ? (
                <div className="products-grid">
                  {personalizedProducts.map(product => (
                    <ProductCard key={product.id} product={product} />
                  ))}
                </div>
              ) : (
                <div className="no-products">
                  <p>No recommendations available at the moment.</p>
                </div>
              )}
            </div>

            {/* You May Also Like Section - Only show if user has activity */}
            {hasActivity && (
              <div className="you-may-also-like-section">
                <h2 className="section-title">
                  You May Also Like
                  <span className="section-subtitle">
                    Based on your recent activity
                  </span>
                </h2>
                
                {recommendationsLoading ? (
                  <div className="recommendations-loading">
                    <div className="loading">Loading more recommendations...</div>
                  </div>
                ) : youMayAlsoLikeProducts.length > 0 ? (
                  <div className="recommendations-grid">
                    {youMayAlsoLikeProducts.map(product => (
                      <ProductCard key={product.id} product={product} />
                    ))}
                  </div>
                ) : (
                  <div className="no-recommendations">
                    <p>No additional recommendations available.</p>
                  </div>
                )}
              </div>
            )}

            {/* Category Browse Section */}
            <div className="category-browse-section">
              <h2 className="section-title">
                Shop by Category
                <span className="section-subtitle">
                  Explore our wide range of products
                </span>
              </h2>
              <div className="category-grid">
                {categories.map(category => (
                  <div
                    key={category}
                    className="category-card"
                    onClick={() => handleCategoryClick(category)}
                  >
                    <div className="category-icon">
                      {getCategoryIcon(category)}
                    </div>
                    <h3>{category}</h3>
                    <p>Explore {category.toLowerCase()}</p>
                  </div>
                ))}
              </div>
            </div>
          </>
        ) : (
          /* Category Products View */
          <div className="category-products-section">
            <div className="category-header">
              <button onClick={handleBackToHome} className="back-button">
                ← Back to Home
              </button>
              <h2>{selectedCategory}</h2>
            </div>
            
            {categoryProducts.length > 0 ? (
              <div className="products-grid">
                {categoryProducts.map(product => (
                  <ProductCard key={product.id} product={product} />
                ))}
              </div>
            ) : (
              <div className="no-products">
                <p>No products found in this category.</p>
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

// Helper function to get category icons
const getCategoryIcon = (category) => {
  const icons = {
    'Electronics': '📱',
    'Home & Kitchen': '🏠',
    'Sports & Fitness': '🏃',
    'Fashion & Clothing': '👕',
    'Books & Education': '📚',
    'Beauty & Personal Care': '💄',
    'Automotive': '🚗',
    'Pet Supplies': '🐕'
  };
  return icons[category] || '🛍️';
};

export default Home;
