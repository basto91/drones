# Drone Deployer Application with Docker

![Drone Image](drone_image.png)

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
   - [Using Docker](#using-docker)
- [Usage](#usage)
- [Deploying with Docker](#deploying-with-docker)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Drone  Application is a Spring Boot Java project designed to management drones for medical deliveries. 

## Features

- **Docker Integration:** Use Docker containers to deploy and manage drone instances.
- **Authentication:** Secure access to the system using authentication and authorization mechanisms.
- **Data Storage:** Store drone data, task history, and user information in a relational database.
- **RESTful API:** Expose a RESTful API for programmatic interaction.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 17 or higher.
- Gradle for building and managing dependencies.
- An Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.
- Docker installed on your machine.
- A relational database (e.g., PostgreSQL) for data storage.
- Git for version control (optional but recommended).

## Getting Started

To get started with the Drone Application, follow these steps:

1. Clone the repository:

   ```bash
   git clone git@github.com:basto91/drones.git
   cd drone
   
2. ```bash
   ./gradlew bootRun



