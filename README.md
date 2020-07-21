<h1 align="center">Tomato</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>  
</p>

<p align="center">
A sample restaurant viewer Android application which displays list of restaurants based on user location.This app was built on <b>Kotlin</b> using <b>Android Architecture components (ViewModel, LiveData, Room)</b>, <b>Retrofit</b>, <b>Coil</b> and <b>Coroutines</b>.
This app asks for user permission to access user location
The app also uses <b>Epoxy</b> library to display the complex Recycler View <br/>
</p>
</br>

<p align="center">
<img src="/previews/splash_screen.jpg" width=150/>
<img src = "/previews/main_screen.jpg" width=150 />
<img src = "/previews/restaurants_screen.jpg" width=150 />
<img src = "/previews/restaurant_details.jpg" width=150 />
</p>

## Download
Go to the [Releases](https://github.com/ManaswiniKundeti/Foodacious/releases) to download the lastest APK.

## App Features
- Collections: The home page of the app displays the collection of restaurants.
- Restaurants: Users can select the type of collection, which displays the list of restaurants in the collection.
- Details: Users can view the restaurant details by selecting the restaurant item.

## Open-source libraries
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/)
- Coroutines
- JetPack
  - LiveData
  - ViewModel
  - Room
- Architecture
  - MVVM Architecture (View - ViewModel - Model)
  - Repository pattern
- Google Play Location Services
- [Retrofit2](https://github.com/square/retrofit)
- [Coil](https://github.com/coil-kt/coil)
- [Epoxy](https://github.com/airbnb/epoxy).

## Architecture
Tomato is based on MVVM architecture and a repository pattern.

<img src=https://developer.android.com/topic/libraries/architecture/images/final-architecture.png width=500>

## API

Tomato uses the [Zomato Api](https://developers.zomato.com/documentation#!/)

## Find this repository useful?
Support it by joining __[stargazers](https://github.com/ManaswiniKundeti/Foodacious/stargazers)__ for this repository.<br>

# License
```xml
Designed and developed by 2020 ManaswiniKundeti (Manaswini Kundeti)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

