Application Bundling

1. webpack: 작게 분리된 많은 애플리케이션 모듈(js, css, scss/sass, images, font)들을 '의존성 분석'을 하여 하나의 js로 묶는 도구

2. webpack의 최종 결과물인 하나의 js 파일을 번들(buddle)이라 하고 묶는 작업을 번들링(bundling)이라 한다.

3. 번들링은 단순히 모듈들을 하나의 파일로 묶는 작업을 의미하지 않는다.

4. 빌드작업(js)
    - linting(ESLint, 문법체크) 작업
    - document(JSDocs) 작업
    - test(Mocha, jest) 작업
    - 난독/압축(uglify) 작업
    - 번들링

5.  js 모듈뿐만 아니라 다양한 asset(images, css, sass/scss, font)들도 모듈로 취급한다.

6.  설치 패키지
    webpack : Core
    webpack-cli : Core 기능을 실행하기 위한 CLI 도구
    webpack-dev-server : 테스트를 위해 webpack에서 제공하는 개발서버
    loaders : css-loader, style-loader, sass-loader(include node-sass), babel-loader, etc ...

7.  webpack 설정(webpack.config.js)
    1. entry 설정
    2. 최종 결과물인 번들링 파일의 이름 및 위치
    3. 다양한 로더 설정
    4. 개발 서버 설정



== 실습 예제 =====

ex01: Bundling I: JS Module
ex02: Bundling Environment Configuration : webpack.config.js
ex03: Webpack Development Server
ex04: Bundling II: CSS Module
ex05: Bundling III: SASS/SCSS Module
ex06: Bundling IV: Image Module
