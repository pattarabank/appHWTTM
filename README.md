# APPLICATION FOR HEALTH WITH TRADITIONAL THAI MEDICINE
## Github link:
https://github.com/pattarabank/appHWTTM
## Installation
1.Download lastest version of ApplicationForHealthWithTraditionalThaiMedicine.apk file from here
https://github.com/pattarabank/appHWTTM/releases
2.Install ApplicationForHealthWithTraditionalThaiMedicine.apk in your android device.
## Installation for developer
1.Clone this git to your Android Studio.
2.Add Firebase to your Android project (following this instruction document https://firebase.google.com/docs/android/setup)
or add Firebase using the Firebase Assistant in Android Studio.
3.Add Firebase Cloud Firestore and Firebase Authentication to your Android project.
4.Installation complete.
## Features
- Help users to access Thai traditional medicine and Thai herbal remedies.
- Help users increase convenience and speed in finding information on basic self-care.
- Help users keep track of their health to be useful in future treatment.
## Direction tree
```bash
📦appHWTTM-main
 ┣ 📂.idea
 ┃ ┣ 📂codeStyles
 ┃ ┃ ┣ 📜codeStyleConfig.xml
 ┃ ┃ ┗ 📜Project.xml
 ┃ ┣ 📜.gitignore
 ┃ ┣ 📜compiler.xml
 ┃ ┣ 📜gradle.xml
 ┃ ┣ 📜jarRepositories.xml
 ┃ ┣ 📜misc.xml
 ┃ ┣ 📜runConfigurations.xml
 ┃ ┗ 📜vcs.xml
 ┣ 📂app
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂androidTest
 ┃ ┃ ┃ ┗ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂apphwttm
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleInstrumentedTest.kt
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂apphwttm
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂admin
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂manage_disease
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AddDiseaseDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteDiseaseAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteDiseaseData2Activity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteDiseaseDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EditDiseaseData2Activity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EditDiseaseDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ManageDataDiseaseAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂manage_firstAid
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AddFirstAidDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteFirstAidAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteFirstAidData2Activity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteFirstAidDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EditFirstAidData2Activity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EditFirstAidDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ManageDataFirstAidAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂manage_herb
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AddHerbDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteHerbAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteHerbData2Activity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteHerbDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EditHerbData2Activity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EditHerbDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ManageDataHerbAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ManageDataActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂calendar
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CalendarActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CalendarDetailBadActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CalendarDetailGoodActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂data_model
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DiseaseSearchModel.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FirstAidModel.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜HerbSearchModel.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂healthRecord
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AddRecordDetailActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AreYouOk2Activity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AreYouOkActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AreYouOkAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AreYouOkModel.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HealthCareActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RelateDiseaseActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂searchPage
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂body
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DiseaseBodyActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂disease
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ActivityDiseaseSearch.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DetailDiseaseSearch.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NewDetailDiseaseSearchActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NewSearchListDiseaseAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SearchListDiseaseAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂firstaid
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ActivityFirstAidSearch.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DetailFirstAidSearch.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SearchListFirstAidAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂head
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DiseaseHeadActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂herb
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ActivityHerbSearch.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DetailHerbSearch.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SearchListHerbAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂lowerbody
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DiseaseLowerBodyActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂relate_herb
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RelateHerbActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AmFineActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HomeActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MainActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜myDateInTH.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ProfileActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RegisterActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SearchActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SplashScreen.kt
 ┃ ┃ ┃ ┣ 📂res
 ┃ ┃ ┃ ┃ ┣ 📂color
 ┃ ┃ ┃ ┃ ┃ ┗ 📜nav_icon_color.xml
 ┃ ┃ ┃ ┃ ┣ 📂drawable
 ┃ ┃ ┃ ┃ ┃ ┣ 📜calendar_rounded.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜close.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜custom_search_bg.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜embarrassed.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜gray.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜green.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜happy.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_baseline_check_24.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_baseline_createdata_24.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_baseline_delete_24.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_baseline_edit_24.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_calendar.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_home.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_launcher_background.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_medical_checkup.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_profile_user.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic__new_icon_forg.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜left_arrow.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜neutral.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜red.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜rounded.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜sad.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜search_body_pic.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜settings.png
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ss_pic.png
 ┃ ┃ ┃ ┃ ┃ ┗ 📜usericon.png
 ┃ ┃ ┃ ┃ ┣ 📂drawable-v24
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ic_launcher_foreground.xml
 ┃ ┃ ┃ ┃ ┣ 📂font
 ┃ ┃ ┃ ┃ ┃ ┣ 📜comfortaa.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜delius.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜kodchasan.xml
 ┃ ┃ ┃ ┃ ┃ ┗ 📜kodchasan_regular.ttf
 ┃ ┃ ┃ ┃ ┣ 📂layout
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_add_disease_data.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_add_first_aid_data.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_add_herb_data.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_add_record_detail.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_admin.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_am_fine.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_are_you_ok.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_are_you_ok2.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_calendar.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_calendar_detail_bad.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_calendar_detail_good.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_delete_disease_data.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_delete_disease_data2.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_delete_first_aid_data.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_delete_first_aid_data2.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_delete_herb.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_delete_herb_data2.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_detail_disease_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_detail_first_aid_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_detail_herb_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_disease_body.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_disease_head.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_disease_lower_body.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_disease_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_edit_disease_data.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_edit_disease_data2.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_edit_first_aid_data.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_edit_first_aid_data2.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_edit_herb.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_edit_herb_data2.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_first_aid_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_health_care.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_herb_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_home.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_main.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_manage_data.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_new_detail_disease_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_profile.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_register.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_relate_disease.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_relate_herb.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜activity_splash_screen.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜add_disease_dropdown_items.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜custom_listview.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜single_item_chip.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜single_item_disease_search.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜single_item_firstaid_search.xml
 ┃ ┃ ┃ ┃ ┃ ┗ 📜single_item_herb_search.xml
 ┃ ┃ ┃ ┃ ┣ 📂menu
 ┃ ┃ ┃ ┃ ┃ ┣ 📜data_menu.xml
 ┃ ┃ ┃ ┃ ┃ ┗ 📜menu.xml
 ┃ ┃ ┃ ┃ ┣ 📂mipmap-anydpi-v26
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_launcher.xml
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ic_launcher_round.xml
 ┃ ┃ ┃ ┃ ┣ 📂mipmap-hdpi
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_launcher.png
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ic_launcher_round.png
 ┃ ┃ ┃ ┃ ┣ 📂mipmap-mdpi
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_launcher.png
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ic_launcher_round.png
 ┃ ┃ ┃ ┃ ┣ 📂mipmap-xhdpi
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_launcher.png
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ic_launcher_round.png
 ┃ ┃ ┃ ┃ ┣ 📂mipmap-xxhdpi
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_launcher.png
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ic_launcher_round.png
 ┃ ┃ ┃ ┃ ┣ 📂mipmap-xxxhdpi
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ic_launcher.png
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ic_launcher_round.png
 ┃ ┃ ┃ ┃ ┣ 📂values
 ┃ ┃ ┃ ┃ ┃ ┣ 📜colors.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜font_certs.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜preloaded_fonts.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜strings.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜style.xml
 ┃ ┃ ┃ ┃ ┃ ┗ 📜themes.xml
 ┃ ┃ ┃ ┃ ┗ 📂values-night
 ┃ ┃ ┃ ┃ ┃ ┗ 📜themes.xml
 ┃ ┃ ┃ ┗ 📜AndroidManifest.xml
 ┃ ┃ ┗ 📂test
 ┃ ┃ ┃ ┗ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂apphwttm
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleUnitTest.kt
 ┃ ┣ 📜.gitignore
 ┃ ┣ 📜build.gradle
 ┃ ┣ 📜google-services.json
 ┃ ┗ 📜proguard-rules.pro
 ┣ 📂gradle
 ┃ ┗ 📂wrapper
 ┃ ┃ ┣ 📜gradle-wrapper.jar
 ┃ ┃ ┗ 📜gradle-wrapper.properties
 ┣ 📜.gitattributes
 ┣ 📜.gitignore
 ┣ 📜gradle.properties
 ┣ 📜gradlew
 ┣ 📜gradlew.bat
 ┣ 📜README.md
 ┗ 📜settings.gradle

```
