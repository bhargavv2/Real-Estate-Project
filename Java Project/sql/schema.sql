IF DB_ID(N'real_estate_db') IS NULL CREATE DATABASE real_estate_db;
GO
USE real_estate_db;
GO
IF OBJECT_ID(N'dbo.users',N'U') IS NULL
CREATE TABLE dbo.users (
 user_id BIGINT IDENTITY(1,1) PRIMARY KEY,
 name NVARCHAR(120) NOT NULL,
 email NVARCHAR(254) NOT NULL UNIQUE,
 password_hash NVARCHAR(255) NOT NULL,
 phone NVARCHAR(32) NOT NULL,
 role NVARCHAR(10) NOT NULL CHECK(role IN ('BUYER','SELLER','ADMIN')),
 created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);
GO
IF OBJECT_ID(N'dbo.properties',N'U') IS NULL
CREATE TABLE dbo.properties (
 property_id BIGINT IDENTITY(1,1) PRIMARY KEY,
 seller_id BIGINT NOT NULL,
 title NVARCHAR(180) NOT NULL,
 property_type NVARCHAR(16) NOT NULL CHECK(property_type IN ('HOUSE','APARTMENT','LAND','COMMERCIAL')),
 location NVARCHAR(200) NOT NULL,
 price DECIMAL(15,2) NOT NULL CHECK(price>0),
 bedrooms INT NOT NULL DEFAULT 0 CHECK(bedrooms>=0),
 bathrooms INT NOT NULL DEFAULT 0 CHECK(bathrooms>=0),
 area DECIMAL(12,2) NOT NULL CHECK(area>0),
 description NVARCHAR(MAX) NOT NULL,
 status NVARCHAR(12) NOT NULL DEFAULT 'AVAILABLE' CHECK(status IN ('AVAILABLE','INACTIVE','SOLD')),
 created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
 updated_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
 CONSTRAINT fk_properties_seller FOREIGN KEY(seller_id) REFERENCES dbo.users(user_id)
);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='idx_property_search' AND object_id=OBJECT_ID('dbo.properties')) CREATE INDEX idx_property_search ON dbo.properties(status,property_type,price);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='idx_property_location' AND object_id=OBJECT_ID('dbo.properties')) CREATE INDEX idx_property_location ON dbo.properties(location);
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='idx_property_seller' AND object_id=OBJECT_ID('dbo.properties')) CREATE INDEX idx_property_seller ON dbo.properties(seller_id);
GO
IF OBJECT_ID(N'dbo.interests',N'U') IS NULL
CREATE TABLE dbo.interests (
 interest_id BIGINT IDENTITY(1,1) PRIMARY KEY,
 property_id BIGINT NOT NULL,
 buyer_id BIGINT NOT NULL,
 interest_date DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
 status NVARCHAR(12) NOT NULL DEFAULT 'NEW' CHECK(status IN ('NEW','CONTACTED','CLOSED','WITHDRAWN')),
 CONSTRAINT fk_interest_property FOREIGN KEY(property_id) REFERENCES dbo.properties(property_id) ON DELETE CASCADE,
 CONSTRAINT fk_interest_buyer FOREIGN KEY(buyer_id) REFERENCES dbo.users(user_id) ON DELETE CASCADE,
 CONSTRAINT uq_buyer_property UNIQUE(buyer_id,property_id)
);
GO
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='idx_interest_seller_view' AND object_id=OBJECT_ID('dbo.interests')) CREATE INDEX idx_interest_seller_view ON dbo.interests(property_id,interest_date);
GO
-- Create an initial administrator outside public registration. Generate a PBKDF2 hash
-- with realestate.util.PasswordHashMain, then run this manually with the hash substituted:
-- INSERT INTO dbo.users(name,email,password_hash,phone,role) VALUES(N'Administrator',N'admin@example.com',N'<PBKDF2_HASH>',N'',N'ADMIN');
-- Create a restricted application login/user and grant only SELECT, INSERT, UPDATE, DELETE.
