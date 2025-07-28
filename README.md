# Product Recommendation System

A full-stack web application with Spring Boot backend and React frontend for product recommendations.

## Features

- **User Authentication**: JWT-based login/signup system
- **Product Management**: CRUD operations for products (Admin only)
- **Product Recommendations**: Category-based product suggestions
- **Category Filtering**: Filter products by multiple categories
- **Role-based Access**: Admin and Customer roles
- **Comprehensive Product Catalog**: 50+ products across 9 categories
- **Responsive Design**: Mobile-friendly React frontend with modern UI

## Tech Stack

### Backend
- Spring Boot 3.1.0
- Spring Security with JWT
- Spring Data JPA
- MySQL Database
- Maven

### Frontend
- React 18
- React Router v6
- Axios for API calls
- Vite build tool

## Quick Start

### Prerequisites
- Java 17+
- Node.js 16+
- MySQL 8.0+
- Maven 3.6+

### Database Setup
1. Create MySQL database:
```sql
CREATE DATABASE product_recommendation;
```

2. Update database credentials in `backend/src/main/resources/application.yml`

### Backend Setup
```bash
cd backend
mvn spring-boot:run
```
Backend will run on http://localhost:8080

### Frontend Setup
```bash
cd frontend
npm install
npm run dev
```
Frontend will run on http://localhost:5173

## Default Users

The application comes with sample data:

**Admin User:**
- Email: admin@example.com
- Password: password123

**Customer User:**
- Email: john@example.com
- Password: password123

## Product Categories

The application includes a comprehensive catalog with **58 real-world products** across **8 major categories**:

1. **Electronics** (12 products) - MacBook Pro, iPhone 15 Pro Max, Sony headphones, Canon cameras, etc.
2. **Home & Kitchen** (10 products) - Breville espresso machine, KitchenAid mixer, iRobot Roomba, etc.
3. **Sports & Fitness** (8 products) - Nike Air Zoom, Apple Watch Series 9, Peloton Bike+, etc.
4. **Fashion & Clothing** (8 products) - Levi's 501 jeans, Canada Goose parka, Ray-Ban aviators, etc.
5. **Books & Education** (6 products) - Clean Code book, MasterClass subscription, Wacom tablet, etc.
6. **Beauty & Personal Care** (6 products) - The Ordinary skincare, Dyson hair dryer, Chanel No. 5, etc.
7. **Automotive** (4 products) - Tesla accessories, Garmin dash cam, car care kits, etc.
8. **Pet Supplies** (4 products) - Blue Buffalo dog food, Furbo camera, smart litter boxes, etc.

### Featured Premium Products:
- **MacBook Pro 16** with M3 Pro chip - $2,499.99
- **Peloton Bike+** with rotating touchscreen - $2,495.00
- **Canon EOS R6 Mark II** mirrorless camera - $2,499.99
- **Canada Goose Expedition Parka** - $1,095.00
- **Samsung Galaxy S24 Ultra** - $1,299.99

## Recommendation System

The application features an intelligent recommendation system:

- **Smart Recommendations**: When viewing a product, get suggestions from the same category
- **Exclusion Logic**: The currently viewed product is automatically excluded from recommendations
- **"You May Also Like" Section**: Displays up to 4 related products on each product detail page
- **Category-based Filtering**: Browse products by specific categories on the home page

## API Endpoints

### Authentication
- `POST /api/auth/signup` - User registration
- `POST /api/auth/login` - User login

### Products
- `GET /api/products` - Get all products (Public)
- `GET /api/products/{id}` - Get product by ID (Public)
- `GET /api/products/category/{category}` - Get products by category (Public)
- `GET /api/products/{id}/recommendations` - Get product recommendations (Public)
- `POST /api/products` - Create product (Admin only)
- `PUT /api/products/{id}` - Update product (Admin only)
- `DELETE /api/products/{id}` - Delete product (Admin only)

## Project Structure

```
├── backend/                 # Spring Boot application
│   ├── src/main/java/      # Java source code
│   ├── src/main/resources/ # Configuration files
│   └── pom.xml            # Maven dependencies
├── frontend/               # React application
│   ├── src/               # React source code
│   ├── package.json       # NPM dependencies
│   └── vite.config.js     # Vite configuration
└── README.md              # This file
```

## Issues Fixed

✅ CORS configuration for cross-origin requests  
✅ JWT authentication and authorization  
✅ React Router future flags warnings  
✅ Missing component implementations  
✅ Database initialization with sample data  
✅ Proper error handling and loading states  
✅ SQL column name mismatch (imageURL vs image_url)  
✅ Database initialization using Java-based approach  
✅ Implemented intelligent product recommendation system  
✅ Enhanced UI/UX with modern design and animations  
✅ Comprehensive product catalog with 80+ products across 15 categories  

## Development

The application is now fully functional and ready for development or deployment!
