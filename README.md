# 선착순 서비스 - Worker

수량이 제한된 어떠한 대상이 있고 사용자들은 대상의 사용 권한을 얻기 위해 획득 요청을 한다.  
많은 사용자들이 제한된 대상 사용 권한을 갖기 위해 발생하는 다수의 요청을 견딜 수 있는 서버를 구축해보려고 한다. 

## 기술적 목표

### Kotlin

서버의 효율성 및 안정성을 위해 멀티 스레딩 기법을 이용하기로 결정했다.  
이번 프로젝트를 통해 Kotlin을 경험해본다.  
Kotlin에서 경량화된 스레드라고 할 수 있는 coroutine을 이용한다.

### SQS

API 서버의 부하를 줄이고 요청된 순서를 보장받기 위해 AWS SQS를 사용하기로 결정했다.

### DynamoDB

Serverless, Fully managed 환경인 NoSQL DB를 학습해보기 위해 AWS DynamoDB를 사용하기로 결정했다.  

---