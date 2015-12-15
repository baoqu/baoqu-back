all: docker-clean docker-run

docker: docker-clean docker-build docker-run docker-att

docker-clean:
	docker rm -f baoqu-back || echo "No baoqu-back image found"

docker-build:
	docker build -t baoqu/back -f docker/Dockerfile .

docker-run:
	docker run -it --name baoqu-back -p 5050:5050 -v ${PWD}:/app baoqu/back
