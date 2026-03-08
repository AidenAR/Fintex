# Fintex Live Coding Interview

This document describes how to set up your environment before the interview.

---

## 1. Install Docker

Docker is required to run PostgreSQL.

**macOS / Windows:**
- Download [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- Install and start Docker Desktop
- Verify: open a terminal and run `docker --version`

**Linux:**
- Follow [Docker Engine installation](https://docs.docker.com/engine/install/) for your distribution
- Verify: `docker --version`

---

## 2. Run PostgreSQL via Docker

Open a terminal and run:

```bash
# Pull the official PostgreSQL image
docker pull postgres

# Create and start a container with password "interview123" and database "interview"
docker run -e POSTGRES_PASSWORD=interview123 -e POSTGRES_DB=interview -p 5432:5432 --name postgres-interview -d postgres
```

**Explanation:**
- `POSTGRES_PASSWORD=interview123` — sets the password for the `postgres` user
- `POSTGRES_DB=interview` — creates a database named `interview`
- `-p 5432:5432` — exposes PostgreSQL on port 5432
- `--name postgres-interview` — names the container (useful for `docker start/stop`)

**Useful commands:**
- Start container: `docker start postgres-interview`
- Stop container: `docker stop postgres-interview`
- Check if running: `docker ps`

The application uses the same password (`interview123`) in `application.properties`.

---

## 3. Install JDK 17

**Option A: Adoptium (recommended)**

1. Go to [https://adoptium.net/](https://adoptium.net/)
2. Download **Temurin 17** for your OS
3. Run the installer
4. Verify in terminal:

```bash
java -version
```

You should see something like: `openjdk version "17.x.x"`

**Option B: Oracle JDK**

1. Go to [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/#java17)
2. Download JDK 17 for your OS
3. Install and verify with `java -version`

---

## 4. Install IntelliJ IDEA Community Edition

1. Go to [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
2. Download **Community Edition** (free)
3. Install and launch IntelliJ
4. On first run, it will detect JDK automatically (or you can point it to JDK 17)

---

## 5. Open the Project in IntelliJ

1. Start IntelliJ IDEA
2. **File → Open**
3. Select the `fintex-live-coding-interview` folder (the one containing `pom.xml`)
4. Choose **Open as Project**
5. IntelliJ will recognize it as a Maven project and import dependencies (this may take a few minutes)

---

## 6. Run the Application

1. Ensure PostgreSQL is running: `docker start postgres-interview` (if not already started)
2. In IntelliJ, find `InterviewApplication.java` in the project tree
3. Right-click → **Run 'InterviewApplication'**
4. Wait until you see `Started InterviewApplication` in the run console

---

## 7. Verify Configuration

Open a browser or use curl:

```bash
curl http://localhost:8080/health
```

**Expected response when everything works:**
```json
{"status":"up","database":"interview"}
```

**If you see `"status":"down"`** — check that:
- PostgreSQL container is running: `docker ps`
- Password in `application.properties` matches `interview123`
- Database `interview` exists (it is created automatically by the Docker command above)

---

## Summary Checklist

Before the interview, confirm:

- [ ] Docker installed and running
- [ ] PostgreSQL container started (`docker start postgres-interview`)
- [ ] JDK 17 installed (`java -version` shows 17)
- [ ] IntelliJ IDEA Community Edition installed
- [ ] Project opens in IntelliJ and Maven dependencies are resolved
- [ ] Application runs and `http://localhost:8080/health` returns `{"status":"up"}`
