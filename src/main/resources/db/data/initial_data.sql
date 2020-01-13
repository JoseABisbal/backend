SET IDENTITY_INSERT Authorities ON; 

-- NEWS STATUS
INSERT INTO News_Status (Id, StatusName) VALUES (1,'Borrador');
INSERT INTO News_Status (Id, StatusName) VALUES (2,'Publicado');

-- USERS STATUS
INSERT INTO Users_Status (Id, StatusName) VALUES (1,'Activo');
INSERT INTO Users_Status (Id, StatusName) VALUES (2,'Inactivo');

-- DEFAULT USERS
INSERT INTO Users (Id,UserName,DisplayName,CreatedDate,ModifiedDate,StatusId) 
VALUES (1, 'admin', 'Administrador', getdate(), getdate(), 1);

INSERT INTO Users (Id,UserName,DisplayName,CreatedDate,ModifiedDate,StatusId) 
VALUES (2, 'SLM1BYH', 'Sadiel Gutierrez', getdate(), getdate(), 1);

-- DEFAULT ROLES
INSERT INTO Roles (Id,RoleName,Description) VALUES (1,'Estándar', 'Rol Estándar');
INSERT INTO Roles (Id,RoleName,Description) VALUES (2,'Administración', 'Rol Administrador ');

-- ROLES USERS
INSERT INTO UsersRoles (UserId,RoleId) VALUES (1, 2);
INSERT INTO UsersRoles (UserId,RoleId) VALUES (2, 2);

-- DEFAULT AUTHORITIES
INSERT INTO Authorities (Id,Code,Description) VALUES (1, 'NEWS_READ_WRITE', 'Los usuarios solo puede editar comunicados.');
INSERT INTO Authorities (Id,Code,Description) VALUES (2, 'USERS_READ_WRITE', 'Los usuarios pueden editar comunicados y gestionar usuarios.');

-- AUTHORITIES - ROLES
INSERT INTO Roles_Authorities (authorityid,role_id) VALUES (1,1);
INSERT INTO Roles_Authorities (authorityid,role_id) VALUES (2,2);