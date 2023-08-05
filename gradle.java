
dependencies {

    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.7.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.annotation:annotation:1.6.0'

    testImplementation 'junit:junit:'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "androidx.browser:browser:1.5.0"
    implementation 'androidx.browser:browser:1.6.0-alpha01'
    implementation 'androidx.core:core:1.10.1'
    implementation 'androidx.recyclerview:recyclerview:1.3.1'
}
