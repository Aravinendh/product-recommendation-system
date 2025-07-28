import api from './api';

export const productService = {
  async getAllProducts() {
    try {
      const response = await api.get('/api/products');
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Failed to fetch products';
    }
  },

  async getProductById(id) {
    try {
      const response = await api.get(`/api/products/${id}`);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Failed to fetch product';
    }
  },

  async createProduct(productData) {
    try {
      const response = await api.post('/api/products', productData);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Failed to create product';
    }
  },

  async updateProduct(id, productData) {
    try {
      const response = await api.put(`/api/products/${id}`, productData);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Failed to update product';
    }
  },

  async deleteProduct(id) {
    try {
      const response = await api.delete(`/api/products/${id}`);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Failed to delete product';
    }
  },

  async getProductsByCategory(category) {
    try {
      const response = await api.get(`/api/products/category/${category}`);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Failed to fetch products by category';
    }
  },

  async getRecommendations(id) {
    try {
      const response = await api.get(`/api/products/${id}/recommendations`);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Failed to fetch recommendations';
    }
  }
};
