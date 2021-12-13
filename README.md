# Android Project
## 2021-07-27 프로젝트 시작


## 2021-12-14 추가

## fragment 에서 View 애니메이션 구현하기

    // fragment 가 보이기 시작할때
    override fun onResume() {
        super.onResume()

        // 알파값 조정으로 fade in 구현하기
        ObjectAnimator.ofFloat(this.binding.fullscreenContent, View.ALPHA, 0f,1f).apply {
            duration = 2000
            start()
        }

        // View 가 회전하면서 돌아오기
        ObjectAnimator.ofFloat(this.binding.fullscreenContent, View.ROTATION, 180f,0f).apply {
            duration = 2000
            start()
        }

        // X 축으로 줄이고 늘리기 
        ObjectAnimator.ofFloat(this.binding.fullscreenContent, View.SCALE_X, 0.5f,5f,1f,0.5f,2.0f,1.0f).apply {
            duration = 2000
            start()
        }

    }

## 안드로이드 Resources

### status Bar 높이 구하기 1

    private fun getStatusBarHeight(): Int {
        var result = 0
        // val resourceId: Int = this.applicationContext.resources.getIdentifier("status_bar_height", "dimen", "android");
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            // result = this.applicationContext.resources.getDimensionPixelSize(resourceId)
            result = resources.getDimensionPixelSize(resourceId)
        }

        return result
    }

### status Bar 높이 구하기 2

        val statusHeight = getStatusBarHeight()

### navigation Bar 높이 구하기 1

    private fun getNaviBarHeight(): Int {

        var result = 0
        // val resources: Resources = this.application.resources
        val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }

        
        return result
    }

### navigation Bar 높이 구하기 2

    val naviHeight = getNaviBarHeight()


