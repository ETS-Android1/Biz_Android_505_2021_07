# DataBinding Activity
* 기존의 Activity에서 *.xml에 정의된 Component(view)를 사용하기 위해서는
각 View를 객체로 선언하고 findByViewId() 함수를 사용하여 객체와 Component를 연결하는
작업이 필요했다

* Rollipop(5.0) 버전부터 findByViewId()를 사용하지 않고 Component를 사용할수 있게 되었다
이때 도입된 개념이 DataBinding 이다

* 원래 DataBinding 개념은 View Component와 데이터를 연동하기 쉽도록 하기위해서 도입되었다
* 이 개념이 확장되면서 findByViewId()를 사용하지 않고 Component를 쓸수있도록 만들어졌다
* 초기에 도입된 DataBinding과 현재(2021년) 사용하는 방법에는 다소 차이가 있다
만드는 방법, 프로젝트에 설정하는 방법에서 차이가 있지만 기본 개념은 같다

## app build.gradle에 속성 추가
* app build.gradle의 android 항목에 다음 속성을 추가한다
    buildFeatures {
        viewBinding true
    }

* old Android에서는 *.xml 파일에 직접 binding하는 속성을 추가해야 한다


# Fragment Activity
* 초기에 Android에서는 모든 화면을 Activity 단위로 만들고 사용을 했다
* Activity는 만들고 화면에 보여지기 까지 많은 resouce(메인 메모리)를 사용한다
* App에 보여주고자 하는 화면이 많으면 App 자체가 느려지거나 오류가 발생하는 경우가
점점 많아진다
* 이런 문제 들 때문에 Android에서는 Fragment라는 개념을 도입하게 된다.
* 여러개의 화면(UI) 가 있는 App에서는 화면과 화면사이에 빠른 전환이 이루어져야 하고
화면이 전환되는 과정에서 App에 오류가 발생하지 않아야 한다
* 하지만 Activity는 이러한 문제를 해결하는데 많은 개발자의 노력이 필요했다
* Android에서는 Fragment를 도입하여 작은 화면을 구현하고, 필요할 경우 바꿔가면서 보여주는
개념을 만들게 된 것이다.
* 사용자에게는 Activity인지 Fragment 인지 관계없이 같은 방식으로 화면을 전환 하지만
개발자 입장에서, 시스템적 입장에서는 상당히 유리한 방법이 된다.
* 기존에 Activity방식에 익숙한 개발자에게는 다소 생소한 개념이 되기도 하였다

* Fragment를 한마디로 정의하면 동적인 작은 Activity다 라고 할 수 있다








