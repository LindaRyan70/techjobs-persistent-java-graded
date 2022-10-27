-- Part 1: Test it with SQL

-- I listed the job table columns and their data types in the table as an SQL comment below.

    -- Table: job
    -- Columns:
    -- id int PK
    -- employer varchar(255)
    -- name varchar(255)
    -- skills varchar(255)

-- I used the following query in MySQL to verify the job table was created w/ appropriate columns & data types. --
-- SELECT * FROM job;


-- Part 2: Test it with SQL

-- First, I had to input employer and skill data into the localhost:8080 form after launching the app to verify it works. --
-- NOTE to self: Included "St. Louis City" as one to check the Test assertion sample will work in query below. --

	-- Verify employer data accessible to database --
	-- SELECT * FROM employer;

	-- Verify skill data accessible to database. --
	-- SELECT * FROM skill;

-- Run GA4 Part 2 Test Query: List the names of the employers in St. Louis City. --
SELECT name FROM employer
WHERE location = "St. Louis City";


-- Part 3: Test it with SQL



-- Part 4: Test it with SQL


