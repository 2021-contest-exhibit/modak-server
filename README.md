
# 1. 환경설정 및 실행

<details> <summary> 1. mysql </summary> 

## 1. mysql 
- 도커 mysql 명령어

**일반 명령어**

```
docker run -d -p 3306:3306 \
-e MYSQL_ALLOW_EMPTY_PASSWORD=true \
--name mysql \
-v /Users/singyeongdeog/Documents/mysql:/var/lib/mysql \
mysql:5.7 --character-set-server=utf8 --collation-server=utf8_unicode_ci 
```

**m1 전용 명령어**

```
docker run --platform linux/amd64 -d -p 3306:3306 \
-e MYSQL_ALLOW_EMPTY_PASSWORD=true \
--name mysql \
-v /Users/singyeongdeog/Documents/mysql:/var/lib/mysql \
mysql:5.7 --character-set-server=utf8 --collation-server=utf8_unicode_ci
```
 
- 도커 mysql 접속
`$ docker exec -it mysql mysql`

- db 생성
`mysql> create database modak;`

</details>      

<details> <summary> 2. spring boot 실행 </summary> 

## 2. spring boot 실행
- `./gradlew bootRun`

</details>

# 2. 개발 기간

# 3. 결과

# 4. 제안 배경 & 기획 의도

# 5. 요구분석

# 6. 개발 환경 및 개발 언어

# 7. DB 설계

# 8. 주요기능 

 