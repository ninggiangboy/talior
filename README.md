# Talior

A modern recruitment and HR management system built with React and Spring Boot.

## 🚀 Overview

Talior is a comprehensive recruitment platform designed to streamline the hiring process, manage candidates, and provide insights into recruitment activities. The application features a modern, responsive interface with powerful backend capabilities.

## 🏗️ Architecture

This project follows a microservices architecture with:

- **Frontend**: React 19 with TypeScript, Vite, and Tailwind CSS
- **Backend**: Spring Boot 3.5 with Kotlin
- **UI Components**: Radix UI primitives with custom styling
- **State Management**: React hooks and context
- **Routing**: React Router v7
- **Form Handling**: React Hook Form with Zod validation

## 📁 Project Structure

```
talior/
├── frontend/                 # React TypeScript application
├── backend/                  # Spring Boot Kotlin application
└── design/                   # UI/UX design assets
```

## 🛠️ Tech Stack

### Frontend

- **React 19** - Modern React with concurrent features
- **TypeScript** - Type-safe JavaScript
- **Vite** - Fast build tool and dev server
- **Tailwind CSS** - Utility-first CSS framework
- **Radix UI** - Accessible UI primitives
- **React Router** - Client-side routing
- **React Hook Form** - Performant forms with validation
- **Zod** - TypeScript-first schema validation
- **Lucide React** - Beautiful icons
- **Recharts** - Composable charting library

### Backend

- **Spring Boot 3.5** - Java framework for building web applications
- **Kotlin** - Modern programming language for JVM
- **Gradle** - Build automation tool
- **JUnit 5** - Testing framework

### Development Tools

- **ESLint** - JavaScript linting
- **Prettier** - Code formatting
- **SonarQube** - Code quality analysis
- **ktlint** - Kotlin linting

## 🚀 Getting Started

### Prerequisites

- **Node.js** (v18 or higher)
- **pnpm** (recommended) or npm
- **Java 21** (for backend)
- **Gradle** (for backend)

### Frontend Setup

1. Navigate to the frontend directory:

   ```bash
   cd frontend
   ```

2. Install dependencies:

   ```bash
   pnpm install
   ```

3. Start the development server:

   ```bash
   pnpm dev
   ```

4. Open your browser and visit `http://localhost:5173`

### Backend Setup

1. Navigate to the backend directory:

   ```bash
   cd backend
   ```

2. Run the application:

   ```bash
   ./gradlew bootRun
   ```

3. The API will be available at `http://localhost:8080`

## 📝 Available Scripts

### Frontend Scripts

- `pnpm dev` - Start development server
- `pnpm build` - Build for production
- `pnpm preview` - Preview production build
- `pnpm lint` - Run ESLint
- `pnpm lint:fix` - Fix ESLint errors
- `pnpm format` - Format code with Prettier
- `pnpm format:check` - Check code formatting

### Backend Scripts

- `./gradlew bootRun` - Run the application
- `./gradlew build` - Build the application
- `./gradlew test` - Run tests
- `./gradlew ktlintCheck` - Check Kotlin code style

## 🐳 Docker Support

Both frontend and backend include Dockerfile configurations for containerized deployment.

### Frontend Docker

```bash
cd frontend
docker build -t talior-frontend .
docker run -p 80:80 talior-frontend
```

### Backend Docker

```bash
cd backend
docker build -t talior-backend .
docker run -p 8080:8080 talior-backend
```

## 🎨 Design System

The application uses a comprehensive design system with:

- **Radix UI** primitives for accessibility
- **Tailwind CSS** for styling
- **Custom components** built on top of Radix UI
- **Responsive design** for all screen sizes
- **Dark/light theme** support

## 📊 Features

Based on the design assets, the application includes:

- **Dashboard** - Overview and analytics
- **Candidate Management** - Track and manage job candidates
- **Recruitment Workflow** - Streamlined hiring process
- **Employee Management** - HR and employee data
- **Job Descriptions** - Create and manage job postings
- **Scheduling** - Calendar and interview scheduling
- **Overview & Analytics** - Recruitment insights and reporting

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

For support and questions, please open an issue in the GitHub repository.

---

Built with ❤️ using modern web technologies
