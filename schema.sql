CREATE TABLE IF NOT EXISTS library_books (
    id SERIAL PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    year INTEGER NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    pages INTEGER NOT NULL,
    genre VARCHAR(50) NOT NULL,
    quantity INTEGER NOT NULL,
    file_size_mb DOUBLE PRECISION,
    file_format VARCHAR(50),
    cover_type VARCHAR(50),
    weight DOUBLE PRECISION,
    duration_hours DOUBLE PRECISION,
    narrator VARCHAR(255),
    subject_name VARCHAR(255),
    grade_level INTEGER
);