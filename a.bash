docker build -t monaco-test .
docker run -e PORT=8080 -p 8080:8080 monaco-test
