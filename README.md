# NewsApp Android Application
* NewsApp is a sample application that display list of news and their details, along with the possibility to save bookmarks (Room database).<br/>
* the App consist of one Activity and three fragments
* This app module is build with MVVM and SingleActivity Architecture. The room database serves as single source of truth. The App is divided into three layers</br>
  * User interface
  * ViewModel
  * Repository

## Used Libraries
* [Room DataBase](https://developer.android.com/training/data-storage/room)
* [Glide Image Loader](https://github.com/bumptech/glide)
  * This is used to load and cache image from server
* [dagger2](https://dagger.dev/)
  * used for dependency injection
* [retrofit2](https://square.github.io/retrofit/) & [okHttp](https://square.github.io/okhttp/)
  * used for http calls (also certificate pinning is implemented t prevent man iin the middle attack)
* [androidx](https://developer.android.com/jetpack/androidx)
  * Navigation Components
  * RecycleView
  * Appcompat
  * Constraintlayout
  * Core-ktx
  * Lifecycle-livedata
  * Lifecycle-viewmodel


