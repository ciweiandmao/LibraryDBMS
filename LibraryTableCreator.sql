CREATE TABLE `book` (
  `BOID` varchar(20) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `BookshelfID` varchar(20) DEFAULT NULL,
  `ISBN` varchar(50) DEFAULT NULL,
  `Price` varchar(10) DEFAULT NULL,
  `ManagerID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`BOID`),
  KEY `BookshelfID` (`BookshelfID`),
  KEY `ManagerID` (`ManagerID`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`BookshelfID`) REFERENCES `bookshelf` (`BOSID`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`ManagerID`) REFERENCES `manager` (`MANID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `bookimport` (
  `BOIID` varchar(20) NOT NULL,
  `BookID` varchar(20) DEFAULT NULL,
  `PurchaseChecklistID` varchar(20) DEFAULT NULL,
  `ImportDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`BOIID`),
  KEY `BookID` (`BookID`),
  KEY `PurchaseChecklistID` (`PurchaseChecklistID`),
  CONSTRAINT `bookimport_ibfk_1` FOREIGN KEY (`BookID`) REFERENCES `book` (`BOID`),
  CONSTRAINT `bookimport_ibfk_2` FOREIGN KEY (`PurchaseChecklistID`) REFERENCES `purchasechecklist` (`PCID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `bookloan` (
  `BLID` varchar(20) NOT NULL,
  `StudentID` varchar(20) DEFAULT NULL,
  `BookID` varchar(20) DEFAULT NULL,
  `BorrowedDate` varchar(20) DEFAULT NULL,
  `DeadLine` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`BLID`),
  KEY `StudentID` (`StudentID`),
  KEY `BookID` (`BookID`),
  CONSTRAINT `bookloan_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`STUID`),
  CONSTRAINT `bookloan_ibfk_2` FOREIGN KEY (`BookID`) REFERENCES `book` (`BOID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `bookreturn` (
  `BRID` varchar(20) NOT NULL,
  `StudentID` varchar(20) DEFAULT NULL,
  `BookID` varchar(20) DEFAULT NULL,
  `ReturnDate` varchar(20) DEFAULT NULL,
  `BorrowStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`BRID`),
  KEY `StudentID` (`StudentID`),
  KEY `BookID` (`BookID`),
  CONSTRAINT `bookreturn_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`STUID`),
  CONSTRAINT `bookreturn_ibfk_2` FOREIGN KEY (`BookID`) REFERENCES `book` (`BOID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `bookshelf` (
  `BOSID` varchar(20) NOT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `Category` varchar(50) DEFAULT NULL,
  `BookCount` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`BOSID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `device` (
  `DEVID` varchar(20) NOT NULL,
  `DeviceType` varchar(50) DEFAULT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `Status` varchar(10) DEFAULT NULL,
  `InstallationDate` varchar(20) DEFAULT NULL,
  `ManagerID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DEVID`),
  KEY `ManagerID` (`ManagerID`),
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`ManagerID`) REFERENCES `manager` (`MANID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `deviceuse` (
  `DUID` varchar(20) NOT NULL,
  `StudentID` varchar(20) DEFAULT NULL,
  `DeviceID` varchar(20) DEFAULT NULL,
  `UsageDate` varchar(20) DEFAULT NULL,
  `UsageStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`DUID`),
  KEY `StudentID` (`StudentID`),
  KEY `DeviceID` (`DeviceID`),
  CONSTRAINT `deviceuse_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`STUID`),
  CONSTRAINT `deviceuse_ibfk_2` FOREIGN KEY (`DeviceID`) REFERENCES `device` (`DEVID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `manager` (
  `MANID` varchar(20) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Age` varchar(10) DEFAULT NULL,
  `Work` varchar(50) DEFAULT NULL,
  `Salary` varchar(20) DEFAULT NULL,
  `HireDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MANID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `purchasechecklist` (
  `PCID` varchar(20) NOT NULL,
  `ManagerID` varchar(50) DEFAULT NULL,
  `SupplierID` varchar(20) DEFAULT NULL,
  `Number` varchar(10) DEFAULT NULL,
  `Price` varchar(20) DEFAULT NULL,
  `PurchaseDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PCID`),
  KEY `ManagerID` (`ManagerID`),
  KEY `SupplierID` (`SupplierID`),
  CONSTRAINT `purchasechecklist_ibfk_1` FOREIGN KEY (`ManagerID`) REFERENCES `manager` (`MANID`),
  CONSTRAINT `purchasechecklist_ibfk_2` FOREIGN KEY (`SupplierID`) REFERENCES `supplier` (`SUPID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `space` (
  `SPAID` varchar(20) NOT NULL,
  `SpaceType` varchar(10) DEFAULT NULL,
  `SpaceCapacity` varchar(10) DEFAULT NULL,
  `SpaceLocation` varchar(50) DEFAULT NULL,
  `SpaceStatus` varchar(10) DEFAULT NULL,
  `ManagerID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`SPAID`),
  KEY `ManagerID` (`ManagerID`),
  CONSTRAINT `space_ibfk_1` FOREIGN KEY (`ManagerID`) REFERENCES `manager` (`MANID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `spaceorder` (
  `SPAOID` varchar(20) NOT NULL,
  `StudentID` varchar(20) DEFAULT NULL,
  `SpaceID` varchar(20) DEFAULT NULL,
  `OrderTime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`SPAOID`),
  KEY `StudentID` (`StudentID`),
  KEY `SpaceID` (`SpaceID`),
  CONSTRAINT `spaceorder_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`STUID`),
  CONSTRAINT `spaceorder_ibfk_2` FOREIGN KEY (`SpaceID`) REFERENCES `space` (`SPAID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `student` (
  `STUID` varchar(20) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `EnrollmentDate` varchar(20) DEFAULT NULL,
  `Major` varchar(20) DEFAULT NULL,
  `Class` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`STUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `supplier` (
  `SUPID` varchar(20) NOT NULL,
  `Company` varchar(50) DEFAULT NULL,
  `ContactPerson` varchar(50) DEFAULT NULL,
  `ContactNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`SUPID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

