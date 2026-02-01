# Cross-platform build for Spring Boot Backend
# Uses native x86 builder for Gradle, then copies to ARM64 runtime

# Stage 1: Build on native x86 (no QEMU emulation - FAST!)
FROM --platform=$BUILDPLATFORM gradle:8.11-jdk21 AS builder

WORKDIR /app

# Copy gradle files first for better caching
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# Download dependencies (cached if build files don't change)
RUN gradle dependencies --no-daemon || true

# Copy source code
COPY src ./src

# Build the application (runs on x86, produces platform-independent JAR)
RUN gradle bootJar --no-daemon -x test

# Stage 2: Runtime (ARM64 target)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Create non-root user for security
RUN addgroup -g 1001 appgroup && \
    adduser -u 1001 -G appgroup -D appuser

# Copy built jar from builder stage (JAR is platform-independent!)
COPY --from=builder /app/build/libs/*.jar app.jar

# Change ownership
RUN chown -R appuser:appgroup /app

USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
