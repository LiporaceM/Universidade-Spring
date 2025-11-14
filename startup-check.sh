#!/bin/bash

echo "Universidade-Spring Startup Verification"
echo "=========================================="
echo ""

# Wait for PostgreSQL to be ready
echo "Waiting for PostgreSQL to start..."
sleep 10

# Check if the application is responding
echo "Checking if application is responding..."
for i in {1..30}; do
    if curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/swagger-ui.html | grep -q "200"; then
        echo "✓ Application is running and responding"
        echo ""
        echo "=========================================="
        echo "Universidade-Spring is ready!"
        echo "=========================================="
        echo ""
        echo "Access the following:"
        echo "- Swagger UI:  http://localhost:8080/swagger-ui.html"
        echo "- API Docs:    http://localhost:8080/v3/api-docs"
        echo ""
        echo "Available Endpoints:"
        echo "- GET    /api/alunos                 - Get all students"
        echo "- POST   /api/alunos                 - Create a new student"
        echo "- GET    /api/cursos                 - Get all courses"
        echo "- GET    /api/professores            - Get all professors"
        echo "- GET    /api/turmas                 - Get all classes"
        echo "- GET    /api/disciplinas            - Get all disciplines"
        echo "- GET    /api/inscricoes             - Get all enrollments"
        echo "- GET    /api/alunos/{id}/mensalidade - Get student tuition"
        echo ""
        exit 0
    fi
    
    if [ $((i % 5)) -eq 0 ]; then
        echo "Still waiting... ($i/30 seconds)"
    fi
    sleep 1
done

echo "✗ Application did not start properly"
echo "Check logs with: docker-compose logs app"
exit 1
