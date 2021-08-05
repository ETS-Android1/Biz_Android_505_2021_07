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
