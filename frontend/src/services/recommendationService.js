import api from './api';

// Generate or get session ID for anonymous users
const getSessionId = () => {
  let sessionId = localStorage.getItem('sessionId');
  if (!sessionId) {
    sessionId = 'session_' + Date.now() + '_' + Math.random().toString(36).substr(2, 9);
    localStorage.setItem('sessionId', sessionId);
  }
  return sessionId;
};

export const recommendationService = {
  // Track user activity
  async trackActivity(productId, activityType) {
    try {
      const sessionId = getSessionId();
      const response = await api.post('/api/recommendations/track', {
        productId,
        activityType
      }, {
        headers: {
          'Session-ID': sessionId
        }
      });
      
      // Update session ID if returned from server
      if (response.data.sessionId) {
        localStorage.setItem('sessionId', response.data.sessionId);
      }
      
      return response.data;
    } catch (error) {
      console.error('Error tracking activity:', error);
      throw error.response?.data || 'Failed to track activity';
    }
  },

  // Get personalized recommendations for home page
  async getPersonalizedRecommendations(limit = 8) {
    try {
      const sessionId = getSessionId();
      const response = await api.get(`/api/recommendations/personalized?limit=${limit}`, {
        headers: {
          'Session-ID': sessionId
        }
      });
      return response.data;
    } catch (error) {
      console.error('Error fetching personalized recommendations:', error);
      throw error.response?.data || 'Failed to fetch recommendations';
    }
  },

  // Get "You may also like" recommendations
  async getYouMayAlsoLikeRecommendations(limit = 4) {
    try {
      const sessionId = getSessionId();
      const response = await api.get(`/api/recommendations/you-may-also-like?limit=${limit}`, {
        headers: {
          'Session-ID': sessionId
        }
      });
      return response.data;
    } catch (error) {
      console.error('Error fetching you may also like recommendations:', error);
      throw error.response?.data || 'Failed to fetch recommendations';
    }
  },

  // Check if user has activity
  async hasUserActivity() {
    try {
      const sessionId = getSessionId();
      const response = await api.get('/api/recommendations/has-activity', {
        headers: {
          'Session-ID': sessionId
        }
      });
      return response.data.hasActivity;
    } catch (error) {
      console.error('Error checking user activity:', error);
      return false;
    }
  }
};