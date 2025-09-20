# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Gracker is an Android board game management app built with Kotlin and Jetpack Compose. It helps users track their game collection, manage players, and record match details. The app follows MVVM architecture pattern and uses a multi-module structure.

## Development Commands

### Build & Assembly
```bash
./gradlew build                    # Build the entire project
./gradlew assemble                 # Assemble main outputs for all variants
./gradlew assembleDebug           # Build debug APK
./gradlew assembleRelease         # Build release APK
```

### Testing
```bash
./gradlew test                    # Run unit tests
./gradlew connectedAndroidTest    # Run instrumented tests
./gradlew koverHtmlReport         # Generate code coverage report
```

### Code Quality
```bash
./gradlew ktlintCheck             # Check Kotlin code style
./gradlew ktlintFormat            # Format Kotlin code
./gradlew detekt                  # Run static code analysis
```

### Project Setup
```bash
./config/setup.sh                # Setup git hooks for code quality checks
```

## Architecture & Module Structure

The project uses a multi-module architecture with the following modules:

- **`:app`** - Main application module with UI setup and navigation
- **`:core:network`** - Network layer with Retrofit services and API responses
- **`:history`** - Feature module for game history management
- **`:snapshot`** - Feature module for current game state/snapshots
- **`:build-logic`** - Custom Gradle plugins and build configuration

### Key Technologies
- **Jetpack Compose** - Modern declarative UI toolkit
- **Hilt** - Dependency injection framework
- **Retrofit** - HTTP client for API communication
- **Room** - Local database persistence
- **Kotlinx Serialization** - JSON serialization
- **Coroutines** - Asynchronous programming
- **Firebase** - Analytics, Crashlytics, and Performance monitoring

## Important Files & Configuration

### Build Configuration
- `build.gradle.kts` - Root build script with shared plugins
- `app/build.gradle.kts` - Main app module configuration
- `gradle.properties` - Gradle and Android project properties
- `secrets.properties` - Secret keys and API tokens (not in git)

### Code Quality
- `config/detekt/detekt.yml` - Detekt static analysis rules
- `config/hooks/pre-commit` - Git pre-commit hook for code formatting and quality checks
- `app/detekt-baseline.xml` - Detekt baseline for existing issues

### Required Setup
1. Add `google-services.json` file to the `app/` directory (Firebase configuration)
2. Fill `secrets.properties` with required API keys and tokens
3. Run `./config/setup.sh` to install git hooks

## Package Structure
All code follows the package structure: `br.com.gracker.*` or `br.com.bmattoso.gracker.*` (main app module)

## Testing Strategy
- Unit tests for business logic and data operations
- Instrumented tests for UI and integration scenarios
- Code coverage reporting via Kover plugin
- Coverage excludes BuildConfig, generated classes, Activities, and Compose functions

## Development Workflow
1. The pre-commit hook automatically runs `ktlintFormat`, `ktlintCheck`, and `detekt`
2. All code must pass static analysis before commits
3. Use `./gradlew build` to verify the entire project builds successfully
4. Firebase integration requires proper configuration files and secrets