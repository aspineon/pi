TAG=0.1.$BUILD_NUMBER
IMG=nielsutrecht/pi
echo "TAG=$TAG" > .env

sudo docker login -u $DOCKER_USER -p $DOCKER_PASS
sudo docker build -t $IMG:$TAG -t $IMG:latest .
sudo docker-compose up -d
