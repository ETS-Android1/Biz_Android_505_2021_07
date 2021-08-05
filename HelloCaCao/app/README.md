# Android App의 ActionBar Customizing
* Android App의 기본 Activity는 모두가 자동으로 구현되는
ActionBar를 가지고 있다. 하지만 자동으로 생성되는 ActionBar는
Customizing 하기가 어렵다.
* 자동으로 생성되는 ActionBar를 보이지 않도록 설정하고
임의로 ActionBar를 설정하여 Customizing을 수행한다.

## 순서
1. MainActivity에 NoActionBar Theme를 적용하여 자동
ActionBar를 감춘다

2. ToolBar View Component를 *.xml 파일에 추가하여 둔다
3. TooBar에 설정된 기본 Theme를 적용하기
4. MainActivity의 onCreate() method에서 설정한 Toolbar가 보이도록 설정

# Preference 만들기
* 어플에서 사용하는 "환경 설정"
* 어플이 실행되는데 필요한 여러가지 값들을 세팅하고,
그 세팅된 값을 유지기 하기 위한 정책
* settinngs Activity를 App에 추가하고
 root_preference.xml 파일에 필요한 항목들을 추가하고
 적당한 곳에서 해당 Activity를 열어서 값을 세팅하면
 App 전체에서 해당 값을 사용할수 있게 된다.

* setting된 값이 필요한 곳에서
SharePreference interface를 사용하여 객체를 선언하고
PreferenceManager.getDefaultSharedPreferences() method를 실행하여
전체 값을 읽어 들인다.
객체로 부터 getString() 등의 method를 사용하여 각각 세팅된 개별 변수값을
추출하여 적용할수 있다.






