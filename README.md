![](https://velog.velcdn.com/images/witwint/post/abbd47e5-dcbd-4739-871c-ade9bf7de24e/image.png)

# 와글와글
---
```
지역기반 소통 커뮤니티

진행기간 :  2022.11.01 > 진행중
팀구성 : 디자이너2명 프론트2명 백엔드2명
```

## 기획배경

![](https://velog.velcdn.com/images/witwint/post/a9e0b9e1-c03c-42c0-a4f2-4274cbb04866/image.jpg)


## Tool
### Backend 
<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=Spring&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/>
   <img src="https://img.shields.io/badge/H2-007396?style=flat&logo=H2&logoColor=white"/>

### Frontend

<img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=React&logoColor=white"/>

---
## ERD
![](https://velog.velcdn.com/images/witwint/post/74be5e7b-7b96-4bfa-a652-a8e5d5cadbae/image.PNG)

---
## 기능소개

### 로그인
![](https://velog.velcdn.com/images/witwint/post/23525004-3ae2-4a6d-b3c5-9f620c3148fb/image.PNG)

### 메인 & 커뮤니티
![](https://velog.velcdn.com/images/witwint/post/2a3aac56-173a-4c6a-bba4-a6c366e8fea3/image.PNG)

### 마이페이지
![](https://velog.velcdn.com/images/witwint/post/393fa09b-7adb-4340-82f3-14fe9263fc7e/image.PNG)

### 유저온도
![](https://velog.velcdn.com/images/witwint/post/66184db9-98b4-433f-947f-961c60c778ec/image.PNG)

---

## Backend

### swagger
![](https://velog.velcdn.com/images/witwint/post/1211586b-e355-48be-a116-2dabb342c7ac/image.PNG)

![](https://velog.velcdn.com/images/witwint/post/d6941e33-3166-4a40-9fee-595856d8ca29/image.PNG)

### h2
![](https://velog.velcdn.com/images/witwint/post/55a22af1-7ff2-49c5-8c4b-58e7de79f089/image.PNG)

### api구조화
``` java
@Service("responseService")
public class ResponseService {

    // 단일건 결과 처리 메소드
    public <T> SingleResult<T> getSingleResult(final T data) {
        final SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // 복수건 결과 처리 메서드
    public <T> ListResult<T> getListResult(final List<T> list) {
        final ListResult<T> result = new ListResult<>();
        result.setData(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과만 처리
    public CommonResult getSuccessResult() {
        final CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    public CommonResult getSuccessResult(final String msg) {
        final CommonResult result = new CommonResult();
        setSuccessResult(result);
        setSuccessResult(result, msg);
        return result;
    }

    // 실패 결과만 처리
    public CommonResult getFailResult() {
        final CommonResult result = new CommonResult();
        result.setSuccess(false);
        setFailResult(result);
        return result;
    }

    public CommonResult getFailResult(final String msg) {
        final CommonResult result = new CommonResult();
        result.setSuccess(false);
        setFailResult(result, -1, msg);
        return result;
    }

    public CommonResult getFailResult(final int code, final String msg) {
        final CommonResult result = new CommonResult();
        result.setSuccess(false);
        setFailResult(result, code, msg);
        return result;
    }

    // API 요청 성공 시 응답 모델을 성공 데이터로 세팅
    private void setSuccessResult(final CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(CommonResponse.SUCCESS.getMessage());
    }

    private void setSuccessResult(final CommonResult result, final String msg) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(msg);
    }

    private void setFailResult(final CommonResult result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMessage(CommonResponse.FAIL.getMessage());
    }

    // API 요청 실패 시 응답 모델을 실패 데이터로 세팅
    private void setFailResult(final CommonResult result, final int code, final String msg) {
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(msg);
    }

}
```