# JDBC Integration

Java + MySQL CRUD demo for WeIntern Pvt Ltd — Week 2 Internship
Assignment, **Task 3**.

Connects to a MySQL database via JDBC and performs INSERT, SELECT,
UPDATE and DELETE on a `students` table through a DAO class, using
`PreparedStatement` everywhere and try-with-resources for cleanup.

> The handbook doesn't name a specific entity for this task, so this
> reuses a simple student record (id, name, age, grade) to keep things
> consistent with Task 1 — swap the table/fields for whatever your
> reviewer expects if they had something else in mind.

## Project Structure

```
JdbcIntegration/
├── src/main/java/com/jdbc/
│   ├── Main.java                # console menu to exercise all CRUD ops
│   ├── Student.java             # simple record (id, name, age, grade)
│   ├── DatabaseConnection.java  # getConnection() — update your credentials here
│   └── StudentDAO.java          # createTable/insert/getAll/update/delete
├── schema.sql                   # CREATE DATABASE / CREATE TABLE
├── pom.xml                      # optional, if you use Maven
└── README.md
```

## 1. Set Up MySQL

1. Make sure MySQL 8.x is installed and running.
2. Run `schema.sql` in MySQL Workbench (or `mysql -u root -p < schema.sql`
   from the CLI) to create the `weintern_db` database and `students` table.
3. Open `DatabaseConnection.java` and update `URL`, `USER` and `PASSWORD`
   to match your MySQL setup.

`Main` also calls `createTable()` on startup (`CREATE TABLE IF NOT
EXISTS`), so the table is created automatically even if you skip the
`CREATE TABLE` part of the script — but the **database** itself
(`weintern_db`) must already exist, so run at least that part of
`schema.sql` first.

## 2. Get the MySQL JDBC Driver

Pick one:

**Option A — Manual JAR (simplest)**
Download `mysql-connector-j-<version>.jar` from
[the MySQL site](https://dev.mysql.com/downloads/connector/j/) and
place it in this project folder.

**Option B — Maven**
Use the included `pom.xml` (already lists `mysql-connector-j`).

## 3. Compile & Run

**With a manual JAR:**

```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
java -cp "out:mysql-connector-j-8.4.0.jar" com.weintern.jdbc.Main
```

(On Windows, use `;` instead of `:` in the classpath.)

**With Maven:**

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.weintern.jdbc.Main"
```

## Features

- **Insert Student** — adds a row via `PreparedStatement`.
- **View All Students** — `SELECT *`, iterates the `ResultSet`.
- **Update Grade** — parameterized `UPDATE ... WHERE id = ?`.
- **Delete Student** — parameterized `DELETE ... WHERE id = ?`.
- Every DB call uses try-with-resources for the `Connection`,
  `Statement`/`PreparedStatement` and `ResultSet`, and catches
  `SQLException` with a message instead of crashing the menu loop.

## Verifying in MySQL Workbench

After each operation in the console app, run `SELECT * FROM students;`
in MySQL Workbench to confirm the change landed — that side-by-side
check is one of the deliverables for this task.

## Notes

- `DatabaseConnection` hardcodes credentials for simplicity — for
  anything beyond a class assignment, don't commit real DB passwords;
  read them from an environment variable or a config file instead.
