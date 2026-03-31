# Environment Provisioning & Deployment Automation Tool (Java)

A Java-based CLI automation framework that simulates multi-environment deployment pipelines across DEV, TEST, and PROD configurations. The tool supports runtime profile detection (LOCAL, DOCKER, CLOUD), configuration validation, staged execution pipelines, rollback handling, and deployment logging.

## Features

* Multi-environment provisioning (DEV / TEST / PROD)
* Config-driven deployment pipeline execution
* Runtime environment detection (LOCAL / DOCKER / CLOUD)
* Database routing via environment variables
* Deployment health checks and validation
* Automated rollback simulation on pipeline failure
* CLI argument support for failure testing
* Execution summary reporting
* Maven build lifecycle integration

## Project Structure

src/main/java/com/envsetup

Core modules:

* ConfigLoader
* DeploymentPipeline
* EnvironmentProfileManager
* DockerDetector
* Validator
* RollbackManager
* LoggerUtility
* VersionManager
* ConfigSummaryPrinter

## Build Project

mvn clean package

## Run Application

java -jar target/envsetup-assistant-1.0.jar --env=DEV

## Simulate Failure Scenario

java -jar target/envsetup-assistant-1.0.jar --env=DEV --fail-db

## Example Output

Deployment Version: v1.0
Environment: DEV
Execution Mode: LOCAL
Deployment completed successfully !!

## Technologies Used

* Java 17
* Maven
* CLI Automation
* Properties-based Configuration
* Docker Runtime Detection Logic
