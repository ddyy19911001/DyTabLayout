# DyTabLayout
Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.ddyy19911001:DyTabLayout:1.0.2'
	}
Step 3. use androidx
        
        android.useAndroidX=true
        android.enableJetifier=true
