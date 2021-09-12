# camping


## 1. mysql 
- 도커 mysql 명령어 
```
docker run -d -p 3306:3306 \
-e MYSQL_ALLOW_EMPTY_PASSWORD=true \
--name mysql \
-v /Users/singyeongdeog/Documents/mysql:/var/lib/mysql \
mysql:5.7
```
 
- 도커 mysql 접속
`$ docker exec -it mysql mysql`

- db 생성
`mysql> create database modak;`
      
## 2. spring boot 실행
- `./gradlew bootRun`