# Cross-platform build for Nuxt.js Frontend
# Uses native x86 builder for npm, then copies to ARM64 runtime

# Stage 1: Build on native x86 (no QEMU emulation needed)
FROM --platform=$BUILDPLATFORM node:20-alpine AS builder

# Install pnpm
RUN corepack enable && corepack prepare pnpm@10.28.2 --activate

WORKDIR /app

# Copy package files first for better caching
COPY package.json pnpm-lock.yaml pnpm-workspace.yaml ./

# Install dependencies (runs on native x86, fast!)
RUN pnpm install --frozen-lockfile

# Copy source code
COPY . .

# Build the application
RUN pnpm run build

# Stage 2: Runtime (ARM64 target)
FROM node:20-alpine

WORKDIR /app

# Create non-root user for security
RUN addgroup -g 1001 appgroup && \
    adduser -u 1001 -G appgroup -D appuser

# Copy built application from builder (cross-platform copy works fine)
COPY --from=builder /app/.output ./.output
COPY --from=builder /app/package.json ./

# Change ownership
RUN chown -R appuser:appgroup /app

USER appuser

# Expose port
EXPOSE 3000

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
    CMD wget -qO- http://localhost:3000/ || exit 1

# Set environment variables for production
ENV NODE_ENV=production
ENV HOST=0.0.0.0
ENV PORT=3000

# Run the application
CMD ["node", ".output/server/index.mjs"]
