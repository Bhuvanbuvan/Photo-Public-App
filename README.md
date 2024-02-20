Navigation:
  1) gradle App Level Dependency:-


             classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${nav_version()}")
           
 2)gradle Module Level Dependency:->
             
             plugins{
             id("androidx.navigation.safeargs.kotlin")
             }
             
            //Navigation
            fun nav_version() = "2.7.7"
            // Kotlin
            implementation("androidx.navigation:navigation-fragment-ktx:${nav_version()}")
            implementation("androidx.navigation:navigation-ui-ktx:${nav_version()}")

FireBase:
1) Firestore:
     ->gradle App Level Dependency and Script

        buildscript {
        repositories {
            google()
        }
        dependencies {
            fun nav_version() = "2.7.7"
            classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${nav_version()}")
            classpath("com.google.gms:google-services:4.4.1")
        }
        }

  ->gradle modele Level Dependency and Plugins
          
          plugins{
          id("com.google.gms.google-services")
           }

           
  //Firestore
        
        implementation("com.google.firebase:firebase-firestore:24.10.2")
  
2) Storage:
     ->gradle modele Level Dependency and Plugins
   //FireBase Storage

        implementation("com.google.firebase:firebase-storage:20.3.0")


Picasso:
//picasso
    
      implementation("com.squareup.picasso:picasso:2.8")
     
   
  
