-- Initial data for the database

-- Insert sample products
INSERT INTO products (name, description, price, category, image_url) VALUES
('Laptop Pro 15', 'High-performance laptop with 16GB RAM and 512GB SSD', 1299.99, 'Electronics', 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400'),
('Wireless Headphones', 'Premium noise-cancelling wireless headphones', 199.99, 'Electronics', 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400'),
('Coffee Maker', 'Automatic drip coffee maker with programmable timer', 89.99, 'Home & Kitchen', 'https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=400'),
('Running Shoes', 'Comfortable running shoes with excellent cushioning', 129.99, 'Sports', 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400'),
('Smartphone X', 'Latest smartphone with advanced camera and 5G connectivity', 899.99, 'Electronics', 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=400'),
('Yoga Mat', 'Non-slip yoga mat perfect for home workouts', 29.99, 'Sports', 'https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=400'),
('Blender', 'High-speed blender for smoothies and food preparation', 149.99, 'Home & Kitchen', 'https://images.unsplash.com/photo-1570197788417-0e82375c9371?w=400'),
('Gaming Mouse', 'Precision gaming mouse with customizable buttons', 79.99, 'Electronics', 'https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=400');

-- Insert sample users (password is 'password123' hashed with BCrypt)
INSERT INTO users (name, email, password, role) VALUES
('Admin User', 'admin@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'ADMIN'),
('John Doe', 'john@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'CUSTOMER'),
('Jane Smith', 'jane@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'CUSTOMER');
