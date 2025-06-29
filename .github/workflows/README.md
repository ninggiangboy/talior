# Talior CI/CD Pipeline

This GitHub Actions workflow provides a comprehensive CI/CD pipeline for the Talior project, which consists of a Kotlin backend and React frontend. The pipeline automatically detects changes in specific directories and runs appropriate jobs based on what has been modified.

## Overview

The pipeline is triggered on:

- **Push** to the `dev` branch
- **Pull Request** targeting the `dev` branch

## Architecture

The workflow uses a smart change detection system that only runs jobs for components that have actually been modified, optimizing build times and resource usage.

### Change Detection

The pipeline uses [dorny/paths-filter](https://github.com/dorny/paths-filter) to detect which parts of the codebase have changed:

- **Backend changes**: Files in the `backend/` directory
- **Frontend changes**: Files in the `frontend/` directory

## Jobs

### 1. `detect-changes`

**Purpose**: Determines which components have been modified  
**Runner**: Self-hosted  
**Outputs**:

- `backend_changed`: Boolean indicating if backend files were modified
- `frontend_changed`: Boolean indicating if frontend files were modified

### 2. `lint-frontend`

**Purpose**: Runs ESLint on the frontend code  
**Trigger**: Only when frontend files have changed  
**Runner**: Self-hosted  
**Dependencies**: `detect-changes`  
**Steps**:

- Setup Node.js 20
- Install pnpm package manager
- Cache pnpm dependencies
- Install frontend dependencies
- Run ESLint

### 3. `unit-test`

**Purpose**: Runs backend unit tests using Gradle  
**Trigger**: Only when backend files have changed  
**Runner**: Self-hosted  
**Dependencies**: `detect-changes`  
**Steps**:

- Setup JDK 21 (Temurin distribution)
- Run Gradle tests with `./gradlew test --no-daemon`

### 4. `sonarqube-backend`

**Purpose**: Performs code quality analysis on backend code  
**Trigger**: Only when backend files have changed  
**Runner**: Self-hosted  
**Dependencies**: `detect-changes`  
**Steps**:

- Setup JDK 21
- Cache SonarQube and Gradle packages
- Build and analyze code using Gradle SonarQube plugin

### 5. `sonarqube-frontend`

**Purpose**: Performs code quality analysis on frontend code  
**Trigger**: Only when frontend files have changed  
**Runner**: Self-hosted  
**Dependencies**: `detect-changes`  
**Steps**:

- Run SonarQube Scanner using the official action

### 6. `build-backend`

**Purpose**: Builds and pushes backend Docker image to Docker Hub  
**Trigger**: Only when backend files have changed  
**Runner**: Self-hosted  
**Dependencies**: `unit-test`, `sonarqube-backend`  
**Steps**:

- Setup Docker Buildx
- Login to Docker Hub
- Build and push Docker image with caching

### 7. `build-frontend`

**Purpose**: Builds and pushes frontend Docker image to Docker Hub  
**Trigger**: Only when frontend files have changed  
**Runner**: Self-hosted  
**Dependencies**: `sonarqube-frontend`, `lint-frontend`  
**Steps**:

- Setup Docker Buildx
- Login to Docker Hub
- Build and push Docker image with caching

## Required Secrets

The following secrets must be configured in your GitHub repository:

| Secret Name          | Description                    | Required For                            |
| -------------------- | ------------------------------ | --------------------------------------- |
| `SONAR_TOKEN`        | SonarQube authentication token | Backend and Frontend SonarQube analysis |
| `SONAR_HOST_URL`     | SonarQube server URL           | Backend and Frontend SonarQube analysis |
| `DOCKERHUB_USERNAME` | Docker Hub username            | Backend and Frontend Docker builds      |
| `DOCKERHUB_TOKEN`    | Docker Hub access token        | Backend and Frontend Docker builds      |

## Docker Images

The pipeline builds and pushes the following Docker images to Docker Hub:

- `{DOCKERHUB_USERNAME}/talior-backend:latest`
- `{DOCKERHUB_USERNAME}/talior-frontend:latest`

## Caching Strategy

The pipeline implements several caching strategies to optimize build times:

1. **pnpm cache**: Caches pnpm dependencies for frontend builds
2. **SonarQube cache**: Caches SonarQube packages for code analysis
3. **Gradle cache**: Caches Gradle dependencies for backend builds
4. **Docker layer cache**: Uses GitHub Actions cache for Docker builds

## Job Dependencies

The workflow ensures proper job sequencing:

```
detect-changes
├── lint-frontend ──┐
├── sonarqube-frontend ──┐
├── unit-test ──┐        │
└── sonarqube-backend ──┐ │
                        │ │
                        ▼ ▼
                build-frontend
                        │
                        ▼
                build-backend
```

## Self-Hosted Runners

This pipeline is configured to run on self-hosted runners. Ensure your self-hosted runners have:

- Docker installed and configured
- Sufficient disk space for builds
- Network access to Docker Hub and SonarQube
- Java 21 and Node.js 20 available

## Troubleshooting

### Common Issues

1. **SonarQube Analysis Fails**

   - Verify `SONAR_TOKEN` and `SONAR_HOST_URL` secrets are correctly set
   - Check SonarQube server accessibility from the runner

2. **Docker Build Fails**

   - Ensure Docker Hub credentials are correct
   - Verify Docker is properly installed on the runner

3. **Cache Issues**
   - Clear GitHub Actions cache if builds become inconsistent
   - Check runner disk space availability

### Debugging

To debug pipeline issues:

1. Check job logs in the GitHub Actions tab
2. Verify all required secrets are configured
3. Ensure self-hosted runners are online and healthy
4. Review change detection logic if jobs are not triggering as expected

## Contributing

When modifying this workflow:

1. Test changes on a feature branch first
2. Ensure all job dependencies are correctly configured
3. Update this README if job structure or requirements change
4. Verify that caching strategies remain effective
